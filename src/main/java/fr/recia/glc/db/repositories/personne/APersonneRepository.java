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
package fr.recia.glc.db.repositories.personne;

import fr.recia.glc.db.dto.personne.PersonneDto;
import fr.recia.glc.db.dto.personne.SimplePersonneDto;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface APersonneRepository<T extends APersonne> extends AbstractRepository<T, Long> {

  @Query("SELECT new fr.recia.glc.db.dto.personne.PersonneDto(ap.id, ap.etat, ap.anneeScolaire, ap.categorie," +
    "ap.civilite, ap.cleJointure.source, ap.cn, ap.dateNaissance, ap.displayName, ap.email, ap.givenName, ap.numBureau, ap.patronyme, " +
    "ap.sn, ap.titre, ap.uid, ap.uuid, ap.emailPersonnel, ap.listeRouge, ap.forceEtat, ap.idEduConnect, " +
    "ap.login.nom, ap.structRattachement.id) " +
    "FROM APersonne ap " +
    "WHERE ap.id = :id")
  PersonneDto findByPersonneId(Long id);

  @Query("SELECT new fr.recia.glc.db.dto.personne.SimplePersonneDto(ap.id, ap.etat, ap.categorie, " +
    "ap.cleJointure.source, ap.givenName, ap.patronyme) " +
    "FROM APersonne ap " +
    "WHERE ap.structRattachement.id = :structureId")
  List<SimplePersonneDto> findByStructureId(Long structureId);

}
