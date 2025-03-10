<template>
  <div class="fraction">
    <NumInputComponent />
    <div class="fraction--separator"></div>
    <NumInputComponent />
  </div>
</template>

<script>
import NumInputComponent from "@/components/NumInputComponent.vue";

export default {
  name: "FractionComponent",
  components: {NumInputComponent}
}
</script>

<style scoped>
.fraction {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.fraction--separator {
  height: 2px;
  background-color: white;
  width: 100%;
  margin: 10px 0;
  border-radius: 15px;
}

</style>