package fr.recia.glc.db.entities.education;

import fr.recia.glc.db.entities.personne.Eleve;
import fr.recia.glc.db.entities.structure.Etablissement;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parent;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MappingEleveEnseignement implements Serializable {

  /**
   * Relation avec l'objet parent.
   */
  @Parent
  @Column(name = "ELEVE_ID", nullable = false)
  private Eleve eleve;
  /**
   * Source d'alimentation de l'association.
   */
  @Basic
  @Column(length = IntConst.ISOURCE, name = "SOURCE", nullable = false)
  private String source;
  /**
   * Relation avec l'enseignement.
   */
  @OneToOne
  @JoinColumn(name = "ENSEIGNEMENT_ID", nullable = false)
  private Enseignement enseignement;
  /**
   * Relation avec l'établissement.
   */
  @OneToOne
  @JoinColumn(name = "ETABLISSEMENT_ID")
  private Etablissement etablissement;

  /**
   * Contructor of the object MappingEleveEnseignement.java.
   */
  public MappingEleveEnseignement() {
    super();
  }

  /**
   * Contructor of the object MappingEleveEnseignement.java.
   *
   * @param source
   * @param enseignement
   */
  public MappingEleveEnseignement(final String source, final Enseignement enseignement) {
    super();
    this.source = source;
    this.enseignement = enseignement;
  }

  /**
   * Contructor of the object MappingEleveEnseignement.java.
   *
   * @param source
   * @param enseignement
   * @param etablissement
   */
  public MappingEleveEnseignement(final String source, final Enseignement enseignement, final Etablissement etablissement) {
    super();
    this.source = source;
    this.enseignement = enseignement;
    this.etablissement = etablissement;
  }

  @Override
  public String toString() {
    return "MappingEleveEnseignement [source=" +
      this.source + ", enseignement=" +
      this.enseignement + ", etablissement=" +
      (etablissement != null ? etablissement.getId() : "default") +
      "]";
  }

}
