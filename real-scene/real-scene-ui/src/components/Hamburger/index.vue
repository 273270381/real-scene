<template>
  <div  @click="toggleClick">
    <svg
      :class="{'is-active':isActive}"
      class="hamburger"
      viewBox="0 0 1024 1024"
      xmlns="http://www.w3.org/2000/svg"
      width="64"
      height="64"
    >
      <path fill="#7F7F7F" d="M 200 0 L 200 1200 L 800 600 Z"/>
    </svg>
  </div>
</template>

<script>
export default {
  name: 'Hamburger',
  props: {
    isActive: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    toggleClick() {
      this.$emit('toggleClick')
    }
  }
}
</script>

<style scoped>
.hamburger {
  cursor: pointer;
  display: inline-block;
  vertical-align: middle;
  width: 10px;
  height: 20px;
}

.hamburger.is-active {
  transform: rotate(180deg);
}

.hamburger:hover{
  background: #F0F2F5;
}
</style>
