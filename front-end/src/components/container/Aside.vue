<template>
  <el-menu
      default-active="dashboard"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      router
  >
    <el-menu-item index="/dashboard">
      <i class="el-icon-menu"></i>
      <template #title>Dashboard</template>
    </el-menu-item>
    <el-menu-item index="/maintain">
      <i class="el-icon-setting"></i>
      <template #title>Maintain</template>
    </el-menu-item>
    <el-menu-item index="/import">
      <i class="el-icon-upload"></i>
      <template #title>Import</template>
    </el-menu-item>
    <el-menu-item index="/search">
      <i class="el-icon-search"></i>
      <template #title>Search</template>
    </el-menu-item>
    <el-menu-item index="/approval">
      <i class="el-icon-document-checked"></i>
      <template #title>Approval</template>
    </el-menu-item>
  </el-menu>
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
.el-menu-vertical-demo {
  width: 100%; /* Adjust the width as needed */
  height: 100vh; /* Full height */
}
</style>
