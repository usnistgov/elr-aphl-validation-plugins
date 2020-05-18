package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.aphl.domain.Test;
import gov.nist.hit.elr.aphl.domain.vocab.ExpandedValueSet;
import gov.nist.hit.elr.aphl.domain.vocab.ValueSets;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.WSUtils;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public abstract class OBX_ws {

  private static Logger logger = Logger.getLogger(OBX_ws.class.getName());

  public abstract Program getProgram();

  public abstract Program getValueSetProgram();

  private static java.util.List<String> CODED_DATATYPES = new ArrayList<String>() {
    {
      add("CE");
      add("CWE");
      add("ID");
      add("IS");
    }
  };

  public java.util.List<String> assertionWithCustomMessages(Element e)
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    // logger.debug("assertionWithCustomMessages");

    java.util.List<String> messages = new ArrayList<String>();

    // parse OBX-3
    List<Element> OBX3List = Query.query(e, "3[1]").get();
    if (OBX3List == null || OBX3List.size() == 0) {
      // no OBX-3, we can move on, no check performed
      return messages;
    }
    Element OBX3 = OBX3List.apply(0);

    List<Simple> OBX3_1List = Query.queryAsSimple(OBX3, "1[1]").get();
    List<Simple> OBX3_3List = Query.queryAsSimple(OBX3, "3[1]").get();
    List<Simple> OBX3_4List = Query.queryAsSimple(OBX3, "4[1]").get();
    List<Simple> OBX3_6List = Query.queryAsSimple(OBX3, "6[1]").get();

    String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
    String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";
    String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
    String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

    ComplexCodedElement _OBX3 = new ComplexCodedElement(OBX3_1, OBX3_3, OBX3_4, OBX3_6);

    // parse OBX-2
    List<Simple> OBX2List = Query.queryAsSimple(e, "2[1]").get();
    if (OBX2List == null || OBX2List.size() == 0) {
      // no OBX-2, we can move on, no check performed
      return messages;
    }
    String _OBX2 = OBX2List.size() > 0 ? OBX2List.apply(0).value().raw() : "";

    // OBX-3 to OBX-2
    messages = checkOBX3_OBX2(_OBX3, _OBX2);
    // If OBX-2 is invalid - we do not check OBX-5
    if (messages.size() > 0) {
      return messages;
    }

    // parse OBX-5 - we only check for CE and CWE
    // TODO : do we want to do it for ID and IS as well ?
    if ("CWE".equals(_OBX2) || "CE".equals(_OBX2)) {
      ComplexCodedElement _OBX5;
      List<Element> OBX5List = Query.query(e, "5[1]").get();
      if (OBX5List == null || OBX5List.size() == 0) {
        _OBX5 = new ComplexCodedElement("", "", "", "");
      } else {
        Element OBX5 = OBX5List.apply(0);

        List<Simple> OBX5_1List = Query.queryAsSimple(OBX5, "1[1]").get();
        List<Simple> OBX5_3List = Query.queryAsSimple(OBX5, "3[1]").get();
        List<Simple> OBX5_4List = Query.queryAsSimple(OBX5, "4[1]").get();
        List<Simple> OBX5_6List = Query.queryAsSimple(OBX5, "6[1]").get();

        String OBX5_1 = OBX5_1List.size() > 0 ? OBX5_1List.apply(0).value().raw() : "";
        String OBX5_3 = OBX5_3List.size() > 0 ? OBX5_3List.apply(0).value().raw() : "";
        String OBX5_4 = OBX5_4List.size() > 0 ? OBX5_4List.apply(0).value().raw() : "";
        String OBX5_6 = OBX5_6List.size() > 0 ? OBX5_6List.apply(0).value().raw() : "";

        _OBX5 = new ComplexCodedElement(OBX5_1, OBX5_3, OBX5_4, OBX5_6);
      }
      messages = checkOBX3_OBX5_CWE(_OBX3, _OBX5);
    }
    return messages;
  }


  public java.util.List<String> checkOBX3_OBX2(ComplexCodedElement obx3, String obx2)
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {

    WSUtils ws = new WSUtils();

    java.util.List<String> messages = new ArrayList<String>();

    CodedElement identifier = obx3.getIdentifier();
    CodedElement alternate = obx3.getAlternateIdentifier();

    java.util.List<Test> tests = ws.getTests(getProgram());

    Set<Test> expectedTests =
        Test.findByOBX3(identifier.getIdentifier(), identifier.getCodeSystem(), tests);
    if (expectedTests.size() > 0) {
      Set<String> expectedValues = expectedTests.stream()
          .map(expectedTest -> expectedTest.getObx2()).collect(Collectors.toSet());
      if (expectedValues.contains("varies")) {
        // no check can be performed
        return messages;
      }
      if (!expectedValues.contains(obx2)) {
        messages.add("The OBX-2 value (" + obx2 + ") is not valid for this OBX-3 value ("
            + identifier.prettyPrint() + "). The expected OBX-2 value is one of "
            + expectedValues.toString() + "");
      }
    }

    expectedTests = Test.findByOBX3(alternate.getIdentifier(), alternate.getCodeSystem(), tests);
    if (expectedTests.size() > 0) {
      Set<String> expectedValues = expectedTests.stream()
          .map(expectedTest -> expectedTest.getObx2()).collect(Collectors.toSet());
      if (expectedValues.contains("varies")) {
        // no check can be performed
        return messages;
      }
      if (!expectedValues.contains(obx2)) {
        messages.add("The OBX-2 value (" + obx2 + ") is not valid for this OBX-3 value ("
            + alternate.prettyPrint() + "). The expected OBX-2 value is one of "
            + expectedValues.toString() + "");
      }
    }
    return messages;
  }

  public java.util.List<String> checkOBX3_OBX5_CWE(ComplexCodedElement obx3,
      ComplexCodedElement obx5)
      throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
    WSUtils ws = new WSUtils();

    java.util.List<String> messages = new ArrayList<String>();

    CodedElement identifier = obx3.getIdentifier();
    CodedElement alternate = obx3.getAlternateIdentifier();

    java.util.List<Test> tests = ws.getTests(getProgram());
    ValueSets valueSets = ws.getValueSets(getValueSetProgram());

    // get the expected Tests for this OBX-3 value
    Set<Test> expectedTests =
        Test.findByOBX3(identifier.getIdentifier(), identifier.getCodeSystem(), tests);

    // TODO : do we want to add an extra layer to check for OBX-2 value ? CAn the value set be
    // different based on the OBX-2 value ?

    // The OBX-3 code has one (or more) associated value set(s)
    if (expectedTests.size() > 0) {
      Set<String> expectedValueSets = expectedTests.stream()
          .filter(expectedTest -> CODED_DATATYPES.contains(expectedTest.getObx2()))
          .map(expectedTest -> expectedTest.getValueset()).collect(Collectors.toSet());

      // check that the value set(s) exists in the "All value sets" tab
      Set<String> normalizedValueSets = valueSets.normalizeValueSetNames(expectedValueSets);
      if (normalizedValueSets.size() == 0) {
        messages.add(
            "Invalid value set identifier(s) " + expectedValueSets.toString() + " for OBX-3 value "
                + identifier.prettyPrint() + ". The tool cannot validate the OBX-5 value.");
      }

      // check that one of the value sets contains the OBX-5 identifier (or the OBX-5 alternate)
      for (String normalizedValueSet : normalizedValueSets) {
        java.util.List<ExpandedValueSet> valueSetList =
            ws.getExpandedValueSet(getValueSetProgram(), normalizedValueSet);
        for (ExpandedValueSet valueSet : valueSetList) {
          // the OBX-5 identifier code + codesystem is in the value set
          if (valueSet.containsCode(obx5.getIdentifier().getIdentifier(),
              obx5.getIdentifier().getCodeSystem())) {
            // PASS
            return messages;
          }
          // the OBX-5 alternate identifier code + codesystem is in the value set
          if (valueSet.containsCode(obx5.getAlternateIdentifier().getIdentifier(),
              obx5.getAlternateIdentifier().getCodeSystem())) {
            // PASS
            return messages;
          }
        }
      }

      // we could not find OBX-5 in the value set(s)
      if (!obx5.getIdentifier().isEmpty() && !obx5.getAlternateIdentifier().isEmpty()) {
        messages
            .add("The OBX-5 value (" + obx5.prettyPrint() + ") is not valid for this OBX-3 value ("
                + identifier.prettyPrint() + "). OBX-5 is expected to be present in the '"
                + normalizedValueSets.toString() + "' value set");
      } else if (!obx5.getIdentifier().isEmpty()) {
        messages.add("The OBX-5 value (" + obx5.getIdentifier().prettyPrint()
            + ") is not valid for this OBX-3 value (" + identifier.prettyPrint()
            + "). OBX-5 is expected to be present in the '" + normalizedValueSets.toString()
            + "' value set");
      } else if (!obx5.getAlternateIdentifier().isEmpty()) {
        messages.add("The OBX-5 value (" + obx5.getAlternateIdentifier().prettyPrint()
            + ") is not valid for this OBX-3 value (" + identifier.prettyPrint()
            + "). OBX-5 is expected to be present in the '" + normalizedValueSets.toString()
            + "' value set");
      } else {
        messages.add("The OBX-5 value is not valid for this OBX-3 value ("
            + identifier.prettyPrint() + "). OBX-5 is expected to be present in the '"
            + normalizedValueSets.toString() + "' value set");
      }
    }

    // TODO : do the same of the OBX-3 alternate
    expectedTests = Test.findByOBX3(alternate.getIdentifier(), alternate.getCodeSystem(), tests);

    if (expectedTests.size() > 0) {
      Set<String> expectedValueSets = expectedTests.stream()
          .filter(expectedTest -> CODED_DATATYPES.contains(expectedTest.getObx2()))
          .map(expectedTest -> expectedTest.getValueset()).collect(Collectors.toSet());

      // check that the value set(s) exists in the "All value sets" tab
      Set<String> normalizedValueSets = valueSets.normalizeValueSetNames(expectedValueSets);
      if (normalizedValueSets.size() == 0) {
        messages.add(
            "Invalid value set identifier(s) " + expectedValueSets.toString() + " for OBX-3 value "
                + alternate.prettyPrint() + ". The tool cannot validate the OBX-5 value.");
      }

      // check that one of the value sets contains the OBX-5 identifier (or the OBX-5 alternate)
      for (String normalizedValueSet : normalizedValueSets) {
        java.util.List<ExpandedValueSet> valueSetList =
            ws.getExpandedValueSet(getValueSetProgram(), normalizedValueSet);
        for (ExpandedValueSet valueSet : valueSetList) {
          // the OBX-5 identifier code + codesystem is in the value set
          if (valueSet.containsCode(obx5.getIdentifier().getIdentifier(),
              obx5.getIdentifier().getCodeSystem())) {
            // PASS
            return messages;
          }
          // the OBX-5 alternate identifier code + codesystem is in the value set
          if (valueSet.containsCode(obx5.getAlternateIdentifier().getIdentifier(),
              obx5.getAlternateIdentifier().getCodeSystem())) {
            // PASS
            return messages;
          }
        }
      }

      if (!obx5.getIdentifier().isEmpty() && !obx5.getAlternateIdentifier().isEmpty()) {
        messages
            .add("The OBX-5 value (" + obx5.prettyPrint() + ") is not valid for this OBX-3 value ("
                + alternate.prettyPrint() + "). OBX-5 is expected to be present in the '"
                + normalizedValueSets.toString() + "' value set");
      } else if (!obx5.getIdentifier().isEmpty()) {
        messages.add("The OBX-5 value (" + obx5.getIdentifier().prettyPrint()
            + ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
            + "). OBX-5 is expected to be present in the '" + normalizedValueSets.toString()
            + "' value set");
      } else if (!obx5.getAlternateIdentifier().isEmpty()) {
        messages.add("The OBX-5 value (" + obx5.getAlternateIdentifier().prettyPrint()
            + ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
            + "). OBX-5 is expected to be present in the '" + normalizedValueSets.toString()
            + "' value set");
      } else {
        messages.add("The OBX-5 value is not valid for this OBX-3 value (" + alternate.prettyPrint()
            + "). OBX-5 is expected to be present in the '" + normalizedValueSets.toString()
            + "' value set");
      }
    }
    return messages;
  }
}
