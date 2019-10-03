package gov.nist.hit.elr.plugin.cp;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class MSH_16 {

  private static String PHLabReportAck = "PHLabReport-Ack";

  /**
   * MSH-16 condition predicate : If the first component (Entity Identifier) of one occurrence of
   * MSH-21 (Message Profile Identifier) is 'PHLabReport-Ack'.
   */

  /**
   * 
   * @param e MSH context
   * @return true if any occurrence of MSH-21.1 is 'PHLabReport-Ack'
   */
  public boolean assertion(Element e) {

    List<Simple> MSH21_1List = Query.queryAsSimple(e, "21[*].1[1]").get();
    Iterator<Simple> it = MSH21_1List.iterator();

    while (it.hasNext()) {
      Simple next = it.next();
      String value = next.value().raw();
      if (PHLabReportAck.equals(value)) {
        return true;
      }
    }
    return false;
  }

}
