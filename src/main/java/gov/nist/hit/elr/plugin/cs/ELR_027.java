package gov.nist.hit.elr.plugin.cs;

import java.util.HashSet;
import java.util.Set;

import gov.nist.hit.elr.plugin.utils.CodedElement;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_027 {

	/**
	 * ELR-027: If PID-7 (Date/Time of Birth) is not valued, then an OBX segment
	 * associated with the SPM segment SHALL be present to report patient age at
	 * specimen collection.
	 */

	private static Set<CodedElement> AGE_LOINC_CODES;

	static {
		CodedElement code1 = new CodedElement("21612-7", "Age - Reported", "LN");
		CodedElement code2 = new CodedElement("29553-5", "Age Time Patient Calc", "LN");
		CodedElement code3 = new CodedElement("35659-2", "Age at specimen collection", "LN");
		CodedElement code4 = new CodedElement("30525-0", "Age", "LN");

		AGE_LOINC_CODES = new HashSet<CodedElement>();
		AGE_LOINC_CODES.add(code1);
		AGE_LOINC_CODES.add(code2);
		AGE_LOINC_CODES.add(code3);
		AGE_LOINC_CODES.add(code4);
	}

	/**
	 * 
	 * @param e PATIENT_RESULT group
	 * @return
	 */
	public boolean assertion(Element e) {
		List<Element> PID7List = Query.query(e, "1[1].1[1].7[1]").get();
		if (PID7List.size() != 0) {
			// PID-7 is present
			return true;
		}

		// OBXs under SPM
		List<Element> OBXList = Query.query(e, "2[*].9[*].2[*]").get();
		if (OBXList.size() == 0) {
			// no OBXs after an SPM
			return false;
		}

		// OBX-3s under SPM
		List<Element> OBX3List = Query.query(e, "2[*].9[*].2[*].3[*]").get();
		if (OBX3List.size() == 0) {
			// no OBX-3s after an SPM
			return false;
		}

		java.util.Set<CodedElement> _OBX3s = new HashSet<CodedElement>();
		Iterator<Element> it = OBX3List.iterator();
		while (it.hasNext()) {
			Element OBX3 = it.next();

			List<Simple> OBX3_1List = Query.queryAsSimple(OBX3, "1[1]").get();
			List<Simple> OBX3_3List = Query.queryAsSimple(OBX3, "3[1]").get();

			List<Simple> OBX3_4List = Query.queryAsSimple(OBX3, "4[1]").get();
			List<Simple> OBX3_6List = Query.queryAsSimple(OBX3, "6[1]").get();

			String OBX3_1 = OBX3_1List.size() > 0 ? OBX3_1List.apply(0).value().raw() : "";
			String OBX3_3 = OBX3_3List.size() > 0 ? OBX3_3List.apply(0).value().raw() : "";

			String OBX3_4 = OBX3_4List.size() > 0 ? OBX3_4List.apply(0).value().raw() : "";
			String OBX3_6 = OBX3_6List.size() > 0 ? OBX3_6List.apply(0).value().raw() : "";

			CodedElement identifierOBX3 = new CodedElement(OBX3_1, OBX3_3);
			CodedElement alternateOBX3 = new CodedElement(OBX3_4, OBX3_6);

			_OBX3s.add(identifierOBX3);
			_OBX3s.add(alternateOBX3);
		}
		return check(_OBX3s);
	}

	public boolean check(Set<CodedElement> obx3s) {
		// Check that the intersection between the OBX-3s and the acceptable values is
		// not empty
		Set<CodedElement> intersection = new HashSet<CodedElement>(obx3s);
		intersection.retainAll(AGE_LOINC_CODES);
		return intersection.size() > 0;
	}
}
