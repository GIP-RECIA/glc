import { useConfigurationStore } from "./configurationStore";
import { useStructureStore } from "./structureStore";
import { getPersonne } from "@/services/personneService";
import type { Personne, SimplePersonne } from "@/types/personneType";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const usePersonneStore = defineStore("personne", () => {
  const configurationStore = useConfigurationStore();
  const structureStore = useStructureStore();

  const currentPersonne = ref<Personne | undefined>();

  const isCurrentPersonne = computed<boolean>({
    get() {
      return currentPersonne.value ? true : false;
    },
    set() {
      currentPersonne.value = undefined;
    },
  });

  const personnes = computed<Array<SimplePersonne> | undefined>(() => {
    const { personnes } = structureStore.currentEtab;

    return personnes;
  });

  const searchList = computed<Array<{ id: number; name: string }> | undefined>(
    () => {
      const { personnes } = structureStore.currentEtab;

      return personnes.map((personne) => {
        return {
          id: personne.id,
          name: personne.patronyme
            ? `${personne.patronyme} ${personne.givenName}`
            : personne.givenName,
        };
      });
    }
  );

  const administrative = computed<Array<SimplePersonne> | undefined>(() => {
    const { administrativeStaff } = configurationStore;

    return personnes.value?.filter((personne) =>
      administrativeStaff?.includes(personne.categorie)
    );
  });

  const initCurrentPersonne = async (id: number): Promise<void> => {
    currentPersonne.value = (await getPersonne(id)).data.payload;
  };

  return {
    currentPersonne,
    isCurrentPersonne,
    personnes,
    searchList,
    administrative,
    initCurrentPersonne,
  };
});
