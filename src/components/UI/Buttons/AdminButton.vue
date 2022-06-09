<template>
  <v-tooltip bottom>
    <template v-slot:activator="{ on, attrs }">
      <v-btn @click="$emit('click')" icon small v-bind="attrs" v-on="on">
        <v-icon color="secondary lighten-2" dark v-bind="attrs" v-on="on">
          <slot></slot>
        </v-icon>
      </v-btn>
    </template>
    <span>{{ title }}</span>
  </v-tooltip>
</template>

<script>
export default {
  name: "AdminButton",
  props: {
    title: {
      type: String,
    },
  },
};
</script>

<style lang="scss" scoped></style>
