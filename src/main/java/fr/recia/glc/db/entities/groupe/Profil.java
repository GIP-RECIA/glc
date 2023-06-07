package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.application.Application;
import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import fr.recia.glc.db.entities.structure.AStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Profil extends AGroupeOfAPersonne {

  /**
   * Définition de la règle de peuplement.
   */
  private String reglePeuplement;

  /**
   * Relation bidirectionnelle.
   * Structure ayant défini le profil.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "structure_fk")
  private AStructure proprietaire;
  /**
   * Relation bidirectionnelle.
   * Listes des applications ayant besoin de ce profil.
   */
  @ManyToMany(mappedBy = "profils", fetch = FetchType.LAZY)
  private Set<Application> applications = new HashSet<>();

  /**
   * Constructeur de l'objet Profil.java.
   */
  public Profil() {
    super();
    this.setCategorie(CategorieGroupe.Profil);
  }

  /**
   * Constructeur de l'objet Profil.java.
   *
   * @param cn              Nom unique de groupe, peut servir comme identifiant.
   * @param membres         Liste des membres du groupe obtenu à partir de la règle.
   * @param reglePeuplement Régle de peuplement du groupe.
   * @param source          Source ayant créé l'objet.
   */
  public Profil(final String cn, final Set<MappingAGroupeAPersonne> membres,
                final String reglePeuplement, final String source) {
    super(cn, CategorieGroupe.Profil, membres, source);
    this.reglePeuplement = reglePeuplement;
  }

  @Override
  public String toString() {
    return "Profil [" +
      super.toString() + ", " +
      this.reglePeuplement +
      "]";
  }

}
