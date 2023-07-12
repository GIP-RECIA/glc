<script setup lang="ts">
import AuthenticationService from "@/cas-publisher/AuthenticationService.js";
import PrincipalService from "@/cas-publisher/PrincipalService.js";
import { useConfigurationStore } from "@/stores/configurationStore";
import { storeToRefs } from "pinia";
import { computed } from "vue";
import { useI18n } from "vue-i18n";

const configurationStore = useConfigurationStore();
const { casUrlLogin, isAuthenticated } = storeToRefs(configurationStore);

const { t } = useI18n();

const modelValue = computed<boolean>(() => !isAuthenticated.value);

// Objet en charge de la redirection vers le serveur CAS
const relogState = {};

// Méthode en charge du processus de connexion
// Une fois connecté, l'utilisateur est redirigé
const login = async () => {
  AuthenticationService.login()
    .then(() => {
      // this.authenticationError = false;
      console.log("BACK TO HOME OR OTHER ROUTE");
    })
    .catch(() => {
      console.error("error");
      // this.authenticationError = true;
      relog();
    });
};

// Méthode effectuant une redirection sur le serveur CAS,
// un listener est mis en place afin de détecter la réponse
// du serveur CAS
const relog = (closeLoginModal = true) => {
  windowOpenCleanup(relogState, closeLoginModal);
  relogState.listener = onmessage;
  window.addEventListener("message", onmessage);

  relogState.window = window.open(casUrlLogin.value);

  // console.log(casUrlLogin.value);
  // if (casUrlLogin.value != undefined) window.open(casUrlLogin.value);
};

// Méthode de nettoyage de la page de login
const windowOpenCleanup = (state, closeLoginModal) => {
  try {
    if (state.listener) {
      window.removeEventListener("message", state.listener);
    }
    if (state.window) {
      state.window.close();
    }
    if (closeLoginModal) {
      // && this.$store.getters.getLoginModalOpened) {
      console.log("setLoginModalOpened");
      // this.$store.commit("setLoginModalOpened", false);
    }
  } catch (e) {
    // eslint-disable-next-line
    console.error(e);
  }
};

// Méthode utilisé lors de la réception de la réponse
// du serveur CAS puis redirige l'utilisateur
const onmessage = (e) => {
  if (typeof e.data !== "string") {
    return;
  }
  const m = e.data.match(/^loggedUser=(.*)$/);
  if (!m) {
    return;
  }

  windowOpenCleanup(relogState, true);
  PrincipalService.identify(true).then(() => {
    console.log("BACK TO HOME OR OTHER ROUTE");
  });
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
