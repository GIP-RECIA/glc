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
import fr.recia.glc.db.dto.personne.SimplePersonneDto;
import fr.recia.glc.db.dto.structure.EtablissementDto;
import fr.recia.glc.db.entities.education.Discipline;
import fr.recia.glc.db.entities.fonction.Fonction;
import fr.recia.glc.db.entities.fonction.TypeFonctionFiliere;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.Etablissement;
import fr.recia.glc.db.repositories.education.DisciplineRepository;
import fr.recia.glc.db.repositories.fonction.FonctionRepository;
import fr.recia.glc.db.repositories.fonction.TypeFonctionFiliereRepository;
import fr.recia.glc.db.repositories.personne.APersonneRepository;
import fr.recia.glc.db.repositories.structure.EtablissementRepository;
import fr.recia.glc.models.apiresponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController()
@RequestMapping(value = "/structure/etablissement")
public class EtablissementController {

  @Autowired
  private EtablissementRepository<Etablissement> etablissementRepository;
  @Autowired
  private FonctionRepository<Fonction> fonctionRepository;
  @Autowired
  private APersonneRepository<APersonne> aPersonneRepository;
  @Autowired
  private DisciplineRepository<Discipline> disciplineRepository;
  @Autowired
  private TypeFonctionFiliereRepository<TypeFonctionFiliere> typeFonctionFiliereRepository;

  @GetMapping()
  public ApiResponse getEtablissements() {
    return new ApiResponse("", etablissementRepository.findAllEtablissements());
  }

  @GetMapping(value = "/{id}")
  public ApiResponse getEtablissement(@PathVariable Long id) {
    EtablissementDto etablissement = etablissementRepository.findByEtablissementId(id);
    etablissement.setFilieres(getUsersByCategories(id, etablissement.getSource()));
    etablissement.setPersonnes(aPersonneRepository.findByStructureId(id));

    return new ApiResponse("", etablissement);
  }

  private List<TypeFonctionFiliereDto> getUsersByCategories(Long id, String source) {
    // Recherche des filières, disciplines et fonctions les liants
    List<FonctionDto> fonctions = fonctionRepository.findByStructureId(id);
    List<TypeFonctionFiliereDto> typesFonctionFiliere = typeFonctionFiliereRepository.findBySource(source);
    List<DisciplineDto> disciplines = disciplineRepository.findBySource(source);
    List<SimplePersonneDto> personnes = aPersonneRepository.findByStructureId(id);

    // Retourne les filières et disciplines s'il n'y a pas de fonction les liants
    if (fonctions.isEmpty()) return Collections.emptyList();

    // Ajout des disciplines aux filières
    List<SimplePersonneDto> finalPersonnes = personnes;
    typesFonctionFiliere = typesFonctionFiliere.stream()
      .map(typeFonctionFiliere -> {
        // Filtre les fonctions de la filière
        List<FonctionDto> fonctionsInFiliere = fonctions.stream()
          .filter(fonction -> Objects.equals(fonction.getFiliere(), typeFonctionFiliere.getId()))
          .toList();
        // Liste les ID de disciplines de la filière
        Set<Long> disciplineIds = fonctionsInFiliere.stream()
          .map(FonctionDto::getDisciplinePoste)
          .collect(Collectors.toSet());
        // Liste les disciplines de la filière
        List<DisciplineDto> disciplinesInFiliere = disciplines.stream()
          .filter(discipline -> disciplineIds.contains(discipline.getId()))
          .toList();
        // Ajout des utilisateurs
        disciplinesInFiliere = disciplinesInFiliere.stream()
          .map(discipline -> {
            // Liste des ID de personne de la discipline
            Set<Long> personneIds = fonctionsInFiliere.stream()
              .filter(fonction -> Objects.equals(fonction.getDisciplinePoste(), discipline.getId()))
              .map(FonctionDto::getPersonne)
              .collect(Collectors.toSet());
            // Liste les personnes de la discipline
            List<SimplePersonneDto> personnesInDiscipline = finalPersonnes.stream()
              .filter(personne -> personneIds.contains(personne.getId()))
              .toList();
            discipline.setPersonnes(personnesInDiscipline);

            return discipline;
          })
          .toList();

        typeFonctionFiliere.setDisciplines(disciplinesInFiliere);

        return typeFonctionFiliere;
      }).toList();

    // Retrait des filières sans disciplines
    return typesFonctionFiliere.stream()
      .filter(typeFonctionFiliere -> !typeFonctionFiliere.getDisciplines().isEmpty())
      .toList();
  }

}
