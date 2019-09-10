package gov.nist.hit.elr.aphl.plugin.extra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import gov.nist.hit.elr.plugin.utils.CSVUtils;
import gov.nist.hit.elr.plugin.utils.CodedElement;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public abstract class OBX {

	public abstract String getFOLDER();

	public abstract String getTEST_CSV();

	public abstract String getOBSERVATIONS_CSV();

	public abstract String getORDERS();

	public abstract String getVALUE_SETS_CSV();

	public java.util.List<String> assertionWithCustomMessages(Element e) throws IOException {

		java.util.List<String> messages = new ArrayList<String>();

		// parse OBX-3
		List<Element> OBX3List = Query.query(e, "3[1]").get();
		if (OBX3List == null || OBX3List.size() == 0) {
			// no OBX-3, we can move on, no check performed
			return messages;
		}
		Element OBX3 = OBX3List.apply(0);

		List<Simple> OBX3_1List = Query.queryAsSimple(OBX3, "1[1]").get();
		List<Simple> OBX3_3List = Query.queryAsSimple(OBX3, "3[1]").get();
		List<Simple> OBX3_4List = Query.queryAsSimple(OBX3, "4[1]").get();
		List<Simple> OBX3_6List = Query.queryAsSimple(OBX3, "6[1]").get();

		String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
		String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";
		String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
		String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

		ComplexCodedElement _OBX3 = new ComplexCodedElement(OBX3_1, OBX3_3, OBX3_4, OBX3_6);

		// parse OBX-2
		List<Simple> OBX2List = Query.queryAsSimple(e, "2[1]").get();
		if (OBX2List == null || OBX2List.size() == 0) {
			// no OBX-2, we can move on, no check performed
			return messages;
		}
		String _OBX2 = OBX2List.size() > 0 ? OBX2List.apply(0).value().raw() : "";

		// OBX-3 to OBX-2
		messages = checkOBX3_OBX2(_OBX3, _OBX2);
		// If OBX-2 is invalid - we do not check OBX-5
		if (messages.size() > 0) {
			return messages;
		}

		// parse OBX-5 - we only check for CE and CWE
		if ("CWE".equals(_OBX2) || "CE".equals(_OBX2)) {
			ComplexCodedElement _OBX5;
			List<Element> OBX5List = Query.query(e, "5[1]").get();
			if (OBX5List == null || OBX5List.size() == 0) {
				_OBX5 = new ComplexCodedElement("", "", "", "");
			} else {
				Element OBX5 = OBX5List.apply(0);

				List<Simple> OBX5_1List = Query.queryAsSimple(OBX5, "1[1]").get();
				List<Simple> OBX5_3List = Query.queryAsSimple(OBX5, "3[1]").get();
				List<Simple> OBX5_4List = Query.queryAsSimple(OBX5, "4[1]").get();
				List<Simple> OBX5_6List = Query.queryAsSimple(OBX5, "6[1]").get();

				String OBX5_1 = OBX5_1List.size() > 0 ? OBX5_1List.apply(0).value().raw() : "";
				String OBX5_3 = OBX5_3List.size() > 0 ? OBX5_3List.apply(0).value().raw() : "";
				String OBX5_4 = OBX5_4List.size() > 0 ? OBX5_4List.apply(0).value().raw() : "";
				String OBX5_6 = OBX5_6List.size() > 0 ? OBX5_6List.apply(0).value().raw() : "";

				_OBX5 = new ComplexCodedElement(OBX5_1, OBX5_3, OBX5_4, OBX5_6);
			}
			messages = checkOBX3_OBX5_CWE(_OBX3, _OBX5);
		}
		return messages;
	}

	public java.util.List<String> checkOBX3_OBX2(ComplexCodedElement obx3, String obx2) throws IOException {
		CSVUtils util = new CSVUtils();
		util.parse(getFOLDER(), getTEST_CSV(), getOBSERVATIONS_CSV(), getORDERS(), getVALUE_SETS_CSV());

		java.util.List<String> messages = new ArrayList<String>();

		CodedElement identifier = obx3.getIdentifier();
		CodedElement alternate = obx3.getAlternateIdentifier();

		if (util.getOBX3_OBX2().containsKey(identifier)) {
			Set<String> expected = util.getOBX3_OBX2().get(identifier);
			if (expected.contains("varies")) {
				// no check can be performed
				return messages;
			}
			if (!expected.contains(obx2)) {
				messages.add(
						"The OBX-2 value (" + obx2 + ") is not valid for this OBX-3 value (" + identifier.prettyPrint()
								+ "). The expected OBX-2 value is one of " + expected.toString() + "");
			}
		}
		if (util.getOBX3_OBX2().containsKey(alternate)) {
			Set<String> expected = util.getOBX3_OBX2().get(alternate);
			if (expected.contains("varies")) {
				// no check can be performed
				return messages;
			}
			if (!expected.contains(obx2)) {
				messages.add(
						"The OBX-2 value (" + obx2 + ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
								+ "). The expected OBX-2 value is one of " + expected.toString() + "");
			}
		}
		return messages;
	}

	public java.util.List<String> checkOBX3_OBX5_CWE(ComplexCodedElement obx3, ComplexCodedElement obx5)
			throws IOException {
		CSVUtils util = new CSVUtils();
		util.parse(getFOLDER(), getTEST_CSV(), getOBSERVATIONS_CSV(), getORDERS(), getVALUE_SETS_CSV());

		java.util.List<String> messages = new ArrayList<String>();

		CodedElement identifier = obx3.getIdentifier();
		CodedElement alternate = obx3.getAlternateIdentifier();

		if (util.getOBX3_OBX5().containsKey(identifier)) {
			String valueSetId = util.getOBX3_OBX5().get(identifier);
			if (!util.getValueSets().containsKey(valueSetId)) {
				messages.add("Invalid value set identifier ('" + valueSetId + "') for OBX-3 value "
						+ identifier.prettyPrint() + ". The tool cannot validate the OBX-5 value.");
			}

			Set<CodedElement> valueSet = util.getValueSets().get(valueSetId);
			if (valueSet.contains(obx5.getIdentifier()) || valueSet.contains(obx5.getAlternateIdentifier())) {
				// PASS
				return messages;
			}
			if (!obx5.getIdentifier().isEmpty() && !obx5.getAlternateIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.prettyPrint() + ") is not valid for this OBX-3 value ("
						+ identifier.prettyPrint() + "). OBX-5 is expected to be present in the '" + valueSetId
						+ "' value set");
			} else if (!obx5.getIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.getIdentifier().prettyPrint()
						+ ") is not valid for this OBX-3 value (" + identifier.prettyPrint()
						+ "). OBX-5 is expected to be present in the '" + valueSetId + "' value set");
			} else if (!obx5.getAlternateIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.getAlternateIdentifier().prettyPrint()
						+ ") is not valid for this OBX-3 value (" + identifier.prettyPrint()
						+ "). OBX-5 is expected to be present in the '" + valueSetId + "' value set");
			} else {
				messages.add("The OBX-5 value is not valid for this OBX-3 value (" + identifier.prettyPrint()
						+ "). OBX-5 is expected to be present in the '" + valueSetId + "' value set");
			}
		}

		if (util.getOBX3_OBX5().containsKey(alternate)) {
			String valueSetId = util.getOBX3_OBX5().get(alternate);
			if (!util.getValueSets().containsKey(valueSetId)) {
				messages.add("Invalid value set identifier ('" + valueSetId + "') for OBX-3 value "
						+ alternate.prettyPrint() + ". The tool cannot validate the OBX-5 value.");
			}

			Set<CodedElement> valueSet = util.getValueSets().get(valueSetId);
			if (valueSet.contains(obx5.getIdentifier()) || valueSet.contains(obx5.getAlternateIdentifier())) {
				// PASS
				return messages;
			}
			if (!obx5.getIdentifier().isEmpty() && !obx5.getAlternateIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.prettyPrint() + ") is not valid for this OBX-3 value ("
						+ alternate.prettyPrint() + "). OBX-5 is expected to be present in the '" + valueSetId
						+ "' value set");
			} else if (!obx5.getIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.getIdentifier().prettyPrint()
						+ ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
						+ "). OBX-5 is expected to be present in the '" + valueSetId + "' value set");
			} else if (!obx5.getAlternateIdentifier().isEmpty()) {
				messages.add("The OBX-5 value (" + obx5.getAlternateIdentifier().prettyPrint()
						+ ") is not valid for this OBX-3 value (" + alternate.prettyPrint()
						+ "). OBX-5 is expected to be present in the '" + valueSetId + "' value set");
			} else {
				messages.add("The OBX-5 value is not valid for this OBX-3 value (" + alternate.prettyPrint()
						+ "). OBX-5 is expected to be present in the '" + valueSetId + "' value set");
			}
		}
		return messages;
	}
}
