import Vue from "vue";
import Vuex from "vuex";
import { signIn } from "@/api/login";
import { signUp } from "@/api/register";
import { fetchDoctors } from "@/api/getDoctors";
import { deleteDoctor } from "@/api/deleteDoctor";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentUser: JSON.parse(localStorage.getItem("currentUser")),
    doctorList: [],
  },
  mutations: {
    ["setUser"](state, payload) {
      state.currentUser = payload;
    },
    ["setDoctorList"](state, payload) {
      state.doctorList = payload;
    },
  },
  actions: {
    async ["login"]({ commit }, { email, password }) {
      const currentUser = await signIn({ email, password });
      if (currentUser) {
        localStorage.setItem("currentUser", JSON.stringify(currentUser));
        commit("setUser", currentUser);
      }
    },
    async ["register"](
      { commit },
      { email, password, firstName, lastName, phoneNumber }
    ) {
      const currentUser = await signUp({
        email,
        password,
        firstName,
        lastName,
        phoneNumber,
      });

      localStorage.setItem("currentUser", JSON.stringify(currentUser));
      commit("setUser", currentUser);
    },
    async ["getDoctors"]({ commit }, payload) {
      const allDoctors = await fetchDoctors({ page: payload });
      commit("setDoctorList", allDoctors.list);
      return allDoctors;
    },
    ["deleteDoctor"](context, payload) {
      deleteDoctor(payload);
    },
  },
  getters: {
    ["currentUser"](store) {
      return store.currentUser;
    },
    ["getDoctorList"](store) {
      return store.doctorList;
    },
  },
  modules: {},
});
