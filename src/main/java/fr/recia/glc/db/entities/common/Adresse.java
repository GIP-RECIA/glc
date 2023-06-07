package fr.recia.glc.db.entities.common;

import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class Adresse implements Serializable {

  /**
   * Champ libre d'une adresse.
   */
  private String adresse;
  /**
   * Code postal.
   */
  @Column(length = IntConst.I40)
  private String codePostal;
  /**
   * Nom de la ville.
   */
  private String ville;
  /**
   * Boite postale dans le cas d'une structure.
   */
  @Column(length = IntConst.I40)
  private String boitePostale;
  /**
   * Pays.
   */
  private String pays;

  /**
   * Constructeur de l'objet Adresse.java.
   */
  public Adresse() {
    super();
  }

  /**
   * Constructeur de l'objet Adresse.java.
   *
   * @param adresse    Champ libre d'une adresse.
   * @param codePostal Code postal.
   * @param ville      Nom de la ville.
   */
  public Adresse(final String adresse, final String codePostal, final String ville) {
    super();
    this.adresse = adresse;
    this.codePostal = codePostal;
    this.ville = ville;
  }

  @Override
  public String toString() {
    return "Adresse [" +
      this.adresse + ", " +
      this.codePostal + ", " +
      this.ville + ", " +
      this.boitePostale + ", " +
      this.pays +
      "]";
  }

}
