package gov.nist.hit.elr.radx.plugin.cs;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import hl7.v2.instance.Element;
import hl7.v2.instance.Field;
import hl7.v2.instance.Query;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class NIH_YYY {

  private String obxPath = "3[*].2[*].6[*].1[*]";
  private String elementPath = "";


  public void setElement(String element) {
    this.elementPath = element;
  }

  /**
   * @param context the root of the message
   * @return true if the assertion passes, false otherwise
   */
  public boolean assertion(Element context) {
    // element is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
    java.util.Set<String> _OBXx = new HashSet<String>();

    // extracting OBXs
    List<Element> OBX_List = Query.query(context, obxPath).get();
    Iterator<Element> it = OBX_List.iterator();
    while (it.hasNext()) {
      Element obx = it.next();
      // extracting OBX-BB
      List<Element> OBXBB_List = Query.query(obx, elementPath).get();
      if (OBXBB_List.size() == 0) {
        _OBXx.add("");
      }
      java.util.Set<String> tmp = new HashSet<String>();
      Iterator<Element> it2 = OBXBB_List.iterator();
      while (it2.hasNext()) {
        Field next2 = (Field) it2.next();
        int instance = next2.instance();
        String value = next2.rawMessageValue();
        tmp.add("[" + instance + "]" + " " + value);
      }
      _OBXx.add(StringUtils.join(tmp, "~"));
    }
    return check(_OBXx);
  }

  public boolean check(Set<String> obxx) {
    return obxx.size() <= 1;
  }
}
