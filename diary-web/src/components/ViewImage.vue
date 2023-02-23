<template>
<!-- 查看图片 -->
  <div>
    <el-dialog width="60%" title="查看图片" :visible.sync="show.dialogViewImg">
      <div class="div-out-width" v-for="(image,index) in images">
        <div class="div-image-out">
          <img class="image-view" v-show="index == i" :src="image.url"/>
          <!--放图片信息与按钮的div-->
          <div v-show="index==i" style="width: 25%;padding:10px 0">

            <div style="width: 100%;margin-left: 5px">
              <div style="width: 100%;border-bottom: #999999 0.1px solid;padding-bottom: 3px;">
                <h1>{{image.rname?image.rname:image.name}}</h1>
              </div>
              <div style="width: 100%;border-bottom: #999999 0.1px solid;padding-bottom: 3px">
                <h4>创建时间</h4>
                <span>{{ image.create_date }}</span>
              </div>
              <div style="width: 100%;border-bottom: #999999 0.1px solid;padding-bottom: 3px">
                <h4>简介</h4>
                <span>{{image.info?image.info:"没有简介信息哦～"}}</span>
              </div>
            </div>

            <div slot="footer" v-show="index==i" class="button-flooter">
              <el-button @click="clicklast(image)" :disabled="i<=0" type="primary" style="border-radius: 20px 0 0 20px;" size="small" icon="el-icon-arrow-left">上一页</el-button>
              <el-button @click="clicknext(image)"  :disabled="i>=images.length -1" type="primary" style="border-radius: 0 20px 20px 0;" size="small">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
            </div>
            <!--        <el-button-group style="margin: 30px 7% 0 8%">-->
            <!--          -->
            <!--        </el-button-group>-->
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ViewImage"
}
</script>

<style scoped>

</style>
