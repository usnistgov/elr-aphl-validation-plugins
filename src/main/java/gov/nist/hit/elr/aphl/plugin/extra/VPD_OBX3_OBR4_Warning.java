package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.List;

import gov.nist.hit.elr.aphl.plugin.extra.csv.VPD_OBX3_OBR4_Warning_csv;
import gov.nist.hit.elr.aphl.plugin.extra.ws.VPD_OBX3_OBR4_Warning_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.PropertiesUtils;
import hl7.v2.instance.Element;

public class VPD_OBX3_OBR4_Warning implements OBX3_OBR4_Warning {
 
  private OBX3_OBR4_Warning datasource;
  
  public VPD_OBX3_OBR4_Warning() throws IOException {
    String ds = (String) PropertiesUtils.getInstance().get("VPD_OBX3_OBR4_Warning");
    switch(ds) {
      case "CVS":
        datasource = new VPD_OBX3_OBR4_Warning_csv();
        break;
      case "WS":
        datasource = new VPD_OBX3_OBR4_Warning_ws();
        break;
      default:
        datasource = new VPD_OBX3_OBR4_Warning_csv();
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
