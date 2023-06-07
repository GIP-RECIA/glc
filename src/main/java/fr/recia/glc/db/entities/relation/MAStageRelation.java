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
