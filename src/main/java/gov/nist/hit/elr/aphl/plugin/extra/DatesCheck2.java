package gov.nist.hit.elr.aphl.plugin.extra;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.healthcare.utils.date.DateUtilNew;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class DatesCheck2 {

  private static Logger logger = LogManager.getLogger();

  private String defaultTz = "";



  /**
   * 
   * If a group does not have an SPM segment, OBR/OBX date must be checked against the other groups
   * SPMs
   * 
   * (OBR.7 = OBX.14 = SPM.17.1) <= (SPM.17.2 = OBR.8) <= SPM.18 <= OBX.19 <= OBR.22
   */

  /**
   * 
   * @param e the message
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) {
    java.util.List<String> result = new java.util.ArrayList<String>();
    // we extract the default timezone first (from MSH-7). If not present, we don't check anything
    defaultTz = extractDefaultTZ(e);
    if ("".equals(defaultTz)) {
      return result;
    }
    // we extract the SPM values from all oo groups in the message. If no SPM, we don't check
    // anything
    java.util.List<Map<String, LocationDate>> spmValues = extractSPMvalues(e);
    if (spmValues.size() == 0) {
      return result;
    }

    // we extract the list of dates to check from the message
    java.util.List<LocationDateList> datesToCheck = checkMessageContext(e, spmValues);
    result.addAll(check(datesToCheck));
    if (result.size() > 0) {
      logger.debug(result);
    }

    return result;
  }


  private java.util.List<Map<String, LocationDate>> extractSPMvalues(Element m) {
    java.util.List<Map<String, LocationDate>> spmValues =
        new java.util.ArrayList<Map<String, LocationDate>>();
    List<Element> spmList = Query.query(m, "3[*].2[*].9[*].1[1]").get();
    Iterator<Element> it = spmList.iterator();
    while (it.hasNext()) {
      Map<String, LocationDate> values = new HashMap<String, LocationDate>();
      Element spm = it.next();
      int spmLine = spm.location().line();

      // SPM-17.1
      List<Simple> SPM17_1List = Query.queryAsSimple(spm, "17[1].1[1].1[1]").get();
      String SPM17_1 = SPM17_1List.size() > 0 ? SPM17_1List.apply(0).value().raw() : "";

      // SPM-17.2
      List<Simple> SPM17_2List = Query.queryAsSimple(spm, "17[1].2[1].1[1]").get();
      String SPM17_2 = SPM17_2List.size() > 0 ? SPM17_2List.apply(0).value().raw() : "";

      // SPM-18
      List<Simple> SPM18List = Query.queryAsSimple(spm, "18[1].1[1]").get();
      String SPM18 = SPM18List.size() > 0 ? SPM18List.apply(0).value().raw() : "";

      values.put("SPM-17.1", new LocationDate("SPM-17.1", SPM17_1, spmLine));
      values.put("SPM-17.2", new LocationDate("SPM-17.2", SPM17_2, spmLine));
      values.put("SPM-18", new LocationDate("SPM-18", SPM18, spmLine));

      spmValues.add(values);
    }
    return spmValues;
  }

  private java.util.List<LocationDateList> checkMessageContext(Element m,
      java.util.List<Map<String, LocationDate>> spmValues) {
    java.util.List<LocationDateList> result = new ArrayList<LocationDateList>();
    // get all the OrderObservation groups
    List<Element> ooList = Query.query(m, "3[*].2[*]").get();
    Iterator<Element> it = ooList.iterator();
    while (it.hasNext()) {
      // check if this OrderObservation has SPM
      Element oo = it.next();
      List<Element> spmList = Query.query(oo, "9[*]").get();

      if (spmList.size() == 0) {
        // no SPM : this group needs to be checked against the SPMs from other oo groups
        java.util.List<LocationDateList> toto = checkOrderObservationContext(oo, spmValues);
        for (LocationDateList list : toto) {
          result.add(list);
        }
      }
    }
    return result;
  }


  public String getDefaultTz() {
    return defaultTz;
  }


  public void setDefaultTz(String defaultTz) {
    this.defaultTz = defaultTz;
  }


  private String extractDefaultTZ(Element m) {
    // MSH-7
    List<Simple> MSH7List = Query.queryAsSimple(m, "1[1].7[1].1[1]").get();
    String MSH7 = MSH7List.size() > 0 ? MSH7List.apply(0).value().raw() : "";

    int max = Math.max(MSH7.indexOf("+"), MSH7.indexOf("-"));
    if (max > -1) {
      return MSH7.substring(max);
    }
    return "";
  }

  public java.util.List<String> check(java.util.List<LocationDateList> dates) {
    java.util.List<String> result = new ArrayList<String>();
    for (LocationDateList dateList : dates) {
      // logger.debug("---");
      for (int i = 0; i < dateList.size() - 1; i++) {
        LocationDate a = dateList.get(i);
        LocationDate b = dateList.get(i + 1);

        String dtm1 = a.getDate();
        String dtm2 = b.getDate();

        // logger.debug("Compare " + a.getLocation() + "[" + a.getDate() + "] to " + b.getLocation()
        // + " [" + b.getDate() + "]");

        if (!DateUtilNew.isValid(dtm1, defaultTz) || !DateUtilNew.isValid(dtm2, defaultTz)) {
          continue;
        }

        OffsetDateTime ext1 = DateUtilNew.parseDTM(dtm1, defaultTz);
        OffsetDateTime ext2 = DateUtilNew.parseDTM(dtm2, defaultTz);


        // logger.debug("Compare " + a.getLocation() + "[" + ext1 + "] to " + b.getLocation() + " ["
        // + ext2 + "]");

        if ((a.getLocation().equals("OBR-7") || a.getLocation().equals("OBX-14")
            || a.getLocation().equals("SPM-17.1"))
            && ((b.getLocation().equals("OBR-7") || b.getLocation().equals("OBX-14")
                || b.getLocation().equals("SPM-17.1")))) {
          // test for equal to
          if (!ext1.isEqual(ext2)) {
            result.add(String.format(
                "The date/time at location %s [line %s] (%s) is different from the date/time at location %s [line %s] (%s).%s%s",
                a.getLocation(), a.getLine(), a.getDate(), b.getLocation(), b.getLine(),
                b.getDate(), checkTZ(a.getLocation(), a.getDate()),
                checkTZ(b.getLocation(), b.getDate())));
          }
        } else if ((a.getLocation().equals("SPM-17.2") || a.getLocation().equals("OBR-8"))
            && ((b.getLocation().equals("SPM-17.2") || b.getLocation().equals("OBR-8")))) {
          // test for equal to
          if (!ext1.isEqual(ext2)) {
            result.add(String.format(
                "The date/time at location %s [line %s] (%s) is different from the date/time at location %s [line %s] (%s).%s%s",
                a.getLocation(), a.getLine(), a.getDate(), b.getLocation(), b.getLine(),
                b.getDate(), checkTZ(a.getLocation(), a.getDate()),
                checkTZ(b.getLocation(), b.getDate())));
          }
        } else {
          // test for lesser or equal to
          if (ext1.isAfter(ext2)) {
            result.add(String.format(
                "The date/time at location %s [line %s] (%s) is later than the date/time at location %s [line %s] (%s).%s%s",
                a.getLocation(), a.getLine(), a.getDate(), b.getLocation(), b.getLine(),
                b.getDate(), checkTZ(a.getLocation(), a.getDate()),
                checkTZ(b.getLocation(), b.getDate())));
          }
        }
      }
    }
    // logger.debug(result);
    return result;
  }

  private String checkTZ(String location, String date) {
    if (date.contains("-") || date.contains("+")) {
      return "";
    }
    String result = String.format(
        " The date/time at location %s (%s) did not contain a timezone offset and the default value of [%s] was used.",
        location, date, defaultTz);
    return result;
  }

  private java.util.List<LocationDateList> checkOrderObservationContext(Element oo,
      java.util.List<Map<String, LocationDate>> spmValues) {

    java.util.List<LocationDateList> result = new ArrayList<LocationDateList>();

    List<Element> OBRList = Query.query(oo, "2[1]").get();
    int obrLine = OBRList.size() > 0 ? OBRList.apply(0).location().line() : 0;

    // OBR-7
    List<Simple> OBR7List = Query.queryAsSimple(oo, "2[1].7[1].1[1]").get();
    String OBR7 = OBR7List.size() > 0 ? OBR7List.apply(0).value().raw() : "";
    // OBR-8
    List<Simple> OBR8List = Query.queryAsSimple(oo, "2[1].8[1].1[1]").get();
    String OBR8 = OBR8List.size() > 0 ? OBR8List.apply(0).value().raw() : "";
    // OBR-22
    List<Simple> OBR22List = Query.queryAsSimple(oo, "2[1].22[1].1[1]").get();
    String ORB22 = OBR22List.size() > 0 ? OBR22List.apply(0).value().raw() : "";


    // OBX
    List<Element> OBXList = Query.query(oo, "6[*].1[1]").get();

    if (OBXList.size() > 0) {
      Iterator<Element> it = OBXList.iterator();
      while (it.hasNext()) {
        Element OBX = it.next();
        int obxLine = OBX.location().line();

        // OBX-14
        List<Simple> OBX14List = Query.queryAsSimple(OBX, "14[1].1[1]").get();
        String OBX14 = OBX14List.size() > 0 ? OBX14List.apply(0).value().raw() : "";
        // OBX-19
        List<Simple> OBX19List = Query.queryAsSimple(OBX, "19[1].1[1]").get();
        String OBX19 = OBX19List.size() > 0 ? OBX19List.apply(0).value().raw() : "";

        for (Map<String, LocationDate> spm : spmValues) {

          LocationDateList list = new LocationDateList();

          LocationDate SPM17_1 = spm.get("SPM-17.1");

          LocationDate SPM17_2 = spm.get("SPM-17.2");

          LocationDate SPM18 = spm.get("SPM-18");

          list.add("OBR-7", OBR7, obrLine);
          list.add("OBX-14", OBX14, obxLine);
          list.add(SPM17_1);
          list.add(SPM17_2);
          list.add("OBR-8", OBR8, obrLine);
          list.add(SPM18);
          list.add("OBX-19", OBX19, obxLine);
          list.add("OBR-22", ORB22, obrLine);

          result.add(list);
        }
      }
    } else {
      for (Map<String, LocationDate> spm : spmValues) {

        LocationDateList list = new LocationDateList();

        LocationDate SPM17_1 = spm.get("SPM-17.1");
        LocationDate SPM17_2 = spm.get("SPM-17.2");
        LocationDate SPM18 = spm.get("SPM-18");

        list.add("OBR-7", OBR7, obrLine);
        list.add(SPM17_1);
        list.add(SPM17_2);
        list.add("OBR-8", OBR8, obrLine);
        list.add(SPM18);
        list.add("OBR-22", ORB22, obrLine);

        result.add(list);
      }
    }
    return result;
  }

  public class LocationDateList extends ArrayList<LocationDate> {

    protected boolean add(String location, String date, int line) {
      if (DateUtilNew.isValid(date, defaultTz)) {
        LocationDate locationDate = new LocationDate(location, date, line);
        return this.add(locationDate);
      }
      return false;
    }

  }



  private class LocationDate {

    private String location;
    private String date;
    private int line;

    protected LocationDate(String location, String date, int line) {
      this.location = location;
      this.date = date;
      this.line = line;
    }

    public String getLocation() {
      return location;
    }

    public String getDate() {
      return date;
    }

    public int getLine() {
      return line;
    }

  };
}
