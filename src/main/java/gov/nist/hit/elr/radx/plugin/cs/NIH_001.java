package gov.nist.hit.elr.radx.plugin.cs;


import hl7.v2.instance.Element;

public class NIH_001 extends NIH_XXX {

  // NIH_001: Where OBX-29 is not valued, OBX-17 (Observation Method) SHALL contain the same value
  // as all other occurrences of OBX-17 (Observation Method) where OBX-29 is not valued in the
  // message

  // OBX-29 is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
  // OBX-29 path from the message root is 3[*].2[*].6[*].1[*].29[*]

  // OBX-17 is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
  // OBX-17 path from the message root is 3[*].2[*].6[*].1[*].17[*]


  /**
   * @param context the root of the message
   * @return true if the assertion passes, false otherwise
   */
  public boolean assertion(Element context) {
    setElement1("29[1]");
    setElement2("17[*]");
    return super.assertion(context);
  }



}
