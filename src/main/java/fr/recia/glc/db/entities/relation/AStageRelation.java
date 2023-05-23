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

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.personne.Eleve;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Set;

/**
 * @author GIP RECIA - Julien Gribonvald
 */
@Entity
public abstract class AStageRelation extends AMappingRelation {

	/** Identifiant de serialisation*/
	private static final long serialVersionUID = -8179648444630891004L;
	/** Type énuméré du type de la relation de stage. */
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

	/**
	 * getter of Eleve to avoid confusion between personne1 and personne2 type
	 * @return <code>Eleve</code>
	 */
	public Eleve getEleve() {
		return (Eleve) this.getPersonne2();
	}

	/**
	 * @return the type
	 */
	public TypeStage getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeStage type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
    return "StageRelation [" +
      type + ", " +
      super.toString() +
      "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AStageRelation other = (AStageRelation) obj;
		if (type != other.type)
			return false;
		return true;
	}

	/**
	 * Check if the object is equals without source comparison
	 * @param obj
	 * @return true is equals else false.
	 */
	public boolean equalsIgnoreSource(Object obj) {
		if (this == obj)
			return true;
		if (!super.equalsIgnoreSource(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AStageRelation other = (AStageRelation) obj;
		if (type != other.type)
			return false;
		return true;
	}

	/**
	 * Test if a set contains a groupe.
	 * @param collection The set of MappingAPersonneAPersonne where to check.
	 * @param object the groupe To find.
	 * @return true if contains, else false.
	 */
	public static boolean containsWithoutSource(final Set<AStageRelation> collection, final AStageRelation object) {
		for (AStageRelation item : collection) {
			if (item.equalsIgnoreSource(object))
				return true;
		}
		return false;
	}

}
