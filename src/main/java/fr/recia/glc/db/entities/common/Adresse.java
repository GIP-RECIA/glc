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
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * Bean - Entity.
 * Adresse d'une personne ou d'une structure.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD></DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 3 Juin 2008
 */
@Embeddable
public class Adresse implements Serializable {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -4804050923555389004L;

	//Attributs
	/** Champ libre d'une adresse. */
	private String adresse;
	/** Code postal. */
	@Column(length = IntConst.I40)
	private String codePostal;
	/** Nom de la ville. */
	private String ville;
	/** Boite postale dans le cas d'une structure. */
	@Column(length = IntConst.I40)
	private String boitePostale;
	/** Pays.*/
	private String pays;

	//Constructeurs
	/**
	 * Constructeur de l'objet Adresse.java.
	 */
	public Adresse() {
		super();
	}

	/**
	 * Constructeur de l'objet Adresse.java.
	 * @param adresse Champ libre d'une adresse.
	 * @param codePostal Code postal.
	 * @param ville Nom de la ville.
	 */
	public Adresse(final String adresse, final String codePostal, final String ville) {
		super();
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	//Accesseurs
	/**
	 * Getter du membre adresse.
	 * @return <code>String</code> le membre adresse
	 */
	public String getAdresse() {
		return this.adresse;
	}

	/**
	 * Setter du membre adresse.
	 * @param adresse la nouvelle valeur du membre adresse
	 */
	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Getter du membre codePostal.
	 * @return <code>String</code> le membre codePostal
	 */
	public String getCodePostal() {
		return this.codePostal;
	}

	/**
	 * Setter du membre codePostal.
	 * @param codePostal la nouvelle valeur du membre codePostal
	 */
	public void setCodePostal(final String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Getter du membre ville.
	 * @return <code>String</code> le membre ville
	 */
	public String getVille() {
		return this.ville;
	}

	/**
	 * Setter du membre ville.
	 * @param ville la nouvelle valeur du membre ville
	 */
	public void setVille(final String ville) {
		this.ville = ville;
	}

	/**
	 * Getter du membre boitePostale.
	 * @return <code>String</code> le membre boitePostale
	 */
	public String getBoitePostale() {
		return this.boitePostale;
	}

	/**
	 * Setter du membre boitePostale.
	 * @param boitePostale la nouvelle valeur du membre boitePostale.
	 */
	public void setBoitePostal(final String boitePostale) {
		this.boitePostale = boitePostale;
	}

	/**
	 * Getter du membre pays.
	 * @return <code>String</code> le membre pays.
	 */
	public String getPays() {
		return this.pays;
	}

	/**
	 * Setter du membre pays.
	 * @param pays la nouvelle valeur du membre pays
	 */
	public void setPays(final String pays) {
		this.pays = pays;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "Adresse [" +
      this.adresse + ", " +
      this.codePostal + ", " +
      this.ville + ", " +
      this.boitePostale + ", " +
      this.pays +
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
		if (this.adresse == null) {
			result = prime * result;
		} else {
			result = prime * result + this.adresse.hashCode();
		}
		if (this.boitePostale == null) {
			result = prime * result;
		} else {
			result = prime * result + this.boitePostale.hashCode();
		}
		if (this.codePostal == null) {
			result = prime * result;
		} else {
			result = prime * result + this.codePostal.hashCode();
		}
		if (this.pays == null) {
			result = prime * result;
		} else {
			result = prime * result + this.pays.hashCode();
		}
		if (this.ville == null) {
			result = prime * result;
		} else {
			result = prime * result + this.ville.hashCode();
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
		if (!(obj instanceof Adresse)) { return false; }
		final Adresse other = (Adresse) obj;
		if (this.adresse == null) {
			if (other.adresse != null) { return false; }
		} else if (!this.adresse.equals(other.adresse)) { return false; }
		if (this.boitePostale == null) {
			if (other.boitePostale != null) { return false; }
		} else if (!this.boitePostale.equals(other.boitePostale)) { return false; }
		if (this.codePostal == null) {
			if (other.codePostal != null) { return false; }
		} else if (!this.codePostal.equals(other.codePostal)) { return false; }
		if (this.pays == null) {
			if (other.pays != null) { return false; }
		} else if (!this.pays.equals(other.pays)) { return false; }
		if (this.ville == null) {
			if (other.ville != null) { return false; }
		} else if (!this.ville.equals(other.ville)) { return false; }
		return true;
	}

}
