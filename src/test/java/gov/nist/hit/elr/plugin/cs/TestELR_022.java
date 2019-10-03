package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.apache.commons.collections4.SetUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.cs.ELR_022;

public class TestELR_022 {

  /**
   * ELR-022: MSH-21.3 (Universal ID) of an occurrence of MSH.21 (Message Profile Identifier) SHALL
   * contain the value "2.16.840.1.113883.9.11"
   */

  private static ELR_022 testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new ELR_022();
  }

  @Test
  public void testCheckSuccess() {
    HashSet<String> actual = SetUtils.hashSet("2.16.840.1.113883.9.11");
    boolean result = testObject.check(actual);
    assertTrue(result);

    actual = SetUtils.hashSet("ABC", "2.16.840.1.113883.9.11");
    result = testObject.check(actual);
    assertTrue(result);

    /*
     * With current implementation, this is valid. May change in the future.
     */
    actual = SetUtils.hashSet("2.16.840.1.113883.9.11", "2.16.840.1.113883.9.11");
    result = testObject.check(actual);
    assertTrue(result);

  }

  @Test
  public void testCheckFail() {
    HashSet<String> actual = SetUtils.hashSet("");
    boolean result = testObject.check(actual);
    assertFalse(result);

    actual = SetUtils.hashSet("ABC");
    result = testObject.check(actual);
    assertFalse(result);

    actual = SetUtils.hashSet("ABC", "DEF");
    result = testObject.check(actual);
    assertFalse(result);

    result = testObject.check(null);
    assertFalse(result);
  }
}
