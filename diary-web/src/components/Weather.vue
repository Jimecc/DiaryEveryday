<template>
  <div>
    <el-tooltip content="晴天" placement="top" effect="light">
      <el-button plain type="primary" class="button_wheather" icon="el-icon-sunny"></el-button>
    </el-tooltip>
    <el-tooltip content="多云" placement="top" effect="light">
      <el-button plain type="primary" class="button_wheather" icon="el-icon-cloudy"></el-button>
    </el-tooltip>
    <el-tooltip content="多云转晴" placement="top" effect="light">
      <el-button plain type="primary" class="button_wheather" icon="el-icon-cloudy-and-sunny"></el-button>
    </el-tooltip>
    <el-tooltip content="下雨啦" placement="top" effect="light">
      <el-button plain type="primary" class="button_wheather" icon="el-icon-heavy-rain"></el-button>
    </el-tooltip>
    <el-tooltip content="打雷啦" placement="top" effect="light">
      <el-button plain type="primary" class="button_wheather" icon="el-icon-lightning"></el-button>
    </el-tooltip>
    <el-tooltip content="下雪啦" placement="top" effect="light">
      <el-button plain type="primary" class="button_wheather" icon="el-icon-light-rain"></el-button>
    </el-tooltip>
  </div>
</template>

<script>
export default {
  name: "Weather",
  data: function(){
    return {
      count: 1
    }
  }
}
</script>

<style scoped>
.button_wheather{
  border-radius: 50%;
  width: 27px;
  height: 27px;
  padding: 5px;
}
</style>
