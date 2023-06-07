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

import fr.recia.glc.db.entities.common.enums.ExternalIdSource;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
@Getter
@Setter
public class ExternalId {

  /**
   * Identifiant externe.
   */
  @Column(nullable = false)
  private String id;
  /**
   * Destinataire de l'identifiant.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20, nullable = false)
  private ExternalIdSource destinataire;

  /**
   * Constructeur de l'objet ExternalId.java.
   */
  public ExternalId() {
    super();
  }

  /**
   * Constructeur de l'objet Mail.java.
   *
   * @param id           Identifiant.
   * @param destinataire Le destinataire utilisant l'identifiant.
   */
  public ExternalId(final String id, final ExternalIdSource destinataire) {
    super();
    this.id = id;
    this.destinataire = destinataire;
  }

  @Override
  public String toString() {
    return "ExternalId [id=" +
      this.id + ", destinataire=" +
      this.destinataire +
      "]";
  }

}
