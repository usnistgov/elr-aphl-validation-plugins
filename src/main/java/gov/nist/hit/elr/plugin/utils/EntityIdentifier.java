package gov.nist.hit.elr.plugin.utils;

public class EntityIdentifier {

  private String entityIdentifier;
  private String namespaceID;
  private String universalID;
  private String universalIDType;

  public EntityIdentifier(String entityIdentifier, String namespaceID, String universalID,
      String universalIDType) {
    super();
    this.entityIdentifier = entityIdentifier;
    this.namespaceID = namespaceID;
    this.universalID = universalID;
    this.universalIDType = universalIDType;
  }

  public String getEntityIdentifier() {
    return entityIdentifier;
  }

  public String getNamespaceID() {
    return namespaceID;
  }

  public String getUniversalID() {
    return universalID;
  }

  public String getUniversalIDType() {
    return universalIDType;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((entityIdentifier == null) ? 0 : entityIdentifier.hashCode());
    result = prime * result + ((namespaceID == null) ? 0 : namespaceID.hashCode());
    result = prime * result + ((universalID == null) ? 0 : universalID.hashCode());
    result = prime * result + ((universalIDType == null) ? 0 : universalIDType.hashCode());
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
    EntityIdentifier other = (EntityIdentifier) obj;
    if (entityIdentifier == null) {
      if (other.entityIdentifier != null)
        return false;
    } else if (!entityIdentifier.equals(other.entityIdentifier))
      return false;
    if (namespaceID == null) {
      if (other.namespaceID != null)
        return false;
    } else if (!namespaceID.equals(other.namespaceID))
      return false;
    if (universalID == null) {
      if (other.universalID != null)
        return false;
    } else if (!universalID.equals(other.universalID))
      return false;
    if (universalIDType == null) {
      if (other.universalIDType != null)
        return false;
    } else if (!universalIDType.equals(other.universalIDType))
      return false;
    return true;
  }

}
