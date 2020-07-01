package gov.nist.hit.elr.plugin.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.nist.hit.elr.aphl.domain.Observation;
import gov.nist.hit.elr.aphl.domain.Order;
import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.aphl.domain.Result;
import gov.nist.hit.elr.aphl.domain.Test;
import gov.nist.hit.elr.aphl.domain.WebService;
import gov.nist.hit.elr.aphl.domain.vocab.ExpandedValueSet;
import gov.nist.hit.elr.aphl.domain.vocab.ValueSet;
import gov.nist.hit.elr.aphl.domain.vocab.ValueSets;

public class WSUtils {

  // private static Logger logger = Logger.getLogger(WSUtils.class.getName());

  private final HttpClient httpClient;

  public WSUtils() {
    System.out.println("WSUtils - 1");
    httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    System.out.println("WSUtils - 2");

  }

  // public static void main(String[] args)
  // throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
  // WSUtils obj = new WSUtils();
  // List<Observation> data = obj.getObservations(Program.ARLN);
  // System.out.println(data);
  // }

  public List<ExpandedValueSet> getExpandedValueSet(Program program, String valueSetName)
      throws IOException, InterruptedException, URISyntaxException {

    List<ExpandedValueSet> resultList = new ArrayList<ExpandedValueSet>();

    ValueSets valueSets = getValueSets(program);
    Set<String> normalizedNames = valueSets.normalizeValueSetName(valueSetName);

    for (String normalizedName : normalizedNames) {
      String resource = StringUtils.join("ValueSet", "/", normalizedName, "/", "$expand");
      String json = sendGET(WebService.VALUESETS_WS, program, resource);
      ObjectMapper objectMapper = new ObjectMapper();
      ExpandedValueSet result =
          objectMapper.readValue(json, new TypeReference<ExpandedValueSet>() {});
      resultList.add(result);
    }
    return resultList;
  }

  public List<ValueSet> getValueSet(Program program, String valueSetName)
      throws IOException, InterruptedException, URISyntaxException {

    List<ValueSet> resultList = new ArrayList<ValueSet>();

    // check that the value set exists first
    ValueSets valueSets = getValueSets(program);
    Set<String> normalizedNames = valueSets.normalizeValueSetName(valueSetName);

    for (String normalizedName : normalizedNames) {
      String resource = StringUtils.join("ValueSet", "/", normalizedName);
      String json = sendGET(WebService.VALUESETS_WS, program, resource);
      ObjectMapper objectMapper = new ObjectMapper();
      ValueSet result = objectMapper.readValue(json, new TypeReference<ValueSet>() {});
      resultList.add(result);
    }
    return resultList;
  }

  public ValueSets getValueSets(Program program)
      throws IOException, InterruptedException, URISyntaxException {
    String json = sendGET(WebService.VALUESETS_WS, program, "ValueSet");
    ObjectMapper objectMapper = new ObjectMapper();
    ValueSets result = objectMapper.readValue(json, new TypeReference<ValueSets>() {});
    return result;
  }

  public List<Order> getOrders(Program program)
      throws IOException, InterruptedException, URISyntaxException {
    String json = sendGET(WebService.APHL_WS, program, "orders");
    ObjectMapper objectMapper = new ObjectMapper();
    Result<Order> result = objectMapper.readValue(json, new TypeReference<Result<Order>>() {});
    return result.getData();
  }

  public List<Observation> getObservations(Program program)
      throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
    String json = sendGET(WebService.APHL_WS, program, "observations");
    ObjectMapper objectMapper = new ObjectMapper();
    Result<Observation> result =
        objectMapper.readValue(json, new TypeReference<Result<Observation>>() {});
    return result.getData();
  }

  public List<Test> getTests(Program program)
      throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
    String json = sendGET(WebService.APHL_WS, program, "tests");
    ObjectMapper objectMapper = new ObjectMapper();
    Result<Test> result = objectMapper.readValue(json, new TypeReference<Result<Test>>() {});
    return result.getData();
  }

  private String sendGET(WebService url, Program program, String resource)
      throws IOException, InterruptedException, URISyntaxException {
    System.out.println("je rentre dans sendGET");
    URI uri = new URI(StringUtils.join(url, "/", program, "/", resource.replaceAll(" ", "%20")));
    WSCache cache = WSCache.getInstance();
    // cache.clearCache();
    if (cache.getCache().containsKey(uri.toString())) {
      Cache<String> cached = cache.getCache().get(uri.toString());
      Instant cachedInstant = cached.getInstant();
      Instant now = Instant.now();
      if (now.isBefore(cachedInstant.plusSeconds(WSCache.getExpiration()))) {
        // the data was cached less than xxx minutes ago, use cached data !
        return cached.getCachedObject();
      }
    }
    // get data from webservice

    HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
    System.out.println("je send le GET");
    System.out.println(uri.toString());
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("je recois la reponse");
    // save response in cache
    Cache<String> value = new Cache<String>(response.body());
    cache.getCache().put(uri.toString(), value);
    System.out.println("je sors de sendGET");
    return response.body();
  }
}
