<script setup lang="ts">
import { usePersonneStore } from "@/stores/personneStore";
import { storeToRefs } from "pinia";
import { ref, watch } from "vue";

const personneStore = usePersonneStore();
const { searchList } = storeToRefs(personneStore);

const select = ref<{ id: number; name: string } | undefined>();
const search = ref<string>();
const loading = ref<boolean>(false);
const items = ref<Array<{ id: number; name: string }>>([]);

watch(search, (val) => {
  if (typeof val !== "undefined" && val !== null && val.length > 0)
    querySelections(val);
  else items.value = [];
});

const querySelections = (q: string) => {
  if (q.length > 2 && searchList.value) {
    loading.value = true;
    items.value = searchList.value.filter(
      (item) => item.name.toLowerCase().indexOf(q.toLowerCase()) > -1
    );
    loading.value = false;
  } else {
    items.value = [];
    loading.value = false;
  }
};
</script>

<template>
  <v-autocomplete
    v-model="select"
    v-model:search="search"
    :loading="loading"
    :items="items"
    item-title="name"
    hide-no-data
    hide-details
    return-object
    clearable
    @click:clear="items = []"
    label="Select"
    variant="outlined"
  />
</template>
