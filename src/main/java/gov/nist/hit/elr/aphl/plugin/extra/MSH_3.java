package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import gov.nist.hit.elr.plugin.utils.CSVUtils;
import gov.nist.hit.elr.plugin.utils.HierarchicDesignator;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public abstract class MSH_3 {

  public abstract String getFOLDER_MSH();

  public abstract String getMSH3_CSV();



  /**
   * 
   * @param e the MSH segment
   * @return
   * @throws IOException
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    java.util.List<String> messages = new ArrayList<String>();

    // MSH-3
    List<Element> MSH3List = Query.query(e, "3[1]").get();
    if (MSH3List == null || MSH3List.size() == 0) {
      // no MSH-3, we can move on, no check performed
      return messages;
    }

    Element MSH3 = MSH3List.apply(0);

    List<Simple> MSH3_1List = Query.queryAsSimple(MSH3, "1[1]").get();
    List<Simple> MSH3_2List = Query.queryAsSimple(MSH3, "2[1]").get();
    List<Simple> MSH3_3List = Query.queryAsSimple(MSH3, "3[1]").get();

    String MSH3_1 = MSH3_1List.size() > 0 ? MSH3_1List.apply(0).value().raw() : "";
    String MSH3_2 = MSH3_2List.size() > 0 ? MSH3_2List.apply(0).value().raw() : "";
    String MSH3_3 = MSH3_3List.size() > 0 ? MSH3_3List.apply(0).value().raw() : "";

    HierarchicDesignator _MSH3 = new HierarchicDesignator(MSH3_1, MSH3_2, MSH3_3);

    // MSH-11
    String _MSH11 = "";

    List<Simple> MSH11List = Query.queryAsSimple(e, "11[1].1[1]").get();
    if (MSH11List != null) {
      _MSH11 = MSH11List.size() > 0 ? MSH11List.apply(0).value().raw() : "";
    }

    messages.addAll(check(_MSH3, _MSH11));
    return messages;
  }

  public java.util.List<String> check(HierarchicDesignator MSH3, String MSH11) throws IOException {

    CSVUtils util = new CSVUtils();
    util.parseSendingApplication(getFOLDER_MSH(), getMSH3_CSV());

    java.util.List<String> messages = new ArrayList<String>();

    // MSH-3 check
    if (!util.getMSH3().contains(MSH3.normalize())) {
      messages.add(MSH3.prettyPrint() + " is not a valid value for MSH-3");
    }

    // MSH-3 - MSH-11 check
    if (util.getMSH3_MSH11().containsKey(MSH3.normalize())) {
      Set<String> expected = util.getMSH3_MSH11().get(MSH3.normalize());
      if (!expected.contains(MSH11)) {
        messages.add("The value '" + MSH11 + "' is not a valid MSH-11 value for this MSH-3 "
            + MSH3.prettyPrint());
      }
    }
    return messages;
  }
}
