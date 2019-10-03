package gov.nist.hit.elr.aphl.plugin.cs;

import gov.nist.hit.elr.plugin.utils.EntityIdentifier;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_21_1 {

  private static String EntityIdentifier = "PHLabReport-NoAck";
  private static String NamespaceID = "phLabResultsELRv251";
  private static String UniversalID = "2.16.840.1.113883.9.11";
  private static String UniversalIDType = "ISO";

  /**
   * ARLN-21: One occurrence of MSH-21 SHALL be valued
   * 'PHLabReport-NoAck^phLabResultsELRv251^2.16.840.1.113883.9.11^ISO'
   */

  /**
   * 
   * @param e MSH context
   * @return
   */
  public boolean assertion(Element e) {
    List<Element> MSH21List = Query.query(e, "21[*]").get();
    Iterator<Element> it = MSH21List.iterator();
    java.util.List<EntityIdentifier> values = new java.util.ArrayList<EntityIdentifier>();

    while (it.hasNext()) {
      Element next = it.next();

      List<Simple> MSH21_1List = Query.queryAsSimple(next, "1[1]").get();
      List<Simple> MSH21_2List = Query.queryAsSimple(next, "2[1]").get();
      List<Simple> MSH21_3List = Query.queryAsSimple(next, "3[1]").get();
      List<Simple> MSH21_4List = Query.queryAsSimple(next, "4[1]").get();

      String MSH21_1 = MSH21_1List.size() > 0 ? MSH21_1List.apply(0).value().raw() : "";
      String MSH21_2 = MSH21_2List.size() > 0 ? MSH21_2List.apply(0).value().raw() : "";
      String MSH21_3 = MSH21_3List.size() > 0 ? MSH21_3List.apply(0).value().raw() : "";
      String MSH21_4 = MSH21_4List.size() > 0 ? MSH21_4List.apply(0).value().raw() : "";

      EntityIdentifier MSH21 = new EntityIdentifier(MSH21_1, MSH21_2, MSH21_3, MSH21_4);

      values.add(MSH21);
    }
    return check(values);
  }

  public boolean check(java.util.List<EntityIdentifier> values) {
    EntityIdentifier expected =
        new EntityIdentifier(EntityIdentifier, NamespaceID, UniversalID, UniversalIDType);
    if (values == null || values.size() == 0) {
      return false;
    }
    long count = values.stream().filter(e -> e.equals(expected)).count();
    return count == 1;
  }

}
