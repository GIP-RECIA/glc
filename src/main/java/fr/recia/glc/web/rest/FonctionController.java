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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import fr.recia.glc.models.mappers.AdditionalFonctionMapping;
import fr.recia.glc.models.mappers.AdditionalFonctionMappingFiliere;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

  @GetMapping()
  public ApiResponse getFonctions() {
    Map<String, Object> data = new HashMap<>();

    List<String> sources = fonctionRepository.findAllSources();
    sources.forEach(source -> {
      List<FonctionDto> fonctions = fonctionRepository.findBySource(source);
      List<TypeFonctionFiliereDto> typesFonctionFiliere;
      List<DisciplineDto> disciplines;

      if (source.startsWith("SarapisUi_")) {
        String querySource = source.substring("SarapisUi_".length());
        typesFonctionFiliere = typeFonctionFiliereRepository.findBySourceSarapis(querySource);
        disciplines = disciplineRepository.findBySourceSarapis(querySource);
      } else {
        typesFonctionFiliere = typeFonctionFiliereRepository.findBySource(source);
        disciplines = disciplineRepository.findBySource(source);
      }

      Set<Long> usedDisciplineIds = new HashSet<>();

      typesFonctionFiliere = typesFonctionFiliere.stream().map(typeFonctionFiliere -> {
        Set<Long> disciplineIds = fonctions.stream()
          .filter(fonction -> Objects.equals(fonction.getFiliere(), typeFonctionFiliere.getId()))
          .map(FonctionDto::getDisciplinePoste)
          .collect(Collectors.toSet());
        usedDisciplineIds.addAll(disciplineIds);
        List<DisciplineDto> disciplinesInFiliere = disciplines.stream()
          .filter(discipline -> disciplineIds.contains(discipline.getId()))
          .toList();
        typeFonctionFiliere.setDisciplines(disciplinesInFiliere);

        return typeFonctionFiliere;
      }).toList();

      List<DisciplineDto> unusedDisciplines = disciplines.stream()
        .filter(discipline -> !usedDisciplineIds.contains(discipline.getId()))
        .toList();

      Map<String, Object> object = new HashMap<>();
      object.put(
        "filiereWithDiscipline",
        typesFonctionFiliere.stream()
          .filter(typeFonctionFiliere -> !typeFonctionFiliere.getDisciplines().isEmpty())
          .toList()
      );
      object.put(
        "filiereWithoutDisciplines",
        typesFonctionFiliere.stream()
          .filter(typeFonctionFiliere -> typeFonctionFiliere.getDisciplines().isEmpty())
          .toList()
      );
      object.put("disciplinesWithoutFiliere", unusedDisciplines);
      try {
        object.put("additional", getAdditional(source));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      data.put(source, object);
    });

    return new ApiResponse("", data);
  }

  private Map<String, Object> getAdditional(String source) throws IOException {
    Map<String, Object> data = new HashMap<>();

    ObjectMapper objectMapper = new ObjectMapper();
    List<AdditionalFonctionMapping> additionalFonctionsMapping = objectMapper.readValue(
      new File("src/main/resources/mapping/additionalFonctionMapping.json"),
      new TypeReference<>() {
      }
    );

    AdditionalFonctionMapping additionalFonctionMapping = additionalFonctionsMapping.stream()
      .filter(af -> Objects.equals(af.getSource(), source))
      .findAny()
      .orElse(null);

    if (additionalFonctionMapping == null) {
      return data;
    }

    List<TypeFonctionFiliereDto> typesFonctionFiliere;
    if (source.startsWith("SarapisUi_")) {
      String querySource = source.substring("SarapisUi_".length());
      typesFonctionFiliere =
        typeFonctionFiliereRepository.findByCodeAndSourceSarapis(
          additionalFonctionMapping.getFilieres().stream()
            .map(AdditionalFonctionMappingFiliere::getCode)
            .toList(),
          querySource
        );
    } else {
      typesFonctionFiliere =
        typeFonctionFiliereRepository.findByCodeAndSource(
          additionalFonctionMapping.getFilieres().stream()
            .map(AdditionalFonctionMappingFiliere::getCode)
            .toList(),
          source
        );
    }

    typesFonctionFiliere = typesFonctionFiliere.stream()
      .map(typeFonctionFiliere -> {
        List<String> disciplineCodes = additionalFonctionMapping.getFilieres().stream()
          .filter(af -> Objects.equals(af.getCode(), typeFonctionFiliere.getCodeFiliere()))
          .findAny()
          .map(AdditionalFonctionMappingFiliere::getDisciplines)
          .get();

        List<DisciplineDto> disciplines;
        if (source.startsWith("SarapisUi_")) {
          String querySource = source.substring("SarapisUi_".length());
          disciplines = disciplineRepository.findByCodeAndSourceSarapis(disciplineCodes, querySource);
        } else {
          disciplines = disciplineRepository.findByCodeAndSource(disciplineCodes, source);
        }
        typeFonctionFiliere.setDisciplines(disciplines);

        return typeFonctionFiliere;
      })
      .toList();

    data.put("additionalWithFiliere", typesFonctionFiliere);

    List<DisciplineDto> disciplines;
    if (source.startsWith("SarapisUi_")) {
      String querySource = source.substring("SarapisUi_".length());
      disciplines = disciplineRepository.findByCodeAndSourceSarapis(additionalFonctionMapping.getDisciplines(), querySource);
    } else {
      disciplines = disciplineRepository.findByCodeAndSource(additionalFonctionMapping.getDisciplines(), source);
    }
    data.put("additionalWithoutFiliere", disciplines);

    return data;
  }

}
