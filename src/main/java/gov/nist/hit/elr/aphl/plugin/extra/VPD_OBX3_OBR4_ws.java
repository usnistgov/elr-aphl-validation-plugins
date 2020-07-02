package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.net.URISyntaxException;

import gov.nist.hit.elr.aphl.domain.Program;
import hl7.v2.instance.Element;

public class VPD_OBX3_OBR4_ws extends OBX3_OBR4_ws {

  public java.util.List<String> assertionWithCustomMessages(Element e)
      throws ClassNotFoundException, IOException, InterruptedException, URISyntaxException {
    try {
      return super.assertionWithCustomMessages(e);
    } catch (Exception e1) {
      System.out.println(e1);
      throw e1;
    }
  }

  @Override
  public Program getProgram() {
    return Program.VPD;
  }

  @Override
  public Program getValueSetProgram() {
    return Program.APHL_VPD;
  }


}
