<script setup lang="ts">
import FiliereDisciplines from "@/components/layout/FiliereDisciplines.vue";
import TeachingModal from "@/components/modal/TeachingModal.vue";
import { useFonctionStore } from "@/stores/fonctionStore";
import { storeToRefs } from "pinia";

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

    <teaching-modal />
  </v-container>
</template>
