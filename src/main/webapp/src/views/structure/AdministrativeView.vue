<script setup lang="ts">
import FiliereDisciplines from "@/components/layout/FiliereDisciplines.vue";
import AdditionalFonctionsModal from "@/components/modal/AdditionalFonctionsModal.vue";
import { useFonctionStore } from "@/stores/fonctionStore";
import { storeToRefs } from "pinia";

const fonctionStore = useFonctionStore();
const { administrative } = storeToRefs(fonctionStore);
</script>

<template>
  <v-container fluid>
    <filiere-disciplines
      v-for="(filiere, index) in administrative"
      :key="index"
      :filiere="filiere"
    />

    <additional-fonctions-modal />
  </v-container>
</template>
