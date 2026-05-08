package gov.nist.hit.elr.aphl.domain.vocab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an element in a value set, consisting of a code and its coding system.
 * This class is used for JSON serialization/deserialization of value set elements
 * in ELR (Electronic Laboratory Reporting) validation plugins.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSetElement {

  /** The code value from the value set */
  private String code;

  /** The coding system (e.g., LOINC, SNOMED-CT) for the code */
  private String system;

  /**
   * Constructs a new ValueSetElement with the specified code and system.
   *
   * @param code the code value (e.g., "94500-6")
   * @param system the coding system identifier (e.g., "http://loinc.org")
   */
  @JsonCreator
  public ValueSetElement(@JsonProperty("code") String code, @JsonProperty("system") String system) {
    super();
    this.code = code;
    this.system = system;
  }

  /**
   * Gets the code value.
   *
   * @return the code
   */
  @JsonGetter("code")
  public String getCode() {
    return code;
  }

  /**
   * Gets the coding system.
   *
   * @return the system
   */
  @JsonGetter("system")
  public String getSystem() {
    return system;
  }


}
