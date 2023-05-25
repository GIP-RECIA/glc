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
import fr.recia.glc.db.entities.structure.AStructure;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Groupe de personnes (AGroupeOfAPersonne) étendu en profil.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>cn, membres, reglePeuplement.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class Profil extends AGroupeOfAPersonne {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 671660028950908342L;

  //Attributs
  /**
   * Définition de la règle de peuplement.
   */
  private String reglePeuplement;

  //Relations
  /**
   * Relation bidirectionnelle.
   * Structure ayant défini le profil.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "structure_fk")
  private AStructure proprietaire;

  /**
   * Relation bidirectionnelle.
   * Listes des applications ayant besoin de ce profil.
   */
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "profils")
  private Set<Application> applications = new HashSet<>();

  //Constructeurs

  /**
   * Constructeur de l'objet Profil.java.
   */
  public Profil() {
    super();
    this.setCategorie(CategorieGroupe.Profil);
  }

  /**
   * Constructeur de l'objet Profil.java.
   *
   * @param cn              Nom unique de groupe, peut servir comme identifiant.
   * @param membres         Liste des membres du groupe obtenu à partir de la règle.
   * @param reglePeuplement Régle de peuplement du groupe.
   * @param source          Source ayant créé l'objet.
   */
  public Profil(final String cn, final Set<MappingAGroupeAPersonne> membres,
                final String reglePeuplement, final String source) {
    super(cn, CategorieGroupe.Profil, membres, source);
    this.reglePeuplement = reglePeuplement;
  }

  //Accesseurs

  /**
   * Getter du membre reglePeuplement.
   *
   * @return <code>String</code> le membre reglePeuplement.
   */
  public String getReglePeuplement() {
    return this.reglePeuplement;
  }

  /**
   * Setter du membre reglePeuplement.
   *
   * @param reglePeuplement la nouvelle valeur du membre reglePeuplement.
   */
  public void setReglePeuplement(final String reglePeuplement) {
    this.reglePeuplement = reglePeuplement;
  }

  //Relations

  /**
   * Getter du membre proprietaire.
   *
   * @return <code>AStructure</code> le membre proprietaire.
   */
  public AStructure getProprietaire() {
    return this.proprietaire;
  }

  /**
   * Setter du membre proprietaire.
   *
   * @param proprietaire la nouvelle valeur du membre proprietaire.
   */
  public void setProprietaire(final AStructure proprietaire) {
    this.proprietaire = proprietaire;
  }

  /**
   * Getter du membre applications.
   *
   * @return <code>Set< Application ></code> le membre applications.
   */
  public Set<Application> getApplications() {
    return this.applications;
  }

  /**
   * Setter du membre applications.
   *
   * @param applications la nouvelle valeur du membre applications.
   */
  public void setApplications(final Set<Application> applications) {
    this.applications = applications;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.groupe.AGroupeOfAPersonne#toString()
   */
  @Override
  public String toString() {
    return "Profil [" +
      super.toString() + ", " +
      this.reglePeuplement +
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
    if (this.reglePeuplement == null) {
      result = prime * result;
    } else {
      result = prime * result + this.reglePeuplement.hashCode();
    }
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
    if (!(obj instanceof Profil)) {
      return false;
    }
    final Profil other = (Profil) obj;
    if (this.reglePeuplement == null) {
      if (other.reglePeuplement != null) {
        return false;
      }
    } else if (!this.reglePeuplement.equals(other.reglePeuplement)) {
      return false;
    }
    return true;
  }

}
