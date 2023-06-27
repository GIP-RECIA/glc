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
package fr.recia.glc.web.rest;

import fr.recia.glc.db.dto.personne.PersonneDto;
import fr.recia.glc.db.entities.fonction.Fonction;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.repositories.fonction.FonctionRepository;
import fr.recia.glc.db.repositories.personne.APersonneRepository;
import fr.recia.glc.models.apiresponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping(value = "/personne")
public class PersonneController {

  @Autowired
  private APersonneRepository<APersonne> aPersonneRepository;
  @Autowired
  private FonctionRepository<Fonction> fonctionRepository;

  @GetMapping
  public ApiResponse searchPersonne(
    @RequestParam(value = "name") String name
  ) {
    return new ApiResponse(
      "",
      ""
    );
  }

  @GetMapping(value = "/{id}")
  public ApiResponse getPersonne(@PathVariable Long id) {
    PersonneDto personne = aPersonneRepository.findByPersonneId(id);
    personne.setFonctions(fonctionRepository.findByPersonneIdAndStructure(id, personne.getStructure()));

    return new ApiResponse(
      "",
      personne
    );
  }

}
