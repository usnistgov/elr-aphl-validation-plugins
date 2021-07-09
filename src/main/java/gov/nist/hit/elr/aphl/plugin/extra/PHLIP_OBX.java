package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.List;

import gov.nist.hit.elr.aphl.plugin.extra.csv.PHLIP_OBX_csv;
import gov.nist.hit.elr.aphl.plugin.extra.ws.PHLIP_OBX_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.PropertiesUtils;
import hl7.v2.instance.Element;

public class PHLIP_OBX implements OBX {

  private OBX datasource;

  public PHLIP_OBX() throws IOException {
    String ds = (String) PropertiesUtils.getInstance().get("PHLIP_OBX");
    switch (ds) {
      case "CVS":
        datasource = new PHLIP_OBX_csv();
        break;
      case "WS":
        datasource = new PHLIP_OBX_ws();
        break;
      default:
        datasource = new PHLIP_OBX_csv();
    }
  }

  @Override
  public List<String> assertionWithCustomMessages(Element e) throws Exception {
    return datasource.assertionWithCustomMessages(e);
  }

  @Override
  public List<String> checkOBX3_OBX2(ComplexCodedElement obx3, String obx2) throws Exception {
    return datasource.checkOBX3_OBX2(obx3, obx2);
  }

  @Override
  public List<String> checkOBX3_OBX5_CWE(ComplexCodedElement obx3, ComplexCodedElement obx5)
      throws Exception {
    return datasource.checkOBX3_OBX5_CWE(obx3, obx5);
  }

}
