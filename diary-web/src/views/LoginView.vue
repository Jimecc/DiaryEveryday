<template>
  <div class="div1">
    <el-form class="div2" :rules="rules" ref="loginForm" :model="loginForm">


      <h1 style="margin-top: 35px">登录</h1>
      <el-divider>每日一记</el-divider>
      <el-form-item prop="username">
        <el-input prefix-icon="el-icon-s-custom" v-model="loginForm.username" class="input_1" placeholder="请输入用户名" ></el-input>
      </el-form-item>
      <el-form-item prop="password" style="margin-top: -20px">
        <el-input prefix-icon="el-icon-key" v-model="loginForm.password" class="input_1" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <div style="height: 60px;display: flex;margin-top: 20px">
        <el-form-item prop="code">
          <el-input v-model="loginForm.code" auto-complete="false" prefix-icon="el-icon-guide" class="input-code" placeholder="请输入验证码"></el-input>
        </el-form-item>
        <img style="width: 100px;height: 38px;margin-top: 10px;margin-left: 30px;border-radius: 10px;border: orange 1px solid" src="http://localhost:8080/captcha">
      </div>

      <el-button type="primary" @click="click_button_login" class="button-submit">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
      <el-button type="text" @click="click_go_register" style="width: 20%;text-align: left;margin-top: 20px">注册</el-button>
      <el-button type="text" style="margin-left: 30%">忘记密码</el-button>

    </el-form>

    <div class="div-3">
      <el-tooltip content="Github" placement="top">
        <img @click="click_img_1" class="img-1" src="../assets/github-black.png">
      </el-tooltip>
      <el-tooltip content="Gitee" placement="top">
        <img @click="click_img_2" class="img-1" src="../assets/gitee.png">
      </el-tooltip>
    </div>

  </div>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      loginForm:{
        username: '',
        password: '',
        code: ''
      },
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
      }
    }
  },
  methods:{
    click_go_register(){
      this.$router.push('/register')
    },
    click_button_login(){
      this.$refs.loginForm.validate((valid)=>{
        if(valid) {
          this.postRequest('/auth/login', this.loginForm).then(resp => {
            console.log("RESP:"+JSON.stringify(resp));
            if (resp) {
              const tokenStr = resp.obj.tokenHead + resp.obj.token;
              localStorage.setItem('tokenStr', tokenStr);
              if (resp.status == 200) {
                let path = this.$route.query.redirect;
                this.$router.replace((path == '/' || path == undefined) ? '/' : path);
              }
            }
          })
        }else{
          this.$message.error('请输入所有字段！');
          return false;
        }
      })
    },
    click_img_1(){
      window.location.href = 'https://github.com/Jimecc/';
    },
    click_img_2(){
      window.location.href = 'https://github.com/Jimecc/';
    }
  }
}
</script>

<style scoped>
.box-card{
  width: 350px;
  height: 500px;
  /*background-color: blanchedalmond;*/
  margin: 10% 0 0 38%;
  border-radius: 20px;
  border: blanchedalmond 1px solid;
  text-align: center;
  background: rgba(255, 255, 255, .4);
}
.input-code{
  width: 75%;
  margin-left: 10%;
  /*border-radius: 100px 0 0 100px;*/
  margin-top: 10px;
}
.div1{
  /*text-align: center;*/
  width: 100%;
  height: 100%;
  position: absolute;
  background-color: blanchedalmond;
  background-image: url('../assets/bgimg.png');
  background-size: cover;
  /*background: linear-gradient(blue,green);*/
  /* 背景渐变色 - 原理2 */
  /*background: linear-gradient(-45bgimg1.pngdeg, #ee7752, #e73c7e, #23a6d5, #23d5ab);*/
  /*!* 背景尺寸 - 原理3 *!*/
  /*background-size: 600% 600%;*/
  /*!* 循环动画 - 原理4 *!*/
  /*animation: gradientBG 10s ease infinite;*/
}
@keyframes gradientBG {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.div2{
  width: 350px;
  height: 520px;
  /*background-color: #fff;*/
  margin: 10% 0 0 40%;
  border-radius: 20px;
  /*top:0;*/
  /*bottom: 0;*/
  /*left: 0;*/
  /*right: 0;*/
  /*margin: auto;*/
  border: coral 1px solid;
  text-align: center;
  background: rgba(255, 255, 255, 1);
}
.input_1{
  width: 80%;
  margin-top: 35px;
  border-radius: 20px;
}
:deep(.el-input__inner) {
  border-radius: 20px;
  border: coral .1px solid;
}
.button-submit{
  width: 80%;
  margin-top: 20px;
  border-radius: 80px;
}
.div-3{
  height: 100%;
  width: 100%;
  position: absolute;
  /*background-color: #42b983;*/
  text-align: right;
}
.img-1{
  width: 40px;
  margin-top: 11%;
  margin-right: 40px;
  border-radius: 150px;
  box-shadow: 0 0 7px black ;
  border: #ffbc03 2px solid;
}
/deep/.timr.el-form .el-form-item__error {
  top: 21%;
  right: 25% !important;
  left: unset;
}
</style>
