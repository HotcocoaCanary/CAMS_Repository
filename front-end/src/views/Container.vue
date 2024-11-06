<script setup>
import Aside from "@/components/container/Aside.vue";
import Header from "@/components/container/Header.vue";
import Footer from "@/components/container/Footer.vue";
</script>

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

<style scoped>
:root {
  --header-footer-bg-color: #BEAEA1;
  --main-bg-color: darkgray;
  --shadow-color: rgba(0, 0, 0, 0.2); /* 阴影颜色 */
}

.common-layout {
  height: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
}

#container {
  flex: 1;
  display: flex;
}

#aside {
  width: 250px; /* 可以设置一个固定宽度或者使用CSS变量 */
  flex: 0 0 auto;
  background-color: white; /* 设置背景颜色 */
  box-shadow: 2px 0 5px var(--shadow-color); /* 添加右侧阴影 */
  border-top-right-radius: 10px; /* 添加右上圆角 */
  border-bottom-right-radius: 10px; /* 添加右下圆角 */
  overflow-y: auto; /* 当内容超出时显示滚动条 */
  z-index: 1; /* 确保阴影在其他元素之上 */
}

#header {
  background-color: var(--header-footer-bg-color);
  box-shadow: 0 2px 5px var(--shadow-color); /* 添加底部阴影 */
  border-radius: 10px 10px 0 0; /* 添加顶部圆角 */
}

#main {
  flex: 1;
  background-color: var(--main-bg-color);
  overflow-y: auto; /* 当内容超出时显示滚动条 */
  border-radius: 0 0 10px 10px; /* 添加底部圆角 */
}

#footer {
  background-color: var(--header-footer-bg-color);
  box-shadow: 0 -2px 5px var(--shadow-color); /* 添加顶部阴影 */
  border-radius: 0 0 10px 10px; /* 添加底部圆角 */
}
</style>
