<template>
  <v-menu bottom min-width="200px" rounded offset-y>
    <template v-slot:activator="{ on }">
      <v-btn icon x-large v-on="on">
        <v-avatar color="brown" size="48">
          <span class="white--text text-h5">{{ getInitials }}</span>
        </v-avatar>
      </v-btn>
    </template>
    <v-card>
      <v-list-item-content class="justify-center">
        <div class="mx-auto text-center">
          <v-avatar color="brown">
            <span class="white--text text-h5">{{ getInitials }}</span>
          </v-avatar>
          <h3>{{ `${getUser().firstName} ${getUser().lastName}` }}</h3>
          <p class="text-caption mt-1">
            {{ getUser().email }}
          </p>
          <v-divider class="my-3"></v-divider>
          <v-btn depressed text block class="d-flex justify-start">
            <v-icon left> mdi-account-circle</v-icon>
            Profile
          </v-btn>
          <v-divider class="my-3"></v-divider>
          <v-btn
              depressed
              text
              block
              class="d-flex justify-start"
              @click="handleLogout"
          >
            <v-icon left> mdi-logout-variant</v-icon>
            Log out
          </v-btn>
        </div>
      </v-list-item-content>
    </v-card>
  </v-menu>
</template>

<script>
export default {
  name: "AvatarMenu",

  computed: {
    getInitials() {
      return `${this.getUser().firstName.toUpperCase()[0]}${
          this.getUser().lastName.toUpperCase()[0]
      }`;
    },
  },
  methods: {
    handleLogout() {
      localStorage.removeItem("currentUser");
      this.$router.push("/login");
    },
    getUser() {
      return JSON.parse(localStorage.getItem("currentUser"));
    },
  },
};
</script>

<style lang="scss" scoped></style>
