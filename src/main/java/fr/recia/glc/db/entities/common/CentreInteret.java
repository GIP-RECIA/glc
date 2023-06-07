package fr.recia.glc.db.entities.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentreInteret extends AbstractSimpleEntity {

  /**
   * Libellé du centre d'intérêt.
   */
  private String libelle;

  /**
   * Constructeur de l'objet CentreInteret.java.
   */
  public CentreInteret() {
    super();
  }

  @Override
  public String toString() {
    return "CentreInteret [" +
      super.toString() + ", " +
      this.libelle +
      "]";
  }

}
