package gov.nist.hit.elr.aphl.plugin.extra;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.CodedElement;


public class TestCandidaExcludedOBX3 {

  private static CandidaExcludedOBX3 testObject;


  @BeforeClass
  public static void setUp() {
    testObject = new CandidaExcludedOBX3();
  }



  @Test
  public void testCheckSuccess() {
    CodedElement loinc1 = new CodedElement("555-3", "LN");
    CodedElement loinc2 = new CodedElement("18482-0", "LN");
    CodedElement loinc3 = new CodedElement("15378-3", "LN");
    CodedElement ce1 = new CodedElement("ABC", "CS1");
    CodedElement ce2 = new CodedElement("DEF", "CS2");
    CodedElement ce3 = new CodedElement("GHI", "CS1");

    List<String> result = testObject.check(loinc1);
    assertEquals(0, result.size());

    result = testObject.check(loinc2);
    assertEquals(0, result.size());

    result = testObject.check(loinc3);
    assertEquals(0, result.size());

    result = testObject.check(ce1);
    assertEquals(0, result.size());

    result = testObject.check(ce2);
    assertEquals(0, result.size());

    result = testObject.check(ce3);
    assertEquals(0, result.size());

    result = testObject.check(new CodedElement("41852-5", "L"));
    assertEquals(0, result.size());


  }

  @Test
  public void testCheckFail() {

    CodedElement excluded = new CodedElement("41852-5", "LN");

    List<String> result = testObject.check(excluded);
    assertEquals(1, result.size());
  }



  @Test
  @Ignore
  public void testMessage() throws Exception {

  }

}
