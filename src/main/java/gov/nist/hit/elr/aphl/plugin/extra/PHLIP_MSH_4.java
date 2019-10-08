package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import hl7.v2.instance.Element;

public class PHLIP_MSH_4 extends MSH_4 {

  @Override
  public String getFOLDER_MSH() {
    return PHLIP.getFOLDER_MSH();
  }

  @Override
  public String getMSH4_CSV() {
    return PHLIP.getMSH4_CSV();
  }

  @Override
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }
}
