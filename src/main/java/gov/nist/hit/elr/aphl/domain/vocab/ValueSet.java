package gov.nist.hit.elr.aphl.domain.vocab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a ValueSet resource as defined in HL7 FHIR.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSet {

    /** The resource type (e.g., "ValueSet"). */
    private String resourceType;

    /** The identifier of the value set. */
    private String id;

    /** The version of the value set. */
    private String version;

    /** The name of the value set. */
    private String name;

    /**
     * Constructs a ValueSet with the specified properties.
     *
     * @param resourceType the resource type
     * @param id the identifier
     * @param version the version
     * @param name the name
     */
    @JsonCreator
    public ValueSet(@JsonProperty("resourceType") String resourceType,
                    @JsonProperty("id") String id,
                    @JsonProperty("version") String version,
                    @JsonProperty("name") String name) {
        super();
        this.resourceType = resourceType;
        this.id = id;
        this.version = version;
        this.name = name;
    }

    /** @return the resource type */
    @JsonGetter("resourceType")
    public String getResourceType() {
        return resourceType;
    }

    /** @return the identifier */
    @JsonGetter("id")
    public String getId() {
        return id;
    }

    /** @return the version */
    @JsonGetter("version")
    public String getVersion() {
        return version;
    }

    /** @return the name */
    @JsonGetter("name")
    public String getName() {
        return name;
    }
}