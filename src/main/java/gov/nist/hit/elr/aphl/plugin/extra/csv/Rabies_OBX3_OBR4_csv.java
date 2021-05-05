package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.context.RABIES;
import hl7.v2.instance.Element;

public class Rabies_OBX3_OBR4_csv extends OBX3_OBR4_csv {

  public String getFOLDER() {
    return RABIES.getFOLDER();
  }

  public String getTEST_CSV() {
    return RABIES.getTEST_CSV();
  }

  public String getOBSERVATIONS_CSV() {
    return RABIES.getOBSERVATIONS_CSV();
  }

  public String getORDERS() {
    return RABIES.getORDERS();
  }

  public String getVALUE_SETS_CSV() {
    return RABIES.getVALUE_SETS_CSV();
  }

  public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
    return super.assertionWithCustomMessages(e);
  }

}
