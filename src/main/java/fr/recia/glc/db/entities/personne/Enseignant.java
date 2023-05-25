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
package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.groupe.MappingAGroupeAPersonneEnseignement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity. APersonne étendu en Enseignant.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>uid, login, password, cn, sn, displayName, givenName, cleJointure, CategoriePersonne.Enseignant, structureRattachement, anneeScolaire,
 * etat.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 11 juin 08
 */
@Entity
public class Enseignant extends APersonnel {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -6998038156079022481L;

  // Attributs
  /**
   * Association d'un(e) Classe/Groupe avec un enseignement
   */
  @OneToMany(mappedBy = "pk.enseignant", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  private Set<MappingAGroupeAPersonneEnseignement> groupesEnseignements = new HashSet<>();

  // Constructeurs

  /**
   * Constructeur de l'objet Enseignant.java.
   */
  public Enseignant() {
    super();
    this.setCategorie(CategoriePersonne.Enseignant);
  }

  /**
   * Constructeur de l'objet Enseignant.java.
   *
   * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
   * @param cleJointure   Clé de jointure, identifiant unique fourni par les différentes sources, mais unique uniquement pour le périmètre de la
   *                      source.
   * @param cn            Nom canonique de la personne : NOM + Prénom usuels.
   * @param givenName     Prénom usuel.
   * @param sn            Nom d'usage.
   */
  public Enseignant(final Date anneeScolaire, final CleJointure cleJointure,
                    final String cn, final String givenName, final String sn) {
    super(anneeScolaire, CategoriePersonne.Enseignant, cleJointure, cn, givenName, sn);
  }

  // Accesseurs

  /**
   * Getter of member groupesEnseignements.
   *
   * @return <code>Set<MappingAGroupeAPersonneEnseignement></code> the attribute groupesEnseignements
   */
  public Set<MappingAGroupeAPersonneEnseignement> getGroupesEnseignements() {
    return groupesEnseignements;
  }

  /**
   * Setter of attribute groupesEnseignements.
   *
   * @param groupesEnseignements the attribute groupesEnseignements to set
   */
  public void setGroupesEnseignements(final Set<MappingAGroupeAPersonneEnseignement> groupesEnseignements) {
    this.groupesEnseignements = groupesEnseignements;
  }

  /**
   * Getter of member groupesEnseignements.
   *
   * @return <code>Set<MappingAGroupeAPersonneEnseignement></code> the attribute groupesEnseignements
   */
	/* public Set<MappingAGroupeAPersonneEnseignement> getGroupesEnseignements() {
    return groupesEnseignements;
  }*/

  /**
   * Setter of attribute groupesEnseignements.
   *
   * @param groupesEnseignements the attribute groupesEnseignements to set
   */
  /* public void setGroupesEnseignement(final Set<MappingAGroupeAPersonneEnseignement> groupesEnseignements) {
    this.groupesEnseignements = groupesEnseignements;
  }*/

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.personne.APersonne#toString()
   */
  @Override
  public String toString() {
    return "Enseignant [" + super.toString() + "]";
  }

}
