package gov.nist.hit.lri.plugin.cp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class OBR_28 {

  private static String ALL[] = {"CC", "BCC"};

  /**
   * OBR-28 Condition Predicate: If CWE_03.1 (Identifier) or CWE_03.4 (Alternate Identifier) of at
   * least one occurrence of OBR-49 (Result Handling) is valued CC or BCC
   */

  /**
   * @param e OBR context
   * @return true if any occurrence of OBR-49.1 or OBR-49.4 is CC or BCC
   */
  public boolean assertion(Element e) {
    Set<String> values = new HashSet<String>();
    List<Simple> OBR_49_1List = Query.queryAsSimple(e, "49[*].1[1]").get();
    Iterator<Simple> it = OBR_49_1List.iterator();

    while (it.hasNext()) {
      Simple next = it.next();
      String value = next.value().raw();
      values.add(value.toUpperCase());
    }

    List<Simple> OBR_49_4List = Query.queryAsSimple(e, "49[*].4[1]").get();
    it = OBR_49_4List.iterator();

    while (it.hasNext()) {
      Simple next = it.next();
      String value = next.value().raw();
      values.add(value.toUpperCase());
    }

    return check(values);
  }


  boolean check(Set<String> actualOBR49_1) {
    Set<String> expected = Collections.<String>emptySet();
    Collections.addAll(expected = new HashSet<String>(Arrays.asList(ALL)));
    actualOBR49_1.retainAll(expected);
    return actualOBR49_1.size() > 0;
  }
}
