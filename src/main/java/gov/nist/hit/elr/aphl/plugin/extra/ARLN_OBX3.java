package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import hl7.v2.instance.Element;

public class ARLN_OBX3 extends OBX3_OBR4 {

  @Override
  public String getFOLDER() {
    return ARLN.getFOLDER();
  }

  @Override
  public String getTEST_CSV() {
    return ARLN.getTEST_CSV();
  }

  @Override
  public String getOBSERVATIONS_CSV() {
    return ARLN.getOBSERVATIONS_CSV();
  }

  @Override
  public String getORDERS() {
    return ARLN.getORDERS();
  }

  @Override
  public String getVALUE_SETS_CSV() {
    return ARLN.getVALUE_SETS_CSV();
  }

  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

}
