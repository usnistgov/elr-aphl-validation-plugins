package gov.nist.hit.elr.plugin.cs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.SetUtils.SetView;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_021 {

  private static String ALL[] = {"PHLabReport-Ack", "PHLabReport-NoAck", "PHLabReport-Batch"};

  /**
   * ELR-021: MSH.21.1 (Entity Identifier) of an occurrence of MSH.21 (Message Profile Identifier)
   * SHALL be valued with 'PHLabReport-Ack' OR 'PHLabReport-NoAck' OR 'PHLabReport-Batch'
   */

  /**
   * 
   * @param e MSH context
   * @return
   */
  public boolean assertion(Element e) {
    List<Simple> MSH21_1List = Query.queryAsSimple(e, "21[*].1[1]").get();
    Iterator<Simple> it = MSH21_1List.iterator();
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

    Set<String> expected = Collections.<String>emptySet();
    Collections.addAll(expected = new HashSet<String>(Arrays.asList(ALL)));

    SetView<String> intersection = SetUtils.intersection(actual, expected);
    return intersection.size() > 0;

  }
}
