package gov.nist.hit.elr.aphl.domain;

public enum Program {

  ARLN, PHLIP, VPD, RABIES, LOI, APHL_ARLN, APHL_PHLIP, APHL_VPD, APHL_RABIES;

  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
