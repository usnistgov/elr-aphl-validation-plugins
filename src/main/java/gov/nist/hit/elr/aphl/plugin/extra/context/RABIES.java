package gov.nist.hit.elr.aphl.plugin.extra.context;

import gov.nist.hit.elr.plugin.utils.PropertiesUtils;

public class RABIES {

	private static final String PROGRAM_DEFAULT = "RABIES";
	private static final String SPM4_CSV_DEFAULT = "todo.csv";
	private static final String TEST_CSV_DEFAULT = "todo.csv";
	private static final String OBSERVATIONS_CSV_DEFAULT = "todo.csv";
	private static final String ORDERS_CSV_DEFAULT = "todo.csv";
	private static final String VALUE_SETS_CSV_DEFAULT = "todo.csv";
	private static final String WEBSERVICE_URL_DEFAULT = "https://aphl.nist.gov/aphl-service/aphl/rabies/";
	private static final String VOCAB_WEBSERVICE_URL_DEFAULT = "https://vocab.nist.gov/vocabulary-service/aphl_rabies";

	private static String PROGRAM;
	private static String SPM4_CSV;
	private static String TEST_CSV;
	private static String OBSERVATIONS_CSV;
	private static String ORDERS_CSV;
	private static String VALUE_SETS_CSV;
	private static String WEBSERVICE_URL;
	private static String VOCAB_WEBSERVICE_URL;

	static {
		PROGRAM = getProp("RABIES_PROGRAM", PROGRAM_DEFAULT);
		SPM4_CSV = getProp("RABIES_SPM4_CSV", SPM4_CSV_DEFAULT);
		TEST_CSV = getProp("RABIES_TEST_CSV", TEST_CSV_DEFAULT);
		OBSERVATIONS_CSV = getProp("RABIES_OBSERVATIONS_CSV", OBSERVATIONS_CSV_DEFAULT);
		ORDERS_CSV = getProp("RABIES_ORDERS_CSV", ORDERS_CSV_DEFAULT);
		VALUE_SETS_CSV = getProp("RABIES_VALUE_SETS_CSV", VALUE_SETS_CSV_DEFAULT);
		WEBSERVICE_URL = getProp("RABIES_WEBSERVICE_URL", WEBSERVICE_URL_DEFAULT);
		VOCAB_WEBSERVICE_URL = getProp("RABIES_VOCAB_WEBSERVICE_URL", VOCAB_WEBSERVICE_URL_DEFAULT);
	}

	private static String getProp(String key, String defaultValue) {
		String value = PropertiesUtils.getInstance().getProperty(key);
		return (value != null) ? value : defaultValue;
	}

	public static String getPROGRAM() {
		return PROGRAM;
	}

	public static String getTEST_CSV() {
		return TEST_CSV;
	}

	public static String getOBSERVATIONS_CSV() {
		return OBSERVATIONS_CSV;
	}

	public static String getORDERS_CSV() {
		return ORDERS_CSV;
	}

	public static String getVALUE_SETS_CSV() {
		return VALUE_SETS_CSV;
	}

	public static String getSPM4_CSV() {
		return SPM4_CSV;
	}

	public static String getWEBSERVICE_URL() {
		return WEBSERVICE_URL;
	}

	public static String getVOCAB_WEBSERVICE_URL() {
		return VOCAB_WEBSERVICE_URL;
	}
}
