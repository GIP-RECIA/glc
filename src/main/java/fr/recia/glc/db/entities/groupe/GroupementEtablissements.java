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

import fr.recia.glc.db.entities.structure.Etablissement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * AGroupe étendu en groupe d'établissement.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>cn, membres.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class GroupementEtablissements extends AGroupe {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -3375315398663209144L;

  //Attributs
  //Relations
  /**
   * Relation bidirectionnelle.
   * Liste des Etablissements membres du groupe.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(name = "groupements_etablissements",
    joinColumns =
    @JoinColumn(name = "GROUPEMENTETABLISSEMENT_ID", referencedColumnName = "ID"),
    inverseJoinColumns =
    @JoinColumn(name = "ETABLISSEMENT_ID", referencedColumnName = "ID"))
  private Set<Etablissement> membres = new HashSet<>();

  //Constructeurs

  /**
   * Constructeur de l'objet GroupementEtablissements.java.
   */
  public GroupementEtablissements() {
    super();
    this.setCategorie(CategorieGroupe.Groupement_etablissement);
  }

  /**
   * Constructeur de l'objet GroupementEtablissements.java.
   *
   * @param cn      Nom unique de groupe, peut servir comme identifiant.
   * @param membres Liste des Etablissements membres du groupe.
   * @param source  Source ayant créé l'objet.
   */
  public GroupementEtablissements(final String cn, final Set<Etablissement> membres, final String source) {
    super(cn, CategorieGroupe.Groupement_etablissement, source);
    this.membres = membres;
  }

  //Accesseurs

  //Relations

  /**
   * Getter du membre membres.
   *
   * @return <code>Set< Etablissement ></code> le membre membres.
   */
  public Set<Etablissement> getMembres() {
    return this.membres;
  }

  /**
   * Setter du membre membres.
   *
   * @param membres la nouvelle valeur du membre membres.
   */
  public void setMembres(final Set<Etablissement> membres) {
    this.membres = membres;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.groupe.AGroupe#toString()
   */
  @Override
  public String toString() {
    return "GroupementEtablissement [" + super.toString() + "]";
  }

  /**
   * Donne la valeur de hachage de l'instance.
   *
   * @return <code>int</code> La valeur du hash.
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    if (this.membres == null) {
      result = prime * result;
    } else {
      result = prime * result + this.membres.hashCode();
    }
    return result;
  }

  /**
   * Teste si un objet est égal à cette instance.
   *
   * @param obj l'instance le l'object à comparer.
   * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof GroupementEtablissements)) {
      return false;
    }
    final GroupementEtablissements other = (GroupementEtablissements) obj;
    if (this.membres == null) {
      if (other.membres != null) {
        return false;
      }
    } else if (!this.membres.equals(other.membres)) {
      return false;
    }
    return true;
  }

}
