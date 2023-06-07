package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
public class TypeDomaine extends AbstractSimpleEntity {

  /**
   * Nom du domaine d'exercice.
   */
  private String libelleDomaine;

  /**
   * Constructeur de l'objet TypeDomaine.java.
   */
  public TypeDomaine() {
    super();
  }

  /**
   * Constructeur de l'objet TypeDomaine.java.
   *
   * @param libelle Nom du domaine d'exercice.
   */
  public TypeDomaine(final String libelle) {
    super();
    this.libelleDomaine = libelle;
  }

  @Override
  public String toString() {
    return "TypeDomaine [" +
      super.toString() + ", " +
      this.libelleDomaine +
      "]";
  }

}
