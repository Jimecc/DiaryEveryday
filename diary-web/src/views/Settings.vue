<template>
  <!-- 全局 -->
<div style="width: 100%;height: 100%;position: absolute;">
  <!-- Header 头部 -->
  <div style="width: 100%;height: 6%;display: flex;border-bottom: #d2d2d2 .1px solid;box-shadow: 0 5px 10px -5px #d0d0d0;">
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

  <div style="position: absolute;width: 30px;left: 50px">
    <el-button plain type="primary" icon="el-icon-arrow-down" size="small" class="button-main-menu-hidden" @click="show.main_menu = !show.main_menu"></el-button>
  </div>
  <!-- Body 身体 -->
  <div style="height: 94%;">
    <!-- Body 中的头部：按钮部分-->
    <div v-if="show.main_menu" style="width: 100%;height: 6%;display: flex;border-bottom: #b9b9b9 .1px solid;">
      <div style="width: 50%;height: 100%;margin-top: .5%">
<!--        <el-button plain type="primary" icon="el-icon-arrow-right" size="small" class="button-menu-hidden"></el-button>-->
      </div>
      <div style="width: 50%;height: 100%;margin-top: .5%;text-align: right">
        <el-button type="primary" icon="el-icon-plus" size="small" class="button-new-note">新建文章</el-button>
      </div>
    </div>
    <!-- Body 中的菜单与文章部分-->
    <div :style="show.main_menu?'box-shadow: 0 -5px 10px -5px #d0d0d0;':''" class="div-main">
      <div style="width: 20%;height: 100%;border-right: #999999 1px solid;">
        <!-- 菜单部分 -->
      </div>
      <div style="width: 80%;height: 100%;display: flex">
        <div shadow="hover" style="width: 100%;height: 100%">
          <div style="width: 100%;height: 9%;display: flex;border-bottom: #999999 .1px solid">
            <div style="width: 70%;height: 100%;margin: 20px 0 0 20px">
              <span style="font-size: 30px;">{{note.title}}</span>
              <a style="margin-left: 20px;color: #999999;">2022-12-21</a>
              <a style="margin-left: 20px;color: #999999;background-color: #cdffab;padding:7px 5px;border-radius: 20px;font-size:10px">最新</a>
              <a style="margin-left: 20px;color: #999999;background-color: #ffe69a;padding:7px 5px;border-radius: 20px;font-size:10px">最新编辑</a>
            </div>
            <div style="width: 30%;height: 100%;text-align: right;margin-top: 25px">
                <el-button type="text" class="buttons-4-note-operation" icon="el-icon-edit"></el-button>
                <el-button type="text" class="buttons-4-note-operation" icon="el-icon-picture-outline"></el-button>
                <el-button type="text" class="buttons-4-note-operation" icon="el-icon-full-screen"></el-button>
                <el-divider direction="vertical"></el-divider>
                <el-button type="text" class="buttons-4-note-operation" icon="el-icon-delete" style="color: #ff5e5e;margin-right: 40px"></el-button>
            </div>
          </div>
        </div>
<!--        <div style="width: 100%;height: 8%;display: flex">-->
<!--          <div style="display: flex;width: 50%;">-->
<!--            <h1 style="margin-left: 20px">title</h1>-->
<!--            <a style="margin-top: 40px;margin-left: 20px;color: #999999;">2022-12-21</a>-->
<!--            <a style="margin-top: 35px;margin-left: 20px;color: #999999;background-color: #cdffab;padding:7px 5px;border-radius: 20px;font-size:10px">最新</a>-->
<!--            <a style="margin-top: 35px;margin-left: 20px;color: #999999;background-color: #ffe69a;padding:7px 5px;border-radius: 20px;font-size:10px">最新编辑</a></div>-->
<!--          &lt;!&ndash; 文章按钮部分 &ndash;&gt;-->
<!--          <div style="text-align: right;height: 8%;width: 50%;">-->
<!--            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-edit"></el-button>-->
<!--            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-picture-outline"></el-button>-->
<!--            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-full-screen"></el-button>-->
<!--            <el-divider direction="vertical"></el-divider>-->
<!--            <el-button type="text" class="buttons-4-note-operation" icon="el-icon-delete" style="color: #ff5e5e;margin-right: 40px"></el-button>-->
<!--          </div>-->
<!--        </div>-->

<!--        &lt;!&ndash; 文章部分与图片部分 &ndash;&gt;-->
<!--        <div style="width: 100%;height: 92%;display: flex;background-color: coral;">-->
<!--          <div style="height: 100%;width: 100%;background-color: #ffd1d1;">-->
<!--            <el-skeleton v-if="this.diaryForm.article==''" />-->
<!--            <DiaryArticle :article="this.diaryForm.article"></DiaryArticle>-->
<!--          </div>-->
<!--          <div style="background-color: #ffc4c4;height: 100%;width: 30%">-->

<!--          </div>-->
<!--        </div>-->
      </div>
    </div>
  </div>
</div>
</template>

<script>
import DiaryArticle from "@/components/DiaryArticle.vue";
export default {
  name: "Settings",
  components:{
    DiaryArticle
  },
  data(){
    return{
      model:{
        note: true
      },
      note:{
        id:null,
        user_id:null,
        title:'文章标题',
        article:'12141243123412341234123',
        info:'',
        create_date:null,
        update_date:null,
        color:'',
        weather:'',
        score:0,
        collection:0,
        shit:0,
        delete_or_not:0
      },
      show:{
        main_menu:false
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
.button-main-menu-hidden{
  border-radius: 0 0 10px 10px;
}
.button-new-note{
  margin-right: 30px;
  margin-top: -10px;
}
.buttons-4-note-operation{
  font-size: 20px;
  margin-top: 10px;
}
.div-main{
  width: 100%;
  height: 94%;
  display: flex;
}
@keyframes button-main-menu-hidden {
  10%,
  90% {
    transform: translate3d(-1px, 0, 0);
  }
  20%,
  80% {
    transform: translate3d(2px, 0, 0);
  }
  30%,
  50%,
  70% {
    transform: translate3d(-4px, 0, 0);
  }
  40%,
  60% {
    transform: translate3d(4px, 0, 0);
  }
}
</style>
