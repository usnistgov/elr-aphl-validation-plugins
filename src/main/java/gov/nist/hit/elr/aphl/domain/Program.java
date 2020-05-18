package gov.nist.hit.elr.aphl.domain;

public enum Program {

  ARLN, PHLIP, APHL_ARLN, APHL_PHLIP;

  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
