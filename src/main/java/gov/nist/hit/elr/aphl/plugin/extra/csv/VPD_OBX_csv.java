package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.OBX;
import gov.nist.hit.elr.aphl.plugin.extra.context.VPD;
import hl7.v2.instance.Element;

public class VPD_OBX_csv extends OBX_csv {

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
