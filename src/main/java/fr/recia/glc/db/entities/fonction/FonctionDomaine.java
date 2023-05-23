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

import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.AStructure;

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
 * Domaines de fonction pour une personne dans une structure.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>domaine, structure, personne, source.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 9 juin 08
 */
@Entity
public class FonctionDomaine extends AFonction {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 4017026658563725022L;

	//Attributs

	//Relations
	/** Relation unidirectionnelle.
	 * Domaines d'exercice. */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE },
			fetch = FetchType.LAZY)
	@JoinTable(name = "fonctions_domaines",
			joinColumns =
				@JoinColumn(name = "FONCTIONDOMAINE_ID", referencedColumnName = "ID"),
			inverseJoinColumns =
				@JoinColumn(name = "TYPEDOMAINE_ID", referencedColumnName = "ID"))
	private Set<TypeDomaine> domaines = new HashSet<>();

	/** Relation unidirectionnelle.
	 * Structure d'exercice des domaines. */
	@ManyToOne
	@JoinColumn(name = "astructure_fk", insertable = false, updatable = false)
	private AStructure structure;

	//Constructeurs
	/**
	 * Constructeur de l'objet FonctionDomaine.java.
	 */
	public FonctionDomaine() {
		super();
		this.setCategorie(CategorieFonction.Domaine);
	}

	/**
	 * Constructeur de l'objet FonctionDomaine.java.
	 * @param domaines Domaines d'exercice.
	 * @param structure Structure d'exercice des domaines.
	 * @param personne Personne exerçant ce domaine.
	 * @param source Source d'alimentation gérant cette fonction.
	 */
	public FonctionDomaine(final Set<TypeDomaine> domaines, final AStructure structure,
                         final APersonne personne, final String source) {
		super(CategorieFonction.Domaine , personne, source);
		this.domaines = domaines;
		this.structure = structure;
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
	 * Setter du membre domaine.
	 * @param domaines la nouvelle valeur du membre domaines.
	 */
	public void setDomaines(final Set<TypeDomaine> domaines) {
		this.domaines = domaines;
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
    return "FonctionDomaine [" +
      super.toString() + ", " +
      this.structure + ", " +
      this.domaines +
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
		if (this.domaines == null) {
			result = prime * result;
		} else {
			result = prime * result + this.domaines.hashCode();
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
		if (!(obj instanceof FonctionDomaine)) { return false; }
		final FonctionDomaine other = (FonctionDomaine) obj;
		if (this.domaines == null) {
			if (other.domaines != null) { return false; }
		} else if (!this.domaines.equals(other.domaines)) { return false; }
		if (this.structure == null) {
			if (other.structure != null) { return false; }
		} else if (!this.structure.equals(other.structure)) { return false; }
		return true;
	}

}
