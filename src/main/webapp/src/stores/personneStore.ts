import { useConfigurationStore } from "./configurationStore";
import { useStructureStore } from "./structureStore";
import type { SimplePersonne } from "@/types/personneType";
import { defineStore } from "pinia";
import { computed } from "vue";

export const usePersonneStore = defineStore("personne", () => {
  const configurationStore = useConfigurationStore();
  const structureStore = useStructureStore();

  const personnes = computed((): Array<SimplePersonne> | undefined => {
    const { personnes } = structureStore.currentEtab;

    return personnes;
  });

  const administrative = computed((): Array<SimplePersonne> | undefined => {
    const { administrativeStaff } = configurationStore;

    return personnes.value?.filter((personne) =>
      administrativeStaff?.includes(personne.categorie)
    );
  });

  return { administrative };
});
