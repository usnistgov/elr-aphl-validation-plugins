package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.context.VPD;
import hl7.v2.instance.Element;

public class VPD_OBX3_OBR4_csv extends OBX3_OBR4_csv {

	public String getTEST_CSV() {
		return VPD.getTEST_CSV();
	}

	public String getOBSERVATIONS_CSV() {
		return VPD.getOBSERVATIONS_CSV();
	}

	public String getORDERS_CSV() {
		return VPD.getORDERS_CSV();
	}

	public String getVALUE_SETS_CSV() {
		return VPD.getVALUE_SETS_CSV();
	}

	public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
		return super.assertionWithCustomMessages(e);
	}

}
