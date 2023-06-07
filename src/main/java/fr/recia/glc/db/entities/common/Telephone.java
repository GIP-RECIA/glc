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

import fr.recia.glc.db.entities.common.enums.TelephoneType;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Getter
@Setter
public class Telephone extends AbstractSimpleEntity {

  /**
   * Numéro de téléphone.
   */
  @Column(nullable = false, length = IntConst.I20)
  private String numero;
  /**
   * Type du numéro de téléphone.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private TelephoneType type;
  /**
   * Source de l'alimentation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;
  /**
   * si téléphone utilisé.
   */
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean used;

  /**
   * Constructeur de l'objet Telephone.java.
   */
  public Telephone() {
    super();
  }

  /**
   * Constructeur de l'objet Telephone.java avec les champs obligatoires.
   *
   * @param numero Numéro de téléphone.
   * @param type   Type du numéro de téléphone (FAX, mobile, fixe pro, fixe pers).
   * @param source source d'alimentation
   * @param used   Si le numéro est utilisé ou non.
   */
  public Telephone(final String numero, final TelephoneType type, final String source, final boolean used) {
    super();
    this.numero = numero;
    this.type = type;
    this.source = source;
    this.used = used;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "Telephone [" +
      super.toString() + ", " +
      this.type + ", " +
      this.numero + ", " +
      this.source + ", " +
      this.used +
      "]";
  }

}
