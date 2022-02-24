package gov.nist.hit.elr.aphl.plugin.extra;

import gov.nist.hit.elr.plugin.utils.CodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ARLN_EPI {

  private final CodedElement epi = new CodedElement("68991-9", "LN");

  /**
   * The message SHALL contain at least one ORDER_OBSERVATION group where SPECIMEN.SPM is present
   * and OBR-4.1 is NOT valued '68991-9' (LOINC).
   */

  /**
   * 
   * @param e the ORU^R01 Message group
   * @return
   */
  public boolean assertion(Element e) throws Exception {

    // The list of ORDER_OBSERVATION
    List<Element> ORDER_OBSERVATIONList = Query.query(e, "3[*].2[*]").get();

    Iterator<Element> it = ORDER_OBSERVATIONList.iterator();
    while (it.hasNext()) {
      Element ORDER_OBSERVATION = it.next();

      // get OBR-4 value (first triplet)
      List<Simple> OBR4_1List = Query.queryAsSimple(ORDER_OBSERVATION, "2[1].4[1].1[1]").get();
      List<Simple> OBR4_3List = Query.queryAsSimple(ORDER_OBSERVATION, "2[1].4[1].3[1]").get();

      String OBR4_1 = OBR4_1List.size() > 0 ? OBR4_1List.apply(0).value().raw() : "";
      String OBR4_3 = OBR4_3List.size() > 0 ? OBR4_3List.apply(0).value().raw() : "";

      CodedElement obr4 = new CodedElement(OBR4_1, OBR4_3);

      // get SPM presence in the group
      List<Element> SPMList = Query.query(ORDER_OBSERVATION, "9[*].1[*]").get();
      boolean specimen = SPMList.size() > 0 ? true : false;

      if (check(obr4, specimen)) {
        return true;
      }
    }
    return false;
  }

  public boolean check(CodedElement obr4, boolean specimen) {
    if (!epi.equals(obr4) && specimen) {
      return true;
    }
    return false;
  }


}
