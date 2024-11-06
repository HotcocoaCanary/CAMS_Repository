import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '@/router';

const baseURL = '/api';
const instance = axios.create({ baseURL });

// 添加响应拦截器
instance.interceptors.response.use(
    response => {
        // 判断业务状态码
        if (response.data.statusCode === 200) {
            // 如果业务成功，直接返回data部分
            return response.data.data;
        } else {
            // 如果业务失败，显示错误信息，并返回标准响应体格式
            ElMessage.error(response.data.statusMessage || '操作失败');
            return Promise.reject({
                statusCode: response.data.statusCode,
                statusMessage: response.data.statusMessage,
                data: null
            });
        }
    },
    error => {
        // 判断响应状态码
        if (error.response) {
            const { statusCode } = error.response;
            if (statusCode === 401) {
                ElMessage.error('请先登录');
                router.push('/login').then(() => console.log('用户未登录'));
            } else {
                ElMessage.error('服务异常');
            }
            // 返回标准响应体格式
            return Promise.reject({
                statusCode: statusCode,
                statusMessage: error.response.data.statusMessage || '服务异常',
                data: null
            });
        } else {
            // 处理网络错误或请求被阻止的情况
            ElMessage.error('网络异常或请求被阻止');
            return Promise.reject({
                statusCode: 'NETWORK_ERROR',
                statusMessage: '网络异常或请求被阻止',
                data: null
            });
        }
    }
);

export default instance;
