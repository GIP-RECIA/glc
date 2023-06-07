package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.common.enums.CategorieFonction;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class AFonction extends AbstractEntity {

  /**
   * Categorie de la fonction.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private CategorieFonction categorie;
  /**
   * Source d'alimentation de la fonction.
   */
  @Column(length = IntConst.ISOURCE)
  private String source;

  /**
   * Relation bidirectionnelle.
   *
   * @see fr.recia.glc.db.entities.personne.APersonne
   */
  @ManyToOne
  @JoinColumn(name = "personne_fk")
  private APersonne personne;

  /**
   * Constructeur de l'objet AFonction.java.
   */
  public AFonction() {
    super();
  }

  /**
   * Constructeur de l'objet AFonction.java.
   *
   * @param categorie Categorie de la fonction.
   * @param personne  Personne ayant cette fonction.
   * @param source    Source d'alimentation gérant cette fonction.
   */
  public AFonction(final CategorieFonction categorie, final APersonne personne, final String source) {
    super();
    this.categorie = categorie;
    this.source = source;
    this.personne = personne;
  }

  @Override
  public String toString() {
    return "AFonction [" +
      super.toString() + ", " +
      this.categorie + ", " +
      this.source +
      "]";
  }

}
