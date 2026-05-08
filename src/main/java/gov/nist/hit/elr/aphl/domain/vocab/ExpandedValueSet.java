package gov.nist.hit.elr.aphl.domain.vocab;

import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an expanded value set from FHIR terminology.
 * An expanded value set contains the actual set of codes that are
 * intended to be used for data exchange with the value set definition.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpandedValueSet extends ValueSet {

	private ValueSetExpansion expansion;

	/**
	 * Constructs an ExpandedValueSet with the specified properties.
	 *
	 * @param resourceType the FHIR resource type (typically "ValueSet")
	 * @param id the logical id of the value set
	 * @param version the version identifier of the value set
	 * @param name the name used to identify this value set
	 * @param expansion the expanded expansion containing the set of codes
	 */
	@JsonCreator
	public ExpandedValueSet(@JsonProperty("resourceType") String resourceType, @JsonProperty("id") String id,
			@JsonProperty("version") String version, @JsonProperty("name") String name,
			@JsonProperty("expansion") ValueSetExpansion expansion) {
		super(resourceType, id, version, name);
		this.expansion = expansion;
	}

	/**
	 * Gets the expansion of this value set.
	 *
	 * @return the expansion containing the set of codes
	 */
	@JsonGetter("expansion")
	public ValueSetExpansion getExpansion() {
		return expansion;
	}

	/**
	 * Checks if this expanded value set contains a specific code in the specified code system.
	 *
	 * @param code the code to check for
	 * @param codeSystem the code system (e.g., LOINC, SNOMED CT, etc.) to check against
	 * @return true if the code exists in the value set expansion with the specified code system, false otherwise
	 */
	public boolean containsCode(String code, String codeSystem) {
		List<ValueSetElement> elements = expansion.getElements();
		Predicate<ValueSetElement> p1 = element -> code.equals(element.getCode());
		Predicate<ValueSetElement> p2 = element -> codeSystem.equals(element.getSystem());
		boolean result = elements.stream().anyMatch(p1.and(p2));
		return result;
	}

}
