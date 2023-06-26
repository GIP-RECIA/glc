<script setup lang="ts">
import { usePersonneStore } from "@/stores/personneStore";
import { storeToRefs } from "pinia";
import { ref, watch } from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const personneStore = usePersonneStore();
const { searchList } = storeToRefs(personneStore);

defineEmits<
  (
    event: "update:select",
    payload: { id: number; name: string } | undefined
  ) => void
>();

const select = ref<{ id: number; name: string } | undefined>();
const search = ref<string>();
const loading = ref<boolean>(false);
const items = ref<Array<{ id: number; name: string }>>([]);
const searchOutOfStructure = ref<boolean>(false);

watch(search, (newSearch) => {
  if (typeof newSearch !== "undefined" && newSearch !== null) {
    if (newSearch.length > 3) {
      searchOutOfStructure.value
        ? findOutOfStructure(newSearch)
        : findInStructure(newSearch);
    } else {
      items.value = [];
      loading.value = false;
    }
  } else items.value = [];
});

watch(searchOutOfStructure, () => {
  select.value = undefined;
});

const findInStructure = (name: string): void => {
  if (searchList.value) {
    loading.value = true;
    items.value = searchList.value.filter(
      (item) => item.name.toLowerCase().indexOf(name.toLowerCase()) > -1
    );
    loading.value = false;
  }
};

const findOutOfStructure = (name: string): void => {
  loading.value = true;
  items.value = [];
  loading.value = false;
};
</script>

<template>
  <div>
    <v-switch
      v-model="searchOutOfStructure"
      :label="t('searchOutOfStructure')"
      hide-details
      inset
    />
    <v-autocomplete
      v-model="select"
      v-model:search="search"
      :label="t('people', 1)"
      :loading="loading"
      :items="items"
      item-title="name"
      hide-no-data
      hide-details
      return-object
      clearable
      variant="outlined"
      @click:clear="items = []"
      @update:model-value="$emit('update:select', select?.id)"
    />
  </div>
</template>
