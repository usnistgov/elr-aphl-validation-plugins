package gov.nist.hit.elr.aphl.plugin.extra;

import gov.nist.hit.elr.plugin.utils.CodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public class CandidaExcludedOBX3Old {

  private final CodedElement excluded1 = new CodedElement("41852-5", "LN");


  /**
   * OBX-3.1 (Identifier) SHALL NOT be valued '41852-5'.
   * 
   */

  /**
   * 
   * @param e the OBX segment
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws Exception {
    java.util.List<String> messages = new java.util.ArrayList<String>();

    // parse OBX-3
    List<Element> OBX3List = Query.query(e, "3[1]").get();
    if (OBX3List == null || OBX3List.size() == 0) {
      // no OBX-3, we can move on, no check performed
      return messages;
    }
    Element OBX3 = OBX3List.apply(0);

    List<Simple> OBX3_1List = Query.queryAsSimple(OBX3, "1[1]").get();
    List<Simple> OBX3_3List = Query.queryAsSimple(OBX3, "3[1]").get();


    String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
    String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";

    CodedElement obx3 = new CodedElement(OBX3_1, OBX3_3);

    return check(obx3);
  }

  public java.util.List<String> check(CodedElement obx3) {
    java.util.List<String> messages = new java.util.ArrayList<String>();

    if (excluded1.equals(obx3)) {
      messages.add("OBX-3.1 (Identifier) SHALL NOT be valued '41852-5'.");
    }

    return messages;
  }


}
