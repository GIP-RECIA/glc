<script setup lang="ts">
import UserModal from "@/components/modal/UserModal.vue";
import { useConfigurationStore } from "@/stores/configurationStore";
import { useFonctionStore } from "@/stores/fonctionStore";
import { useStructureStore } from "@/stores/structureStore";
import AccountView from "@/views/structure/AccountView.vue";
import AdministrativeView from "@/views/structure/AdministrativeView.vue";
import DashboardView from "@/views/structure/DashboardView.vue";
import ExportView from "@/views/structure/ExportView.vue";
import InfoView from "@/views/structure/InfoView.vue";
import TeachingView from "@/views/structure/TeachingView.vue";
import { storeToRefs } from "pinia";
import { watch } from "vue";
import { useI18n } from "vue-i18n";
import { useRoute } from "vue-router";

const { t } = useI18n();

const route = useRoute();
const { structureId } = route.params;

const fonctionStore = useFonctionStore();
fonctionStore.init();

const structureStore = useStructureStore();
structureStore.initCurrentEtab(Number(structureId));

const configurationStore = useConfigurationStore();
const { currentTab } = storeToRefs(configurationStore);

watch(
  () => route.params.structureId,
  (newValue) => {
    structureStore.initCurrentEtab(Number(newValue));
  }
);
</script>

<template>
  <div>
    <v-tabs v-model="currentTab" align-tabs="center">
      <v-tab value="dashboard">{{ t("dashboard") }}</v-tab>
      <v-tab value="info">{{ t("information") }}</v-tab>
      <v-tab value="administrativeStaff">{{ t("administrativeStaff") }}</v-tab>
      <v-tab value="teachingStaff">{{ t("teachingStaff") }}</v-tab>
      <v-tab value="accounts">{{ t("accounts") }}</v-tab>
      <v-tab value="exports">{{ t("exports") }}</v-tab>
    </v-tabs>
    <v-window v-model="currentTab">
      <v-window-item value="dashboard">
        <dashboard-view />
      </v-window-item>
      <v-window-item value="info">
        <info-view />
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
    <user-modal />
  </div>
</template>
