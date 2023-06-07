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
public class Groupe extends AGroupeOfFoncClasseGroupe {

  /**
   * Relation unidirectionnelle.
   * Liste des classes associées au groupe.
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JoinTable(name = "groupes_classes",
    joinColumns = @JoinColumn(name = "GROUPE_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "CLASSE_ID", referencedColumnName = "ID")
  )
  private Set<Classe> relationsClasses = new HashSet<>();

  /**
   * Constructeur de l'objet Groupe.java.
   */
  public Groupe() {
    super();
    this.setCategorie(CategorieGroupe.Groupe);
  }

  /**
   * Constructeur de l'objet Groupe.java.
   *
   * @param cn           Nom unique du groupe, peut servir comme identifiant.
   * @param membres      Liste des personnes membre du groupe.
   * @param proprietaire Etablissement ayant défini ce groupe.
   * @param source       Source ayant créé l'objet.
   */
  public Groupe(final String cn, final Set<MappingAGroupeAPersonne> membres,
                final Etablissement proprietaire, final String source) {
    super(cn, CategorieGroupe.Groupe, membres, proprietaire, source);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Groupe [");
    sb.append(super.toString());
    if (this.relationsClasses != null && !this.relationsClasses.isEmpty()) {
      sb.append(", Liste de classes liées : [");
      for (Classe classe : this.relationsClasses) {
        sb.append(classe.getCn());
        sb.append(", ");
      }
      sb.delete(sb.length() - 2, sb.length());
      sb.append("]");
    }
    sb.append("]");
    return sb.toString();
  }

}
