<script setup lang="ts">
import { useStructureStore } from "@/stores/structureStore";
import { storeToRefs } from "pinia";

const structureStore = useStructureStore();
const { currentEtab } = storeToRefs(structureStore);
</script>

<template>
  <v-container fluid>
    <div v-if="currentEtab">
      {{ currentEtab }}
    </div>
  </v-container>
</template>
