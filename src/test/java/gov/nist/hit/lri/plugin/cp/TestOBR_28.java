package gov.nist.hit.lri.plugin.cp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestOBR_28 {

  // private static Logger logger = Logger.getLogger(TestOBR_28.class.getName());

  private static OBR_28 testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new OBR_28();
  }

  @Test
  public void testCheckSuccess() {

    HashSet<String> obr49 = new HashSet<String>(Arrays.asList("CC", "BCC"));
    boolean result = testObject.check(obr49);
    assertTrue(result);

    obr49 = new HashSet<String>(Arrays.asList("CC"));
    result = testObject.check(obr49);
    assertTrue(result);


    obr49 = new HashSet<String>(Arrays.asList("BCC"));
    result = testObject.check(obr49);
    assertTrue(result);


    obr49 = new HashSet<String>(Arrays.asList("A", "B", "C", "BCC", "XYZ"));
    result = testObject.check(obr49);
    assertTrue(result);
  }

  @Test
  public void testCheckFail() {
    HashSet<String> obr49 = new HashSet<String>(Arrays.asList("A", "B", "C", "XYZ"));
    boolean result = testObject.check(obr49);
    assertFalse(result);


    obr49 = new HashSet<String>();
    result = testObject.check(obr49);
    assertFalse(result);
  }

}
