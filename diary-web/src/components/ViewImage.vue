<template>
  <el-dialog title="查看图片" :visible.sync="this.show">
    <!--    <el-dialog width="60%" title="查看图片" visible="this.dialogViewImg">-->
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
</template>

<script>
import {h} from "vue";

export default {
  name: "ViewImage",
  props:{
    images:[],
    show: true
  },
  computed:{
    v_model_img_name(){
      var rname =this.images[this.i].rname;
      return rname?rname:this.images[this.i].name
    },
    v_model_img_info(){
      var info =this.images[this.i].info;
      return info?info:"没有简介信息哦~"
    }
  },
  data(){
    return{
      i: 0,
      info:'',
      image:{
        id: 0,
        name: '',
        rname:'',
        url:'',
        info:'',
        user_id:0,
        diary_id:0,
        create_date:null,
        image_status: true
      }
    }
  },
  mounted() {
    this.mount_first_image();
  },
  methods:{
    mount_first_image(){
      this.image = this.images[0]
      this.image_proportion(this.image);
      console.log(JSON.stringify(this.image));
    },
    image_proportion(i){
      let imgSrc= i.url;
      let img = new Image();
      img.src= imgSrc;
      console.log(img.width + ':' +img.height+'-----'+i.url);
      if(img.width > img.height){
        this.image.image_status = true; // 横向
      }else{
        this.image.image_status = false; // 纵向
      }
    },
    clicklast(image){
      this.i --;
      this.image_proportion(image);
    },
    clicknext(image){
      this.i++;
      this.image_proportion(image);
    }
  }
}
</script>

<style scoped>
/*横向的图片*/
.div-image-out{
  display: flex;
  width:100%;
}
.div-out-width{
  width:100%;
  /*background-color: #42b983;*/
}
.button-flooter{
  margin: 20px 0 0 0 ;
  text-align: right;
}
.input-img-name>>>.el-input__inner {
  border: 0;
  color:red;
  font-size: 24px;
  font-weight: 1000;
}

.input-img-name>>>.el-textarea__inner {
  border: 0;
  color: #ff9900;
  resize: none;
  font-size: 24px;
  font-weight: 1000;
  padding: 0;

}
/* 如果你的 el-input type 设置成textarea ，就要用这个了 */
.input-img-info>>>.el-textarea__inner {
  border: 0;
  color: #868686;
  padding: 0;
}
.el-dialog__body {
  padding: 30px 20px;
  color: #606266;
  font-size: 14px;
  word-break: break-all;
}
.image-view{
  border: #999999 0.1px solid;
  padding:10px;
  width: 75%;
  height: 0%;
}
</style>
