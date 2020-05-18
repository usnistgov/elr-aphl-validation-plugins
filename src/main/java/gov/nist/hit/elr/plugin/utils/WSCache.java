package gov.nist.hit.elr.plugin.utils;

import java.util.HashMap;
import java.util.Map;

public class WSCache {

  private static WSCache single_instance = null;

  private static final int expiration = 300;

  public Map<String, Cache<String>> cache;


  private WSCache() {
    cache = new HashMap<String, Cache<String>>();
  }

  // static method to create instance of Singleton class
  public static WSCache getInstance() {
    if (single_instance == null)
      single_instance = new WSCache();
    return single_instance;
  }

  public Map<String, Cache<String>> getCache() {
    return cache;
  }

  public static int getExpiration() {
    return expiration;
  }

  public void clearCache() {
    cache = new HashMap<String, Cache<String>>();
  }

  // tests
  // observations
  // orders
  // valuesets
  // expanded value sets



}
