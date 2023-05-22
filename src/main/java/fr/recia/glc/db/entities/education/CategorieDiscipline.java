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
package fr.recia.glc.db.entities.education;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Bean - Entity Catégorie de discipline de poste.
 * Représente la catégorie de poste de recrutement du personnel des structures (enseignant et non enseignant).
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>code, pivotDiscipline, source.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 9 juin 08
 */
@Entity
@Table(
		uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "source" }) }
	)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategorieDiscipline extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 1472567229480842811L;

	//Attributs
	/** Code de la catégorie de discipline.*/
	@Column(nullable = false, length = IntConst.I60)
	private String code;
	/** Libellé de la catégorie de discipline de poste. */
	private String pivotDiscipline;
	/** Source d'alimentation de la catégorie de discipline de poste. */
	@Column(nullable = false, length = IntConst.ISOURCE)
	private String source;

	//Relations
	/** Relation bidirectionnelle.
	 * Liste des personnes ayant cette catégorie de discipline.	 */
	/* @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorieDisciplines")
	private Set<APersonne> personnes = new HashSet<APersonne>();*/

	//Constructeurs
	/**
	 * Constructeur de l'objet CategorieDiscipline.java.
	 */
	public CategorieDiscipline() {
		super();
	}

	/**
	 * Constructeur de l'objet CategorieDiscipline.java.
	 * @param code Code de la catégorie de discipline.
	 * @param pivotDiscipline Libellé de la catégorie de discipline de poste.
	 * @param source La source d'alimentation de l'objet.
	 */
	public CategorieDiscipline(final String code, final String pivotDiscipline, final String source) {
		super();
		this.code = code;
		this.pivotDiscipline = pivotDiscipline;
		this.source = source;
	}

	//Accesseurs
	/**
	 * Getter du membre code.
	 * @return <code>String</code> le membre code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Setter du membre code.
	 * @param code la nouvelle valeur du membre code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Getter du membre pivotDiscipline.
	 * @return <code>String</code> le membre pivotDiscipline
	 */
	public String getPivotDiscipline() {
		return this.pivotDiscipline;
	}

	/**
	 * Setter du membre pivotDiscipline.
	 * @param pivotDiscipline la nouvelle valeur du membre pivotDiscipline
	 */
	public void setPivotDiscipline(final String pivotDiscipline) {
		this.pivotDiscipline = pivotDiscipline;
	}

	/**
	 * Getter of member source.
	 * @return <code>String</code> the attribute source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter of member source.
	 * @param source the source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Getter du membre personnes.
	 * @return <code>Set< APersonne ></code> le membre personnes.
	 */
  /* public Set<APersonne> getPersonnes() {
		return personnes;
	}*/

	/**
	 * Setter du membre personnes.
	 * @param personnes la nouvelle valeur du membre personnes.
	 */
  /* public void setPersonnes(final Set<APersonne> personnes) {
		this.personnes = personnes;
	}*/

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CategorieDiscipline [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.code);
		sb.append(", ");
		sb.append(this.pivotDiscipline);
		sb.append(", ");
		sb.append(this.source);
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
		if (this.code == null) {
			result = prime * result;
		} else {
			result = prime * result + this.code.hashCode();
		}
		if (this.pivotDiscipline == null) {
			result = prime * result;
		} else {
			result = prime * result + this.pivotDiscipline.hashCode();
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
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof CategorieDiscipline)) { return false; }
		final CategorieDiscipline other = (CategorieDiscipline) obj;
		if (this.code == null) {
			if (other.code != null) { return false; }
		} else if (!this.code.equals(other.code)) { return false; }
		if (this.pivotDiscipline == null) {
			if (other.pivotDiscipline != null) { return false; }
		} else if (!this.pivotDiscipline.equals(other.pivotDiscipline)) { return false; }
		if (this.source == null) {
			if (other.source != null) { return false; }
		} else if (!this.source.equals(other.source)) { return false; }
		return true;
	}

}
