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

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"codefiliere", "source"})
})
@Getter
@Setter
public class TypeFonctionFiliere extends AbstractSimpleEntity {

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

  @Override
  public String toString() {
    return "TypeFonctionFiliere [" +
      super.toString() + ", " +
      this.codeFiliere + ", " +
      this.libelleFiliere + ", " +
      this.source +
      "]";
  }

}
