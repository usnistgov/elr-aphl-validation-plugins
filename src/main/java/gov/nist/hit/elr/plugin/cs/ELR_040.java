package gov.nist.hit.elr.plugin.cs;

import java.util.Arrays;
import java.util.Collections;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_040 {

  /**
   * ELR-040: OBR-3 (Filler Order Number) SHALL NOT contain the same value as another occurrence of
   * OBR-3 (Filler Order Number) in the message.
   */

  /**
   * 
   * @param e the root message
   * @return
   */
  public boolean assertion(Element e) {

    List<Element> OBR3List = Query.query(e, "3[*].2[*].2[*].3[*]").get();
    Iterator<Element> it = OBR3List.iterator();
    java.util.List<java.util.List<String>> values =
        new java.util.ArrayList<java.util.List<String>>();
    while (it.hasNext()) {
      Element next = it.next();

      List<Simple> OBR3_1List = Query.queryAsSimple(next, "1[1]").get();
      List<Simple> OBR3_2List = Query.queryAsSimple(next, "2[1]").get();
      List<Simple> OBR3_3List = Query.queryAsSimple(next, "3[1]").get();
      List<Simple> OBR3_4List = Query.queryAsSimple(next, "4[1]").get();

      String OBR3_1 = OBR3_1List.size() > 0 ? OBR3_1List.apply(0).value().raw() : "";
      String OBR3_2 = OBR3_2List.size() > 0 ? OBR3_2List.apply(0).value().raw() : "";
      String OBR3_3 = OBR3_3List.size() > 0 ? OBR3_3List.apply(0).value().raw() : "";
      String OBR3_4 = OBR3_4List.size() > 0 ? OBR3_4List.apply(0).value().raw() : "";

      java.util.List<String> OBR3 = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);

      values.add(OBR3);
    }
    return check(values);
  }

  public boolean check(java.util.List<java.util.List<String>> actual) {
    if (actual == null || actual.size() == 0) {
      return true;
    }
    long count =
        actual.stream().filter(e -> Collections.frequency(actual, e) > 1).distinct().count();
    return count == 0;
  }
}
