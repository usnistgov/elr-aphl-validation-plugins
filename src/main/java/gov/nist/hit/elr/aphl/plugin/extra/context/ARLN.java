package gov.nist.hit.elr.aphl.plugin.extra.context;

import gov.nist.hit.elr.aphl.domain.Program;

public class ARLN {

  public static final Program program = Program.ARLN;

  private static final String FOLDER = "arln/20220203";
  private static final String TEST_CSV = "IGAMT_ARLN_import_tables_Tests.csv";
  private static final String OBSERVATIONS_CSV = "IGAMT_ARLN_import_tables_Observations.csv";
  private static final String ORDERS = "IGAMT_ARLN_import_tables_Orders.csv";
  private static final String VALUE_SETS_CSV = "IGAMT_ARLN_import_tables_Value_sets.csv";

  // private static final String FOLDER_SPM = "arln/20190906";
  // private static final String SPECIMEN_TYPE_CSV = "ARLN_SPM4_ValueSet.csv";

  private static final String WEBSERVICE_URL =
      "https://hit-dev.nist.gov:8097/aphl-service/aphl/arln/";

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

  public static String getWEBSERVICE_URL() {
    return WEBSERVICE_URL;
  }

  public static String getFolder() {
    return FOLDER;
  }

  public static String getTestCsv() {
    return TEST_CSV;
  }

  public static String getObservationsCsv() {
    return OBSERVATIONS_CSV;
  }

  public static String getOrders() {
    return ORDERS;
  }

  public static String getValueSetsCsv() {
    return VALUE_SETS_CSV;
  }

  // public static String getFolderSpm() {
  // return FOLDER_SPM;
  // }
  //
  // public static String getSpecimenTypeCsv() {
  // return SPECIMEN_TYPE_CSV;
  // }

  public static String getWebserviceUrl() {
    return WEBSERVICE_URL;
  }



}
