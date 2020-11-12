package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;
import java.util.ArrayList;

import gov.nist.hit.elr.aphl.plugin.extra.SPM_4;
import gov.nist.hit.elr.aphl.plugin.extra.context.ELR_FOUNDATION;
import gov.nist.hit.elr.plugin.utils.CSVUtils;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public class SPM_4_csv implements SPM_4 {

  public String getFOLDER_SPM() {
    return ELR_FOUNDATION.getFOLDER_SPM();
  }

  public String getSPECIMEN_TYPE_CSV() {
    return ELR_FOUNDATION.getSPECIMEN_TYPE_CSV();
  }

  /**
   * 
   * @param SPM segment
   * @return
   * @throws IOException
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    java.util.List<String> messages = new ArrayList<String>();

    List<Element> SPM4List = Query.query(e, "4[1]").get();
    if (SPM4List == null || SPM4List.size() == 0) {
      // no SPM-4, we can move on, no check performed
      return messages;
    }
    Element SPM4 = SPM4List.apply(0);

    List<Simple> SPM4_1List = Query.queryAsSimple(SPM4, "1[1]").get();
    List<Simple> SPM4_3List = Query.queryAsSimple(SPM4, "3[1]").get();

    List<Simple> SPM4_4List = Query.queryAsSimple(SPM4, "4[1]").get();
    List<Simple> SPM4_6List = Query.queryAsSimple(SPM4, "6[1]").get();

    String SPM4_1 = SPM4_1List.size() > 0 ? SPM4_1List.apply(0).value().raw() : "";
    String SPM4_3 = SPM4_3List.size() > 0 ? SPM4_3List.apply(0).value().raw() : "";

    String SPM4_4 = SPM4_4List.size() > 0 ? SPM4_4List.apply(0).value().raw() : "";
    String SPM4_6 = SPM4_6List.size() > 0 ? SPM4_6List.apply(0).value().raw() : "";

    CodedElement identifierSPM4 = new CodedElement(SPM4_1, SPM4_3);
    CodedElement alternateSPM4 = new CodedElement(SPM4_4, SPM4_6);

    ComplexCodedElement _SPM4 = new ComplexCodedElement(identifierSPM4, alternateSPM4);
    return check(_SPM4);
  }

  public java.util.List<String> check(ComplexCodedElement SPM4) throws IOException {

    CSVUtils util = new CSVUtils();
    util.parseSpecimenTypeCSV(getFOLDER_SPM(), getSPECIMEN_TYPE_CSV());

    java.util.List<String> messages = new ArrayList<String>();

    CodedElement identifierSPM4 = SPM4.getIdentifier();
    CodedElement alternateSPM4 = SPM4.getAlternateIdentifier();
    // Check if SPM-4 (identifier or alternate) is in the value set
    if (!util.getSPM4().contains(identifierSPM4) && !util.getSPM4().contains(alternateSPM4)) {
      String st = "SPM-4 (Specimen Type) does not contain a valid code for the " + getProgram()
          + " program.";
      messages.add(st);
      // if (alternateSPM4.isEmpty() && !identifierSPM4.isEmpty()) {
      // messages.add("The value '" + identifierSPM4.prettyPrint()
      // + "' at location SPM-4 (Specimen Type) is not a member of the value set.");
      // }
      // if (identifierSPM4.isEmpty() && !alternateSPM4.isEmpty()) {
      // messages.add("The value '" + alternateSPM4.prettyPrint()
      // + "' at location SPM-4 (Specimen Type) is not a member of the value set.");
      // }
      // if (!identifierSPM4.isEmpty() && !alternateSPM4.isEmpty()) {
      // messages.add("Neither of the values '" + identifierSPM4.prettyPrint() + "' or '"
      // + alternateSPM4.prettyPrint()
      // + "' at location SPM-4 (Specimen Type) is a member of the value set.");
      // }

    }
    return messages;
  }

  public String getProgram() {
    return "ELR-Foundation";
  }
}
