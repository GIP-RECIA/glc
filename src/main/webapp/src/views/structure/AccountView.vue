<script setup lang="ts">
import UserCard from "@/components/UserCard.vue";
import AccountFilter from "@/components/filter/AccountFilter.vue";
import { usePersonneStore } from "@/stores/personneStore";
import type { SimplePersonne } from "@/types/personneType";
import { storeToRefs } from "pinia";
import { watch, ref } from "vue";

const personneStore = usePersonneStore();
const { personnes } = storeToRefs(personneStore);

const items = ref<Array<SimplePersonne> | undefined>();
const pageItems = ref<Array<SimplePersonne> | undefined>();
const pagination = ref({
  page: 1,
  pages: 1,
});
const itemsPerPage: number = 20;

watch(personnes, (newValue) => {
  if (typeof newValue !== "undefined" && newValue !== null)
    items.value = newValue;
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

items.value = personnes.value;
</script>

<template>
  <v-container fluid>
    <account-filter
      class="mb-8"
      @update:result="(result: Array<SimplePersonne>) => items = result"
    />
    <v-row>
      <v-col
        v-for="(user, index) in pageItems"
        :key="index"
        :cols="12"
        :md="6"
        :lg="4"
        :xxl="3"
        class="pa-2"
      >
        <user-card variant="flat" :user="user" />
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
