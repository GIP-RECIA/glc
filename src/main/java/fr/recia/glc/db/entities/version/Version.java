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

import fr.recia.glc.db.utils.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean Entity.
 * Historisation des versions de la base de données de Sarapis.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>dateVersion et version.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 4 févr. 09
 */
@Entity
public class Version extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -2539122025647942517L;

  //Attributs
  /**
   * Date de la mise en place de la version.
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date dateVersion;
  /**
   * Numéro de la version.
   */
  @Column(unique = true, nullable = false, length = IntConst.I30)
  private String version;
  /**
   * Les commentaires.
   */
  @Lob
  @Column(columnDefinition = "TEXT")
  private String commentaires;

  //Relations
  /**
   * Relation bidirectionnelle.
   * Liste des tables impactées par cette nouvelle version.
   */
  @OneToMany(mappedBy = "sinceVersion")
  private Set<VersionTable> versionImpact = new HashSet<>();

  /**
   * Constructeur de l'objet Version.java.
   */
  public Version() {
    super();
  }

  //Accesseurs

  /**
   * Getter du membre dateVersion.
   *
   * @return <code>Date</code> le membre dateVersion.
   */
  public Date getDateVersion() {
    return dateVersion;
  }

  /**
   * Setter du membre dateVersion.
   *
   * @param dateVersion la nouvelle valeur du membre dateVersion.
   */
  public void setDateVersion(final Date dateVersion) {
    this.dateVersion = dateVersion;
  }

  /**
   * Getter du membre version.
   *
   * @return <code>String</code> le membre version.
   */
  public String getVersion() {
    return version;
  }

  /**
   * Setter du membre version.
   *
   * @param version la nouvelle valeur du membre version.
   */
  public void setVersion(final String version) {
    this.version = version;
  }

  /**
   * Getter du membre commentaires.
   *
   * @return <code>String</code> le membre commentaires.
   */
  public String getCommentaires() {
    return commentaires;
  }

  /**
   * Setter du membre commentaires.
   *
   * @param commentaires la nouvelle valeur du membre commentaires.
   */
  public void setCommentaires(final String commentaires) {
    this.commentaires = commentaires;
  }

  //Relations

  /**
   * Getter du membre versionImpact.
   *
   * @return <code>Set< VersionTable ></code> le membre versionImpact.
   */
  public Set<VersionTable> getVersionImpact() {
    return versionImpact;
  }

  /**
   * Setter du membre versionImpact.
   *
   * @param versionImpact la nouvelle valeur du membre versionImpact.
   */
  public void setVersionImpact(final Set<VersionTable> versionImpact) {
    this.versionImpact = versionImpact;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractSimpleEntity#toString()
   */
  @Override
  public String toString() {
    return "Version[" +
      super.toString() + ", " +
      this.version + ", " +
      this.dateVersion + ", " +
      this.commentaires +
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
    int result = 1;
    if (version == null) {
      result = prime * result;
    } else {
      result = prime * result + version.hashCode();
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
    if (!(obj instanceof Version)) {
      return false;
    }
    final Version other = (Version) obj;
    if (version == null) {
      if (other.version != null) {
        return false;
      }
    } else if (!version.equals(other.version)) {
      return false;
    }
    return true;
  }

}
