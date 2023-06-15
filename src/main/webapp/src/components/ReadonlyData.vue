<script setup lang="ts">
defineProps<{
  label: string;
  value?: string;
}>();
</script>

<template>
  <div>
    <div>
      {{ label }}
    </div>
    <div>
      {{ value ? value : "-" }}
    </div>
  </div>
</template>
