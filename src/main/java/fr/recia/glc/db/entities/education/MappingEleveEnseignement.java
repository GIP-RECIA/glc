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
package fr.recia.glc.db.entities.education;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.personne.Eleve;
import fr.recia.glc.db.entities.structure.Etablissement;
import org.hibernate.annotations.Parent;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Set;

/**
 * Embedded Object. Définition de lien enseignement avec la source et l'élève.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>source, enseignement</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 28 Octobre 2013
 */
@Embeddable
public class MappingEleveEnseignement implements Serializable {

	/** Identitifant de serialization. */
	private static final long serialVersionUID = -8513866991911167167L;

	/** Relation avec l'opbjet parent. */
	@Parent
	@Column(name = "ELEVE_ID", nullable = false)
	private Eleve eleve;

	/** Source d'alimentation de l'association. */
	@Basic
	@Column(length = IntConst.ISOURCE, name = "SOURCE", nullable = false, unique = false)
	private String source;

	/** Relation avec l'enseignement. */
	@OneToOne
	@JoinColumn(name = "ENSEIGNEMENT_ID", nullable = false)
	private Enseignement enseignement;

	/** Relation avec l'établissement. */
	@OneToOne
	@JoinColumn(name = "ETABLISSEMENT_ID", nullable = true)
	private Etablissement etablissement;

	/**
	 * Contructor of the object MappingEleveEnseignement.java.
	 */
	public MappingEleveEnseignement() {
		super();
	}

	/**
	 * Contructor of the object MappingEleveEnseignement.java.
	 *
	 * @param source
	 * @param enseignement
	 */
	public MappingEleveEnseignement(final String source, final Enseignement enseignement) {
		super();
		this.source = source;
		this.enseignement = enseignement;
	}

	/**
	 * Contructor of the object MappingEleveEnseignement.java.
	 *
	 * @param source
	 * @param enseignement
	 * @param etablissement
	 */
	public MappingEleveEnseignement(final String source, final Enseignement enseignement, final Etablissement etablissement) {
		super();
		this.source = source;
		this.enseignement = enseignement;
		this.etablissement = etablissement;
	}

	/**
	 * Getter of member eleve.
	 *
	 * @return <code>Eleve</code> the attribute eleve
	 */
	public Eleve getEleve() {
		return eleve;
	}

	/**
	 * Setter of attribute eleve.
	 *
	 * @param eleve the attribute eleve to set
	 */
	public void setEleve(final Eleve eleve) {
		this.eleve = eleve;
	}

	/**
	 * Getter of member source.
	 *
	 * @return <code>String</code> the attribute source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter of attribute source.
	 *
	 * @param source the attribute source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Getter of member enseignement.
	 *
	 * @return <code>Enseignement</code> the attribute enseignement
	 */
	public Enseignement getEnseignement() {
		return enseignement;
	}

	/**
	 * Setter of attribute enseignement.
	 *
	 * @param enseignement the attribute enseignement to set
	 */
	public void setEnseignement(final Enseignement enseignement) {
		this.enseignement = enseignement;
	}

	/**
	 * Getter of attribute
	 *
	 * @return the etablissement
	 */
	public Etablissement getEtablissement() {
		return etablissement;
	}

	/**
	 * Setter of attribute etablissement
	 *
	 * @param etablissement the etablissement to set
	 */
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eleve == null) ? 0 : eleve.hashCode());
		result = prime * result + ((enseignement == null) ? 0 : enseignement.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingEleveEnseignement other = (MappingEleveEnseignement) obj;
		if (enseignement == null) {
			if (other.enseignement != null)
				return false;
		} else if (!enseignement.equals(other.enseignement))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	/**
	 * Teste si un objet est égal à cette instance en ignorant l'attribut source.
	 *
	 * @param obj
	 * @return boolean
	 */
	public boolean equalsIgnoreSource(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappingEleveEnseignement other = (MappingEleveEnseignement) obj;
		if (enseignement == null) {
			if (other.enseignement != null)
				return false;
		} else if (!enseignement.equals(other.enseignement))
			return false;
		return true;
	}

	/**
	 * Teste si un objet est égal à cette instance en ignorant l'attribut source.
	 *
	 * @param obj
	 * @return boolean
	 */
	 /* public boolean equalsCodeEnsIgnoreSource(final Object obj) {
     if (this == obj)
       return true;
     if (obj == null)
       return false;
     if (getClass() != obj.getClass())
       return false;
     MappingEleveEnseignement other = (MappingEleveEnseignement) obj;
     if (enseignement == null) {
       if (other.enseignement != null)
         return false;
     } else if (!enseignement.equalsOnSourceCode(other.enseignement))
       return false;
     return true;
   }*/

	/**
	 * Test if a set of MappingEleveEnseignement contains an enseignement without checking the source of MappingEleveEnseignement link.
	 *
	 * @param collection
	 * @param object
	 * @return boolean
	 */
	public static boolean containsWithoutSource(final Set<MappingEleveEnseignement> collection, final MappingEleveEnseignement object) {
		for (MappingEleveEnseignement item : collection) {
			if (item.equalsIgnoreSource(object))
				return true;
		}
		return false;
	}

	/**
	 * Test if a set of MappingEleveEnseignement contains an enseignement without checking the source of MappingEleveEnseignement link.
	 *
	 * @param collection
	 * @param object
	 * @return boolean
	 */
  /* public static boolean containsCodeEnsWithoutSource(final Set<MappingEleveEnseignement> collection, final MappingEleveEnseignement object) {
    for (MappingEleveEnseignement item : collection) {
      if (item.equalsCodeEnsIgnoreSource(object))
        return true;
    }
    return false;
  }*/

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
    return "MappingEleveEnseignement [source=" +
      this.source + ", enseignement=" +
      this.enseignement + ", etablissement=" +
      (etablissement != null ? etablissement.getId() : "default") +
      "]";
	}

}
