package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@Setter
public class AnneeScolaire extends AbstractEntity {

  /**
   * Informe de l'année en cours, une seule insertion par année possible,
   * année à la rentrée (de septembre).
   */
  @Column(unique = true, nullable = false)
  @Temporal(TemporalType.DATE)
  private Date anneeEnCours;
  /**
   * Date exacte du passage à l'année suivante pour les insertions.
   */
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date passageAnneeSuivante;
  /**
   * Date de fin des autorisations des accés pour les utilisateurs de l'année scolaire précédente.
   * Si null on y place la date de passageAnneeSuivante.
   */
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date finAutorisation;
  /**
   * Booleen indiquant l'insertion du complet des établissements en début d'année scolaire.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean etabMAJ;

  /**
   * Constructeur de l'objet AnneeScolaire.java.
   */
  public AnneeScolaire() {
    super();
  }

  /**
   * Constructeur de l'objet AnneeScolaire.java.
   *
   * @param anneeEnCours         Année scolaire en cours, année à la rentrée (de septembre).
   * @param passageAnneeSuivante Date de passage des insertions à l'année suivante.
   * @param finAutorisation      Date de fin des autorisations des accés
   *                             pour les utilisateurs de l'année scolaire précédente.
   */
  public AnneeScolaire(final Date anneeEnCours, final Date passageAnneeSuivante, final Date finAutorisation) {
    super();
    this.anneeEnCours = anneeEnCours;
    this.passageAnneeSuivante = passageAnneeSuivante;
    this.finAutorisation = finAutorisation;
  }

  @Override
  public String toString() {
    return "AnneeScolaire [" +
      super.toString() + ", " +
      this.anneeEnCours + ", " +
      this.passageAnneeSuivante + ", " +
      this.finAutorisation + ", " +
      this.etabMAJ +
      "]";
  }

}
