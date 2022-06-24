package it.rjcsoft.prv.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTReader;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import it.rjcsoft.prv.enums.PrvSpatialOps;

public class PrvGeometryUtils {

	private static final GeometryFactory geometryFactory = new GeometryFactory();

	private static final WKTReader wktReader = new WKTReader(geometryFactory);

	private PrvGeometryUtils() {
	}

	public static Geometry convertToGeometry(Object wktObj) {
		Geometry geometry = null;

		try {
			if (wktObj != null) {
				geometry = wktReader.read(wktObj.toString());
				geometry.setSRID(4326);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geometry;
	}

	public static Geometry convertToGeometry(Object wktObj, int sRID) {
		Geometry geometry = null;

		try {
			if (wktObj != null) {
				geometry = wktReader.read(wktObj.toString());
				geometry.setSRID(sRID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geometry;
	}

	public static Polygon convertToPolygon(Object wktObj) {
		Geometry g = convertToGeometry(wktObj);
		if (g instanceof Polygon) {
			return (Polygon) g;
		}
		return null;
	}

	public static Point convertToPoint(Object wktObj) {
		Geometry g = convertToGeometry(wktObj);
		if (g instanceof Point) {
			return (Point) g;
		}
		return null;
	}

	public static LineString convertToLineString(Object wktObj) {
		Geometry g = convertToGeometry(wktObj);
		if (g instanceof LineString) {
			return (LineString) g;
		}
		return null;
	}

	public static BooleanExpression buildGeometryCondiction(PrvSpatialOps operation, Object path, String filterString) {
		return buildGeometryCondiction(operation, path, filterString, false, 4326);
	}

	public static BooleanExpression buildGeometryCondiction(PrvSpatialOps operation, Object path, String filterString,
			int sRID) {
		return buildGeometryCondiction(operation, path, filterString, false, sRID);
	}

	public static BooleanExpression buildGeometryCondiction(PrvSpatialOps operation, Object path, String filterString,
			boolean useRevert, int sRID) {
		Geometry filter = convertToGeometry(filterString, sRID);
		if (filter == null || !filter.isValid() || filter.isEmpty()) {
			return Expressions.asBoolean(true).isFalse();
		}
		String condictionPattern = " %s({0},{1}) = TRUE ";
		if (useRevert) {
			condictionPattern = " %s({1},{0}) = TRUE ";
		}
		String condiction = String.format(condictionPattern, operation);
		return Expressions.booleanTemplate(condiction, path, filter);
	}

	public static Geometry convertSRID(Geometry geom, int targetSRID) {
		if (geom == null) return null;
		try {
			Integer sourceSRID = geom.getSRID();
			CoordinateReferenceSystem sourceCRS = CRS.decode(String.format("EPSG:%d", sourceSRID));
			CoordinateReferenceSystem targetCRS = CRS.decode(String.format("EPSG:%d", targetSRID));
			MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, false);
			return JTS.transform(geom, transform);
		} catch (Exception e) {
			return null;
		}
	}

}
