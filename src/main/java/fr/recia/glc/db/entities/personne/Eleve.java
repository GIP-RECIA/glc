package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.enums.CategoriePersonne;
import fr.recia.glc.db.entities.education.MEF;
import fr.recia.glc.db.entities.education.MappingEleveEnseignement;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(indexes = {
  @Index(name = "sconetId", columnList = "sconetId"),
  @Index(name = "INE", columnList = "INE")
})
@Getter
@Setter
public class Eleve extends APersonne {

  /**
   * Identifiant sconet.
   */
  @Column(length = IntConst.I15)
  private String sconetId;
  /**
   * Identifiant National.
   */
  @Column(length = IntConst.I11)
  private String INE;
  /**
   * Vrai si l'élève est majeur, Faux sinon.
   */
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean majeur;
  /**
   * Vrai si l'élève est majeur anticipé, Faux sinon, null si non renseigné.
   */
  @Column(columnDefinition = "BIT")
  private Boolean majeurAnticipe;
  /**
   * Régime de l'élève.
   */
  @Column(length = IntConst.I128)
  private String regime;
  /**
   * Statut de l'élève.
   */
  @Column(length = IntConst.I128)
  private String statut;
  /**
   * Vrai si l'élève utilise les transports scolaire, Faux sinon, null si non renseigné.
   */
  @Column(columnDefinition = "BIT")
  private Boolean transport;
  /**
   * Vrai si l'élève est boursier, Faux sinon, null si non renseigné.
   */
  @Column(columnDefinition = "BIT")
  private Boolean boursier;

  /**
   * Relation unidirectionnelle.
   * MEF associé à l'élève.
   */
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "mef_fk")
  private MEF mef;
  /**
   * Relation unidirectionnelle.
   * Liste des enseignements suivis par l'élève.
   */
  @ElementCollection(fetch = FetchType.LAZY)
  @JoinTable(name = "eleves_enseignements", joinColumns = @JoinColumn(name = "ELEVE_ID", referencedColumnName = "ID"))
  private Set<MappingEleveEnseignement> enseignements = new HashSet<>();

  /**
   * Constructeur de l'objet Eleve.java.
   */
  public Eleve() {
    super();
    this.setCategorie(CategoriePersonne.Eleve);
  }

  /**
   * Constructeur de l'objet Eleve.java.
   *
   * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
   * @param cleJointure   Clé de jointure, identifiant unique fourni par les différentes sources,
   *                      mais unique uniquement pour le périmètre de la source.
   * @param cn            Nom canonique de la personne : NOM + Prénom usuels.
   * @param givenName     Prénom usuel.
   * @param sn            Nom d'usage.
   */
  public Eleve(final Date anneeScolaire, final CleJointure cleJointure,
               final String cn, final String givenName, final String sn) {
    super(anneeScolaire, CategoriePersonne.Eleve, cleJointure, cn, givenName, sn);
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.personne.APersonne#toString()
   */
  @Override
  public String toString() {
    return "Eleve [" +
      super.toString() + ", " +
      this.sconetId + ", " +
      this.INE + ", " +
      this.boursier + ", " +
      this.majeur + ", " +
      this.majeurAnticipe + ", " +
      this.regime + ", " +
      this.statut + ", " +
      this.transport + ", " +
      this.mef + ", " +
      this.enseignements +
      "]";
  }

}
