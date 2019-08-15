package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import gov.nist.hit.elr.plugin.utils.CSVUtils;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;

public abstract class OBX {

	public abstract String getFOLDER();

	public abstract String getTEST_CSV();

	public abstract String getOBSERVATIONS_CSV();

	public abstract String getORDERS();

	public abstract String getVALUE_SETS_CSV();

	public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {
		return null;
	}

	public java.util.List<String> check() throws IOException {
		// OBX-3 to OBX-2
		// TODO : if OBX-2 is invalid - we do not check OBX-5
		// TODO does the check depend on OBX-2 value ??? or maybe only check for CWE ?
		// OBX-3 to OBX-5
		return null;
	}

	public java.util.List<String> checkOBX3_OBX2(ComplexCodedElement obx3, String obx2) throws IOException {
		CSVUtils util = new CSVUtils();
		util.parse(getFOLDER(), getTEST_CSV(), getOBSERVATIONS_CSV(), getORDERS(), getVALUE_SETS_CSV());

		java.util.List<String> messages = new ArrayList<String>();

		CodedElement identifier = obx3.getIdentifier();
		CodedElement alternate = obx3.getAlternateIdentifier();

		if (util.getOBX3_OBX2().containsKey(identifier)) {
			String expected = util.getOBX3_OBX2().get(identifier);
			if (!expected.equals(obx2)) {
				messages.add("The OBX-2 value (" + obx2 + ") is not valid for this OBX-3 value ("
						+ identifier.prettyPrint() + "). The expected OBX-2 value is " + expected + "");
			}
		}
		if (util.getOBX3_OBX2().containsKey(alternate)) {
			String expected = util.getOBX3_OBX2().get(alternate);
			if (!expected.equals(obx2)) {
				messages.add("The OBX-2 value (" + obx2 + ") is not valid for this OBX-3 value ("
						+ alternate.prettyPrint() + "). The expected OBX-2 value is " + expected + "");
			}
		}
		return messages;
	}

	public java.util.List<String> checkOBX3_OBX5(ComplexCodedElement obx3, ComplexCodedElement obx5)
			throws IOException {
		CSVUtils util = new CSVUtils();

		java.util.List<String> messages = new ArrayList<String>();

		CodedElement identifier = obx3.getIdentifier();
		CodedElement alternate = obx3.getAlternateIdentifier();

		if (util.getOBX3_OBX5().containsKey(identifier)) {
			String valueSetId = util.getOBX3_OBX5().get(identifier);
			if (!util.getValueSets().containsKey(valueSetId)) {
				messages.add("Invalid value set identifier (" + valueSetId + ") for OBX-3 value "
						+ identifier.prettyPrint() + ". The tool cannot validate the OBX-5 value.");
			}

			Set<CodedElement> valueSet = util.getValueSets().get(valueSetId);
			if (valueSet.contains(obx5.getIdentifier()) || valueSet.contains(obx5.getAlternateIdentifier())) {
				// PASS
				return messages;
			}
			if (!obx5.getIdentifier().isEmpty() && !obx5.getAlternateIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.prettyPrint() + ") is not valid for this OBX-3 value ("
						+ alternate.prettyPrint() + "). OBX-5 is expected to be present in the " + valueSetId
						+ " value set");
			} else if (!obx5.getIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.getIdentifier().prettyPrint()
						+ ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
						+ "). OBX-5 is expected to be present in the " + valueSetId + " value set");
			} else if (!obx5.getAlternateIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.getAlternateIdentifier().prettyPrint()
						+ ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
						+ "). OBX-5 is expected to be present in the " + valueSetId + " value set");
			}
		}
		// TODO : do the same for alternate OBX-3
		return messages;
	}
}
