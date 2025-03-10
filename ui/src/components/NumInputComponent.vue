<template>
  <input type="number">
</template>

<script>
export default {
  name: "NumInputComponent"
}
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