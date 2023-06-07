package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@Setter
public abstract class AGroupe extends AbstractEntity {

  /**
   * Type de groupe.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I30)
  private CategorieGroupe categorie;
  /**
   * Année scolaire de validité de l'objet.
   * Année à la rentrée de septembre.
   */
  @Temporal(TemporalType.DATE)
  private Date anneeScolaire;
  /**
   * Nom unique de groupe, peut servir comme identifiant au sein d'un établissement.
   */
  private String cn;
  /**
   * Description du groupe.
   */
  private String description;
  /**
   * Source ayant créé le groupe.
   */
  @Column(length = IntConst.ISOURCE)
  private String source;

  /**
   * Constructeur de l'objet AGroupe.java.
   */
  public AGroupe() {
    super();
  }

  /**
   * Constructeur de l'objet AGroupe.java.
   *
   * @param cn        Nom unique de groupe, peut servir comme identifiant.
   * @param categorie Type de groupe.
   * @param source    Source ayant créé l'objet.
   */
  public AGroupe(final String cn, final CategorieGroupe categorie, final String source) {
    super();
    this.cn = cn;
    this.categorie = categorie;
    this.source = source;
  }

  @Override
  public String toString() {
    return "AGroupe [" +
      super.toString() + ", " +
      this.categorie + ", " +
      this.cn + ", " +
      this.source + ", " +
      this.anneeScolaire + ", " +
      this.description +
      "]";
  }

}
