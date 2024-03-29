package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.hit.elr.aphl.plugin.extra.OBX3_OBR4_Warning;
import gov.nist.hit.elr.plugin.utils.CSVUtils;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public abstract class OBX3_OBR4_Warning_csv implements OBX3_OBR4_Warning {

  private static Logger logger = LogManager.getLogger();

  public abstract String getFOLDER();

  public abstract String getTEST_CSV();

  public abstract String getOBSERVATIONS_CSV();

  public abstract String getORDERS();

  public abstract String getVALUE_SETS_CSV();

  /**
   * 
   * @param e the ORDER_OBSERVATION group
   * @return
   * @throws IOException
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {

    java.util.List<String> messages = new ArrayList<String>();

    List<Element> OBR4List = Query.query(e, "2[1].4[1]").get();

    if (OBR4List == null || OBR4List.size() == 0) {
      // no OBR-4, we can move on, no check performed
      return messages;
    }

    List<Element> OBX3List = Query.query(e, "6[*].1[1].3[1]").get();
    if (OBX3List == null || OBX3List.size() == 0) {
      // no OBX-3 under the OBR-4 - this is outside the scope of this conformance
      // statement
      return messages;
    }

    Element OBR4 = OBR4List.apply(0);

    List<Simple> OBR4_1List = Query.queryAsSimple(OBR4, "1[1]").get();
    List<Simple> OBR4_3List = Query.queryAsSimple(OBR4, "3[1]").get();

    List<Simple> OBR4_4List = Query.queryAsSimple(OBR4, "4[1]").get();
    List<Simple> OBR4_6List = Query.queryAsSimple(OBR4, "6[1]").get();

    String OBR4_1 = OBR4_1List.size() > 0 ? OBR4_1List.apply(0).value().raw() : "";
    String OBR4_3 = OBR4_3List.size() > 0 ? OBR4_3List.apply(0).value().raw() : "";

    String OBR4_4 = OBR4_4List.size() > 0 ? OBR4_4List.apply(0).value().raw() : "";
    String OBR4_6 = OBR4_6List.size() > 0 ? OBR4_6List.apply(0).value().raw() : "";

    CodedElement identifierOBR4 = new CodedElement(OBR4_1, OBR4_3);
    CodedElement alternateOBR4 = new CodedElement(OBR4_4, OBR4_6);

    ComplexCodedElement _OBR4 = new ComplexCodedElement(identifierOBR4, alternateOBR4);
    java.util.List<ComplexCodedElement> _OBX3s = new ArrayList<ComplexCodedElement>();

    Iterator<Element> it = OBX3List.iterator();
    while (it.hasNext()) {
      Element OBX3 = it.next();
      List<Simple> OBX3_1List = Query.queryAsSimple(OBX3, "1[1]").get();
      List<Simple> OBX3_3List = Query.queryAsSimple(OBX3, "3[1]").get();

      List<Simple> OBX3_4List = Query.queryAsSimple(OBX3, "4[1]").get();
      List<Simple> OBX3_6List = Query.queryAsSimple(OBX3, "6[1]").get();

      String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
      String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";

      String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
      String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

      CodedElement identifierOBX3 = new CodedElement(OBX3_1, OBX3_3);
      CodedElement alternateOBX3 = new CodedElement(OBX3_4, OBX3_6);

      ComplexCodedElement _OBX3 = new ComplexCodedElement(identifierOBX3, alternateOBX3);
      _OBX3s.add(_OBX3);

    }
    return check(_OBR4, _OBX3s);
  }

  public java.util.List<String> check(ComplexCodedElement OBR4,
      java.util.List<ComplexCodedElement> OBX3s) throws IOException {
    CSVUtils util = new CSVUtils();
    util.parse(getFOLDER(), getTEST_CSV(), getOBSERVATIONS_CSV(), getORDERS(), getVALUE_SETS_CSV());

    java.util.List<String> messages = new ArrayList<String>();

    CodedElement identifierOBR4 = OBR4.getIdentifier();
    CodedElement alternateOBR4 = OBR4.getAlternateIdentifier();

    for (ComplexCodedElement OBX3 : OBX3s) {
      CodedElement identifierOBX3 = OBX3.getIdentifier();
      CodedElement alternateOBX3 = OBX3.getAlternateIdentifier();

      // 1. Check if OBX-3 is in 'Test'
      boolean obx3InTests = util.getOBX3_OBR4().containsKey(identifierOBX3)
          || util.getOBX3_OBR4().containsKey(alternateOBX3);

      if (obx3InTests) {
        CodedElement obx3 =
            util.getOBX3_OBR4().containsKey(identifierOBX3) ? identifierOBX3 : alternateOBX3;
        CodedElement obx4 =
            util.getOBR4_OBX3().containsKey(identifierOBR4) ? identifierOBR4 : alternateOBR4;

        Set<CodedElement> obr4Expected = util.getOBX3_OBR4().get(obx3);
        // 1.1 Check if the parent OBR-4 matches
        if (obr4Expected.contains(obx4)) {
          // success : move to the next OBX-3
          // logger.debug("SUCCESS 1");
          continue;
        }
        // 1.2 Check if the OBR-4 value is associated with another OBX-3 in test
        if (util.getOBR4_OBX3().containsKey(obx4)) {
          messages.add("[WARNING] The OBR-4 value (" + obx4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          // logger.debug("WARNING 1");
          continue;
        }
        // 1.3 Check if OBR-4 is present in "Orders"
        if (util.getOBR4().contains(obx4)) {
          // TODO should be a warning
          messages.add("[WARNING] The OBR-4 value (" + obx4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          // logger.debug("WARNING 2");
          continue;
        }
        // 1.4 OBR-4 is not present in either "Tests", nor in "Orders"
        messages.add("[WARNING] The OBR-4 value (" + OBR4.prettyPrint()
            + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
        // logger.debug("WARNING 3");
      }

      if (!obx3InTests) {
        // 2.1 Check if OBX-3 is present in "Observations"
        if (!util.getOBX3().contains(identifierOBX3) && !util.getOBX3().contains(alternateOBX3)) {
          // this is an error - but we won't catch it here : move to the next OBX-3
          continue;
        }

        CodedElement obx3 =
            util.getOBX3().contains(identifierOBX3) ? identifierOBX3 : alternateOBX3;
        CodedElement obx4 =
            util.getOBR4_OBX3().containsKey(identifierOBR4) ? identifierOBR4 : alternateOBR4;

        // 2.2 Check if OBR-4 is present in "Tests"
        if (util.getOBR4_OBX3().containsKey(obx4)) {
          messages.add("[WARNING] The OBR-4 value (" + obx4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          // logger.debug("WARNING 4");
          continue;
        }
        // 2.3 Check if OBR-4 is in "Orders"
        if (util.getOBR4().contains(obx4)) {
          messages.add("[WARNING] The OBR-4 value (" + obx4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          // logger.debug("WARNING 5");
          continue;
        }
        // 2.4 OBR-4 is not present in either "Tests", nor in "Orders"
        messages.add("[WARNING] The OBR-4 value (" + OBR4.prettyPrint()
            + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
        // logger.debug("WARNING 6");
      }
    }
    // logger.debug(messages);
    return messages;
  }
}
