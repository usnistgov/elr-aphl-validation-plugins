package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import hl7.v2.instance.Element;

public class PHLIP_SPM4 extends SPM_4 {

  @Override
  public String getFOLDER_SPM() {
    return PHLIP.getFOLDER_SPM();
  }

  @Override
  public String getSPECIMEN_TYPE_CSV() {
    return PHLIP.getSPECIMEN_TYPE_CSV();
  }

  @Override
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

  @Override
  protected String getProgram() {
    return "PHLIP";
  }
}
