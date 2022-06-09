<template>
  <div class="register d-flex flex-column justify-center">
    <v-form ref="form" @submit.prevent="handleSubmit">
      <div class="text-md-h4 mb-8 text-sm-center">Create an account</div>
      <div class="d-flex justify-space-between align-start">
        <div class="d-flex flex-column columns">
          <v-text-field
              label="Email address"
              v-model="email"
              outlined
              :rules="$options.emailValidation"
          ></v-text-field>
          <v-text-field
              v-model="password"
              class="mt-4"
              label="Password"
              outlined
              :rules="$options.passwordValidation"
              type="password"
          ></v-text-field>
          <v-text-field
              v-model="confirmPassword"
              class="mt-4"
              label="Confirm password"
              outlined
              :rules="[
              ...$options.passwordValidation,
              $options.confirmPasswordValidation(password, confirmPassword),
            ]"
              type="password"
          ></v-text-field>
        </div>
        <div class="d-flex flex-column columns">
          <v-text-field
              v-model="firstName"
              label="First name"
              outlined
              :rules="[(firstName) => !!firstName || 'First name is required']"
          ></v-text-field>
          <v-text-field
              v-model="lastName"
              class="mt-4"
              label="Last name"
              outlined
              :rules="[(lastName) => !!lastName || 'Last name is required']"
          ></v-text-field>
          <v-text-field
              v-model="phoneNumber"
              class="mt-4"
              label="Phone number"
              :rules="[
              (phoneNumber) => !!phoneNumber || 'Phone number is required',
              (phoneNumber) =>
                /^[0-9]*$/.test(phoneNumber) || 'Only numbers allowed',
            ]"
              outlined
          ></v-text-field>
        </div>
      </div>

      <v-btn class="mt-8" color="primary" elevation="0" block type="submit"
      >Register
      </v-btn
      >
      <div class="mt-8 text-sm-center">
        Already have an account?
        <router-link
            to="/login"
            color="primary"
            class="text-h6 text-decoration-none"
        >Log in
        </router-link
        >
      </div>
    </v-form>
  </div>
</template>

<script>
import {
  confirmPasswordValidation,
  emailValidation,
  passwordValidation,
} from "@/helpers/validationRules";
import {mapActions} from "vuex";

export default {
  name: "RegisterPage",
  emailValidation,
  passwordValidation,
  confirmPasswordValidation,

  data() {
    return {
      email: null,
      password: null,
      confirmPassword: null,
      firstName: null,
      lastName: null,
      phoneNumber: null,
    };
  },
  methods: {
    ...mapActions({
      register: "register",
    }),
    async handleSubmit() {
      if (this.$refs.form.validate()) {
        try {
          this.isLoading = true;
          await this.register({
            email: this.email,
            password: this.password,
            firstName: this.firstName,
            lastName: this.lastName,
            phoneNumber: this.phoneNumber,
          });
          await this.$router.push("/");
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

<style scoped>
.register {
  margin: 0 auto;
  height: 100%;
  max-width: 40%;
}

.columns {
  width: 48%;
}
</style>
