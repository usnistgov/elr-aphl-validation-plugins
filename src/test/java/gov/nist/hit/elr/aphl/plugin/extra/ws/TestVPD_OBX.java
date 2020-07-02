package gov.nist.hit.elr.aphl.plugin.extra.ws;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.aphl.plugin.extra.OBX;
import gov.nist.hit.elr.aphl.plugin.extra.OBX_ws;
import gov.nist.hit.elr.aphl.plugin.extra.VPD_OBX;
import gov.nist.hit.elr.aphl.plugin.extra.VPD_OBX_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;

public class TestVPD_OBX {

  private static OBX testObject;

  private static ComplexCodedElement OBX3;
  private static String OBX2;
  private static ComplexCodedElement OBX5;

  @BeforeClass
  public static void setUp() {
    testObject = new VPD_OBX();
  }

  @Test
  public void testCheckOBX3_OBX2_Success()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = PRT020/PHINQUESTION
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("PRT020", "PHINQUESTION", "", "");
    OBX2 = "CWE";
    //List<String> result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PRT020", "PHINQUESTION");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PRT020", "PHINQUESTION", "ABC", "L");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PRT020", "PHINQUESTION");
  //  result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 85678-1 / LN
    // OBX-2 = NM
    OBX3 = new ComplexCodedElement("85678-1", "LN", "", "");
    OBX2 = "NM";
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "85678-1", "LN");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("85678-1", "LN", "ABC", "L");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "85678-1", "LN");
   // result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 61188-9 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("61188-9", "LN", "", "");
    OBX2 = "SN";
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "61188-9", "LN");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("61188-9", "LN", "ABC", "L");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "61188-9", "LN");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 30952-6 / LN
    // OBX-2 = TS
    OBX3 = new ComplexCodedElement("30952-6", "LN", "", "");
    OBX2 = "TS";
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "30952-6", "LN");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("30952-6", "LN", "ABC", "L");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30952-6", "LN");
  //  result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT641 / PLT
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("PLT641", "PLT", "", "");
    OBX2 = "TX";
 //   result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT641", "PLT");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT641", "PLT", "ABC", "L");
 //   result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT641", "PLT");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT539 / PLT
    // OBX-2 = ED
    OBX3 = new ComplexCodedElement("PLT539", "PLT", "", "");
    OBX2 = "ED";
  //  result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT539", "PLT");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT539", "PLT", "ABC", "L");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT539", "PLT");
 //   result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT668 / PLT
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("PLT668", "PLT", "", "");
    OBX2 = "ST";
 //   result = testObject.checkOBX3(OBX3);
 //   assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT668", "PLT");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT668", "PLT", "ABC", "L");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT668", "PLT");
  //  result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());
  }


  @Test
  public void testCheckOBX3_OBX2_Fail()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = PLT641 / PLT
    // OBX-2 = ED
    OBX3 = new ComplexCodedElement("PLT641", "PLT", "", "");
    OBX2 = "ED";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = PRT020/PHINQUESTION
    // OBX-2 = NM
    OBX3 = new ComplexCodedElement("PRT020", "PHINQUESTION", "", "");
    OBX2 = "NM";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 85678-1 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("85678-1", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 61188-9 / LN
    // OBX-2 = TS
    OBX3 = new ComplexCodedElement("61188-9", "LN", "", "");
    OBX2 = "TS";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = 30952-6 / LN
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("30952-6", "LN", "", "");
    OBX2 = "TX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());



    // OBX-3 = PLT539 / PLT
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("PLT539", "PLT", "", "");
    OBX2 = "ST";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    // OBX-3 = PLT668 / PLT
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("PLT668", "PLT", "", "");
    OBX2 = "CWE";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Success()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = PLT355/PLT
    // OBX-2 = CWE
    // OBX-5 = 260373001 / SCT

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "", "");
  //  List<String> result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "", "");
  //  result = testObject.checkOBX3(OBX3);
 //   assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "", "");
 //   result = testObject.checkOBX3(OBX3);
 //   assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "", "");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("", "", "260373001", "SCT");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("", "", "260373001", "SCT");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "260373001", "SCT");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("", "", "260373001", "SCT");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "ABC", "L");
   // result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "ABC", "L");
  //  result = testObject.checkOBX3(OBX3);
  //  assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "ABC", "L");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("260373001", "SCT", "ABC", "L");
  //  result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "260373001", "SCT");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "260373001", "SCT");
   // result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "260373001", "SCT");
  //  result = testObject.checkOBX3(OBX3);
   // assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "260373001", "SCT");
 //   result = testObject.checkOBX3(OBX3);
 //   assertEquals(0, result.size());
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());


  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Fail()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = PLT355/PLT
    // OBX-2 = CWE
    // OBX-5 = UNK / NULLFL

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("PLT355", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT355", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());
  }

  @Test
  @Ignore
  public void testCheckMessage() {

  }
}
