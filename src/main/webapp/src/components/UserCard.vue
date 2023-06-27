<script setup lang="ts">
import { usePersonneStore } from "@/stores/personneStore";
import { getEtat } from "@/types/enums/Etat";
import type { SimplePersonne } from "@/types/personneType";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const personneStore = usePersonneStore();
const { initCurrentPersonne } = personneStore;

const props = defineProps<{
  user: SimplePersonne;
}>();

const { color, i18n } = getEtat(props.user.etat);
</script>

<template>
  <v-card @click="initCurrentPersonne(user.id)">
    <v-card-text>
      <v-icon
        icon="fas fa-user"
        :color="color"
        :title="t(i18n)"
        :alt="t(i18n)"
        class="mr-2"
      />{{
        user.patronyme ? `${user.patronyme} ${user.givenName}` : user.givenName
      }}
    </v-card-text>
  </v-card>
</template>
