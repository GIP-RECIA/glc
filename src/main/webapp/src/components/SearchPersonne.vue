<script setup lang="ts">
import { usePersonneStore } from "@/stores/personneStore";
import { storeToRefs } from "pinia";
import { ref, watch } from "vue";

const personneStore = usePersonneStore();
const { personnes } = storeToRefs(personneStore);

const select = ref<string | undefined>();
const search = ref<string>();
const loading = ref<boolean>(false);
const items = ref<Array<string>>([]);

watch(search, (val) => {
  if (val == "") items.value = [];
  val && val !== select.value && querySelections(val);
});

const querySelections = (q: string) => {
  const searchList = personnes.value?.map((personne) =>
    personne.patronyme
      ? `${personne.patronyme} ${personne.givenName}`
      : personne.givenName
  );

  if (q.length > 2 && searchList) {
    loading.value = true;
    items.value = searchList.filter(
      (item) => item.toLowerCase().indexOf(q.toLowerCase()) > -1
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
    hide-no-data
    hide-details
    clearable
    @click:clear="items = []"
    label="Select"
    variant="outlined"
  />
</template>
