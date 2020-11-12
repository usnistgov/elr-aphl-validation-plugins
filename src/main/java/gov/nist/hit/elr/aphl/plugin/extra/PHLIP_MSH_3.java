package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.context.PHLIP;
import hl7.v2.instance.Element;

public class PHLIP_MSH_3 extends MSH_3 {

  @Override
  public String getFOLDER_MSH() {
    return PHLIP.getFOLDER_MSH();
  }

  @Override
  public String getMSH3_CSV() {
    return PHLIP.getMSH3_CSV();
  }

  @Override
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }
}
