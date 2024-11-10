<template>
  <div class="sidebar-container">
    <el-menu
        default-active="dashboard"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        router
        background-color="#2c3e50"
        text-color="#fff"
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
        <template #title>查询综合测评</template>
      </el-menu-item>
      <el-menu-item index="/approval" class="rounded-menu-item menu-item">
        <i class="el-icon-document-checked"></i>
        <template #title>我的审批</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'SideBar',
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
.sidebar-container {
  width: 100%;
  height: 98%;
  background-color: #2c3e50; /* 更深的蓝色背景 */
}

.el-menu-vertical-demo {
  width: 96%;
  height: 100%;
  border-right: none; /* 移除边框 */
}

.menu-item {
  border-radius: 5px;
  transition: all 0.3s ease-in-out; /* 更平滑的过渡效果 */
  background: linear-gradient(to right, #35495e, #2c3e50); /* 更深的渐变色 */
  margin: 5px 0;
  position: relative;
  z-index: 1;
}

.menu-item:hover {
  transform: scale(1.05);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.4); /* 更细腻的阴影效果 */
}

.menu-item::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.1); /* 添加半透明的背景层 */
  z-index: -1;
  border-radius: 5px;
}

.rounded-menu-item {
  border-radius: 0 10px 10px 0;
  box-shadow: 5px 0 5px rgba(0, 0, 0, 0.3);
}

</style>
