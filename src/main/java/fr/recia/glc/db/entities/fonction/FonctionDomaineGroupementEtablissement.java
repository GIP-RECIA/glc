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

import fr.recia.glc.db.entities.groupe.GroupementEtablissements;
import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Domaine de fonction au sein d'un groupement d'établissment.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>domaines, groupeEtablissment, personne.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class FonctionDomaineGroupementEtablissement extends AFonction {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 2540322583307181709L;

	//Attributs

	//Relations
	/** Relation unidirectionnelle.
	 * Domaines d'exercice. */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "domaines_gp_etab",
			joinColumns =
				@JoinColumn(name = "FONCTIONDOMAINEGROUPETAB_ID", referencedColumnName = "ID"),
			inverseJoinColumns =
				@JoinColumn(name = "TYPEDOMAINE_ID", referencedColumnName = "ID"))
	private Set<TypeDomaine> domaines = new HashSet<TypeDomaine>();

	/** Relation unidirectionnelle.
	 * Groupement d'établissment dans lequel l'exercice des domaines est effectué.*/
	@ManyToOne
	@JoinColumn(name = "groupe_etablissement_fk", insertable = false, updatable = false)
	private GroupementEtablissements groupeEtablissements;

	//Constructeurs
	/**
	 * Constructeur de l'objet FonctionDomaineGroupementEtablissement.java.
	 */
	public FonctionDomaineGroupementEtablissement() {
		super();
		this.setCategorie(CategorieFonction.Domaine_Groupement);
	}

	/**
	 * Constructeur de l'objet FonctionDomaineGroupementEtablissement.java.
	 * @param domaines Domaines d'exercice.
	 * @param groupeEtablissements Groupement d'établissment dans lequel l'exercice des domaines est effectué.
	 * @param personne Personne exerçant ces domaines.
	 * @param source Source d'alimentation gérant cette fonction.
	 */
	public FonctionDomaineGroupementEtablissement(final Set<TypeDomaine> domaines,
                                                final GroupementEtablissements groupeEtablissements,
                                                final APersonne personne, final String source) {
		super(CategorieFonction.Domaine_Groupement, personne, source);
		this.domaines = domaines;
		this.groupeEtablissements = groupeEtablissements;
	}

	//Accesseurs

	//Relations
	/**
	 * Getter du membre domaines.
	 * @return <code>Set< TypeDomaine ></code> le membre domaines.
	 */
	public Set<TypeDomaine> getDomaines() {
		return this.domaines;
	}

	/**
	 * Setter du membre domaines.
	 * @param domaines la nouvelle valeur du membre domaines.
	 */
	public void setDomaines(final Set<TypeDomaine> domaines) {
		this.domaines = domaines;
	}

	/**
	 * Getter du membre groupeEtablissements.
	 * @return <code>GroupementEtablissements</code> le membre groupeEtablissements.
	 */
	public GroupementEtablissements getGroupeEtablissements() {
		return this.groupeEtablissements;
	}

	/**
	 * Setter du membre groupeEtablissements.
	 * @param groupeEtablissements la nouvelle valeur du membre groupeEtablissements.
	 */
	public void setGroupeEtablissements(final GroupementEtablissements groupeEtablissements) {
		this.groupeEtablissements = groupeEtablissements;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.fonction.AFonction#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FonctionDomaineGroupement [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.groupeEtablissements);
		sb.append(", ");
		sb.append(this.domaines);
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
		if (this.domaines == null) {
			result = prime * result;
		} else {
			result = prime * result + this.domaines.hashCode();
		}
		if (this.groupeEtablissements == null) {
			result = prime * result;
		} else {
			result = prime * result + this.groupeEtablissements.hashCode();
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
		if (!(obj instanceof FonctionDomaineGroupementEtablissement)) { return false; }
		final FonctionDomaineGroupementEtablissement other = (FonctionDomaineGroupementEtablissement) obj;
		if (this.domaines == null) {
			if (other.domaines != null) { return false; }
		} else if (!this.domaines.equals(other.domaines)) {	return false; }
		if (this.groupeEtablissements == null) {
			if (other.groupeEtablissements != null) { return false; }
		} else if (!this.groupeEtablissements.equals(other.groupeEtablissements)) { return false; }
		return true;
	}

}
