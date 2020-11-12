package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.List;
import gov.nist.hit.elr.aphl.plugin.extra.ws.PHLIP_OBX3_OBR4_Warning_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;

public class PHLIP_OBX3_OBR4_Warning implements OBX3_OBR4_Warning {

  private OBX3_OBR4_Warning datasource;
  
  public PHLIP_OBX3_OBR4_Warning() {
    datasource = new PHLIP_OBX3_OBR4_Warning_ws();
  }

  @Override
  public List<String> assertionWithCustomMessages(Element e) throws Exception {
    return datasource.assertionWithCustomMessages(e);
  }

  @Override
  public List<String> check(ComplexCodedElement OBR4, List<ComplexCodedElement> OBX3s)
      throws Exception {
    return datasource.check(OBR4, OBX3s);
  }
}
