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
package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.entities.common.enums.CategorieRelation;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MappingAPersonneAPersonneId implements Serializable {

  /**
   * The personne to associate to the personne2.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "APERSONNE1_ID", nullable = false)
  private APersonne personne1;
  /**
   * The personne to associate to the personne2.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "APERSONNE2_ID", nullable = false)
  private APersonne personne2;

  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private CategorieRelation categorie;

  /**
   * Contructor of the object MappingAPersonneAPersonneId.java.
   */
  public MappingAPersonneAPersonneId() {
    super();
  }

  /**
   * Contructor of the object MappingAPersonneAPersonne.java.
   *
   * @param personne1
   * @param personne2
   */
  public MappingAPersonneAPersonneId(final APersonne personne1, final APersonne personne2,
                                     final CategorieRelation categoryRelation) {
    super();
    this.personne1 = personne1;
    this.personne2 = personne2;
    this.categorie = categoryRelation;
  }

  @Override
  public String toString() {
    return "MappingAPersonneAPersonneId [personne1=" +
      this.personne1 + ", personne2=" +
      this.personne2 + ", categoryRelation=" +
      this.categorie +
      "]";
  }

}
