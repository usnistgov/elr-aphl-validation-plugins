package gov.nist.hit.elr.aphl.domain;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

  private String id;
  private String obr4Code;
  private String obr4CodeSystem;
  private String program;
  private String source;
  private String updatedDate;


  @JsonCreator
  public Order(@JsonProperty("id") String id, @JsonProperty("obr4Code") String obr4Code,
      @JsonProperty("obr4CodeSystem") String obr4CodeSystem,
      @JsonProperty("program") String program, @JsonProperty("source") String source,
      @JsonProperty("updatedDate") String updatedDate) {
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
    return "Order [id=" + id + ", obr4Code=" + obr4Code + ", obr4CodeSystem=" + obr4CodeSystem
        + ", program=" + program + ", source=" + source + ", updatedDate=" + updatedDate + "]";
  }

  public static void main(String[] args) throws JsonProcessingException, IOException {

    // deserialize
    String json =
        "{\"id\": \"id\",\"obr4Code\": \"obr4Code\",\"obr4CodeSystem\": \"obr4CodeSystem\",\"program\": \"program\",\"source\": \"source\",\"updatedDate\": \"2020-03-26T19:41:36.061Z\"}";

    Order bean = new ObjectMapper().reader(Order.class).readValue(json);

    System.out.println(bean.getId());
    System.out.println(bean.getObr4Code());
    System.out.println(bean.getObr4CodeSystem());
    System.out.println(bean.getProgram());
    System.out.println(bean.getSource());
    System.out.println(bean.getUpdatedDate());

    // serialize
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);

    // deserialize list
    String json_1 =
        "{\"id\": \"1\",\"obr4Code\": \"obr4Code\",\"obr4CodeSystem\": \"obr4CodeSystem\",\"program\": \"program\",\"source\": \"source\",\"updatedDate\": \"2020-03-26T19:41:36.061Z\"}";
    String json_2 =
        "{\"id\": \"2\",\"obr4Code\": \"obr4Code\",\"obr4CodeSystem\": \"obr4CodeSystem\",\"program\": \"program\",\"source\": \"source\",\"updatedDate\": \"2020-03-26T19:41:36.061Z\"}";

    String json_list = "" + "[" + json_1 + "," + json_2 + "]";

    Order[] list = new ObjectMapper().reader(Order[].class).readValue(json_list);

    System.out.println(list[0].getId());
    System.out.println(list[0].getObr4Code());
    System.out.println(list[0].getObr4CodeSystem());
    System.out.println(list[0].getProgram());
    System.out.println(list[0].getSource());
    System.out.println(list[0].getUpdatedDate());

    System.out.println(list[1].getId());
    System.out.println(list[1].getObr4Code());
    System.out.println(list[1].getObr4CodeSystem());
    System.out.println(list[1].getProgram());
    System.out.println(list[1].getSource());
    System.out.println(list[1].getUpdatedDate());

    // serialize list
    String result_list = new ObjectMapper().writeValueAsString(list);
    System.out.println(result_list);
  }

}
