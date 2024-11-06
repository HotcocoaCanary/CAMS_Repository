//定制请求的实例
import axios from 'axios';
import { ElMessage } from 'element-plus'
const baseURL = '/api';
const instance = axios.create({ baseURL })

import router from '@/router'
//添加响应拦截器
instance.interceptors.response.use(
    result => {
        //判断业务状态码
        if(result.data.code===200){
            return result.data;
        }
        //操作失败
        ElMessage.error(result.data.msg?result.data.msg:'服务异常')
        //异步操作的状态转换为失败
        return Promise.reject(result.data)

    },
    err => {
        //判断响应状态码,如果为401,则证明未登录,提示请登录,并跳转到登录页面
        if(err.response.status===401){
            ElMessage.error('请先登录')
            router.push('/login').then(r => console.log('用户未登录'))
        }else{
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);
    }
)
export default instance;
