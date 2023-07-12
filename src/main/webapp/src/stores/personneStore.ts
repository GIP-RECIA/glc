import { getPersonne } from "@/services/personneService";
import { useConfigurationStore } from "@/stores/configurationStore";
import { useStructureStore } from "@/stores/structureStore";
import { Etat } from "@/types/enums/Etat";
import type { Personne, SimplePersonne } from "@/types/personneType";
import isEmpty from "lodash.isempty";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const usePersonneStore = defineStore("personne", () => {
  const configurationStore = useConfigurationStore();
  const structureStore = useStructureStore();

  /* -- Pour la personne courante -- */

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
   * courante formaté
   */
  const additionalFonctionsForCheckboxes = computed<Array<string>>(() => {
    const items = currentPersonne.value?.additionalFonctions.map(
      (fonction) => `${fonction.filiere}-${fonction.disciplinePoste}`
    );

    return typeof items === "undefined" ? [] : items;
  });

  /* -- Pour la structure courante -- */

  /**
   * Retourne la liste des personnes de la structure
   */
  const personnes = computed<Array<SimplePersonne> | undefined>(() => {
    const { currentEtab } = structureStore;

    return currentEtab?.personnes;
  });

  const deletedPersonnes = computed<Array<SimplePersonne>>(() => {
    const { currentEtab } = structureStore;

    const result = currentEtab?.personnes?.filter(
      (personne) => personne.etat == Etat.Delete
    );

    return isEmpty(result) ? [] : result!;
  });

  /**
   * Retourne la liste des personnes de la structure courante formaté
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
   * Retourne la liste des personnels administratifs de la structure courante
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
    deletedPersonnes,
    searchList,
    administrative,
  };
});
