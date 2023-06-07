package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import fr.recia.glc.db.entities.fonction.FonctionClasseGroupe;
import fr.recia.glc.db.entities.structure.Etablissement;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class AGroupeOfFoncClasseGroupe extends AGroupeOfAPersonne {

  /**
   * Relation bidirectionnelle.
   * Listes des personnes ayant une fonction au sein
   * de la classe ou du groupe avec leur fonction.
   */
  @OneToMany(mappedBy = "classeGroupe", fetch = FetchType.LAZY)
  private Set<FonctionClasseGroupe> fonctions = new HashSet<>();
  /**
   * Relation bidirectionnelle.
   * Associations personne-enseignement-groupe.
   */
  @OneToMany(mappedBy = "pk.groupe", fetch = FetchType.LAZY)
  private Set<MappingAGroupeAPersonneEnseignement> ProfsEnseignements = new HashSet<>();
  /**
   * Relation unidirectionnelle.
   * Etablissement ayant défini cette classe ou ce groupe.
   */
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
  @JoinColumn(name = "etablissement_fk")
  private Etablissement proprietaire;

  /**
   * Constructeur de l'objet AGroupeOfFoncClasseGroupe.java.
   */
  public AGroupeOfFoncClasseGroupe() {
    super();
  }

  /**
   * Constructeur de l'objet AGroupeOfFoncClasseGroupe.java.
   *
   * @param cn           Nom unique de groupe, peut servir comme identifiant.
   * @param categorie    Type de groupe.
   * @param membres      Liste des personnes membres du groupe.
   * @param proprietaire Etablissement ayant défini cette classe ou ce groupe.
   * @param source       Source ayant créé l'objet.
   */
  public AGroupeOfFoncClasseGroupe(final String cn, final CategorieGroupe categorie,
                                   final Set<MappingAGroupeAPersonne> membres, final Etablissement proprietaire, final String source) {
    super(cn, categorie, membres, source);
    this.proprietaire = proprietaire;
  }

}
