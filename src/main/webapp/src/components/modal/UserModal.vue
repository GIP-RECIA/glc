<script setup lang="ts">
import ReadonlyData from "@/components/ReadonlyData.vue";
import BaseModal from "@/components/modal/BaseModal.vue";
import { usePersonneStore } from "@/stores/personneStore";
import moment from "moment";
import { storeToRefs } from "pinia";
import { useI18n } from "vue-i18n";

const { t } = useI18n();

const personneStore = usePersonneStore();
const { currentPersonne, isCurrentPersonne } = storeToRefs(personneStore);
</script>

<template>
  <base-modal
    v-model="isCurrentPersonne"
    :title="
      currentPersonne
        ? currentPersonne.patronyme
          ? `${currentPersonne.patronyme} ${currentPersonne.givenName}`
          : currentPersonne.givenName
        : ''
    "
  >
    <div v-if="currentPersonne" class="d-flex flex-row flex-wrap">
      <readonly-data
        label="uid"
        :value="currentPersonne.uid"
        class="flex-item"
      />
      <readonly-data
        label="uuid"
        :value="currentPersonne.uuid"
        class="flex-item"
      />
      <readonly-data
        label="idEduConnect"
        :value="currentPersonne.idEduConnect"
        class="flex-item"
      />
      <readonly-data
        :label="t('civility')"
        :value="currentPersonne.civilite"
        class="flex-item"
      />
      <readonly-data
        :label="t('lastName')"
        :value="currentPersonne.patronyme"
        class="flex-item"
      />
      <readonly-data
        :label="t('firstName')"
        :value="currentPersonne.givenName"
        class="flex-item"
      />
      <readonly-data
        :label="t('birthDate')"
        :value="moment(currentPersonne.dateNaissance).format('L')"
        class="flex-item"
      />
      <readonly-data
        :label="t('email') + ' ac'"
        :value="currentPersonne.email"
        class="flex-item"
      />
      <readonly-data
        :label="t('email') + ' perso'"
        :value="currentPersonne.emailPersonnel"
        class="flex-item"
      />
      <readonly-data
        :label="t('schoolYear')"
        :value="moment(currentPersonne.anneeScolaire).format('Y')"
        class="flex-item"
      />
      <readonly-data
        :label="t('login')"
        :value="currentPersonne.login"
        class="flex-item"
      />
      <readonly-data
        :label="t('status')"
        :value="currentPersonne.etat"
        class="flex-item"
      />
      <readonly-data
        label="categorie"
        :value="currentPersonne.categorie"
        class="flex-item"
      />
      <readonly-data label="cn" :value="currentPersonne.cn" class="flex-item" />
      <readonly-data
        label="displayName"
        :value="currentPersonne.displayName"
        class="flex-item"
      />
      <readonly-data
        label="numBureau"
        :value="currentPersonne.numBureau"
        class="flex-item"
      />
      <readonly-data label="sn" :value="currentPersonne.sn" class="flex-item" />
      <readonly-data
        label="titre"
        :value="currentPersonne.titre"
        class="flex-item"
      />
      <readonly-data
        label="listeRouge"
        :value="currentPersonne.listeRouge.toString()"
        class="flex-item"
      />
      <readonly-data
        label="forceEtat"
        :value="currentPersonne.forceEtat"
        class="flex-item"
      />
    </div>
    <template #footer>
      <div class="d-flex justify-space-between w-100">
        <div>
          <v-btn color="secondary" prepend-icon="fas fa-lock">
            {{ t("lock") }}
          </v-btn>
          <v-btn color="secondary" prepend-icon="fas fa-lock-open">
            {{ t("unlock") }}
          </v-btn>
          <v-btn color="secondary" prepend-icon="fas fa-rotate-right">
            {{ t("reinitialize") }}
          </v-btn>
        </div>
        <div>
          <v-btn color="primary" prepend-icon="fas fa-plus">
            {{ t("add") }}
          </v-btn>
        </div>
      </div>
    </template>
  </base-modal>
</template>

<style scoped lang="scss">
.flex-item {
  width: 100%;
}

@media (min-width: 700px) {
  .flex-item {
    width: 50%;
  }
}
</style>
