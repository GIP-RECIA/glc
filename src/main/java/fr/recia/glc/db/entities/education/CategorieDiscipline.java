package fr.recia.glc.db.entities.education;

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
public class CategorieDiscipline extends AbstractSimpleEntity {

  /**
   * Code de la catégorie de discipline.
   */
  @Column(nullable = false, length = IntConst.I60)
  private String code;
  /**
   * Libellé de la catégorie de discipline de poste.
   */
  private String pivotDiscipline;
  /**
   * Source d'alimentation de la catégorie de discipline de poste.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  /**
   * Constructeur de l'objet CategorieDiscipline.java.
   */
  public CategorieDiscipline() {
    super();
  }

  /**
   * Constructeur de l'objet CategorieDiscipline.java.
   *
   * @param code            Code de la catégorie de discipline.
   * @param pivotDiscipline Libellé de la catégorie de discipline de poste.
   * @param source          La source d'alimentation de l'objet.
   */
  public CategorieDiscipline(final String code, final String pivotDiscipline, final String source) {
    super();
    this.code = code;
    this.pivotDiscipline = pivotDiscipline;
    this.source = source;
  }

  @Override
  public String toString() {
    return "CategorieDiscipline [" +
      super.toString() + ", " +
      this.code + ", " +
      this.pivotDiscipline + ", " +
      this.source +
      "]";
  }

}
