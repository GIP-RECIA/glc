package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.entities.common.enums.CategorieRelation;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MappingAPersonneAPersonneId implements Serializable {

  /**
   * The personne to associate to the personne2.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "APERSONNE1_ID", nullable = false)
  private APersonne personne1;
  /**
   * The personne to associate to the personne2.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "APERSONNE2_ID", nullable = false)
  private APersonne personne2;

  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private CategorieRelation categorie;

  /**
   * Contructor of the object MappingAPersonneAPersonneId.java.
   */
  public MappingAPersonneAPersonneId() {
    super();
  }

  /**
   * Contructor of the object MappingAPersonneAPersonne.java.
   *
   * @param personne1
   * @param personne2
   */
  public MappingAPersonneAPersonneId(final APersonne personne1, final APersonne personne2,
                                     final CategorieRelation categoryRelation) {
    super();
    this.personne1 = personne1;
    this.personne2 = personne2;
    this.categorie = categoryRelation;
  }

  @Override
  public String toString() {
    return "MappingAPersonneAPersonneId [personne1=" +
      this.personne1 + ", personne2=" +
      this.personne2 + ", categoryRelation=" +
      this.categorie +
      "]";
  }

}
