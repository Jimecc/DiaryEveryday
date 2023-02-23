<template>
  <div>
    <div class="diary-div-pics">
      <div style="display: flow;width:40px;">
        <el-row>
          <el-col  :span="32" v-for="(img, index) in images" :key="index" :offset="index > 0 ? 2 : 0">
            <div :body-style="{ padding: '0px' }" style="overflow-x: hidden;width: 210px;height:auto;padding:10px;margin: 20px 30px 20px 30px;border-radius:15px;box-shadow: 0 0 5px #9a9a9a">
              <img style="width:100%;height: auto;left:0;right:0;margin:auto;box-shadow: 0 0 5px #ffebcd" :src="img.url">
              <div style="padding: 14px;">
                <span style="width: 90%;padding: 0 10px 0 0;">{{ img.rname==null ? img.name : img.rname }}</span>
                <div>
                  <time>{{ img.create_date }}</time>
                </div>
                <div class="div-img-button-edit">
                  <el-button type="text" icon="el-icon-edit"></el-button>
                  <el-button type="text" icon="el-icon-view"   @click="click_view_image(index)"></el-button>
                  <el-button type="text" icon="el-icon-delete" style="color: red" @click="click_delete_image(img.id)"></el-button>
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
          multiple
          :show-file-list="false">
        <el-button type="primary" style="width: 300%;margin: 5px 5% 0 5%">上传图片</el-button>
      </el-upload>
    </div>
  </div>
</template>

<script>
export default {
  name: "SideImage",
  props:{
    note_id: String
  },
  data(){
    return{
      images:[]
    }
  },
  mounted() {
    this.getSideImages();
  },
  methods:{
    getSideImages(){
      console.log("XXXXXXXX_______"+this.note_id);
      this.getRequest('/index/file/'+this.note_id).then(resp=>{
        if(resp.length>0){
          this.images = resp ;
        }else{
          this.images=null;
          this.$message({
            message: '该日记还没有创建相册，快点上传照片留存美好回忆吧！',
            type: 'info'
          });
        }
      })
    },
    handleUploadForm(param){
      let formData = new FormData()
      formData.append('file', param.file)
      formData.append('note_id', this.note_id)
      this.postRequest('/file/upload', formData).then((res) => {
        if (res.status == 200) {
          this.$message.success('提交成功');
          this.getRequest('/index/file/'+this.note_id).then(resp=> {
            this.images = resp;
          });
        } else {
          this.$message.error('提交失败')
        }
      })
    },
    click_view_image(index){
      this.show.dialogViewImg = true;
      this.i = index;
      console.log(index);
    },
    click_delete_image(index){
      this.deleteRequest('/file/delete/'+index).then(resp=>{
        if(resp.status == 200){
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.getRequest('/index/file/'+this.note_id).then(resp=> {
            this.images = resp;
          });
        }
      })
    },
  }
}
</script>

<style scoped>
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
.div-img-button-edit{
  text-align: right;
}
</style>
