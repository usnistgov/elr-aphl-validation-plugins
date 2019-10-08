package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.ArrayList;

import gov.nist.hit.elr.plugin.utils.CSVUtils;
import gov.nist.hit.elr.plugin.utils.HierarchicDesignator;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public abstract class MSH_4 {

  public abstract String getFOLDER_MSH();

  public abstract String getMSH4_CSV();


  /**
   * 
   * @param e the MSH segment
   * @return
   * @throws IOException
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {

    java.util.List<String> messages = new ArrayList<String>();

    // MSH-4
    List<Element> MSH4List = Query.query(e, "4[1]").get();
    if (MSH4List == null || MSH4List.size() == 0) {
      // no MSH-4, we can move on, no check performed
      return messages;
    }

    Element MSH4 = MSH4List.apply(0);

    List<Simple> MSH4_1List = Query.queryAsSimple(MSH4, "1[1]").get();
    List<Simple> MSH4_2List = Query.queryAsSimple(MSH4, "2[1]").get();
    List<Simple> MSH4_3List = Query.queryAsSimple(MSH4, "3[1]").get();

    String MSH4_1 = MSH4_1List.size() > 0 ? MSH4_1List.apply(0).value().raw() : "";
    String MSH4_2 = MSH4_2List.size() > 0 ? MSH4_2List.apply(0).value().raw() : "";
    String MSH4_3 = MSH4_3List.size() > 0 ? MSH4_3List.apply(0).value().raw() : "";

    HierarchicDesignator _MSH4 = new HierarchicDesignator(MSH4_1, MSH4_2, MSH4_3);

    messages.addAll(check(_MSH4));

    return messages;
  }

  public java.util.List<String> check(HierarchicDesignator MSH4) throws IOException {

    CSVUtils util = new CSVUtils();
    util.parseSendingFacility(getFOLDER_MSH(), getMSH4_CSV());
    java.util.List<String> messages = new ArrayList<String>();

    // MSH-4 check
    if (!util.getMSH4().contains(MSH4.normalize())) {
      messages.add(MSH4.prettyPrint() + " is not a valid value for MSH-4");
    }

    return messages;
  }

}
