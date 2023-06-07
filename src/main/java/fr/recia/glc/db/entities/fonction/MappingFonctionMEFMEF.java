package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.education.MEF;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parent;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
@Getter
@Setter
public class MappingFonctionMEFMEF {

  /**
   * Entity parent fonctionMEF
   */
  @Parent
  @Column(name = "FONCTIONMEF_ID", nullable = false)
  private FonctionMEF fonctionMEF;
  /**
   * Source d'alimentation de l'association.
   */
  @Basic
  @Column(length = IntConst.ISOURCE, name = "SOURCE", nullable = false)
  private String source;
  /**
   * Si l'enseignant est responsable de formation.
   */
  @Basic
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false", name = "RESPONSABLE")
  private boolean isResponsable;
  /**
   * Relation avec le MEF.
   */
  @OneToOne
  @JoinColumn(name = "MEF_ID", nullable = false)
  private MEF mef;

  /**
   * Contructor of the object MappingFonctionMEFMEF.java.
   */
  public MappingFonctionMEFMEF() {
    super();
  }

  /**
   * Contructor of the object MappingFonctionMEFMEF.java.
   *
   * @param source
   * @param mef
   * @param isResponsable
   */
  public MappingFonctionMEFMEF(final String source, final MEF mef, final boolean isResponsable) {
    super();
    this.source = source;
    this.mef = mef;
    this.isResponsable = isResponsable;
  }

  @Override
  public String toString() {
    return "MappingFonctionMEFMEF [" +
      this.source + ", " +
      this.mef.toString() +
      "]";
  }

}
