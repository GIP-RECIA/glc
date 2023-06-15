<script setup lang="ts">
import { useFonctionStore } from "@/stores/fonctionStore";

const fonctionStore = useFonctionStore();
fonctionStore.init();
</script>

<template>
  <header></header>
  <main>
    <RouterView />
  </main>
  <footer></footer>
</template>

<style scoped></style>
