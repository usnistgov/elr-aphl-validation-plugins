package gov.nist.hit.elr.aphl.plugin.extra;

public class ARLN {

  private static final String FOLDER = "arln/20200117";
  private static final String TEST_CSV = "IGAMT_ARLN_import_tables_Tests.csv";
  private static final String OBSERVATIONS_CSV = "IGAMT_ARLN_import_tables_Observations.csv";
  private static final String ORDERS = "IGAMT_ARLN_import_tables_Orders.csv";
  private static final String VALUE_SETS_CSV = "IGAMT_ARLN_import_tables_Value_sets.csv";

  private static final String FOLDER_SPM = "arln/20190906";
  private static final String SPECIMEN_TYPE_CSV = "ARLN_SPM4_ValueSet.csv";

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
    return FOLDER_SPM;
  }

  public static String getSPECIMEN_TYPE_CSV() {
    return SPECIMEN_TYPE_CSV;
  }

}
