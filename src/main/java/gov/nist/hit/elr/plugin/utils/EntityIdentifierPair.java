package gov.nist.hit.elr.plugin.utils;

public class EntityIdentifierPair {

  private EntityIdentifier placerAssignedIdentifier;
  private EntityIdentifier fillerAssignedIdentifier;

  public EntityIdentifierPair(EntityIdentifier placerAssignedIdentifier,
      EntityIdentifier fillerAssignedIdentifier) {
    super();
    this.placerAssignedIdentifier = placerAssignedIdentifier;
    this.fillerAssignedIdentifier = fillerAssignedIdentifier;
  }

  public EntityIdentifierPair(String placerEntityIdentifier, String placerNamespaceID,
      String placerUniversalID, String placerUniversalIDType, String fillerentityIdentifier,
      String fillerNamespaceID, String fillerUniversalID, String fillerUniversalIDType) {
    super();

    EntityIdentifier placerAssignedIdentifier = new EntityIdentifier(placerEntityIdentifier,
        placerNamespaceID, placerUniversalID, placerUniversalIDType);
    EntityIdentifier fillerAssignedIdentifier = new EntityIdentifier(fillerentityIdentifier,
        fillerNamespaceID, fillerUniversalID, fillerUniversalIDType);

    this.placerAssignedIdentifier = placerAssignedIdentifier;
    this.fillerAssignedIdentifier = fillerAssignedIdentifier;

  }

  public EntityIdentifier getPlacerAssignedIdentifier() {
    return placerAssignedIdentifier;
  }

  public void setPlacerAssignedIdentifier(String placerEntityIdentifier, String placerNamespaceID,
      String placerUniversalID, String placerUniversalIDType) {
    EntityIdentifier placerAssignedIdentifier = new EntityIdentifier(placerEntityIdentifier,
        placerNamespaceID, placerUniversalID, placerUniversalIDType);
    this.placerAssignedIdentifier = placerAssignedIdentifier;
  }

  public void setPlacerAssignedIdentifier(EntityIdentifier placerAssignedIdentifier) {
    this.placerAssignedIdentifier = placerAssignedIdentifier;
  }

  public EntityIdentifier getFillerAssignedIdentifier() {
    return fillerAssignedIdentifier;
  }

  public void setFillerAssignedIdentifier(EntityIdentifier fillerAssignedIdentifier) {
    this.fillerAssignedIdentifier = fillerAssignedIdentifier;
  }

  public void setFillerAssignedIdentifier(String fillerentityIdentifier, String fillerNamespaceID,
      String fillerUniversalID, String fillerUniversalIDType) {
    EntityIdentifier fillerAssignedIdentifier = new EntityIdentifier(fillerentityIdentifier,
        fillerNamespaceID, fillerUniversalID, fillerUniversalIDType);
    this.fillerAssignedIdentifier = fillerAssignedIdentifier;
  }

  public boolean isEmpty() {
    return placerAssignedIdentifier.isEmpty() && fillerAssignedIdentifier.isEmpty();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((fillerAssignedIdentifier == null) ? 0 : fillerAssignedIdentifier.hashCode());
    result = prime * result
        + ((placerAssignedIdentifier == null) ? 0 : placerAssignedIdentifier.hashCode());
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
    EntityIdentifierPair other = (EntityIdentifierPair) obj;
    if (fillerAssignedIdentifier == null) {
      if (other.fillerAssignedIdentifier != null)
        return false;
    } else if (!fillerAssignedIdentifier.equals(other.fillerAssignedIdentifier))
      return false;
    if (placerAssignedIdentifier == null) {
      if (other.placerAssignedIdentifier != null)
        return false;
    } else if (!placerAssignedIdentifier.equals(other.placerAssignedIdentifier))
      return false;
    return true;
  }


  @Override
  public String toString() {
    return "EntityIdentifierPair [placerAssignedIdentifier=" + placerAssignedIdentifier
        + ", fillerAssignedIdentifier=" + fillerAssignedIdentifier + "]";
  }

  public String prettyPrint() {

    String prettyPlacer = "Empty Placer";
    String prettyFiller = "Empty Filler";

    if (!placerAssignedIdentifier.isEmpty()) {
      prettyPlacer = "Placer Entity Identifier ='" + placerAssignedIdentifier.getEntityIdentifier()
          + "', Placer Namespace ID='" + placerAssignedIdentifier.getNamespaceID()
          + "', Placer Universal ID='" + placerAssignedIdentifier.getUniversalID()
          + "', Placer Universal ID Type='" + placerAssignedIdentifier.getUniversalIDType() + "'";
    }
    if (!fillerAssignedIdentifier.isEmpty()) {
      prettyFiller = "Filler Entity Identifier ='" + fillerAssignedIdentifier.getEntityIdentifier()
          + "', Filler Namespace ID='" + fillerAssignedIdentifier.getNamespaceID()
          + "', Filler Universal ID='" + fillerAssignedIdentifier.getUniversalID()
          + "', Filler Universal ID Type='" + fillerAssignedIdentifier.getUniversalIDType() + "'";
    }
    return "[" + prettyPlacer + ", " + prettyFiller + "]";
  }
}
