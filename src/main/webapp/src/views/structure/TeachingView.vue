<script setup lang="ts">
import FiliereDisciplines from "@/components/FiliereDisciplines.vue";
import TeachingModal from "@/components/modal/TeachingModal.vue";
import { useFonctionStore } from "@/stores/fonctionStore";
import { ref } from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const fonctionStore = useFonctionStore();
const { getTeaching } = fonctionStore;

const filters = [
  {
    title: t("all"),
    value: "all",
  },
  {
    title: t("used"),
    value: "etab",
  },
];
const displayFilter = ref<{
  title: string;
  value: string;
}>(filters[1]);
</script>

<template>
  <v-container fluid>
    <v-select
      v-model="displayFilter"
      :items="filters"
      itemTitle="title"
      itemValue="value"
      :label="t('filter')"
      returnObject
    />
    <div
      v-for="(filiere, index) in getTeaching(displayFilter.value)"
      :key="index"
    >
      <filiere-disciplines :filiere="filiere" />
    </div>

    <teaching-modal />
  </v-container>
</template>
