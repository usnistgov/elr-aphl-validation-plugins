package gov.nist.hit.elr.aphl.plugin.extra.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.aphl.plugin.extra.MSH_3;
import gov.nist.hit.elr.aphl.plugin.extra.PHLIP_MSH_3;
import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.HierarchicDesignator;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestPHLIP_MSH3 {


  private static MSH_3_csv testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new PHLIP_MSH_3_csv();
  }


  @Test
  public void testCheckSuccess() throws IOException {
    HierarchicDesignator MSH3 =
        new HierarchicDesignator("Chemware.AK.Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    String MSH11 = "P";
    List<String> result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Chemware.AK.Prod".toUpperCase(),
        "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon".toUpperCase(), "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon".toUpperCase(), "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

  }

  @Test
  public void testCheckFail() throws IOException {
    HierarchicDesignator MSH3 =
        new HierarchicDesignator("Chemware.AK.Prod", "2.16.840.1.114222.4.3.3.42.2", "ISO");
    String MSH11 = "P";
    List<String> result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());


    MSH3 = new HierarchicDesignator("Chemware_AK_Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());

    MSH3 = new HierarchicDesignator("Chemware AK Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());

    MSH3 = new HierarchicDesignator("Chemware.AK.Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());
  }

  @Test
  @Ignore
  public void testMessage() throws Exception {

    String globalFolder = "/PHLIP";

    String profiles = StringUtils.join(globalFolder, "/Profile.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
    String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

    String message1FileName = "TestMessages/MSH3/Message1.txt";

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
        switch (key) {
          case "value-set":
            break;
          case "structure":
            break;
          case "content":
          default:
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
}
