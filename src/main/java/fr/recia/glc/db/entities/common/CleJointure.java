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
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CleJointure implements Serializable {

  /**
   * Source de l'alimentation.
   */
  @Column(length = IntConst.ISOURCE)
  private String source;
  /**
   * Clé fournie lors l'alimentation.
   */
  @Column(length = IntConst.ICLE)
  private String cle;

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
   * @param cle    clé identifiante, donc unique, de l'objet fourni lors de l'alimentation.
   */
  public CleJointure(final String source, final String cle) {
    super();
    this.source = source;
    this.cle = cle;
  }

  @Override
  public String toString() {
    return "CleJointure [" +
      this.source + ", " +
      this.cle +
      "]";
  }

}
