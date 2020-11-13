package gov.nist.hit.elr.plugin.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;

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

  private static Logger logger = Logger.getLogger(WSUtils.class.getName());

  private Client client;
  public WSUtils() {
    ClientConfig config = new ClientConfig();
    client = ClientBuilder.newClient(config);
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
    URI uri = new URI(StringUtils.join(url, "/", program, "/", resource.replaceAll(" ", "%20")));
    logger.debug(uri);
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
    WebTarget target = client.target(uri);
    String response = target.
        request(MediaType.APPLICATION_JSON).
        get(String.class);
       
    // save response in cache
    Cache<String> value = new Cache<String>(response);
    cache.getCache().put(uri.toString(), value);
    return response;
  }
}
