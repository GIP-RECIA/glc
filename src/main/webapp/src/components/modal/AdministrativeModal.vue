<script setup lang="ts">
import BaseModal from "./BaseModal.vue";
import { ref } from "vue";

let dialog = ref<boolean>(false);
</script>

<template>
  <div>
    <v-btn
      color="primary"
      icon="fas fa-user-plus"
      @click="dialog = true"
    ></v-btn>

    <base-modal v-model="dialog" title="Test" :persistent="true">
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Non, ad quos. Hic
      veniam ipsa commodi recusandae fugiat, odio sed delectus facere. Est,
      fugiat similique architecto alias atque iure voluptate accusantium?
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

<style scoped></style>
