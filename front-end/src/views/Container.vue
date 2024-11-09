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

.common-layout {
  height: 100vh;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  background: linear-gradient(to bottom right, #999999, #666666, #999999);
}

#container {
  height: 100%;
  width: 100%;
  display: flex;
}

#aside {
  background-color: #cccccc;
  width: 17%;
}

#header {
  width: 100%;
}

#main {
  width: 100%;
  overflow-y: auto; /* 当内容超出时显示滚动条 */
}

#footer {
  width: 100%;
}
</style>
