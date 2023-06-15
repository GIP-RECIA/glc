<script setup lang="ts"></script>

<template>
  <div>
    <RouterLink :to="{ name: 'structure', params: { structureId: 1234 } }"
      >Go to structure</RouterLink
    >
  </div>
</template>
