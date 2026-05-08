package gov.nist.hit.elr.aphl.domain;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Represents an HL7 Order (OBR) segment.
 * Contains fields such as id, OBR-4 (Universal Service Identifier), OBR-4 coding system, program, source, and updated date.
 */
public class Order {

	private String id;
	private String obr4Code;
	private String obr4CodeSystem;
	private String program;
	private String source;
	private String updatedDate;

	@JsonCreator
	public Order(@JsonProperty("id") String id, @JsonProperty("obr4Code") String obr4Code,
			@JsonProperty("obr4CodeSystem") String obr4CodeSystem, @JsonProperty("program") String program,
			@JsonProperty("source") String source, @JsonProperty("updatedDate") String updatedDate) {
		this.id = id;
		this.obr4Code = obr4Code;
		this.obr4CodeSystem = obr4CodeSystem;
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

	public static Order find(String obr4Code, String obr4CodeSystem, List<Order> orders) {
		Order result = orders.stream().filter(order -> obr4Code.equals(order.getObr4Code()))
				.filter(order -> obr4CodeSystem.equals(order.getObr4CodeSystem())).findAny().orElse(null);
		return result;
	}

	public static long count(String obr4Code, String obr4CodeSystem, Collection<Order> orders) {
		long result = orders.stream().filter(order -> obr4Code.equals(order.getObr4Code()))
				.filter(order -> obr4CodeSystem.equals(order.getObr4CodeSystem())).count();
		return result;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", obr4Code=" + obr4Code + ", obr4CodeSystem=" + obr4CodeSystem + ", program="
				+ program + ", source=" + source + ", updatedDate=" + updatedDate + "]";
	}

}
