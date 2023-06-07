package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.enums.CategorieFonction;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.Etablissement;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class FonctionMEF extends AFonction {

  /**
   * Relation unidirectionnelle.
   */
  @ManyToOne
  @JoinColumn(name = "etablissement_fk")
  private Etablissement etablissement;

  /**
   * Relation unidirectionnelle.
   */
  @ElementCollection(fetch = FetchType.LAZY)
  @JoinTable(
    name = "fonctions_mefs",
    joinColumns = @JoinColumn(name = "FONCTIONMEF_ID", referencedColumnName = "ID")
  )
  private Set<MappingFonctionMEFMEF> mefs = new HashSet<>();

  /**
   * Constructeur de l'objet FonctionMEF.java.
   */
  public FonctionMEF() {
    super();
    this.setCategorie(CategorieFonction.MEF);
  }

  /**
   * Constructeur de l'objet FonctionMEF.java.
   *
   * @param mefs          Liste des mefs.
   * @param etablissement l'établissement associé à l'enseignement de ces mef
   * @param personne      la personne ayant la fonction d'enseigner associé à ce mefs.
   * @param source        Source d'alimentation gérant cette fonction.
   */
  public FonctionMEF(final Set<MappingFonctionMEFMEF> mefs, final Etablissement etablissement,
                     final APersonne personne, final String source) {
    super(CategorieFonction.MEF, personne, source);
    this.mefs = mefs;
    this.etablissement = etablissement;
  }

  @Override
  public String toString() {
    return "FonctionMEF [" +
      super.toString() + ", " +
      this.etablissement + ", " +
      this.mefs +
      "]";
  }

}
