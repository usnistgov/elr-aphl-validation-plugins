package gov.nist.hit.elr.aphl.plugin.extra.context;

import gov.nist.hit.elr.aphl.domain.Program;

public class VPD {

  public static final Program program = Program.VPD;

  private static final String FOLDER = "vpd/20200624";
  private static final String FOLDER_SPM = "vpd/20200624";


  //private static final String FOLDER_MSH = "vpd/20200624";
  //private static final String MSH3_CSV = "MSH3.csv";
  //private static final String MSH4_CSV = "MSH4.csv";

  private static final String TEST_CSV = "IGAMT_VPD_Tests.csv";
  private static final String OBSERVATIONS_CSV = "IGAMT_VPD_Observations.csv";
  private static final String ORDERS = "IGAMT_VPD_Orders.csv";
  private static final String VALUE_SETS_CSV = "IGAMT_VPD_ValueSets.csv";

  private static final String SPECIMEN_TYPE_CSV = "VPD_SPM4.csv";

  private static final String WEBSERVICE_URL =
      "https://hit-dev.nist.gov:8097/aphl-service/aphl/vpd/";

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

//  public static String getFOLDER_MSH() {
//    return FOLDER_MSH;
//  }
//
//  public static String getMSH3_CSV() {
//    return MSH3_CSV;
//  }
//
//  public static String getMSH4_CSV() {
//    return MSH4_CSV;
//  }

  public static String getWEBSERVICE_URL() {
    return WEBSERVICE_URL;
  }


}
