package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.MyTreeNode;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestOBR_26 {

  private static OBR_26 testObject;
  private static MyTreeNode<String> parentOBR;
  private static MyTreeNode<String> parentOBX;
  private static MyTreeNode<String> childOBR;
  private static MyTreeNode<String> childOBX;


  private static MyTreeNode<String> child_2_OBR;

  private static MyTreeNode<String> parent_3_OBX;
  private static MyTreeNode<String> child_3_OBR;

  @BeforeClass
  public static void setUp() {
    testObject = new OBR_26();

    parentOBR = new MyTreeNode<String>(""); // nothing in OBR-26

    // OBX-3 = 1234-5 and OBX-4 = 1
    parentOBX = new MyTreeNode<String>("");
    MyTreeNode<String> obx3 = new MyTreeNode<String>("");
    obx3.addChild("1234-5");
    obx3.addChild("");
    obx3.addChild("LN");
    MyTreeNode<String> obx4 = new MyTreeNode<String>("1");

    parentOBX.addChild(obx3);
    parentOBX.addChild(obx4);

    // OBX-3 = 1234-5 and no OBX-4
    parent_3_OBX = new MyTreeNode<String>("");
    parent_3_OBX.addChild(obx3);

    // OBR-26.1 = 1234-5 and OBR-26.2 = 1
    childOBR = new MyTreeNode<String>("");
    MyTreeNode<String> obr26_1 = new MyTreeNode<String>("");
    obr26_1.addChild("1234-5");
    obr26_1.addChild("");
    obr26_1.addChild("LN");
    MyTreeNode<String> obr26_2 = new MyTreeNode<String>("1");

    childOBR.addChild(obr26_1);
    childOBR.addChild(obr26_2);


    // OBR-26.1 = 1234-5 and no OBR-26.2
    child_3_OBR = new MyTreeNode<String>("");
    child_3_OBR.addChild(obr26_1);

    //
    childOBX = new MyTreeNode<String>("");
    MyTreeNode<String> child_obx3 = new MyTreeNode<String>("");
    child_obx3.addChild("4567-8");
    child_obx3.addChild("");
    child_obx3.addChild("LN");
    MyTreeNode<String> child_obx4 = new MyTreeNode<String>("1");

    childOBX.addChild(child_obx3);
    childOBX.addChild(child_obx4);
  }

  @Test
  @Ignore
  public void testCheckSucess() throws Exception {
    Set<MyTreeNode<String>> OBXs =
        new HashSet<MyTreeNode<String>>(Arrays.asList(parentOBX, childOBX));
    List<String> result = testObject.check(childOBR, OBXs);
    assertEquals(0, result.size());

    OBXs.add(parent_3_OBX);
    result = testObject.check(child_3_OBR, OBXs);
    assertEquals(0, result.size());

    result = testObject.check(childOBR, OBXs);
    assertEquals(0, result.size());

  }

  @Test
  @Ignore
  public void testCheckFail() throws Exception {

    child_2_OBR = new MyTreeNode<String>("");

    MyTreeNode<String> obr26_1 = new MyTreeNode<String>("");
    obr26_1.addChild("ABCDEF");
    obr26_1.addChild("");
    obr26_1.addChild("LN");
    MyTreeNode<String> obr26_2 = new MyTreeNode<String>("1");

    child_2_OBR.addChild(obr26_1);
    child_2_OBR.addChild(obr26_2);

    Set<MyTreeNode<String>> OBXs = new HashSet<MyTreeNode<String>>();

    List<String> result = testObject.check(child_2_OBR, OBXs);
    assertEquals(1, result.size());

    OBXs.add(childOBX);
    result = testObject.check(child_2_OBR, OBXs);
    assertEquals(1, result.size());

    result = testObject.check(child_2_OBR, OBXs);
    assertEquals(1, result.size());

    OBXs.add(parentOBX);
    result = testObject.check(child_2_OBR, OBXs);
    assertEquals(1, result.size());


    result = testObject.check(child_3_OBR, OBXs);
    assertEquals(1, result.size());
  }

  @Test
  public void testMessage() throws Exception {

    String globalFolder = "/MessageProfile";

    String profiles = StringUtils.join(globalFolder, "/Profile.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints_obr-26.xml");
    String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

    String message1FileName = "TestMessages/OBR26/Message.txt";

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
            default:
              // Util.printEntry(entry);
          }
        }
      }
    }
    // System.out.println(errors);
    // System.out.println(alerts);
  }
}
