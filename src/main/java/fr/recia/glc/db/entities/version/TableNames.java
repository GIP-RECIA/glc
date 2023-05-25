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
package fr.recia.glc.db.entities.version;

/**
 * Liste des noms des tables.
 *
 * @author GIP RECIA - Gribonvald Julien 4 févr. 09
 */
public enum TableNames {
  afonction,
  afonctionofrelationstage,
  agroupe,
  agroupeofapersonne,
  agroupeoffoncclassegroupe,
  anneescolaire,
  apersonne,
  apersonne_prenoms,
  apersonnes_astructures,
  apersonnes_agroupes,
  apersonnes_agroupes_enseignements,
  apersonnes_catdisciplines,
  apersonnes_centres_interets,
  apersonnes_services,
  application,
  applications_profils,
  astructure,
  astructures_applications,
  bassinformation,
  categoriediscipline,
  centreinteret,
  classe,
  collectivitelocale,
  discipline,
  domaines_gp_etab,
  droitsattribut,
  droitscategorie,
  eleve,
  eleves_enseignements,
  enseignant,
  enseignement,
  entreprise,
  etab_rattachement_administratif,
  etab_rattachement_fonctionnel,
  etablissement,
  externalids,
  fonction,
  fonctionclassegroupe,
  fonctiondomaine,
  fonctiondomainegroupementetablissement,
  @Deprecated
  fonctionenseignement,
  fonctionmef,
  fonctionrelation,
  fonctions_domaines,
  @Deprecated
  fonctions_enseignements,
  fonctions_mefs,
  fonctions_rel_of_eleves,
  fonctionstage,
  fusionpersonne,
  genuid,
  groupe,
  groupementetablissements,
  groupements_etablissements,
  incertain,
  login,
  mail,
  mef,
  ministeretutelle,
  nonenseignantcollectivitelocale,
  nonenseignantetablissement,
  nonenseignantserviceacademique,
  partenariat,
  personnelexterieur,
  personnerelationeleve,
  plugindescription,
  profil,
  property,
  relationeleve,
  relations_apersonnes,
  responsableentreprise,
  roleapplicatif,
  roles_applicatifs_profils,
  serviceacademique,
  telephone,
  tuteurstage,
  typedomaine,
  typefonctionfiliere,
  typeservice,
  typestructure,
  version,
  versiontable
}
