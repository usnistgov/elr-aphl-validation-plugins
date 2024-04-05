package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.aphl.plugin.extra.DatesCheck.LocationDateList;
import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestDatesCheck {

  private static DatesCheck testObject;

  private static List<LocationDateList> dates;

  @BeforeClass
  public static void setUp() {
    testObject = new DatesCheck();
    testObject.setDefaultTz("-0500");
  }

  @Before
  public void setDates() {
    dates = new ArrayList<LocationDateList>();
  }

  @Test
  public void testCheckSuccess() {
    LocationDateList e = testObject.new LocationDateList();
    e.add("PID-7", "20191216");
    e.add("OBR-7", "20191217");
    e.add("OBX-14 ", "20191217");
    e.add("SPM-17.1", "20191217");
    e.add("SPM-17.2 ", "20191218");
    e.add("OBR-8", "20191218");
    e.add("SPM-18", "20191219");
    e.add("OBX-19 ", "20191220");
    e.add("OBR-22", "20191221");
    e.add("MSH-7", "20191222");
    dates.add(e);
    List<String> result = testObject.check(dates);
    assertEquals(0, result.size());

    // all the same
    setDates();
    e = testObject.new LocationDateList();
    e.add("PID-7", "20191216");
    e.add("OBR-7", "20191216");
    e.add("OBX-14 ", "20191216");
    e.add("SPM-17.1", "20191216");
    e.add("SPM-17.2 ", "20191216");
    e.add("OBR-8", "20191216");
    e.add("SPM-18", "20191216");
    e.add("OBX-19 ", "20191216");
    e.add("OBR-22", "20191216");
    e.add("MSH-7", "20191216");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // missing some
    setDates();
    e = testObject.new LocationDateList();
    e.add("PID-7", "20191216");
    e.add("OBX-14 ", "20191216");
    e.add("SPM-17.1", "20191216");
    e.add("OBR-8", "20191216");
    e.add("OBX-19 ", "20191216");
    e.add("MSH-7", "20191216");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    e = testObject.new LocationDateList();
    e.add("PID-7", "20191216");
    e.add("OBX-14 ", "20191217");
    e.add("SPM-17.1", "20191217");
    e.add("OBR-8", "20191218");
    e.add("OBX-19 ", "20191220");
    e.add("MSH-7", "20191222");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // different precision
    e = testObject.new LocationDateList();
    e.add("PID-7", "2019121612");
    e.add("OBR-7", "201912171212");
    e.add("OBX-14 ", "20191217121212");
    e.add("SPM-17.1", "20191217121212.3");
    e.add("SPM-17.2 ", "20191218121212.34");
    e.add("OBR-8", "20191218121212.345");
    e.add("SPM-18", "20191219121212.3456");
    e.add("OBX-19 ", "20191220121212.345");
    e.add("OBR-22", "20191221121212.34");
    e.add("MSH-7", "20191222121212.3");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // different precision && missing some
    e = testObject.new LocationDateList();
    e.add("OBR-7", "20191217121212.3");
    e.add("SPM-17.1", "20191217121212.3");
    e.add("OBR-8", "20191218121212.345");
    e.add("OBX-19 ", "20191220121212.345");
    e.add("MSH-7", "20191222121212.3");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // time zone
    e = testObject.new LocationDateList();
    e.add("PID-7", "201912161313-0500");
    e.add("OBR-7", "201912171313-0500");
    e.add("OBX-14 ", "201912171313-0500");
    e.add("SPM-17.1", "201912171313-0500");
    e.add("SPM-17.2 ", "201912181313-0500");
    e.add("OBR-8", "201912181313-0500");
    e.add("SPM-18", "201912191313-0500");
    e.add("OBX-19 ", "201912201313-0500");
    e.add("OBR-22", "201912211313-0500");
    e.add("MSH-7", "201912221313-0500");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // time zone or not
    e = testObject.new LocationDateList();
    e.add("PID-7", "201912161313-0500");
    e.add("OBR-7", "201912171313");
    e.add("OBX-14 ", "201912171313-0500");
    e.add("SPM-17.1", "201912171313");
    e.add("SPM-17.2 ", "201912181313-0500");
    e.add("OBR-8", "201912181313");
    e.add("SPM-18", "201912191313-0500");
    e.add("OBX-19 ", "201912201313");
    e.add("OBR-22", "201912211313-0500");
    e.add("MSH-7", "201912221313");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // different time zones
    e = testObject.new LocationDateList();
    e.add("OBR-7", "201912171400-0500");
    e.add("OBX-14", "201912171300-0600");
    e.add("SPM-17.1", "2019121712-0700");

    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());

    // OBR-7 = 0000
    e = testObject.new LocationDateList();
    e.add("PID-7", "20191216");
    e.add("OBR-7", "0000");
    e.add("OBX-14 ", "20191217");
    e.add("SPM-17.1", "20191217");
    e.add("SPM-17.2 ", "20191218");
    e.add("OBR-8", "20191218");
    e.add("SPM-18", "20191219");
    e.add("OBX-19 ", "20191220");
    e.add("OBR-22", "20191221");
    e.add("MSH-7", "20191222");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(0, result.size());
  }

  @Test
  public void testCheckFail() {
    LocationDateList e = testObject.new LocationDateList();
    e.add("PID-7", "20191222");
    e.add("OBR-7", "20191221");
    e.add("OBX-14 ", "20191220");
    e.add("SPM-17.1", "20191219");
    e.add("SPM-17.2 ", "20191218");
    e.add("OBR-8", "20191217");
    e.add("SPM-18", "20191216");
    e.add("OBX-19 ", "20191215");
    e.add("OBR-22", "20191214");
    e.add("MSH-7", "20191213");
    dates.add(e);
    List<String> result = testObject.check(dates);
    assertEquals(9, result.size());


    e = testObject.new LocationDateList();
    e.add("PID-7", "198303130000");
    e.add("OBR-7", "0000");
    e.add("OBX-14 ", "20221129000000");
    e.add("OBX-14 ", "20221129000000");
    e.add("OBX-14 ", "20221129000000");
    e.add("SPM-17.1", "20221129000000");
    e.add("SPM-17.2 ", "20221129000000");
    e.add("MSH-7", "20221130134900-0800");
    dates.add(e);
    result = testObject.check(dates);
    assertEquals(9, result.size());


  }

  @Test
  @Ignore
  public void testMessage() throws Exception {

    String globalFolder = "";

    String profiles = StringUtils.join(globalFolder, "/MessageProfile/DateChecks/Profile.xml");
    String constraints =
        StringUtils.join(globalFolder, "/MessageProfile/DateChecks/Constraints.xml");
    String valueSets = StringUtils.join(globalFolder, "/MessageProfile/DateChecks/ValueSets.xml");

    String message1FileName = "TestMessages/DateChecks/Message.txt";

    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message1 = new File(classLoader.getResource(message1FileName).getFile());
    String messageString = FileUtils.readFileToString(message1);
    Report report = validator.check(messageString, "5d5d68ce6dc12f5d54495e15");

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
