import App from "@/App.vue";
import "@/assets/main.scss";
import i18n from "@/plugins/i18n";
import pinia from "@/plugins/pinia";
import vuetify from "@/plugins/vuetify";
import router from "@/router";
import { createApp } from "vue";
import "vuetify/styles";

const app = createApp(App);

app.use(pinia);
app.use(router);
app.use(vuetify);
app.use(i18n);

app.mount("#app");
