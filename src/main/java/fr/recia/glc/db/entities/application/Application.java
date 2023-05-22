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
package fr.recia.glc.db.entities.application;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.groupe.Profil;
import fr.recia.glc.db.entities.groupe.RoleApplicatif;
import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity
 * Object décrivant une application.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>identifiant, nom, password, categorie.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 5 juin 08
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Application extends AbstractEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -1512141897671120147L;

	//Attibuts
	/** Catégorie de l'application. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I40)
	private CategorieApplication categorie;
	/** Description de l'application. */
	private String description;
	/** Identifiant unique de l'application. */
	@Column(unique = true, length = IntConst.I40)
	private String identifiant;
	/** Nom unique de l'application, pouvant servir de login. */
	@Column(unique = true, length = IntConst.I100)
	private String nom;
	/** Password de l'application. */
	private String password;

	//Relations
	/** Relation bidirectionnelle. */
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "applications_profils", joinColumns = @JoinColumn(name = "APPLICATION_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PROFIL_ID", referencedColumnName = "ID"))
	private Set<Profil> profils = new HashSet<>();

	/** Relation unidirectionnelle. */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "apersonne_fk")
	private APersonne proprietaire;

	/** Relation bidirectionnelle. */
	@OneToMany(mappedBy = "proprietaire", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<RoleApplicatif> rolesApplicatifs = new HashSet<>();

	//Constructeurs
	/**
	 * Constructeur de l'objet Application.java.
	 */
	public Application() {
		super();
	}

	/**
	 * Constructeur de l'objet Application.java.
	 * @param identifiant Identifiant unique de l'application.
	 * @param password Password de l'application.
	 * @param nom Nom unique de l'application, pouvant servir de login.
	 * @param categorie Catégorie de l'application.
	 */
	public Application(final String identifiant, final String password, final String nom, final CategorieApplication categorie) {
		super();
		this.identifiant = identifiant;
		this.password = password;
		this.nom = nom;
		this.categorie = categorie;
	}

	//Accesseurs
	/**
	 * Getter du membre categorie.
	 * @return <code>CategorieApplication</code> le membre categorie
	 */
	public CategorieApplication getCategorie() {
		return this.categorie;
	}

	/**
	 * Setter du membre categorie.
	 * @param categorie la nouvelle valeur du membre categorie
	 */
	public void setCategorie(final CategorieApplication categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter du membre description.
	 * @return <code>String</code> le membre description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter du membre description.
	 * @param description la nouvelle valeur du membre description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Getter du membre identifiant.
	 * @return <code>String</code> le membre identifiant
	 */
	public String getIdentifiant() {
		return this.identifiant;
	}

	/**
	 * Setter du membre identifiant.
	 * @param identifiant la nouvelle valeur du membre identifiant
	 */
	public void setIdentifiant(final String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Getter du membre nom.
	 * @return <code>String</code> le membre nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Setter du membre nom.
	 * @param nom la nouvelle valeur du membre nom
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * Getter du membre password.
	 * @return <code>String</code> le membre password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Setter du membre password.
	 * @param password la nouvelle valeur du membre password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	//Relations
	/**
	 * Getter du membre profils.
	 * @return <code>Set< Profil ></code> le membre profils
	 */
	public Set<Profil> getProfils() {
		return this.profils;
	}

	/**
	 * Setter du membre profils.
	 * @param profils la nouvelle valeur du membre profils
	 */
	public void setProfils(final Set<Profil> profils) {
		this.profils = profils;
	}

	/**
	 * Getter du membre proprietaire.
	 * @return <code>APersonne</code> le membre proprietaire
	 */
	public APersonne getProprietaire() {
		return this.proprietaire;
	}

	/**
	 * Setter du membre proprietaire.
	 * @param proprietaire la nouvelle valeur du membre proprietaire
	 */
	public void setProprietaire(final APersonne proprietaire) {
		this.proprietaire = proprietaire;
	}

	/**
	 * Getter du membre rolesApplicatifs.
	 * @return <code>Set< RoleApplicatif ></code> le membre rolesApplicatifs
	 */
	public Set<RoleApplicatif> getRolesApplicatifs() {
		return this.rolesApplicatifs;
	}

	/**
	 * Setter du membre rolesApplicatifs.
	 * @param rolesApplicatifs la nouvelle valeur du membre rolesApplicatifs
	 */
	public void setRolesApplicatifs(final Set<RoleApplicatif> rolesApplicatifs) {
		this.rolesApplicatifs = rolesApplicatifs;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Application [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.categorie);
		sb.append(", ");
		sb.append(this.identifiant);
		sb.append(", ");
		sb.append(this.nom);
		sb.append(", ");
		sb.append(this.description);
		sb.append(", ");
		sb.append(this.password);
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
		if (this.identifiant == null) {
			result = prime * result;
		} else {
			result = prime * result + this.identifiant.hashCode();
		}
		if (this.nom == null) {
			result = prime * result;
		} else {
			result = prime * result + this.nom.hashCode();
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
		if (!(obj instanceof Application)) {
			return false;
		}
		final Application other = (Application) obj;
		if (this.categorie == null) {
			if (other.categorie != null) {
				return false;
			}
		} else if (!this.categorie.equals(other.categorie)) {
			return false;
		}
		if (this.identifiant == null) {
			if (other.identifiant != null) {
				return false;
			}
		} else if (!this.identifiant.equals(other.identifiant)) {
			return false;
		}
		if (this.nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!this.nom.equals(other.nom)) {
			return false;
		}
		return true;
	}

}
