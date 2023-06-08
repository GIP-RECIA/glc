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
