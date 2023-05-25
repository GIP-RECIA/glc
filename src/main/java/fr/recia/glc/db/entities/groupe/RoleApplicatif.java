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

import fr.recia.glc.db.entities.application.Application;

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
 * Ben - Entity.
 * Groupe de personne (AGroupeOfAPersonne) étendu en
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>cn, application, membres (peut être nul mais dans ce cas il doit y avoir un profil de défini).</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class RoleApplicatif extends AGroupeOfAPersonne {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -122752751569508953L;

  //Attributs

  //Relations
  /**
   * Relation unidirectionnelle.
   * Liste des profils utiles au rôle applicatif.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(name = "roles_applicatifs_profils",
    joinColumns =
    @JoinColumn(name = "ROLEAPPLICATIF_ID", referencedColumnName = "ID"),
    inverseJoinColumns =
    @JoinColumn(name = "PROFIL_ID", referencedColumnName = "ID"))
  private Set<Profil> profils = new HashSet<>();

  /**
   * Relation bidirectionnelle.
   * Application pour laquelle le role applicatif est défini.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "APPLICATION_ID")
  private Application proprietaire;

  //Constructeurs

  /**
   * Constructeur de l'objet RoleApplicatif.java.
   */
  public RoleApplicatif() {
    super();
    this.setCategorie(CategorieGroupe.Role_applicatif);
  }

  /**
   * Constructeur de l'objet RoleApplicatif.java.
   *
   * @param cn          Nom unique de groupe, peut servir comme identifiant.
   * @param membres     Liste des membres du groupe en dehors des profils.
   * @param application Application pour laquelle le role applicatif est défini.
   * @param source      Source ayant créé l'objet.
   */
  public RoleApplicatif(final String cn, final Set<MappingAGroupeAPersonne> membres,
                        final Application application, final String source) {
    super(cn, CategorieGroupe.Role_applicatif, membres, source);
    this.proprietaire = application;
  }

  //Accesseurs

  //Relations

  /**
   * Getter du membre profils.
   *
   * @return <code>Set< Profil ></code> le membre profils.
   */
  public Set<Profil> getProfils() {
    return this.profils;
  }

  /**
   * Setter du membre profils.
   *
   * @param profils la nouvelle valeur du membre profils.
   */
  public void setProfils(final Set<Profil> profils) {
    this.profils = profils;
  }

  /**
   * Getter du membre proprietaire.
   *
   * @return <code>Application</code> le membre proprietaire.
   */
  public Application getProprietaire() {
    return this.proprietaire;
  }

  /**
   * Setter du membre proprietaire.
   *
   * @param proprietaire la nouvelle valeur du membre proprietaire.
   */
  public void setProprietaire(final Application proprietaire) {
    this.proprietaire = proprietaire;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.groupe.AGroupe#toString()
   */
  @Override
  public String toString() {
    return "RoleApplicatif [" +
      super.toString() + ", " +
      this.profils +
      "]";
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
    if (this.proprietaire == null) {
      result = prime * result;
    } else {
      result = prime * result + this.proprietaire.hashCode();
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
    if (!(obj instanceof RoleApplicatif)) {
      return false;
    }
    final RoleApplicatif other = (RoleApplicatif) obj;
    if (this.proprietaire == null) {
      if (other.proprietaire != null) {
        return false;
      }
    } else if (!this.proprietaire.equals(other.proprietaire)) {
      return false;
    }
    return true;
  }

}
