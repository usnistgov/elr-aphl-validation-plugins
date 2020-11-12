package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.List;

import gov.nist.hit.elr.aphl.plugin.extra.ws.ARLN_OBX_ws;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;

public class ARLN_OBX implements OBX {
  
  private OBX datasource;
  
  public ARLN_OBX() {
    datasource = new ARLN_OBX_ws();
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
  public List<String> checkOBX3_OBX5_CWE(ComplexCodedElement obx3, ComplexCodedElement obx5) throws Exception {
    return datasource.checkOBX3_OBX5_CWE(obx3, obx5);
  }


}
