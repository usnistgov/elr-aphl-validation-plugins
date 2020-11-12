package gov.nist.hit.elr.plugin.utils;

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
    return "ComplexCodedElement [identifier=" + identifier + ", alternateIdentifier="
        + alternateIdentifier + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((alternateIdentifier == null) ? 0 : alternateIdentifier.hashCode());
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
    ComplexCodedElement other = (ComplexCodedElement) obj;
    if (alternateIdentifier == null) {
      if (other.alternateIdentifier != null)
        return false;
    } else if (!alternateIdentifier.equals(other.alternateIdentifier))
      return false;
    if (identifier == null) {
      if (other.identifier != null)
        return false;
    } else if (!identifier.equals(other.identifier))
      return false;
    return true;
  }

}
