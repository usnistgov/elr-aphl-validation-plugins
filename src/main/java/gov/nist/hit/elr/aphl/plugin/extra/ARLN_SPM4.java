package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.List;

import gov.nist.hit.elr.aphl.plugin.extra.csv.ARLN_SPM4_csv;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import gov.nist.hit.elr.plugin.utils.PropertiesUtils;
import hl7.v2.instance.Element;

public class ARLN_SPM4 implements SPM_4 {

  private SPM_4 datasource;

  public ARLN_SPM4() throws IOException{

    String ds = (String) PropertiesUtils.getInstance().get("ARLN_SPM4");
    switch(ds) {
      case "CVS":
        datasource = new ARLN_SPM4_csv();
        break;
//      case "WS":
//        datasource = new ARLN_SPM4_ws();
//        break;
      default:
        datasource = new ARLN_SPM4_csv();
    }
  }

  @Override
  public java.util.List<String> assertionWithCustomMessages(Element e) throws Exception {
    return datasource.assertionWithCustomMessages(e);
  }

  @Override
  public List<String> check(ComplexCodedElement SPM4) throws Exception {
    return datasource.check(SPM4);
  }

  @Override
  public String getProgram() {
    return datasource.getProgram();
  }
}
