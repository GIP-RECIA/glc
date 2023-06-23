<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import { usePersonneStore } from "@/stores/personneStore";
import { storeToRefs } from "pinia";

const personneStore = usePersonneStore();
const { personnes } = storeToRefs(personneStore);
</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col
        v-for="(user, index) in personnes"
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
