import { getConfiguration } from "@/services/configurationService";
import type { Configuration } from "@/types/configurationType";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useConfigurationStore = defineStore("configuration", () => {
  const configuration = ref<Configuration | undefined>();
  const structures = ref<Array<{ id: number; name: string }>>([]);
  const currentStructure = ref<number | undefined>();
  const currentTab = ref<string>("dashboard");

  const administrativeStaff = computed<Array<string> | undefined>(() => {
    return configuration.value
      ? configuration.value.administrativeStaff
      : undefined;
  });

  const administrativeCodes = computed<Array<string> | undefined>(() => {
    return configuration.value
      ? configuration.value.administrativeCodes
      : undefined;
  });

  const teachingCodes = computed<Array<string> | undefined>(() => {
    return configuration.value ? configuration.value.teachingCodes : undefined;
  });

  const init = async (): Promise<void> => {
    configuration.value = (await getConfiguration()).data.payload;
  };

  const setCurrentStructure = (value: number): void => {
    currentStructure.value = value;
  };

  const setCurrentTab = (value: string): void => {
    currentTab.value = value;
  };

  return {
    structures,
    currentStructure,
    currentTab,
    administrativeStaff,
    administrativeCodes,
    teachingCodes,
    init,
    setCurrentStructure,
  };
});
