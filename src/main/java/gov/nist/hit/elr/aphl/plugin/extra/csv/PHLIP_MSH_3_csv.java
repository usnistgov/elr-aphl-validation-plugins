package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.MSH_3;
import gov.nist.hit.elr.aphl.plugin.extra.PHLIP;
import hl7.v2.instance.Element;

public class PHLIP_MSH_3_csv extends MSH_3_csv {

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
