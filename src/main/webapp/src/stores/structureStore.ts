import { getEtablissements } from "@/services/structureService";
import type { Etablissement } from "@/types/etablissementType";
import { defineStore } from "pinia";
import { readonly, ref } from "vue";

export const useStructureStore = defineStore("structure", () => {
  const etablissements = ref<Array<Etablissement> | undefined>();

  const init = async (): Promise<void> => {
    etablissements.value = (await getEtablissements()).data.payload;
  };

  const getEtablissement = (id: number): Etablissement | undefined =>
    etablissements.value?.find((etablissement) => etablissement.id === id);

  return {
    etablissements: readonly(etablissements),
    init,
    getEtablissement,
  };
});
