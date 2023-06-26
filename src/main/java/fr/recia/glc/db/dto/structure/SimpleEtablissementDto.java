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
package fr.recia.glc.db.dto.structure;

import fr.recia.glc.db.enums.CategorieStructure;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SimpleEtablissementDto {

  private Long id;
  private String uai;
  private CategorieStructure categorie;
  private String type;
  private String nom;
  private String nomCourt;
  private String ville;
  private String siren;

  public SimpleEtablissementDto(Long id, String uai, CategorieStructure categorie,
                                String nom, String nomCourt, String siren) {
    this.id = id;
    this.uai = uai;
    this.categorie = categorie;
    this.nom = nom;
    this.nomCourt = nomCourt;
    this.siren = siren;
  }

}
