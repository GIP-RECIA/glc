<script setup lang="ts">
import { signIn } from "@/services/authenticationService";
import { useConfigurationStore } from "@/stores/configurationStore";
import { storeToRefs } from "pinia";
import { computed } from "vue";
import { useI18n } from "vue-i18n";

const configurationStore = useConfigurationStore();
const { casUrlLogin, isAuthenticated } = storeToRefs(configurationStore);

const { t } = useI18n();

const modelValue = computed<boolean>(() => !isAuthenticated.value);

const login = async () => {
  try {
    const response = await signIn();
    console.log(response);
  } catch (e) {
    relog();
  }
};

const relog = () => {
  if (casUrlLogin.value != undefined) window.open(casUrlLogin.value);
};
</script>

<template>
  <v-dialog v-model="modelValue" :max-width="300">
    <v-card>
      <v-toolbar color="rgba(0, 0, 0, 0)">
        <v-toolbar-title class="text-h6">{{ t("casSignIn") }}</v-toolbar-title>
      </v-toolbar>
      <v-card-text> </v-card-text>
      <v-card-actions class="d-flex justify-end">
        <v-btn
          color="primary"
          prepend-icon="fas fa-right-to-bracket"
          @click="login"
        >
          {{ t("signIn") }}
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
