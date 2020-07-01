package gov.nist.hit.elr.aphl.domain;

public enum Program {

  ARLN, PHLIP, VPD, APHL_ARLN, APHL_PHLIP, APHL_VPD;

  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
