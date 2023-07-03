<script setup lang="ts">
import FiliereDisciplines from "@/components/layout/FiliereDisciplines.vue";
import { useConfigurationStore } from "@/stores/configurationStore";
import { useFonctionStore } from "@/stores/fonctionStore";
import { storeToRefs } from "pinia";

const configurationStore = useConfigurationStore();
const { isAdditionalFonction } = storeToRefs(configurationStore);

const fonctionStore = useFonctionStore();
const { teaching } = storeToRefs(fonctionStore);
</script>

<template>
  <v-container fluid>
    <filiere-disciplines
      v-for="(filiere, index) in teaching"
      :key="index"
      :filiere="filiere"
    />

    <div class="fab ma-4">
      <v-btn
        color="primary"
        icon="fas fa-user-plus"
        @click="isAdditionalFonction = true"
      />
    </div>
  </v-container>
</template>
