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
package fr.recia.glc.db.repositories.structure;

import fr.recia.glc.db.dto.structure.EtablissementDto;
import fr.recia.glc.db.entities.structure.Etablissement;
import fr.recia.glc.db.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EtablissementRepository<T extends Etablissement> extends AbstractRepository<T, Long> {

  @Query("SELECT new fr.recia.glc.db.dto.structure.EtablissementDto(e.id, e.uai, " +
    "new fr.recia.glc.db.dto.common.AdresseDto(e.adresse.adresse , e.adresse.codePostal , e.adresse.ville, " +
    "e.adresse.boitePostale, e.adresse.pays), e.categorie, e.nom, e.nomCourt, e.siren) " +
    "FROM Etablissement e " +
    "WHERE e.uai IS NOT NULL")
  List<EtablissementDto> findAllEtablissements();

  @Query("SELECT new fr.recia.glc.db.dto.structure.EtablissementDto(e.id, e.uai, e.etat, e.etatAlim, " +
    "e.anneeScolaire, new fr.recia.glc.db.dto.common.AdresseDto(e.adresse.adresse , e.adresse.codePostal , " +
    "e.adresse.ville, e.adresse.boitePostale, e.adresse.pays), e.categorie, e.mail, e.nom, e.nomCourt, e.siren, " +
    "e.siteWeb, e.modeleLogin, e.logo) " +
    "FROM Etablissement e " +
    "WHERE e.id = :id")
  EtablissementDto findByIdEtablissement(Long id);

}
