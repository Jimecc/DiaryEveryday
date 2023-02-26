<template>
  <!-- 全局 -->
<div style="width: 100%;height: 100%;position: absolute;">
  <!-- Header 头部 -->
  <div style="width: 100%;height: 6%;background-color: #ffffff;display: flex;border-bottom: #d2d2d2 .1px solid;box-shadow: 0 5px 10px -5px #c5c5c5;">
    <div style="display: flex;margin-top: 10px;width: 50%;text-align: left">
      <img src="../assets/logo/post4.png" style="width: 35px;height: 30px;margin-left: 40px;">
      <el-input class="input-search" placeholder="请输入要搜索的内容"></el-input>
    </div>
    <div style="margin-top: 10px;width: 50%;">
      <div style="display: flex;float:right;">
        <el-button-group>
          <!-- 如果是node模式，那么node为true，显示node模式-->
          <el-button :plain="!model.note"  type="primary" size="small" :icon="model.note?'el-icon-d-arrow-right':''"   @click="model.note = !model.note"><span v-if="!model.note">待办事项</span></el-button>
          <el-button :plain="model.note"   type="primary" size="small" :icon="!model.note?'el-icon-d-arrow-left':''"   @click="model.note = !model.note"><span v-if="model.note" >Node模式</span></el-button>
        </el-button-group>
        <img src="http://192.168.199.100:8888/group1/M00/00/03/wKjHZGP6ZcaAQkW8AAGrxVybNPY083.JPG" style="width: 30px;height: 30px;margin:0 30px 0 20px;border-radius: 18px">
      </div>
    </div>
  </div>

  <!-- Body 身体 -->
  <div style="height: 94%;">
    <!-- Body 中的头部：按钮部分-->
    <div style="width: 100%;height: 5%;display: flex">
      <div style="width: 50%;height: 100%;margin-top: .5%">
        <el-button plain type="primary" icon="el-icon-arrow-right" size="small" class="button-menu-hidden"></el-button>
      </div>
      <div style="width: 50%;height: 100%;margin-top: .5%;text-align: right">
        <el-button plain type="primary" icon="el-icon-plus" size="small" class="button-new-note">新建文章</el-button>
      </div>
    </div>
    <!-- Body 中的菜单与文章部分-->
    <div style="width: 100%;height: 95%;display: flex">
      <div style="width: 20%;height: 100%;background-color: #ffb095;">
        <!-- 菜单部分 -->
      </div>
      <div style="width: 80%;height: 100%;background-color: #ffffff;">
        <!-- 文章按钮部分 -->
        <div style="text-align: right;height: 8%;width: 100%;border-bottom: #e8e8e8 .1px solid">
            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-edit"></el-button>
            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-picture-outline"></el-button>
            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-full-screen"></el-button>
            <el-divider direction="vertical"></el-divider>
            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-delete" style="color: #ff5e5e;margin-right: 40px"></el-button>
        </div>
        <!-- 文章部分与图片部分 -->
        <div style="width: 100%;height: 92%;background-color: #ffffff">

        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "Settings",
  data(){
    return{
      model:{
        note: true
      }
    }
  }
}
</script>

<style scoped>
.input-search:deep(.el-input__inner) {
  width: 200px;
  height: 30px;
  margin-left: 20px;
  border-radius: 3px 15px 15px 3px;
}
.el-button-group-5-buttons{
  background-color: blanchedalmond;
  border-radius: 10px;
  width: 50px;
}
.button-menu-hidden{
  border-radius: 0 10px 10px 0;
}
.button-new-note{
  margin-right: 30px;
}
.buttons-4-note-operation{
  font-size: 20px;
  margin-top: 10px;
}

</style>
