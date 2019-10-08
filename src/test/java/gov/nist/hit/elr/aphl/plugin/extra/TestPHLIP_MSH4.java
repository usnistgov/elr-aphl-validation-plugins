package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.HierarchicDesignator;

public class TestPHLIP_MSH4 {
  private static MSH_4 testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new PHLIP_MSH_4();
  }


  @Test
  public void testCheckSuccess() throws IOException {
    HierarchicDesignator MSH4 =
        new HierarchicDesignator("AK.Anchorage.SPHL", "2.16.840.1.114222.4.1.14410", "ISO");
    List<String> result = testObject.check(MSH4);
    assertEquals(0, result.size());

    MSH4 = new HierarchicDesignator("AK.Anchorage.SPHL".toUpperCase(),
        "2.16.840.1.114222.4.1.14410", "ISO");
    result = testObject.check(MSH4);
    assertEquals(0, result.size());

    MSH4 = new HierarchicDesignator("", "2.16.840.1.114222.4.1.14410", "ISO");
    result = testObject.check(MSH4);
    assertEquals(0, result.size());

  }

  @Test
  public void testCheckFail() throws IOException {
    HierarchicDesignator MSH4 =
        new HierarchicDesignator("AK.Anchorage.SPHL", "2.16.840.1.114222.4.1.11476", "ISO");
    List<String> result = testObject.check(MSH4);
    assertEquals(1, result.size());

    MSH4 = new HierarchicDesignator("AK_Anchorage_SPHL", "2.16.840.1.114222.4.1.14410", "ISO");
    result = testObject.check(MSH4);
    assertEquals(1, result.size());


    MSH4 = new HierarchicDesignator("", "2.16.840.1.114222.4.1.14410.32", "ISO");
    result = testObject.check(MSH4);
    assertEquals(1, result.size());
  }
}
