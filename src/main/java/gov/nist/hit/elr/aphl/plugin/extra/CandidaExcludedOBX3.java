package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.ArrayList;

import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.HierarchicDesignator;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class CandidaExcludedOBX3 {

  private final CodedElement excluded1 = new CodedElement("41852-5", "LN");
  // this is the fixed value for Candida program
  private final HierarchicDesignator candida =
      new HierarchicDesignator("CDC.ARLN.Candida", "2.16.840.1.114222.4.1.219331", "ISO");


  /**
   * OBX-3.1 (Identifier) SHALL NOT be valued '41852-5'.
   * 
   */

  /**
   * 
   * @param e the ORU^R01 Message group
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws Exception {
    java.util.List<String> messages = new java.util.ArrayList<String>();
    java.util.List<CodedElement> _OBX3s = new ArrayList<CodedElement>();

    // MSH-6 'CDC.ARLN.Candida^2.16.840.1.114222.4.1.219331^ISO'.
    List<Element> MSH6List = Query.query(e, "6[1]").get();
    if (MSH6List == null || MSH6List.size() == 0) {
      // no MSH-6, we can move on, no check performed
      return messages;
    }

    Element MSH6 = MSH6List.apply(0);

    List<Simple> MSH6_1List = Query.queryAsSimple(MSH6, "1[1]").get();
    List<Simple> MSH6_2List = Query.queryAsSimple(MSH6, "2[1]").get();
    List<Simple> MSH6_3List = Query.queryAsSimple(MSH6, "3[1]").get();

    String MSH6_1 = MSH6_1List.size() > 0 ? MSH6_1List.apply(0).value().raw() : "";
    String MSH6_2 = MSH6_2List.size() > 0 ? MSH6_2List.apply(0).value().raw() : "";
    String MSH6_3 = MSH6_3List.size() > 0 ? MSH6_3List.apply(0).value().raw() : "";

    HierarchicDesignator _MSH6 = new HierarchicDesignator(MSH6_1, MSH6_2, MSH6_3);

    // check if this is the Candida program, based on MSH-6.2
    if (_MSH6.getUniversalId().equals(candida.getUniversalId())) {
      // not candida, we stop
      return messages;
    }


    // The list of OBX-3
    List<Element> OBX3List = Query.query(e, "3[*].2[*].6[*].1[1].3[1]").get();
    Iterator<Element> it = OBX3List.iterator();
    while (it.hasNext()) {
      Element OBX3 = it.next();

      List<Simple> OBX3_1List = Query.queryAsSimple(OBX3, "1[1]").get();
      List<Simple> OBX3_3List = Query.queryAsSimple(OBX3, "3[1]").get();
      List<Simple> OBX3_4List = Query.queryAsSimple(OBX3, "4[1]").get();
      List<Simple> OBX3_6List = Query.queryAsSimple(OBX3, "6[1]").get();

      String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
      String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";

      CodedElement identifierOBX3 = new CodedElement(OBX3_1, OBX3_3);
      if (!identifierOBX3.isEmpty()) {
        _OBX3s.add(identifierOBX3);
      }

    }
    messages = check(_OBX3s);
    return messages;
  }

  public java.util.List<String> check(java.util.List<CodedElement> obx3) {
    java.util.List<String> messages = new java.util.ArrayList<String>();

    if (obx3 != null && obx3.contains(excluded1)) {
      messages.add("OBX-3.1 (Identifier) SHALL NOT be valued '41852-5'.");
    }

    return messages;
  }


}
