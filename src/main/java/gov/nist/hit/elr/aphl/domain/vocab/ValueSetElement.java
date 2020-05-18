package gov.nist.hit.elr.aphl.domain.vocab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSetElement {

  private String code;
  private String system;

  @JsonCreator
  public ValueSetElement(@JsonProperty("code") String code, @JsonProperty("system") String system) {
    super();
    this.code = code;
    this.system = system;
  }

  @JsonGetter("code")
  public String getCode() {
    return code;
  }

  @JsonGetter("system")
  public String getSystem() {
    return system;
  }


}
