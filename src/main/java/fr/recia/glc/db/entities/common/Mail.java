package fr.recia.glc.db.entities.common;

import fr.recia.glc.db.entities.common.enums.MailType;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Getter
@Setter
public class Mail extends AbstractSimpleEntity {

  /**
   * Adresse mail.
   */
  @Column(nullable = false)
  private String adresse;
  /**
   * Type de l'adresse.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private MailType type;
  /**
   * Source de l'alimentation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;
  /**
   * Si adresse validée
   */
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean validated;

  /**
   * Constructeur de l'objet Mail.java.
   */
  public Mail() {
    super();
  }

  /**
   * Constructeur de l'objet Mail.java.
   *
   * @param adresse   Adresse mail.
   * @param type      Le type d'usage.
   * @param source    La source dd'alimentation.
   * @param validated Si l'adresse a été vérifiée ou si de source sûre.
   */
  public Mail(String adresse, MailType type, String source, boolean validated) {
    super();
    this.adresse = adresse;
    this.type = type;
    this.source = source;
    this.validated = validated;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "Mail [" +
      super.toString() + ", " +
      this.adresse + ", " +
      this.type + ", " +
      this.validated + ", " +
      this.source +
      "]";
  }

}
