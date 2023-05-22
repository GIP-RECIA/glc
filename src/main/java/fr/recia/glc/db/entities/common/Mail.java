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

/**
 * Bean - Entity. Décrit une adresse mail ainsi que son usage.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>adresse, usage.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 5 février 2016
 */
@Entity
public class Mail extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 35L;

	// Attributs
	/** adresse mail. */
	@Column(nullable = false)
	private String adresse;
	/** Type de l'adresse. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I20)
	private MailType type;
	/** Source de l'alimentation. */
	@Column(nullable = false, length = IntConst.ISOURCE)
	private String source;
	/** si adresse validée */
	@Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
	private boolean validated;

	// Constructeurs
	/**
	 * Constructeur de l'objet Mail.java.
	 */
	public Mail() {
		super();
	}

	/**
	 * Constructeur de l'objet Mail.java.
	 *
	 * @param adresse   Adresse mail.
	 * @param type      Le type d'usage.
	 * @param source    La source dd'alimentation.
	 * @param validated Si l'adresse a été vérifiée ou si de source sûre.
	 */
	public Mail(String adresse, MailType type, String source, boolean validated) {
		super();
		this.adresse = adresse;
		this.type = type;
		this.source = source;
		this.validated = validated;
	}

	// Accesseurs
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the type
	 */
	public MailType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final MailType type) {
		this.type = type;
	}

	/**
	 * @return the validated
	 */
	public boolean isValidated() {
		return validated;
	}

	/**
	 * @param validated the validated to set
	 */
	public void setValidated(final boolean validated) {
		this.validated = validated;
	}

	/**
	 * Getter du membre source
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter du membre source
	 *
	 * @param source the source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 *
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Mail [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.adresse);
		sb.append(", ");
		sb.append(this.type);
		sb.append(", ");
		sb.append(this.validated);
		sb.append(", ");
		sb.append(this.source);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Donne la valeur de hachage de l'instance.
	 *
	 * @return <code>int</code> La valeur du hash.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (adresse == null) {
			result = prime * result;
		} else {
			result = prime * result + adresse.hashCode();
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
	 *
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
		if (!(obj instanceof Mail)) {
			return false;
		}
		final Mail other = (Mail) obj;
		if (adresse == null) {
			if (other.adresse != null) {
				return false;
			}
		} else if (!adresse.equals(other.adresse)) {
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
	 *
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
		if (!(obj instanceof Mail)) {
			return false;
		}
		final Mail other = (Mail) obj;
		if (adresse == null) {
			if (other.adresse != null) {
				return false;
			}
		} else if (!adresse.equals(other.adresse)) {
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
	 * Teste equals uniquement sur l'adresse
	 *
	 * @param obj l'instance le l'object à comparer.
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 */
	public boolean equalsOnAdresse(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Mail)) {
			return false;
		}
		final Mail other = (Mail) obj;
		if (adresse == null) {
			if (other.adresse != null) {
				return false;
			}
		} else if (!adresse.equals(other.adresse)) {
			return false;
		}
		return true;
	}

	/**
	 * Test if a set of Mail contains a mail without checking the source.
	 *
	 * @param collection
	 * @param object
	 * @return boolean
	 */
	/*public static boolean containsWithoutSource(final Set<Mail> collection, final Mail object) {
    for (Mail item : collection) {
      if (item.equalsIgnoreSource(object))
        return true;
    }
    return false;
  }*/

}
