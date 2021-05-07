package gov.nist.hit.elr.aphl.plugin.extra;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.CodedElement;


public class TestCandidaExcludedOBX3 {

  private static CandidaExcludedOBX3 testObject;
  private static List<CodedElement> OBX3s;



  @BeforeClass
  public static void setUp() {
    testObject = new CandidaExcludedOBX3();
    OBX3s = new ArrayList<CodedElement>();

  }

  @Before
  public void resetData() {
    OBX3s.removeAll(OBX3s);
  }


  @Test
  public void testCheckSuccess() {
    CodedElement loinc1 = new CodedElement("555-3", "LN");
    CodedElement loinc2 = new CodedElement("18482-0", "LN");
    CodedElement loinc3 = new CodedElement("15378-3", "LN");
    CodedElement ce1 = new CodedElement("ABC", "CS1");
    CodedElement ce2 = new CodedElement("DEF", "CS2");
    CodedElement ce3 = new CodedElement("GHI", "CS1");


    OBX3s.add(ce1);
    OBX3s.add(ce2);
    OBX3s.add(ce3);
    OBX3s.add(loinc1);
    OBX3s.add(loinc2);
    OBX3s.add(loinc3);

    List<String> result = testObject.check(OBX3s);
    assertEquals(0, result.size());
  }

  @Test
  public void testCheckFail() {

    CodedElement excluded = new CodedElement("41852-5", "LN");

    OBX3s.add(excluded);

    List<String> result = testObject.check(OBX3s);
    assertEquals(1, result.size());
  }



  @Test
  @Ignore
  public void testMessage() throws Exception {

  }

}
