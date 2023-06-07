package fr.recia.glc.db.entities.fonction;

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
  @UniqueConstraint(columnNames = {"codefiliere", "source"})
})
@Getter
@Setter
public class TypeFonctionFiliere extends AbstractSimpleEntity {

  /**
   * Code de la fonction filière.
   */
  @Column(nullable = false, length = IntConst.I30)
  private String codeFiliere;
  /**
   * Libellé de la fonction filière.
   */
  private String libelleFiliere;
  /**
   * Source d'alimentation de la fonction filière.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  /**
   * Constructeur de l'objet TypeFonctionFiliere.java.
   */
  public TypeFonctionFiliere() {
    super();
  }

  /**
   * Constructeur de l'objet TypeFonctionFiliere.java.
   *
   * @param codeFiliere    Code de la fonction filière.
   * @param libelleFiliere Libellé de la fonction filière.
   * @param source         Source d'alimentation de l'objet.
   */
  public TypeFonctionFiliere(final String codeFiliere, final String libelleFiliere, final String source) {
    super();
    this.codeFiliere = codeFiliere;
    this.libelleFiliere = libelleFiliere;
    this.source = source;
  }

  @Override
  public String toString() {
    return "TypeFonctionFiliere [" +
      super.toString() + ", " +
      this.codeFiliere + ", " +
      this.libelleFiliere + ", " +
      this.source +
      "]";
  }

}
