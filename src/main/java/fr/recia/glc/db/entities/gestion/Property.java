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
package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Bean - Entity.
 * Description permettant de gérer les propriétés spécifiques à l'application.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD></DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 5 oct. 09
 */
@Entity
public class Property extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -4982261158366953925L;

	//Attributs
	/** Name of the property. */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true, length = IntConst.I128)
	private PropertyName name;

	/** Value of the property. */
	private String value;

	/**
	 * Constructeur de l'objet Property.java.
	 */
	public Property() {
		super();
	}

	/**
	 * Constructeur de l'objet Property.java.
	 * @param name
	 * @param value
	 */
	public Property(final PropertyName name, final String value) {
		super();
		this.name = name;
		this.value = value;
	}

	//Accesseurs
	/**
	 * Getter du membre name.
	 * @return <code>String</code> le membre name.
	 */
	public PropertyName getName() {
		return name;
	}

	/**
	 * Setter du membre name.
	 * @param name la nouvelle valeur du membre name.
	 */
	public void setName(final PropertyName name) {
		this.name = name;
	}

	/**
	 * Getter du membre value.
	 * @return <code>PropertyValue</code> le membre value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Setter du membre value.
	 * @param value la nouvelle valeur du membre value.
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "Property [" +
      super.toString() + ", " +
      this.name + ", " +
      this.value +
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
		if (name == null) {
			result = prime * result;
		} else {
			result = prime * result + name.hashCode();
		}
		if (value == null) {
			result = prime * result;
		} else {
			result = prime * result + value.hashCode();
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Property)) {
			return false;
		}
		final Property other = (Property) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}
