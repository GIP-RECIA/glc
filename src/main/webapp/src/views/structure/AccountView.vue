<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import { useStructureStore } from "@/stores/structureStore";
import { storeToRefs } from "pinia";

const structureStore = useStructureStore();
const { currentEtab } = storeToRefs(structureStore);
</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col
        v-for="(user, index) in currentEtab?.personnes"
        :key="index"
        :cols="12"
        :md="6"
        :lg="4"
        :xxl="3"
        class="pa-2"
      >
        <user-card :user="user" />
      </v-col>
    </v-row>
  </v-container>
</template>
