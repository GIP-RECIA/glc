import { useStructureStore } from "./structureStore";
import { getFonctions } from "@/services/fonctionService";
import type { Filiere } from "@/types/filiereType";
import type { Fonction } from "@/types/fonctionType";
import { defineStore } from "pinia";
import { computed, ref, unref } from "vue";

export const useFonctionStore = defineStore("fonctions", () => {
  const structureStore = useStructureStore();

  const fonctions = ref<Fonction | undefined>();

  const filieres = computed((): Array<Filiere> | undefined => {
    return fonctions.value
      ? fonctions.value["AC-ORLEANS-TOURS"].filiereWithDiscipline
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

  const ENS = computed((): Filiere | undefined => {
    return filieres.value?.find((filiere) => filiere.codeFiliere === "ENS");
  });

  const init = async (): Promise<void> => {
    fonctions.value = (await getFonctions()).data.payload;
  };

  const getFilteredFilieres = (filter: string): Array<Filiere> | undefined => {
    switch (filter) {
      case "etab":
        return unref(currentEtabFilieres);
      default:
        return unref(filieres);
    }
  };

  return { filieres, init, currentEtabFilieres, getFilteredFilieres, ENS };
});
