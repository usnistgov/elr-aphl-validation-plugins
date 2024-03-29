package gov.nist.hit.elr.aphl.plugin.extra.ws;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.hit.elr.aphl.domain.Observation;
import gov.nist.hit.elr.aphl.domain.Order;
import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.aphl.domain.Test;
import gov.nist.hit.elr.aphl.plugin.extra.OBX3_OBR4_Warning;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.WSUtils;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public abstract class OBX3_OBR4_Warning_ws implements OBX3_OBR4_Warning {

  private static Logger logger = LogManager.getLogger();

  public abstract Program getProgram();

  public abstract Program getValueSetProgram();

  /**
   * 
   * @param e the ORDER_OBSERVATION group
   * @return
   * @throws IOException
   * @throws InterruptedException
   * @throws ClassNotFoundException
   * @throws URISyntaxException
   */
  public java.util.List<String> assertionWithCustomMessages(Element e)
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // logger.debug("assertionWithCustomMessages");

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
      java.util.List<ComplexCodedElement> OBX3s)
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {

    java.util.List<String> messages = new ArrayList<String>();

    CodedElement identifierOBR4 = OBR4.getIdentifier();
    CodedElement alternateOBR4 = OBR4.getAlternateIdentifier();

    WSUtils ws = new WSUtils();
    java.util.List<Test> tests = ws.getTests(getProgram());
    java.util.List<Order> orders = ws.getOrders(getProgram());
    java.util.List<Observation> observations = ws.getObservations(getProgram());

    for (ComplexCodedElement OBX3 : OBX3s) {
      CodedElement identifierOBX3 = OBX3.getIdentifier();
      CodedElement alternateOBX3 = OBX3.getAlternateIdentifier();

      // 1. Check if OBX-3 is in 'Test'
      long identifierOBX3Count =
          Test.countByOBX3(identifierOBX3.getIdentifier(), identifierOBX3.getCodeSystem(), tests);
      long alternateOBX3Count =
          Test.countByOBX3(alternateOBX3.getIdentifier(), alternateOBX3.getCodeSystem(), tests);

      boolean obx3InTests = identifierOBX3Count > 0 || alternateOBX3Count > 0;

      if (obx3InTests) {
        CodedElement obx3 = identifierOBX3Count > 0 ? identifierOBX3 : alternateOBX3;
        Set<Test> obr4Expected = Test.findByOBX3(obx3.getIdentifier(), obx3.getCodeSystem(), tests);

        Set<Test> subsetId = Test.findByOBR4(identifierOBR4.getIdentifier(),
            identifierOBR4.getCodeSystem(), obr4Expected);
        Set<Test> subsetAlt = Test.findByOBR4(alternateOBR4.getIdentifier(),
            alternateOBR4.getCodeSystem(), obr4Expected);

        // 1.1 Check if the parent OBR-4 matches
        if (subsetId.size() > 0 || subsetAlt.size() > 0) {
          // success : move to the next OBX-3
          continue;
        }

        // 1.2 Check if the OBR-4 value is associated with another OBX-3 in test
        long identifierOBR4Count =
            Test.countByOBR4(identifierOBR4.getIdentifier(), identifierOBR4.getCodeSystem(), tests);
        if (identifierOBR4Count > 0) {
          messages.add("[WARNING] The OBR-4 value (" + identifierOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        long alternateOBR4Count =
            Test.countByOBR4(alternateOBR4.getIdentifier(), alternateOBR4.getCodeSystem(), tests);
        if (alternateOBR4Count > 0) {
          messages.add("[WARNING] The OBR-4 value (" + alternateOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        // 1.3 Check if OBR-4 is present in "Orders"
        identifierOBR4Count =
            Order.count(identifierOBR4.getIdentifier(), identifierOBR4.getCodeSystem(), orders);
        if (identifierOBR4Count > 0) {
          // TODO should be a warning
          messages.add("[WARNING] The OBR-4 value (" + identifierOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        alternateOBR4Count =
            Order.count(alternateOBR4.getIdentifier(), alternateOBR4.getCodeSystem(), orders);
        if (alternateOBR4Count > 0) {
          // TODO should be a warning
          messages.add("[WARNING] The OBR-4 value (" + alternateOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        // 1.4 OBR-4 is not present in either "Tests", nor in "Orders"
        messages.add("[WARNING] The OBR-4 value (" + OBR4.prettyPrint()
            + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
      }

      if (!obx3InTests) {
        // 2.1 Check if OBX-3 is present in "Observations"
        identifierOBX3Count = Observation.count(identifierOBX3.getIdentifier(),
            identifierOBX3.getCodeSystem(), observations);
        alternateOBX3Count = Observation.count(alternateOBX3.getIdentifier(),
            alternateOBX3.getCodeSystem(), observations);

        if (identifierOBX3Count == 0 && alternateOBX3Count == 0) {
          // this is an error - but we won't catch it here : move to the next OBX-3
          continue;
        }

        CodedElement obx3 = identifierOBX3Count > 0 ? identifierOBX3 : alternateOBX3;

        // 2.2 Check if OBR-4 is present in "Tests"
        long identifierOBR4Count =
            Test.countByOBR4(identifierOBR4.getIdentifier(), identifierOBR4.getCodeSystem(), tests);
        if (identifierOBR4Count > 0) {
          messages.add("[WARNING] The OBR-4 value (" + identifierOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        long alternateOBR4Count =
            Test.countByOBR4(alternateOBR4.getIdentifier(), alternateOBR4.getCodeSystem(), tests);
        if (alternateOBR4Count > 0) {
          messages.add("[WARNING] The OBR-4 value (" + alternateOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }


        // 2.3 Check if OBR-4 is in "Orders"
        identifierOBR4Count =
            Order.count(identifierOBR4.getIdentifier(), identifierOBR4.getCodeSystem(), orders);
        if (identifierOBR4Count > 0) {
          messages.add("[WARNING] The OBR-4 value (" + identifierOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        alternateOBR4Count =
            Order.count(alternateOBR4.getIdentifier(), alternateOBR4.getCodeSystem(), orders);
        if (alternateOBR4Count > 0) {
          messages.add("[WARNING] The OBR-4 value (" + alternateOBR4.prettyPrint()
              + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
          continue;
        }

        // 2.4 OBR-4 is not present in either "Tests", nor in "Orders"
        messages.add("[WARNING] The OBR-4 value (" + OBR4.prettyPrint()
            + ") should not be associated with this OBX-3 value (" + obx3.prettyPrint() + ")");
      }
    }
    // logger.debug(messages);
    return messages;
  }
}
