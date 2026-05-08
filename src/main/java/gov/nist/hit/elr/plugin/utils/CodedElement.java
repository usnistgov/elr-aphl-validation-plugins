package gov.nist.hit.elr.plugin.utils;

import java.util.Objects;

/**
 * Represents a coded element with an identifier, optional text description, and code system.
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 */
public class CodedElement implements Comparable<CodedElement> {

    /** The identifier of the coded element (e.g., code value) */
    private final String identifier;

    /** The text description of the coded element (e.g., code description) */
    private final String text;

    /** The code system (e.g., coding scheme, value set) */
    private final String codeSystem;

    /**
     * Creates a new CodedElement with identifier and code system.
     *
     * @param identifier the identifier of the coded element
     * @param codeSystem the code system of the coded element
     */
    public CodedElement(String identifier, String codeSystem) {
        this.identifier = identifier;
        this.text = null;
        this.codeSystem = codeSystem;
    }

    /**
     * Creates a new CodedElement with identifier, text, and code system.
     *
     * @param identifier the identifier of the coded element
     * @param text the text description of the coded element
     * @param codeSystem the code system of the coded element
     */
    public CodedElement(String identifier, String text, String codeSystem) {
        this.identifier = identifier;
        this.text = text;
        this.codeSystem = codeSystem;
    }

    /**
     * Gets the identifier of the coded element.
     *
     * @return the identifier, or null if not set
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the text description of the coded element.
     *
     * @return the text description, or null if not set
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the code system of the coded element.
     *
     * @return the code system, or null if not set
     */
    public String getCodeSystem() {
        return codeSystem;
    }

    /**
     * Checks if both identifier and code system are empty or null.
     * Note: This method does not consider the text field for emptiness.
     *
     * @return true if both identifier and code system are empty or null, false otherwise
     */
    public boolean isEmpty() {
        return (identifier == null && codeSystem == null)
            || ("".equals(identifier) && "".equals(codeSystem));
    }

    /**
     * Checks if the text field is empty or null.
     *
     * @return true if text is empty or null, false otherwise
     */
    public boolean isTextEmpty() {
        return text == null || "".equals(text);
    }

    /**
     * Checks if all fields (identifier, text, codeSystem) are empty or null.
     *
     * @return true if all fields are empty or null, false otherwise
     */
    public boolean isCompletelyEmpty() {
        return isEmpty() && isTextEmpty();
    }

    /**
     * Returns a formatted string representation of the coded element.
     * Format: identifier/codeSystem
     *
     * @return formatted string, or empty string if both identifier and codeSystem are empty
     */
    public String prettyPrint() {
        if (!isEmpty()) {
            return identifier + "/" + codeSystem;
        }
        return "";
    }

    /**
     * Returns a string representation of this coded element.
     *
     * @return string representation including identifier and codeSystem
     */
    @Override
    public String toString() {
        return "CodedElement [identifier=" + identifier + ", codeSystem=" + codeSystem + "]";
    }

    /**
     * Returns a detailed string representation including all fields.
     *
     * @return detailed string representation
     */
    public String toStringDetailed() {
        return "CodedElement [identifier=" + identifier + ", text=" + text + ", codeSystem=" + codeSystem + "]";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two CodedElement objects are considered equal if their identifier and codeSystem
     * fields are equal. The text field is not considered in equality comparison.
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
        CodedElement other = (CodedElement) obj;
        return Objects.equals(identifier, other.identifier)
            && Objects.equals(codeSystem, other.codeSystem);
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
        return Objects.hash(identifier, codeSystem);
    }

    /**
     * Compares this CodedElement with another object for order.
     * <p>
     * Elements are compared by identifier first, then by codeSystem.
     * </p>
     *
     * @param other the CodedElement to compare with
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(CodedElement other) {
        int identifierCompare = ((identifier == null) ?
                (other.identifier == null ? 0 : -1) :
                (other.identifier == null ? 1 : identifier.compareTo(other.identifier)));
        if (identifierCompare != 0) {
            return identifierCompare;
        }
        return ((codeSystem == null) ?
                (other.codeSystem == null ? 0 : -1) :
                (other.codeSystem == null ? 1 : codeSystem.compareTo(other.codeSystem)));
    }
}