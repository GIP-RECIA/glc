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
package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.CleJointure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Bean - Entity.
 * Structure étendue en Service Académique.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>uai, nom, siren, cleJointure, academie.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 11 juin 08
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceAcademique extends AStructure {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -84911884588753777L;

	//Attributs
	/** Numéro UAI, ancien RNE. */
	@Column(unique = true, length = IntConst.IUAI)
	private String uai;
	/** Nom de l'académie ou code. */
	private String academie;

	//Constructeurs
	/**
	 * Constructeur de l'objet ServiceAcademique.java.
	 */
	public ServiceAcademique() {
		super();
		this.setCategorie(CategorieStructure.Service_academique);
	}
	/**
	 * Constructeur de l'objet ServiceAcademique.java.
	 * @param uai Numéro UAI, ancien RNE.
	 * @param nom Nom unique de la stucture.
	 * @param siren Numéro de SIRET/SIREN unique de la structure.
	 * @param cleJointure Clé de jointure unique de la structure.
	 * @param academie Nom de l'académie ou code.
	 */
	public ServiceAcademique(final String uai, final String nom, final String siren,
                           final CleJointure cleJointure, final String academie) {
		super(CategorieStructure.Service_academique, nom, siren, cleJointure);
		this.academie = academie;
		this.uai = uai;
	}

	//Accesseurs
	/**
	 * Getter du membre uai.
	 * @return <code>String</code> le membre uai.
	 */
	public String getUai() {
		return this.uai;
	}

	/**
	 * Setter du membre uai.
	 * @param uai la nouvelle valeur du membre uai.
	 */
	public void setUai(final String uai) {
		this.uai = uai;
	}

	/**
	 * Getter du membre academie.
	 * @return <code>String</code> le membre academie.
	 */
	public String getAcademie() {
		return this.academie;
	}

	/**
	 * Setter du membre academie.
	 * @param academie la nouvelle valeur du membre academie.
	 */
	public void setAcademie(final String academie) {
		this.academie = academie;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.structure.AStructure#toString()
	 */
	@Override
	public String toString() {
    return "ServiceAcademique [" +
      super.toString() + ", " +
      this.uai + ", " +
      this.academie +
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
		if (this.academie == null) {
			result = prime * result;
		} else {
			result = prime * result + this.academie.hashCode();
		}
		if (this.uai == null) {
			result = prime * result;
		} else {
			result = prime * result + this.uai.hashCode();
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
		if (!super.equals(obj)) { return false; }
		if (!(obj instanceof ServiceAcademique)) { return false; }
		final ServiceAcademique other = (ServiceAcademique) obj;
		if (this.academie == null) {
			if (other.academie != null) { return false; }
		} else if (!this.academie.equals(other.academie)) { return false; }
		if (this.uai == null) {
			if (other.uai != null) { return false; }
		} else if (!this.uai.equals(other.uai)) { return false; }
		return true;
	}

}
