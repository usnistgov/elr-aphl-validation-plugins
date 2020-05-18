package gov.nist.hit.elr.aphl.domain.vocab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {

  private ValueSet resource;

  @JsonCreator
  public Resource(@JsonProperty("resource") ValueSet resource) {
    super();
    this.resource = resource;
  }

  @JsonGetter("resource")
  public ValueSet getResource() {
    return resource;
  }
}
