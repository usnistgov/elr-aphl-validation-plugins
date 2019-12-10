package gov.nist.hit.elr.plugin.cs;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import scala.collection.immutable.List;

public class ELR_064 {

  /**
   * ELR-064: Specimen (Specimen Group) SHALL be present in at least one occurrence of one
   * Order_Observation Group.
   */

  /**
   * 
   * @param e PATIENT_RESULT
   * @return
   */
  public boolean assertion(Element e) {
    // Get the list of "Specimen Group" in the PATIENT RESULT group
    List<Element> specimenGroupList = Query.query(e, "2[*].9[*]").get();

    // Get the number of "Specimen Group" in the list
    int count = specimenGroupList.size();

    return check(count);
  }

  public boolean check(int specimenGroupCount) {
    // Check that the count is at least one.
    return specimenGroupCount > 0;
  }
}
