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
package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 oct. 2013
 */
@Entity
@Table(name = "apersonnes_agroupes")
@AssociationOverrides({
	@AssociationOverride(name = "pk.personne",
		joinColumns = @JoinColumn(name = "APERSONNE_ID")),
	@AssociationOverride(name = "pk.groupe",
		joinColumns = @JoinColumn(name = "AGROUPEOFAPERS_ID")) })
public class MappingAGroupeAPersonne implements Serializable {

	/** Identifiant de serialisation*/
	private static final long serialVersionUID = 9171972868617131333L;

	/** The Source which insert the entry. */
	@Basic
	@Column(name = "SOURCE", length = IntConst.ISOURCE, nullable = false)
	private String source;

	/** The pk */
	@EmbeddedId
	private MappingAGroupeAPersonneId pk = new MappingAGroupeAPersonneId();

	/**
	 * Contructor of the object MappingAGroupeAPersonne.java.
	 */
	public MappingAGroupeAPersonne() {
		super();
	}

	/**
	 * Contructor of the object MappingAGroupeAPersonne.java.
	 * @param source
	 * @param groupe
	 * @param personne
	 */
	public MappingAGroupeAPersonne(final String source, final APersonne personne, final  AGroupeOfAPersonne groupe) {
		super();
		this.source = source;
		this.pk = new MappingAGroupeAPersonneId(personne, groupe);
	}

	/**
	 * Getter of member source.
	 * @return <code>String</code> the attribute source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter of attribute source.
	 * @param source the attribute source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Getter of member pk.
	 * @return <code>MappingAGroupeAPersonneId</code> the attribute pk
	 */
	public MappingAGroupeAPersonneId getPk() {
		return pk;
	}

	/**
	 * Setter of attribute pk.
	 * @param pk the attribute pk to set
	 */
	public void setPk(final MappingAGroupeAPersonneId pk) {
		this.pk = pk;
	}

	/**
	 * Getter of member personne.
	 * @return <code>APersonne</code> the attribute personne
	 */
	//@Transient
	public APersonne getPersonne() {
		return this.getPk().getPersonne();
	}

	/**
	 * Setter of attribute personne.
	 * @param personne the attribute personne to set
	 */
	public void setPersonne(APersonne personne) {
		this.getPk().setPersonne(personne);
	}

	/**
	 * Getter of member groupe.
	 * @return <code>AGroupeOfAPersonne</code> the attribute groupe
	 */
	//@Transient
	public AGroupeOfAPersonne getGroupe() {
		return this.getPk().getGroupe();
	}

	/**
	 * Setter of attribute groupe.
	 * @param groupe the attribute groupe to set
	 */
	public void setGroupe(AGroupeOfAPersonne groupe) {
		this.getPk().setGroupe(groupe);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingAGroupeAPersonne other = (MappingAGroupeAPersonne) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	/**
	 * Check if the object is equals without source comparison
	 * @param obj
	 * @return true is equals else false.
	 */
	public boolean equalsIgnoreSource(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingAGroupeAPersonne other = (MappingAGroupeAPersonne) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	/**
	 * Test if a set contains a groupe.
	 * @param collection The set of MappingAGroupeAPersonne where to check.
	 * @param object the groupe To find.
	 * @return true if contains, else false.
	 */
	public static boolean containsWithoutSource(final Set<MappingAGroupeAPersonne> collection, final MappingAGroupeAPersonne object) {
		for (MappingAGroupeAPersonne item : collection) {
			if (item.equalsIgnoreSource(object))
				return true;
		}
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
    return "MappingAGroupeAPersonne [source=" +
      this.source + ", groupe=" +
      this.pk.getGroupe().getId() + ", personne=" +
      this.pk.getPersonne().getId() +
      "]";
	}

}
