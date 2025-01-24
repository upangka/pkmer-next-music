<template>
  <div class="indicator"></div>
</template>
<style lang="scss" scoped>
@use './variables.scss' as dropdown;
.indicator {
  position: absolute;
  width: 0;
  height: 0;
  top: -9px;
  left: 50%;
  margin-left: -10px;
  border-style: solid;
  border-width: 0px 10px 10px 10px;
  border-color: transparent transparent dropdown.$dropdown-background transparent;
}
</style>
