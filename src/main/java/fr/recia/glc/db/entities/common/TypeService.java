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
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"libelleService", "source"})
})
@Getter
@Setter
public class TypeService extends AbstractSimpleEntity {

  /**
   * Nom du service.
   */
  @Column(nullable = false)
  private String libelleService;
  /**
   * Source de l'alimentation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  /**
   * Constructeur de l'objet TypeService.java.
   */
  public TypeService() {
    super();
  }

  /**
   * Constructeur de l'objet TypeService.java.
   *
   * @param libelleService Nom du service.
   * @param source         Source d'alimentation.
   */
  public TypeService(final String libelleService, final String source) {
    super();
    this.libelleService = libelleService;
    this.source = source;
  }

  @Override
  public String toString() {
    return "TypeService [" +
      super.toString() + ", " +
      this.libelleService + ", " +
      this.source +
      "]";
  }

}
