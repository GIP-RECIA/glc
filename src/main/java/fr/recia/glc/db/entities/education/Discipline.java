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
package fr.recia.glc.db.entities.education;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Bean - Entity. Description d'une discipline de poste pour le personnel des structures (enseignant et non enseignant).
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>code, disiplinePoste, source.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 6 juin 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"code", "source"})
})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Discipline extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 867919868004375371L;

  // Attributs
  /**
   * Code de la discipline.
   */
  @Column(nullable = false, length = IntConst.I30)
  private String code;
  /**
   * Libellé de la discipline de poste.
   */
  private String disciplinePoste;
  /**
   * Source d'alimentation de la discipline de poste.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  // Constructeurs

  /**
   * Constructeur de l'objet Discipline.java.
   */
  public Discipline() {
    super();
  }

  /**
   * Constructeur de l'objet Discipline.java.
   *
   * @param code            Code de la discipline.
   * @param disciplinePoste Libellé de la discipline de poste.
   * @param source          Source d'alimentation de l'objet.
   */
  public Discipline(final String code, final String disciplinePoste, final String source) {
    super();
    this.code = code;
    this.disciplinePoste = disciplinePoste;
    this.source = source;
  }

  // Accesseurs

  /**
   * Getter du membre code.
   *
   * @return <code>String</code> le membre code
   */
  public String getCode() {
    return this.code;
  }

  /**
   * Setter du membre code.
   *
   * @param code la nouvelle valeur du membre code
   */
  public void setCode(final String code) {
    this.code = code;
  }

  /**
   * Getter du membre disciplinePoste.
   *
   * @return <code>String</code> le membre disciplinePoste
   */
  public String getDisciplinePoste() {
    return this.disciplinePoste;
  }

  /**
   * Setter du membre disciplinePoste.
   *
   * @param disciplinePoste la nouvelle valeur du membre disciplinePoste
   */
  public void setDisciplinePoste(final String disciplinePoste) {
    this.disciplinePoste = disciplinePoste;
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
    return "Discipline [" +
      super.toString() + ", " +
      this.code + ", " +
      this.disciplinePoste + ", " +
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
    if (this.code == null) {
      result = prime * result;
    } else {
      result = prime * result + this.code.hashCode();
    }
    if (this.disciplinePoste == null) {
      result = prime * result;
    } else {
      result = prime * result + this.disciplinePoste.hashCode();
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
    if (!(obj instanceof Discipline)) {
      return false;
    }
    final Discipline other = (Discipline) obj;
    if (this.code == null) {
      if (other.code != null) {
        return false;
      }
    } else if (!this.code.equals(other.code)) {
      return false;
    }
    if (this.disciplinePoste == null) {
      if (other.disciplinePoste != null) {
        return false;
      }
    } else if (!this.disciplinePoste.equals(other.disciplinePoste)) {
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
