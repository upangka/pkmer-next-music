<script lang="ts" setup>
import { onMounted } from 'vue'

onMounted(insertInitScript)

function insertInitScript() {
  const scriptEl = document.createElement('script')

  scriptEl.innerHTML = `
      window.__PKMER_MUSIC__ = (function init(exports) {
        function hello() {
          console.log("hello world");
        }
        exports.hello = hello;
        return exports;
      })({});
    `
  document.head.appendChild(scriptEl)
}
</script>

<template>
  <RouterView #default="{ Component }">
    <component :is="Component" />
  </RouterView>
</template>
