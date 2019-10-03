package gov.nist.hit.elr.plugin.cs;

import java.util.HashSet;
import java.util.Set;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_022 {

  private static String OID = "2.16.840.1.113883.9.11";

  /**
   * ELR-022: MSH-21.3 (Universal ID) of an occurrence of MSH.21 (Message Profile Identifier) SHALL
   * contain the value "2.16.840.1.113883.9.11"
   */

  /**
   * 
   * @param e MSH context
   * @return
   */
  public boolean assertion(Element e) {

    List<Simple> MSH21_3List = Query.queryAsSimple(e, "21[*].3[1]").get();
    Iterator<Simple> it = MSH21_3List.iterator();
    Set<String> actual = new HashSet<String>();
    while (it.hasNext()) {
      Simple next = it.next();
      String value = next.value().raw();
      actual.add(value);
    }
    return check(actual);
  }

  public boolean check(Set<String> actual) {
    if (actual == null || actual.size() == 0) {
      return false;
    }
    return actual.contains(OID);
  }
}
