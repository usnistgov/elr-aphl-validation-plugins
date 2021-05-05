package gov.nist.hit.elr.aphl.plugin.extra.context;

import gov.nist.hit.elr.aphl.domain.Program;

public class RABIES {

  public static final Program program = Program.RABIES;


  private static final String WEBSERVICE_URL =
      "https://hit-dev.nist.gov:8097/aphl-service/aphl/rabies/";

  private static final String FOLDER = "rabies/";

  private static final String TEST_CSV = "IGAMT_Rabies_Tests.csv";
  private static final String OBSERVATIONS_CSV = "IGAMT_Rabies_Observations.csv";
  private static final String ORDERS = "IGAMT_Rabies_Orders.csv";
  private static final String VALUE_SETS_CSV = "IGAMT_Rabies_ValueSets.csv";


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


}
