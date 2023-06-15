import { getEtablissements } from "@/services/structureService";
import type { Etablissement } from "@/types/etablissementType";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useStructureStore = defineStore("structure", () => {
  const etablissements = ref<Array<Etablissement>>();

  const init = async (): Promise<void> => {
    etablissements.value = (await getEtablissements()).data.payload;
  };

  return { etablissements, init };
});
