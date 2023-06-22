import { getConfiguration } from "@/services/configurationService";
import type { Configuration } from "@/types/configurationType";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useConfigurationStore = defineStore("configuration", () => {
  const configuration = ref<Configuration | undefined>();

  const administrativeStaff = computed((): Array<string> | undefined => {
    return configuration.value
      ? configuration.value.administrativeStaff
      : undefined;
  });

  const init = async (): Promise<void> => {
    configuration.value = (await getConfiguration()).data.payload;
  };

  return { administrativeStaff, init };
});