package gov.nist.hit.elr.aphl.plugin.extra.context;

import gov.nist.hit.elr.plugin.utils.PropertiesUtils;

public class ELR_FOUNDATION {

	private static final String PROGRAM_DEFAULT = "ELR_FOUNDATION";
	private static final String SPM4_CSV_DEFAULT = "todo.csv";

	private static String PROGRAM;
	private static String SPM4_CSV;

	static {
		PROGRAM = getProp("ELR_PROGRAM", PROGRAM_DEFAULT);
		SPM4_CSV = getProp("ELR_SPM4_CSV", SPM4_CSV_DEFAULT);
	}

	private static String getProp(String key, String defaultValue) {
		String value = PropertiesUtils.getInstance().getProperty(key);
		return (value != null) ? value : defaultValue;
	}

	public static String getPROGRAM() {
		return PROGRAM;
	}

	public static String getSPM4_CSV() {
		return SPM4_CSV;
	}

}
