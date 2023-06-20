import { useStructureStore } from "./structureStore";
import { getFonctions } from "@/services/fonctionService";
import type { Filiere } from "@/types/filiereType";
import type { CustomMapping, SourceFonction } from "@/types/fonctionType";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useFonctionStore = defineStore("fonctions", () => {
  const structureStore = useStructureStore();

  const fonctions = ref<Array<SourceFonction> | undefined>();

  const filieres = computed((): Array<Filiere> | undefined => {
    const sourceEtab = structureStore.currentEtab
      ? structureStore.currentEtab.source
      : undefined;

    return fonctions.value
      ? fonctions.value.find((fonction) => fonction.source === sourceEtab)
          ?.filieres
      : undefined;
  });

  const customMapping = computed((): CustomMapping | undefined => {
    const sourceEtab = structureStore.currentEtab
      ? structureStore.currentEtab.source
      : undefined;

    return fonctions.value
      ? fonctions.value.find((fonction) => fonction.source === sourceEtab)
          ?.customMapping
      : undefined;
  });

  const currentEtabFilieres = computed((): Array<Filiere> | undefined => {
    const filieresInEtab = structureStore.currentEtab
      ? structureStore.currentEtab.filieres
      : [];

    return filieres.value?.filter((filiere) =>
      filieresInEtab.includes(filiere.id)
    );
  });

  const init = async (): Promise<void> => {
    fonctions.value = (await getFonctions()).data.payload;
  };

  const getAdministrative = (filter: string): Array<Filiere> | undefined => {
    const administrativeCodes = [
      "DIR",
      "ADF",
      "CTR",
      "EDU",
      "MDS",
      "PSY",
      "ADM",
      "AED",
    ];
    const administrative = filieres.value?.filter((filiere) =>
      administrativeCodes.includes(filiere.codeFiliere)
    );

    switch (filter) {
      case "etab":
        return administrative;
      default:
        return administrative;
    }
  };

  const getTeaching = (filter: string): Array<Filiere> | undefined => {
    const teachingCodes = [
      "ACP",
      "APP",
      "DOC",
      "DCT",
      "ENS",
      "FCA",
      "FIJ",
      "REM",
      "STG",
    ];
    const teaching = filieres.value?.filter((filiere) =>
      teachingCodes.includes(filiere.codeFiliere)
    );

    switch (filter) {
      case "etab":
        return teaching;
      default:
        return teaching;
    }
  };

  return {
    filieres,
    customMapping,
    init,
    getAdministrative,
    getTeaching,
  };
});
