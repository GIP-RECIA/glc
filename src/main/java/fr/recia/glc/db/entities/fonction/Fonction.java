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

import fr.recia.glc.db.entities.education.Discipline;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.AStructure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Bean - Entity.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>filiere, structure, personne (discipline pour un enseignant normalement), source.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 9 juin 08
 */
@Entity
public class Fonction extends AFonction {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -4336375008100003335L;

	//Attributs

	//Relations
	/** Relation unidirectionnelle.
	 * Discipline de poste d'un enseignant ou d'un personnel d'établissement.*/
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "discipline_poste_fk")
	private Discipline disciplinePoste;

	/** Relation unidirectionnelle.
	 * Fonction filière, N_FONCTION_FILIERE. */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "filiere_fk")
	private TypeFonctionFiliere filiere;

	/** Relation unidirectionnelle.
	 * Structure d'exercice de la fonction. */
	@ManyToOne
	@JoinColumn(name = "astructure_fk")
	private AStructure structure;

	//Constructeurs
	/**
	 * Constructeur de l'objet Fonction.java.
	 */
	public Fonction() {
		super();
		this.setCategorie(CategorieFonction.Fonction);
	}

	/**
	 * Constructeur de l'objet Fonction.java.
	 * @param filiere Fonction filière, N_FONCTION_FILIERE.
	 * @param structure Structure d'exercice de la fonction.
	 * @param personne Personne ayant cette fonction.
	 * @param source Source d'alimentation gérant cette fonction.
	 */
	public Fonction(final TypeFonctionFiliere filiere, final AStructure structure,
                  final APersonne personne, final String source) {
		super(CategorieFonction.Fonction, personne, source);
		this.filiere = filiere;
		this.structure = structure;
	}

	/**
	 * Constructeur de l'objet Fonction.java.
	 * @param disciplinePoste Discipline de poste d'un enseignant ou d'un personnel d'établissement.
	 * @param filiere Fonction filière, N_FONCTION_FILIERE.
	 * @param structure Structure d'exercice de la fonction.
	 * @param personne Personne ayant cette fonction.
	 * @param source Source d'alimentation gérant cette fonction.
	 */
	public Fonction(final Discipline disciplinePoste, final TypeFonctionFiliere filiere,
                  final AStructure structure, final APersonne personne, final String source) {
		super(CategorieFonction.Fonction, personne, source);
		this.disciplinePoste = disciplinePoste;
		this.filiere = filiere;
		this.structure = structure;
	}

	//Accesseurs

	//Relations
	/**
	 * Getter du membre disciplinePoste.
	 * @return <code>Discipline</code> le membre disciplinePoste
	 */
	public Discipline getDisciplinePoste() {
		return this.disciplinePoste;
	}

	/**
	 * Setter du membre disciplinePoste.
	 * @param disciplinePoste la nouvelle valeur du membre disciplinePoste
	 */
	public void setDisciplinePoste(final Discipline disciplinePoste) {
		this.disciplinePoste = disciplinePoste;
	}

	/**
	 * Getter du membre filiere.
	 * @return <code>TypeFonctionFiliere</code> le membre filiere
	 */
	public TypeFonctionFiliere getFiliere() {
		return this.filiere;
	}

	/**
	 * Setter du membre filiere.
	 * @param filiere la nouvelle valeur du membre filiere
	 */
	public void setFiliere(final TypeFonctionFiliere filiere) {
		this.filiere = filiere;
	}
	/**
	 * Getter du membre structure.
	 * @return <code>AStructure</code> le membre structure
	 */
	public AStructure getStructure() {
		return this.structure;
	}

	/**
	 * Setter du membre structure.
	 * @param structure la nouvelle valeur du membre structure
	 */
	public void setStructure(final AStructure structure) {
		this.structure = structure;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.fonction.AFonction#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Fonction [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.disciplinePoste);
		sb.append(", ");
		sb.append(this.filiere);
		sb.append(", ");
		sb.append(this.structure);
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
		if (this.filiere == null) {
			result = prime * result;
		} else {
			result = prime * result + this.filiere.hashCode();
		}
		if (this.disciplinePoste == null) {
			result = prime * result;
		} else {
			result = prime * result + this.disciplinePoste.hashCode();
		}
		if (this.structure == null) {
			result = prime * result;
		} else {
			result = prime * result + this.structure.hashCode();
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
		if (!(obj instanceof Fonction)) { return false; }
		final Fonction other = (Fonction) obj;
		if (this.filiere == null) {
			if (other.filiere != null) { return false; }
		} else if (!this.filiere.equals(other.filiere)) { return false; }
		if (this.disciplinePoste == null) {
			if (other.disciplinePoste != null) { return false; }
		} else if (!this.disciplinePoste.equals(other.disciplinePoste)) { return false; }
		if (this.structure == null) {
			if (other.structure != null) { return false; }
		} else if (!this.structure.equals(other.structure)) { return false; }
		return true;
	}

}
