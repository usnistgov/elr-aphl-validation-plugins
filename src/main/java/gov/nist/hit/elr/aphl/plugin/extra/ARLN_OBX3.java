package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.List;
import gov.nist.hit.elr.aphl.plugin.extra.ws.ARLN_OBX3_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;

public class ARLN_OBX3 implements OBX3_OBR4 {

  private OBX3_OBR4 datasource;
  
  public ARLN_OBX3() {
    datasource = new ARLN_OBX3_ws();
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
