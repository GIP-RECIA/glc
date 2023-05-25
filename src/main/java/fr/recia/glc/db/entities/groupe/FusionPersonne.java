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
import java.util.Set;

/**
 * Bean - Entity.
 * Groupe de personne, permettant d'informer sur le fait que plusieurs personnes définies sont les mêmes.
 * C'est l'utilisateur qui informe effectue la fusion, prévoir un prcessus de mot de passe sur chaque
 * compte pour la fusion, permet ainsi de définir un seul utilisateur pour plusieurs clé de jointure par exemple.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>cn, membres (deux membres au minimum doivent être donnés).</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 11 juin 08
 */
@Entity
public class FusionPersonne extends AGroupeOfAPersonne {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 4846698873547654588L;

  //Constructeurs

  /**
   * Constructeur de l'objet FusionPersonne.java.
   */
  public FusionPersonne() {
    super();
    this.setCategorie(CategorieGroupe.Fusion);
  }

  /**
   * Constructeur de l'objet FusionPersonne.java.
   *
   * @param cn      Nom du groupe de fusion, peut servir à définir le compte principal.
   * @param membres Liste des personnes fusionnant.
   * @param source  Source ayant créé l'objet.
   */
  public FusionPersonne(final String cn, final Set<MappingAGroupeAPersonne> membres, final String source) {
    super(cn, CategorieGroupe.Fusion, membres, source);
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.groupe.AGroupeOfAPersonne#toString()
   */
  @Override
  public String toString() {
    return "FusionPersonne [" + super.toString() + "]";
  }

}
