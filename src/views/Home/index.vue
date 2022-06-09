<template>
  <div class="mt-12 ml-12 text-md-h3">
    Welcome {{ getUser.firstName }} to Ehealth
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "HomePage",
  computed: {
    ...mapGetters({
      getUser: "currentUser"
    })
  }
};
</script>

<style lang="scss" scoped></style>
