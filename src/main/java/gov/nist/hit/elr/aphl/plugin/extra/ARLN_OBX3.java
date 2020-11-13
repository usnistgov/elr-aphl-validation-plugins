package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.List;

import gov.nist.hit.elr.aphl.plugin.extra.csv.ARLN_OBX3_csv;
import gov.nist.hit.elr.aphl.plugin.extra.ws.ARLN_OBX3_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.PropertiesUtils;
import hl7.v2.instance.Element;

public class ARLN_OBX3 implements OBX3_OBR4 {

  private OBX3_OBR4 datasource;

  public ARLN_OBX3() throws IOException {
    String ds = (String) PropertiesUtils.getInstance().get("ARLN_OBX3");
    switch(ds) {
      case "CVS":
        datasource = new ARLN_OBX3_csv();
        break;
      case "WS":
        datasource = new ARLN_OBX3_ws();
        break;
      default:
        datasource = new ARLN_OBX3_csv();
    }
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
