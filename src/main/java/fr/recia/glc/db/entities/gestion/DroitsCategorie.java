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

import fr.recia.glc.db.entities.common.AbstractSimpleEntity;
import fr.recia.glc.db.entities.groupe.RoleApplicatif;
import fr.recia.glc.db.entities.structure.AStructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Bean - Entity.
 * Définition des droits sur les catégories d'utulisateurs.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>structure, roleApplicatif, les autres attributs sont définis à False par défaut.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class DroitsCategorie extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 3604012635621027146L;

	// Attributs
	/** Droit de changement du password pour la catégorie d'utilisateur. False par défaut. */
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean changerPassword;
	/** Droit de changement de nom de login pour la catégorie d'utilisateur. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean changerLogin;
	/** Droit de changement d'identité pour la catégorie d'utilisateur. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean changerIdentite;
	/** Droit de modification de la fiche de données pour la catégorie d'utilisateur. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean modifierFiche;
	/** Droit de visibilité de la fiche de données pour la catégorie d'utilisateur. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean voirFiche;
	/** Modèle de login défini par l'établissement. */
	private String modeleLogin = "prenom.nom";

	// Relations
	/** Relation unidirectionnelle.
	 * Structure définissant ces droits. */
	@ManyToOne
	@JoinColumn(name = "structure_fk")
	private AStructure structure;

	/** Relation unidirectionnelle.
	 * RoleApplicatif pour lequel ces droits sont applicables.*/
	@ManyToOne
	@JoinColumn(name = "role_applicatif_fk")
	private RoleApplicatif roleApplicatif;

	// Construteurs
	/**
	 * Constructeur de l'objet DroitsCategorie.java.
	 */
	public DroitsCategorie() {
		super();
	}

	/**
	 * Constructeur de l'objet DroitsCategorie.java.
	 * @param structure Structure définissant ces droits.
	 * @param roleApplicatif RoleApplicatif pour lequel ces droits sont applicables.
	 */
	public DroitsCategorie(final AStructure structure, final RoleApplicatif roleApplicatif) {
		super();
		this.structure = structure;
		this.roleApplicatif = roleApplicatif;
	}

	// Accesseurs
	/**
	 * Getter du membre changerPassword.
	 * @return <code>boolean</code> le membre changerPassword.
	 */
	public boolean isChangerPassword() {
		return this.changerPassword;
	}

	/**
	 * Setter du membre changerPassword.
	 * @param changerPassword la nouvelle valeur du membre changerPassword.
	 */
	public void setChangerPassword(final boolean changerPassword) {
		this.changerPassword = changerPassword;
	}

	/**
	 * Getter du membre changerLogin.
	 * @return <code>boolean</code> le membre changerLogin.
	 */
	public boolean isChangerLogin() {
		return this.changerLogin;
	}

	/**
	 * Setter du membre changerLogin.
	 * @param changerLogin la nouvelle valeur du membre changerLogin.
	 */
	public void setChangerLogin(final boolean changerLogin) {
		this.changerLogin = changerLogin;
	}

	/**
	 * Getter du membre changerIdentite.
	 * @return <code>boolean</code> le membre changerIdentite.
	 */
	public boolean isChangerIdentite() {
		return this.changerIdentite;
	}

	/**
	 * Setter du membre changerIdentite.
	 * @param changerIdentite la nouvelle valeur du membre changerIdentite.
	 */
	public void setChangerIdentite(final boolean changerIdentite) {
		this.changerIdentite = changerIdentite;
	}

	/**
	 * Getter du membre modifierFiche.
	 * @return <code>boolean</code> le membre modifierFiche.
	 */
	public boolean isModifierFiche() {
		return this.modifierFiche;
	}

	/**
	 * Setter du membre modifierFiche.
	 * @param modifierFiche la nouvelle valeur du membre modifierFiche.
	 */
	public void setModifierFiche(final boolean modifierFiche) {
		this.modifierFiche = modifierFiche;
	}

	/**
	 * Getter du membre voirFiche.
	 * @return <code>boolean</code> le membre voirFiche.
	 */
	public boolean isVoirFiche() {
		return this.voirFiche;
	}

	/**
	 * Setter du membre voirFiche.
	 * @param voirFiche la nouvelle valeur du membre voirFiche.
	 */
	public void setVoirFiche(final boolean voirFiche) {
		this.voirFiche = voirFiche;
	}

	/**
	 * Getter du membre modeleLogin.
	 * @return <code>String</code> le membre modeleLogin.
	 */
	public String getModeleLogin() {
		return this.modeleLogin;
	}

	/**
	 * Setter du membre modeleLogin.
	 * @param modeleLogin la nouvelle valeur du membre modeleLogin.
	 */
	public void setModeleLogin(final String modeleLogin) {
		this.modeleLogin = modeleLogin;
	}

	//Relations
	/**
	 * Getter du membre structure.
	 * @return <code>AStructure</code> le membre structure.
	 */
	public AStructure getStructure() {
		return this.structure;
	}

	/**
	 * Setter du membre structure.
	 * @param structure la nouvelle valeur du membre structure.
	 */
	public void setStructure(final AStructure structure) {
		this.structure = structure;
	}

	/**
	 * Getter du membre roleApplicatif.
	 * @return <code>RoleApplicatif</code> le membre roleApplicatif.
	 */
	public RoleApplicatif getRoleApplicatif() {
		return this.roleApplicatif;
	}

	/**
	 * Setter du membre roleApplicatif.
	 * @param roleApplicatif la nouvelle valeur du membre roleApplicatif.
	 */
	public void setRoleApplicatif(final RoleApplicatif roleApplicatif) {
		this.roleApplicatif = roleApplicatif;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "DroitCategorie [" +
      super.toString() + ", " +
      this.changerPassword + ", " +
      this.changerLogin + ", " +
      this.changerIdentite + ", " +
      this.modifierFiche + ", " +
      this.voirFiche + ", " +
      this.modeleLogin + ", " +
      this.roleApplicatif + ", " +
      this.structure +
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
		if (this.roleApplicatif == null) {
			result = prime * result;
		} else {
			result = prime * result + this.roleApplicatif.hashCode();
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DroitsCategorie)) {
			return false;
		}
		final DroitsCategorie other = (DroitsCategorie) obj;
		if (this.roleApplicatif == null) {
			if (other.roleApplicatif != null) {
				return false;
			}
		} else if (!this.roleApplicatif.equals(other.roleApplicatif)) {
			return false;
		}
		if (this.structure == null) {
			if (other.structure != null) {
				return false;
			}
		} else if (!this.structure.equals(other.structure)) {
			return false;
		}
		return true;
	}

}
