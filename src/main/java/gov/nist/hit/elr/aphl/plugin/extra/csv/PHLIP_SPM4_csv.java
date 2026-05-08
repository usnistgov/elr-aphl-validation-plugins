package gov.nist.hit.elr.aphl.plugin.extra.csv;

import java.io.IOException;

import gov.nist.hit.elr.aphl.plugin.extra.context.PHLIP;
import hl7.v2.instance.Element;

public class PHLIP_SPM4_csv extends SPM_4_csv {

	@Override
	public String getSPM4_CSV() {
		return PHLIP.getSPM4_CSV();
	}

	@Override
	public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
		return super.assertionWithCustomMessages(e);
	}

	@Override
	public String getProgram() {
		return PHLIP.getPROGRAM();
	}
}
