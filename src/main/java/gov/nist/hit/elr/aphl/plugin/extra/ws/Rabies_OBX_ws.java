package gov.nist.hit.elr.aphl.plugin.extra.ws;

import java.io.IOException;
import java.net.URISyntaxException;

import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.aphl.plugin.extra.OBX;
import hl7.v2.instance.Element;

public class Rabies_OBX_ws extends OBX_ws {


  public java.util.List<String> assertionWithCustomMessages(Element e)
      throws ClassNotFoundException, IOException, InterruptedException, URISyntaxException {
    return super.assertionWithCustomMessages(e);
  }

  @Override
  public Program getProgram() {
    return Program.RABIES;
  }

  @Override
  public Program getValueSetProgram() {
    return Program.APHL_RABIES;
  }
}
