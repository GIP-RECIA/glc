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
package fr.recia.glc.db.entities.version;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Bean Entity de gestion des version sur les tables.
 * A une version on associe les tables impactées par les modifications.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>nomTable et derniereModification.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 4 févr. 09
 */
@Entity
public class VersionTable extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -731982785025952232L;

  // Attributs
  /**
   * Nom de la table concernée par la version.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I60)
  private TableNames tableName;

  // Relations
  /**
   * Dernière version de modification appliquée à la table.
   */
  @ManyToOne
  @JoinColumn(name = "version_fk")
  private Version sinceVersion;

  @Column(nullable = false, columnDefinition = "BIT not null default FALSE ")
  private boolean deleted;

  /**
   * Constructeur de l'objet VersionTable.java.
   */
  public VersionTable() {
    super();
  }

  /**
   * Constructeur de l'objet VersionTable.java.
   *
   * @param tableName    Nom de la table concernée par la version.
   * @param sinceVersion Dernière version de modification de la table.
   */
  public VersionTable(final TableNames tableName, final Version sinceVersion) {
    super();
    this.tableName = tableName;
    this.sinceVersion = sinceVersion;
  }

  //Accesseurs

  /**
   * Getter du membre table.
   *
   * @return <code>TableNames</code> le membre table.
   */
  public TableNames getTable() {
    return tableName;
  }

  /**
   * Setter du membre table.
   *
   * @param tableName la nouvelle valeur du membre table.
   */
  public void setTable(final TableNames tableName) {
    this.tableName = tableName;
  }

  /**
   * Getter du membre sinceVersion.
   *
   * @return <code>Version</code> le membre sinceVersion.
   */
  public Version getSinceVersion() {
    return sinceVersion;
  }

  /**
   * Setter du membre sinceVersion.
   *
   * @param sinceVersion la nouvelle valeur du membre sinceVersion.
   */
  public void setSinceVersion(final Version sinceVersion) {
    this.sinceVersion = sinceVersion;
  }

  /**
   * @return the deleted
   */
  public boolean isDeleted() {
    return deleted;
  }

  /**
   * @param deleted the deleted to set
   */
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
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
    int result = 1;
    if (tableName == null) {
      result = prime * result;
    } else {
      result = prime * result + tableName.hashCode();
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
    if (!(obj instanceof VersionTable)) {
      return false;
    }
    final VersionTable other = (VersionTable) obj;
    if (tableName == null) {
      if (other.tableName != null) {
        return false;
      }
    } else if (!tableName.equals(other.tableName)) {
      return false;
    }
    return true;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractSimpleEntity#toString()
   */
  @Override
  public String toString() {
    return "VersionTable[" +
      super.toString() + ", " +
      this.tableName +
      "]";
  }

}
