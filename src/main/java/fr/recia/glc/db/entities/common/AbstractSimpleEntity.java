package fr.recia.glc.db.entities.common;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractSimpleEntity implements Serializable {

  /**
   * Id de l'objet géré de façon automatique avec clé généré
   * si non renseigné lors du persist.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Constructeur de l'objet AbstractEntity.java.
   */
  public AbstractSimpleEntity() {
    super();
  }

  @Override
  public String toString() {
    return "AbstractSimpleEntity [" + this.id + "]";
  }

}
