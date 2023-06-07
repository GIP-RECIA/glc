package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import fr.recia.glc.db.entities.education.MEF;
import fr.recia.glc.db.entities.groupe.AGroupeOfFoncClasseGroupe;
import fr.recia.glc.db.entities.groupe.MappingAGroupeAPersonne;
import fr.recia.glc.db.entities.structure.Etablissement;
import lombok.Getter;
import lombok.Setter;

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
public class Classe extends AGroupeOfFoncClasseGroupe {

  /**
   * Mefs associé à la classe.
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "classes_mefs",
    joinColumns = @JoinColumn(name = "CLASSE_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "MEF_ID", referencedColumnName = "ID")
  )
  private Set<MEF> mefs = new HashSet<>();

  /**
   * Constructeur de l'objet Classe.java.
   */
  public Classe() {
    super();
    this.setCategorie(CategorieGroupe.Classe);
  }

  /**
   * Constructeur de l'objet Classe.java.
   *
   * @param cn           Nom unique de la classe, peut servir comme identifiant.
   * @param membres      Liste des personnes membre de la classe.
   * @param proprietaire Etablissement ayant défini cette classe.
   * @param source       Source ayant créé l'objet.
   */
  public Classe(final String cn, final Set<MappingAGroupeAPersonne> membres,
                final Etablissement proprietaire, final String source) {
    super(cn, CategorieGroupe.Classe, membres, proprietaire, source);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Classe [");
    sb.append(super.toString());
    if (this.mefs != null && !this.mefs.isEmpty()) {
      sb.append(", MEFs : [");
      for (MEF mef : this.mefs) {
        sb.append(mef.getCode());
        sb.append(", ");
      }
      sb.delete(sb.length() - 2, sb.length());
      sb.append("]");
    }
    sb.append("]");
    return sb.toString();
  }

}
