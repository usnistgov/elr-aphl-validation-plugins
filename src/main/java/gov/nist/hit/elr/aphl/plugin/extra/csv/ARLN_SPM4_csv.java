package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.SPM_4;
import gov.nist.hit.elr.aphl.plugin.extra.context.ARLN;
import hl7.v2.instance.Element;

public class ARLN_SPM4_csv extends SPM_4_csv {

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
  public String getProgram() {
    return "ARLN";
  }
}
