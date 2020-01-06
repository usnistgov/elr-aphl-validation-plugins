package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import gov.nist.healthcare.utils.date.DateUtil;
import gov.nist.healthcare.utils.date.ExtendedDate;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class DateChecks {

  private static Logger logger = Logger.getLogger(DateChecks.class.getName());

  private String defaultTz = "-0500";

  /**
   * PID.7 <= (OBR.7 = OBX.14 = SPM.17.1) <= (SPM.17.2 = OBR.8) <= SPM.18 <= OBX.19 <= OBR.22 <=
   * MSH.7
   */

  /**
   * 
   * @param e the message
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) {
    java.util.List<LocationDateList> datesToCheck = checkMessageContext(e);
    java.util.List<String> result = check(datesToCheck);
    if (result.size() > 0) {
      logger.debug(result);
    }
    return result;
  }

  public java.util.List<String> check(java.util.List<LocationDateList> dates) {
    java.util.List<String> result = new ArrayList<String>();
    for (LocationDateList dateList : dates) {
      logger.debug("---");
      LocationDate e = dateList.get(dateList.size() - 1);
      if (e.getLocation().equals("MSH-7")) {
        int max = Math.max(e.getDate().indexOf("+"), e.getDate().indexOf("-"));
        if (max > -1) {
          defaultTz = e.getDate().substring(max);
        }
      }

      for (int i = 0; i < dateList.size() - 1; i++) {
        LocationDate a = dateList.get(i);
        LocationDate b = dateList.get(i + 1);

        String dtm1 = a.getDate();
        String dtm2 = b.getDate();

        logger.debug("Compare " + a.getLocation() + "[" + a.getDate() + "] to " + b.getLocation()
            + " [" + b.getDate() + "]");

        if (!DateUtil.isValid(dtm1) || !DateUtil.isValid(dtm2)) {
          continue;
        }

        dtm1 = DateUtil.truncateTo(dtm1, dtm2, true);
        dtm2 = DateUtil.truncateTo(dtm2, dtm1, true);

        logger.debug("Compare " + a.getLocation() + "[" + dtm1 + "] to " + b.getLocation() + " ["
            + dtm2 + "] (truncated)");

        ExtendedDate ext1 = DateUtil.toExtendedDate(dtm1, defaultTz);
        ExtendedDate ext2 = DateUtil.toExtendedDate(dtm2, defaultTz);

        if ((a.getLocation().equals("OBR-7") || a.getLocation().equals("OBX-14")
            || a.getLocation().equals("SPM-17.1"))
            && ((b.getLocation().equals("OBR-7") || b.getLocation().equals("OBX-14")
                || b.getLocation().equals("SPM-17.1")))) {
          // test for equal to
          if (!ext1.isEqual(ext2)) {
            result.add(String.format(
                "The date/time at location %s (%s) is different from the date/time at location %s (%s)",
                a.getLocation(), a.getDate(), b.getLocation(), b.getDate()));
          }
        } else if ((a.getLocation().equals("SPM-17.2") || a.getLocation().equals("OBR-8"))
            && ((b.getLocation().equals("SPM-17.2") || b.getLocation().equals("OBR-8")))) {
          // test for equal to
          if (!ext1.isEqual(ext2)) {
            result.add(String.format(
                "The date/time at location %s (%s) is different from the date/time at location %s (%s)",
                a.getLocation(), a.getDate(), b.getLocation(), b.getDate()));
          }
        } else {
          // test for lesser or equal to
          if (ext1.isAfter(ext2)) {
            result.add(String.format(
                "The date/time at location %s (%s) is later than the date/time at location %s (%s)",
                a.getLocation(), a.getDate(), b.getLocation(), b.getDate()));
          }
        }
      }
    }
    logger.debug(result);
    return result;
  }

  private java.util.List<LocationDateList> checkMessageContext(Element m) {

    java.util.List<LocationDateList> result = new ArrayList<LocationDateList>();

    // MSH-7
    List<Simple> MSH7List = Query.queryAsSimple(m, "1[1].7[1].1[1]").get();
    String MSH7 = MSH7List.size() > 0 ? MSH7List.apply(0).value().raw() : "";

    // PATIENT RESULT
    List<Element> prList = Query.query(m, "3[*]").get();
    Iterator<Element> it = prList.iterator();
    while (it.hasNext()) {
      Element pr = it.next();
      java.util.List<LocationDateList> toto = checkPatientResultContext(pr);
      for (LocationDateList list : toto) {
        if (DateUtil.isValid(MSH7)) {
          list.add("MSH-7", MSH7);

          // MSH-7 time zone is the default time zone
          // defaultTz = MSH7.
          int max = Math.max(MSH7.indexOf("+"), MSH7.indexOf("-"));
          if (max > -1) {
            defaultTz = MSH7.substring(max);
          }
        }
        result.add(list);
      }
    }
    return result;
  }

  private java.util.List<LocationDateList> checkPatientResultContext(Element pr) {

    java.util.List<LocationDateList> result = new ArrayList<LocationDateList>();

    // PID-7
    List<Simple> PID7List = Query.queryAsSimple(pr, "1[1].1[1].7[1].1[1]").get();
    String PID7 = PID7List.size() > 0 ? PID7List.apply(0).value().raw() : "";

    // ORDER OBSERVATION
    List<Element> ooList = Query.query(pr, "2[*]").get();
    Iterator<Element> it = ooList.iterator();
    while (it.hasNext()) {
      Element oo = it.next();
      java.util.List<LocationDateList> toto = checkOrderObservationContext(oo);
      for (LocationDateList list : toto) {
        list.add(0, "PID-7", PID7);
        result.add(list);
      }
    }
    return result;
  }

  private java.util.List<LocationDateList> checkOrderObservationContext(Element oo) {

    java.util.List<LocationDateList> result = new ArrayList<LocationDateList>();

    // OBR-7
    List<Simple> OBR7List = Query.queryAsSimple(oo, "2[1].7[1].1[1]").get();
    String OBR7 = OBR7List.size() > 0 ? OBR7List.apply(0).value().raw() : "";
    // OBR-8
    List<Simple> OBR8List = Query.queryAsSimple(oo, "2[1].8[1].1[1]").get();
    String OBR8 = OBR8List.size() > 0 ? OBR8List.apply(0).value().raw() : "";
    // OBR-22
    List<Simple> OBR22List = Query.queryAsSimple(oo, "2[1].22[1].1[1]").get();
    String ORB22 = OBR22List.size() > 0 ? OBR22List.apply(0).value().raw() : "";


    // SPM-17.1
    List<Simple> SPM17_1List = Query.queryAsSimple(oo, "9[1].1[1].17[1].1[1].1[1]").get();
    String SPM17_1 = SPM17_1List.size() > 0 ? SPM17_1List.apply(0).value().raw() : "";
    // SPM-17.2
    List<Simple> SPM17_2List = Query.queryAsSimple(oo, "9[1].1[1].17[1].2[1].1[1]").get();
    String SPM17_2 = SPM17_2List.size() > 0 ? SPM17_2List.apply(0).value().raw() : "";
    // SPM-18
    List<Simple> SPM18List = Query.queryAsSimple(oo, "9[1].1[1].18[1].1[1]").get();
    String SPM18 = SPM18List.size() > 0 ? SPM18List.apply(0).value().raw() : "";


    // OBX
    List<Element> OBXList = Query.query(oo, "6[*].1[1]").get();

    if (OBXList.size() > 0) {
      Iterator<Element> it = OBXList.iterator();
      while (it.hasNext()) {
        Element OBX = it.next();
        // OBX-14
        List<Simple> OBX14List = Query.queryAsSimple(OBX, "14[1].1[1]").get();
        String OBX14 = OBX14List.size() > 0 ? OBX14List.apply(0).value().raw() : "";
        // OBX-19
        List<Simple> OBX19List = Query.queryAsSimple(OBX, "19[1].1[1]").get();
        String OBX19 = OBX19List.size() > 0 ? OBX19List.apply(0).value().raw() : "";

        LocationDateList list = new LocationDateList();
        list.add("OBR-7", OBR7);
        list.add("OBX-14", OBX14);
        list.add("SPM-17.1", SPM17_1);
        list.add("SPM-17.2", SPM17_2);
        list.add("OBR-8", OBR8);
        list.add("SPM-18", SPM18);
        list.add("OBX-19", OBX19);
        list.add("OBR-22", ORB22);

        result.add(list);
      }
    } else {
      LocationDateList list = new LocationDateList();
      list.add("OBR-7", OBR7);
      list.add("SPM-17.1", SPM17_1);
      list.add("SPM-17.2", SPM17_2);
      list.add("OBR-8", OBR8);
      list.add("SPM-18", SPM18);
      list.add("OBR-22", ORB22);

      result.add(list);
    }

    return result;
  }

  public class LocationDateList extends ArrayList<LocationDate> {

    protected boolean add(String location, String date) {
      if (DateUtil.isValid(date)) {
        LocationDate locationDate = new LocationDate(location, date);
        return this.add(locationDate);
      }
      return false;
    }


    protected void add(int position, String location, String date) {
      LocationDate locationDate = new LocationDate(location, date);
      this.add(position, locationDate);
    }
  }

  private class LocationDate {

    private String location;
    private String date;

    protected LocationDate(String location, String date) {
      this.location = location;
      this.date = date;
    }

    public String getLocation() {
      return location;
    }

    public String getDate() {
      return date;
    }
  };
}
