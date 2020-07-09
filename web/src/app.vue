<template>
  <div>
    <the-header></the-header>

    <router-view></router-view>

    <the-footer></the-footer>
  </div>
</template>

<script>

import TheHeader from "./components/the-header";
import TheFooter from "./components/the-footer";
export default {
  name: 'app',
  components: {TheFooter, TheHeader},
}
</script>
