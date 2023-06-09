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
package fr.recia.glc.db.dto.personne;

import fr.recia.glc.db.dto.common.AdresseDto;
import fr.recia.glc.db.entities.common.Adresse;
import fr.recia.glc.db.enums.CategoriePersonne;
import fr.recia.glc.db.enums.Civilite;
import fr.recia.glc.db.enums.Etat;
import fr.recia.glc.db.enums.ForceEtat;
import fr.recia.glc.db.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class PersonneDto {

  private Etat etat;
  private Date anneeScolaire;
  private CategoriePersonne categorie;
  private AdresseDto adresse;
  private Civilite civilite;
  private String cn;
  private Date dateNaissance;
  private String displayName;
  private String email;
  private String givenName;
  private String numBureau;
  private String patronyme;
//  private Sexe sexe;
  private String sn;
  private String titre;
  private String uid;
  private String uuid;
  private String emailPersonnel;
  private boolean listeRouge;
  private ForceEtat forceEtat;
  private String idEduConnect;
  private String login;
//  private String alias;
//  private Set<String> prenoms;

}
