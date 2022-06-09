<template>
  <v-card max-width="280" class="pa-4">
    <div class="d-flex justify-end mb-2">
      <div class="d-flex justify-space-between" style="width: 70px">
        <AdminButton title="Edit" @click="handleEdit"> mdi-pencil </AdminButton>
        <AdminButton title="Delete" @click="handleDelete">
          mdi-delete
        </AdminButton>
      </div>
    </div>
    <v-divider />
    <div class="d-flex justify-space-between mt-2">
      <div>
        <p class="text-md-subtitle-1 mb-1">
          First name: {{ doctor.firstName }}
        </p>
        <p class="text-md-subtitle-1 mb-1">Last name: {{ doctor.lastName }}</p>
      </div>
      <v-avatar color="grey" size="60">{{
        `${doctor.firstName[0]}${doctor.lastName[0]}`
      }}</v-avatar>
    </div>
    <v-divider />
    <p class="text-md-subtitle-2 d-flex mb-1 mt-2">
      <span>Phone number:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.phoneNumber }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1">
      <span>Speciality:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.speciality }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1">
      <span>Price:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.price }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1">
      <span>Grade:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.grade }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1">
      <span>Experience:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.experience }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1">
      <span>Classification:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.classification }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1">
      <span>Rating:</span><span class="flex-grow-1 card-spaces"></span
      ><span class="card-style">{{ doctor.rating }}</span>
    </p>
    <p class="text-md-subtitle-2 d-flex mb-1"><span>Hospitals:</span></p>
    <ul class="text-md-subtitle-2">
      <li
        class="card-style"
        v-for="(hospital, index) in doctor.hospitals"
        :key="index"
      >
        {{ hospital }}
      </li>
    </ul>
  </v-card>
</template>

<script>
import AdminButton from "@/components/UI/Buttons/AdminButton";
import { mapActions } from "vuex";

export default {
  name: "DoctorCard",
  components: { AdminButton },
  props: {
    doctor: {
      type: Object,
    },
  },
  methods: {
    ...mapActions({
      deleteDoctor: "deleteDoctor",
      getDoctors: "getDoctors",
    }),
    handleEdit() {
      //Edit
      console.log("Edit");
    },
    async handleDelete() {
      await this.deleteDoctor(this.doctor.email);
      await this.getDoctors(1);
    },
  },
};
</script>

<style lang="scss" scoped>
.card-spaces {
  height: 15px;
  border-bottom: 2px dotted lightgray;
  margin: 0 4px 0 4px;
}

.card-style {
  font-weight: 400;
}
</style>
