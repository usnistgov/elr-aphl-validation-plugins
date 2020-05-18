package gov.nist.hit.elr.aphl.domain.vocab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.plugin.utils.WSUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpandedValueSet extends ValueSet {

  private ValueSetExpansion expansion;

  @JsonCreator
  public ExpandedValueSet(@JsonProperty("resourceType") String resourceType,
      @JsonProperty("id") String id, @JsonProperty("version") String version,
      @JsonProperty("name") String name, @JsonProperty("expansion") ValueSetExpansion expansion) {
    super(resourceType, id, version, name);
    this.expansion = expansion;
  }

  @JsonGetter("expansion")
  public ValueSetExpansion getExpansion() {
    return expansion;
  }

  public boolean containsCode(String code, String codeSystem) {
    List<ValueSetElement> elements = expansion.getElements();
    Predicate<ValueSetElement> p1 = element -> code.equals(element.getCode());
    Predicate<ValueSetElement> p2 = element -> codeSystem.equals(element.getSystem());
    boolean result = elements.stream().anyMatch(p1.and(p2));
    return result;
  }


  public static void main(String[] args)
      throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    List<ExpandedValueSet> valueSet =
        ws.getExpandedValueSet(Program.APHL_ARLN, "Acinetobacter culture result");
    for (ExpandedValueSet vs : valueSet) {
      System.out.println(vs.getName());
      System.out.println(vs.getExpansion().getTotal());
      System.out.println(vs.containsCode("2106-3", "CDCREC"));
      System.out.println(vs.containsCode("UNK", "CDCREC"));
    }
  }
}
