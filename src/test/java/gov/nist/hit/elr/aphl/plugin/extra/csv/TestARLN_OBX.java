package gov.nist.hit.elr.aphl.plugin.extra.csv;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;

public class TestARLN_OBX {

  private static OBX_csv testObject;

  private static ComplexCodedElement OBX3;
  private static String OBX2;
  private static ComplexCodedElement OBX5;

  @BeforeClass
  public static void setUp() {
    testObject = new ARLN_OBX_csv();
  }

  @Test
  public void testCheckOBX3_OBX2_Success() throws IOException {
    // OBX-3 = 19156-9/LN
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("19156-9", "LN", "", "");
    OBX2 = "CWE";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "19156-9", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("19156-9", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "19156-9", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT641 / PLT
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("PLT641", "PLT", "", "");
    OBX2 = "TX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT641", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT641", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT641", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 85930-6 / LN
    // OBX-2 = DT
    OBX3 = new ComplexCodedElement("85930-6", "LN", "", "");
    OBX2 = "DT";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "85930-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("85930-6", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "85930-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT609 / PLT
    // OBX-2 = DT
    OBX3 = new ComplexCodedElement("PLT609", "PLT", "", "");
    OBX2 = "DT";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT609", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT609", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT609", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 108-1 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("108-1", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "108-1", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("108-1", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "108-1", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 15378-3 / LN
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("15378-3", "LN", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "15378-3", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("15378-3", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "15378-3", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 35659-2 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("35659-2", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "35659-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("35659-2", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "35659-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 35659-2 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("35659-2", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "35659-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("35659-2", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "35659-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());


    // OBX-3 = PLT610 / PLT
    // OBX-2 = DT
    OBX3 = new ComplexCodedElement("PLT610", "PLT", "", "");
    OBX2 = "DT";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT610", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT610", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT610", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT622 / PLT
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("PLT622", "PLT", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT622", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT622", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT622", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT631 / PLT
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("PLT631", "PLT", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT631", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT631", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT631", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT642 / PLT
    // OBX-2 = DT
    OBX3 = new ComplexCodedElement("PLT642", "PLT", "", "");
    OBX2 = "DT";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT642", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT642", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT642", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());


    // 2020 01 10
    // OBX-3 = 45187-2 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("45187-2", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "45187-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("45187-2", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "45187-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 206-3 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("206-3", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "206-3", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("206-3", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "206-3", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT2251 / PLT
    // OBX-2 = CX
    OBX3 = new ComplexCodedElement("PLT2251", "PLT", "", "");
    OBX2 = "CX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT2251", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT2251", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT2251", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT2260 / PLT
    // OBX-2 = CX
    OBX3 = new ComplexCodedElement("PLT2260", "PLT", "", "");
    OBX2 = "CX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT2260", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT2260", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT2260", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());
  }

  @Test
  public void testCheckOBX3_OBX2_Fail() throws IOException {
    // OBX-3 = 19156-9/LN
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("19156-9", "LN", "", "");
    OBX2 = "ST";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "19156-9", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("19156-9", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "19156-9", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = PLT641 / PLT
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("PLT641", "PLT", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT641", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT641", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT641", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 85930-6 / LN
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("85930-6", "LN", "", "");
    OBX2 = "TX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "85930-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("85930-6", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "85930-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = PLT609 / PLT
    // OBX-2 = DTM
    OBX3 = new ComplexCodedElement("PLT609", "PLT", "", "");
    OBX2 = "DTM";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT609", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT609", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT609", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 108-1 / LN
    // OBX-2 = DTM
    OBX3 = new ComplexCodedElement("108-1", "LN", "", "");
    OBX2 = "DTM";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "108-1", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("108-1", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "108-1", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 15378-3 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("15378-3", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "15378-3", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("15378-3", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "15378-3", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 35659-2 / LN
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("35659-2", "LN", "", "");
    OBX2 = "ST";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "35659-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("35659-2", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "35659-2", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());


    // OBX-3 = PLT642 / PLT
    // OBX-2 = DTM
    OBX3 = new ComplexCodedElement("PLT642", "PLT", "", "");
    OBX2 = "DTM";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT642", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT642", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT642", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = PLT2260 / PLT
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("PLT2260", "PLT", "", "");
    OBX2 = "ST";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT2260", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT2260", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT2260", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());
  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Success() throws IOException {
    // OBX-3 = 90003-5/LN
    // OBX-2 = CWE
    // OBX-5 = 91288006 / SCT (Acinetobacter culture result)

    OBX3 = new ComplexCodedElement("90003-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("", "", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("", "", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("", "", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("91288006", "SCT", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("ABC", "L", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("90003-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "90003-5", "LN");
    OBX5 = new ComplexCodedElement("ABC", "L", "91288006", "SCT");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());



    //
    OBX3 = new ComplexCodedElement("PLT622", "PLT", "", "");
    OBX5 = new ComplexCodedElement("PLT620", "PLT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT622", "PLT", "", "");
    OBX5 = new ComplexCodedElement("PLR4162", "PLR", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT622", "PLT", "", "");
    OBX5 = new ComplexCodedElement("373121007", "SCT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT631", "PLT", "", "");
    OBX5 = new ComplexCodedElement("MD", "FIPS5_2", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT631", "PLT", "", "");
    OBX5 = new ComplexCodedElement("NY", "FIPS5_2", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT631", "PLT", "", "");
    OBX5 = new ComplexCodedElement("WI", "FIPS5_2", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());
    
    OBX3 = new ComplexCodedElement("PLT645", "PLT", "", "");
    OBX5 = new ComplexCodedElement("OTH", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());
    
    OBX3 = new ComplexCodedElement("555-3", "LN", "", "");
    OBX5 = new ComplexCodedElement("243450000", "SCT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());
    
    OBX3 = new ComplexCodedElement("15378-3", "LN", "", "");
    OBX5 = new ComplexCodedElement("243450000", "SCT", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());
  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Fail() throws IOException {
    // OBX-3 = 6985-6/LN
    // OBX-2 = CWE
    // OBX-5 = UNK / NULLFL

    OBX3 = new ComplexCodedElement("6985-6", "LN", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "", "");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("6985-6", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "6985-6", "LN");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());
  }

  @Test
  @Ignore
  public void testCheckMessage() {

  }
}
