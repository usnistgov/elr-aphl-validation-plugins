package gov.nist.hit.elr.radx.plugin.cs;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import hl7.v2.instance.Element;
import hl7.v2.instance.Field;
import hl7.v2.instance.Query;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class NIH_XXX {

  private String obxPath = "3[*].2[*].6[*].1[*]";
  private String element1 = "";
  private String element2 = "";



  public void setElement1(String element1) {
    this.element1 = element1;
  }

  public void setElement2(String element2) {
    this.element2 = element2;
  }

  /**
   * @param context the root of the message
   * @return true if the assertion passes, false otherwise
   */
  public boolean assertion(Element context) {
    // element1 is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
    // element1 path from the message root is element1.29[*]

    // element2 is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
    // element2 path from the message root is element2.17[*]

    // loop on OBXs so filter out the ones that are with empty element1
    List<Element> OBXList = Query.query(context, obxPath).get();
    Iterator<Element> it = OBXList.iterator();

    java.util.Set<String> _OBXx = new HashSet<String>();
    while (it.hasNext()) {
      Element next = it.next();
      // extracting OBX-AA
      List<Element> OBXAA_List = Query.query(next, element1).get();

      // extracting the values as String. The field does not repeat so the list
      // should only have one element. If that is not the case, other validation
      // mechanisms will catch that. We only focus on the first element of the List

      if (OBXAA_List.size() == 0) {
        // extracting OBX-BB. This field can repeat and is complex.
        List<Element> OBXBB_List = Query.query(next, element2).get();
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
    }
    return check(_OBXx);
  }

  public boolean check(Set<String> obxx) {
    return obxx.size() <= 1;
  }
}
