package fr.recia.glc.db.entities.common;

import fr.recia.glc.db.entities.common.enums.ExternalIdSource;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
@Getter
@Setter
public class ExternalId {

  /**
   * Identifiant externe.
   */
  @Column(nullable = false)
  private String id;
  /**
   * Destinataire de l'identifiant.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20, nullable = false)
  private ExternalIdSource destinataire;

  /**
   * Constructeur de l'objet ExternalId.java.
   */
  public ExternalId() {
    super();
  }

  /**
   * Constructeur de l'objet Mail.java.
   *
   * @param id           Identifiant.
   * @param destinataire Le destinataire utilisant l'identifiant.
   */
  public ExternalId(final String id, final ExternalIdSource destinataire) {
    super();
    this.id = id;
    this.destinataire = destinataire;
  }

  @Override
  public String toString() {
    return "ExternalId [id=" +
      this.id + ", destinataire=" +
      this.destinataire +
      "]";
  }

}
