package gov.nist.hit.elr.plugin.utils;

public class CodedElement {

	private String identifier;
	private String codeSystem;

	public CodedElement(String identifier, String codeSystem) {
		super();
		this.identifier = identifier;
		this.codeSystem = codeSystem;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCodeSystem() {
		return codeSystem;
	}

	public void setCodeSystem(String codeSystem) {
		this.codeSystem = codeSystem;
	}

	public boolean isEmpty() {
		return (identifier == null && codeSystem == null) || ("".equals(identifier) && "".equals(codeSystem));
	}

	public String prettyPrint() {
		if (!isEmpty()) {
			return identifier + "/" + codeSystem;
		}
		return "";
	}

	@Override
	public String toString() {
		return "CodedElement [identifier=" + identifier + ", codeSystem=" + codeSystem + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeSystem == null) ? 0 : codeSystem.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodedElement other = (CodedElement) obj;
		if (codeSystem == null) {
			if (other.codeSystem != null)
				return false;
		} else if (!codeSystem.equals(other.codeSystem))
			return false;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

}
