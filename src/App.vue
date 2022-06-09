<template>
  <v-app>
    <div class="app-container d-flex">
      <v-navigation-drawer
        permanent
        v-if="$route.path !== '/login' && $route.path !== '/register'"
      >
        <v-list-item class="logo-container">
          <LogoComponent />
        </v-list-item>

        <v-list nav class="mt-8">
          <v-list-item link to="/">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Home</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item link to="/dashboard" v-if="isAdmin">
            <v-list-item-icon>
              <v-icon>mdi-view-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Dashboard</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item link to="/calendar">
            <v-list-item-icon>
              <v-icon>mdi-help-box</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Calendar</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
      <div class="app-container__right-side">
        <v-app-bar
          elevation="0"
          height="68"
          class="d-flex justify-end"
          v-if="$route.path !== '/login' && $route.path !== '/register'"
        >
          <AvatarMenu />
        </v-app-bar>
        <v-main class="content">
          <router-view />
        </v-main>
      </div>
    </div>
  </v-app>
</template>

<script>
import { role } from "@/helpers/role";
import LogoComponent from "@/components/LogoComponent";
import AvatarMenu from "@/components/AvatarMenu";
import { avatarMenu } from "@/helpers/avatarMenu";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "App",
  components: { AvatarMenu, LogoComponent },
  avatarMenu,
  data: () => ({
    items: [
      { title: "Home", icon: "mdi-home", link: "/" },
      { title: "Dashboard", icon: "mdi-view-dashboard", link: "/dashboard" },
      { title: "Logout", icon: "mdi-help-box", link: "/login" }
    ],
    right: null,
    role: JSON.parse(localStorage.getItem("currentUser")).role
  }),
  methods: {
    ...mapActions({
      getUserDetails: "userDetails"
    })
  },
  computed: {
    ...mapGetters({
      getUser: "currentUser"
    }),
    isAdmin() {
      return this.getUser.role === role.admin;
    }
  }
};
</script>

<style lang="scss" scoped>
.app-container {
  height: 100%;

  &__right-side {
    width: 100%;
  }
}

.content {
  height: calc(100% - 68px);
}

.logo-container {
  height: 68px !important;
}
</style>
