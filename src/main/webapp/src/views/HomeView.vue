<script setup lang="ts">
import { useStructureStore } from "@/stores/structureStore";

const structureStore = useStructureStore();
</script>

<template>
  <v-container>
    <v-row>
      <v-col
        v-for="(etablissement, index) in structureStore.etablissements"
        :key="index"
        :cols="12"
        :sm="6"
      >
        <v-btn
          block
          :to="{ name: 'structure', params: { structureId: etablissement.id } }"
        >
          {{ etablissement.nom }}
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>
