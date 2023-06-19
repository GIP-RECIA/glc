<script setup lang="ts">
import FiliereDisciplines from "@/components/FiliereDisciplines.vue";
import TeachingModal from "@/components/modal/TeachingModal.vue";
import { useFonctionStore } from "@/stores/fonctionStore";
import { storeToRefs } from "pinia";

const fonctionStore = useFonctionStore();
const { ENS } = storeToRefs(fonctionStore);
</script>

<template>
  <v-container fluid>
    <div v-if="ENS">
      <filiere-disciplines :filiere="ENS" />
    </div>

    <teaching-modal />
  </v-container>
</template>
