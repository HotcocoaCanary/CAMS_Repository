<template>
  <div class="common-layout">
    <el-container id="container">
      <el-aside id="aside">
        <Aside></Aside>
      </el-aside>
      <el-container>
        <el-header id="header">
          <Header></Header>
        </el-header>
        <el-main id="main">
          <router-view></router-view>
        </el-main>
        <el-footer id="footer">
          <Footer></Footer>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import Aside from "@/components/container/Aside.vue";
import Header from "@/components/container/Header.vue";
import Footer from "@/components/container/Footer.vue";
</script>

<style scoped>
.common-layout {
  font-family: 'Arial', sans-serif;
  background-color: #f5f5f5;
}

#container {
  height: 100vh;
}

#aside {
  width: 250px;
  background-color: #333;
  color: white;
  margin-right: 10px;
}

#header {
  background-color: #007bff;
  color: white;
  padding: 10px;
  font-size: 1.2em;
  height: 60px;
}

#main {
  background-color: #ffffff; /* 白色背景 */
  padding: 20px; /* 内边距 */
  margin-top: 8px;
  margin-bottom: 8px;
  border-radius: 8px;
}

#footer {
  background-color: #333;
  color: white;
  text-align: center;
  padding: 10px;
}
</style>

