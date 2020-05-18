package gov.nist.hit.elr.aphl.domain.vocab;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSetExpansion {

  private int total;
  private List<ValueSetElement> elements;

  @JsonCreator
  public ValueSetExpansion(@JsonProperty("total") int total,
      @JsonProperty("contains") List<ValueSetElement> elements) {
    super();
    this.total = total;
    this.elements = elements;
  }

  @JsonGetter("total")
  public int getTotal() {
    return total;
  }

  @JsonGetter("contains")
  public List<ValueSetElement> getElements() {
    return elements;
  }

}
