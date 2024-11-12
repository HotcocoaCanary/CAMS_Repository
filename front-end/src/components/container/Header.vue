<template>
  <div class="header">
    <span>CAMS_Repository</span>
    <div class="header-actions">
      <!-- 如果用户已登录，则显示用户欢迎信息和登出按钮 -->
      <span>Welcome: {{ user.name }}</span> <!-- 使用 user 对象的 name 属性来显示用户名 -->
      <button @click="handleLogout">登出</button>
    </div>
  </div>
</template>

<script>
import { userLogout } from "@/api/user.js";
import router from "@/router/index.js";
import emitter from "@/utils/emitter.js";
import { onMounted, onUnmounted, reactive, computed, watch } from "vue";

export default {
  setup() {
    // 使用 reactive 创建响应式的 user 对象
    const user = reactive({
      id: "",
      password: "",
      name: "",
      gender: "",
      role: ""
    });

    // 处理登出逻辑
    const handleLogout = async () => {
      try {
        await userLogout(); // 调用登出 API
        user.name = ""; // 清空用户信息
        await router.push('/login'); // 重定向到登录页面
      } catch (error) {
        console.error("登出失败", error);
      }
    };

    // 注册事件监听，接收 user 数据
    const updateUser = (value) => {
      Object.assign(user, value); // 更新 user 对象
    };

    // 在 user.name 更新后判断跳转
    watch(() => user.name, (newValue) => {
      if (newValue === "") {
        router.push('/login'); // 如果 user.name 为空，跳转到登录页面
      }
    });

    // 注册事件监听
    onMounted(() => {
      emitter.on('sendUser', updateUser);
    });

    onUnmounted(() => {
      emitter.off('sendUser', updateUser);
    });

    // 返回模板中需要的属性和方法
    return {
      user,
      handleLogout,
    };
  }
};
</script>


<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #007bff;
  color: white;
}

.header-actions {
  display: flex;
  gap: 10px;
  align-items: center;
}

button {
  background-color: #ffffff;
  color: #007bff;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #f5f5f5;
}
</style>
