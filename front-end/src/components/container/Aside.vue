<template>
  <div class="sidebar-container">
    <div class="sidebar-header">菜单</div>
    <el-menu
        :default-active="defaultActive"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        router
        background-color="#2c3e50"
        text-color="1a1a1a"
        active-text-color="#42b983"
        :collapse-transition="false"
    >
      <el-menu-item index="/home" class="rounded-menu-item menu-item">
        <i class="el-icon-menu"></i>
        <template #title>主页</template>
      </el-menu-item>
      <el-menu-item index="/maintain" class="rounded-menu-item menu-item">
        <i class="el-icon-setting"></i>
        <template #title>综合测评信息维护</template>
      </el-menu-item>
      <el-menu-item index="/import" class="rounded-menu-item menu-item">
        <i class="el-icon-upload"></i>
        <template #title>导入综合测评成绩</template>
      </el-menu-item>
      <el-menu-item index="/search" class="rounded-menu-item menu-item">
        <i class="el-icon-search"></i>
        <template #title>导出综合测评</template>
      </el-menu-item>
      <el-menu-item index="/approval" class="rounded-menu-item menu-item">
        <i class="el-icon-document-checked"></i>
        <template #title>我的审批</template>
      </el-menu-item>
    </el-menu>
    <div class="sidebar-footer">感谢支持</div>
  </div>
</template>

<script>
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'SideBar',
  setup() {
    const router = useRouter();
    const defaultActive = ref(router.currentRoute.value.path);

    // 监听路由变化，更新菜单选中状态
    watch(() => router.currentRoute.value.path, (newPath) => {
      defaultActive.value = newPath;
    });

    return {
      defaultActive,
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
};
</script>

<style scoped>
.sidebar-container {
  height: 100vh;
  overflow-y: auto; /* 添加垂直滚动条 */
  background-color: #ffffff; /* 浅色背景 */
  color: #333; /* 深色文本 */
}

.sidebar-header {
  display: flex; /* 使用Flexbox布局 */
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  position: sticky;
  top: 0;
  background-color: #007bff;
  color: #ffffff;
  font-size: 1.2em; /* 设置与#header相同的字体大小 */
  height: 60px; /* 设置与#header相同的高度 */
  text-align: center; /* 文本居中 */
  z-index: 1000;
  margin-bottom: 30px;
}

.sidebar-footer {
  display: flex; /* 使用Flexbox布局 */
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  position: fixed;
  bottom: 0;
  left: 0;
  width: 250px;
  height: 60px;
  background-color: #333; /* 底部背景颜色 */
  color: white;
  text-align: center;
  padding: 10px;
  font-size: 1em; /* 根据需要设置字体大小 */
  z-index: 1000; /* 确保底部在其他元素之上 */
}

.el-menu-vertical-demo {
  width: 100%; /* 调整宽度以匹配容器 */
  border-right: none; /* 移除边框 */
  background-color: #fff !important; /* 覆盖默认的背景颜色 */
  color: #1a1a1a; /* 菜单文本颜色 */
  transition: background-color 0.3s ease; /* 添加过渡效果 */
}

.rounded-menu-item {
  margin: 5px;
  border-radius: 8px; /* 添加圆角 */
  transition: background-color 0.3s ease; /* 添加过渡效果 */
}

.menu-item i {
  color: #717171FF; /* 图标颜色 */
}

/* 选中菜单项的样式 */
.el-menu-item.is-active {
  background-color: #007bff !important; /* 选中背景颜色 */
  color: #ffffff !important; /* 选中文本颜色 */
}

/* 鼠标悬停菜单项的样式 */
.el-menu-item:hover {
  background-color: #007bff !important; /* 悬停背景颜色 */
  color: #ffffff !important; /* 悬停文本颜色 */
}
</style>
