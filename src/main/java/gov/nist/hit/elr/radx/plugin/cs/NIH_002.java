package gov.nist.hit.elr.radx.plugin.cs;


import hl7.v2.instance.Element;

public class NIH_002 extends NIH_XXX {

  // NIH_002: Where OBX-29 is not valued, OBX-19 (Date/Time of the Analysis) SHALL contain the same
  // value as all other occurrences of OBX-19 (Date/Time of the Analysis) where OBX-29 is not valued
  // in the message

  // OBX-29 is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
  // OBX-29 path from the message root is 3[*].2[*].6[*].1[*].29[*]

  // OBX-19 is located under PATIENT_RESULT.ORDER_OBSERVATION.OSBERVATION.OBX
  // OBX-19 path from the message root is 3[*].2[*].6[*].1[*].19[*]


  /**
   * @param context the root of the message
   * @return true if the assertion passes, false otherwise
   */
  public boolean assertion(Element context) {
    setElement1("29[1]");
    setElement2("19[*]");
    return super.assertion(context);
  }
}
