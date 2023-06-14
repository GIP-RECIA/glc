<script setup lang="ts">
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

    <v-dialog v-model="dialog" persistent width="auto">
      <v-card>
        <v-toolbar color="rgba(0, 0, 0, 0)">
          <v-toolbar-title class="text-h6">Title</v-toolbar-title>
          <template v-slot:append>
            <v-btn
              icon="fas fa-xmark"
              color="default"
              variant="plain"
              @click="dialog = false"
            />
          </template>
        </v-toolbar>
        <v-card-text>Body</v-card-text>
        <v-card-actions>
          <v-btn
            color="success"
            prepend-icon="fas fa-floppy-disk"
            @click="dialog = false"
          >
            {{ $t("save") }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped></style>
