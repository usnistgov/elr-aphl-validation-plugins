package gov.nist.hit.elr.aphl.plugin.extra.ws;

import java.io.IOException;
import java.net.URISyntaxException;

import gov.nist.hit.elr.aphl.plugin.extra.context.VPD;
import hl7.v2.instance.Element;

public class VPD_OBX3_OBR4_Warning_ws extends OBX3_OBR4_Warning_ws {

	@Override
	public String getPROGRAM() {
		return VPD.getPROGRAM();
	}

	@Override
	public String getWEBSERVICE_URL() {
		return VPD.getWEBSERVICE_URL();
	}

	@Override
	public String getVOCAB_WEBSERVICE_URL() {
		return VPD.getVOCAB_WEBSERVICE_URL();
	}

	public java.util.List<String> assertionWithCustomMessages(Element e)
			throws ClassNotFoundException, IOException, InterruptedException, URISyntaxException {
		return super.assertionWithCustomMessages(e);
	}

}
