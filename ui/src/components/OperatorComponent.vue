<template>
  <div class="select">
    <div class="select--toggle" @click="toggleDropdown">
      <div id="select--display">
        <svg :xmlns="selectedSvg.xmlns" :width="selectedSvg.width" :height="selectedSvg.height" :viewBox="selectedSvg.viewBox" :fill="selectedSvg.fill" :stroke="selectedSvg.stroke" :stroke-width="selectedSvg.strokeWidth" :stroke-linecap="selectedSvg.strokeLinecap" :stroke-linejoin="selectedSvg.strokeLinejoin">
          <line v-for="line in selectedSvg.lines" :x1="line.x1" :y1="line.y1" :x2="line.x2" :y2="line.y2"></line>
        </svg>
      </div>
      <div :class="['select--arrow', { 'rotate': dropdownOpen }]">
        <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="#ffffff" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <path d="M12 5v13M5 12l7 7 7-7"/>
        </svg>
      </div>
    </div>
    <transition name="fade">
      <div class="select--options" v-if="dropdownOpen">
        <div class="select--option" v-for="option in options" :key="option.value" @click="selectOption(option.value)">
          <svg :xmlns="option.svg.xmlns" :width="option.svg.width" :height="option.svg.height" :viewBox="option.svg.viewBox" :fill="option.svg.fill" :stroke="option.svg.stroke" :stroke-width="option.svg.strokeWidth" :stroke-linecap="option.svg.strokeLinecap" :stroke-linejoin="option.svg.strokeLinejoin">
            <line v-for="line in option.svg.lines" :x1="line.x1" :y1="line.y1" :x2="line.x2" :y2="line.y2"></line>
          </svg>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "OperatorComponent",
  data() {
    return {
      dropdownOpen: false,
      selectedOption: "+",
      options: [
        { value: "+", svg: { xmlns: "http://www.w3.org/2000/svg", width: 25, height: 25, viewBox: "0 0 24 24", fill: "none", stroke: "#ffffff", strokeWidth: 2, strokeLinecap: "round", strokeLinejoin: "round", lines: [{ x1: 12, y1: 5, x2: 12, y2: 19 }, { x1: 5, y1: 12, x2: 19, y2: 12 }] } },
        { value: "-", svg: { xmlns: "http://www.w3.org/2000/svg", width: 25, height: 25, viewBox: "0 0 24 24", fill: "none", stroke: "#ffffff", strokeWidth: 2, strokeLinecap: "round", strokeLinejoin: "round", lines: [{ x1: 5, y1: 12, x2: 19, y2: 12 }] } },
        { value: "*", svg: { xmlns: "http://www.w3.org/2000/svg", width: 25, height: 25, viewBox: "0 0 24 24", fill: "none", stroke: "#ffffff", strokeWidth: 2, strokeLinecap: "round", strokeLinejoin: "round", lines: [{ x1: 18, y1: 6, x2: 6, y2: 18 }, { x1: 6, y1: 6, x2: 18, y2: 18 }] } },
        { value: "/", svg: { xmlns: "http://www.w3.org/2000/svg", width: 25, height: 25, viewBox: "0 0 24 24", fill: "none", stroke: "#ffffff", strokeWidth: 2, strokeLinecap: "round", strokeLinejoin: "round", lines: [{ x1: 7, y1: 20, x2: 17, y2: 4 }] } }
      ]
    };
  },
  computed: {
    selectedSvg() {
      return this.options.find(option => option.value === this.selectedOption).svg;
    }
  },
  methods: {
    toggleDropdown() {
      this.dropdownOpen = !this.dropdownOpen;
    },
    selectOption(value) {
      this.selectedOption = value;
      this.dropdownOpen = false;
      this.$emit('operator-change', value);
    }
  }
}
</script>

<style scoped>
.select {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.select--toggle {
  width: 60px;
  height: 40px;
  border: 2px solid grey;
  border-radius: 8px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.3s ease-in-out;
}

.select--toggle:hover {
  border-color: white;
  box-shadow: 0 0 8px white;
}

.select--arrow {
  position: relative;
  left: 0;
  margin-top: 8px;
  transition: all 0.3s ease-in-out;
  margin-left: 4px;
}

.select--arrow.rotate {
  transform: rotate(180deg);
  margin-top: -8px;
}

.select--options {
  position: absolute;
  top: 35px;
  left: 0;
  background-color: black;
  border: 2px solid white;
  box-shadow: 0 0 10px white;
  border-radius: 8px;
  overflow: hidden;
  transition: max-height 0.3s ease-in-out;
  max-height: 200px;
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

#select--display {
  margin-top: 8px;
}

.select--option {
  padding: 5px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

.select--option:hover {
  background-color: #555;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>