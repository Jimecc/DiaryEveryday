<template>
  <div class="div_1">
    <el-container>
      <el-header>
        <el-page-header style="margin-top: 25px" content="编辑日记" @back="goBack"></el-page-header>
      </el-header>
      <el-divider>每日一记</el-divider>
      <el-container>
        <el-aside class="aside_1">
          <el-input v-model="articleForm.title" class="input_title" placeholder="请输入文章标题"></el-input>
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
              <el-button type="info" plain class="button-submit-save">保存并退出</el-button>
            </div>
          </div>
        </el-aside>
        <el-divider direction="vertical"></el-divider>
        <el-main>
          <!-- 普通编辑器与Markdown编辑器的选择开关 -->
          <el-button-group>
            <el-button size="small" :type="!value?'primary':''" v-model="value" @click="value = !value" class="button-chs-editor">Markdown编辑器</el-button>
            <el-button size="small" :type="value?'primary':''"  v-model="value" @click="value = !value" class="button-chs-editor">普通编辑器</el-button>
          </el-button-group>

          <div style="width: 100%;display: flex">
            <div style="width: 80%">

            </div>
            <div style="text-align: right;width: 18%">
              <el-button size="small"  @click="click_show_images" type="text" icon="el-icon-picture-outline" >显示相册</el-button>
            </div>
          </div>
          <div style="display: flex">
            <div v-if="value" style="width: 100%;">
              <el-input
                  class="el-input-article"
                  type="textarea"
                  :autosize="{minRows:30,maxRows:32}"
                  placeholder="请输入内容"
                  v-model="articleForm.article"
                  show-word-limit="true"
                  clearable="true"
                  suffix-icon="el-icon-tickets"
              >
              </el-input>
            </div>
            <div v-if="!value" style="padding: 10px;width: 100%;height: 600px">
              <mavon-editor :toolbars="toolbars" style="border-radius: 10px;height:100%;width: 100%" v-model="articleForm.article" ref=md></mavon-editor>
            </div>

            <div v-if="show.show_pic_div" style="height: 730px;" class="new-diary-div-pics">
              <div style="display: flow;width:40px">
                <el-row>
                  <el-col  :span="32" v-for="(img, index) in images" :key="index" :offset="index > 0 ? 2 : 0">
                    <div :body-style="{ padding: '0px' }" style="width: 220px;height:auto;padding:10px;margin: 20px 30px 20px 30px;border: grey .1px solid;border-radius:15px;box-shadow: 0 0 5px #f3f4f6">
                      <img style="width:220px;height: auto;left:0;right:0;margin:auto;box-shadow: 0 0 5px #ffebcd" :src="img.url" class="drawer-s-images">
                      <div style="padding: 14px;">
                        <span>{{ img.rname==null ? img.name : img.rname }}</span>
                        <div>
                          <time class="time">{{ img.create_date }}</time>
                        </div>
                        <div class="div-img-button-edit">
                          <el-button type="text" icon="el-icon-edit"   class="button-img-edit" @click="show.dialogFormVisible = true"></el-button>
                          <el-button type="text" icon="el-icon-view"   @click="click_view___img" class="button-img-edit"></el-button>
                          <el-button type="text" icon="el-icon-delete" @click="click_delete_img(img.id)" class="button-img-edit"></el-button>
                        </div>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>

          </div>
        </el-main>
      </el-container>
    </el-container>

    <el-dialog title="编辑图片信息" :visible.sync="show.dialogFormVisible">
      <el-form :model="fileForm">
        <el-form-item label="图片名称" label-width="80px">
          <el-input v-model="fileForm.name" placeholder="请输入图片名称" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图片简介" label-width="80px">
          <el-input placeholder="请输入简介信息" type="textarea" size="min" :autosize="{ minRows: 4, maxRows: 5}" v-model="fileForm.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="show.dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="show.dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="查看图片" :visible.sync="show.dialogViewImg">
      <div style="display: flex;width:100%;">
        <img style="border: #999999 0.1px solid;padding:10px;width: 70%;height: auto" src="http://192.168.199.100:8888/group1/M00/00/01/wKjHZGPr1ZWAD6_8AAb5j6JZa2E410.PNG"/>
        <div style="width: 25%;padding:10px 0">
          <div style="width: 100%;margin-left: 5px">
            <div style="width: 100%;border-bottom: #999999 0.1px solid;padding-bottom: 3px">
              <h1>123.png</h1>
            </div>
            <div style="width: 100%;border-bottom: #999999 0.1px solid;padding-bottom: 3px">
              <h4>创建时间</h4>
              <span>2022-02-04</span>
            </div>
            <div style="width: 100%;border-bottom: #999999 0.1px solid;padding-bottom: 3px">
              <h4>简介</h4>
              <span>21312341232112312312312312312</span>
            </div>
          </div>

          <el-button-group style="margin: 30px 7% 0 8%">
            <el-button type="primary" style="border-radius: 20px 0 0 20px;" size="small" icon="el-icon-arrow-left">上一页</el-button>
            <!--              <el-button type="primary" size="small" icon="el-icon-connection"></el-button>-->
            <el-button type="primary" style="border-radius: 0 20px 20px 0;" size="small">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
          </el-button-group>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import FilesManagement from "@/components/FilesManagement.vue";
export default {
  name: "NewDiaryView",
  props:{
    diary: {}
  },
  components:{
    FilesManagement
  },
  data() {
    return {
      toolbars: {
        bold: true,
        italic: true,
        header: true,
        underline: true,
        strikethrough: true,
        mark: true,
        superscript: true,
        subscript: true,
        quote: true,
        ol: true,
        ul: true,
        link: true,
        imagelink: true,
        code: false,
        table: true,
        fullscreen: true,
        readmodel: true,
        htmlcode: true,
        help: true,
        undo: true,
        redo: true,
        trash: true,
        save: true,
        navigation: true,
        alignleft: true,
        aligncenter: true,
        alignright: true,
        subfield: true,
        preview: true,
      },
      value:false,
      articleForm: {
        id: 0,
        manager_id: 0,
        title: '',
        article: '',
        info: '',
        create_date: new Date(),
        update_date:null,
        color: '',
        weather: '',
        score: 0,
        collection: 0,
        shit: 0,
        delete_or_not:0
      },
      fileForm:{
        id: 0,
        url: '',
        name:'',
        rname:'',
        info:'',
        user_id:0,
        diary_id:0,
        create_date: null
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
        show_pic_div: false,
        dialogViewImg: false,
        dialogFormVisible: false
      },
      value1: null,
      value2: null,
      value3: new Date(),
      show_date: false,
      show_weather: false,
      drawer: true,
      article: '',
      images: []
    }
  },
  mounted() {
    this.mounted_edit_diary();
  },
  methods:{
    click_view___img(){
      this.show.dialogViewImg = !this.show.dialogViewImg;
    },
    click_delete_img(id){
      this.deleteRequest('/file/delete/'+id);
      this.getRequest('/file/list4article/'+this.articleForm.id).then(resp=>{
        this.images = resp ;
      })
    },
    click_show_images(){
      this.show.show_pic_div = !this.show.show_pic_div;
      this.getRequest('/file/list4article/'+this.articleForm.id).then(resp=>{
        this.images = resp ;
      })
    },
    handleUploadForm(param){
      // -- 跳出选择框
      if(this.articleForm.id == 0){
        this.$confirm('检测到该日记还没有保存，该操作必须要至少保存一次日记后才可以使用，是否保存？', '提示', {
          confirmButtonText: '是，立即保存',
          cancelButtonText: '否，稍后手动保存',
          type: 'warning'
        }).then(() => {
          // -- 如果选择是，那么就进行提交，并且依据status进行判断，将返回值赋给 articleForm，以获得id
          if(this.articleForm.title=='' || this.articleForm.article == ''){
            this.$message.error('字段不完整，请填写标题与文章后再提交')
            return;
          }
          this.postRequest('/diary/insert',this.articleForm).then(resp=>{
            if(resp){
              if(resp.code == 200) {
                // -- 提交文章
                this.articleForm = resp.obj;
                // -- 上传图片
                let formData = new FormData()
                formData.append('file', param.file)
                formData.append('diary_id', this.articleForm.id)
                // 向后端发送数据
                this.postRequest('/file/upload', formData).then((res) => {
                  if (res.code === 200) {
                    this.$message.success('修改信息成功')
                  } else {
                    this.$message.error('修改信息失败')
                  }
                  this.formFileList = []
                  this.uploadFormFileList = []
                })
              }
            }
          })
          return
          // -- 如果选择否，则什么都不做
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          });
          return
        });
      }else{
        let formData = new FormData()
        formData.append('file', param.file)
        formData.append('diary_id', this.articleForm.id)
        this.postRequest('/file/upload', formData).then((res) => {
          if (res.code === 200) {
            this.$message.success('提交成功')
          } else {
            this.$message.error('提交失败')
          }
          this.formFileList = []
          this.uploadFormFileList = []
        })
      }
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
      this.articleForm = this.$route.query.diary;
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
      this.$router.push({
        path: '/',
        query: {
          diary: this.articleForm,
        }
      });
    },
    click_submit(){
      this.postRequest('/diary/insert',this.articleForm).then(resp=>{
        if(resp){
          if(resp.code == 200) {
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
.input_title{
  width: 100%;
  border: lightslategray 1px solid;
  border-radius: 5px;
}
.aside_1{
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
.new-diary-div-pics{
  overflow:auto;
  text-overflow:ellipsis;
  width: 30%;
  height: 90%;

  border: #333333 1px solid;
  border-radius: 5px;
  margin-left: 10px;
  /*margin-right: 10px;*/
}
.drawer-s-images{

}

.div-img-button-edit{
  text-align: right;
}

.button-img-edit{
  font-size: 15px;
}
.el-input-article{
  box-shadow: 0 0 5px #999999;
  border-radius: 10px;
  padding: 20px;
  width: 96%;
}
.button-chs-editor{
  width: 150px;
  border-radius: 10px;
}
</style>

