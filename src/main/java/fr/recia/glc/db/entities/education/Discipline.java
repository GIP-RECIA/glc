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
  @UniqueConstraint(columnNames = {"code", "source"})
})
@Getter
@Setter
public class Discipline extends AbstractSimpleEntity {

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

  @Override
  public String toString() {
    return "Discipline [" +
      super.toString() + ", " +
      this.code + ", " +
      this.disciplinePoste + ", " +
      this.source +
      "]";
  }

}
