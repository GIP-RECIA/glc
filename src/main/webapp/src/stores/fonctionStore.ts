import { getFonctions } from "@/services/fonctionService";
import type { Filiere } from "@/types/filiereType";
import type { Fonction } from "@/types/fonctionType";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useFonctionStore = defineStore("fonctions", () => {
  const fonctions = ref<Fonction | undefined>();

  const filieres = computed((): Array<Filiere> | undefined =>
    fonctions.value
      ? fonctions.value["AC-ORLEANS-TOURS"].filiereWithDiscipline
      : undefined
  );

  const init = async (): Promise<void> => {
    fonctions.value = (await getFonctions()).data.payload;
  };

  return { filieres, init };
});
