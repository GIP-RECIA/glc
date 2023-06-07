package fr.recia.glc.db.entities.groupe;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "apersonnes_agroupes")
@AssociationOverrides({
  @AssociationOverride(
    name = "pk.personne",
    joinColumns = @JoinColumn(name = "APERSONNE_ID")),
  @AssociationOverride(
    name = "pk.groupe",
    joinColumns = @JoinColumn(name = "AGROUPEOFAPERS_ID"))
})
@Getter
@Setter
public class MappingAGroupeAPersonne implements Serializable {

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
  private MappingAGroupeAPersonneId pk = new MappingAGroupeAPersonneId();

  /**
   * Contructor of the object MappingAGroupeAPersonne.java.
   */
  public MappingAGroupeAPersonne() {
    super();
  }

  /**
   * Contructor of the object MappingAGroupeAPersonne.java.
   *
   * @param source
   * @param groupe
   * @param personne
   */
  public MappingAGroupeAPersonne(final String source, final APersonne personne, final AGroupeOfAPersonne groupe) {
    super();
    this.source = source;
    this.pk = new MappingAGroupeAPersonneId(personne, groupe);
  }

  @Override
  public String toString() {
    return "MappingAGroupeAPersonne [source=" +
      this.source + ", groupe=" +
      this.pk.getGroupe().getId() + ", personne=" +
      this.pk.getPersonne().getId() +
      "]";
  }

}
