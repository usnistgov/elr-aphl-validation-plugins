package gov.nist.hit.elr.aphl.plugin.extra.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.aphl.plugin.extra.PHLIP_SPM4;
import gov.nist.hit.elr.aphl.plugin.extra.SPM_4;
import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestPHLIP_SPM4 {

  private static SPM_4_csv testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new PHLIP_SPM4_csv();

  }

  @Before
  public void resetData() {

  }

  @Test
  public void testCheckSuccess() throws IOException {

    ComplexCodedElement SPM4 =
        new ComplexCodedElement("116155002", "SCT", "Ampulla of Vater cytologic material", "L");
    List<String> result = testObject.check(SPM4);
    assertEquals(0, result.size());

    SPM4 = new ComplexCodedElement("Ampulla of Vater cytologic material", "L", "116155002", "SCT");
    result = testObject.check(SPM4);
    assertEquals(0, result.size());

    SPM4 = new ComplexCodedElement("116155002", "SCT", "", "");
    result = testObject.check(SPM4);
    assertEquals(0, result.size());

    SPM4 = new ComplexCodedElement("", "", "116155002", "SCT");
    result = testObject.check(SPM4);
    assertEquals(0, result.size());

  }

  @Test
  public void testCheckFail() throws IOException {
    ComplexCodedElement SPM4 =
        new ComplexCodedElement("_116155002", "SCT", "Ampulla of Vater cytologic material", "L");
    List<String> result = testObject.check(SPM4);
    System.out.println(result);
    assertEquals(1, result.size());

    SPM4 = new ComplexCodedElement("Ampulla of Vater cytologic material", "L", "_116155002", "SCT");
    result = testObject.check(SPM4);
    System.out.println(result);
    assertEquals(1, result.size());

    SPM4 = new ComplexCodedElement("_116155002", "SCT", "", "");
    result = testObject.check(SPM4);
    assertEquals(1, result.size());

    SPM4 = new ComplexCodedElement("", "", "_116155002", "SCT");
    result = testObject.check(SPM4);
    assertEquals(1, result.size());
  }

  @Test
  @Ignore
  public void testMessage() throws Exception {

    String globalFolder = "/ARLN";

    String profiles = StringUtils.join(globalFolder, "/Profile.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
    String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

    String message1FileName = "ARLN/Message.txt";

    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message1 = new File(classLoader.getResource(message1FileName).getFile());
    String messageString = FileUtils.readFileToString(message1);
    Report report = validator.check(messageString, "5d557ed577493e608b838fa8");

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
