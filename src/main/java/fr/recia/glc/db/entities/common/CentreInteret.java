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

/**
 * Bean - Entity.
 * Description d'un centre d'intérêt.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>libelle, structure.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
//@Entity
public class CentreInteret extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 5278810039677009941L;

	//Attributs
	/** Libellé du centre d'intérêt. */
	private String libelle;

		//Relations
	/** Relation bidirectionnelle.
	 * Structure ayant défini le centre d'intérêt. */
	/*@ManyToOne
	private AStructure structure;*/

	/** Relation bidirectionnelle.
	 * Liste des personnes ayant ce centre d'interêt au sein de la structure l'ayant défini. */
	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "centresInterets")
	private Set<APersonne> personnes = new HashSet<APersonne>(); */

	//Constructeurs
	/**
	 * Constructeur de l'objet CentreInteret.java.
	 */
	public CentreInteret() {
		super();
	}

	/**
	 * Constructeur de l'objet CentreInteret.java.
	 * @param libelle Libellé du centre d'intérêt.
	 * @param structure Structure ayant défini le centre d'intérêt.
	 */
	/*public CentreInteret(final String libelle, final AStructure structure) {
		super();
		this.libelle = libelle;
		this.structure = structure;
	}*/

	//Accesseurs
	/**
	 * Getter du membre libelle.
	 * @return <code>String</code> le membre libelle.
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Setter du membre libelle.
	 * @param libelle la nouvelle valeur du membre libelle.
	 */
	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

  //Relations
	/**
	 * Getter du membre structure.
	 * @return <code>AStructure</code> le membre structure.
	 */
	/*public AStructure getStructure() {
		return this.structure;
	}*/

	/**
	 * Setter du membre structure.
	 * @param structure la nouvelle valeur du membre structure.
	 */
	/*public void setStructure(final AStructure structure) {
		this.structure = structure;
	}*/

	/**
	 * Getter du membre personnes.
	 * @return <code>Set< APersonne ></code> le membre personnes.
	 */
	/*public Set<APersonne> getPersonnes() {
		return this.personnes;
	}*/

	/**
	 * Setter du membre personnes.
	 * @param personnes la nouvelle valeur du membre personnes.
	 */
	/*public void setPersonnes(final Set<APersonne> personnes) {
		this.personnes = personnes;
	}*/

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "CentreInteret [" +
      super.toString() + ", " +
      this.libelle +
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
		if (this.libelle == null) {
			result = prime * result;
		} else {
			result = prime * result + this.libelle.hashCode();
		}
		/*if (this.structure == null) {
			result = prime * result;
		} else {
			result = prime * result + this.structure.hashCode();
		}*/
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
		if (!(obj instanceof CentreInteret)) { return false; }
		final CentreInteret other = (CentreInteret) obj;
		if (this.libelle == null) {
			if (other.libelle != null) { return false; }
		} else if (!this.libelle.equals(other.libelle)) { return false; }
		/*if (this.structure == null) {
			if (other.structure != null) { return false; }
		} else if (!this.structure.equals(other.structure)) { return false; }*/
		return true;
	}

}
