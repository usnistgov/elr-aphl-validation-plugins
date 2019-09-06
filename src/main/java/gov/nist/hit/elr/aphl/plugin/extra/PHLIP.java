package gov.nist.hit.elr.aphl.plugin.extra;

public class PHLIP {

	private static final String FOLDER = "phlip/jun2019";
	private static final String TEST_CSV = "Flu_Encoding_Guideline_Jun2019(obx2)-Test.csv";
	private static final String OBSERVATIONS_CSV = "Flu_Encoding_Guideline_Jun2019(obx2)-Observations.csv";
	private static final String ORDERS = "Flu_Encoding_Guideline_Jun2019(obx2)-Orders.csv";
	private static final String VALUE_SETS_CSV = "Flu_Encoding_Guideline_Jun2019(obx2)-ValueSets.csv";

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
