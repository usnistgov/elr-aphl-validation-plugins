package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.List;

import gov.nist.hit.elr.aphl.plugin.extra.csv.Rabies_OBX3_OBR4_csv;
import gov.nist.hit.elr.aphl.plugin.extra.ws.Rabies_OBX3_OBR4_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.PropertiesUtils;
import hl7.v2.instance.Element;

public class Rabies_OBX3_OBR4 implements OBX3_OBR4 {

  private OBX3_OBR4 datasource;

  public Rabies_OBX3_OBR4() throws IOException {
    String ds = (String) PropertiesUtils.getInstance().get("Rabies_OBX3_OBR4");
    switch (ds) {
      case "CVS":
        datasource = new Rabies_OBX3_OBR4_csv();
        break;
      case "WS":
        datasource = new Rabies_OBX3_OBR4_ws();
        break;
      default:
        datasource = new Rabies_OBX3_OBR4_csv();
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
