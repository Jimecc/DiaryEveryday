import axios from 'axios'
import { Message } from 'element-ui';
import router from '../router'


// 请求拦截器
axios.interceptors.request.use(config=>{
    // 如果存在 token，请求携带这个 token
    if(localStorage.getItem('tokenStr')){
        config.headers['Authorization'] = localStorage.getItem('tokenStr');
    }
    return config;
},error=>{
    console.log(error);
})

// 响应拦截器
axios.interceptors.response.use(success=>{
    console.log("SUCCESS::::::"+JSON.stringify(success))
    // 业务逻辑错误
    if(success.status && success.status == 200){
        console.log("上传成功啦啦啦啦啦啦");
        if(success.data.status == 500 || success.data.status == 401 || success.data.status == 403){
            Message.error({message:success.data.message});
            return;
        }
        if(success.data.message){
            Message.success({message:success.data.message});
        }
    }
    return success.data;

},error=>{
    console.log("ERROR::::"+JSON.stringify(error));
    console.log("error:::::"+JSON.stringify(error));
    if(error.response.status == 504 || error.response.status == 404){
        Message.error({message:'服务器被吃了'});
    }else if(error.response.status == 403){
        Message.error({message:'权限不足，请联系管理员！'});
    }else if(error.response.status == 401){
        Message.error({message:'尚未登录，请登录！！！'});
        router.replace('/');
    }else{
        if(error.response.message){
            Message.error({message:error.response.data.message});
        }else{
            Message.error({message:'未知错误 o(╯□╰)o'});
        }
    }
    return;
})


let base = '';
export const postRequest=(url,params)=>{
    return axios({
        method: 'post',
        url:`${base}${url}`,
        data:params
    })
}

export const putRequest=(url,params)=>{
    return axios({
        method:'put',
        url:`${base}${url}`,
        data:params
    })
}

export const getRequest=(url,params)=>{
    return axios({
        method:'get',
        url:`${base}${url}`,
        data:params
    })
}

export const deleteRequest=(url,params)=>{
    return axios({
        method:'delete',
        url:`${base}${url}`,
        data:params
    })
}


