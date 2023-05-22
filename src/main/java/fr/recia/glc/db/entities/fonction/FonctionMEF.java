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

import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.Etablissement;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Ensemble des MEF associés à un enseignant pour un établissement.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>mef.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class FonctionMEF extends AFonction {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 4906542086808245030L;

	//Attributs

		//Relations
	/** Relation unidirectionnelle. */
	@ManyToOne
	@JoinColumn(name = "etablissement_fk")
	private Etablissement etablissement;

	/** Relation unidirectionnelle. */
	/* @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "fonctions_mefs",
			joinColumns =
				@JoinColumn(name = "FONCTIONMEF_ID", referencedColumnName = "ID"),
			inverseJoinColumns =
				@JoinColumn(name = "MEF_ID", referencedColumnName = "ID"))
	private Set<MEF> mefs = new HashSet<>();*/

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "fonctions_mefs", joinColumns =
			@JoinColumn(name = "FONCTIONMEF_ID", referencedColumnName = "ID"))
	private Set<MappingFonctionMEFMEF> mefs = new HashSet<>();

	//Constructeurs
	/**
	 * Constructeur de l'objet FonctionMEF.java.
	 */
	public FonctionMEF() {
		super();
		this.setCategorie(CategorieFonction.MEF);
	}
	/**
	 * Constructeur de l'objet FonctionMEF.java.
	 * @param mefs Liste des mefs.
	 * @param etablissement l'établissement associé à l'enseignement de ces mef
	 * @param personne la personne ayant la fonction d'enseigner associé à ce mefs.
	 * @param source Source d'alimentation gérant cette fonction.
	 */
	public FonctionMEF(final Set<MappingFonctionMEFMEF> mefs, final Etablissement etablissement,
                     final APersonne personne, final String source) {
		super(CategorieFonction.MEF, personne, source);
		this.mefs = mefs;
		this.etablissement = etablissement;
	}

	//Accesseurs
  //Relations
	/**
	 * Getter du membre etablissement.
	 * @return <code>Etablissement</code> le membre etablissement.
	 */
	public Etablissement getEtablissement() {
		return this.etablissement;
	}

	/**
	 * Setter du membre etablissement.
	 * @param etablissement la nouvelle valeur du membre etablissement.
	 */
	public void setEtablissement(final Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * Getter of member mefs.
	 * @return <code>Set<MappingFonctionMEFMEF></code> the attribute mefs
	 */
	public Set<MappingFonctionMEFMEF> getMefs() {
		return mefs;
	}

	/**
	 * Setter of attribute mefs.
	 * @param mefs the attribute mefs to set
	 */
	public void setMefs(final Set<MappingFonctionMEFMEF> mefs) {
		this.mefs = mefs;
	}

	/**
	 * Ajoute un Mef à la liste.
	 * @param mef le mef à ajouter.
	 */
	public void addMef(final MappingFonctionMEFMEF mef) {
		this.mefs.add(mef);
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.fonction.AFonction#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FonctionMEF [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.etablissement);
		sb.append(", ");
		sb.append(this.mefs);
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
		int result = super.hashCode();
		if (this.etablissement == null) {
			result = prime * result;
		} else {
			result = prime * result + this.etablissement.hashCode();
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
		if (!super.equals(obj)) { return false; }
		if (!(obj instanceof FonctionMEF)) { return false; }
		final FonctionMEF other = (FonctionMEF) obj;
		if (this.etablissement == null) {
			if (other.etablissement != null) { return false; }
		} else if (!this.etablissement.equals(other.etablissement)) { return false; }
		return true;
	}

}
