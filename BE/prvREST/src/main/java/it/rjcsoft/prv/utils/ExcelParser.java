package it.rjcsoft.prv.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.rjcsoft.prv.annotation.ExcelPosition;
import it.rjcsoft.prv.enums.FormatType;

public class ExcelParser {

	private ExcelParser() {
	}

	protected static final Logger log = LoggerFactory.getLogger(ExcelParser.class);
	protected static final String NUMERO_PAGINA = "numeroPagina";

	public static <E> List<E> parseExcelTo(String filePath, Class<E> parser, Integer rowToSkip) throws Exception {
		List<E> returnList = new ArrayList<>();
		try (InputStream inp = new FileInputStream(filePath); Workbook wb = WorkbookFactory.create(inp);) {
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				try {
					returnList.addAll(mapExcelData(wb.getSheetAt(i), parser, rowToSkip, i));
				} catch (Exception e) {
					//TODO: inserire almeno un log; prova se riesci a fare un unico try/catch
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return returnList;
	}

	public static <E> List<E> mapExcelData(Sheet sheet, Class<E> parser, Integer rowToSkip, Integer numOfSheet)
			throws Exception {

		List<String[]> rows = readRows(sheet, rowToSkip);
		numOfSheet++;
		if (!rows.isEmpty()) {
			ArrayList<E> retObject = new ArrayList<>();
			try {
				for (String[] row : rows) {
					E obj = parser.getDeclaredConstructor().newInstance();
					for (Field field : parser.getDeclaredFields()) {
						field.setAccessible(true);
						if (!field.isAnnotationPresent(ExcelPosition.class)) {
							continue;
						}
						if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
							FormatType formatType = field.getAnnotation(ExcelPosition.class).formatType();
							int position = field.getAnnotation(ExcelPosition.class).position() - 1;
							String name = field.getAnnotation(ExcelPosition.class).name();
							if (position < row.length) {
								String value = row[position];
								setValueField(obj, value, field, formatType);
							}
							if (NUMERO_PAGINA.equalsIgnoreCase(name)) {
								String value = numOfSheet.toString();
								setValueField(obj, value, field, formatType);
							}
						}

					}

					retObject.add(obj);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				throw e;
			}
			return retObject;
		}
		return Collections.emptyList();
	}

	private static <E> void setValueField(E obj, String value, Field field, FormatType formatType) throws Exception {
		Object valueToSet;
		switch (formatType) {
			case INTEGER:
				valueToSet = Integer.parseInt(value);
				break;
			case BOOLEAN:
				valueToSet = "1.0".equals(value);
				if (valueToSet.equals(false))
					throw new Exception();
				break;
			case FLOAT:
				try {
					BigDecimal bd = new BigDecimal(value);
					valueToSet = bd.floatValue();
				} catch (Exception e1) {
					log.error(e1.getMessage());
					throw e1;
				}
				break;
			case DOUBLE:
				try {
					BigDecimal bd = new BigDecimal(value);
					valueToSet = bd.doubleValue();
				} catch (Exception e1) {
					log.error(e1.getMessage());
					throw e1;
				}
				break;
			case DATE:
				try {
					valueToSet = new SimpleDateFormat(field.getAnnotation(ExcelPosition.class).dateFormat())
							.parse(value);
				} catch (ParseException e) {
					log.error(e.getMessage());
					throw e;
				}
				break;
			case TEXT:
			default:
				valueToSet = value;
				break;
		}
		field.set(obj, valueToSet);
	}

	private static List<String[]> readRows(Sheet sheet, Integer rowToSkip) {
		List<String[]> list = new ArrayList<>();
		Row row = null;
		rowToSkip = (rowToSkip == null) ? 1 : rowToSkip;
		for (int i = rowToSkip; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			List<String> appList = new ArrayList<>();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (row.getCell(j) != null && StringUtils.isNotBlank(row.getCell(j).toString())) {
					org.apache.poi.ss.usermodel.Cell currCell = row.getCell(j);

					appList.add(currCell.toString());
				}
			}
			if (CollectionUtils.isNotEmpty(appList)) {
				list.add(appList.toArray(new String[appList.size()]));
			}
		}
		return list;
	}
}
