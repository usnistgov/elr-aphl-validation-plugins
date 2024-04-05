package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.ArrayList;

import gov.nist.hit.elr.plugin.utils.EntityIdentifierPair;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class OBR_29 {


  /*
   * Context ORU_R01
   * 
   * If OBR-29 (Parent) is valued, then there SHALL be at least one other OBR in the message where
   * OBR-2 (Placer Order Number) = OBR-29.1 (Placer Assigned Identifier) AND OBR-3 (Filler Order
   * Number) = OBR-29.2 (Filler Assigned Identifier)
   */

  public java.util.List<String> assertionWithCustomMessages(hl7.v2.instance.Element e) {

    java.util.List<String> messages = new java.util.ArrayList<String>();
    List<Element> OBRList = Query.query(e, "3[*].2[*].2[*]").get();
    if (OBRList.size() == 0) {
      // no OBR segment
      return messages;
    }

    List<Element> OBR29List = Query.query(e, "3[*].2[*].2[*].29[*]").get();

    // no OBR-29
    if (OBR29List.size() == 0) {
      return messages;
    }

    java.util.List<EntityIdentifierPair> placerFillerList = new ArrayList<EntityIdentifierPair>();
    java.util.List<EntityIdentifierPair> parentList = new ArrayList<EntityIdentifierPair>();

    // parse each OBR to extract OBR-2, OBR-3, OBR-29
    Iterator<Element> it = OBRList.iterator();
    while (it.hasNext()) {

      Element next = it.next();

      // extract OBR-2, OBR-3
      List<Simple> OBR2_1List = Query.queryAsSimple(next, "2[1].1[1]").get();
      List<Simple> OBR2_2List = Query.queryAsSimple(next, "2[1].2[1]").get();
      List<Simple> OBR2_3List = Query.queryAsSimple(next, "2[1].3[1]").get();
      List<Simple> OBR2_4List = Query.queryAsSimple(next, "2[1].4[1]").get();

      String OBR2_1 = OBR2_1List.size() > 0 ? OBR2_1List.apply(0).value().raw() : "";
      String OBR2_2 = OBR2_2List.size() > 0 ? OBR2_2List.apply(0).value().raw() : "";
      String OBR2_3 = OBR2_3List.size() > 0 ? OBR2_3List.apply(0).value().raw() : "";
      String OBR2_4 = OBR2_4List.size() > 0 ? OBR2_4List.apply(0).value().raw() : "";

      List<Simple> OBR3_1List = Query.queryAsSimple(next, "3[1].1[1]").get();
      List<Simple> OBR3_2List = Query.queryAsSimple(next, "3[1].2[1]").get();
      List<Simple> OBR3_3List = Query.queryAsSimple(next, "3[1].3[1]").get();
      List<Simple> OBR3_4List = Query.queryAsSimple(next, "3[1].4[1]").get();

      String OBR3_1 = OBR3_1List.size() > 0 ? OBR3_1List.apply(0).value().raw() : "";
      String OBR3_2 = OBR3_2List.size() > 0 ? OBR3_2List.apply(0).value().raw() : "";
      String OBR3_3 = OBR3_3List.size() > 0 ? OBR3_3List.apply(0).value().raw() : "";
      String OBR3_4 = OBR3_4List.size() > 0 ? OBR3_4List.apply(0).value().raw() : "";

      EntityIdentifierPair OBR2_OBR3 =
          new EntityIdentifierPair(OBR2_1, OBR2_2, OBR2_3, OBR2_4, OBR3_1, OBR3_2, OBR3_3, OBR3_4);

      placerFillerList.add(OBR2_OBR3);

      // extract OBR-29
      List<Simple> OBR29_1_1List = Query.queryAsSimple(next, "29[1].1[1].1[1]").get();
      List<Simple> OBR29_1_2List = Query.queryAsSimple(next, "29[1].1[1].2[1]").get();
      List<Simple> OBR29_1_3List = Query.queryAsSimple(next, "29[1].1[1].3[1]").get();
      List<Simple> OBR29_1_4List = Query.queryAsSimple(next, "29[1].1[1].4[1]").get();
      List<Simple> OBR29_2_1List = Query.queryAsSimple(next, "29[1].2[1].1[1]").get();
      List<Simple> OBR29_2_2List = Query.queryAsSimple(next, "29[1].2[1].2[1]").get();
      List<Simple> OBR29_2_3List = Query.queryAsSimple(next, "29[1].2[1].3[1]").get();
      List<Simple> OBR29_2_4List = Query.queryAsSimple(next, "29[1].2[1].4[1]").get();

      String OBR29_1_1 = OBR29_1_1List.size() > 0 ? OBR29_1_1List.apply(0).value().raw() : "";
      String OBR29_1_2 = OBR29_1_2List.size() > 0 ? OBR29_1_2List.apply(0).value().raw() : "";
      String OBR29_1_3 = OBR29_1_3List.size() > 0 ? OBR29_1_3List.apply(0).value().raw() : "";
      String OBR29_1_4 = OBR29_1_4List.size() > 0 ? OBR29_1_4List.apply(0).value().raw() : "";
      String OBR29_2_1 = OBR29_2_1List.size() > 0 ? OBR29_2_1List.apply(0).value().raw() : "";
      String OBR29_2_2 = OBR29_2_2List.size() > 0 ? OBR29_2_2List.apply(0).value().raw() : "";
      String OBR29_2_3 = OBR29_2_3List.size() > 0 ? OBR29_2_3List.apply(0).value().raw() : "";
      String OBR29_2_4 = OBR29_2_4List.size() > 0 ? OBR29_2_4List.apply(0).value().raw() : "";

      EntityIdentifierPair OBR29 = new EntityIdentifierPair(OBR29_1_1, OBR29_1_2, OBR29_1_3,
          OBR29_1_4, OBR29_2_1, OBR29_2_2, OBR29_2_3, OBR29_2_4);

      parentList.add(OBR29);
    }
    return check(placerFillerList, parentList);
  }

  public java.util.List<String> check(java.util.List<EntityIdentifierPair> placerFillerList,
      java.util.List<EntityIdentifierPair> parentList) {

    java.util.List<String> messages = new java.util.ArrayList<String>();

    for (int i = 0; i < parentList.size(); i++) {

      EntityIdentifierPair OBR29 = parentList.get(i);
      if (OBR29.isEmpty()) {
        continue;
      }

      java.util.List<EntityIdentifierPair> tmp = new java.util.ArrayList<EntityIdentifierPair>();
      tmp.addAll(placerFillerList);

      // to ensure that we are not matching with the same OBR segment
      EntityIdentifierPair removed = tmp.remove(i);

      // an OBR where OBR-29.1 = OBR-2 and OBR-29.2 = OBR-3 within the same OBR segment is also
      // invalid.
      if (removed.equals(OBR29)) {
        messages.add(
            "An invalid OBR segment was found because OBR-29.1 = OBR-2 and OBR-29.2 = OBR-3 in the OBR segment with OBR-29.1 = "
                + OBR29.getPlacerAssignedIdentifier().toShortString() + " and OBR-29.2 =  "
                + OBR29.getFillerAssignedIdentifier().toShortString() + "");
      }

      if (!tmp.contains(OBR29)) {
        messages.add(
            "No matching OBR-2 and OBR-3 values found in the message for the parent OBR with OBR-29.1 = "
                + OBR29.getPlacerAssignedIdentifier().toShortString() + " and OBR-29.2 = "
                + OBR29.getFillerAssignedIdentifier().toShortString() + "");
      }
    }
    return messages;
  }

}
