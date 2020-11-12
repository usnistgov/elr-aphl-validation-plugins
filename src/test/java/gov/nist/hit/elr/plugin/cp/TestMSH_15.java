package gov.nist.hit.elr.plugin.cp;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestMSH_15 {

  private static Logger logger = Logger.getLogger(TestMSH_15.class.getName());

  private static String globalFolder = "/MessageProfile";

  private static String profiles;
  private static String constraints;
  private static String valueSets;

  private static String message1FileName = "TestMessages/MSH15/Message1.txt";
  private static String message2FileName = "TestMessages/MSH15/Message2.txt";
  private static String message3FileName = "TestMessages/MSH15/Message3.txt";
  private static String message4FileName = "TestMessages/MSH15/Message4.txt";

  @BeforeClass
  public static void setUp() {
    profiles = StringUtils.join(globalFolder, "/Profile.xml");
    constraints = StringUtils.join(globalFolder, "/Constraints.xml");
    valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");
  }

  /**
   * MSH-21.1 = PHLabReport-Ack, MSH-15 is present. Predicate is true, usage is R. No error/alert
   * expected
   * 
   * @throws Exception
   */
  @Test
  public void test1() throws Exception {
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
            case "Spec Error":
              Util.printEntry(entry);
              alerts++;
              break;
          }
        }
      }
    }
    assertEquals(0, errors);
    assertEquals(0, alerts);
  }

  /**
   * MSH-21.1 = PHLabReport-Ack, MSH-15 is absent. Predicate is true, usage is R. One error, no
   * alert expected
   * 
   * @throws Exception
   */
  @Test
  public void test2() throws Exception {
    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message2 = new File(classLoader.getResource(message2FileName).getFile());
    String messageString = FileUtils.readFileToString(message2);
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
    assertEquals(2, errors);
    assertEquals(0, alerts);
  }

  /**
   * MSH-21.1 != PHLabReport-Ack, MSH-15 is present. Predicate is false, usage is RE. No error, no
   * alert expected
   * 
   * @throws Exception
   */
  @Test
  public void test3() throws Exception {
    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message3 = new File(classLoader.getResource(message3FileName).getFile());
    String messageString = FileUtils.readFileToString(message3);
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
    assertEquals(0, errors);
    assertEquals(0, alerts);
  }

  /**
   * MSH-21.1 != PHLabReport-Ack, MSH-15 is absent. Predicate is false, usage is RE. No error, no
   * alert expected
   * 
   * @throws Exception
   */
  @Test
  public void test4() throws Exception {
    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message4 = new File(classLoader.getResource(message4FileName).getFile());
    String messageString = FileUtils.readFileToString(message4);
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
    assertEquals(0, errors);
    assertEquals(0, alerts);
  }

}
