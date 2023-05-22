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
package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author GIP RECIA - Julien Gribonvald 25 oct. 2013
 */
@Embeddable
public class MappingAGroupeAPersonneId implements Serializable {

	/** Identifiant de serialisation */
	private static final long serialVersionUID = -3355562175987895747L;

	/** The personne to associate to the groupe. */
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "APERSONNE_ID", nullable = false)
	private APersonne personne;

	/** The groupe to associate with the person. */
	@ManyToOne
	@JoinColumn(name = "AGROUPEOFAPERS_ID", nullable = false)
	private AGroupeOfAPersonne groupe;

	/**
	 * Contructor of the object MappingAGroupeAPersonneId.java.
	 */
	public MappingAGroupeAPersonneId() {
		super();
	}

	/**
	 * Contructor of the object MappingAGroupeAPersonne.java.
	 *
	 * @param groupe
	 * @param personne
	 */
	public MappingAGroupeAPersonneId(final APersonne personne, final AGroupeOfAPersonne groupe) {
		super();
		this.groupe = groupe;
		this.personne = personne;
	}

	/**
	 * Getter of member personne.
	 *
	 * @return <code>APersonne</code> the attribute personne
	 */
	public APersonne getPersonne() {
		return personne;
	}

	/**
	 * Setter of attribute personne.
	 *
	 * @param personne the attribute personne to set
	 */
	public void setPersonne(final APersonne personne) {
		this.personne = personne;
	}

	/**
	 * Getter of member groupe.
	 *
	 * @return <code>AGroupeOfAPersonne</code> the attribute groupe
	 */
	public AGroupeOfAPersonne getGroupe() {
		return groupe;
	}

	/**
	 * Setter of attribute groupe.
	 *
	 * @param groupe the attribute groupe to set
	 */
	public void setGroupe(final AGroupeOfAPersonne groupe) {
		this.groupe = groupe;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupe == null) ? 0 : groupe.hashCode());
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingAGroupeAPersonneId other = (MappingAGroupeAPersonneId) obj;
		if (groupe == null) {
			if (other.groupe != null)
				return false;
		} else if (!groupe.equals(other.groupe))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		return true;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MappingAGroupeAPersonneId [personne=");
		builder.append(personne.getId());
		builder.append(", groupe=");
		builder.append(groupe.getId());
		builder.append("]");
		return builder.toString();
	}

}
