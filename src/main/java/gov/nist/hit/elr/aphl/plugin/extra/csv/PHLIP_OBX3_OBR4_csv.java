package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.OBX3_OBR4;
import gov.nist.hit.elr.aphl.plugin.extra.PHLIP;
import hl7.v2.instance.Element;

public class PHLIP_OBX3_OBR4_csv extends OBX3_OBR4_csv {

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
