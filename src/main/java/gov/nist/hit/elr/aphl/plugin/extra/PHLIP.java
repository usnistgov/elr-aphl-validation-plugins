package gov.nist.hit.elr.aphl.plugin.extra;

public class PHLIP {

	private static final String FOLDER = "phlip/20190924";
	private static final String TEST_CSV = "IGAMT_PHLIP_Tests.csv";
	private static final String OBSERVATIONS_CSV = "IGAMT_PHLIP_Observations.csv";
	private static final String ORDERS = "IGAMT_PHLIP_Orders.csv";
	private static final String VALUE_SETS_CSV = "IGAMT_PHLIP_ValueSets.csv";

	// private static final String FOLDER_SPM = "";
	// private static final String SPECIMEN_TYPE_CSV = "";

	public static String getFOLDER() {
		return FOLDER;
	}

	public static String getTEST_CSV() {
		return TEST_CSV;
	}

	public static String getOBSERVATIONS_CSV() {
		return OBSERVATIONS_CSV;
	}

	public static String getORDERS() {
		return ORDERS;
	}

	public static String getVALUE_SETS_CSV() {
		return VALUE_SETS_CSV;
	}

	public static String getFOLDER_SPM() {
		return ELR_FOUNDATION.getFOLDER_SPM();
	}

	public static String getSPECIMEN_TYPE_CSV() {
		return ELR_FOUNDATION.getSPECIMEN_TYPE_CSV();
	}
}
