<script setup lang="ts">
import SearchPersonne from "@/components/SearchPersonne.vue";
import BaseModal from "@/components/modal/BaseModal.vue";
import { useConfigurationStore } from "@/stores/configurationStore";
import { useFonctionStore } from "@/stores/fonctionStore";
import { storeToRefs } from "pinia";
import { ref } from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const configurationStore = useConfigurationStore();
const { currentTab } = storeToRefs(configurationStore);

const fonctionStore = useFonctionStore();
const { customMapping } = storeToRefs(fonctionStore);

let dialog = ref<boolean>(false);
let selected = ref<Array<string>>([]);
</script>

<template>
  <div>
    <div class="fab ma-4">
      <v-btn color="primary" icon="fas fa-user-plus" @click="dialog = true" />
    </div>

    <base-modal v-model="dialog" :title="t('add')">
      Current tab : {{ currentTab }}
      <search-personne />
      <div>
        <b>{{ t("additionalFunction", 2) }}</b>
      </div>
      <div v-for="(filiere, index) in customMapping?.filieres" :key="index">
        <div>
          <b>{{ filiere.libelleFiliere }}</b>
        </div>
        <div class="d-flex flex-row flex-wrap">
          <div
            v-for="(discipline, index) in filiere.disciplines"
            :key="index"
            class="flex-item"
          >
            <v-checkbox
              v-model="selected"
              :label="discipline.disciplinePoste"
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
