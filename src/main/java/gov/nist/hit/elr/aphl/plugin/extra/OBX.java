package gov.nist.hit.elr.aphl.plugin.extra;

import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;


public interface OBX {

  public java.util.List<String> assertionWithCustomMessages(Element e) throws Exception;

  public java.util.List<String> checkOBX3_OBX2(ComplexCodedElement obx3, String obx2) throws Exception;

  public java.util.List<String> checkOBX3_OBX5_CWE(ComplexCodedElement obx3,
      ComplexCodedElement obx5) throws Exception;
}
