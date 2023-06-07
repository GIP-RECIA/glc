package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.entities.common.enums.CategorieRelation;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "relations_apersonnes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AssociationOverrides({
  @AssociationOverride(name = "pk.personne1", joinColumns = @JoinColumn(name = "APERSONNE1_ID")),
  @AssociationOverride(name = "pk.personne2", joinColumns = @JoinColumn(name = "APERSONNE2_ID"))
})
@Getter
@Setter
public class AMappingRelation implements Serializable {

  /**
   * The Source which insert the entry.
   */
  @Basic
  @Column(name = "SOURCE", length = IntConst.ISOURCE, nullable = false)
  private String source;
  /**
   * The pk
   */
  @EmbeddedId
  private MappingAPersonneAPersonneId pk = new MappingAPersonneAPersonneId();

  /**
   * Empty Constructor, must not be used.
   */
  public AMappingRelation() {
    super();
  }

  /**
   * Contructor of the object MappingAGroupeAPersonne.java.
   *
   * @param source
   * @param personne1
   * @param personne2
   */
  public AMappingRelation(final String source, final APersonne personne1,
                          final APersonne personne2, final CategorieRelation categoryRelation) {
    super();
    this.source = source;
    this.pk = new MappingAPersonneAPersonneId(personne1, personne2, categoryRelation);
  }

  @Override
  public String toString() {
    return "MappingAPersonneAPersonne [source=" +
      source + ", categoryRelation=" +
      this.pk.getCategorie() + ", personne1=" +
      this.pk.getPersonne1().getId() + ", personne2=" +
      this.pk.getPersonne2().getId() +
      "]";
  }

}
