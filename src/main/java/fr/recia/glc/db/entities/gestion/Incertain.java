package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.AStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class Incertain extends AbstractSimpleEntity {

  /**
   * Nom de l'attribut xml de la source posant problème.
   */
  private String attribut;
  /**
   * Création automatique de la date de création de l'objet lors de la construction.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreation;
  /**
   * Valeur pouvant poser problème.
   */
  private String value;
  /**
   * Texte explicatif.
   */
  @Lob
  @Column(columnDefinition = "TEXT")
  private String texte;
  /**
   * Indique le type du champ posant problème,
   * vrai si champ obligatoire, faux si facultatif.
   * Si faux la personne ou la structure n'est pas dans un état incertain.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean obligatoire;

  /**
   * Relation bidirectionnelle.
   * Personne incertaine.
   */
  @ManyToOne
  private APersonne incertainPers;
  /**
   * Relation bidirectionnelle.
   * Structure Incertaine.
   */
  @ManyToOne
  private AStructure incertainStruct;

  /**
   * Constructeur de l'objet Incertain.java.
   */
  public Incertain() {
    super();
  }

  /**
   * Constructeur de l'objet Incertain.java.
   *
   * @param attribut    Attribut posant problème.
   * @param value       Valeur de l'attribut si renseigné.
   * @param obligatoire Vrai si le champ est obligatoire faux si facultatif.
   * @param incertain   Personne ayant des attributs à problème, pouvant bloqué l'état à incertain.
   * @param texte       Texte facultatif pour indiquer le type de problème.
   */
  public Incertain(final String attribut, final String value, final boolean obligatoire,
                   final APersonne incertain, final String texte) {
    super();
    this.attribut = attribut;
    this.obligatoire = obligatoire;
    this.incertainPers = incertain;
    this.texte = texte;
    this.value = value;
  }

  /**
   * Constructeur de l'objet Incertain.java.
   *
   * @param attribut    Attribut posant problème.
   * @param value       Valeur de l'attribut si renseigné.
   * @param obligatoire Vrai si le champ est obligatoire faux si facultatif.
   * @param incertain   Structure ayant des attributs à problème, pouvant bloqué l'état à incertain.
   * @param texte       Texte facultatif pour indiquer le type de problème.
   */
  public Incertain(final String attribut, final String value, final boolean obligatoire,
                   final AStructure incertain, final String texte) {
    super();
    this.attribut = attribut;
    this.obligatoire = obligatoire;
    this.incertainStruct = incertain;
    this.texte = texte;
    this.value = value;
  }

  /**
   * Constructeur de l'objet Incertain.java.
   * Cas où la structure est indéfinie et que des personne y sont rattachées
   * ou par la définition d'une classe appartenant à la structure.
   *
   * @param attribut        Attribut posant problème.
   * @param value           Valeur de l'attribut si renseigné.
   * @param obligatoire     Vrai si le champ est obligatoire faux si facultatif.
   * @param incertainPers   Personne ayant des attributs à problème, pouvant bloqué l'état à incertain.
   * @param incertainStruct Structure ayant des attributs à problème, pouvant bloqué l'état à incertain.
   * @param texte           Texte facultatif pour indiquer le type de problème.
   */
  public Incertain(final String attribut, final String value, final boolean obligatoire, final APersonne incertainPers,
                   final AStructure incertainStruct, final String texte) {
    super();
    this.attribut = attribut;
    this.obligatoire = obligatoire;
    this.incertainPers = incertainPers;
    this.incertainStruct = incertainStruct;
    this.texte = texte;
  }

  /**
   * Setter automatique du membre dateCreation lors de la creation de l'objet.
   */
  @PrePersist
  public void prePersistOps() {
    Date d = new Date();
    d.setTime(Calendar.getInstance().getTimeInMillis());
    if (this.dateCreation == null) {
      this.dateCreation = d;
    }
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    String incertainPers = this.incertainPers != null && this.incertainPers.getCleJointure() != null ? ", " +
      this.incertainPers.getId() + ", " +
      this.incertainPers.getCategorie() + ", " +
      this.incertainPers.getCleJointure().getSource() + ", " +
      this.incertainPers.getCleJointure().getCle() : "";
    String incertainStruct = this.incertainStruct != null && this.incertainStruct.getCleJointure() != null ? ", " +
      this.incertainStruct.getId() + ", " +
      this.incertainStruct.getCategorie() + ", " +
      this.incertainStruct.getCleJointure().getSource() + ", " +
      this.incertainStruct.getCleJointure().getCle() : "";

    return "Incertain [" +
      super.toString() + ", " +
      this.attribut + ", " +
      this.obligatoire + ", " +
      this.value + ", " +
      this.texte + ", " +
      this.dateCreation +
      incertainPers +
      incertainStruct +
      "]";
  }

}
