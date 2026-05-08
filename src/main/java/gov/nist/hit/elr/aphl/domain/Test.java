package gov.nist.hit.elr.aphl.domain;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Represents a combined test entity containing both order (OBR) and observation (OBX) information.
 * Contains fields such as id, OBR-4 (Universal Service Identifier), OBR-4 coding system,
 * OBX-3 (Observation Identifier), OBX-3 coding system, OBX-2 (Observation Result),
 * valueset, program, source, and updated date.
 */
public class Test {

	private String id;
	private String obr4Code;
	private String obr4CodeSystem;
	private String obx3Code;
	private String obx3CodeSystem;
	private String obx2;
	private String valueset;
	private String program;
	private String source;
	private String updatedDate;

	@JsonCreator
	public Test(@JsonProperty("id") String id, @JsonProperty("obr4Code") String obr4Code,
			@JsonProperty("obr4CodeSystem") String obr4CodeSystem, @JsonProperty("obx3Code") String obx3Code,
			@JsonProperty("obx3CodeSystem") String obx3CodeSystem, @JsonProperty("obx2") String obx2,
			@JsonProperty("valueset") String valueset, @JsonProperty("program") String program,
			@JsonProperty("source") String source, @JsonProperty("updatedDate") String updatedDate) {
		this.id = id;
		this.obr4Code = obr4Code;
		this.obr4CodeSystem = obr4CodeSystem;
		this.obx3Code = obx3Code;
		this.obx3CodeSystem = obx3CodeSystem;
		this.obx2 = obx2;
		this.valueset = valueset;
		this.program = program;
		this.source = source;
		this.updatedDate = updatedDate;
	}

	@JsonGetter("id")
	public String getId() {
		return id;
	}

	@JsonGetter("obr4Code")
	public String getObr4Code() {
		return obr4Code;
	}

	@JsonGetter("obr4CodeSystem")
	public String getObr4CodeSystem() {
		return obr4CodeSystem;
	}

	@JsonGetter("obx3Code")
	public String getObx3Code() {
		return obx3Code;
	}

	@JsonGetter("obx3CodeSystem")
	public String getObx3CodeSystem() {
		return obx3CodeSystem;
	}

	@JsonGetter("obx2")
	public String getObx2() {
		return obx2;
	}

	@JsonGetter("valueset")
	public String getValueset() {
		return valueset;
	}

	@JsonGetter("program")
	public String getProgram() {
		return program;
	}

	@JsonGetter("source")
	public String getSource() {
		return source;
	}

	@JsonGetter("updatedDate")
	public String getUpdatedDate() {
		return updatedDate;
	}

	public static long countByOBX3(String obx3Code, String obx3CodeSystem, Collection<Test> tests) {
		long result = tests.stream().filter(test -> obx3Code.equals(test.getObx3Code()))
				.filter(test -> obx3CodeSystem.equals(test.getObx3CodeSystem())).count();
		return result;
	}

	public static Set<Test> findByOBX3(String obx3Code, String obx3CodeSystem, Collection<Test> tests) {
		Set<Test> result = tests.stream().filter(test -> obx3Code.equals(test.getObx3Code()))
				.filter(test -> obx3CodeSystem.equals(test.getObx3CodeSystem())).collect(Collectors.toSet());
		return result;
	}

	public static long countByOBR4(String obr4Code, String obr4CodeSystem, Collection<Test> tests) {
		long result = tests.stream().filter(test -> obr4Code.equals(test.getObr4Code()))
				.filter(test -> obr4CodeSystem.equals(test.getObr4CodeSystem())).count();
		return result;
	}

	public static Set<Test> findByOBR4(String obr4Code, String obr4CodeSystem, Collection<Test> tests) {
		Set<Test> result = tests.stream().filter(test -> obr4Code.equals(test.getObr4Code()))
				.filter(test -> obr4CodeSystem.equals(test.getObr4CodeSystem())).collect(Collectors.toSet());
		return result;
	}

	public static Set<String> map(Collection<Test> tests) {
		Set<String> result = tests.stream().map(test -> test.getObx3Code()).collect(Collectors.toSet());
		return result;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", obr4Code=" + obr4Code + ", obr4CodeSystem=" + obr4CodeSystem + ", obx3Code="
				+ obx3Code + ", obx3CodeSystem=" + obx3CodeSystem + ", obx2=" + obx2 + ", valueset=" + valueset
				+ ", program=" + program + ", source=" + source + ", updatedDate=" + updatedDate + "]";
	}

}
