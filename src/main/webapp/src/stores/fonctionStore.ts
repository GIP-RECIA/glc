import { useConfigurationStore } from "./configurationStore";
import { useStructureStore } from "./structureStore";
import { getFonctions } from "@/services/fonctionService";
import type { Filiere } from "@/types/filiereType";
import type { CustomMapping, SourceFonction } from "@/types/fonctionType";
import isEmpty from "lodash.isempty";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useFonctionStore = defineStore("fonctions", () => {
  const configurationStore = useConfigurationStore();
  const structureStore = useStructureStore();

  const fonctions = ref<Array<SourceFonction> | undefined>();

  const filieres = computed<Array<Filiere> | undefined>(() => {
    const { currentEtab } = structureStore;

    return fonctions.value
      ? fonctions.value.find(
          (fonction) => fonction.source === currentEtab?.source
        )?.filieres
      : undefined;
  });

  const customMapping = computed<CustomMapping | undefined>(() => {
    const { currentEtab } = structureStore;

    const customMapping = fonctions.value?.find(
      (fonction) => fonction.source === currentEtab?.source
    )?.customMapping;

    return isEmpty(customMapping) ? undefined : customMapping;
  });

  const administrative = computed<Array<Filiere> | undefined>(() => {
    const { administrativeCodes } = configurationStore;
    const { currentEtab } = structureStore;

    return currentEtab?.filieres.filter((filiere) =>
      administrativeCodes?.includes(filiere.codeFiliere)
    );
  });

  const teaching = computed<Array<Filiere> | undefined>(() => {
    const { teachingCodes } = configurationStore;
    const { currentEtab } = structureStore;

    return currentEtab?.filieres.filter((filiere) =>
      teachingCodes?.includes(filiere.codeFiliere)
    );
  });

  const init = async (): Promise<void> => {
    fonctions.value = (await getFonctions()).data.payload;
  };

  return {
    filieres,
    customMapping,
    administrative,
    teaching,
    init,
  };
});
