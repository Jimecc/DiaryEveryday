// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })


let proxyObj={}

proxyObj['/']={

  // websocket
  ws:false,

  // 目标地址
  target:'http://localhost:8080',
  // target:'http://192.168.199.107:8080',

  // 发送请求头 host 会被改成 target
  changeOrigin:true,

  //不重写请求地址
  pathRewrite: {
    '/': '/'
  }


}




module.exports={
  devServer:{
    host:'localhost',
    port:8081,
    proxy: proxyObj
  },
  lintOnSave: false,
  publicPath:'./'
}
