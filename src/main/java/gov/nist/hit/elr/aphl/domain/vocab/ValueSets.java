package gov.nist.hit.elr.aphl.domain.vocab;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a collection of ValueSet resources as returned by a FHIR ValueSet $expand operation.
 * Contains metadata about the resource type, total count, and list of entries.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSets {

    private String resourceType;
    private int total;
    private List<Resource> entry;

    /**
     * Constructs a ValueSets instance.
     *
     * @param resourceType the FHIR resource type (typically "ValueSet")
     * @param total        the total number of ValueSet resources
     * @param entry        the list of Resource objects containing ValueSet data
     */
    @JsonCreator
    public ValueSets(@JsonProperty("resourceType") String resourceType,
                     @JsonProperty("total") int total,
                     @JsonProperty("entry") List<Resource> entry) {
        super();
        this.resourceType = resourceType;
        this.total = total;
        this.entry = entry;
    }

    /**
     * Gets the FHIR resource type.
     *
     * @return the resource type string
     */
    @JsonGetter("resourceType")
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Gets the total number of ValueSet resources.
     *
     * @return the total count
     */
    @JsonGetter("total")
    public int getTotal() {
        return total;
    }

    /**
     * Gets the list of Resource entries.
     *
     * @return the list of Resource objects
     */
    @JsonGetter("entry")
    public List<Resource> getEntry() {
        return entry;
    }

    /**
     * Checks if a ValueSet with the given name exists in the collection.
     *
     * @param valueSetName the name of the ValueSet to search for
     * @return true if a ValueSet with the exact name is found, false otherwise
     */
    public boolean containsValueSet(String valueSetName) {
        Predicate<Resource> p1 = resource -> valueSetName.equals(resource.getResource().getName());
        boolean result = entry.stream().anyMatch(p1);
        return result;
    }

    /**
     * Returns a set of normalized ValueSet names that match the given name (case-insensitive).
     *
     * @param valueSetName the ValueSet name to match (case-insensitive)
     * @return a set containing the normalized name(s) of matching ValueSets
     */
    public Set<String> normalizeValueSetName(String valueSetName) {
        Predicate<Resource> p1 = resource -> valueSetName.equalsIgnoreCase(resource.getResource().getName());
        Set<String> result = entry.stream().filter(p1).map(resource -> resource.getResource().getName())
                .collect(Collectors.toSet());
        return result;
    }

    /**
     * Returns a set of normalized ValueSet names for a collection of input names.
     *
     * @param valueSetNames a set of ValueSet names to normalize
     * @return a set containing all normalized names found for the input names
     */
    public Set<String> normalizeValueSetNames(Set<String> valueSetNames) {
        Set<String> result = new HashSet<String>();
        for (String valueSetName : valueSetNames) {
            result.addAll(normalizeValueSetName(valueSetName));
        }
        return result;
    }
}