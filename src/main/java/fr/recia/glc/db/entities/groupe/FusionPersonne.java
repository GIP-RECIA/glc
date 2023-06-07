package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.common.enums.CategorieGroupe;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import java.util.Set;

@Entity
@Getter
@Setter
public class FusionPersonne extends AGroupeOfAPersonne {

  /**
   * Constructeur de l'objet FusionPersonne.java.
   */
  public FusionPersonne() {
    super();
    this.setCategorie(CategorieGroupe.Fusion);
  }

  /**
   * Constructeur de l'objet FusionPersonne.java.
   *
   * @param cn      Nom du groupe de fusion, peut servir à définir le compte principal.
   * @param membres Liste des personnes fusionnant.
   * @param source  Source ayant créé l'objet.
   */
  public FusionPersonne(final String cn, final Set<MappingAGroupeAPersonne> membres, final String source) {
    super(cn, CategorieGroupe.Fusion, membres, source);
  }

  @Override
  public String toString() {
    return "FusionPersonne [" + super.toString() + "]";
  }

}
