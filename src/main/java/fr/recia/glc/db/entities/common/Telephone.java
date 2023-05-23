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

import fr.recia.glc.db.commons.IntConst;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Set;

/**
 * Bean - Entity.
 * Décrit un numero de téléphone ainsi que son type (Fax, fixe perso, fixe pro, mobile).
 * Il ne peut y avoir qu'un seul type de numéro utilisé à la fois.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>numero, type, source.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 5 juin 08
 */
@Entity
public class Telephone extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 4003475729881037673L;

	//Attributs
	/** Numéro de téléphone. */
	@Column(nullable = false, length = IntConst.I20)
	private String numero;
	/** Type du numéro de téléphone. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I20)
	private TelephoneType type;
	/** Source de l'alimentation. */
	@Column(nullable = false, length = IntConst.ISOURCE)
	private String source;
	/** si téléphone utilisé. */
	@Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
	private boolean used;

	//Constructeurs
	/**
	 * Constructeur de l'objet Telephone.java.
	 */
	public Telephone() {
		super();
	}

	/**
	 * Constructeur de l'objet Telephone.java avec les champs obligatoires.
	 * @param numero Numéro de téléphone.
	 * @param type Type du numéro de téléphone (FAX, mobile, fixe pro, fixe pers).
	 * @param source source d'alimentation
	 * @param used Si le numéro est utilisé ou non.
	 */
	public Telephone(final String numero, final TelephoneType type, final String source, final boolean used) {
		super();
		this.numero = numero;
		this.type = type;
		this.source = source;
		this.used = used;
	}

	//Accesseurs
	/**
	 * Getter du membre numero.
	 * @return <code>String</code> le membre numero
	 */
	public String getNumero() {
		return this.numero;
	}

	/**
	 * Setter du membre numero.
	 * @param numero la nouvelle valeur du membre numero
	 */
	public void setNumero(final String numero) {
		this.numero = numero;
	}

	/**
	 * Getter du membre type.
	 * @return <code>TelephoneType</code> le membre type
	 */
	public TelephoneType getType() {
		return this.type;
	}

	/**
	 * Setter du membre type.
	 * @param type la nouvelle valeur du membre type
	 */
	public void setType(final TelephoneType type) {
		this.type = type;
	}

	/**
	 * Getter du membre source
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 *  Setter du membre source
	 * @param source the source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * @return the used
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(final boolean used) {
		this.used = used;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "Telephone [" +
      super.toString() + ", " +
      this.type + ", " +
      this.numero + ", " +
      this.source + ", " +
      this.used +
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
		if (numero == null) {
			result = prime * result;
		} else {
			result = prime * result + numero.hashCode();
		}
		if (type == null) {
			result = prime * result;
		} else {
			result = prime * result + type.hashCode();
		}
		if (source == null) {
			result = prime * result;
		} else {
			result = prime * result + source.hashCode();
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
		if (!(obj instanceof Telephone)) {
			return false;
		}
		final Telephone other = (Telephone) obj;
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		return true;
	}

	/**
	 * Teste equals mais en ignorant la source d'alimentation
	 * @param obj l'instance le l'object à comparer.
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 */
	public boolean equalsIgnoreSource(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Telephone)) {
			return false;
		}
		final Telephone other = (Telephone) obj;
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	/**
	 * Test if a set of Telephone contains a telephone without checking the source.
	 * @param collection
	 * @param object
	 * @return boolean
	 */
	public static boolean containsWithoutSource(final Set<Telephone> collection, final Telephone object) {
		for (Telephone item : collection) {
			if (item.equalsIgnoreSource(object))
				return true;
		}
		return false;
	}

}
