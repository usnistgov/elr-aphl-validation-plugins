package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;

import hl7.v2.instance.Element;

public class VPD_OBX3_OBR extends OBX3_OBR4 {

  public String getFOLDER() {
    return VPD.getFOLDER();
  }

  public String getTEST_CSV() {
    return VPD.getTEST_CSV();
  }

  public String getOBSERVATIONS_CSV() {
    return VPD.getOBSERVATIONS_CSV();
  }

  public String getORDERS() {
    return VPD.getORDERS();
  }

  public String getVALUE_SETS_CSV() {
    return VPD.getVALUE_SETS_CSV();
  }

  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

}
