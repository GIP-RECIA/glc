import VueI18nPlugin from "@intlify/unplugin-vue-i18n/vite";
import vue from "@vitejs/plugin-vue";
import { dirname, resolve } from "node:path";
import { fileURLToPath, URL } from "node:url";
import { defineConfig } from "vite";
import vuetify from "vite-plugin-vuetify";

// https://vitejs.dev/config/
export default defineConfig({
  root: "./src/main/webapp",
  envDir: "../../../",
  plugins: [
    vue(),
    vuetify({ autoImport: true }),
    VueI18nPlugin({
      include: resolve(
        dirname(fileURLToPath(import.meta.url)),
        "./src/main/webapp/src/locales/**"
      ),
    }),
  ],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src/main/webapp/src", import.meta.url)),
    },
  },
  build: {
    rollupOptions: {
      output: {
        assetFileNames: "assets/glc-[name].[ext]",
        entryFileNames: "assets/glc-[name].js",
        chunkFileNames: "assets/glc-[name].js",
      },
    },
  },
});
