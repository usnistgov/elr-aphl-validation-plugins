package gov.nist.hit.elr.aphl.domain;

public enum WebService {

  APHL_WS("https://aphl.nist.gov/aphl-service/aphl"), VALUESETS_WS(
      "https://vocab.nist.gov/vocabulary-service");

  // APHL_WS("http://hit-dev-admin.nist.gov:8097/aphl-service/aphl"), VALUESETS_WS(
  // "http://hit-dev-admin.nist.gov:8095/vocabulary-service");

  private String url;

  WebService(String wsUrl) {
    this.url = wsUrl;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return url;
  }

}
