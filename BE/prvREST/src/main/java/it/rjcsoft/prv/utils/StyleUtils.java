package it.rjcsoft.prv.utils;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.geotools.coverage.grid.GridCoordinates2D;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridCoverage2DReader;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.util.factory.Hints;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.rjcsoft.prv.dto.stili.StiliDTO;
import it.rjcsoft.prv.enums.SCALE;

public class StyleUtils {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	private static final String PNG_EXT = "png";
	private Double globalMaxValue = null;
	private Double currMaxValue = null;
	private Double currMinValue = null;
	private SCALE scale;
	private Double maxValue;
	private Double minValue;
	private boolean useOpacity;
	private Double fixedOpacity;
	private Color mainColor;
	private boolean useSpectrum;
	private boolean useReverseGradient;
	private final DecimalFormat decimalFormat = new DecimalFormat("0.000");
	private List<Double> userLevels = null;

	public StyleUtils(Color mainColor, Double minValue, Double maxValue, boolean useOpacity, Double fixedOpacity,
			boolean useSpectrum, boolean useReverseGradient, SCALE scale, List<Double> userLevels) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.mainColor = mainColor;
		this.useOpacity = useOpacity;
		this.fixedOpacity = fixedOpacity;
		this.useSpectrum = useSpectrum;
		this.useReverseGradient = useReverseGradient;
		this.scale = scale;
		this.userLevels = userLevels;
	}

	public StiliDTO convert(File input, Integer idGeotiff, String uom) throws IllegalArgumentException, IOException {

		log.info("START - Check input:{}", input.getAbsolutePath());

		AbstractGridFormat format = GridFormatFinder.findFormat(input);
		Hints hints = null;
		if (format instanceof GeoTiffFormat) {
			hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
		}
		AbstractGridCoverage2DReader reader = format.getReader(input, hints);

		GridCoverage2D grid = reader.read(null);
		// -----
		GridEnvelope dimensions = reader.getOriginalGridRange();
		GridCoordinates maxDimensions = dimensions.getHigh();
		int w = maxDimensions.getCoordinateValue(0) + 1;
		int h = maxDimensions.getCoordinateValue(1) + 1;
		int numBands = 1;// reader.getGridCoverageCount();

		log.info("w={}, h={}, numBands={}, USE 1 as default band", w, h, numBands);

		currMinValue = Double.MAX_VALUE;
		currMaxValue = Double.MIN_VALUE;
		for (int x = 0; x < w; x += 100) {
			for (int y = 0; y < h; y += 100) {
				double[] vals = new double[numBands];
				grid.evaluate(new GridCoordinates2D(x, y), vals);
				currMinValue = Math.min(currMinValue, vals[0]);
				currMaxValue = Math.max(currMaxValue, vals[0]);
			}
		}

		globalMaxValue = currMaxValue;

		if (minValue != null) {
			currMinValue = Math.max(minValue, currMinValue);
		}
		if (maxValue != null) {
			currMaxValue = Math.min(maxValue, currMaxValue);
		}
		if (CollectionUtils.isEmpty(userLevels)) {
			buildLevels(256, 2.0);
		}

		BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Map<Double, Color> colorsMap = new HashMap<>();
		double userLevelsSize = userLevels.size();
		for (int i = 0; i < userLevelsSize; i++) {
			Color pixelColor = getPixelColor((i / userLevelsSize), useOpacity);
			colorsMap.put(userLevels.get(i), pixelColor);
		}
		drawImage(w, h, userLevels, grid, numBands, colorsMap, bufferedImage);

		Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
		g2.drawImage(bufferedImage, 0, 0, null);
		BufferedImage scaledBackgroundImage = new BufferedImage(bufferedImage.getWidth() * 2,
				bufferedImage.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(2.0, 2.0);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		scaledBackgroundImage = scaleOp.filter(bufferedImage, scaledBackgroundImage);

		Pair<String, Double> pair = new ImmutablePair<>(uom, 0.01);
		BufferedImage barBuffImage = drawColorBar(colorsMap, pair);
		return new StiliDTO(idGeotiff, Base64.encodeBase64String(GeoTiffUtils.toByteArray(scaledBackgroundImage, PNG_EXT)), Base64.encodeBase64String(GeoTiffUtils.toByteArray(barBuffImage, PNG_EXT)));		
	}

	private void drawImage(int w, int h, List<Double> values, final GridCoverage2D grid, int numBands,
			Map<Double, Color> colorsMap, final BufferedImage bufferedImage) {
		final Integer step = 150;
		final Semaphore sem = new Semaphore(0);
		final AtomicInteger atomicInteger = new AtomicInteger(-1);
		for (int i = 0; i < step; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					int start = -1;
					try {
						GridCoordinates2D currPoint = new GridCoordinates2D();
						start = atomicInteger.incrementAndGet();
						for (currPoint.x = start; currPoint.x < w; currPoint.x += step) {
							for (currPoint.y = 0; currPoint.y < h; currPoint.y++) {
								drawPixel(currPoint, numBands, grid, values);
							}
						}
					} finally {
						sem.release();
					}
				}

				private void drawPixel(GridCoordinates2D currPoint, int numBands, GridCoverage2D grid,
						List<Double> values) {
					try {
						double[] vals = new double[numBands];
						grid.evaluate(currPoint, vals);
						double rValue = getClosedValue(vals[0], values);
						Color pixelColor = colorsMap.get(rValue);
						bufferedImage.setRGB(currPoint.x, currPoint.y, pixelColor.getRGB());
					} catch (Exception e) {
					}

				}
			});
			t.start();
		}
		try {
			sem.acquire(step);
			log.debug("END - Drawing completed");
		} catch (InterruptedException e) {
		}

	}

	private Double getClosedValue(double valueToCheck, List<Double> values) {
		Double retValue = values.get(0);
		for (Double currValue : values) {
			if (currValue > valueToCheck) {
				break;
			}
			retValue = currValue;
		}
		return retValue;
	}

	private BufferedImage drawColorBar(Map<Double, Color> map, Pair<String, Double> unit) {
		BufferedImage barBuffImage = null;
		if (map == null || unit == null) {
			return barBuffImage;
		}
		int wCell = 10;
		int maxHeight = 250;
		int hCell = 1;
		int hChar = 16;
		try {
			barBuffImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g1 = (Graphics2D) barBuffImage.getGraphics();
			FontMetrics metrics = g1.getFontMetrics();
			boolean useDecimal = currMaxValue < 1000;
			String textMaxValue = getTextMaxValue(useDecimal, unit);
			int maxBarW = (int) (wCell * 1.5) + metrics.stringWidth((currMaxValue) + unit.getLeft());
			if (currMaxValue >= 0.0) {
				maxBarW = metrics.stringWidth(textMaxValue);
			}
			barBuffImage = new BufferedImage(maxBarW, maxHeight + metrics.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
			g1 = (Graphics2D) barBuffImage.getGraphics();
			g1.setColor(Color.BLACK);
			List<Double> keys = new ArrayList<>(map.keySet());
			Collections.sort(keys);
			Collections.reverse(keys);
			int x = (int) (wCell * 1.5);
			double step = map.size() / (double) maxHeight;
			for (int i = 0; i < maxHeight; i++) {
				int index = (int) Math.round(i * step);
				Double double1 = keys.get(Math.min(index, keys.size() - 1));
				String currValue = (!useDecimal ? Integer.toString((int) (double1.doubleValue()))
						: decimalFormat.format(double1));
				currValue += " " + unit.getLeft();
				boolean isLast = ((i + 1) == maxHeight);
				if ((isLast || ((i % hChar) == 0 && (maxHeight - i) > hChar))) {
					int y = isLast ? maxHeight + 8 : (i) + hChar;
					g1.setColor(Color.BLACK);
					g1.drawString(currValue, x, y);
				}
				Color color = map.get(double1);
				if (color == null) {
					color = getPixelColor(step, false);
				}
				g1.setColor(color);
				g1.fillRect(0, 8 + hCell * i, wCell, hCell);
			}
			g1.setColor(Color.BLACK);
			g1.drawRect(0, 8, wCell, maxHeight);
			g1.drawString(textMaxValue, 1, barBuffImage.getHeight() - 5);
		} catch (Exception e) {
			log.error(e.getMessage());
			barBuffImage = null;
		}
		return barBuffImage;
	}

	private String getTextMaxValue(boolean useDecimal, Pair<String, Double> unit) {
		String textMaxValue = "(*) MAX:";
		if (unit != null) {
			if (!useDecimal) {
				textMaxValue += ((int) (globalMaxValue.doubleValue()));
			} else {
				textMaxValue += (decimalFormat.format(globalMaxValue));
			}
			textMaxValue += "  " + unit.getLeft();
		}
		return textMaxValue;
	}

	private Color getPixelColor(Double d, boolean useOpacity) {
		float[] compArray = new float[4];
		Color.RGBtoHSB(mainColor.getRed(), mainColor.getGreen(), mainColor.getBlue(), compArray);
		compArray[1] = useReverseGradient ? 1.0f - d.floatValue() : d.floatValue();
		compArray[1] = Math.max(Math.min(compArray[1], 0.999f), 0.0f);
		if (useSpectrum) {
			compArray[0] = compArray[1];
		}
		float op = 1.0f;
		if (useOpacity) {
			op = fixedOpacity != null ? fixedOpacity.floatValue() : compArray[1];
		}
		op = Math.max(op, 0.0f);
		op = Math.min(op, 0.999f);
		Color cc = new Color(Color.HSBtoRGB(compArray[0], compArray[1], compArray[2]));
		cc.getRGBComponents(compArray);
		return new Color(compArray[0], compArray[1], compArray[2], op);
	}

	public void buildLevels(int numClasses, double parabola_exp) {
		if (numClasses < 2) {
			numClasses = 2;
		}
		if (scale == SCALE.PARABOLIC) {
			userLevels = buildLevelsPar(currMaxValue, currMinValue, numClasses - 2, parabola_exp);
		} else {
			userLevels = (scale == SCALE.LINEAR) ? buildLevelsLin(currMaxValue, currMinValue, numClasses - 2)
					: buildLevelsLog(currMaxValue, currMinValue, numClasses - 2);
		}
	}

	public static List<Double> buildLevelsPar(Double currMaxValue, Double currMinValue, int numClasses, double exp) {
		double deltaStep = 1.0 / numClasses;
		double delta = currMaxValue - currMinValue;
		List<Double> values = new ArrayList<>();
		for (double x = 0; x <= 1.0; x += deltaStep) {
			double d = currMinValue + (delta) * Math.pow(x, exp);
			values.add(d);
		}
		if (!values.contains(currMaxValue)) {
			values.add(currMaxValue);
		}
		return values;
	}

	public static List<Double> buildLevelsLin(Double currMaxValue, Double currMinValue, int numClasses) {
		double delta = currMaxValue - currMinValue;
		double deltaStep = delta / numClasses;
		List<Double> values = new ArrayList<>();
		for (double x = 0; x < delta; x += deltaStep) {
			values.add(currMinValue + x);
		}
		if (!values.contains(currMaxValue)) {
			values.add(currMaxValue);
		}
		return values;
	}

	public static List<Double> buildLevelsLog(Double currMaxValue, Double currMinValue, int numClasses) {
		double deltaStep = 9.0 / numClasses;
		double delta = currMaxValue - currMinValue;
		List<Double> values = new ArrayList<>();
		values.add(currMinValue);
		for (double x = 1; x <= 10.0; x += deltaStep) {
			double d = currMinValue + (delta) * Math.log10(x);
			values.add(d);
		}
		if (!values.contains(currMaxValue)) {
			values.add(currMaxValue);
		}
		return values;
	}

}
