package gov.nist.hit.elr.plugin.utils;

import java.util.Objects;

/**
 * Represents a pair of Entity Identifiers (Placer and Filler) as used in HL7 messages.
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 */
public class EntityIdentifierPair {

    /** The placer assigned identifier */
    private final EntityIdentifier placerAssignedIdentifier;

    /** The filler assigned identifier */
    private final EntityIdentifier fillerAssignedIdentifier;

    /**
     * Creates a new EntityIdentifierPair with placer and filler identifiers.
     *
     * @param placerAssignedIdentifier the placer assigned identifier
     * @param fillerAssignedIdentifier the filler assigned identifier
     */
    public EntityIdentifierPair(EntityIdentifier placerAssignedIdentifier,
                                EntityIdentifier fillerAssignedIdentifier) {
        this.placerAssignedIdentifier = placerAssignedIdentifier;
        this.fillerAssignedIdentifier = fillerAssignedIdentifier;
    }

    /**
     * Creates a new EntityIdentifierPair with the component values for both identifiers.
     *
     * @param placerEntityIdentifier the placer entity identifier
     * @param placerNamespaceID the placer namespace ID
     * @param placerUniversalID the placer universal ID
     * @param placerUniversalIDType the placer universal ID type
     * @param fillerEntityIdentifier the filler entity identifier
     * @param fillerNamespaceID the filler namespace ID
     * @param fillerUniversalID the filler universal ID
     * @param fillerUniversalIDType the filler universal ID type
     */
    public EntityIdentifierPair(String placerEntityIdentifier, String placerNamespaceID,
                                String placerUniversalID, String placerUniversalIDType,
                                String fillerEntityIdentifier, String fillerNamespaceID,
                                String fillerUniversalID, String fillerUniversalIDType) {
        this.placerAssignedIdentifier = new EntityIdentifier(placerEntityIdentifier, placerNamespaceID,
                placerUniversalID, placerUniversalIDType);
        this.fillerAssignedIdentifier = new EntityIdentifier(fillerEntityIdentifier, fillerNamespaceID,
                fillerUniversalID, fillerUniversalIDType);
    }

    /**
     * Gets the placer assigned identifier.
     *
     * @return the placer assigned identifier, or null if not set
     */
    public EntityIdentifier getPlacerAssignedIdentifier() {
        return placerAssignedIdentifier;
    }

    /**
     * Gets the filler assigned identifier.
     *
     * @return the filler assigned identifier, or null if not set
     */
    public EntityIdentifier getFillerAssignedIdentifier() {
        return fillerAssignedIdentifier;
    }

    /**
     * Checks if both identifiers are empty or null.
     *
     * @return true if both identifiers are empty or null, false otherwise
     */
    public boolean isEmpty() {
        return (placerAssignedIdentifier == null || placerAssignedIdentifier.isEmpty()) &&
                (fillerAssignedIdentifier == null || fillerAssignedIdentifier.isEmpty());
    }

    /**
     * Returns a string representation of this entity identifier pair.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "EntityIdentifierPair [placerAssignedIdentifier=" + placerAssignedIdentifier +
                ", fillerAssignedIdentifier=" + fillerAssignedIdentifier + "]";
    }

    /**
     * Returns a formatted string representation suitable for display.
     *
     * @return formatted string representation
     */
    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        if (placerAssignedIdentifier != null && !placerAssignedIdentifier.isEmpty()) {
            builder.append("Placer Entity Identifier = '")
                    .append(placerAssignedIdentifier.getEntityIdentifier())
                    .append("', Placer Namespace ID = '")
                    .append(placerAssignedIdentifier.getNamespaceID())
                    .append("', Placer Universal ID = '")
                    .append(placerAssignedIdentifier.getUniversalID())
                    .append("', Placer Universal ID Type = '")
                    .append(placerAssignedIdentifier.getUniversalIDType())
                    .append("'");
        } else {
            builder.append("Empty Placer");
        }

        builder.append(", ");

        if (fillerAssignedIdentifier != null && !fillerAssignedIdentifier.isEmpty()) {
            builder.append("Filler Entity Identifier = '")
                    .append(fillerAssignedIdentifier.getEntityIdentifier())
                    .append("', Filler Namespace ID = '")
                    .append(fillerAssignedIdentifier.getNamespaceID())
                    .append("', Filler Universal ID = '")
                    .append(fillerAssignedIdentifier.getUniversalID())
                    .append("', Filler Universal ID Type = '")
                    .append(fillerAssignedIdentifier.getUniversalIDType())
                    .append("'");
        } else {
            builder.append("Empty Filler");
        }

        builder.append("]");
        return builder.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two EntityIdentifierPair objects are considered equal if their placer and filler
     * assigned identifiers are equal.
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
        EntityIdentifierPair other = (EntityIdentifierPair) obj;
        return Objects.equals(placerAssignedIdentifier, other.placerAssignedIdentifier)
                && Objects.equals(fillerAssignedIdentifier, other.fillerAssignedIdentifier);
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
        return Objects.hash(placerAssignedIdentifier, fillerAssignedIdentifier);
    }
}