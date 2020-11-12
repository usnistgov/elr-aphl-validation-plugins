package gov.nist.hit.elr.aphl.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.EntityIdentifier;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestELR_21_1 {

  private static ELR_21_1 testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new ELR_21_1();
  }

  @Test
  public void testCheckSuccess() {
    List<EntityIdentifier> values = new ArrayList<EntityIdentifier>();
    EntityIdentifier ei = new EntityIdentifier("PHLabReport-NoAck", "phLabResultsELRv251",
        "2.16.840.1.113883.9.11", "ISO");
    values.add(ei);
    assertTrue(testObject.check(values));

    EntityIdentifier ei_2 = new EntityIdentifier("Test", "Test", "2.16.840.1.113883.9.12", "ISO");
    values.add(ei_2);
    assertTrue(testObject.check(values));

    EntityIdentifier ei_3 =
        new EntityIdentifier("PHLabReport-NoAck", "Test", "2.16.840.1.113883.9.12", "ISO");
    values.add(ei_3);
    assertTrue(testObject.check(values));
  }

  @Test
  public void testCheckFail() {
    List<EntityIdentifier> values = new ArrayList<EntityIdentifier>();
    EntityIdentifier ei = new EntityIdentifier("Test", "Test", "2.16.840.1.113883.9.12", "ISO");
    values.add(ei);
    assertFalse(testObject.check(values));

    EntityIdentifier ei_2 = new EntityIdentifier("Test", "Test", "2.16.840.1.113883.9.12", "ISO");
    values.add(ei_2);
    assertFalse(testObject.check(values));

    EntityIdentifier ei_3 =
        new EntityIdentifier("PHLabReport-NoAck", "Test", "2.16.840.1.113883.9.12", "ISO");
    values.add(ei_3);
    assertFalse(testObject.check(values));

    EntityIdentifier ei_4 =
        new EntityIdentifier("PHLabReport-NoAck", "", "2.16.840.1.113883.9.11", "ISO");
    values.add(ei_4);
    assertFalse(testObject.check(values));
  }

  @Test
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
    Report report = validator.check(messageString, "5d52faf184aeb5b33d8a5db2");

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
            case "Spec Error":
              Util.printEntry(entry);
              alerts++;
              break;
          }
        }
      }
    }
    
    //XML
  
    
    
  }
}
