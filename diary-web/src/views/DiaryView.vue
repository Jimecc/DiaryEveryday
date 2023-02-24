<template>
  <div style="background-size: cover;height: 100%;">
    <el-container>

      <!------- Header部分 包含：搜索框、头像、头像弹窗----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
      <el-header class="aheader-1">
        <div style="width: 100%;display: flex;margin-top: 15px;margin-left: 1.5%;">
          <el-input  class="input-search" @keyup.enter.native="clickENTER" size="small" suffix-icon="el-icon-search" v-model="input_search" placeholder="看看那天的你在做什么？"></el-input>
          <!-- 下拉框 -->
          <el-dropdown>
            <!-- 小头像 -->
            <el-avatar style="border: #666666 .1px solid;margin-top: 1.5px;margin-left: 8px" size="small" src="http://192.168.199.100:8888/group1/M00/00/03/wKjHZGPw0VmAOeVVAAGrxVybNPY458.JPG"></el-avatar>
            <!-- 下拉框菜单 -->
            <el-dropdown-menu slot="dropdown" style="width: 180px;">
              <div style="width: 80%;left: 0;right: 0;margin: 15px auto 10px auto;box-shadow: 0 0 5px #999999;border-radius: 10px;">
                <div style="width: 100%;text-align: center;">
                  <el-avatar :size="60" style="box-shadow: 0 0 5px #999999;margin-top: 15px" src="http://192.168.199.100:8888/group1/M00/00/03/wKjHZGPw0VmAOeVVAAGrxVybNPY458.JPG"></el-avatar>
                </div>
                <div style="width: 100%;text-align: center;overflow-y: hidden;margin-top: 5px">
                  <span style="border-bottom: #cecece .1px solid;padding: 20px 10px 15px 10px;">Jimecc</span>
                </div>
                <div style="text-align: center;border-bottom: #cecece .1px solid;padding: 8px 10px;margin-bottom: 20px">
                  <el-button style="height: 34px;font-size:10px;margin-top: 8px;margin-bottom: 20px" type="info" plain >🏠 个人中心</el-button>
                </div>
              </div>
              <el-dropdown-item>
                <el-button @click="click_button_layout" type="text" size="small" style="color: #999999;width: 100%;text-align: left">设置</el-button>
              </el-dropdown-item>
              <el-dropdown-item click="click_button_layout">
                <el-button @click="click_button_layout" type="text" size="small" style="color: #999999;width: 100%;text-align: left">退出登录</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>

      <!------- 分割线 每日一记 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
      <el-divider>每日一记</el-divider>

      <!------- 按钮层 包含：五个按钮、新建文章----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
      <el-container class="aheader-2">
        <div class="header2-1">
          <el-button @click="initIndex" class="button1" size="small" icon="el-icon-milk-tea"></el-button>
          <el-button @click="click_show_date" class="button2" size="small" icon="el-icon-date"></el-button>
          <el-button @click="click_show_color" class="button2" size="small" icon="el-icon-price-tag"></el-button>
          <el-button @click="click_collection" class="button2" size="small" icon="el-icon-star-off"></el-button>
          <el-button @click="click_order" class="button2" size="small" :icon="icons.order"></el-button>
        </div>
        <div class="header2-2">
          <el-button type="primary" size="small" icon="el-icon-circle-plus-outline" class="button-header2-rignt-2" @click="goNewDiary">新建文章</el-button>
        </div>
      </el-container>

      <!------- 主体 包含：一个Index、一个显示文章、一个图片显示 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
      <el-container>
        <el-aside class="aside-out">
          <div v-if="show.show_date" class="div-side-search-date">
            <el-date-picker v-model="search_date"
                            type="date"
                            class="el-date-picker-1"
                            placeholder="选择日期">
            </el-date-picker>
            <el-button class="button-search-date" type="primary">查 找</el-button>
          </div>
          <div class="aside2-1">
            <div v-if="show.show_aside_title" class="aside-title">
              <h1>我的日记</h1>
            </div>
            <div v-if="!this.show.show_color" class="chose-color" style="height: 38px">
              <el-button size="small" @click="click_button_color('coral')" class="button-5-color" style="background-color: coral"></el-button>
              <el-button size="small" @click="click_button_color('greenyellow')"  class="button-5-color" style="background-color: greenyellow"></el-button>
              <el-button size="small" @click="click_button_color('violet')" class="button-5-color" style="background-color: violet"></el-button>
              <el-button size="small" @click="click_button_color('gray')"   class="button-5-color" style="background-color: gray"></el-button>
              <el-button size="small" @click="click_button_color('yellow')" class="button-5-color" style="background-color: yellow"></el-button>
            </div>
            <div v-if="this.show.show_color" class="chose-color" style="padding: 5px 0;">
              <el-rate v-model="score"></el-rate>
              <el-tooltip content="查找" placement="bottom" effect="light">
                <el-button size="small" type="primary" @click="click_rate" style="  border-radius: 8px;height: 15px;padding: 5px;margin: 0px 0 0 70%;background-color:#fff;width: 45px"></el-button>
              </el-tooltip>
            </div>

            <!------- 目录侧边栏部分 -------------->
            <div class="div2_4">
              <el-timeline :reverse="reverse">
                <el-timeline-item
                    v-for="(directory, index) in directories"
                    :key="index"
                    :color="directory.color">
                  <!--                  :timestamp="directory.create_date"-->
                  <div class="div2_5">
                    <el-button style="color: gray;margin-top: -10px" type="text" @click="clickDiaryName(directory.id)">
                      <span style="color: black">{{directory.title}}</span>
                    </el-button>
                    <el-button v-if="directory.collection==1" type="text" class="button_colletcion" icon="el-icon-star-on"></el-button>
                  </div>
                  <span style="color: grey;font-size: 13px">{{directory.create_date}}</span>
                  <el-rate
                      v-model="directory.score"
                      disabled
                      text-color="#ff9900">
                  </el-rate>
                </el-timeline-item>
              </el-timeline>
            </div>
          </div>
        </el-aside>

        <el-main class="main-out" style="display: flex">
          <el-card class="article-card">
            <div slot="header">
              <span class="article_title">{{diaryForm.title}}</span>
              <span class="article_date">{{diaryForm.create_date}}</span>
              <el-button :disabled="diaryForm.id == null" icon="el-icon-delete"           @click="click_delete_diary" style="font-size:20px;float: right; padding: 3px 0;margin-left: 10px" :style="this.diaryForm.id==null ? '' : 'color:red'" type="text"></el-button>
              <el-button :disabled="diaryForm.id == null" icon="el-icon-edit"             @click="click_button_editor" style="font-size:20px;float: right; padding: 3px 0" type="text"></el-button>
              <el-button :disabled="diaryForm.id == null" icon="el-icon-full-screen" style="font-size:20px;float: right; padding: 3px 0" type="text"></el-button>
              <el-button :disabled="diaryForm.id == null" icon="el-icon-picture-outline"  @click="click_show_img" style="font-size:20px;float: right; padding: 3px 0" type="text"></el-button>
            </div>
            <!------- 主体部分 文章显示 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="div-article">
              <div class="text-item">
                <!--                  {{this.diaryForm.article}}-->
                <span v-if="this.diaryForm.article=='' && this.diaryForm.title==''">[暂无内容]</span>
                <el-skeleton v-if="this.diaryForm.article==''" />
                <DiaryArticle :article="this.diaryForm.article"></DiaryArticle>
              </div>
            </div>
          </el-card>



          <div  v-if="this.show.show_img"  class="div-img-out" style="height:720px;">

            <div class="diary-div-pics">
              <div style="display: flow;width:40px;">
                <el-row>
                  <el-col  :span="32" v-for="(img, index) in images" :key="index" :offset="index > 0 ? 2 : 0">
                    <div :body-style="{ padding: '0px' }" style="overflow-x: hidden;width: 210px;height:auto;padding:10px;margin: 20px 30px 20px 30px;border-radius:15px;box-shadow: 0 0 5px #9a9a9a">
                      <img style="width:100%;height: auto;left:0;right:0;margin:auto;box-shadow: 0 0 5px #ffebcd" :src="img.url" class="drawer-s-images">
                      <div style="padding: 14px;">
                        <span style="width: 90%;padding: 0 10px 0 0;">{{ img.rname==null ? img.name : img.rname }}</span>
                        <div>
                          <time class="time">{{ img.create_date }}</time>
                        </div>
                        <div class="div-img-button-edit">
                          <el-button type="text" icon="el-icon-edit"   class="button-img-edit"></el-button>
                          <el-button type="text" icon="el-icon-view"   @click="click_view_image(index)"     class="button-img-edit"></el-button>
                          <el-button type="text" icon="el-icon-delete" @click="click_delete_image(img.id)"  class="button-img-delete"></el-button>
                        </div>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </div>

            </div>
            <div style="height: 40px;width: 100%">
              <el-upload
                  :http-request="handleUploadForm"
                  class="upload-demo"
                  multiple
                  :show-file-list="false">
                <el-button type="primary" style="width: 300%;margin: 5px 5% 0 5%">上传图片</el-button>
              </el-upload>
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
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
import DiaryArticle from "@/components/DiaryArticle.vue";
export default {
  name: "DiaryView",
  components:{
    DiaryArticle
  },
  data() {
    return {
      input_search: '',
      reverse: true,
      directories: [],
      score:null,
      search_date:new Date(),
      diaryForm:{
        id:null,
        manager_id:null,
        title:null,
        article:'',
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
      count: 0,
      icons:{
        order: 'el-icon-bottom', // 向下箭头
      },
      show:{
        dialogViewImg: false,
        show_color: false,
        show_aside_title:true,
        show_date: false,
        fullscreen: false,
        show_img: false, // 为了开发改成true，最后一定要改为false
        show_editor: false,
        show_img_flag: false
      },
      colors:{
        face:['#99A9BF', '#F7BA2A', '#FF9900']
      },
      max: 7,
      value:null,
      images:[],
      i:0
    }
  },
  mounted(){
    this.initIndex();
    this.getLatestNote();
  },
  methods:{
    getLatestNote(){
      this.getRequest('/note/latest').then(resp=>{
        this.diaryForm = resp.obj;
      })
    },
    clicklast(image){
      this.i --;
      this.image_proportion(image);
    },
    clicknext(image){
      this.i++;
      this.image_proportion(image);
    },
    click_view_image(index){
      this.show.dialogViewImg = true;
      this.i = index;
      console.log(index);
    },
    click_delete_image(index){
      this.deleteRequest('/file/delete/'+index).then(resp=>{
        if(resp.status == 200){
          this.getRequest('/index/file/'+this.diaryForm.id).then(resp=> {
            this.images = resp.obj;
          });
        }
      })
    },
    handleUploadForm(param){
      let formData = new FormData()
      formData.append('file', param.file)
      formData.append('note_id', this.diaryForm.id)
      this.postRequest('/file/upload', formData).then((res) => {
        if (res.status == 200) {
          this.$message.success('提交成功');
          this.getRequest('/index/file/'+this.diaryForm.id).then(resp=> {
            this.images = resp.obj;
          });
        } else {
          this.$message.error('提交失败')
        }
      })

    },

    click_button_layout(){
      this.getRequest('/auth/logout').then(resp=>{
        if (resp.status==200){
          localStorage.removeItem('tokenStr');
          localStorage.removeItem('user');
          this.$router.replace('/login');
        }
      })
    },
    click_delete_diary(){
      this.$confirm('此操作将删除该日记，删除后不可恢复，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        this.deleteRequest('/note/delete/'+this.diaryForm.id).then(resp=>{
          if(resp.status == 200){
            this.init_diary_form();
            this.initIndex();
          }else{
            this.$message.error('删除失败，可能您未选中文章，或已经提交过删除请求了！');
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      })

    },
    goNewDiary(){
      this.$router.push('/new')
    },
    initIndex(){
      // initIndex(){
      console.log("开始请求目录")
      this.getRequest('/index/note/all').then(resp=>{
        if(resp){
          this.directories = resp.obj;
        }
      })
    },

    click_order(){
      if(this.reverse == true){
        this.reverse = false;
        this.icons.order = 'el-icon-top';
      }else{
        this.reverse = true;
        this.icons.order = 'el-icon-bottom';
      }
    },

    click_show_color(){
      this.show.show_color = !this.show.show_color;
    },

    click_button_color(color){
      this.getRequest('/index/note/color/'+color).then(resp=>{
        if(resp) {
          this.directories = resp;
        }
      })
    },
    click_button_editor(){
      this.$router.push({
        path: '/new',
        query: {
          diary: this.diaryForm,
        }
      });
    },
    clickDiaryName(id){
      // 请求相册
      if(this.show.show_img){ // 如果相册是true，那么就请求
        this.getRequest('/index/file/'+id).then(resp=>{
          if(resp.status == 200) {
            this.images = resp.obj;
          }else{
            this.images = null;
          }
        })
      }

      // 请求文章
      this.getRequest('/note/article/'+id).then(resp=>{
        if(resp){
          this.diaryForm = resp.obj;
        }
      })
    },

    click_show_img(){
      if(!this.show.show_img){ // false 打开相册
        this.show.show_img= true;
        this.getRequest('/index/file/'+this.diaryForm.id).then(resp=>{
          if(resp.status == 200){
            this.images = resp.obj;
          }else{
            this.images = [];
          }
        })
      }else{ // true 关闭相册
        this.show.show_img = false;
        this.images = [];
      }
    },

    click_collection(){
      this.getRequest('/index/note/collection').then(resp=>{
        if(resp) {
          this.directories = resp;
        }
      })
    },

    click_rate(){
      this.getRequest('/index/note/score/'+this.score).then(resp=>{
        if(resp){
          this.directories = resp;
        }
      })
    },
    clickENTER(){
      this.getRequest('/index/search/'+this.input_search).then(resp=>{
        this.directories = resp.obj;
      })
    },
    click_show_date(){
      this.show.show_date = !this.show.show_date;
    },
    init_diary_form() {
      this.diaryForm.id = null;
      this.diaryForm.info = '';
      this.diaryForm.title = null;
      this.diaryForm.article = '';
      this.diaryForm.create_date = null;
      this.diaryForm.update_date = null;
      this.diaryForm.color = '';
      this.diaryForm.weather = '';
      this.diaryForm.score = null;
      this.diaryForm.collection = null;
      this.diaryForm.shit = null;
      this.diaryForm.delete = null;
      this.diaryForm.manager_id = null;
    }
  }
}
</script>

<style scoped>
.aside2-1{
  margin: 20px 0px 25px 15px;
  width: 90%;
  height: 90%;
  background-color: #fff;
  border-radius: 15px;
  padding: 10px 0;
  box-shadow: 0 0 10px #bebebe;
}
.div2_4{
  overflow:auto;
  text-overflow:ellipsis;
  height: 78%;
  width: 100%;
  margin-top: 60px;
  background-color: #f6f6f6;
  border-top: #ababab 1px solid;
  border-bottom: #ababab 1px solid;
  border-radius: 15px;
  padding: 15px 0;
}
.aheader-1{
  text-align: center;
  margin-top: 10px;
}
.aheader-2{
  margin-top: 5px;
}
.header2-1{
  width: 48%;
  margin-left: 20px;
  text-align: left;
  /*background-color: #999999;*/
}
.header2-2{
  width: 48%;
  margin-left: 20px;
  text-align: right;
  /*background-color: #42b983;*/
}
.div2_5{
  padding: 3px;
  /*background-color: #42b983;*/
  height: 30px;
  /*border-bottom: #999999 .5px solid;*/
}
.input-search{
  width: 95%;
  height: 15px;
  border: black;
  margin-right: 10px;
}
.button_search{
  width: 8%;
}
.button1{
  background-image: url('../assets/xxx.png');
  background-size: contain;
  border-radius: 10px;
  margin: 0 0px 0px 35px;
  width: 43px;
  color: #be9446;
}
.button2{
  background-image: url('../assets/xxx.png');
  background-size: contain;
  border-radius: 10px;
  margin: 0 0px 0px 15px;
  width: 43px;
  color:#be9446;
}
.button-header2-rignt-1{
  margin-right: 40px;
  border-radius: 10px;
}
.button-header2-rignt-2{
  border-radius: 10px;
  margin-right: 18px;
}
.button_xx{
  border-radius: 15px;
}
.input-search>>>.el-input__inner { /*或者 .s2>>>.el-input__inner  */
  border-radius: 20px; /*输入框圆角值*/
}

.aside-out{
  margin-top: 0px;
  margin-left: 30px;
  width: 400px;
  /*background-color: beige;*/
  height: 800px;
  border-radius: 20px;
}
.article_title{
  font-size: 25px;
  font-weight: 800;
}
.article_date{
  font-size: medium;
  font-weight: 400;
  color: gray;
  margin-left: 15px;
}
.button-5-color{
  border-radius: 8px;
  width: 15px;
  height: 15px;
  padding: 5px;
  margin: -10px 0 0 10px;
}
.button_colletcion{
  border-radius: 50%;
  width: 15px;
  height: 15px;
  padding: 5px;
  margin: -10px 0 0 -10px;
  font-size: 18px;
  color: red;
}
.chose-color{
  width: 100%;
  background-color: #f2f2f2;
  /*border: #999999 1px solid;*/
  height: 30px;
  margin-top: 10px;
  /*margin-left: 15px;*/
  border-radius: 7px;
  float: left;
}
.main-out{
  margin-top: -10px;
  margin-left: 0px;
  width: 100%;
  /*background-color: #333333;*/
}
.aside-title{
  text-align: center;
  width: 80%;
  /*background-color: #999999;*/
  margin-left: 20px;
}
.text-item{
  /*white-space: pre-line;*/
  white-space: pre-wrap;
  /*word-wrap:break-word;*/
  width: 100%;
  height: 100%;
  /*display: flex;*/
  /*background-color: #999999;*/
}
.el-divider--horizontal {
  display: block;
  height: 1px;
  /*width: 70%;*/
  /*margin: 24px 14%;*/
  width: 30%;
  margin: 20px 35% 10px 35%;
  background-color: orange;
  box-shadow: 0 0 5px orange;
}
.div-side-search-date{
  margin-top: 10px;
  background-color: #fff6ef;
  width: 265px;
  display: flex;
  margin-left: 20px;
  border-radius: 10px;
}
.button-search-date{
  height: 38px;
  width: 60px;
  border-radius: 10px;
  margin-top: 11px;
  margin-left: 15px;
  margin-bottom: 5px;
  padding: 10px;
}
.el-date-picker-1{
  margin-left: 15px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.el-date-picker-1[data-v-32487c3e] {
  width: 160px;
}
.div-img-in{
  position: relative;
  /*width: 95%;*/
  padding: 15px;
  height: 700px;
  margin: 0px 0 30px 0;
  /*border: grey 1px solid;*/
  /*border-radius: 5px;*/
}
.article-card{
  margin-top: 10px;
  margin-left: -20px;
  border-radius: 15px;
  width: 100%;
  /*background-color: #42b983;*/
  height: 96%;
}

::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;height: 0;
}

.diary-div-pics{
  overflow:auto;
  text-overflow:ellipsis;
  width: 95%;
  height: 650px;
  border-radius: 10px;
  margin: 5px 0 10px 5px;
  border-bottom: #666666 .1px solid;
  border-top: #666666 .1px solid;
  /*box-shadow: 0 0 5px #666666;*/
}
.div-img-out{
  width: 32%;
  /*border: black 1px solid;*/
  margin: 10px 0 0 10px;
  border-radius: 10px;
  height: 100%;
  overflow-x:auto;
  overflow-y: hidden;
  box-shadow: 0 0 5px #bebebe;
  padding:10px;
}

.div-img-button-edit{
  text-align: right;
}
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
  width: 60%;
  height: 0%;
}
.button-img-delete{
  font-size: 15px;
  color: red;
}
.div-article{
  overflow:auto;
  text-overflow:ellipsis;
  height: 620px;
  width: 100%;
}
</style>
