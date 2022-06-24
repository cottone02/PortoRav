package it.rjcsoft.prv.utils;

import java.util.HashMap;
import java.util.Map;

public class PrvMailUtils {

	public static final String ACCOUNT_ACTIVATION = "ACCOUNT_ACTIVATION";
	public static final String ADMIN_USER_ACTIVATION = "ADMIN_USER_ACTIVATION";
	public static final String RESET_PASSWORD = "RESET_PASSWORD";
	public static final String CHANGED_ROLE_ADMIN = "CHANGED_ROLE_ADMIN";
	public static final String CHANGED_ROLE_READER = "CHANGED_ROLE_READER";
	public static final String CHANGED_ROLE_READER_EXT = "CHANGED_ROLE_READER_EXT";
	public static final String CHANGED_ROLE_WRITER = "CHANGED_ROLE_WRITER";
	public static final String CHANGED_ROLE_WRITER_EXT = "CHANGED_ROLE_WRITER_EXT";
	public static final String CHANGED_ROLE_DEFAULT = "CHANGED_ROLE_DEFAULT";
	public static final String MONITORING = "MONITORING";
	public static final String GENERAL = "GENERAL_COMMUNICATION";

	private static final String[] ACCOUNT_ACTIVATION_PH = {"%url%", "%base_url%", "%otp%", "%email%", "%expiration_date%"};
	private static final String[] ADMIN_USER_ACTIVATION_PH = {"%url%", "%email%" };
	private static final String[] RESET_PASSWORD_PH = {"%url%", "%base_url%", "%otp%", "%email%", "%expiration_date%"};
	private static final String[] CHANGED_ROLE_ADMIN_PH = {"%url%"};
	private static final String[] CHANGED_ROLE_READER_PH = {"%url%"};
	private static final String[] CHANGED_ROLE_READER_EXT_PH = {"%url%"};
	private static final String[] CHANGED_ROLE_WRITER_PH = {"%url%"};
	private static final String[] CHANGED_ROLE_WRITER_EXT_PH = {"%url%"};
	private static final String[] CHANGED_ROLE_DEFAULT_PH = {"%url%"};
	private static final String[] MONITORING_PH = {"%url%", "%action%", "%extra_info%"};
	private static final String[] GENERAL_PH = {"%url%","%azienda%", "%body%"};
	

	public static Map<String, String[]> placeholdersMap;
	
	static {
		placeholdersMap = new HashMap<>();
		placeholdersMap.put(ACCOUNT_ACTIVATION, ACCOUNT_ACTIVATION_PH);
		placeholdersMap.put(ADMIN_USER_ACTIVATION, ADMIN_USER_ACTIVATION_PH);
		placeholdersMap.put(RESET_PASSWORD, RESET_PASSWORD_PH);
		placeholdersMap.put(CHANGED_ROLE_ADMIN, CHANGED_ROLE_ADMIN_PH);
		placeholdersMap.put(CHANGED_ROLE_READER, CHANGED_ROLE_READER_PH);
		placeholdersMap.put(CHANGED_ROLE_READER_EXT, CHANGED_ROLE_READER_EXT_PH);
		placeholdersMap.put(CHANGED_ROLE_WRITER, CHANGED_ROLE_WRITER_PH);
		placeholdersMap.put(CHANGED_ROLE_WRITER_EXT, CHANGED_ROLE_WRITER_EXT_PH);
		placeholdersMap.put(CHANGED_ROLE_DEFAULT, CHANGED_ROLE_DEFAULT_PH);
		placeholdersMap.put(MONITORING, MONITORING_PH);
		placeholdersMap.put(GENERAL, GENERAL_PH);		
	}

	public static String[] getPlaceholders(String key) {
		return placeholdersMap.get(key);
	}

	public static String getLanguage() {
		return "IT";
	}
	
	public static Map<String, String> initPlaceholders(String key, String...sortedPhValues) {
		String[] phs = getPlaceholders(key);
		Map<String, String> map = new HashMap<>();
		map.put("\\\"", "\"");
		if(phs!=null && sortedPhValues!=null && phs.length >= sortedPhValues.length) {
			for (int i = 0; i < phs.length; i++) {
				map.put(phs[i], sortedPhValues[i]);
			}
		}
		return map;
	}
	
	public static String getRoleKey(String role) {
		switch(role) {
			case "WRITER"		: return CHANGED_ROLE_WRITER;
			case "WRITER_EXT"	: return CHANGED_ROLE_WRITER_EXT;
			case "READER" 		: return CHANGED_ROLE_READER; 
			case "READER_EXT"	: return CHANGED_ROLE_READER_EXT;
			case "ADMIN"		: return CHANGED_ROLE_ADMIN;
			default				: return CHANGED_ROLE_DEFAULT;
		}
	}

}
