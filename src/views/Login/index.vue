<template>
  <div class="login d-flex flex-column justify-center">
    <v-form ref="form" lazy-validation @submit.prevent="handleSubmit">
      <div class="text-md-h4 mb-8 text-sm-center">Welcome to Ehealth</div>
      <v-text-field
        v-model="email"
        label="Email address"
        outlined
        :rules="$options.emailValidation"
      />
      <v-text-field
        v-model="password"
        class="mt-4"
        label="Password"
        outlined
        :rules="$options.passwordValidation"
        type="password"
      />
      <v-btn
        class="mt-8"
        color="primary"
        elevation="0"
        block
        type="submit"
        :loading="isLoading"
        >Log in</v-btn
      >
      <div class="mt-8 text-sm-center">
        Don't have an account yet?
        <router-link
          to="/register"
          color="primary"
          class="text-h6 text-decoration-none"
          >Register
        </router-link>
      </div>
    </v-form>
  </div>
</template>

<script>
import { emailValidation, passwordValidation } from "@/helpers/validationRules";
import { mapActions } from "vuex";

export default {
  name: "LoginPage",
  emailValidation,
  passwordValidation,
  data() {
    return {
      email: null,
      password: null,
      isLoading: false,
    };
  },
  methods: {
    ...mapActions({
      login: "login",
    }),
    async handleSubmit() {
      if (this.$refs.form.validate()) {
        try {
          this.isLoading = true;
          await this.login({ email: this.email, password: this.password });
          await this.$router.push("/home");
        } catch (error) {
          console.log(error);
        } finally {
          this.isLoading = false;
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.login {
  margin: 0 auto;
  height: 100%;
  max-width: 25%;
}
</style>
