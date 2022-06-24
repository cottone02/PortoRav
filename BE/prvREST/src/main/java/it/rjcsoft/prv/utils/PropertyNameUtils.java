package it.rjcsoft.prv.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Geometry;

import com.google.common.base.CaseFormat;

public class PropertyNameUtils {

	private PropertyNameUtils() {
	}

	public static String convertPropertyName2Col(String propertyName) {
		try {
			if (StringUtils.isBlank(propertyName) || StringUtils.equalsAny(propertyName, "class")) {
				return null;
			}
			return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, propertyName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void buildCondition(Map<String, Object> params, List<String> conditions, String tableAlias,
			String column, String propertyName, Object srcValue, boolean useIlike) {
		String condition = String.format(" %s.%s = :%s ", tableAlias, column, propertyName);
		params.put(propertyName, srcValue);
		if (srcValue instanceof String && useIlike) {
			condition = String.format(" %s.%s ilike :%s ", tableAlias, column, propertyName);
			params.put(propertyName, "%" + srcValue + "%");
		} else if (srcValue instanceof Geometry) {
			condition = String.format(" ST_CONTAINS(st_geomfromtext(:%s, 4326), %s.%s) ", propertyName, tableAlias, column);
			params.put(propertyName, srcValue.toString());
		} else if (srcValue instanceof Date) {
			boolean containsEnd = StringUtils.containsIgnoreCase(propertyName, "end"); 
			boolean containsStart = !containsEnd && StringUtils.containsIgnoreCase(propertyName, "start");
			String operator = "=";
			if (containsEnd) {
				operator = "<=";
			} else if (containsStart) {
				operator = ">=";
			}
//			condition = String.format(" %s.upload_ts %s :%s ", tableAlias, operator, propertyName);
			condition = String.format(" %s.date %s :%s ", tableAlias, operator, propertyName);
		}
		conditions.add(condition);
	}

	public static void buildUpdateSet(Map<String, Object> params, List<String> assignments, String column,
			String propertyName, Object srcValue) {
		String assignment = String.format(" %s=:%s ", column, propertyName);
		params.put(propertyName, srcValue);
		if (srcValue instanceof Geometry) {
			assignment = String.format(" %s=ST_GeomFromText(:%s, 4326) ", column, propertyName);
			params.put(propertyName, srcValue.toString());
		}
		assignments.add(assignment);
	}

}
