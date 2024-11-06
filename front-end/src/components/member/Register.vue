<template>
  <div class="register">
    <div class="form">
      <h2 class="formTitle">注册</h2>
      <form @submit.prevent="submitForm" name="registerForm">
        <label>
          学号
          <input type="text" v-model="id" class="input" required/>
        </label>
        <label>
          用户名
          <input type="text" v-model="name" class="input" required/>
        </label>
        <label>
          密码
          <input type="password" v-model="password" class="input" required>
        </label>
        <label>
          性别
          <select v-model="gender" class="input">
            <option value="男">男</option>
            <option value="女">女</option>
          </select>
        </label>
        <label>
          院系
          <input type="text" v-model="className.department" class="input" required>
        </label>
        <label>
          年级
          <input type="text" v-model="className.grade" class="input" required>
        </label>
        <label>
          班级
          <input type="text" v-model="className.name" class="input" required>
        </label>
        <button class="submitButton">注册</button>
      </form>
    </div>
  </div>
</template>

<script>
import {userRegisterService} from "@/api/user.js";
import {ElMessage} from "element-plus";
export default {
  data() {
    return {
      id: '',
      name: '',
      password: '',
      gender: '男', // 默认选中"男"
      className: {
        department: '',
        grade: '',
        name: ''
      },
      role: 'STUDENT', // 根据需求，这里可以设置为常量或动态获取
    };
  },
  methods: {
    async submitForm() {
      try {
        // 使用userRegisterService发送注册请求
        await userRegisterService({
          id: this.id,
          password: this.password,
          name: this.name,
          gender: this.gender,
          className: JSON.stringify(this.className), // 假设后端需要className为字符串格式
          role: this.role
        });
        ElMessage.success('注册成功')
      } catch (error) {
        ElMessage.error('注册失败，请稍后再试');
      }
    }
  }
};
</script>

<style scoped>
.register {
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
  box-sizing: border-box; /* 防止宽度超过容器 */
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
