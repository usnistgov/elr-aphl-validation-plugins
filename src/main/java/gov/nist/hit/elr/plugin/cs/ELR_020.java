package gov.nist.hit.elr.plugin.cs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_020 {

  private static String PHLabReportAck = "PHLabReport-Ack";
  private static String NE = "NE";
  private static String ALL[] = {"AL", "NE", "ER", "SU"};

  /**
   * ELR-020: MSH-16 (Application Acknowledgement Type) SHALL contain the constant value 'AL', 'NE',
   * 'ER', or 'SU', IF any occurrence of MSH-21.1 (Entity Identifier) is 'PHLabReport-Ack', ELSE, if
   * valued, SHALL contain the constant value 'NE' .
   */

  /**
   * 
   * @param e MSH context
   * @return
   */
  public boolean assertion(Element e) {

    List<Simple> MSH16List = Query.queryAsSimple(e, "16[1]").get();
    String MSH16 = "";

    if (MSH16List != null && MSH16List.size() != 0) {
      // MSH-16 is present
      MSH16 = MSH16List.apply(0).value().raw();
    }

    List<Simple> MSH21_1List = Query.queryAsSimple(e, "21[*].1[1]").get();
    Iterator<Simple> it = MSH21_1List.iterator();
    Set<String> MSH21_1 = new HashSet<String>();

    while (it.hasNext()) {
      Simple next = it.next();
      String value = next.value().raw();
      MSH21_1.add(value);
    }
    return check(MSH16, MSH21_1);
  }

  public boolean check(String actual_MSH16, Set<String> actualMSH21_1) {
    Set<String> expected = Collections.<String>emptySet();
    Collections.addAll(expected = new HashSet<String>(Arrays.asList(ALL)));

    if (actualMSH21_1 == null || actualMSH21_1.size() == 0) {
      return "".equals(actual_MSH16) || NE.equals(actual_MSH16);
    }

    if (actualMSH21_1.contains(PHLabReportAck)) {
      return expected.contains(actual_MSH16);
    }

    return "".equals(actual_MSH16) || NE.equals(actual_MSH16);

  }
}
