package it.rjcsoft.prv.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridCoverage2DReader;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.GridCoverageLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.ColorMap;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.util.factory.Hints;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import org.opengis.filter.FilterFactory2;

public class GeoTiffUtils {

	private GeoTiffUtils() {
	}

	public static Polygon convertGridToPolygon(GridCoverage2D grid) {
		double latLowLeft = grid.getEnvelope2D().getMinY();
		double lonLowLeft = grid.getEnvelope2D().getMinX();
		double latUpRight = grid.getEnvelope2D().getMaxY();
		double lonUpRight = grid.getEnvelope2D().getMaxX();

		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		Coordinate[] coordinates = new Coordinate[2];
		coordinates[0] = new Coordinate(lonLowLeft, latLowLeft);
		coordinates[1] = new Coordinate(lonUpRight, latUpRight);
		LineString diagonalLineString = geometryFactory.createLineString(coordinates);
		return (Polygon) diagonalLineString.getEnvelope();
	}

	public static Polygon convertGeotiffToPolygon(File file) throws IOException {
		GeoTiffReader reader = new GeoTiffReader(file);
		GridCoverage2D grid = reader.read(null);
		if (grid == null) {
			return null;
		}
		return convertGridToPolygon(grid);
	}

	public static String fromFileToBase64(File input) throws IOException {
		AbstractGridFormat format = GridFormatFinder.findFormat(input);
		Hints hints = null;
		if (format instanceof GeoTiffFormat) {
			hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
		}
		AbstractGridCoverage2DReader reader = format.getReader(input, hints);
		GridCoverage2D grid = reader.read(null);
		reader.dispose();
		BufferedImage image = new BufferedImage(grid.getGridGeometry().getGridRange2D().width,
				grid.getGridGeometry().getGridRange2D().height, BufferedImage.TYPE_4BYTE_ABGR);
		MapContent mapContent = new MapContent();
		mapContent.getViewport().setCoordinateReferenceSystem(grid.getCoordinateReferenceSystem());
		Layer rasterLayer = new GridCoverageLayer(grid, createStyle(1, -0.4, 0.2));
		mapContent.addLayer(rasterLayer);
		GTRenderer draw = new StreamingRenderer();
		draw.setMapContent(mapContent);
		Graphics2D graphics = image.createGraphics();
		draw.paint(graphics, grid.getGridGeometry().getGridRange2D(), mapContent.getMaxBounds());
		byte[] bytes = toByteArray(image, "png");
		return Base64.encodeBase64String(bytes);
	}

	public static byte[] fromFileToByteArray(File input) throws IOException {
		AbstractGridFormat format = GridFormatFinder.findFormat(input);
		Hints hints = null;
		if (format instanceof GeoTiffFormat) {
			hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
		}
		AbstractGridCoverage2DReader reader = format.getReader(input, hints);
		GridCoverage2D grid = reader.read(null);
		reader.dispose();
		BufferedImage image = new BufferedImage(grid.getGridGeometry().getGridRange2D().width,
				grid.getGridGeometry().getGridRange2D().height, BufferedImage.TYPE_4BYTE_ABGR);
		MapContent mapContent = new MapContent();
		mapContent.getViewport().setCoordinateReferenceSystem(grid.getCoordinateReferenceSystem());
		Layer rasterLayer = new GridCoverageLayer(grid, createStyle(1, -0.4, 0.2));
		mapContent.addLayer(rasterLayer);
		GTRenderer draw = new StreamingRenderer();
		draw.setMapContent(mapContent);
		Graphics2D graphics = image.createGraphics();
		draw.paint(graphics, grid.getGridGeometry().getGridRange2D(), mapContent.getMaxBounds());
		return toByteArray(image, "png");
	}

	private static Style createStyle(int band, double min, double max) {
		FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
		StyleFactory sf = CommonFactoryFinder.getStyleFactory();
		RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
		ColorMap cMap = sf.createColorMap();
//		ColorMapEntry start = sf.createColorMapEntry();
//		start.setColor(ff.literal("#ff0000"));
//		start.setQuantity(ff.literal(min));
//		ColorMapEntry end = sf.createColorMapEntry();
//		end.setColor(ff.literal("#0000ff"));
//		end.setQuantity(ff.literal(max));

//		cMap.addColorMapEntry(start);
//		cMap.addColorMapEntry(end);
		sym.setColorMap(cMap);
		return SLD.wrapSymbolizers(sym);
	}

	public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);
		return baos.toByteArray();
	}
}
