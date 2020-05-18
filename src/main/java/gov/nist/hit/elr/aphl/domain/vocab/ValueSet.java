package gov.nist.hit.elr.aphl.domain.vocab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.plugin.utils.WSUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSet {

  private String resourceType;
  private String id;
  private String version;
  private String name;


  @JsonCreator
  public ValueSet(@JsonProperty("resourceType") String resourceType, @JsonProperty("id") String id,
      @JsonProperty("version") String version, @JsonProperty("name") String name) {
    super();
    this.resourceType = resourceType;
    this.id = id;
    this.version = version;
    this.name = name;
  }

  @JsonGetter("resourceType")
  public String getResourceType() {
    return resourceType;
  }

  @JsonGetter("id")
  public String getId() {
    return id;
  }

  @JsonGetter("version")
  public String getVersion() {
    return version;
  }

  @JsonGetter("name")
  public String getName() {
    return name;
  }


  public static void main(String[] args)
      throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    List<ValueSet> valueSet = ws.getValueSet(Program.APHL_ARLN, "Acinetobacter culture result");
    for (ValueSet vs : valueSet) {
      System.out.println(vs.getName());
    }

  }
}
