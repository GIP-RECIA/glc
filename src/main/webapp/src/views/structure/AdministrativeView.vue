<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import AdministrativeModal from "@/components/modal/AdministrativeModal.vue";
import { useFonctionStore } from "@/stores/fonctionStore";
import { capitalize } from "@/utils/stringUtils";
import { ref } from "vue";

const fonctionStore = useFonctionStore();

const filieres = ref(
  fonctionStore.fonctions["AC-ORLEANS-TOURS"].filiereWithDiscipline
);
const etats = ref([
  "Invalide",
  "Valide",
  "Bloque",
  "Delete",
  "Incertain",
  "Incertain_Export_Delete",
  "Incertain_Export_Modify",
  "Incertain_Export_Add",
]);
</script>

<template>
  <v-container fluid>
    <div v-for="(filiere, index) in filieres" :key="index">
      <div>{{ capitalize(filiere.libelleFiliere) }}</div>
      <v-row>
        <v-col
          v-for="(discipline, index) in filiere.disciplines"
          :key="index"
          :cols="12"
          :md="6"
          :lg="4"
          :xxl="3"
          class="pa-2"
        >
          <v-card :title="capitalize(discipline.disciplinePoste)">
            <v-card-text>
              <v-row>
                <v-col
                  :cols="6"
                  v-for="(user, index) in etats"
                  :key="index"
                  class="pa-2"
                >
                  <user-card
                    :fistName="'Didier'"
                    :lastName="'CUNAFO'"
                    :status="user"
                  />
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>

    <administrative-modal />
  </v-container>
</template>
