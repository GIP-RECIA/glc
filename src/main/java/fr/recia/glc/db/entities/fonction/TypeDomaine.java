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
package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Entity;

/**
 * Bean - Entity.
 * Définition d'un domaine d'exercice pour une personne du service académique ou d'une collectivité locale.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>libelle.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class TypeDomaine extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -2360994897595556905L;

	//Attributs
	/** Nom du domaine d'exercice. */
	private String libelleDomaine;

	//Constructeurs
	/**
	 * Constructeur de l'objet TypeDomaine.java.
	 */
	public TypeDomaine() {
		super();
	}

	/**
	 * Constructeur de l'objet TypeDomaine.java.
	 * @param libelle Nom du domaine d'exercice.
	 */
	public TypeDomaine(final String libelle) {
		super();
		this.libelleDomaine = libelle;
	}

	//Accesseurs
	/**
	 * Getter du membre libelleDomaine.
	 * @return <code>String</code> le membre libelleDomaine.
	 */
	public String getLibelleDomaine() {
		return this.libelleDomaine;
	}

	/**
	 * Setter du membre libelleDomaine.
	 * @param libelleDomaine la nouvelle valeur du membre libelleDomaine.
	 */
	public void setLibelleDomaine(final String libelleDomaine) {
		this.libelleDomaine = libelleDomaine;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "TypeDomaine [" +
      super.toString() + ", " +
      this.libelleDomaine +
      "]";
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
		if (this.libelleDomaine == null) {
			result = prime * result;
		} else {
			result = prime * result + this.libelleDomaine.hashCode();
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
		if (!(obj instanceof TypeDomaine)) { return false; }
		final TypeDomaine other = (TypeDomaine) obj;
		if (this.libelleDomaine == null) {
			if (other.libelleDomaine != null) { return false; }
		} else if (!this.libelleDomaine.equals(other.libelleDomaine)) { return false; }
		return true;
	}

}
