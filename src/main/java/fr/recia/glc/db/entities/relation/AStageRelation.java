package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.entities.common.enums.CategorieRelation;
import fr.recia.glc.db.entities.common.enums.TypeStage;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.personne.Eleve;
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
public abstract class AStageRelation extends AMappingRelation {

  /**
   * Type énuméré du type de la relation de stage.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I30)
  private TypeStage type;

  /**
   * Empty Constructor, must not be used.
   */
  public AStageRelation() {
    super();
  }

  /**
   * @param source
   * @param tuteurStage
   * @param eleve
   * @param type
   * @param typeStage
   */
  public AStageRelation(final String source, final APersonne tuteurStage, final Eleve eleve,
                        final TypeStage type, final CategorieRelation typeStage) {
    super(source, tuteurStage, eleve, typeStage);
    this.type = type;
  }

  @Override
  public String toString() {
    return "StageRelation [" +
      type + ", " +
      super.toString() +
      "]";
  }

}
