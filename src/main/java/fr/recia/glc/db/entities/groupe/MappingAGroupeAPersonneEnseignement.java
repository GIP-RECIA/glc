package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.education.Enseignement;
import fr.recia.glc.db.entities.personne.Enseignant;
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
@Table(name = "apersonnes_agroupes_enseignements")
@AssociationOverrides({
  @AssociationOverride(name = "pk.enseignant", joinColumns = @JoinColumn(name = "APERSONNE_ID")),
  @AssociationOverride(name = "pk.groupe", joinColumns = @JoinColumn(name = "AGROUPEOFFONCCLASSEGROUPE_ID")),
  @AssociationOverride(name = "pk.enseignement", joinColumns = @JoinColumn(name = "ENSEIGNEMENT_ID"))
})
@Getter
@Setter
public class MappingAGroupeAPersonneEnseignement implements Serializable {

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
  private MappingAGroupeAPersonneEnseignementId pk = new MappingAGroupeAPersonneEnseignementId();

  /**
   * Contructor of the object MappingAGroupeAPersonneEnseignement.java.
   */
  public MappingAGroupeAPersonneEnseignement() {
    super();
  }

  /**
   * Contructor of the object MappingAGroupeAPersonneEnseignement.java.
   *
   * @param source
   * @param groupe
   * @param enseignant
   * @param enseignement
   */
  public MappingAGroupeAPersonneEnseignement(final String source, final Enseignant enseignant,
                                             final AGroupeOfFoncClasseGroupe groupe, final Enseignement enseignement) {
    super();
    this.source = source;
    this.pk = new MappingAGroupeAPersonneEnseignementId(enseignant, groupe, enseignement);
  }

  @Override
  public String toString() {
    return "MappingAGroupeAPersonneEnseignement [source=" +
      source + ", division=" +
      this.pk.getGroupe().getId() + ", enseignant=" +
      this.pk.getEnseignant().getId() + ", enseignement=" +
      this.pk.getEnseignement().getId() +
      "]";
  }

}
