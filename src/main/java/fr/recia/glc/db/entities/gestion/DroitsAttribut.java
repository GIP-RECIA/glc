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
 * Définition des droits utilisateurs par rapport à un attribut.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>cle, visible, modifier, ajouter, supprimer, collectif.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class DroitsAttribut extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -3833891444074882307L;
	// Attributs
	/** Nom de l'attribut. */
	private String cle;
	/** Autorisation de visibilité de la clé. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean visible;
	/** Autorisation de modification de la clé. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean modifier;
	/** Autorisation d'ajout de la clé. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean ajouter;
	/** Autorisation de suppression de la clé. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean supprimer;
	/** Autorisation de visibilité la clé à d'autres personnes. False par défaut.*/
	@Column(nullable = false, columnDefinition = "BIT")
	private boolean collectif;

	// Relations
	/** Relation unidirectionnelle.
	 * Structure pour laquelle est configuré ce type de droit. */
	@ManyToOne
	@JoinColumn(name = "structure_fk")
	private AStructure structure;

	/** Relation unidirectionnelle.
	 * RoleApplicatif auquel s'applique ce type de droit. */
	@ManyToOne
	@JoinColumn(name = "role_applicatif_fk")
	private RoleApplicatif roleApplicatif;

	//Constructeurs
	/**
	 * Constructeur de l'objet DroitAttribut.java.
	 */
	public DroitsAttribut() {
		super();
	}

	/**
	 * Constructeur de l'objet DroitAttribut.java.
	 * @param cle Nom de l'attribut.
	 * @param structure Structure pour laquelle est configuré ce type de droit.
	 * @param roleApplicatif RoleApplicatif auquel s'applique ce type de droit.
	 */
	public DroitsAttribut(final String cle, final AStructure structure, final RoleApplicatif roleApplicatif) {
		super();
		this.cle = cle;
		this.structure = structure;
		this.roleApplicatif = roleApplicatif;
	}

	// Accesseurs
	/**
	 * Getter du membre cle.
	 * @return <code>String</code> le membre cle.
	 */
	public String getCle() {
		return this.cle;
	}

	/**
	 * Setter du membre cle.
	 * @param cle la nouvelle valeur du membre cle.
	 */
	public void setCle(final String cle) {
		this.cle = cle;
	}

	/**
	 * Getter du membre visible.
	 * @return <code>boolean</code> le membre visible.
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * Setter du membre visible.
	 * @param visible la nouvelle valeur du membre visible.
	 */
	public void setVisible(final boolean visible) {
		this.visible = visible;
	}

	/**
	 * Getter du membre modifier.
	 * @return <code>boolean</code> le membre modifier.
	 */
	public boolean isModifier() {
		return this.modifier;
	}

	/**
	 * Setter du membre modifier.
	 * @param modifier la nouvelle valeur du membre modifier.
	 */
	public void setModifier(final boolean modifier) {
		this.modifier = modifier;
	}

	/**
	 * Getter du membre ajouter.
	 * @return <code>boolean</code> le membre ajouter.
	 */
	public boolean isAjouter() {
		return this.ajouter;
	}

	/**
	 * Setter du membre ajouter.
	 * @param ajouter la nouvelle valeur du membre ajouter.
	 */
	public void setAjouter(final boolean ajouter) {
		this.ajouter = ajouter;
	}

	/**
	 * Getter du membre supprimer.
	 * @return <code>boolean</code> le membre supprimer.
	 */
	public boolean isSupprimer() {
		return this.supprimer;
	}

	/**
	 * Setter du membre supprimer.
	 * @param supprimer la nouvelle valeur du membre supprimer.
	 */
	public void setSupprimer(final boolean supprimer) {
		this.supprimer = supprimer;
	}

	/**
	 * Getter du membre collectif.
	 * @return <code>boolean</code> le membre collectif.
	 */
	public boolean isCollectif() {
		return this.collectif;
	}

	/**
	 * Setter du membre collectif.
	 * @param collectif la nouvelle valeur du membre collectif.
	 */
	public void setCollectif(final boolean collectif) {
		this.collectif = collectif;
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
		final StringBuilder sb = new StringBuilder("DroitAttribut [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.cle);
		sb.append(", ");
		sb.append(this.ajouter);
		sb.append(", ");
		sb.append(this.visible);
		sb.append(", ");
		sb.append(this.modifier);
		sb.append(", ");
		sb.append(this.supprimer);
		sb.append(", ");
		sb.append(this.collectif);
		sb.append(", ");
		sb.append(this.structure);
		sb.append(", ");
		sb.append(this.roleApplicatif);
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
		if (this.cle == null) {
			result = prime * result;
		} else {
			result = prime * result + this.cle.hashCode();
		}
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
		if (!(obj instanceof DroitsAttribut)) {
			return false;
		}
		final DroitsAttribut other = (DroitsAttribut) obj;
		if (this.cle == null) {
			if (other.cle != null) {
				return false;
			}
		} else if (!this.cle.equals(other.cle)) {
			return false;
		}
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
