<script setup lang="ts">
import ReadonlyData from "@/components/ReadonlyData.vue";
import BaseModal from "@/components/modal/BaseModal.vue";
import { useFonctionStore } from "@/stores/fonctionStore";
import { capitalize } from "@/utils/stringUtils";
import { ref } from "vue";

let dialog = ref<boolean>(false);
let selected = ref<Array<number>>([]);

const fonctionStore = useFonctionStore();

const filieres = ref(
  fonctionStore.fonctions["AC-ORLEANS-TOURS"].filiereWithDiscipline
);
</script>

<template>
  <div>
    <div class="fab ma-4">
      <v-btn color="primary" icon="fas fa-user-plus" @click="dialog = true" />
    </div>

    <base-modal v-model="dialog" :title="$t('add')">
      <div class="d-flex flex-row flex-wrap">
        <readonly-data :label="$t('status')" class="flex-item" />
        <readonly-data :label="$t('mail')" class="flex-item" />
      </div>
      <div>{{ $t("additionalFunction", 2) }}</div>
      <div v-for="(filiere, index) in filieres" :key="index">
        <div>{{ capitalize(filiere.libelleFiliere) }}</div>
        <div class="d-flex flex-row flex-wrap">
          <div
            v-for="(discipline, index) in filiere.disciplines"
            :key="index"
            class="flex-item"
          >
            <v-checkbox
              v-model="selected"
              :label="capitalize(discipline.disciplinePoste)"
              :value="`${filiere.id}-${discipline.id}`"
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
          {{ $t("save") }}
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
