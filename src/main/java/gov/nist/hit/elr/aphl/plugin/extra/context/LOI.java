package gov.nist.hit.elr.aphl.plugin.extra.context;

import gov.nist.hit.elr.aphl.domain.Program;

public class LOI {

  public static final Program program = Program.LOI;

  private static final String FOLDER = "loi/20230413";
  private static final String TEST_CSV = "Tests.csv";
  private static final String OBSERVATIONS_CSV = "Observations.csv";
  private static final String ORDERS = "Orders.csv";
  private static final String VALUE_SETS_CSV = "Value Sets.csv";


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

}
