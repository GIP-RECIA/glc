package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public abstract class AGroupeOfAPersonne extends AGroupe {

  /**
   * Relation bidirectionnelle. Liste des personnes membres du groupe.
   */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.groupe")
  private Set<MappingAGroupeAPersonne> membres = new HashSet<>();

  /**
   * Constructeur de l'objet AGroupeOfAPersonne.java.
   */
  public AGroupeOfAPersonne() {
    super();
  }

  /**
   * Constructeur de l'objet AGroupeOfAPersonne.java.
   *
   * @param cn        Nom unique de groupe, peut servir comme identifiant.
   * @param categorie Type de groupe.
   * @param membres   Liste des membres du groupe.
   * @param source    Source ayant créé l'objet.
   */
  public AGroupeOfAPersonne(final String cn, final CategorieGroupe categorie,
                            final Set<MappingAGroupeAPersonne> membres, final String source) {
    super(cn, categorie, source);
    this.membres = membres;
  }

  @Override
  public String toString() {
    return "AGroupeOfAPersonne [" + super.toString() + "]";
  }

}
