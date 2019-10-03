package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;

public class TestPHLIP_OBX {

  private static OBX testObject;

  private static ComplexCodedElement OBX3;
  private static String OBX2;
  private static ComplexCodedElement OBX5;

  @BeforeClass
  public static void setUp() {
    testObject = new PHLIP_OBX();
  }

  @Test
  public void testCheckOBX3_OBX2_Success() throws IOException {
    // OBX-3 = LAB514/PHINQUESTION
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "", "");
    OBX2 = "CWE";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB514", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB514", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 35659-2 / LN
    // OBX-2 = NM
    OBX3 = new ComplexCodedElement("35659-2", "LN", "", "");
    OBX2 = "NM";
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
    OBX2 = "NM";
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

    // OBX-3 = 11368-8 / LN
    // OBX-2 = TS
    OBX3 = new ComplexCodedElement("11368-8", "LN", "", "");
    OBX2 = "TS";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "11368-8", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("11368-8", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "11368-8", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PHLIP02 / PHINQUESTION
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("PHLIP02", "PHINQUESTION", "", "");
    OBX2 = "TX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PHLIP02", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PHLIP02", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PHLIP02", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT248 / PLT
    // OBX-2 = varies
    OBX3 = new ComplexCodedElement("PLT248", "PLT", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT248", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT248", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT248", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());


    // OBX-3 = TRAVEL05 / PHINQUESTION
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("TRAVEL05", "PHINQUESTION", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "TRAVEL05", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("TRAVEL05", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "TRAVEL05", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());


    // OBX-3 = TRAVEL05 / PHINQUESTION
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("TRAVEL05", "PHINQUESTION", "", "");
    OBX2 = "ST";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "TRAVEL05", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("TRAVEL05", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "TRAVEL05", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());
  }

  @Test
  public void testCheckOBX3_OBX2_Fail() throws IOException {
    // OBX-3 = LAB514/PHINQUESTION
    // OBX-2 = NM
    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "", "");
    OBX2 = "NM";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB514", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB514", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 35659-2 / LN
    // OBX-2 = TS
    OBX3 = new ComplexCodedElement("35659-2", "LN", "", "");
    OBX2 = "TS";
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

    // OBX-3 = 11368-8 / LN
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("11368-8", "LN", "", "");
    OBX2 = "TX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "11368-8", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("11368-8", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "11368-8", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = PHLIP02 / PHINQUESTION
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("PHLIP02", "PHINQUESTION", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PHLIP02", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PHLIP02", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PHLIP02", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Success() throws IOException {
    // OBX-3 = LAB514/PHINQUESTION
    // OBX-2 = CWE
    // OBX-5 = UNK / NULLFL

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "", "");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB514", "PHINQUESTION", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB514", "PHINQUESTION");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Fail() throws IOException {
    // OBX-3 = PLT249/PLT
    // OBX-2 = CWE
    // OBX-5 = UNK / NULLFL

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "", "");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT249", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT249", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());
  }

  @Test
  @Ignore
  public void testCheckMessage() {

  }
}
