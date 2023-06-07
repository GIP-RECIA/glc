package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.education.Enseignement;
import fr.recia.glc.db.entities.personne.Enseignant;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MappingAGroupeAPersonneEnseignementId implements Serializable {

  /**
   * The personne to associate to the groupe and enseignement.
   */
  @ManyToOne
  @JoinColumn(name = "APERSONNE_ID", nullable = false)
  private Enseignant enseignant;
  /**
   * The groupe to associate with the person and enseignement.
   */
  @ManyToOne
  @JoinColumn(name = "AGROUPEOFFONCCLASSEGROUPE_ID", nullable = false)
  private AGroupeOfFoncClasseGroupe groupe;
  /**
   * The enseignement to associate with the person and groupe.
   */
  @ManyToOne
  @JoinColumn(name = "ENSEIGNEMENT_ID", nullable = false)
  private Enseignement enseignement;

  /**
   * Contructor of the object MappingAGroupeAPersonneEnseignementId.java.
   */
  public MappingAGroupeAPersonneEnseignementId() {
    super();
  }

  /**
   * Contructor of the object MappingAGroupeAPersonneEnseignementId.java.
   *
   * @param enseignant
   * @param groupe       Une classe ou un groupe.
   * @param enseignement
   */
  public MappingAGroupeAPersonneEnseignementId(final Enseignant enseignant,
                                               final AGroupeOfFoncClasseGroupe groupe,
                                               final Enseignement enseignement) {
    super();
    this.enseignant = enseignant;
    this.groupe = groupe;
    this.enseignement = enseignement;
  }

  @Override
  public String toString() {
    return "MappingAGroupeAPersonneEnseignementId [enseignant=" +
      this.enseignant + ", groupe=" +
      this.groupe + ", enseignement=" +
      this.enseignement +
      "]";
  }

}
