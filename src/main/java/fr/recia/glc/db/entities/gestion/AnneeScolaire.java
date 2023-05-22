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

import fr.recia.glc.db.entities.common.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * Bean - Entity.
 * Description permettant de gérer les informations d'une année scolaire à un autre.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>anneeEnCours.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 12 juin 08
 */
@Entity
public class AnneeScolaire extends AbstractEntity {

	/**  Identifiant de sérialisation. */
	private static final long serialVersionUID = -1215737339076588552L;

	// Attributs
	/** Informe de l'année en cours, une seule insertion par année possible,
	 * année à la rentrée (de septembre). */
	@Column(unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date anneeEnCours;
	/** Date exacte du passage à l'année suivante pour les insertions. */
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date passageAnneeSuivante;
	/** Date de fin des autorisations des accés pour les utilisateurs de l'année scolaire précédente.
	 * Si null on y place la date de passageAnneeSuivante.*/
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date finAutorisation;
	/** Booleen indiquant l'insertion du complet des établissement en début d'année scolaire. */
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean etabMAJ;

	//Constructeurs
	/**
	 * Constructeur de l'objet AnneeScolaire.java.
	 */
	public AnneeScolaire() {
		super();
	}

	/**
	 * Constructeur de l'objet AnneeScolaire.java.
	 * @param anneeEnCours Année scolaire en cours, année à la rentrée (de septembre).
	 * @param passageAnneeSuivante Date de passage des insertions à l'année suivante.
	 * @param finAutorisation Date de fin des autorisations des accés
	 * pour les utilisateurs de l'année scolaire précédente.
	 */
	public AnneeScolaire(final Date anneeEnCours, final Date passageAnneeSuivante, final Date finAutorisation) {
		super();
		this.anneeEnCours = anneeEnCours;
		this.passageAnneeSuivante = passageAnneeSuivante;
		this.finAutorisation = finAutorisation;
	}

	//Accesseurs
	/**
	 * Getter du membre anneeEnCours.
	 * @return <code>Date</code> le membre anneeEnCours.
	 */
	public Date getAnneeEnCours() {
		return this.anneeEnCours;
	}

	/**
	 * Setter du membre anneeEnCours.
	 * @param anneeEnCours la nouvelle valeur du membre anneeEnCours.
	 */
	public void setAnneeEnCours(final Date anneeEnCours) {
		this.anneeEnCours = anneeEnCours;
	}

	/**
	 * Getter du membre passageAnneeSuivante.
	 * @return <code>Date</code> le membre passageAnneeSuivante.
	 */
	public Date getPassageAnneeSuivante() {
		return this.passageAnneeSuivante;
	}

	/**
	 * Setter du membre passageAnneeSuivante.
	 * @param passageAnneeSuivante la nouvelle valeur du membre passageAnneeSuivante.
	 */
	public void setPassageAnneeSuivante(final Date passageAnneeSuivante) {
		this.passageAnneeSuivante = passageAnneeSuivante;
	}

	/**
	 * Getter du membre finAutorisation.
	 * @return <code>Date</code> le membre finAutorisation.
	 */
	public Date getFinAutorisation() {
		return this.finAutorisation;
	}

	/**
	 * Setter du membre finAutorisation.
	 * @param finAutorisation la nouvelle valeur du membre finAutorisation.
	 */
	public void setFinAutorisation(final Date finAutorisation) {
		this.finAutorisation = finAutorisation;
	}

	/**
	 * Getter du membre etabMAJ.
	 * @return <code>boolean</code> le membre etabMAJ.
	 */
	public boolean isEtabMAJ() {
		return etabMAJ;
	}

	/**
	 * Setter du membre etabMAJ.
	 * @param etabMAJ la nouvelle valeur du membre etabMAJ.
	 */
	public void setEtabMAJ(final boolean etabMAJ) {
		this.etabMAJ = etabMAJ;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AnneeScolaire [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.anneeEnCours);
		sb.append(", ");
		sb.append(this.passageAnneeSuivante);
		sb.append(", ");
		sb.append(this.finAutorisation);
		sb.append(", ");
		sb.append(this.etabMAJ);
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
		if (this.anneeEnCours == null) {
			result = prime * result;
		} else {
			result = prime * result + this.anneeEnCours.hashCode();
		}
		if (this.finAutorisation == null) {
			result = prime * result;
		} else {
			result = prime * result + this.finAutorisation.hashCode();
		}
		if (this.passageAnneeSuivante == null) {
			result = prime * result;
		} else {
			result = prime * result + this.passageAnneeSuivante.hashCode();
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
		if (!(obj instanceof AnneeScolaire)) {
			return false;
		}
		final AnneeScolaire other = (AnneeScolaire) obj;
		if (this.anneeEnCours == null) {
			if (other.anneeEnCours != null) {
				return false;
			}
		} else if (!this.anneeEnCours.equals(other.anneeEnCours)) {
			return false;
		}
		if (this.finAutorisation == null) {
			if (other.finAutorisation != null) {
				return false;
			}
		} else if (!this.finAutorisation.equals(other.finAutorisation)) {
			return false;
		}
		if (this.passageAnneeSuivante == null) {
			if (other.passageAnneeSuivante != null) {
				return false;
			}
		} else if (!this.passageAnneeSuivante.equals(other.passageAnneeSuivante)) {
			return false;
		}
		return true;
	}

}
