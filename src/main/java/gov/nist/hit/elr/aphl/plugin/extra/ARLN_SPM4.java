package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import hl7.v2.instance.Element;

public class ARLN_SPM4 extends SPM_4 {

  @Override
  public String getFOLDER_SPM() {
    return ARLN.getFOLDER_SPM();
  }

  @Override
  public String getSPECIMEN_TYPE_CSV() {
    return ARLN.getSPECIMEN_TYPE_CSV();
  }

  @Override
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

  @Override
  protected String getProgram() {
    return "ARLN";
  }
}
