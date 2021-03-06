package gov.nist.hit.elr.aphl.plugin.extra;

import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;


public interface SPM_4 {

  /**
   * 
   * @param SPM segment
   * @return
   * @throws IOException
   */
  public java.util.List<String> assertionWithCustomMessages(Element e) throws Exception ;

  public java.util.List<String> check(ComplexCodedElement SPM4) throws Exception ;

  public String getProgram();
}
