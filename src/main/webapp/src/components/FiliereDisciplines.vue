<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import type { Filiere } from "@/types/filiereType";

defineProps<{
  filiere: Filiere;
}>();
</script>

<template>
  <div>
    <div class="mb-3">{{ filiere.libelleFiliere }}</div>
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
        <v-card :title="discipline.disciplinePoste">
          <v-card-text>
            <v-row>
              <v-col
                :cols="6"
                v-for="(user, index) in 0"
                :key="index"
                class="pa-2"
              >
                <user-card
                  :fistName="user.givenName"
                  :lastName="user.patronyme"
                  :status="user.etat"
                />
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>
