<script setup lang="ts">
import { useStructureStore } from "@/stores/structureStore";
import type { SimpleEtablissement } from "@/types/etablissementType";
import { storeToRefs } from "pinia";
import { watch, ref } from "vue";

const structureStore = useStructureStore();
structureStore.init();
const { etabs } = storeToRefs(structureStore);

const select = ref<string>();
const items = ref<Array<SimpleEtablissement> | undefined>();
const pageItems = ref<Array<SimpleEtablissement> | undefined>();
const pagination = ref({
  page: 1,
  pages: 1,
});
const itemsPerPage: number = 20;

watch(etabs, (newValue) => {
  if (typeof newValue !== "undefined" && newValue !== null)
    items.value = newValue;
});

watch(select, (newValue) => {
  if (typeof newValue !== "undefined" && newValue !== null) {
    items.value = etabs.value?.filter((etablissement) => {
      let filters =
        etablissement.nom.toLowerCase().indexOf(newValue.toLowerCase()) > -1 ||
        etablissement.uai.toLowerCase().indexOf(newValue.toLowerCase()) > -1;
      if (etablissement.ville) {
        filters =
          filters ||
          etablissement.ville.toLowerCase().indexOf(newValue.toLowerCase()) >
            -1;
      }

      return filters;
    });
  } else items.value = etabs.value;
});

watch(items, (newValue) => {
  if (typeof newValue !== "undefined" && newValue !== null) {
    pagination.value.page = 1;
    pagination.value.pages = Math.round(
      items.value ? items.value.length / itemsPerPage : 1
    );
    showPage(1);
  }
});

const showPage = (page: number) => {
  if (typeof items.value !== "undefined" && items.value !== null) {
    pageItems.value = items.value.filter((_, index) => {
      return page == 1
        ? index < itemsPerPage
        : index >= (page - 1) * itemsPerPage && index < page * itemsPerPage;
    });
  }
};
</script>

<template>
  <v-container>
    <v-text-field
      v-model="select"
      variant="solo"
      rounded
      clearable
      flat
      hide-details
      class="mb-8"
    />
    <v-row>
      <v-col
        v-for="(etablissement, index) in pageItems"
        :key="index"
        :cols="12"
        :md="6"
        :lg="4"
        :xl="3"
      >
        <v-btn
          block
          flat
          :to="{ name: 'structure', params: { structureId: etablissement.id } }"
        >
          <span v-if="etablissement.type">
            {{ etablissement.nom }} ({{ etablissement.type }}
            {{ etablissement.uai }})
          </span>
          <span v-else>
            {{ etablissement.nom }}
          </span>
        </v-btn>
      </v-col>
    </v-row>
    <v-pagination
      v-if="pagination.pages > 1"
      v-model="pagination.page"
      :length="pagination.pages"
      rounded="circle"
      class="mt-8"
      @update:model-value="showPage"
    />
  </v-container>
</template>
