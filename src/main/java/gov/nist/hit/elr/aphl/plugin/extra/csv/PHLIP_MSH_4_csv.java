package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.MSH_4;
import gov.nist.hit.elr.aphl.plugin.extra.context.PHLIP;
import hl7.v2.instance.Element;

public class PHLIP_MSH_4_csv extends MSH_4_csv {

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
