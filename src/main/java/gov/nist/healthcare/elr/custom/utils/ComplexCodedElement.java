package gov.nist.healthcare.elr.custom.utils;

public class ComplexCodedElement {

	private CodedElement identifier;
	private CodedElement alternateIdentifier;

	public ComplexCodedElement(CodedElement identifier, CodedElement alternateIdentifier) {
		super();
		this.identifier = identifier;
		this.alternateIdentifier = alternateIdentifier;
	}

	public ComplexCodedElement(String identifier, String codeSystem, String alternateIdentifier,
			String alternateCodeSystem) {
		super();
		this.identifier = new CodedElement(identifier, codeSystem);
		this.alternateIdentifier = new CodedElement(alternateIdentifier, alternateCodeSystem);
	}

	public CodedElement getIdentifier() {
		return identifier;
	}

	public CodedElement getAlternateIdentifier() {
		return alternateIdentifier;
	}

	public String prettyPrint() {
		String result = "";

		if (!identifier.isEmpty() && !alternateIdentifier.isEmpty()) {
			result = identifier.prettyPrint() + " and " + alternateIdentifier.prettyPrint();

		} else if (!identifier.isEmpty()) {
			result = identifier.prettyPrint();

		} else if (!alternateIdentifier.isEmpty()) {
			result = alternateIdentifier.prettyPrint();
		}
		return result;
	}

	@Override
	public String toString() {
		return "ComplexCodedElement [identifier=" + identifier + ", alternateIdentifier=" + alternateIdentifier + "]";
	}

}
