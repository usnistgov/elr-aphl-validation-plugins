package gov.nist.hit.elr.aphl.domain;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Represents an HL7 Observation (OBX) segment.
 * Contains fields such as id, OBX-3 (code), OBX-3 coding system, program, source, and updated date.
 */
public class Observation {
	private String id;
	private String obx3Code;
	private String obx3CodeSystem;
	private String program;
	private String source;
	private String updatedDate;

	@JsonCreator
	public Observation(@JsonProperty("id") String id, @JsonProperty("obx3Code") String obx3Code,
			@JsonProperty("obx3CodeSystem") String obx3CodeSystem, @JsonProperty("program") String program,
			@JsonProperty("source") String source, @JsonProperty("updatedDate") String updatedDate) {
		this.id = id;
		this.obx3Code = obx3Code;
		this.obx3CodeSystem = obx3CodeSystem;
		this.program = program;
		this.source = source;
		this.updatedDate = updatedDate;
	}

	@JsonGetter("id")
	public String getId() {
		return id;
	}

	@JsonGetter("obx3Code")
	public String getObx3Code() {
		return obx3Code;
	}

	@JsonGetter("obx3CodeSystem")
	public String getObx3CodeSystem() {
		return obx3CodeSystem;
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

	public static Observation find(String obx3Code, String obx3CodeSystem, List<Observation> observations) {
		Observation result = observations.stream().filter(observation -> obx3Code.equals(observation.getObx3Code()))
				.filter(observation -> obx3CodeSystem.equals(observation.getObx3CodeSystem())).findAny().orElse(null);
		return result;
	}

	public static long count(String obx3Code, String obx3CodeSystem, Collection<Observation> observations) {
		long result = observations.stream().filter(observation -> obx3Code.equals(observation.getObx3Code()))
				.filter(observation -> obx3CodeSystem.equals(observation.getObx3CodeSystem())).count();
		return result;
	}

	@Override
	public String toString() {
		return "Observation [id=" + id + ", obx3Code=" + obx3Code + ", obx3CodeSystem=" + obx3CodeSystem + ", program="
				+ program + ", source=" + source + ", updatedDate=" + updatedDate + "]";
	}

}
