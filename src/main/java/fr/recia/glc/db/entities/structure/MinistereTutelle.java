package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
public class MinistereTutelle extends AbstractSimpleEntity {

  /**
   * Nom du ministère.
   */
  private String ministere;

  /**
   * Constructeur de l'objet MinistereTutelle.java.
   */
  public MinistereTutelle() {
    super();
  }

  /**
   * Constructeur de l'objet MinistereTutelle.java.
   *
   * @param ministere Ministère de tutelle.
   */
  public MinistereTutelle(final String ministere) {
    super();
    this.ministere = ministere;
  }

  @Override
  public String toString() {
    return "MinistereTutelle [" +
      super.toString() + ", " +
      this.ministere +
      "]";
  }

}
