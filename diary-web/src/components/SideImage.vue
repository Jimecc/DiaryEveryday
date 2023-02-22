<template>
  <div>
    <el-card v-for="(o, index) in 5" :key="o" :offset="index > 0 ? 2 : 0" class="el-card-img" :body-style="{ padding: '0px' }">
      <div class="div-img">
        <img src="https://media.istockphoto.com/id/1136437406/photo/san-francisco-skyline-with-oakland-bay-bridge-at-sunset-california-usa.jpg?s=612x612&w=0&k=20&c=JVBBZT2uquZbfY0njYHv8vkLfatoM4COJc-lX5QKYpE=" class="image">
      </div>
      <div style="padding: 14px;">
        <span>好吃的汉堡</span>
        <div class="bottom clearfix">
          <time class="time">{{ 2022-12-12 }}</time>
          <el-button type="text" class="button">操作按钮</el-button>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "SideImage"
}
</script>

<style scoped>
.el-card-img{
  width: 90%;
  margin: 15px 0 0 15px;
}
.image {
  border: #333333 .5px solid;
  border-radius: 5px 10px 0 0;
}
.div-img{
  width: 100%;
  padding: 5px;
  /*padding: 10px;*/
  display: block;
}
</style>
