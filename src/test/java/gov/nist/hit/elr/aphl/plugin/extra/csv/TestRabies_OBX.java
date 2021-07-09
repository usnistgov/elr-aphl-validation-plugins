package gov.nist.hit.elr.aphl.plugin.extra.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

@Ignore
public class TestRabies_OBX {

  private static OBX_csv testObject;

  private static ComplexCodedElement OBX3;
  private static String OBX2;
  private static ComplexCodedElement OBX5;

  @BeforeClass
  public static void setUp() {
    testObject = new Rabies_OBX_csv();
  }

  @Test
  public void testCheckOBX3_OBX2_Success()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = 23391-6 / LN
    // OBX-2 = CWE
    OBX3 = new ComplexCodedElement("23391-6", "LN", "", "");
    OBX2 = "CWE";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "23391-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("23391-6", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "23391-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = LAB517 / PHINQUESTION
    // OBX-2 = CX
    OBX3 = new ComplexCodedElement("LAB517", "PHINQUESTION", "", "");
    OBX2 = "CX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "LAB517", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("LAB517", "PHINQUESTION", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "LAB517", "PHINQUESTION");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = PLT528 / PLT
    // OBX-2 = DT
    OBX3 = new ComplexCodedElement("PLT528", "PLT", "", "");
    OBX2 = "DT";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT528", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT528", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT528", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 30525-0 / LN
    // OBX-2 = NM
    OBX3 = new ComplexCodedElement("30525-0", "LN", "", "");
    OBX2 = "NM";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "30525-0", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("30525-0", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30525-0", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 30525-0 / LN
    // OBX-2 = SN
    OBX3 = new ComplexCodedElement("30525-0", "LN", "", "");
    OBX2 = "SN";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "30525-0", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("30525-0", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30525-0", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    // OBX-3 = 30959-1 / LN
    // OBX-2 = ST
    OBX3 = new ComplexCodedElement("30959-1", "LN", "", "");
    OBX2 = "ST";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "30959-1", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("30959-1", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30959-1", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());


    // OBX-3 = PLT2066 / PLT
    // OBX-2 = TX
    OBX3 = new ComplexCodedElement("PLT2066", "PLT", "", "");
    OBX2 = "TX";
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT2066", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT2066", "PLT", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT2066", "PLT");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(0, result.size());

  }


  @Test
  public void testCheckOBX3_OBX2_Fail()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = 23391-6 / LN
    // OBX-2 = CX
    OBX3 = new ComplexCodedElement("23391-6", "LN", "", "");
    OBX2 = "CX";
    List<String> result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "23391-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("23391-6", "LN", "ABC", "L");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "23391-6", "LN");
    result = testObject.checkOBX3_OBX2(OBX3, OBX2);
    assertEquals(1, result.size());
  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Success()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = PLT524/PLT
    // OBX-2 = CWE
    // OBX-5 = UNK / NULLFL

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "", "");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("", "", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("PLT524", "PLT", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "PLT524", "PLT");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(0, result.size());

  }

  @Test
  public void testCheckOBX3_OBX5_CWE_Fail()
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // OBX-3 = 30957-5/LN
    // OBX-2 = CWE
    // OBX-5 = UNK / NULLFL

    OBX3 = new ComplexCodedElement("30957-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    List<String> result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "", "");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("", "", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("UNK", "NULLFL", "ABC", "L");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "", "");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("", "", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("30957-5", "LN", "ABC", "L");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());

    OBX3 = new ComplexCodedElement("ABC", "L", "30957-5", "LN");
    OBX5 = new ComplexCodedElement("ABC", "L", "UNK", "NULLFL");
    result = testObject.checkOBX3_OBX5_CWE(OBX3, OBX5);
    assertEquals(1, result.size());
  }

  @Test
  public void testMessage() throws Exception {
    String globalFolder = "/RABIES";

    String profiles = StringUtils.join(globalFolder, "/Profile-2.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints-2.xml");
    String valueSets = StringUtils.join(globalFolder, "/ValueSets-2.xml");

    String message1FileName = "TestMessages/Rabies/Message1.txt";

    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message1 = new File(classLoader.getResource(message1FileName).getFile());
    String messageString = FileUtils.readFileToString(message1);
    Report report = validator.check(messageString, "ORU_R01");

    Set<String> keys = report.getEntries().keySet();
    int errors = 0;
    int alerts = 0;
    for (String key : keys) {
      List<Entry> entries = report.getEntries().get(key);
      if (entries != null && entries.size() > 0) {
        System.out.println("*** " + key + " ***");
        for (Entry entry : entries) {
          switch (entry.getClassification()) {
            case "Error":
              Util.printEntry(entry);
              errors++;
              break;
            case "Alert":
              Util.printEntry(entry);
              alerts++;
              break;
          }
        }
      }
    }
  }
}
