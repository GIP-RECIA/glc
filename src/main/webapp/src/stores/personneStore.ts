import { useConfigurationStore } from "./configurationStore";
import { useStructureStore } from "./structureStore";
import { getPersonne } from "@/services/personneService";
import type { Personne, SimplePersonne } from "@/types/personneType";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const usePersonneStore = defineStore("personne", () => {
  const configurationStore = useConfigurationStore();
  const structureStore = useStructureStore();

  /* -- Pour la personne courrante -- */

  const currentPersonne = ref<Personne | undefined>();

  /**
   * Initialise `currentPersonne`
   *
   * @param id Identifiant de la personne
   */
  const initCurrentPersonne = async (id: number): Promise<void> => {
    currentPersonne.value = (await getPersonne(id)).data;
  };

  /**
   * Retourne s'il y a une personne de défini ou l'efface
   */
  const isCurrentPersonne = computed<boolean>({
    get() {
      return currentPersonne.value ? true : false;
    },
    set() {
      currentPersonne.value = undefined;
    },
  });

  /**
   * Retourne la liste des fonctions complémentaires de la personne
   * courrante formaté
   */
  const additionalFonctionsForCheckboxes = computed<Array<string>>(() => {
    const items = currentPersonne.value?.additionalFonctions.map(
      (fonction) => `${fonction.filiere}-${fonction.disciplinePoste}`
    );

    return typeof items === "undefined" ? [] : items;
  });

  /* -- Pour la structure courrante -- */

  /**
   * Retourne la liste des personnes de la structure
   */
  const personnes = computed<Array<SimplePersonne> | undefined>(() => {
    const { currentEtab } = structureStore;

    return currentEtab?.personnes;
  });

  /**
   * Retourne la liste des personnes de la structure courrante formaté
   */
  const searchList = computed<Array<{ id: number; name: string }> | undefined>(
    () => {
      const { currentEtab } = structureStore;

      return currentEtab?.personnes.map((personne) => {
        return {
          id: personne.id,
          name: personne.patronyme
            ? `${personne.patronyme} ${personne.givenName}`
            : personne.givenName,
        };
      });
    }
  );

  /**
   * Retourne la liste des personnels administratifs de la structure courrante
   */
  const administrative = computed<Array<SimplePersonne> | undefined>(() => {
    const { administrativeStaff } = configurationStore;

    return personnes.value?.filter((personne) =>
      administrativeStaff?.includes(personne.categorie)
    );
  });

  return {
    currentPersonne,
    initCurrentPersonne,
    isCurrentPersonne,
    additionalFonctionsForCheckboxes,
    personnes,
    searchList,
    administrative,
  };
});
