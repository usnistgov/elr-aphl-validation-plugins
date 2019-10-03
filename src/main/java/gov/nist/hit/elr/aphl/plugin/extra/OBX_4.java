package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.ArrayList;
import java.util.Collections;

import gov.nist.hit.elr.plugin.utils.CodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class OBX_4 {

  /**
   * OBX-4 condition predicate/conformance statement : If there are multiple OBX segments associated
   * with the same OBR segment that have the same OBX-3 values for (OBX-3.1 and OBX-3.3) or (OBX-3.4
   * and OBX-3.6).
   */

  /**
   * 
   * @param e the ORDER_OBSERVATION group
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) {
    java.util.List<String> messages = new java.util.ArrayList<String>();

    List<Element> OBXList = Query.query(e, "6[*].1[1]").get();
    if (OBXList == null || OBXList.size() == 0) {
      // no OBX, we can move on, no check performed
      return messages;
    }
    java.util.List<CodedElement> _OBX3s = new ArrayList<CodedElement>();
    Iterator<Element> it = OBXList.iterator();
    while (it.hasNext()) {

      Element OBX = it.next();
      // OBX-3
      List<Simple> OBX3_1List = Query.queryAsSimple(OBX, "3[1].1[1]").get();
      List<Simple> OBX3_3List = Query.queryAsSimple(OBX, "3[1].3[1]").get();
      List<Simple> OBX3_4List = Query.queryAsSimple(OBX, "3[1].4[1]").get();
      List<Simple> OBX3_6List = Query.queryAsSimple(OBX, "3[1].6[1]").get();

      String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
      String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";
      String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
      String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

      CodedElement identifierOBX3 = new CodedElement(OBX3_1, OBX3_3);
      CodedElement alternateOBX3 = new CodedElement(OBX3_4, OBX3_6);

      if (!identifierOBX3.isEmpty()) {
        _OBX3s.add(identifierOBX3);
      }
      if (!alternateOBX3.isEmpty()) {
        _OBX3s.add(alternateOBX3);
      }

    }
    Iterator<Element> it2 = OBXList.iterator();
    while (it2.hasNext()) {

      Element OBX = it2.next();
      // OBX-3
      List<Simple> OBX3_1List = Query.queryAsSimple(OBX, "3[1].1[1]").get();
      List<Simple> OBX3_3List = Query.queryAsSimple(OBX, "3[1].3[1]").get();
      List<Simple> OBX3_4List = Query.queryAsSimple(OBX, "3[1].4[1]").get();
      List<Simple> OBX3_6List = Query.queryAsSimple(OBX, "3[1].6[1]").get();

      String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
      String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";
      String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
      String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

      CodedElement identifierOBX3 = new CodedElement(OBX3_1, OBX3_3);
      CodedElement alternateOBX3 = new CodedElement(OBX3_4, OBX3_6);

      // OBX-4
      List<Simple> OBX4List = Query.queryAsSimple(OBX, "4[1]").get();
      String OBX4 = OBX4List.size() > 0 ? OBX4List.apply(0).value().raw() : "";

      if (!identifierOBX3.isEmpty()) {
        messages.addAll(check(_OBX3s, identifierOBX3, OBX4));
      }
      if (!alternateOBX3.isEmpty()) {
        messages.addAll(check(_OBX3s, alternateOBX3, OBX4));
      }
    }
    return messages;
  }

  public java.util.List<String> check(java.util.List<CodedElement> obx3s, CodedElement obx3,
      String obx4) {
    java.util.List<String> messages = new java.util.ArrayList<String>();
    int count = Collections.frequency(obx3s, obx3);
    if (count > 1 && "".equals(obx4)) {
      messages.add("OBX-4 is required with OBX-3 (" + obx3.prettyPrint() + ")");
      // messages.add(
      // "The combination of OBX-3 (" + obx3.prettyPrint() + ") and OBX-4 (" + obx4 + ") is not
      // unique");

    }
    return messages;
  }
}
