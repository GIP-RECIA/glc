<script setup lang="ts">
import { getEtat } from "@/enums/Etat";

defineProps<{
  fistName: string;
  lastName: string;
  status: string;
}>();
</script>

<template>
  <v-card>
    <div class="ma-2">
      <v-icon icon="fas fa-user" :color="getEtat(status).color" />
      {{ lastName }} {{ fistName }}
    </div>
  </v-card>
</template>
