/*
 * Copyright (C) 2023 GIP-RECIA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.AStructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;

/**
 * Bean - Entity.
 * Table faisant le lien entre deux personnes avec des informations précises et identiques.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>attribut, obligatoire, incertainPers et/ou incertainStruct.</DD>
 * <DD>IncertainPers (resp. IncertainStruct) est obligatoire s'il s'agit d'une personne (resp. structure).
 * Et s'il s'agit d'une personne rattachée à un structure incertaine
 * IncertainPers et IncertainStruct sont obligatoire.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class Incertain extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 4173410952069524554L;

  // Attributs
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
   * vrai si champ obligatoire, faux si facultaif.
   * Si faux la personne ou la structure n'est pas dans un état incertain.
   */
  @Column(nullable = false, columnDefinition = "BIT")
  private boolean obligatoire;

  // Relations
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

  // Constructeurs

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

  //Accesseurs

  /**
   * Getter du membre attribut.
   *
   * @return <code>String</code> le membre attribut.
   */
  public String getAttribut() {
    return this.attribut;
  }

  /**
   * Setter du membre attribut.
   *
   * @param attribut la nouvelle valeur du membre attribut.
   */
  public void setAttribut(final String attribut) {
    this.attribut = attribut;
  }

  /**
   * Getter of attribute dateCreation.
   *
   * @return <code>Date</code> the attribute dateCreation
   */
  public Date getDateCreation() {
    return dateCreation;
  }

  /**
   * Setter of attribute dateCreation.
   *
   * @param dateCreation <code>Date</code> the attribute dateCreation to set
   */
  public void setDateCreation(final Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  /**
   * Getter du membre value.
   *
   * @return <code>String</code> le membre value.
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Setter du membre value.
   *
   * @param value la nouvelle valeur du membre value.
   */
  public void setValue(final String value) {
    this.value = value;
  }

  /**
   * Getter du membre texte.
   *
   * @return <code>String</code> le membre texte.
   */
  public String getTexte() {
    return this.texte;
  }

  /**
   * Setter du membre texte.
   *
   * @param texte la nouvelle valeur du membre texte.
   */
  public void setTexte(final String texte) {
    this.texte = texte;
  }

  /**
   * Getter du membre obligatoire.
   *
   * @return <code>boolean</code> le membre obligatoire.
   */
  public boolean isObligatoire() {
    return this.obligatoire;
  }

  /**
   * Setter du membre obligatoire.
   *
   * @param obligatoire la nouvelle valeur du membre obligatoire.
   */
  public void setObligatoire(final boolean obligatoire) {
    this.obligatoire = obligatoire;
  }

  //Relations

  /**
   * Getter du membre incertainPers.
   *
   * @return <code>APersonne</code> le membre incertainPers.
   */
  public APersonne getIncertainPers() {
    return this.incertainPers;
  }

  /**
   * Setter du membre incertainPers.
   *
   * @param incertainPers la nouvelle valeur du membre incertainPers.
   */
  public void setIncertainPers(final APersonne incertainPers) {
    this.incertainPers = incertainPers;
  }

  /**
   * Getter du membre incertainStruct.
   *
   * @return <code>AStructure</code> le membre incertainStruct.
   */
  public AStructure getIncertainStruct() {
    return this.incertainStruct;
  }

  /**
   * Setter du membre incertainStruct.
   *
   * @param incertainStruct la nouvelle valeur du membre incertainStruct.
   */
  public void setIncertainStruct(final AStructure incertainStruct) {
    this.incertainStruct = incertainStruct;
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

  /**
   * Donne la valeur de hachage de l'instance.
   *
   * @return <code>int</code> La valeur du hash.
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    final int cst1 = 1237;
    final int cst2 = 1231;

    int result = 1;
    if (this.attribut == null) {
      result = prime * result;
    } else {
      result = prime * result + this.attribut.hashCode();
    }
    if (this.value == null) {
      result = prime * result;
    } else {
      result = prime * result + this.value.hashCode();
    }
    if (this.incertainPers == null) {
      result = prime * result;
    } else {
      result = prime * result + this.incertainPers.hashCode();
    }
    if (this.incertainStruct == null) {
      result = prime * result;
    } else {
      result = prime * result + this.incertainStruct.hashCode();
    }
    if (this.texte == null) {
      result = prime * result;
    } else {
      result = prime * result + this.texte.hashCode();
    }
    if (this.obligatoire) {
      result = prime * result + cst2;
    } else {
      result = prime * result + cst1;
    }
    return result;
  }

  /**
   * Teste si un objet est égal à cette instance.
   *
   * @param obj l'instance le l'object à comparer.
   * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Incertain)) {
      return false;
    }
    final Incertain other = (Incertain) obj;
    if (this.attribut == null) {
      if (other.attribut != null) {
        return false;
      }
    } else if (!this.attribut.equals(other.attribut)) {
      return false;
    }
    if (this.value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!this.value.equals(other.value)) {
      return false;
    }
    if (this.incertainPers == null) {
      if (other.incertainPers != null) {
        return false;
      }
    } else if (!this.incertainPers.equals(other.incertainPers)) {
      return false;
    }
    if (this.incertainStruct == null) {
      if (other.incertainStruct != null) {
        return false;
      }
    } else if (!this.incertainStruct.equals(other.incertainStruct)) {
      return false;
    }
    if (this.obligatoire != other.obligatoire) {
      return false;
    }
    if (this.texte == null) {
      if (other.texte != null) {
        return false;
      }
    } else if (!this.texte.equals(other.texte)) {
      return false;
    }
    return true;
  }

}
