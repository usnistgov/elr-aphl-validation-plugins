package gov.nist.hit.elr.aphl.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> {

  private int count;
  private List<T> data;

  @JsonCreator
  public Result(@JsonProperty("count") int count, @JsonProperty("data") List<T> data) {
    super();
    this.count = count;
    this.data = data;
  }

  @JsonGetter("count")
  public int getCount() {
    return count;
  }

  @JsonGetter("data")
  public List<T> getData() {
    return data;
  }

  @Override
  public String toString() {
    return "Result [count=" + count + ", data=" + data + "]";
  }



}
