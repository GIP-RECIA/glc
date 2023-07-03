import { getConfiguration } from "@/services/configurationService";
import type { Configuration } from "@/types/configurationType";
import { Tabs } from "@/types/enums/Tabs";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useConfigurationStore = defineStore("configuration", () => {
  const configuration = ref<Configuration | undefined>();

  /**
   * Initialise `configuration`
   */
  const init = async (): Promise<void> => {
    configuration.value = (await getConfiguration()).data.payload;
  };

  /**
   * Retourne la liste des types de personnel administratif
   */
  const administrativeStaff = computed<Array<string> | undefined>(() => {
    return configuration.value
      ? configuration.value.administrativeStaff
      : undefined;
  });

  /**
   * Retourne la liste des codes de filière d'administration
   */
  const administrativeCodes = computed<Array<string> | undefined>(() => {
    return configuration.value
      ? configuration.value.administrativeCodes
      : undefined;
  });

  /**
   * Retourne la liste des codes de filière d'enseigments
   */
  const teachingCodes = computed<Array<string> | undefined>(() => {
    return configuration.value ? configuration.value.teachingCodes : undefined;
  });

  /* --- Gestion des onglets de structure --- */

  const structures = ref<Array<{ id: number; name: string }>>([]);
  const currentStructure = ref<number | undefined>();
  const currentTab = ref<number>(Tabs.Dashboard);

  const setCurrentStructure = (value: number): void => {
    currentStructure.value = value;
  };

  const setCurrentTab = (value: number): void => {
    currentTab.value = value;
  };

  /* --  -- */

  const isAdditionalFonction = ref<boolean>(false);

  return {
    init,
    administrativeStaff,
    administrativeCodes,
    teachingCodes,
    structures,
    currentStructure,
    currentTab,
    setCurrentStructure,
    setCurrentTab,
    isAdditionalFonction,
  };
});
