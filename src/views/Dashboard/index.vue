<template>
  <div class="pa-16">
    <v-row>
      <v-col
          v-for="(doctor, index) in doctorList"
          :key="index"
          cols="12"
          sm="6"
          md="4"
          lg="3"
      >
        <DoctorCard :doctor="doctor"/>
      </v-col>
    </v-row>
    <div class="text-center">
      <v-pagination
          class="mt-8"
          v-model="page"
          :length="totalPages"
          circle
          @next="handleNext"
          @previous="handlePrevious"
          @input="handlePage"
      ></v-pagination>
    </div>
  </div>
</template>

<script>
import DoctorCard from "@/views/Dashboard/components/DoctorCard";
import {mapActions, mapGetters} from "vuex";

export default {
  name: "DashboardPage",
  components: {DoctorCard},
  data() {
    return {
      page: 1,
      totalPages: 4,
    };
  },
  computed: {
    ...mapGetters({
      doctorList: "getDoctorList",
    }),
  },
  methods: {
    ...mapActions({
      fetchDoctors: "getDoctors",
    }),
    async handleNext() {
      await this.fetchDoctors(this.page);
    },
    async handlePrevious() {
      await this.fetchDoctors(this.page);
    },
    async handlePage() {
      await this.fetchDoctors(this.page);
    },
  },
  async mounted() {
    const doctors = await this.fetchDoctors(this.page);

    this.totalPages = doctors.totalPages;
  },
};
</script>

<style scoped></style>
