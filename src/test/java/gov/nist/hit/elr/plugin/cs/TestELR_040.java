package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.cs.ELR_040;

public class TestELR_040 {

  /**
   * ELR-040: OBR-3 (Filler Order Number) SHALL NOT contain the same value as another occurrence of
   * OBR-3 (Filler Order Number) in the message.
   */

  private static ELR_040 testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new ELR_040();
  }

  @Test
  public void testCheckSuccess() {
    List<List<String>> actual = new ArrayList<List<String>>();

    String OBR3_1 = "OBR3_1";
    String OBR3_2 = "OBR3_2";
    String OBR3_3 = "OBR3_3";
    String OBR3_4 = "OBR3_4";

    List<String> OBR3 = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3);

    boolean result = testObject.check(actual);
    assertTrue(result);

    OBR3_1 = "OBR3_1+";
    OBR3_2 = "OBR3_2+";
    OBR3_3 = "OBR3_3+";
    OBR3_4 = "OBR3_4+";

    List<String> OBR3_second = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3_second);

    result = testObject.check(actual);
    assertTrue(result);
  }

  @Test
  public void testCheckFail() {

    List<List<String>> actual = new ArrayList<List<String>>();

    String OBR3_1 = "OBR3_1";
    String OBR3_2 = "OBR3_2";
    String OBR3_3 = "OBR3_3";
    String OBR3_4 = "OBR3_4";

    List<String> OBR3_first = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3_first);

    List<String> OBR3_second = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3_second);

    boolean result = testObject.check(actual);
    assertFalse(result);

    List<String> OBR3_third = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3_third);

    result = testObject.check(actual);
    assertFalse(result);

    OBR3_1 = "OBR3_1+";
    OBR3_2 = "OBR3_2+";
    OBR3_3 = "OBR3_3+";
    OBR3_4 = "OBR3_4+";

    List<String> OBR3_fourth = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3_fourth);

    result = testObject.check(actual);
    assertFalse(result);

    List<String> OBR3_fifth = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
    actual.add(OBR3_fifth);

    result = testObject.check(actual);
    assertFalse(result);
  }
}
