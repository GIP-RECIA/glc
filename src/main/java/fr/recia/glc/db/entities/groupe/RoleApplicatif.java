package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.application.Application;
import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class RoleApplicatif extends AGroupeOfAPersonne {

  /**
   * Relation unidirectionnelle.
   * Liste des profils utiles au rôle applicatif.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(name = "roles_applicatifs_profils",
    joinColumns = @JoinColumn(name = "ROLEAPPLICATIF_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "PROFIL_ID", referencedColumnName = "ID")
  )
  private Set<Profil> profils = new HashSet<>();
  /**
   * Relation bidirectionnelle.
   * Application pour laquelle le role applicatif est défini.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "APPLICATION_ID")
  private Application proprietaire;

  /**
   * Constructeur de l'objet RoleApplicatif.java.
   */
  public RoleApplicatif() {
    super();
    this.setCategorie(CategorieGroupe.Role_applicatif);
  }

  /**
   * Constructeur de l'objet RoleApplicatif.java.
   *
   * @param cn          Nom unique de groupe, peut servir comme identifiant.
   * @param membres     Liste des membres du groupe en dehors des profils.
   * @param application Application pour laquelle le role applicatif est défini.
   * @param source      Source ayant créé l'objet.
   */
  public RoleApplicatif(final String cn, final Set<MappingAGroupeAPersonne> membres,
                        final Application application, final String source) {
    super(cn, CategorieGroupe.Role_applicatif, membres, source);
    this.proprietaire = application;
  }

  @Override
  public String toString() {
    return "RoleApplicatif [" +
      super.toString() + ", " +
      this.profils +
      "]";
  }

}
