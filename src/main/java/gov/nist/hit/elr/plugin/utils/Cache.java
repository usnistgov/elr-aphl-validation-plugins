package gov.nist.hit.elr.plugin.utils;

import java.time.Instant;

public class Cache<T> {

  private Instant instant;

  private T cachedObject;



  public Cache(T cachedObject) {
    super();
    this.cachedObject = cachedObject;
    this.instant = Instant.now();
  }

  public Instant getInstant() {
    return instant;
  }

  public T getCachedObject() {
    return cachedObject;
  }

}
