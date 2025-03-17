<template>
  <div class="fraction">
    <NumInputComponent id="num" @input-change="onInputChange('num', $event)" />
    <div class="fraction--separator"></div>
    <NumInputComponent id="dec" @input-change="onInputChange('dec', $event)" />
  </div>
</template>

<script>
import NumInputComponent from "@/components/NumInputComponent.vue";

export default {
  name: "FractionComponent",
  components: { NumInputComponent },
  methods: {
    onInputChange(type, value) {
      this.$emit('fraction-change', { type, value });
    }
  }
};
</script>

<style scoped>
.fraction {
  display: flex;
  align-items: center;
  flex-direction: column;
}
</style>