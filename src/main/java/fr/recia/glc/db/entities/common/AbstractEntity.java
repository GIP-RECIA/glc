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
package fr.recia.glc.db.entities.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Object Abstrait Hérité par tout les bean - Entity principaux (structure, personne, groupe, fonction).
 * Cela permet de gérer de façon automatique les id, date de création, date de modification et la version.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>id, version, dateCreation, dateModification.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 5 juin 08
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity extends AbstractSimpleEntity implements Serializable {

  //Attributs
  /**
   * Gestion des versions de l'objet, incrémentation de +1 à chaque modification de update ou merge.
   */
  @Version
  private long version;
  /**
   * Création automatique de la date de création de l'objet lors de la construction.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreation;
  /**
   * Donne l'information de la date de modification de l'objet.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateModification;
  /**
   * Donne l'information de la date d'acquittement de l'objet lors de l'export.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateAcquittement;

  //Constructeur

  /**
   * Constructeur de l'objet AbstractEntity.java.
   */
  public AbstractEntity() {
    super();
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
    if (this.dateModification == null) {
      this.dateModification = d;
    }
  }

  /**
   * Setter automatique du membre dateModification lors de la modification de l'objet.
   * WARNING : Surchargé dans entity APersonne pour éviter la màj auto lors de la modification en delete.
   */
  @PreUpdate
  public void preUpdateOps() {
    this.dateModification.setTime(Calendar.getInstance().getTimeInMillis());
  }

  //Accesseurs

  /**
   * Getter du membre version.
   *
   * @return <code>int</code> le membre version
   */
  public long getVersion() {
    return this.version;
  }

  /**
   * Setter du membre version.
   *
   * @param version la nouvelle valeur du membre version
   */
  public void setVersion(final long version) {
    this.version = version;
  }

  /**
   * Getter du membre dateCreation.
   *
   * @return <code>Date</code> le membre dateCreation
   */
  public Date getDateCreation() {
    return this.dateCreation;
  }

  /**
   * Setter du membre dateCreation.
   *
   * @param dateCreation la nouvelle valeur du membre dateCreation
   */
  public void setDateCreation(final Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  /**
   * Getter du membre dateModification.
   *
   * @return <code>Date</code> le membre dateModification
   */
  public Date getDateModification() {
    return this.dateModification;
  }

  /**
   * Setter du membre dateModification.
   */
  public void setDateModification() {
    this.dateModification = new Date();
  }

  /**
   * Setter du membre dateModification.
   *
   * @param dateModification la nouvelle valeur du membre dateModification
   */
  public void setDateModification(final Date dateModification) {
    this.dateModification = dateModification;
  }

  /**
   * Getter du membre dateAcquittement.
   *
   * @return <code>Date</code> le membre dateAcquittement.
   */
  public Date getDateAcquittement() {
    return dateAcquittement;
  }

  /**
   * Setter du membre dateAcquittement.
   *
   * @param dateAcquittement la nouvelle valeur du membre dateAcquittement.
   */
  public void setDateAcquittement(final Date dateAcquittement) {
    this.dateAcquittement = dateAcquittement;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "AbstractEntity [" +
      super.toString() + ", " +
      this.version + ", " +
      this.dateCreation + ", " +
      this.dateModification +
      "]";
  }

}
