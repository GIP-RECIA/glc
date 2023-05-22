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

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * Bean - Entity.
 * Description abstraite d'un groupe.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>catégorie, cn.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class AGroupe extends AbstractEntity {

	//Attributs
	/** Type de groupe. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I30)
	private CategorieGroupe categorie;
	/** Année scolaire de validité de l'objet.
	 * Année à la rentrée de septembre.*/
	@Temporal(TemporalType.DATE)
	private Date anneeScolaire;
	/** Nom unique de groupe, peut servir comme identifiant au sein d'un établissement. */
	private String cn;
	/** Description du groupe. */
	private String description;
	/** Source ayant créé le groupe. */
	@Column(length = IntConst.ISOURCE)
	private String source;

	//Constructeurs
	/**
	 * Constructeur de l'objet AGroupe.java.
	 */
	public AGroupe() {
		super();
	}

	/**
	 * Constructeur de l'objet AGroupe.java.
	 * @param cn Nom unique de groupe, peut servir comme identifiant.
	 * @param categorie Type de groupe.
	 * @param source Source ayant créé l'objet.
	 */
	public AGroupe(final String cn, final CategorieGroupe categorie, final String source) {
		super();
		this.cn = cn;
		this.categorie = categorie;
		this.source = source;
	}

	//Accesseurs
	/**
	 * Getter du membre categorie.
	 * @return <code>CategorieGroupe</code> le membre categorie.
	 */
	public CategorieGroupe getCategorie() {
		return this.categorie;
	}

	/**
	 * Setter du membre categorie.
	 * @param categorie la nouvelle valeur du membre categorie.
	 */
	public void setCategorie(final CategorieGroupe categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter of member anneeScolaire.
	 * @return <code>Date</code> the attribute anneeScolaire
	 */
	public Date getAnneeScolaire() {
		return anneeScolaire;
	}

	/**
	 * Setter of attribute anneeScolaire.
	 * @param anneeScolaire the attribute anneeScolaire to set
	 */
	public void setAnneeScolaire(final Date anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	/**
	 * Getter du membre cn.
	 * @return <code>String</code> le membre cn.
	 */
	public String getCn() {
		return this.cn;
	}

	/**
	 * Setter du membre cn.
	 * @param cn la nouvelle valeur du membre cn.
	 */
	public void setCn(final String cn) {
		this.cn = cn;
	}

	/**
	 * Getter du membre description.
	 * @return <code>String</code> le membre description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter du membre description.
	 * @param description la nouvelle valeur du membre description.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Getter du membre source.
	 * @return <code>String</code> le membre source.
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter du membre source.
	 * @param source la nouvelle valeur du membre source.
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AGroupe [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.categorie);
		sb.append(", ");
		sb.append(this.cn);
		sb.append(", ");
		sb.append(this.source);
		sb.append(", ");
		sb.append(this.anneeScolaire);
		sb.append(", ");
		sb.append(this.description);
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
		if (this.categorie == null) {
			result = prime * result;
		} else {
			result = prime * result + this.categorie.hashCode();
		}
		if (this.cn == null) {
			result = prime * result;
		} else {
			result = prime * result + this.cn.hashCode();
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AGroupe)) {
			return false;
		}
		final AGroupe other = (AGroupe) obj;
		if (this.categorie == null) {
			if (other.categorie != null) {
				return false;
			}
		} else if (!this.categorie.equals(other.categorie)) {
			return false;
		}
		if (this.cn == null) {
			if (other.cn != null) {
				return false;
			}
		} else if (!this.cn.equalsIgnoreCase(other.cn)) {
			return false;
		}
		if (this.source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!this.source.equals(other.source)) {
			return false;
		}
		return true;
	}

}
