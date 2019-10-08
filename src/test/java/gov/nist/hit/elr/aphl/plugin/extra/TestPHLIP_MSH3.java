package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.utils.HierarchicDesignator;

public class TestPHLIP_MSH3 {


  private static MSH_3 testObject;

  @BeforeClass
  public static void setUp() {
    testObject = new PHLIP_MSH_3();
  }


  @Test
  public void testCheckSuccess() throws IOException {
    HierarchicDesignator MSH3 =
        new HierarchicDesignator("Chemware.AK.Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    String MSH11 = "P";
    List<String> result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Chemware.AK.Prod".toUpperCase(),
        "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon".toUpperCase(), "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("Horizon".toUpperCase(), "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

    MSH3 = new HierarchicDesignator("", "2.16.840.1.114222.4.3.3.18", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(0, result.size());

  }

  @Test
  public void testCheckFail() throws IOException {
    HierarchicDesignator MSH3 =
        new HierarchicDesignator("Chemware.AK.Prod", "2.16.840.1.114222.4.3.3.42.2", "ISO");
    String MSH11 = "P";
    List<String> result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());


    MSH3 = new HierarchicDesignator("Chemware_AK_Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());

    MSH3 = new HierarchicDesignator("Chemware AK Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "P";
    result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());

    MSH3 = new HierarchicDesignator("Chemware.AK.Prod", "2.16.840.1.114222.4.3.3.42.1", "ISO");
    MSH11 = "T";
    result = testObject.check(MSH3, MSH11);
    assertEquals(1, result.size());
  }
}
