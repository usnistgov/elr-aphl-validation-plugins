package gov.nist.hit.elr.plugin.cs;

import java.util.HashSet;
import java.util.Set;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_051 {

	/**
	 * ELR-051: The value of OBSERVATION[*].OBX.14.1 (Time) SHALL be equal to the
	 * value of OBR.7.1 (Time).
	 */

	/**
	 * 
	 * @param e the Order_Observation Group
	 * @return
	 */
	public boolean assertion(Element e) {

		List<Simple> OBR7List = Query.queryAsSimple(e, "2[1].7[1].1[1]").get();
		String OBR7_1 = OBR7List.size() > 0 ? OBR7List.apply(0).value().raw() : "";

		List<Simple> OBX14List = Query.queryAsSimple(e, "6[*].1[1].14[1].1[1]").get();
		Iterator<Simple> it = OBX14List.iterator();
		HashSet<String> values = new HashSet<String>();

		while (it.hasNext()) {
			Simple next = it.next();
			String OBX14_1 = next.value().raw();
			if (OBX14_1 != null && !"".equals(OBX14_1)) {
				values.add(OBX14_1);
			}
		}
		return check(OBR7_1, values);
	}

	public boolean check(String obr7, Set<String> obx14) {
		// remove any OBX-14 empty value
		obx14.remove("");

		// no OBX-14, no check
		if (obx14.size() == 0) {
			return true;
		}

		// all the valued OBX-14 must match OBR-7
		return (obx14.size() == 1) && (obx14.contains(obr7));
	}
}
