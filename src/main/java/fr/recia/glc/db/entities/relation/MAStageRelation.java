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
import fr.recia.glc.db.entities.common.enums.TypeStage;
import fr.recia.glc.db.entities.personne.Eleve;
import fr.recia.glc.db.entities.personne.TuteurStage;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MASTAGE")
@Getter
@Setter
public class MAStageRelation extends AStageRelation {

  /**
   * Empty Constructor, must not be used.
   */
  public MAStageRelation() {
    super();
  }

  /**
   * @param source
   * @param tuteurStage
   * @param eleve
   */
  public MAStageRelation(final String source, final TuteurStage tuteurStage, final Eleve eleve) {
    super(source, tuteurStage, eleve, TypeStage.Tuteur, CategorieRelation.MAStage);
  }

  @Override
  public String toString() {
    return "MAStageRelation [" + super.toString() + "]";
  }

}
