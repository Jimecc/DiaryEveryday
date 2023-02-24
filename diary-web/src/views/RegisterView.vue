<template>

  <div class="div-out-1">
    <el-form class="div-2"  :rules="rules" ref="registerParam" :model="registerParam">
      <div class="div-form">
        <h3 style="text-align: center">注册</h3>
        <el-divider>每日一记</el-divider>

        <el-form-item prop="name" class="el-form-item">
            <el-input v-model="registerParam.name"  class="input-info" placeholder="请输入昵称"></el-input>
        </el-form-item>
        <el-form-item prop="username" class="el-form-item">
            <el-input v-model="registerParam.username"  class="input-info" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password1" class="el-form-item">
            <el-input v-model="registerParam.password1" class="input-info" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="password2" class="el-form-item">
            <el-input v-model="registerParam.password2" class="input-info" placeholder="请确认密码" show-password></el-input>
        </el-form-item>

        <el-button type="primary" @click="button_submit" class="button-submit">注&nbsp;&nbsp;&nbsp;册</el-button>
        <div style="text-align: right;">
          <el-button size="small" @click="button_go_login" type="text" class="button-go-login">已有账号，去登录</el-button>
        </div>

      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "RegisterView",
  data(){
    return{
      registerParam:{
        name:'',
        username:'',
        password1:'',
        password2:''
      },
      rules:{
        name: [{required: true, message: '请输入昵称', trigger: 'blur'}],
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password1: [{required: true, message: '请输入密码', trigger: 'blur'}],
        password2: [{required: true, message: '请确认密码', trigger: 'blur'}]
      }
    }
  },
  methods:{
    button_go_login(){
      this.$router.push('/login')
    },
    button_submit(){
      this.postRequest('/auth/register',this.registerParam).then(resp=>{
        if(resp){
          alert(JSON.stringify(resp));
        }
      })
    }
  }
}
</script>

<style scoped>
:deep(.el-input__inner) {
  border-radius: 10px;
  border: coral .1px solid;
}
.div-out-1{
  width: 100%;
  height: 100%;
  position: absolute;
  /*background-color: #42b983;*/
  background-color: blanchedalmond;
  background-image: url('../assets/bgimg.png');
  background-size: cover;
  /*text-align: center;*/
}
.div-2{
  background-image: url('../assets/plant.svg') ;
  background-size: contain;
  background-repeat: no-repeat;
  display: flex;
  background-color: #fff;
  width: 680px;
  height: 450px;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 20px;
  box-shadow: 0 0 15px gray;
}
.input-info{
  margin-top: 10px;
}
.div-form{
  width: 45%;
  margin-left: 53%;
}
/* button */
.button-submit{
  width: 100%;
  margin-top: 15px;
}
.button-go-login{
  /*margin-left: 20%;*/
  /*font-size: 10px;*/
  margin-top: 10px;
  color: #b7b7b7;
}
.el-form-item{
  margin-top: -11px;
  width: 95%;
  /*background-color: #42b983;*/
}
</style>


