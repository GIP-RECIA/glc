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

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Entity;

/**
 * Bean - Entity.
 * Objet définissant le ministère de tutelle d'un établissement.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>libelle.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 20 juin 08
 */
@Entity
public class MinistereTutelle extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -1621449511844521300L;

	//Attributs
	/** Nom du ministère. */
	private String ministere;

	//Constructeurs
	/**
	 * Constructeur de l'objet MinistereTutelle.java.
	 */
	public MinistereTutelle() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur de l'objet MinistereTutelle.java.
	 * @param ministere Ministère de tutelle.
	 */
	public MinistereTutelle(final String ministere) {
		super();
		this.ministere = ministere;
	}

	//Accesseurs
	/**
	 * Getter du membre ministere.
	 * @return <code>String</code> le membre ministere.
	 */
	public String getMinistere() {
		return this.ministere;
	}

	/**
	 * Setter du membre ministere.
	 * @param ministere la nouvelle valeur du membre ministere.
	 */
	public void setMinistere(final String ministere) {
		this.ministere = ministere;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractSimpleEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MinistereTutelle [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.ministere);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Donne la valeur de hachage de l'instance.
	 * @return <code>int</code> La valeur du hash.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.ministere == null) {
			result = prime * result;
		} else {
			result = prime * result + this.ministere.hashCode();
		}
		return result;
	}

	/**
	 * Teste si un objet est égal à cette instance.
	 * @param obj l'instance le l'object à comparer.
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof MinistereTutelle)) { return false; }
		final MinistereTutelle other = (MinistereTutelle) obj;
		if (this.ministere == null) {
			if (other.ministere != null) { return false; }
		} else if (!this.ministere.equals(other.ministere)) { return false; }
		return true;
	}

}
