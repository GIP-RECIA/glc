<script setup lang="ts">
import FiliereDisciplines from "@/components/layout/FiliereDisciplines.vue";
import AdministrativeModal from "@/components/modal/AdministrativeModal.vue";
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

    <administrative-modal />
  </v-container>
</template>
