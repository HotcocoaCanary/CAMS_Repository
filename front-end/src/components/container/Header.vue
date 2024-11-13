<template>
  <div class="header">
    <span>CAMS_Repository</span>
    <div v-if="user && user.name" class="header-actions">
      <span>Welcome: {{ user.name }}</span>
      <button @click="handleLogout">登出</button>
    </div>
  </div>
</template>

<script setup>
import { userLogout } from "@/api/user.js";
import router from "@/router/index.js";
import { useUserStore } from "@/stores/user.js";
import { computed, onMounted } from "vue";

const userStore = useUserStore();

const handleLogout = async () => {
  try {
    userStore.setUser(undefined); // 清空用户信息
    await userLogout(); // 调用登出 API
    await router.push('/login'); // 重定向到登录页面
  } catch (error) {
    console.error("登出失败", error);
    location.reload();
  }
};

// 使用计算属性来响应式地获取用户信息
const user = computed(() => userStore.user);

// 在组件挂载时执行的操作
onMounted(async () => {
  if (userStore.user === undefined) {
    await router.push('/login');
  }
});
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
