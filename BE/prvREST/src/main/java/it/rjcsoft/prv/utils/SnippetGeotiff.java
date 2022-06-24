package it.rjcsoft.prv.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.geotools.coverage.grid.GridCoordinates2D;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridGeometry2D;
import org.geotools.coverage.grid.io.AbstractGridCoverage2DReader;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.map.GridCoverageLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.ColorMap;
import org.geotools.styling.ColorMapEntry;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.util.factory.Hints;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridEnvelope;
import org.opengis.filter.FilterFactory2;
import org.opengis.referencing.operation.TransformException;

public class SnippetGeotiff {

	public static int getRGB(int index) {
		int[] p = getPattern(index);
		return getElement(p[0]) << 16 | getElement(p[1]) << 8 | getElement(p[2]);
	}

	public static int getElement(int index) {
		int value = index - 1;
		int v = 0;
		for (int i = 0; i < 8; i++) {
			v = v | (value & 1);
			v <<= 1;
			value >>= 1;
		}
		v >>= 1;
		return v & 0xFF;
	}

	public static int[] getPattern(int index) {
		index += 2;
		int n = (int) Math.cbrt(index);
		index -= (n * n * n);
		int[] p = new int[3];
		Arrays.fill(p, n);
		if (index == 0) {
			return p;
		}
		index--;
		int v = index % 3;
		index = index / 3;
		if (index < n) {
			p[v] = index % n;
			return p;
		}
		index -= n;
		p[v] = index / n;
		p[++v % 3] = index % n;
		return p;
	}

	private static int getComplementaryColor(int color) {
		int R = color & 255;
		int G = (color >> 8) & 255;
		int B = (color >> 16) & 255;
		int A = (color >> 24) & 255;
		R = 255 - R;
		G = 255 - G;
		B = 255 - B;
		return R + (G << 8) + (B << 16) + (A << 24);
	}

	private static Style createStyleOLD(int band, double min, double max, int indexStart, int indexEnd) {

		FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
		StyleFactory sf = CommonFactoryFinder.getStyleFactory();

		RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
		ColorMap cMap = sf.createColorMap();
		ColorMapEntry start = sf.createColorMapEntry();
		start.setColor(ff.literal(getRGB(indexStart)));
		start.setQuantity(ff.literal(min));
		ColorMapEntry end = sf.createColorMapEntry();
		end.setColor(ff.literal(getRGB(indexEnd)));
		end.setQuantity(ff.literal(max));

		cMap.addColorMapEntry(start);
		cMap.addColorMapEntry(end);
		sym.setColorMap(cMap);
		return SLD.wrapSymbolizers(sym);

	}

	private static Style createStyle(double min, double max, int numClasses) {
		List<Double> levels = buildLevels(min, max, numClasses);
		FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
		StyleFactory sf = CommonFactoryFinder.getStyleFactory();

		RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
		ColorMap cMap = sf.createColorMap();
		for (int i = 0; i < levels.size(); i++) {
			Double currValue = levels.get(i);
			if(currValue>max) {
				continue;
			}
			ColorMapEntry end = sf.createColorMapEntry();
			switch (i % 9) {
			case 0:
				end.setColor(ff.literal(Color.WHITE.getRGB()));
				break;
			case 1:
				end.setColor(ff.literal(Color.BLUE.getRGB()));
				break;
			case 2:
				end.setColor(ff.literal(Color.RED.getRGB()));
				break;
			case 3:
				end.setColor(ff.literal(Color.CYAN.getRGB()));
				break;
			case 4:
				end.setColor(ff.literal(Color.MAGENTA.getRGB()));
				break;
			case 5:
				end.setColor(ff.literal(Color.YELLOW.getRGB()));
				break;
			case 6:
				end.setColor(ff.literal(Color.DARK_GRAY.getRGB()));
				break;
			case 7:
				end.setColor(ff.literal(Color.GREEN.getRGB()));
				break;
			default:
				end.setColor(ff.literal(Color.BLACK.getRGB()));
				break;
			}
			end.setQuantity(ff.literal(currValue.doubleValue()));

			cMap.addColorMapEntry(end);
		}

		sym.setColorMap(cMap);
		return SLD.wrapSymbolizers(sym);

	}

	public static List<Double> buildLevels(double min, double max, int numClasses) {
		if (numClasses <= 1) {
			numClasses = 2;
		}
		double deltaStep = (10.0) / numClasses;
		System.out.println("::::min=" + min + " max=" + max + " numClasses=" + numClasses + " deltaStep=" + deltaStep);
		List<Double> values = new ArrayList<>();
		values.add(min);
		System.out.println(1 + "\t" + Math.log10(1));
		for (double x = deltaStep; x < 10; x += deltaStep) {
			double d2 = 10.0 - x;
			if(d2<1) {
				continue;
			}
			double d = 1.0 - Math.log10(d2);
			System.out.println(x + "\t" + d);
			values.add(min + (max - min) * d);
		}
		System.out.println(10 + "\t" + Math.log10(10));
		values.add(max);
		// Collections.sort(values);
		System.out.println(StringUtils.join(values, " _ "));
		return values;
	}

	public static List<Double> buildLevelsLinear(double min, double max, int numClasses) {
		if (numClasses <= 1) {
			numClasses = 2;
		}
		double deltaStep = (max - min) / numClasses;
		System.out.println("::::min=" + min + " max=" + max + " numClasses=" + numClasses + " deltaStep=" + deltaStep);
		List<Double> values = new ArrayList<>();
		for (double x = min; x < max; x += deltaStep) {
			values.add(x);
		}
		values.add(max);
		System.out.println(StringUtils.join(values, " - "));
		return values;
	}

	public static void convert(File input) throws TransformException {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body style=\"background-color: aqua;\"><table style=\"width:100%\"> <tr>");
			AbstractGridFormat format = GridFormatFinder.findFormat(input);
			// working around a bug/quirk in geotiff loading via format.getReader which
			// doesn't set this correctly
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
			int numBands = reader.getGridCoverageCount();
			GridGeometry2D geometry = grid.getGridGeometry();
			System.out.println("numBands:" + numBands + " USE 1 as default band");
			System.out.println("w:" + w + " h:" + h);
			double minValue = Double.MAX_VALUE;
			double maxValue = Double.MIN_VALUE;
			for (int k = 0; k < w; k += 100) {
				System.out.print(" " + k);
				for (int jj = 0; jj < h; jj += 100) {

					// org.geotools.geometry.Envelope2D pixelEnvelop =
					// geometry.gridToWorld(new GridEnvelope2D(k, jj, 1, 1));

					// double lat = pixelEnvelop.getCenterY();
					// double lon = pixelEnvelop.getCenterX();

					double[] vals = new double[numBands];
					grid.evaluate(new GridCoordinates2D(k, jj), vals);
					minValue = Math.min(minValue, vals[0]);
					maxValue = Math.max(maxValue, vals[0]);
					// Do something!

				}
			}
			System.out.println("\nminValue:" + minValue + " maxValue:" + maxValue);
			// -----
			reader.dispose();

			for (int i = 8; i < 9; i++) {
				BufferedImage image = new BufferedImage(grid.getGridGeometry().getGridRange2D().width,
						grid.getGridGeometry().getGridRange2D().height, BufferedImage.TYPE_4BYTE_ABGR);

				MapContent mapContent = new MapContent();
				mapContent.getViewport().setCoordinateReferenceSystem(grid.getCoordinateReferenceSystem());
				Layer rasterLayer = new GridCoverageLayer(grid, createStyle(minValue, maxValue, i));
				mapContent.addLayer(rasterLayer);
				GTRenderer draw = new StreamingRenderer();
				draw.setMapContent(mapContent);
				Graphics2D graphics = image.createGraphics();
				draw.paint(graphics, grid.getGridGeometry().getGridRange2D(), mapContent.getMaxBounds());
				byte[] bytes = toByteArray(image, "png");
				String bytesBase64 = Base64.encodeBase64String(bytes);
				
				File outputfile = new File("C:\\example.png");
				ImageIO.write(image, "png", outputfile);
				
				String stringa = "<td><div><img width=\"400\" height=\"500\" src=\"data:image/png;base64, "
						+ bytesBase64 + "\"/></div></td>";
				sb.append(stringa);
				System.out.println(i + ") Done...");
			}
			sb.append(" </tr></body></html>");
			File htmlFile = new File("C:/" + input.getName() + ".html");
			FileWriter myWriter = new FileWriter(htmlFile);
			myWriter.write(sb.toString());
			myWriter.close();
			// Desktop.getDesktop().browse(htmlFile.toURI());
			System.out.println("Successfully tif processed. Check " + htmlFile.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		File input1 = new File("D:\\Progetti\\JavaApplication4\\src\\myPackageImageIO\\n42_e011_1arc_v3.tif");
		File input = new File("D:\\Progetti\\JavaApplication4\\src\\myPackageImageIO\\n45_e012_1arc_v3.tif");
//		convert(input);
		convert(input1);
		buildLevels(1, 20, 1);
		buildLevels(1, 20, 2);
		buildLevels(1, 20, 3);
		buildLevels(1, 20, 10);
	}

	public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);
		byte[] bytes = baos.toByteArray();
		return bytes;

	}
	


}
