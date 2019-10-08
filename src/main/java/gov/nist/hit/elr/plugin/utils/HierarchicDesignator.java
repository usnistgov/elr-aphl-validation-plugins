package gov.nist.hit.elr.plugin.utils;

public class HierarchicDesignator {

  private String namespaceId;
  private String universalId;
  private String universalIdType;

  public HierarchicDesignator(String namespaceId, String universalId, String universalIdType) {
    super();
    this.namespaceId = namespaceId;
    this.universalId = universalId;
    this.universalIdType = universalIdType;
  }

  public String getNamespaceId() {
    return namespaceId;
  }

  public String getUniversalId() {
    return universalId;
  }

  public String getUniversalIdType() {
    return universalIdType;
  }

  public HierarchicDesignator normalize() {
    return new HierarchicDesignator(namespaceId.toLowerCase(), universalId.toLowerCase(),
        universalIdType.toLowerCase());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((namespaceId == null) ? 0 : namespaceId.hashCode());
    result = prime * result + ((universalId == null) ? 0 : universalId.hashCode());
    result = prime * result + ((universalIdType == null) ? 0 : universalIdType.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HierarchicDesignator other = (HierarchicDesignator) obj;
    if (namespaceId == null) {
      if (other.namespaceId != null)
        return false;
    } else if (!namespaceId.equals(other.namespaceId))
      return false;
    if (universalId == null) {
      if (other.universalId != null)
        return false;
    } else if (!universalId.equals(other.universalId))
      return false;
    if (universalIdType == null) {
      if (other.universalIdType != null)
        return false;
    } else if (!universalIdType.equals(other.universalIdType))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "HierarchicDesignator [namespaceId=" + namespaceId + ", universalId=" + universalId
        + ", universalIdType=" + universalIdType + "]";
  }

  public String prettyPrint() {
    return "[Namespace ID='" + namespaceId + "', Universal ID='" + universalId
        + "', Universal ID Type='" + universalIdType + "']";
  }

}
