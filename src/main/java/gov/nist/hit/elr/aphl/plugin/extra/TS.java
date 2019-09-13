package gov.nist.hit.elr.aphl.plugin.extra;

import gov.nist.healthcare.utils.date.DateUtil;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.immutable.List;

public class TS {

//	public java.util.List<String> assertionWithCustomMessages(Element e) {
//
//	}

	/**
	 * 
	 * @param e TS context
	 * @return
	 */
	public boolean assertion(Element e) {

		// parse TS
		List<Simple> TS1List = Query.queryAsSimple(e, "1[1]").get();
		if (TS1List == null || TS1List.size() == 0) {
			return true;
		}
		Simple TS1 = TS1List.apply(0);
		if (e == null) {
			// element is null, nothing to check
			return true;
		}
		String dtm = TS1.value().raw();
		return check(dtm);
	}

	public boolean check(String dtm) {
		return DateUtil.isValid(dtm);
	}

}
