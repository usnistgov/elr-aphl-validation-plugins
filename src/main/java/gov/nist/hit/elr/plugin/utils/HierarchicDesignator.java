package gov.nist.hit.elr.plugin.utils;

import java.util.Objects;

/**
 * Represents an HL7 Hierarchic Designator (HD) data type component.
 * <p>
 * The Hierarchic Designator consists of three components:
 * <ol>
 *   <li>Namespace ID</li>
 *   <li>Universal ID</li>
 *   <li>Universal ID Type</li>
 * </ol>
 * </p>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 */
public class HierarchicDesignator {

    /** The namespace ID */
    private final String namespaceId;

    /** The universal ID */
    private final String universalId;

    /** The universal ID type */
    private final String universalIdType;

    /**
     * Creates a new HierarchicDesignator with all three components.
     *
     * @param namespaceId the namespace ID
     * @param universalId the universal ID
     * @param universalIdType the universal ID type
     */
    public HierarchicDesignator(String namespaceId, String universalId, String universalIdType) {
        this.namespaceId = namespaceId;
        this.universalId = universalId;
        this.universalIdType = universalIdType;
    }

    /**
     * Gets the namespace ID.
     *
     * @return the namespace ID, or null if not set
     */
    public String getNamespaceId() {
        return namespaceId;
    }

    /**
     * Gets the universal ID.
     *
     * @return the universal ID, or null if not set
     */
    public String getUniversalId() {
        return universalId;
    }

    /**
     * Gets the universal ID type.
     *
     * @return the universal ID type, or null if not set
     */
    public String getUniversalIdType() {
        return universalIdType;
    }

    /**
     * Creates a normalized copy of this HierarchicDesignator with all components converted to lowercase.
     * <p>
     * This method is useful for case-insensitive comparisons.
     * </p>
     *
     * @return a new HierarchicDesignator with all components in lowercase
     */
    public HierarchicDesignator normalize() {
        return new HierarchicDesignator(
                (namespaceId == null) ? null : namespaceId.toLowerCase(),
                (universalId == null) ? null : universalId.toLowerCase(),
                (universalIdType == null) ? null : universalIdType.toLowerCase()
        );
    }

    /**
     * Checks if all three components are empty or null.
     *
     * @return true if all components are empty or null, false otherwise
     */
    public boolean isEmpty() {
        return (isEmptyOrNull(namespaceId) &&
                isEmptyOrNull(universalId) &&
                isEmptyOrNull(universalIdType));
    }

    /**
     * Returns a string representation of this hierarchic designator.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "HierarchicDesignator [namespaceId=" + namespaceId +
                ", universalId=" + universalId +
                ", universalIdType=" + universalIdType + "]";
    }

    /**
     * Returns a formatted string representation suitable for display.
     *
     * @return formatted string representation
     */
    public String prettyPrint() {
        return "[Namespace ID='" + namespaceId + "', Universal ID='" + universalId +
                "', Universal ID Type='" + universalIdType + "']";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two HierarchicDesignator objects are considered equal if all their corresponding
     * fields are equal.
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
        HierarchicDesignator other = (HierarchicDesignator) obj;
        return Objects.equals(namespaceId, other.namespaceId)
                && Objects.equals(universalId, other.universalId)
                && Objects.equals(universalIdType, other.universalIdType);
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
        return Objects.hash(namespaceId, universalId, universalIdType);
    }

    /**
     * Helper method to check if a string is empty or null.
     *
     * @param str the string to check
     * @return true if the string is null or empty, false otherwise
     */
    private boolean isEmptyOrNull(String str) {
        return str == null || "".equals(str);
    }
}