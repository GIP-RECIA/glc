<script setup lang="ts">
import SearchPersonne from "@/components/SearchPersonne.vue";
import BaseModal from "@/components/modal/BaseModal.vue";
import { setPersonneAdditionalTeachings } from "@/services/personneService";
import { useConfigurationStore } from "@/stores/configurationStore";
import { storeToRefs } from "pinia";
import { computed, ref } from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const configurationStore = useConfigurationStore();
const { isAdditionalTeachings } = storeToRefs(configurationStore);

const selectedUser = ref<number | undefined>();
const selected = ref<Array<string>>([]);

const isSelected = computed<boolean>(() => selected.value.length > 0);

const isSelectedUser = computed<boolean>(() => selectedUser.value != undefined);

const setSelectedUser = (id: number | undefined) => {
  selectedUser.value = id;
};

const save = () => {
  isAdditionalTeachings.value = false;
  setPersonneAdditionalTeachings(selectedUser.value!, selected.value);
};
</script>

<template>
  <base-modal v-model="isAdditionalTeachings" :title="t('add')">
    <search-personne @update:select="setSelectedUser" />
    <div>
      <b>{{ t("additionalTeaching", 2) }}</b>
    </div>
    <template #footer>
      <v-btn
        color="success"
        prepend-icon="fas fa-floppy-disk"
        :disabled="!isSelectedUser || !isSelected"
        @click="save"
      >
        {{ t("save") }}
      </v-btn>
    </template>
  </base-modal>
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
