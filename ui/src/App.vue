<template>
  <h1>Bruchrechner</h1>
  <div class="fraction--wrapper">
    <div id="frac-1" class="fraction--container">
      <FractionComponent @fraction-change="onFractionChange(1, $event)" />
    </div>
    <div class="fraction--spacer"></div>
    <div id="frac-op" class="fraction--operator">
      <OperatorComponent @operator-change="onOperatorChange" />
    </div>
    <div class="fraction--spacer"></div>
    <div id="frac-2" class="fraction--container">
      <FractionComponent @fraction-change="onFractionChange(2, $event)" />
    </div>
    <div class="fraction--display">
      <div class="fraction--display-equals">
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="25" viewBox="0 0 24 24" fill="none" stroke="#ffffff"
             stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <line x1="5" y1="12" x2="19" y2="12"></line>
        </svg>
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="25" viewBox="0 0 24 24" fill="none" stroke="#ffffff"
             stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <line x1="5" y1="12" x2="19" y2="12"></line>
        </svg>
      </div>
      <div class="fraction--display-result">
        <span class="fraction--display-num">{{ result.num }}</span>
        <div class="fraction--separator"></div>
        <span class="fraction--display-den">{{ result.den }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import FractionComponent from "@/components/FractionComponent.vue";
import OperatorComponent from "@/components/OperatorComponent.vue";

const fraction1 = ref({ num: 0, dec: 1 });
const fraction2 = ref({ num: 0, dec: 1 });
const operator = ref('+');
const result = ref({ num: 0, den: 1 });

const onFractionChange = (fractionNumber, { type, value }) => {
  if (fractionNumber === 1) {
    fraction1.value[type] = value;
  } else {
    fraction2.value[type] = value;
  }
  if (allValuesSet()) {
    calculateResult();
  }
};

const onOperatorChange = (value) => {
  operator.value = value;
  if (allValuesSet()) {
    calculateResult();
  }
};

const allValuesSet = () => {
  return fraction1.value.num && fraction1.value.dec && fraction2.value.num && fraction2.value.dec && operator.value;
};

const calculateResult = async () => {
  const response = await fetch(`http://localhost:24165/calculate?num1=${fraction1.value.num}&den1=${fraction1.value.dec}&num2=${fraction2.value.num}&den2=${fraction2.value.dec}&op=${operator.value}`);
  const data = await response.text();
  const [num, den] = data.split(',');
  result.value = { num: parseInt(num), den: parseInt(den) };
};
</script>

<style>
.fraction--wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
}

.fraction--spacer {
  width: 20px;
}

.fraction--separator {
  height: 2px;
  background-color: white;
  width: 100%;
  margin: 10px 0;
  border-radius: 15px;
}

.fraction--display {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: row;
  font-size: 30px;
  color: white;
}

.fraction--display-equals {
  display: flex;
  flex-direction: column;
}

.fraction--display-equals svg {
  margin: -8px 10px;
}

.fraction--display-result {
  width: 30px;
  text-align: center;
}
</style>