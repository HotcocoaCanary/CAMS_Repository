<template>
  <div class="auth-container">
    <div class="auth-tabs">
      <button
          class="auth-tab"
          :class="{ active: isLoginActive }"
          @click="isLoginActive = true"
      >
        登录
      </button>
      <button
          class="auth-tab"
          :class="{ active: !isLoginActive }"
          @click="isLoginActive = false"
      >
        注册
      </button>
    </div>

    <component :is="currentComponent" />
  </div>
</template>

<script>
import Login from "@/components/member/Login.vue";
import Register from "@/components/member/Register.vue";

export default {
  components: {
    Login,
    Register
  },
  data() {
    return {
      isLoginActive: true
    };
  },
  computed: {
    currentComponent() {
      return this.isLoginActive ? 'Login' : 'Register';
    }
  }
};
</script>

<style scoped>
.auth-container {
  max-width: 360px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.auth-tabs {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

.auth-tab {
  flex: 1;
  padding: 10px;
  border: none;
  background-color: #f0f0f0;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.auth-tab.active {
  background-color: #007bff;
  color: white;
}

.auth-tab:hover:not(.active) {
  background-color: #ddd;
}
</style>
