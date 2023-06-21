<script setup lang="ts">
import ReadonlyData from "@/components/ReadonlyData.vue";
import BaseModal from "@/components/modal/BaseModal.vue";
// import { useFonctionStore } from "@/stores/fonctionStore";
import { ref } from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

let dialog = ref<boolean>(false);
let selected = ref<Array<string>>([]);

// const fonctionStore = useFonctionStore();
// const {} = fonctionStore;
</script>

<template>
  <div>
    <div class="fab ma-4">
      <v-btn color="primary" icon="fas fa-user-plus" @click="dialog = true" />
    </div>

    <base-modal v-model="dialog" :title="t('add')">
      <div v-if="ENS">
        <div class="d-flex flex-row flex-wrap">
          <readonly-data :label="t('status')" class="flex-item" />
          <readonly-data :label="t('mail')" class="flex-item" />
        </div>
        <div>{{ t("lessons", 2) }}</div>
        <div class="d-flex flex-row flex-wrap">
          <div v-for="(discipline, index) in 0" :key="index" class="flex-item">
            <v-checkbox
              v-model="selected"
              :label="discipline.disciplinePoste"
              :value="`${ENS.id}-${discipline.id}`"
              color="primary"
              :hide-details="true"
            />
          </div>
        </div>
      </div>
      <template #footer>
        <v-btn
          color="success"
          prepend-icon="fas fa-floppy-disk"
          @click="dialog = false"
        >
          {{ t("save") }}
        </v-btn>
      </template>
    </base-modal>
  </div>
</template>

<style scoped lang="scss">
.flex-item {
  width: 100%;
}

@media (min-width: 700px) {
  .flex-item {
    width: 50%;
  }
}
</style>
