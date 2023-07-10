<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import type { Filiere } from "@/types/filiereType";

defineProps<{
  filiere: Filiere;
}>();
</script>

<template>
  <div class="pb-4">
    <div>
      <div class="pb-2">
        {{ filiere.libelleFiliere }}
      </div>
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
          <v-card :title="discipline.disciplinePoste" flat>
            <v-card-text>
              <v-row>
                <v-col
                  :cols="6"
                  v-for="(user, index) in discipline.personnes"
                  :key="index"
                  class="pa-2"
                >
                  <user-card variant="tonal" :user="user" />
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
