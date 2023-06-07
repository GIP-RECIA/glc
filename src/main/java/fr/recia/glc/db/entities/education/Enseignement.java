package fr.recia.glc.db.entities.education;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"code", "matiere", "source"})
})
@Getter
@Setter
public class Enseignement extends AbstractSimpleEntity {

  /**
   * Code de la discipline.
   */
  @Column(/*nullable = false,*/length = IntConst.I20)
  private String code;
  /**
   * Année scolaire de validité de l'objet.
   * Année à la rentrée de septembre.
   */
  @Temporal(TemporalType.DATE)
  private Date anneeScolaire;
  /**
   * Libellé de la matière enseignée.
   */
  @Column(nullable = false, length = IntConst.I128)
  private String matiere;
  /**
   * Source d'alimentation de la discipline de poste.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;
  /**
   * Code matière de rattachement National, si matière académique.
   */
  @Column(length = IntConst.I20)
  private String codeRattach;

  /**
   * Constructeur de l'objet Enseignement.java.
   */
  public Enseignement() {
    super();
  }

  /**
   * Constructeur de l'objet Enseignement.java.
   *
   * @param matiere Libellé de la matière enseignée.
   * @param source  Source d'alimentation de l'objet.
   */
  public Enseignement(final String matiere, final String source) {
    super();
    this.matiere = matiere;
    this.source = source;
  }

  /**
   * Constructeur de l'objet Enseignement.java.
   *
   * @param code    Code de la matière.
   * @param matiere Libellé de la matière enseignée.
   * @param source  Source d'alimentation de l'objet.
   */
  public Enseignement(final String code, final String matiere, final String source) {
    super();
    this.code = code;
    this.matiere = matiere;
    this.source = source;
  }

  @Override
  public String toString() {
    return "Enseignement [" +
      super.toString() + ", " +
      this.code + ", " +
      this.matiere + ", " +
      this.source + ", " +
      this.codeRattach + ", " +
      this.anneeScolaire +
      "]";
  }

}
