package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import hl7.v2.instance.Element;

public class PHLIP_OBX extends OBX {

  public String getFOLDER() {
    return PHLIP.getFOLDER();
  }

  public String getTEST_CSV() {
    return PHLIP.getTEST_CSV();
  }

  public String getOBSERVATIONS_CSV() {
    return PHLIP.getOBSERVATIONS_CSV();
  }

  public String getORDERS() {
    return PHLIP.getORDERS();
  }

  public String getVALUE_SETS_CSV() {
    return PHLIP.getVALUE_SETS_CSV();
  }

  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

}
