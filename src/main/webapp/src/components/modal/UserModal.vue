<script setup lang="ts">
import ReadonlyData from "@/components/ReadonlyData.vue";
import BaseModal from "@/components/modal/BaseModal.vue";
import { usePersonneStore } from "@/stores/personneStore";
import { storeToRefs } from "pinia";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const personneStore = usePersonneStore();
const { currentPersonne, isCurrentPersonne } = storeToRefs(personneStore);
</script>

<template>
  <base-modal
    v-model="isCurrentPersonne"
    :title="currentPersonne ? currentPersonne.displayName : ''"
  >
    <div class="d-flex flex-row flex-wrap">
      <readonly-data :label="t('status')" class="flex-item" />
      <readonly-data :label="t('mail')" class="flex-item" />
    </div>
    {{ currentPersonne }}
    <template #footer> </template>
  </base-modal>
</template>
