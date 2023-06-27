import {
  getEtablissements,
  getEtablissement,
} from "@/services/structureService";
import { useConfigurationStore } from "@/stores/configurationStore";
import type {
  Etablissement,
  SimpleEtablissement,
} from "@/types/etablissementType";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useStructureStore = defineStore("structure", () => {
  const configurationStore = useConfigurationStore();

  const etabs = ref<Array<SimpleEtablissement> | undefined>();
  const currentEtab = ref<Etablissement | undefined>();

  const init = async (): Promise<void> => {
    etabs.value = (await getEtablissements()).data.payload;
  };

  const initCurrentEtab = async (id: number): Promise<void> => {
    const { structures, setCurrentStructure, setCurrentTab } =
      configurationStore;

    currentEtab.value = (await getEtablissement(id)).data.payload;
    const index = structures.findIndex((structures) => structures.id == id);
    if (index == -1) {
      structures.push({
        id: id,
        name: currentEtab.value ? currentEtab.value.nom : "ERROR",
      });
      setCurrentStructure(structures.length - 1);
    } else setCurrentStructure(index);
    setCurrentTab("dashboard");
  };

  return {
    etabs,
    currentEtab,
    init,
    initCurrentEtab,
  };
});
