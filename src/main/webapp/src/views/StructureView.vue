<script setup lang="ts">
import AccountView from "./structure/AccountView.vue";
import AdministrativeView from "./structure/AdministrativeView.vue";
import DashboardView from "./structure/DashboardView.vue";
import ExportView from "./structure/ExportView.vue";
import TeachingView from "./structure/TeachingView.vue";
import { ref } from "vue";

let tab = ref<string>();
</script>

<template>
  <div>
    <v-tabs v-model="tab" align-tabs="center">
      <v-tab value="dashboard">{{ $t("dashboard") }}</v-tab>
      <v-tab value="administrativeStaff">{{ $t("administrativeStaff") }}</v-tab>
      <v-tab value="teachingStaff">{{ $t("teachingStaff") }}</v-tab>
      <v-tab value="accounts">{{ $t("accounts") }}</v-tab>
      <v-tab value="exports">{{ $t("exports") }}</v-tab>
    </v-tabs>
    <v-window v-model="tab">
      <v-window-item value="dashboard">
        <dashboard-view />
      </v-window-item>
      <v-window-item value="administrativeStaff">
        <administrative-view />
      </v-window-item>
      <v-window-item value="teachingStaff">
        <teaching-view />
      </v-window-item>
      <v-window-item value="accounts">
        <account-view />
      </v-window-item>
      <v-window-item value="exports">
        <export-view />
      </v-window-item>
    </v-window>
  </div>
</template>

<style scoped></style>
