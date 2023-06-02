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
package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.utils.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Bean - Entity.
 * * Description d'un bassin de formation.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>code, nom, source.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"code", "source"})
})
public class BassinFormation extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -1774348301432274220L;
  //Attributs
  /**
   * Code du bassin de formation.
   */
  @Column(nullable = false, length = IntConst.I10)
  private String code;
  /**
   * Nom du bassin de formation.
   */
  private String nom;
  /**
   * Source d'alimentation du bassin de formation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  //Constructeurs

  /**
   * Constructeur de l'objet BassinFormation.java.
   */
  public BassinFormation() {
    super();
  }

  /**
   * Constructeur de l'objet BassinFormation.java.
   *
   * @param code   Code du bassin de formation.
   * @param nom    Nom du bassin de formation.
   * @param source Source d'alimentation de l'objet.
   */
  public BassinFormation(final String code, final String nom, final String source) {
    super();
    this.code = code;
    this.nom = nom;
    this.source = source;
  }

  //Accesseurs

  /**
   * Getter du membre code.
   *
   * @return <code>String</code> le membre code.
   */
  public String getCode() {
    return this.code;
  }

  /**
   * Setter du membre code.
   *
   * @param code la nouvelle valeur du membre code.
   */
  public void setCode(final String code) {
    this.code = code;
  }

  /**
   * Getter du membre nom.
   *
   * @return <code>String</code> le membre nom.
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Setter du membre nom.
   *
   * @param nom la nouvelle valeur du membre nom.
   */
  public void setNom(final String nom) {
    this.nom = nom;
  }

  /**
   * Getter of member source.
   *
   * @return <code>String</code> the attribute source
   */
  public String getSource() {
    return source;
  }

  /**
   * Setter of member source.
   *
   * @param source the source to set
   */
  public void setSource(final String source) {
    this.source = source;
  }

  /**
   * Transforme cette instance en une chaine de caractères.
   *
   * @return <code>String</code> La Chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "BassinFormation [" +
      super.toString() + ", " +
      this.code + ", " +
      this.nom + ", " +
      this.source +
      "]";
  }

  /**
   * Donne la valeur de hashage de l'instance.
   *
   * @return <code>int</code> La valeur de hash.
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    if (this.code == null) {
      result = prime * result;
    } else {
      result = prime * result + this.code.hashCode();
    }
    if (this.nom == null) {
      result = prime * result;
    } else {
      result = prime * result + this.nom.hashCode();
    }
    if (this.source == null) {
      result = prime * result;
    } else {
      result = prime * result + this.source.hashCode();
    }
    return result;
  }

  /**
   * Teste si un objet est égal à cette instance.
   *
   * @param obj Instance d'un objet à comparer.
   * @return <code>boolean</code> vrai si l'instance est identique, faux sinon.
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
    if (!(obj instanceof BassinFormation)) {
      return false;
    }
    final BassinFormation other = (BassinFormation) obj;
    if (this.code == null) {
      if (other.code != null) {
        return false;
      }
    } else if (!this.code.equals(other.code)) {
      return false;
    }
    if (this.nom == null) {
      if (other.nom != null) {
        return false;
      }
    } else if (!this.nom.equals(other.nom)) {
      return false;
    }
    if (this.source == null) {
      if (other.source != null) {
        return false;
      }
    } else if (!this.source.equals(other.source)) {
      return false;
    }
    return true;
  }

}
