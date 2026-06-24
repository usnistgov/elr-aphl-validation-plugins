package gov.nist.hit.elr.plugin.cs;

import hl7.v2.instance.Element;

public class TODO {

	/**
	 * 
	 * @param e any context
	 * @return
	 */
	public java.util.List<String> assertionWithCustomMessages(Element e) {
		java.util.List<String> messages = new java.util.ArrayList<String>();
		messages.add("This Conformance Statement cannot be evaluated because it was not implemented.");
		return messages;
	}

}
