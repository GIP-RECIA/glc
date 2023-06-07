package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"l", "c", "xx"})
})
@Getter
@Setter
public class GenUID extends AbstractEntity {

  /**
   * Code région.
   */
  @Column(length = IntConst.I1)
  private String l;
  /**
   * Code département.
   */
  @Column(length = IntConst.I1)
  private String c;
  /**
   * Code de l'année d'insertion.
   */
  @Column(length = IntConst.I2)
  private String xx;
  /**
   * Code alphanumérique autoincrémenté, sauvegardé sous forme d'entier.
   */
  private int iiii;

  /**
   * Constructeur de l'objet GenUID.java.
   */
  public GenUID() {
    super();
  }

  /**
   * Constructeur de l'objet GenUID.java.
   *
   * @param l Code région.
   * @param c Code département.
   */
  public GenUID(final String l, final String c) {
    super();
    this.l = l;
    this.c = c;
  }

  @Override
  public String toString() {
    return "GenUID [" +
      super.toString() + ", " +
      this.l + ", " +
      this.xx + ", " +
      this.c + ", " +
      this.iiii +
      "]";
  }

}
