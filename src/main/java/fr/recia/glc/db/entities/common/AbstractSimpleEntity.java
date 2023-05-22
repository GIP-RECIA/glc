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
package fr.recia.glc.db.entities.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Object Abstrait Simple Hérité par les bean - Entity,
 *  n'ayant pas besoin de gestions spécifique par rapport aux dates de création et modification.
 * Cela permet de gérer de façon automatique les id, date de création, date de modification et la version.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>id, version, dateCreation, dateModification.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 5 juin 08
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractSimpleEntity implements Serializable {

	//Attributs
	/** Id de l'objet géré de façon automatique avec clé généré
	 * si non renseigné lors du persist. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//Constructeur
	/**
	 * Constructeur de l'objet AbstractEntity.java.
	 */
	public AbstractSimpleEntity() {
		super();
	}

	//Accesseurs
	/**
	 * Getter du membre id.
	 * @return <code>long</code> le membre id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Setter du membre id.
	 * @param id la nouvelle valeur du membre id
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AbstractSimpleEntity [");
		sb.append(this.id);
		sb.append("]");
		return sb.toString();
	}

}
