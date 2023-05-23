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

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.education.MEF;
import org.hibernate.annotations.Parent;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

/**
 * Embedded Object.
 * Définition de lien MEF avec la source.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>source, MEF, fonctionMEF.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 22 Octobre 2013
 */
@Embeddable
public class MappingFonctionMEFMEF implements Serializable {

	/** Identifiant de sérialisation.*/
	private static final long serialVersionUID = -1823349869867433846L;

	/** Entity parent fonctionMEF */
	@Parent
	@Column(name = "FONCTIONMEF_ID", nullable = false)
	private FonctionMEF fonctionMEF;

	/** Source d'alimentation de l'association. */
	@Basic
	@Column(length = IntConst.ISOURCE, name = "SOURCE", nullable = false, unique = false)
	private String source;

	/** Si l'enseignant est responsable de formation. */
	@Basic
	@Column(nullable = false, columnDefinition = "BIT not null DEFAULT false", name = "RESPONSABLE")
	private boolean isResponsable;

	/** Relation avec le MEF. */
	@OneToOne
	@JoinColumn(name = "MEF_ID", nullable = false)
	private MEF mef;

	/**
	 * Contructor of the object MappingFonctionMEFMEF.java.
	 */
	public MappingFonctionMEFMEF() {
		super();
	}

	/**
	 * Contructor of the object MappingFonctionMEFMEF.java.
	 * @param source
	 * @param mef
	 * @param isResponsable
	 */
	public MappingFonctionMEFMEF(final String source, final MEF mef, final boolean isResponsable) {
		super();
		this.source = source;
		this.mef = mef;
		this.isResponsable = isResponsable;
	}

	/**
	 * Getter of member fonctionMEF.
	 * @return <code>FonctionMEF</code> the attribute fonctionMEF
	 */
	public FonctionMEF getFonctionMEF() {
		return fonctionMEF;
	}

	/**
	 * Setter of attribute fonctionMEF.
	 * @param fonctionMEF the attribute fonctionMEF to set
	 */
	public void setFonctionMEF(final FonctionMEF fonctionMEF) {
		this.fonctionMEF = fonctionMEF;
	}

	/**
	 * Getter of member source.
	 * @return <code>String</code> the attribute source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter of attribute source.
	 * @param source the attribute source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Getter of member mef.
	 * @return <code>MEF</code> the attribute mef
	 */
	public MEF getMef() {
		return mef;
	}

	/**
	 * Setter of attribute mef.
	 * @param mef the attribute mef to set
	 */
	public void setMef(final MEF mef) {
		this.mef = mef;
	}

	/**
	 * Getter of member isResponsable.
	 * @return <code>boolean</code> the attribute isResponsable
	 */
	public boolean isResponsable() {
		return isResponsable;
	}

	/**
	 * Setter of attribute isResponsable.
	 * @param isResponsable the attribute isResponsable to set
	 */
	public void setIsResponsable(boolean isResponsable) {
		this.isResponsable = isResponsable;
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
		if (this.mef == null) {
			result = prime * result;
		} else {
			result = prime * result + mef.hashCode();
		}
		if (this.fonctionMEF == null) {
			result = prime * result;
		} else {
			result = prime * result + fonctionMEF.hashCode();
		}
		if (this.source == null) {
			result = prime * result;
		} else {
			result = prime * result + this.source.hashCode();
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingFonctionMEFMEF other = (MappingFonctionMEFMEF) obj;
		if (mef == null) {
			if (other.mef != null)
				return false;
		} else if (!mef.equals(other.mef))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	/**
	 * Teste si un objet est égal à cette instance en ignorant l'attribut source.
	 * @param obj l'instance de l'object à comparer.
	 * @param onCodeMEFOnly Pour vérifier uniquement sur le code MEF
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 */
	public boolean equalsIgnoreSource(Object obj, boolean ignoreLibelleMEF) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingFonctionMEFMEF other = (MappingFonctionMEFMEF) obj;
		if (mef == null) {
			if (other.mef != null)
				return false;
		} else if (!ignoreLibelleMEF && !mef.equals(other.mef)) {
			return false;
		} else if (ignoreLibelleMEF && !mef.equalsOnCode(other.mef))
			return false;
		return true;
	}

	/**
	 * Methods to chek is the MappingFonctionMEFMEF collection contains a MEF.
	 * @param collection
	 * @param object
	 * @return true if found, else false.
	 */
	/* public static boolean containsWithoutSource(final Collection<MappingFonctionMEFMEF> collection,
			final MappingFonctionMEFMEF object, boolean ignoreLibelleMEF) {
		for (MappingFonctionMEFMEF item : collection) {
			if (item.equalsIgnoreSource(object, ignoreLibelleMEF)) {
				return true;
			}
		}

		return false;
	}*/

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
    return "MappingFonctionMEFMEF [" +
      this.source + ", " +
      this.mef.toString() +
      "]";
	}

}
