package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.entities.groupe.RoleApplicatif;
import fr.recia.glc.db.entities.structure.AStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class DroitsAttribut extends AbstractSimpleEntity {

  /**
   * Nom de l'attribut.
   */
  private String cle;
  /**
   * Autorisation de visibilité de la clé. False par défaut.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean visible;
  /**
   * Autorisation de modification de la clé. False par défaut.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean modifier;
  /**
   * Autorisation d'ajout de la clé. False par défaut.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean ajouter;
  /**
   * Autorisation de suppression de la clé. False par défaut.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean supprimer;
  /**
   * Autorisation de visibilité la clé à d'autres personnes. False par défaut.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean collectif;

  /**
   * Relation unidirectionnelle.
   * Structure pour laquelle est configuré ce type de droit.
   */
  @ManyToOne
  @JoinColumn(name = "structure_fk")
  private AStructure structure;

  /**
   * Relation unidirectionnelle.
   * RoleApplicatif auquel s'applique ce type de droit.
   */
  @ManyToOne
  @JoinColumn(name = "role_applicatif_fk")
  private RoleApplicatif roleApplicatif;

  /**
   * Constructeur de l'objet DroitAttribut.java.
   */
  public DroitsAttribut() {
    super();
  }

  /**
   * Constructeur de l'objet DroitAttribut.java.
   *
   * @param cle            Nom de l'attribut.
   * @param structure      Structure pour laquelle est configuré ce type de droit.
   * @param roleApplicatif RoleApplicatif auquel s'applique ce type de droit.
   */
  public DroitsAttribut(final String cle, final AStructure structure, final RoleApplicatif roleApplicatif) {
    super();
    this.cle = cle;
    this.structure = structure;
    this.roleApplicatif = roleApplicatif;
  }

  @Override
  public String toString() {
    return "DroitAttribut [" +
      super.toString() + ", " +
      this.cle + ", " +
      this.ajouter + ", " +
      this.visible + ", " +
      this.modifier + ", " +
      this.supprimer + ", " +
      this.collectif + ", " +
      this.structure + ", " +
      this.roleApplicatif +
      "]";
  }

}
