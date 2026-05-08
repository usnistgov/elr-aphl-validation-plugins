package gov.nist.hit.elr.plugin.utils;

import java.util.Objects;

/**
 * Represents a complex coded element with a primary identifier and an optional alternate identifier.
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 */
public class ComplexCodedElement {

    /** The primary identifier */
    private final CodedElement identifier;

    /** The alternate identifier (may be null) */
    private final CodedElement alternateIdentifier;

    /**
     * Creates a new ComplexCodedElement with primary and alternate identifiers.
     *
     * @param identifier the primary identifier
     * @param alternateIdentifier the alternate identifier, or null if not present
     */
    public ComplexCodedElement(CodedElement identifier, CodedElement alternateIdentifier) {
        this.identifier = identifier;
        this.alternateIdentifier = alternateIdentifier;
    }

    /**
     * Creates a new ComplexCodedElement with identifier, code system, alternate identifier,
     * and alternate code system.
     *
     * @param identifier the primary identifier
     * @param codeSystem the primary code system
     * @param alternateIdentifier the alternate identifier
     * @param alternateCodeSystem the alternate code system
     */
    public ComplexCodedElement(String identifier, String codeSystem,
                               String alternateIdentifier, String alternateCodeSystem) {
        this.identifier = new CodedElement(identifier, codeSystem);
        this.alternateIdentifier = (alternateIdentifier == null || alternateCodeSystem == null)
                ? null
                : new CodedElement(alternateIdentifier, alternateCodeSystem);
    }

    /**
     * Gets the primary identifier.
     *
     * @return the primary identifier, or null if not set
     */
    public CodedElement getIdentifier() {
        return identifier;
    }

    /**
     * Gets the alternate identifier.
     *
     * @return the alternate identifier, or null if not set
     */
    public CodedElement getAlternateIdentifier() {
        return alternateIdentifier;
    }

    /**
     * Checks if both identifiers are empty or null.
     *
     * @return true if both identifiers are empty or null, false otherwise
     */
    public boolean isEmpty() {
        return ((identifier == null || identifier.isEmpty()) &&
                (alternateIdentifier == null || alternateIdentifier.isEmpty()));
    }

    /**
     * Returns a formatted string representation of the complex coded element.
     * Format: identifier [and alternateIdentifier]
     *
     * @return formatted string representation
     */
    public String prettyPrint() {
        StringBuilder result = new StringBuilder();

        if (identifier != null && !identifier.isEmpty()) {
            result.append(identifier.prettyPrint());
        }

        if (alternateIdentifier != null && !alternateIdentifier.isEmpty()) {
            if (result.length() > 0) {
                result.append(" and ");
            }
            result.append(alternateIdentifier.prettyPrint());
        }

        return result.toString();
    }

    /**
     * Returns a string representation of this complex coded element.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "ComplexCodedElement [identifier=" + identifier +
                ", alternateIdentifier=" + alternateIdentifier + "]";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two ComplexCodedElement objects are considered equal if their identifier and
     * alternateIdentifier fields are equal.
     * </p>
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComplexCodedElement other = (ComplexCodedElement) obj;
        return Objects.equals(identifier, other.identifier)
                && Objects.equals(alternateIdentifier, other.alternateIdentifier);
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * This method is supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * </p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(identifier, alternateIdentifier);
    }
}