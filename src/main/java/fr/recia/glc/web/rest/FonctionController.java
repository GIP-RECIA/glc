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

import fr.recia.glc.db.dto.education.DisciplineDto;
import fr.recia.glc.db.dto.fonction.FonctionDto;
import fr.recia.glc.db.dto.fonction.TypeFonctionFiliereDto;
import fr.recia.glc.db.entities.education.Discipline;
import fr.recia.glc.db.entities.fonction.Fonction;
import fr.recia.glc.db.entities.fonction.TypeFonctionFiliere;
import fr.recia.glc.db.repositories.education.DisciplineRepository;
import fr.recia.glc.db.repositories.fonction.FonctionRepository;
import fr.recia.glc.db.repositories.fonction.TypeFonctionFiliereRepository;
import fr.recia.glc.models.apiresponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController()
@RequestMapping(value = "/fonction")
public class FonctionController {

  @Autowired
  private FonctionRepository<Fonction> fonctionRepository;
  @Autowired
  private DisciplineRepository<Discipline> disciplineRepository;
  @Autowired
  private TypeFonctionFiliereRepository<TypeFonctionFiliere> typeFonctionFiliereRepository;

  @GetMapping(value = "/")
  public ApiResponse getFonctions() {

    List<FonctionDto> fonctions = fonctionRepository.findAllUi();
    List<TypeFonctionFiliereDto> typesFonctionFiliere = typeFonctionFiliereRepository.findBySource("AC-ORLEANS-TOURS");
    List<DisciplineDto> disciplines = disciplineRepository.findBySource("AC-ORLEANS-TOURS");

    typesFonctionFiliere = typesFonctionFiliere.stream().map(typeFonctionFiliere -> {
      List<Long> disciplineIds = fonctions.stream().filter(fonction -> fonction.getFiliere() == typeFonctionFiliere.getId())
        .map(FonctionDto::getDisciplinePoste)
        .toList();
      typeFonctionFiliere.setDisciplines(disciplineIds);

      return typeFonctionFiliere;
    }).toList();

    Map<String, List<?>> data = new HashMap<>();
    data.put("typesFonctionFiliere", typesFonctionFiliere);
    data.put("disciplines", disciplines);

    return new ApiResponse(
      "",
      data
      );
  }

}
