package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import fr.recia.glc.db.entities.structure.Etablissement;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class GroupementEtablissements extends AGroupe {

  /**
   * Relation bidirectionnelle.
   * Liste des Etablissements membres du groupe.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(name = "groupements_etablissements",
    joinColumns = @JoinColumn(name = "GROUPEMENTETABLISSEMENT_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ETABLISSEMENT_ID", referencedColumnName = "ID")
  )
  private Set<Etablissement> membres = new HashSet<>();

  /**
   * Constructeur de l'objet GroupementEtablissements.java.
   */
  public GroupementEtablissements() {
    super();
    this.setCategorie(CategorieGroupe.Groupement_etablissement);
  }

  /**
   * Constructeur de l'objet GroupementEtablissements.java.
   *
   * @param cn      Nom unique de groupe, peut servir comme identifiant.
   * @param membres Liste des Etablissements membres du groupe.
   * @param source  Source ayant créé l'objet.
   */
  public GroupementEtablissements(final String cn, final Set<Etablissement> membres, final String source) {
    super(cn, CategorieGroupe.Groupement_etablissement, source);
    this.membres = membres;
  }

  @Override
  public String toString() {
    return "GroupementEtablissement [" + super.toString() + "]";
  }

}
