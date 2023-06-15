import { getFonctions } from "@/services/fonctionService";
import type { Fonction } from "@/types/fonctionType";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useFonctionStore = defineStore("fonctions", () => {
  const fonctions = ref<Fonction>();

  const init = async (): Promise<void> => {
    fonctions.value = (await getFonctions()).data.payload;
  };

  return { fonctions, init };
});
