package it.rjcsoft.prv.model.excel;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.rjcsoft.prv.annotation.ExcelPosition;

public class BaseExcel {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	public static <E extends BaseExcel> String[] getHeadersFromCsvDao(Class<E> excelClass) {

		if (null == excelClass) {
			return ArrayUtils.EMPTY_STRING_ARRAY;
		}

		Field[] fields = excelClass.getDeclaredFields();

		String[] headers = new String[fields.length];
		for (Field field : fields) {
			if (field.isAnnotationPresent(ExcelPosition.class)
					&& field.getAnnotation(ExcelPosition.class).position() - 1 <= fields.length) {
				headers[field.getAnnotation(ExcelPosition.class).position() - 1] = StringUtils
						.capitalize(field.getName());
			}
		}
		return headers;

	}
}
