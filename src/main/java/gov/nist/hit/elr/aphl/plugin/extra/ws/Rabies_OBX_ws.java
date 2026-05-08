package gov.nist.hit.elr.aphl.plugin.extra.ws;

import java.io.IOException;
import java.net.URISyntaxException;

import gov.nist.hit.elr.aphl.plugin.extra.context.RABIES;
import hl7.v2.instance.Element;

public class Rabies_OBX_ws extends OBX_ws {

	@Override
	public String getPROGRAM() {
		return RABIES.getPROGRAM();
	}

	@Override
	public String getWEBSERVICE_URL() {
		return RABIES.getWEBSERVICE_URL();
	}

	@Override
	public String getVOCAB_WEBSERVICE_URL() {
		return RABIES.getVOCAB_WEBSERVICE_URL();
	}

	public java.util.List<String> assertionWithCustomMessages(Element e)
			throws ClassNotFoundException, IOException, InterruptedException, URISyntaxException {
		return super.assertionWithCustomMessages(e);
	}

}
