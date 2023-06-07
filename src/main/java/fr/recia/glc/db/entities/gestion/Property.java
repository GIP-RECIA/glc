package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.entities.common.enums.PropertyName;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Getter
@Setter
public class Property extends AbstractSimpleEntity {

  /**
   * Name of the property.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true, length = IntConst.I128)
  private PropertyName name;

  /**
   * Value of the property.
   */
  private String value;

  /**
   * Constructeur de l'objet Property.java.
   */
  public Property() {
    super();
  }

  /**
   * Constructeur de l'objet Property.java.
   *
   * @param name
   * @param value
   */
  public Property(final PropertyName name, final String value) {
    super();
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Property [" +
      super.toString() + ", " +
      this.name + ", " +
      this.value +
      "]";
  }

}
