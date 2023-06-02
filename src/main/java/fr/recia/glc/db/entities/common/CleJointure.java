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

import fr.recia.glc.db.utils.IntConst;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * Embedded Object.
 * Définition d'une clé de jointure avec sa source et la clé unique.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>source, cle.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 3 Juin 2008
 */
@Embeddable
public class CleJointure implements Serializable {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -3877767565823706447L;

  //Attributs
  /**
   * Source de l'alimentation.
   */
  @Column(length = IntConst.ISOURCE)
  private String source;
  /**
   * clé fournie lors l'alimentation.
   */
  @Column(length = IntConst.ICLE)
  private String cle;

  //Constructeurs

  /**
   * Constructeur de l'objet CleJointure.java.
   */
  public CleJointure() {
    super();
  }

  /**
   * Constructeur de l'objet CleJointure.java avec les champs obligatoires.
   *
   * @param source Source de l'alimentation :
   *               Annuaire Fédérateur(AF), ENT ou autres sources non connues actuellement.
   * @param cle    clé identifiante, donc unique, de l'object fournie lors de l'alimentation.
   */
  public CleJointure(final String source, final String cle) {
    super();
    this.source = source;
    this.cle = cle;
  }

  //Accesseurs

  /**
   * Getter du membre source.
   *
   * @return <code>String</code> le membre source
   */
  public String getSource() {
    return this.source;
  }

  /**
   * Setter du membre source.
   *
   * @param source la nouvelle valeur du membre source
   */
  public void setSource(final String source) {
    this.source = source;
  }

  /**
   * Getter du membre cle.
   *
   * @return <code>String</code> le membre cle
   */
  public String getCle() {
    return this.cle;
  }

  /**
   * Setter du membre cle.
   *
   * @param cle la nouvelle valeur du membre cle
   */
  public void setCle(final String cle) {
    this.cle = cle;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "CleJointure [" +
      this.source + ", " +
      this.cle +
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
    if (cle == null) {
      result = prime * result;
    } else {
      result = prime * result + cle.hashCode();
    }
    if (source == null) {
      result = prime * result;
    } else {
      result = prime * result + source.hashCode();
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
    if (!(obj instanceof CleJointure)) {
      return false;
    }
    final CleJointure other = (CleJointure) obj;
    if (cle == null) {
      if (other.cle != null) {
        return false;
      }
    } else if (!cle.equals(other.cle)) {
      return false;
    }
    if (source == null) {
      if (other.source != null) {
        return false;
      }
    } else if (!source.equals(other.source)) {
      return false;
    }
    return true;
  }

}
