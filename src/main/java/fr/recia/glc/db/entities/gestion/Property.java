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
package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.entities.common.enums.PropertyName;
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
public class Property extends AbstractSimpleEntity {

  /**
   * Name of the property.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true, length = IntConst.I128)
  private PropertyName name;

  /**
   * Value of the property.
   */
  private String value;

  /**
   * Constructeur de l'objet Property.java.
   */
  public Property() {
    super();
  }

  /**
   * Constructeur de l'objet Property.java.
   *
   * @param name
   * @param value
   */
  public Property(final PropertyName name, final String value) {
    super();
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Property [" +
      super.toString() + ", " +
      this.name + ", " +
      this.value +
      "]";
  }

}
