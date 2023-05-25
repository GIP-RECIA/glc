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
 * AGroupeOfFoncClasseGroupe (Groupe de personnes avec fonction) étendu en groupe.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>cn, membres, proprietaire.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class Groupe extends AGroupeOfFoncClasseGroupe {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -4188409793285248122L;

  //Attributs
  /**
   * Relation unidirectionnelle.
   * Liste des classes associées au groupe.
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JoinTable(name = "groupes_classes",
    joinColumns =
    @JoinColumn(name = "GROUPE_ID", referencedColumnName = "ID"),
    inverseJoinColumns =
    @JoinColumn(name = "CLASSE_ID", referencedColumnName = "ID"))
  private Set<Classe> relationsClasses = new HashSet<>();

  //Constructeurs

  /**
   * Constructeur de l'objet Groupe.java.
   */
  public Groupe() {
    super();
    this.setCategorie(CategorieGroupe.Groupe);
  }

  /**
   * Constructeur de l'objet Groupe.java.
   *
   * @param cn           Nom unique du groupe, peut servir comme identifiant.
   * @param membres      Liste des personnes membre du groupe.
   * @param proprietaire Etablissement ayant défini ce groupe.
   * @param source       Source ayant créé l'objet.
   */
  public Groupe(final String cn, final Set<MappingAGroupeAPersonne> membres,
                final Etablissement proprietaire, final String source) {
    super(cn, CategorieGroupe.Groupe, membres, proprietaire, source);
  }

  /**
   * Getter of member relationsClasses.
   *
   * @return <code>Set<Classe></code> the attribute relationsClasses
   */
  public Set<Classe> getRelationsClasses() {
    return relationsClasses;
  }

  /**
   * Setter of attribute relationsClasses.
   *
   * @param relationsClasses the attribute relationsClasses to set
   */
  public void setRelationsClasses(final Set<Classe> relationsClasses) {
    this.relationsClasses = relationsClasses;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.groupe.AGroupeOfFoncClasseGroupe#toString()
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Groupe [");
    sb.append(super.toString());
    if (this.relationsClasses != null && !this.relationsClasses.isEmpty()) {
      sb.append(", Liste de classes liées : [");
      for (Classe classe : this.relationsClasses) {
        sb.append(classe.getCn());
        sb.append(", ");
      }
      sb.delete(sb.length() - 2, sb.length());
      sb.append("]");
    }
    sb.append("]");
    return sb.toString();
  }

}
