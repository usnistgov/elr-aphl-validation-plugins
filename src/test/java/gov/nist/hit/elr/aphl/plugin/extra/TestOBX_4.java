package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestOBX_4 {

  private static OBX_4 testObject;

  private static List<CodedElement> OBX3s;

  @BeforeClass
  public static void setUp() {
    testObject = new OBX_4();
    OBX3s = new ArrayList<CodedElement>();
  }

  @Before
  public void resetData() {
    OBX3s.removeAll(OBX3s);
  }

  @Test
  public void testCheckSuccess() {
    CodedElement ce1 = new CodedElement("ABC", "CS1");
    CodedElement ce2 = new CodedElement("DEF", "CS2");
    CodedElement ce3 = new CodedElement("GHI", "CS1");

    OBX3s.add(ce1);
    OBX3s.add(ce2);
    OBX3s.add(ce3);

    // OBX-3 are all unique, no OBX-4
    List<String> result = testObject.check(OBX3s, ce1, "");
    assertEquals(0, result.size());

    result = testObject.check(OBX3s, ce2, "");
    assertEquals(0, result.size());

    result = testObject.check(OBX3s, ce3, "");
    assertEquals(0, result.size());

    // OBX-3 are all unique, with a value OBX-4
    result = testObject.check(OBX3s, ce1, "1");
    assertEquals(0, result.size());

    result = testObject.check(OBX3s, ce2, "2");
    assertEquals(0, result.size());

    result = testObject.check(OBX3s, ce3, "z");
    assertEquals(0, result.size());

    // Multiple OBX-3, with a value OBX-4
    OBX3s.add(ce1);
    OBX3s.add(ce2);
    OBX3s.add(ce3);

    result = testObject.check(OBX3s, ce1, "1");
    assertEquals(Collections.frequency(OBX3s, ce1), 2);
    assertEquals(0, result.size());

    result = testObject.check(OBX3s, ce2, "2");
    assertEquals(Collections.frequency(OBX3s, ce2), 2);
    assertEquals(0, result.size());

    result = testObject.check(OBX3s, ce3, "z");
    assertEquals(Collections.frequency(OBX3s, ce3), 2);
    assertEquals(0, result.size());
  }

  @Test
  public void testCheckFail() {
    CodedElement ce1 = new CodedElement("ABC", "CS1");
    CodedElement ce2 = new CodedElement("DEF", "CS2");
    CodedElement ce3 = new CodedElement("GHI", "CS1");

    OBX3s.add(ce1);
    OBX3s.add(ce2);
    OBX3s.add(ce3);
    OBX3s.add(ce1);
    OBX3s.add(ce2);
    OBX3s.add(ce3);

    // Multiple OBX-3, no OBX-4
    List<String> result = testObject.check(OBX3s, ce1, "");
    assertEquals(Collections.frequency(OBX3s, ce1), 2);
    assertEquals(1, result.size());

    result = testObject.check(OBX3s, ce2, "");
    assertEquals(Collections.frequency(OBX3s, ce2), 2);
    assertEquals(1, result.size());

    result = testObject.check(OBX3s, ce3, "");
    assertEquals(Collections.frequency(OBX3s, ce3), 2);
    assertEquals(1, result.size());

  }

  @Test
  public void testMessage() throws Exception {

    String globalFolder = "/MessageProfile";

    String profiles = StringUtils.join(globalFolder, "/Profile.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
    String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

    String message1FileName = "TestMessages/OBX4/Message1.txt";

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
