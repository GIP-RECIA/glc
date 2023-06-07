package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.entities.common.enums.CategorieRelation;
import fr.recia.glc.db.entities.common.enums.TypeStage;
import fr.recia.glc.db.entities.personne.Eleve;
import fr.recia.glc.db.entities.personne.Enseignant;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ENSTUTSTAGE")
@Getter
@Setter
public class EnsTutStageRelation extends AStageRelation {

  /**
   * Empty Constructor, must not be used.
   */
  public EnsTutStageRelation() {
    super();
  }

  /**
   * @param source
   * @param tuteurStage
   * @param eleve
   */
  public EnsTutStageRelation(final String source, final Enseignant tuteurStage, final Eleve eleve) {
    super(source, tuteurStage, eleve, TypeStage.Enseignant_tuteur, CategorieRelation.EnsTutStage);
  }

  @Override
  public String toString() {
    return "EnsTutStageRelation [" + super.toString() + "]";
  }

}
