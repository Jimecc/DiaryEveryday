<template>
  <div class="div_1">
    <el-container>
      <el-header>
        <el-page-header style="margin-top: 25px" content="编辑日记" @back="goBack"></el-page-header>
      </el-header>
      <el-divider>每日一记</el-divider>
      <el-container>
        <el-aside class="class-aside-out">
          <el-input v-model="articleForm.title" minlength="1" class="input-title" placeholder="请输入文章标题"></el-input>
          <div style="margin-top: 15px;">
            <el-tooltip content="选择日期(默认今天)" placement="bottom" effect="light">
              <el-button @click="click_show_date" :style="istyle.date" class="button_4" size="small" :icon="icons.date"></el-button>
            </el-tooltip>
            <el-tooltip content="天气" placement="bottom" effect="light">
              <el-button @click="click_show_weather" :style="istyle.weather" class="button_4" size="small"  :icon="icons.weather"></el-button>
            </el-tooltip>
            <el-tooltip content="今天心情太好啦！收藏一下吧！" placement="bottom" effect="light">
              <el-button @click="click_collect" :style="istyle.collection" class="button_4" size="small" :icon="icons.collection"></el-button>
            </el-tooltip>
            <el-tooltip content="今天心情不太行...放到厕所当厕纸吧（沉底）" placement="bottom" effect="light">
              <el-button @click="click_shit" :style="istyle.shit" class="button_4" size="small" icon="el-icon-toilet-paper"></el-button>
            </el-tooltip>
          </div>

          <!--天气-->
          <div v-if="show_weather" class="div_weather">
            <el-tooltip content="晴天" placement="top" effect="light">
              <el-button @click="click_weather('sunny')" plain type="primary" class="button_weather" icon="el-icon-sunny"></el-button>
            </el-tooltip>
            <el-tooltip content="多云" placement="top" effect="light">
              <el-button @click="click_weather('cloudy')" plain type="primary" class="button_weather" icon="el-icon-cloudy"></el-button>
            </el-tooltip>
            <el-tooltip content="多云转晴" placement="top" effect="light">
              <el-button @click="click_weather('cloudy_sunny')" plain type="primary" class="button_weather" icon="el-icon-cloudy-and-sunny"></el-button>
            </el-tooltip>
            <el-tooltip content="下雨啦" placement="top" effect="light">
              <el-button @click="click_weather('rain')" plain type="primary" class="button_weather" icon="el-icon-heavy-rain"></el-button>
            </el-tooltip>
            <el-tooltip content="打雷啦" placement="top" effect="light">
              <el-button @click="click_weather('light')" plain type="primary" class="button_weather" icon="el-icon-lightning"></el-button>
            </el-tooltip>
            <el-tooltip content="下雪啦" placement="top" effect="light">
              <el-button @click="click_weather('snow')" plain type="primary" class="button_weather" icon="el-icon-light-rain"></el-button>
            </el-tooltip>
          </div>
          <div style="margin-top: 15px">
            <el-button size="small" @click="color_orange" :style="istyle.colors.oragne" class="button_color"></el-button>
            <el-button size="small" @click="color_green"  :style="istyle.colors.green"  class="button_color"></el-button>
            <el-button size="small" @click="color_purple" :style="istyle.colors.purple" class="button_color"></el-button>
            <el-button size="small" @click="color_gray"   :style="istyle.colors.gray"   class="button_color"></el-button>
            <el-button size="small" @click="color_yellow" :style="istyle.colors.yellow" class="button_color"></el-button>
            <el-button size="small" @click="color_pure" :style="istyle.colors.pure"   class="button_color"></el-button>
          </div>
          <el-divider content-position="left">给今天打个分吧</el-divider>
          <div class="block">
            <el-rate
                v-model="articleForm.score">
            </el-rate>
          </div>
          <el-divider></el-divider>
          <div v-if="show_date">
            <el-date-picker
                v-model="articleForm.create_date"
                type="date"
                placeholder="选择日期">
            </el-date-picker>
            <el-divider></el-divider>
          </div>
          <div>
            <el-button @click="click_submit" type="primary" class="button-submit">提交</el-button>
            <div style="display: flex;margin-top: 10px">
              <el-button @click="click_out_without_save" type="danger" plain class="button-submit-nosave">不保存退出</el-button>
              <el-button @click="click_out_and_save" type="info" plain class="button-submit-save">保存并退出</el-button>
            </div>
          </div>
        </el-aside>
        <el-divider direction="vertical"></el-divider>
        <el-main>
          <div style="width: 100%;display: flex">
            <div style="width: 80%">
              <el-button size="small" type="text" icon="el-icon-picture-outline">插入图片</el-button>

              <el-upload
                  :http-request="handleUploadForm"
                  class="upload-demo"
                  multiple
                  :show-file-list="false"
              >
                  <el-button size="small" type="text" icon="el-icon-folder-opened">新建相关相册</el-button>
              </el-upload>

              <el-button size="small" type="text" icon="el-icon-box" @click="drawer = true">附件管理</el-button>
              <el-drawer
                  class="el_drawer_pics"
                  title="附件管理"
                  size="60%"
                  :visible.sync="drawer"
                  :with-header="true">
                <FilesManagement/>

                <!--            <router-view/>-->
              </el-drawer>
            </div>
            <div style="text-align: right;width: 18%">
              <el-button size="small" @click="click_show_pic_button" type="text" icon="el-icon-picture-outline">显示相册</el-button>
            </div>
          </div>
          <div style="display: flex">
            <el-input
                type="textarea"
                :autosize="{minRows:25,maxRows:30}"
                placeholder="请输入内容"
                v-model="articleForm.article"
                show-word-limit="true"
                clearable="true"
                suffix-icon="el-icon-tickets"
            >
            </el-input>
            <div v-if="show.show_pic_div" class="div-pics">
              123
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>
<script>
export default {
  name: "EditDiaryView",
  props:{
    diary_id: String
  },
  data() {
    return {
      articleForm: {
        id: null,
        user_id:null,
        title: '',
        create_date: new Date(),
        article: '',
        info: '',
        color: '',
        weather: '',
        score: 0,
        collection: 0,
        shit: 0
      },
      icons:{
        collection: 'el-icon-star-off',
        date: 'el-icon-date',
        shit: 'el-icon-help',
        weather: 'el-icon-cloudy-and-sunny'
      },
      istyle:{
        collection: 'margin-left: 10px;width:50px;height:50px',
        date: 'margin-left:0px',
        shit: '',
        weather: '',
        colors:{
          oragne: 'background-color: coral',
          green: 'background-color: greenyellow',
          purple: 'background-color: violet',
          gray: 'background-color: gray',
          yellow: 'background-color: yellow',
          pure: 'background-color: #fff'
        }
      },
      show:{
        show_pic_div: false
      },
      value1: null,
      value2: null,
      value3: new Date(),
      show_date: false,
      show_weather: false,
      drawer: false,
      article: ''
    }
  },
  mounted() {
    this.mounted_edit_diary();
  },
  methods:{
    handleUploadForm(param){
      let formData = new FormData()
      // 在formData中加入我们需要的参数
      formData.append('file', param.file)
      formData.append('note_id', 1)
      alert("xxx1")
      // 向后端发送数据
      this.postRequest('/file/upload', formData).then((resp) => {
        alert("xxx1")
        if (resp.status === 200) {
          this.$message.success('修改信息成功')
        } else {
          this.$message.success('修改信息失败')
        }
      })
    },
    click_out_without_save(){
      this.$confirm('检测到未保存的内容，是否在离开页面前保存修改？', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '保存',
        cancelButtonText: '放弃修改'
      })
          .then(() => {
            this.$message({
              type: 'info',
              message: '保存修改'
            });
            this.$store.commit('show_editor_page_false');
          })

          .catch(action => {
            this.$message({
              type: 'info',
              message: action === 'cancel'
                  ? '放弃保存并离开页面'
                  : '停留在当前页面'
            })
            this.$store.commit('show_editor_page_false');
          });

    },
    click_show_pic_button(){
      this.show.show_pic_div = !this.show.show_pic_div;
    },
    mounted_edit_diary(){
      if(this.diary_id != ''){
        console.log("xxxxxxxx查询了编辑文章")
        this.getRequest('/note/article/'+this.$route.query.did).then(resp=>{
          if (resp){
            // alert(JSON.stringify(resp.data.obj));
            this.articleForm = resp.obj;
          }
        })
      }
    },
    // 点击了日期按钮
    click_show_date(){
      if(this.show_date ==true){
        this.show_date = false;
        this.icons.date = 'el-icon-date';
        this.istyle.date = 'margin-left:0px';
      }else{
        this.icons.date = 'el-icon-loading';
        this.istyle.date = 'margin-left:0px;background-color:#cfe6ff;border:#409EFF 1px solid;color:#ff7f50'
        this.show_date = true;
      }
    },
    // 点击了天气按钮
    click_show_weather(){
      if(this.show_weather == true){
        this.show_weather = false;
        this.icons.weather = 'el-icon-cloudy-and-sunny';
        this.istyle.weather = '';
      }else{
        this.show_weather = true;
        this.icons.weather = 'el-icon-sunny';
        this.istyle.weather = 'background-color:#cfe6ff;border:#409EFF 1px solid;color:#ff7f50';
      }
    },
    // 点击了收藏按钮
    click_collect(){
      this.articleForm.shit = 0;
      this.istyle.shit = '';
      if(this.articleForm.collection == 1){
        this.articleForm.collection = 0;
        this.icons.collection = 'el-icon-star-off';
        this.istyle.collection = '';
      }else{
        this.articleForm.collection = 1;
        this.icons.collection = 'el-icon-star-on';
        this.istyle.collection = 'background-color:#cfe6ff;border:#409EFF 1px solid;color:#ff7f50';
      }
    },
    click_shit(){
      this.articleForm.collection = 0;
      this.istyle.collection = '';
      this.icons.collection = 'el-icon-star-off';
      if(this.articleForm.shit == 1){
        this.articleForm.shit = 0;
        this.istyle.shit = '';
      }else{
        this.articleForm.shit = 1;
        this.istyle.shit = 'background-color:#cfe6ff;border:#409EFF 1px solid;color:#ff7f50';
      }
    },
    // 功能按钮
    goBack(){
      this.$router.push('/');
    },
    click_submit(){
      this.postRequest('/note/insert',this.articleForm).then(resp=>{
        if(resp){
          if(resp.status == 201 || resp.status==203) {
            this.articleForm = resp.obj;
          }
        }
      })
    },
    color_orange(){
      this.init_color();
      this.istyle.colors.oragne='background-color: coral; box-shadow: 0 0 8px red;';
      this.articleForm.color='coral';
    },
    color_green(){
      this.init_color();
      this.istyle.colors.green='background-color: greenyellow;box-shadow: 0 0 8px green;';
      this.articleForm.color='greenyellow';
    },
    color_purple(){
      this.init_color();
      this.istyle.colors.purple='background-color: violet;box-shadow: 0 0 8px purple;';
      this.articleForm.color='violet';
    },
    color_gray(){
      this.init_color();
      this.istyle.colors.gray='background-color: gray;   box-shadow: 0 0 8px gray;';
      this.articleForm.color='gray';
    },
    color_yellow(){
      this.init_color();
      this.istyle.colors.yellow='background-color: yellow;box-shadow: 0 0 8px gray;';
      this.articleForm.color='yellow';
    },
    color_pure(){
      this.init_color();
      this.articleForm.color='';
    },
    init_color(){
      this.istyle.colors.oragne='background-color: coral';
      this.istyle.colors.green='background-color: greenyellow';
      this.istyle.colors.purple='background-color: violet';
      this.istyle.colors.gray='background-color: gray';
      this.istyle.colors.yellow='background-color: yellow';
      this.istyle.colors.pure='background-color: #fff';
    },
    click_weather(weather){
      this.articleForm.weather = weather;
    }
  }
}
</script>

<style scoped>
.div_1{
  width: 100%;
  height: 100%;
  position: absolute;
  /*background-color: blanchedalmond;*/
}
.header_1{
  margin-top: 20px;
}
.input-title{
  width: 100%;
  border: lightslategray 1px solid;
  border-radius: 5px;
}
.class-aside-out{
  width: 250px;
  padding: 30px;
  /*background-color: coral;*/
}
.el-divider--vertical{
  display: inline-block;
  width: 1px;
  height: 800px;
  margin: 0 8px;
  vertical-align: middle;
  position: relative;
}
.button_color{
  border-radius: 50%;
  width: 15px;
  height: 15px;
  padding: 5px;
  /*border: black .1px solid;*/
}
.button-submit{
  width: 100%;
}
.button-submit-nosave{
  width: 50%;
}
.button-submit-save{
  width: 50%;
}

.el-date /deep/  .el-calendar-table .el-calendar-day{
  width: 60px;
  height: 40px;
}
.el_drawer_pics{
}
.div_weather{
  margin-top: 15px;
  background-color: #c3dad9;
  padding: 10px 10px 10px 13px;
  border-radius: 10px;
}
.button_weather{
  border-radius: 50%;
  width: 27px;
  height: 27px;
  padding: 5px;
}
.button_4 {
  margin-left: 10px;
  width: 50px;
  height: 50px;
  font-size: 18px;
  border-radius: 15px;
}
.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 70%;
  margin: 24px 14%;
  /*background-color: orange;*/
  /*box-shadow: 0 0 5px orange;*/
}
.div-pics{
  width: 30%;
  border: #333333 1px solid;
  border-radius: 5px;
  margin-left: 10px;
  /*margin-right: 10px;*/
}
</style>

