package gov.nist.hit.elr.plugin.utils;

import java.util.Objects;

/**
 * Represents an HL7 Entity Identifier (EI) data type component.
 * <p>
 * The Entity Identifier consists of four components:
 * <ol>
 *   <li>Entity Identifier</li>
 *   <li>Namespace ID</li>
 *   <li>Universal ID</li>
 *   <li>Universal ID Type</li>
 * </ol>
 * </p>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 */
public class EntityIdentifier {

    /** The entity identifier */
    private final String entityIdentifier;

    /** The namespace ID */
    private final String namespaceID;

    /** The universal ID */
    private final String universalID;

    /** The universal ID type */
    private final String universalIDType;

    /**
     * Creates a new EntityIdentifier with all four components.
     *
     * @param entityIdentifier the entity identifier
     * @param namespaceID the namespace ID
     * @param universalID the universal ID
     * @param universalIDType the universal ID type
     */
    public EntityIdentifier(String entityIdentifier, String namespaceID,
                            String universalID, String universalIDType) {
        this.entityIdentifier = entityIdentifier;
        this.namespaceID = namespaceID;
        this.universalID = universalID;
        this.universalIDType = universalIDType;
    }

    /**
     * Gets the entity identifier.
     *
     * @return the entity identifier, or null if not set
     */
    public String getEntityIdentifier() {
        return entityIdentifier;
    }

    /**
     * Gets the namespace ID.
     *
     * @return the namespace ID, or null if not set
     */
    public String getNamespaceID() {
        return namespaceID;
    }

    /**
     * Gets the universal ID.
     *
     * @return the universal ID, or null if not set
     */
    public String getUniversalID() {
        return universalID;
    }

    /**
     * Gets the universal ID type.
     *
     * @return the universal ID type, or null if not set
     */
    public String getUniversalIDType() {
        return universalIDType;
    }

    /**
     * Checks if all four components are empty or null.
     *
     * @return true if all components are empty or null, false otherwise
     */
    public boolean isEmpty() {
        return (isEmptyOrNull(entityIdentifier) &&
                isEmptyOrNull(namespaceID) &&
                isEmptyOrNull(universalID) &&
                isEmptyOrNull(universalIDType));
    }

    /**
     * Returns a string representation suitable for display.
     *
     * @return a formatted string representation
     */
    public String toShortString() {
        if (isEmpty()) {
            return "[]";
        }
        return "[" + entityIdentifier + ", " + namespaceID + ", " + universalID + ", " + universalIDType + "]";
    }

    /**
     * Returns a string representation of this entity identifier.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "EntityIdentifier [entityIdentifier=" + entityIdentifier +
                ", namespaceID=" + namespaceID +
                ", universalID=" + universalID +
                ", universalIDType=" + universalIDType + "]";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two EntityIdentifier objects are considered equal if all their corresponding
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
        EntityIdentifier other = (EntityIdentifier) obj;
        return Objects.equals(entityIdentifier, other.entityIdentifier)
                && Objects.equals(namespaceID, other.namespaceID)
                && Objects.equals(universalID, other.universalID)
                && Objects.equals(universalIDType, other.universalIDType);
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
        return Objects.hash(entityIdentifier, namespaceID, universalID, universalIDType);
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