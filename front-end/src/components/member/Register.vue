<template>
  <div class="register">
    <div class="form">
      <h2 class="formTitle">注册</h2>
      <form @submit.prevent="submitForm" name="registerForm">
        <label>
          学号
          <input type="text" v-model="id" class="input" :required="role !== 'TEACHER'" />
        </label>
        <label>
          用户名
          <input type="text" v-model="name" class="input" required />
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
          角色
          <select v-model="role" class="input">
            <option value="STUDENT">学生</option>
            <option value="TEACHER">班主任教师</option>
          </select>
        </label>
        <label v-if="role === 'TEACHER'">
          学院
          <input type="text" v-model="classNameOrDepartment" class="input" required />
        </label>
        <label v-else>
          班级
          <input type="text" v-model="classNameOrDepartment" class="input" required />
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
      role: 'STUDENT', // 默认角色为学生
      classNameOrDepartment: '', // 用于存储学院或班级信息
    };
  },
  methods: {
    async submitForm() {
      const registerData = {
        user: {
          id: this.id,
          password: this.password,
          name: this.name,
          gender: this.gender,
          role: this.role,
        },
        classNameOrDepartment: this.classNameOrDepartment,
      };
      try {
        await userRegisterService(registerData);
        ElMessage.success('注册成功');
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
