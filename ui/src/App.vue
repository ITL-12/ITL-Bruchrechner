<template>
  <h1>Bruchrechner</h1>
  <div class="fraction--wrapper">
    <div class="fraction--container">
      <FractionComponent />
    </div>
    <div class="fraction--spacer"></div>
    <div class="fraction--operator">
      <OperatorComponent />
    </div>
    <div class="fraction--spacer"></div>
    <div class="fraction--container">
      <FractionComponent />
    </div>
    <div class="fraction--display">

    </div>
  </div>
</template>

<script setup>
import FractionComponent from "@/components/FractionComponent.vue";
import OperatorComponent from "@/components/OperatorComponent.vue";
</script>

<style scoped>
.fraction--wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
}

.fraction--spacer {
  width: 20px;
}
</style>
