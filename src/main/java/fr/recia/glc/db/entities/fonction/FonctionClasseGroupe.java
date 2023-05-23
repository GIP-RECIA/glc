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
import fr.recia.glc.db.entities.groupe.AGroupeOfFoncClasseGroupe;
import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Bean - Entity.
 * Fonction au sein d'une classe ou d'un groupe.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>type, personne, classegroupe, source.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 9 juin 08
 */
@Entity
public class FonctionClasseGroupe extends AFonction {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -1543953413958583108L;

	//Attributs
	/** Type énuméré du type de fonction. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I30)
	private TypeClasse type;

	//Relations
	/** Relation bidirectionnelle.
	 * Classe ou groupe concerné.*/
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH },
			fetch = FetchType.LAZY)
	@JoinColumn(name = "classe_groupe_fk")
	private AGroupeOfFoncClasseGroupe classeGroupe;

	//Constructeurs
	/**
	 * Constructeur de l'objet FonctionClasseGroupe.java.
	 */
	public FonctionClasseGroupe() {
		super();
		this.setCategorie(CategorieFonction.Classe);
	}

	/**
	 * Constructeur de l'objet FonctionClasseGroupe.java.
	 * @param type Type énuméré du type de fonction.
	 * @param personne Personne ayant cette fonction.
	 * @param classeGroupe Classe ou groupe concerné.
	 * @param source Source d'alimentation gérant cette fonction.
	 */
	public FonctionClasseGroupe(final TypeClasse type, final APersonne personne,
			                        final AGroupeOfFoncClasseGroupe classeGroupe, final String source) {
		super(CategorieFonction.Classe, personne, source);
		this.type = type;
		this.classeGroupe = classeGroupe;
	}

	//Accesseurs
	/**
	 * Getter du membre type.
	 * @return <code>TypeClasse</code> le membre type
	 */
	public TypeClasse getType() {
		return this.type;
	}

	/**
	 * Setter du membre type.
	 * @param type la nouvelle valeur du membre type
	 */
	public void setType(final TypeClasse type) {
		this.type = type;
	}

	//Relations
	/**
	 * Getter du membre classeGroupe.
	 * @return <code>AGroupeOfFoncClasseGroupe</code> le membre classeGroupe
	 */
	public AGroupeOfFoncClasseGroupe getClasseGroupe() {
		return this.classeGroupe;
	}

	/**
	 * Setter du membre classeGroupe.
	 * @param classeGroupe la nouvelle valeur du membre classeGroupe
	 */
	public void setClasseGroupe(final AGroupeOfFoncClasseGroupe classeGroupe) {
		this.classeGroupe = classeGroupe;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.fonction.AFonction#toString()
	 */
	@Override
	public String toString() {
    /* String classeGroupe =
      this.classeGroupe != null && this.classeGroupe.getCn() != null && this.classeGroupe.getProprietaire() != null ?
        ", ClasseGroupe [" + this.classeGroupe.getCn() +
        ", Etablissement[" + this.classeGroupe.getProprietaire().getCleJointure() +
        "]]" : "";*/
    return "FonctionClasseGroupe [" +
      super.toString() + ", " +
      this.type + ", " +
      this.classeGroupe +
      // classeGroupe +
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
		int result = super.hashCode();
		if (this.classeGroupe == null) {
			result = prime * result;
		} else {
			result = prime * result + this.classeGroupe.hashCode();
		}
		if (this.type == null) {
			result = prime * result;
		} else {
			result = prime * result + this.type.hashCode();
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
		if (!(obj instanceof FonctionClasseGroupe)) { return false; }
		final FonctionClasseGroupe other = (FonctionClasseGroupe) obj;
		if (this.classeGroupe == null) {
			if (other.classeGroupe != null) { return false; }
		} else if (!this.classeGroupe.equals(other.classeGroupe)) { return false; }
		if (this.type == null) {
			if (other.type != null) { return false; }
		} else if (!this.type.equals(other.type)) { return false; }
		return true;
	}

}
