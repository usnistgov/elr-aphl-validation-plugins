package gov.nist.hit.elr.aphl.plugin.extra;


import java.util.ArrayList;

import gov.nist.hit.elr.plugin.utils.CodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;



public class CandidaOBX3 {

  /**
   * The message SHALL contain one OBX with OBX-3 = [555-3, 18482-0, 15378-3]
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
      String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
      String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

      CodedElement identifierOBX3 = new CodedElement(OBX3_1, OBX3_3);
      if (!identifierOBX3.isEmpty() ) {
        _OBX3s.add(identifierOBX3);
      }

      //uncomment if we want to check alternate code as well (OBX-3.4)
      //      CodedElement alternateOBX3 = new CodedElement(OBX3_4, OBX3_6);
      //      if (!alternateOBX3.isEmpty() ) {
      //        _OBX3s.add(alternateOBX3);
      //      }

    }
    messages = check(_OBX3s);
    return messages;
  }

  public java.util.List<String> check(java.util.List<CodedElement> obx3s) {
    java.util.List<String> messages = new java.util.ArrayList<String>();

    CodedElement loinc1 = new CodedElement("555-3","LN");
    CodedElement loinc2 = new CodedElement("18482-0","LN");
    CodedElement loinc3 = new CodedElement("15378-3","LN");

    if(obx3s.contains(loinc1)||obx3s.contains(loinc2)||obx3s.contains(loinc3)) {
      return messages;
    }
    messages.add("The message SHALL contain one OBX where OBX-3 is one of the following LOINC codes [555-3, 18482-0, 15378-3]");
    return messages;
  }
}
