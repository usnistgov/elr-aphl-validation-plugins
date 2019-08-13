package gov.nist.healthcare.elr.custom.cs;

import java.util.Set;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_019 {

	private static String PHLabReportAck = "PHLabReport-Ack";
	private static String AL = "AL";
	private static String NE = "NE";

	/**
	 * ELR-019: MSH-15 (Accept Acknowledgment Type) SHALL contain the constant value
	 * 'AL' IF any occurrence of MSH.21.1 (Entity Identifier) is 'PHLabReport-Ack',
	 * ELSE, if valued, SHALL contain the constant value 'NE'.
	 */

	/**
	 * 
	 * @param e MSH context
	 * @return
	 */
	public boolean assertion(Element e) {

		List<Simple> MSH15List = Query.queryAsSimple(e, "15[1]").get();
		String MSH15 = "";

		if (MSH15List != null && MSH15List.size() != 0) {
			// MSH-15 is present
			MSH15 = MSH15List.apply(0).value().raw();
		}

		List<Simple> MSH21_1List = Query.queryAsSimple(e, "21[*].1[1]").get();
		Iterator<Simple> it = MSH21_1List.iterator();

		while (it.hasNext()) {
			Simple next = it.next();
			String value = next.value().raw();
			if (PHLabReportAck.equals(value)) {
				return AL.equals(MSH15);
			}
		}

		return "".equals(MSH15) || NE.equals(MSH15);
	}

	public boolean check(String actual_MSH15, Set<String> actualMSH21_1) {

		if (actualMSH21_1 == null || actualMSH21_1.size() == 0) {
			return "".equals(actual_MSH15) || NE.equals(actual_MSH15);
		}

		if (actualMSH21_1.contains(PHLabReportAck)) {
			return AL.equals(actual_MSH15);
		}

		return "".equals(actual_MSH15) || NE.equals(actual_MSH15);

	}
}
