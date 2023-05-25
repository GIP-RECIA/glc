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

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity. Description abstraite d'un groupe (AGroupe) étendu en groupe de personne.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>categorie, cn, membres.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 10 juin 08
 */
@SuppressWarnings("serial")
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class AGroupeOfAPersonne extends AGroupe {

  // Attributs

  // Relations
  /**
   * Relation bidirectionnelle. Liste des personnes membre du groupe.
   */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.groupe")
  private Set<MappingAGroupeAPersonne> membres = new HashSet<>();

  // Constructeurs

  /**
   * Constructeur de l'objet AGroupeOfAPersonne.java.
   */
  public AGroupeOfAPersonne() {
    super();
  }

  /**
   * Constructeur de l'objet AGroupeOfAPersonne.java.
   *
   * @param cn        Nom unique de groupe, peut servir comme identifiant.
   * @param categorie Type de groupe.
   * @param membres   Liste des membres du groupe.
   * @param source    Source ayant créé l'objet.
   */
  public AGroupeOfAPersonne(final String cn, final CategorieGroupe categorie,
                            final Set<MappingAGroupeAPersonne> membres, final String source) {
    super(cn, categorie, source);
    this.membres = membres;
  }

  // Accesseurs

  // Relations

  /**
   * Getter du membre membres.
   *
   * @return <code>Set< APersonne ></code> le membre membres.
   */
  public Set<MappingAGroupeAPersonne> getMembres() {
    return this.membres;
  }

  /**
   * Setter du membre membres.
   *
   * @param membres la nouvelle valeur du membre membres.
   */
  public void setMembres(final Set<MappingAGroupeAPersonne> membres) {
    this.membres = membres;
  }

  /**
   * Ajoute un membre au groupe.
   *
   * @param membre le mebre à ajouter.
   */
  public void addMembre(final MappingAGroupeAPersonne membre) {
    this.membres.add(membre);
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.groupe.AGroupe#toString()
   */
  @Override
  public String toString() {
    return "AGroupeOfAPersonne [" + super.toString() + "]";
  }

}
