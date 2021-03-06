package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.SPM_4;
import gov.nist.hit.elr.aphl.plugin.extra.context.VPD;
import hl7.v2.instance.Element;

public class VPD_SPM4_csv extends SPM_4_csv {

  @Override
  public String getFOLDER_SPM() {
    return VPD.getFOLDER_SPM();
  }

  @Override
  public String getSPECIMEN_TYPE_CSV() {
    return VPD.getSPECIMEN_TYPE_CSV();
  }

  @Override
  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

  @Override
  public String getProgram() {
    return "VPD";
  }
}
