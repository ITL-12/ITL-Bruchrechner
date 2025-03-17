<template>
  <input type="number" @input="onInput">
</template>

<script>
export default {
  name: "NumInputComponent",
  methods: {
    onInput(event) {
      this.$emit('input-change', event.target.value);
    }
  }
};
</script>

<style scoped>
input {
  all: unset;
  width: 30px;
  height: 30px;
  text-align: center;
  font-size: 25px;
  border: 2px solid grey;
  cursor: text;
  padding: 5px;
  border-radius: 8px;
  transition: all 0.3s ease-in-out;
}

input:focus {
  border-color: white;
  box-shadow: 0 0 8px white;
}
</style>