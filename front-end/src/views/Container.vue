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
  height: 100vh;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  background-image: linear-gradient(to right, #40505F, #6E8496);
}

#container {
  height: 100%;
  width: 100%;
  display: flex;
}

#aside {
  background-color: #2c3e50; /* 主题色 */
  box-shadow: 5px 0 15px rgba(0, 0, 0, 0.3); /* 仅水平阴影 */
  width: 10%;
  min-width: 250px; /* 确保侧边栏在小屏幕上也有足够的空间 */
  transition: width 0.3s; /* 平滑过渡效果 */
}

#header {
  margin-left:5px;
  background-image: linear-gradient(to right, #6E8496, #4A5C6E);
  color: #fff; /* 白色文字 */
  border-radius: 0 0 10px 10px;
  box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.2);
  width: 99.5%;
  z-index: 10; /* 确保在内容滚动时，头部保持在最上方 */
}

#main {
  margin: 10px;
  border-radius: 10px;
  box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.3); /* 仅水平阴影 */
  background-image: linear-gradient(to right, #6E8496, #4A5C6E);
  color: #333; /* 深色文字 */
  width: 99%;
  overflow-y: auto;
}

#footer {
  margin-left:5px;
  background-image: linear-gradient(to right, #6E8496, #4A5C6E);
  color: #fff; /* 白色文字 */
  border-radius: 10px 10px 0 0;
  box-shadow: -5px -5px 15px rgba(0, 0, 0, 0.2); /* 向上投射阴影 */
  width: 99.5%;
}

/* 响应式布局 */
@media (max-width: 768px) {
  #aside {
    width: 100%; /* 在小屏幕上侧边栏占满整个屏幕 */
  }
  #header, #footer {
    padding: 10px; /* 减少内边距 */
  }
}
</style>
