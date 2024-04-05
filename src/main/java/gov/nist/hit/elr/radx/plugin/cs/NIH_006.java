package gov.nist.hit.elr.radx.plugin.cs;


import hl7.v2.instance.Element;

public class NIH_006 extends NIH_YYY {

  /**
   * @param context the root of the message
   * @return true if the assertion passes, false otherwise
   */
  public boolean assertion(Element context) {
    setElement("24[*]");
    return super.assertion(context);
  }
}
