package gov.nist.hit.elr.aphl.domain.vocab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.plugin.utils.WSUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueSets {

  private String resourceType;
  private int total;
  private List<Resource> entry;

  @JsonCreator
  public ValueSets(@JsonProperty("resourceType") String resourceType,
      @JsonProperty("total") int total, @JsonProperty("entry") List<Resource> entry) {
    super();
    this.resourceType = resourceType;
    this.total = total;
    this.entry = entry;
  }

  @JsonGetter("resourceType")
  public String getResourceType() {
    return resourceType;
  }

  @JsonGetter("total")
  public int getTotal() {
    return total;
  }

  @JsonGetter("entry")
  public List<Resource> getEntry() {
    return entry;
  }

  public boolean containsValueSet(String valueSetName) {
    Predicate<Resource> p1 = resource -> valueSetName.equals(resource.getResource().getName());
    boolean result = entry.stream().anyMatch(p1);
    return result;
  }

  public Set<String> normalizeValueSetName(String valueSetName) {
    Predicate<Resource> p1 =
        resource -> valueSetName.equalsIgnoreCase(resource.getResource().getName());
    Set<String> result = entry.stream().filter(p1).map(resource -> resource.getResource().getName())
        .collect(Collectors.toSet());
    return result;
  }

  public Set<String> normalizeValueSetNames(Set<String> valueSetNames) {
    Set<String> result = new HashSet<String>();
    for (String valueSetName : valueSetNames) {
      result.addAll(normalizeValueSetName(valueSetName));
    }
    return result;
  }


  public static void main(String[] args)
      throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    ValueSets valueSets = ws.getValueSets(Program.APHL_ARLN);
    System.out.println(valueSets.getTotal());
    System.out.println(valueSets.containsValueSet("Race"));
    System.out.println(valueSets.containsValueSet("race"));

    System.out.println(valueSets.normalizeValueSetName("race"));
    System.out.println(valueSets.normalizeValueSetName("Race"));
  }
}
