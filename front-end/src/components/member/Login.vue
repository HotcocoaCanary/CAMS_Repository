<template>
  <div class="login">
    <div class="form">
      <h2 class="formTitle">登录</h2>
      <form @submit.prevent="submitForm" name="loginForm">
        <label>
          学号
          <input type="text" id="loginId" class="input" v-model="id" required/>
        </label>
        <label>
          密码
          <input type="password" class="input" v-model="password" required>
        </label>
        <label>
          <input type="checkbox" v-model="rememberMe" class="checkbox"/>
          记住我
        </label>
        <button class="submitButton">登录</button>
      </form>
    </div>
  </div>
</template>

<script>
import { userLoginService } from "@/api/user.js"; // 使用登录服务
import { ElMessage } from "element-plus";
import router from "@/router/index.js";

export default {
  data() {
    return {
      id: '', // 添加id字段
      password: '',
      rememberMe: false,
    };
  },
  methods: {
    async submitForm() {
      try {
        // 使用userLoginService发送登录请求
        await userLoginService({
          id: this.id,
          password: this.password,
          rememberMe: this.rememberMe
        });
        ElMessage.success('登录成功');
        await router.push('/dashboard');
      } catch (error) {
        ElMessage.error('登录失败，请稍后再试');
      }
    }
  }
};
</script>

<style scoped>
.login {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  background-color: #ffffff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.form {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #ffffff;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
  cursor: pointer;
}

.form:hover {
  transform: translateY(-2px);
}

.formTitle {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.input {
  height: 40px;
  padding: 10px;
  font-size: 16px;
  width: 100%;
  margin: 10px 0;
  border: 1px solid #ddd;
  box-sizing: border-box;
  flex-grow: 1;
  border-radius: 5px 0 0 5px;
}

.submitButton {
  padding: 10px;
  margin-top: 20px;
  width: 100%;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submitButton:hover {
  background-color: #1a1a1a;
}

</style>
