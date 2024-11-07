import request from '@/utils/request.js'

// 提供调用注册接口的函数
export const userRegisterService = (registerData) => {
    // 将对象转换为JSON字符串
    const jsonData = JSON.stringify(registerData);
    // 设置请求头
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    return request.post('/user/register', jsonData, config);
}

// 提供调用登录接口的函数
export const userLoginService = (loginData) => {
    // 将对象转换为JSON字符串
    const jsonData = JSON.stringify(loginData);
    // 设置请求头
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    return request.post('/user/login', jsonData, config);
}
