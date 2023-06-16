<script setup lang="ts">
import { useStructureStore } from "@/stores/structureStore";
import type { Etablissement } from "@/types/etablissementType";
import { ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const { structureId } = route.params;

const structureStore = useStructureStore();
const { getEtablissement } = structureStore;

const etablissement = ref(getEtablissement(Number(structureId)));
</script>

<template>
  <v-container fluid>
    <div v-if="etablissement">
      {{ etablissement }}
    </div>
  </v-container>
</template>
