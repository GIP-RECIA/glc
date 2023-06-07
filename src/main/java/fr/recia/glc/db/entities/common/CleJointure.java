package fr.recia.glc.db.entities.common;

import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CleJointure implements Serializable {

  /**
   * Source de l'alimentation.
   */
  @Column(length = IntConst.ISOURCE)
  private String source;
  /**
   * Clé fournie lors l'alimentation.
   */
  @Column(length = IntConst.ICLE)
  private String cle;

  /**
   * Constructeur de l'objet CleJointure.java.
   */
  public CleJointure() {
    super();
  }

  /**
   * Constructeur de l'objet CleJointure.java avec les champs obligatoires.
   *
   * @param source Source de l'alimentation :
   *               Annuaire Fédérateur(AF), ENT ou autres sources non connues actuellement.
   * @param cle    clé identifiante, donc unique, de l'objet fourni lors de l'alimentation.
   */
  public CleJointure(final String source, final String cle) {
    super();
    this.source = source;
    this.cle = cle;
  }

  @Override
  public String toString() {
    return "CleJointure [" +
      this.source + ", " +
      this.cle +
      "]";
  }

}
