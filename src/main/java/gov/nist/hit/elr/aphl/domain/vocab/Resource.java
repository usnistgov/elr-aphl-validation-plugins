package gov.nist.hit.elr.aphl.domain.vocab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a resource wrapper for a ValueSet object.
 * This class is used in JSON serialization/deserialization to wrap a ValueSet.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {

  private ValueSet resource;

  /**
   * Constructs a Resource with the given ValueSet.
   *
   * @param resource the ValueSet to wrap
   */
  @JsonCreator
  public Resource(@JsonProperty("resource") ValueSet resource) {
    super();
    this.resource = resource;
  }

  /**
   * Returns the wrapped ValueSet.
   *
   * @return the ValueSet
   */
  @JsonGetter("resource")
  public ValueSet getResource() {
    return resource;
  }
}
