package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.ArrayList;

import gov.nist.healthcare.utils.date.DateUtil;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Segment;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class DTM {

  /**
   * 
   * @param e
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) {
    java.util.List<String> messages = new ArrayList<String>();
    String name = "";
    // get segment name
    if (e instanceof Segment) {
      Segment s = (Segment) e;
      // System.err.println(s.location().path());
      name = s.location().path();
    }
    List<Simple> DTMList;
    switch (name) {
      case "MSH":
        DTMList = Query.queryAsSimple(e, "7[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        Iterator<Simple> it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location MSH-7 is not a valid date.");
          }
        }
        break;
      case "SFT":
        DTMList = Query.queryAsSimple(e, "6[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location SFT-6 is not a valid date.");
          }
        }
        break;
      case "PID":
        DTMList = Query.queryAsSimple(e, "7[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location PID-7 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "29[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location PID-29 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "33[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location PID-33 is not a valid date.");
          }
        }
        break;
      case "PV1":
        DTMList = Query.queryAsSimple(e, "44[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location PV1-44 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "45[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location PV1-45 is not a valid date.");
          }
        }
        break;
      case "OBR":
        DTMList = Query.queryAsSimple(e, "7[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location OBR-7 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "8[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location OBR-8 is not a valid date.");
          }
        }
        break;
      case "OBX":
        DTMList = Query.queryAsSimple(e, "14[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location OBX-14 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "19[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location OBX-19 is not a valid date.");
          }
        }
        break;
      case "SPM":
        DTMList = Query.queryAsSimple(e, "17[*].1[1].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location SPM-17.1 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "17[*].2[1].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location SPM-17.2 is not a valid date.");
          }
        }
        DTMList = Query.queryAsSimple(e, "18[*].1[1]").get();
        if (DTMList == null || DTMList.size() == 0) {
          // TODO
        }
        it = DTMList.iterator();
        while (it.hasNext()) {
          Simple simple = it.next();
          String DTM = simple.value().raw();
          if (!check(DTM)) {
            messages.add("The value " + DTM + " at location SPM-18 is not a valid date.");
          }
        }
      default:
        return messages;
    }
    // System.err.println(messages);
    return messages;
  }

  public boolean check(String dtm) {
    return DateUtil.isValid(dtm);
  }

}
