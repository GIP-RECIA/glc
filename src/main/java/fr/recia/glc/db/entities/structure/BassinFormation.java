package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"code", "source"})
})
@Getter
@Setter
public class BassinFormation extends AbstractSimpleEntity {

  /**
   * Code du bassin de formation.
   */
  @Column(nullable = false, length = IntConst.I10)
  private String code;
  /**
   * Nom du bassin de formation.
   */
  private String nom;
  /**
   * Source d'alimentation du bassin de formation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  /**
   * Constructeur de l'objet BassinFormation.java.
   */
  public BassinFormation() {
    super();
  }

  /**
   * Constructeur de l'objet BassinFormation.java.
   *
   * @param code   Code du bassin de formation.
   * @param nom    Nom du bassin de formation.
   * @param source Source d'alimentation de l'objet.
   */
  public BassinFormation(final String code, final String nom, final String source) {
    super();
    this.code = code;
    this.nom = nom;
    this.source = source;
  }

  @Override
  public String toString() {
    return "BassinFormation [" +
      super.toString() + ", " +
      this.code + ", " +
      this.nom + ", " +
      this.source +
      "]";
  }

}
