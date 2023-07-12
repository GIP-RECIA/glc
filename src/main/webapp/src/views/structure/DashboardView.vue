<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import { usePersonneStore } from "@/stores/personneStore";
import { storeToRefs } from "pinia";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const personneStore = usePersonneStore();
const { deletedPersonnes } = storeToRefs(personneStore);
</script>

<template>
  <v-container fluid>
    <v-card :subtitle="`${t('warning', 2)} (${0})`" flat class="mb-4">
      <v-card-text></v-card-text>
    </v-card>
    <v-card
      :subtitle="`${t('deletingOrDeletedAccounts')} (${
        deletedPersonnes.length
      })`"
      flat
    >
      <v-card-text>
        <v-row>
          <v-col
            v-for="(personne, index) in deletedPersonnes"
            :key="index"
            :cols="12"
            :md="6"
            :lg="4"
            :xxl="3"
            class="pa-2"
          >
            <user-card variant="tonal" :user="personne" />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>
