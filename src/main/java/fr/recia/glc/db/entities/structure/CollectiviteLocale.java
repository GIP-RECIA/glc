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
package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.enums.CategorieStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
public class CollectiviteLocale extends AStructure {

  /**
   * Région ou département de la collectivité.
   */
  @Column(nullable = false)
  private String lieuGeographique;

  /**
   * Constructeur de l'objet CollectiviteLocale.java.
   */
  public CollectiviteLocale() {
    super();
    this.setCategorie(CategorieStructure.Collectivite_locale);
  }

  /**
   * Constructeur de l'objet CollectiviteLocale.java.
   *
   * @param nom              Nom unique de la stucture.
   * @param siren            Numéro de SIRET/SIREN unique de la structure.
   * @param cleJointure      Clé de jointure unique de la structure.
   * @param lieuGeographique Région ou département de la collectivité.
   */
  public CollectiviteLocale(final String nom, final String siren,
                            final CleJointure cleJointure, final String lieuGeographique) {
    super(CategorieStructure.Collectivite_locale, nom, siren, cleJointure);
    this.lieuGeographique = lieuGeographique;
  }

  @Override
  public String toString() {
    return "CollectiviteLocale [" +
      super.toString() + ", " +
      this.lieuGeographique +
      "]";
  }

}
