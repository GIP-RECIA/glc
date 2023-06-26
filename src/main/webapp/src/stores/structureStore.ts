import {
  getEtablissements,
  getEtablissement,
} from "@/services/structureService";
import type {
  Etablissement,
  SimpleEtablissement,
} from "@/types/etablissementType";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useStructureStore = defineStore("structure", () => {
  const etabs = ref<Array<SimpleEtablissement> | undefined>();
  const currentEtab = ref<Etablissement | undefined>();

  const init = async (): Promise<void> => {
    etabs.value = (await getEtablissements()).data.payload;
  };

  const initCurrentEtab = async (id: number): Promise<void> => {
    currentEtab.value = (await getEtablissement(id)).data.payload;
  };

  return {
    etabs,
    currentEtab,
    init,
    initCurrentEtab,
  };
});
