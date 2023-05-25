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
package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Bean - Entity. Description d'une fonction de filière de la nommenclature N_FONCTION_FILIERE.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>codeFiliere, libelleFiliere, source.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 10 juin 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"codefiliere", "source"})
})
public class TypeFonctionFiliere extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 859631824227731491L;
  // Attributs
  /**
   * Code de la fonction filière.
   */
  @Column(nullable = false, length = IntConst.I30)
  private String codeFiliere;
  /**
   * Libellé de la fonction filière.
   */
  private String libelleFiliere;
  /**
   * Source d'alimentation de la fonction filière.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  // Constructeurs

  /**
   * Constructeur de l'objet TypeFonctionFiliere.java.
   */
  public TypeFonctionFiliere() {
    super();
  }

  /**
   * Constructeur de l'objet TypeFonctionFiliere.java.
   *
   * @param codeFiliere    Code de la fonction filière.
   * @param libelleFiliere Libellé de la fonction filière.
   * @param source         Source d'alimentation de l'objet.
   */
  public TypeFonctionFiliere(final String codeFiliere, final String libelleFiliere, final String source) {
    super();
    this.codeFiliere = codeFiliere;
    this.libelleFiliere = libelleFiliere;
    this.source = source;
  }

  // Accesseurs

  /**
   * Getter du membre codeFiliere.
   *
   * @return <code>String</code> le membre codeFiliere.
   */
  public String getCodeFiliere() {
    return this.codeFiliere;
  }

  /**
   * Setter du membre codeFiliere.
   *
   * @param codeFiliere la nouvelle valeur du membre codeFiliere.
   */
  public void setCodeFiliere(final String codeFiliere) {
    this.codeFiliere = codeFiliere;
  }

  /**
   * Getter du membre libelleFiliere.
   *
   * @return <code>String</code> le membre libelleFiliere.
   */
  public String getLibelleFiliere() {
    return this.libelleFiliere;
  }

  /**
   * Setter du membre libelleFiliere.
   *
   * @param libelleFiliere la nouvelle valeur du membre libelleFiliere.
   */
  public void setLibelleFiliere(final String libelleFiliere) {
    this.libelleFiliere = libelleFiliere;
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
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "TypeFonctionFiliere [" +
      super.toString() + ", " +
      this.codeFiliere + ", " +
      this.libelleFiliere + ", " +
      this.source +
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
    if (this.codeFiliere == null) {
      result = prime * result;
    } else {
      result = prime * result + this.codeFiliere.hashCode();
    }
    if (this.libelleFiliere == null) {
      result = prime * result;
    } else {
      result = prime * result + this.libelleFiliere.hashCode();
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
    if (!(obj instanceof TypeFonctionFiliere)) {
      return false;
    }
    final TypeFonctionFiliere other = (TypeFonctionFiliere) obj;
    if (this.codeFiliere == null) {
      if (other.codeFiliere != null) {
        return false;
      }
    } else if (!this.codeFiliere.equals(other.codeFiliere)) {
      return false;
    }
    if (this.libelleFiliere == null) {
      if (other.libelleFiliere != null) {
        return false;
      }
    } else if (!this.libelleFiliere.equals(other.libelleFiliere)) {
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
