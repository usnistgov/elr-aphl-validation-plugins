package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.CodedElement;

public class TestELR_027 {

  private static ELR_027 testObject;

  private static Set<CodedElement> OBX3s;

  private static CodedElement code1;
  private static CodedElement code2;
  private static CodedElement code3;
  private static CodedElement code4;
  private static CodedElement code5;

  @BeforeClass
  public static void setUp() {
    testObject = new ELR_027();
    OBX3s = new HashSet<CodedElement>();
    code1 = new CodedElement("21612-7", "Age - Reported", "LN");
    code2 = new CodedElement("29553-5", "Age Time Patient Calc", "LN");
    code3 = new CodedElement("35659-2", "Age at specimen collection", "LN");
    code4 = new CodedElement("30525-0", "Age", "LN");
    code5 = new CodedElement("AGE", "Age Local", "L");
  }

  @Before
  public void resetData() {
    OBX3s.removeAll(OBX3s);
  }

  @Test
  public void testCheckSuccess() {
    OBX3s.add(code1);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code2);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code3);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code4);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code1);
    OBX3s.add(code5);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code2);
    OBX3s.add(code5);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code3);
    OBX3s.add(code5);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code4);
    OBX3s.add(code5);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code1);
    OBX3s.add(code5);
    assertTrue(testObject.check(OBX3s));

    OBX3s.add(code2);
    assertTrue(testObject.check(OBX3s));
    resetData();

    OBX3s.add(code3);
    assertTrue(testObject.check(OBX3s));

    OBX3s.add(code4);
    assertTrue(testObject.check(OBX3s));
  }

  @Test
  public void testCheckFail() {
    // empty OBX-3 set
    assertFalse(testObject.check(OBX3s));

    // value not in the acceptable set
    OBX3s.add(code5);
    assertFalse(testObject.check(OBX3s));
  }

}
