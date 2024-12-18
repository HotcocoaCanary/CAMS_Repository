<script setup>
import { ref } from 'vue';
import axios from 'axios';

const isLoading = ref(false);
const selectedTerm = ref('FRESHMAN_FALL'); // 默认选中大一上

const handleExport = async () => {
  isLoading.value = true; // 开始加载

  try {
    // 设置请求的URL和查询参数
    const url = `/api/teacher/ce/get?term=${selectedTerm.value}`;

    // 发起GET请求
    const response = await axios.get(url, {
      responseType: 'blob', // 因为我们要下载的是一个文件
    });

    // 创建一个Blob对象，并使用URL.createObjectURL创建一个可下载的链接
    const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    const downloadUrl = window.URL.createObjectURL(blob);

    // 创建一个临时的a标签用于下载
    const link = document.createElement('a');
    link.href = downloadUrl;
    link.download = `${selectedTerm.value}ce.xlsx`; // 设置下载的文件名
    document.body.appendChild(link);
    link.click();

    // 清理和释放URL对象
    window.URL.revokeObjectURL(downloadUrl);
    document.body.removeChild(link);
  } catch (error) {
    console.error('下载Excel失败:', error);
  } finally {
    isLoading.value = false; // 加载结束
  }
};
</script>

<template>
  <div>
    <el-select v-model="selectedTerm" placeholder="请选择学期">
      <el-option
          v-for="term in termOptions"
          :key="term.value"
          :label="term.label"
          :value="term.value"
      />
    </el-select>
    <el-button
        type="success"
        :loading="isLoading"
        @click="handleExport"
        round
    >
      导出
    </el-button>
  </div>
</template>

<script>
// 定义学期枚举值和对应的中文描述
const termOptions = [
  { value: 'FRESHMAN_FALL', label: '大一上' },
  { value: 'FRESHMAN_SPRING', label: '大一下' },
  { value: 'SOPHOMORE_FALL', label: '大二上' },
  { value: 'SOPHOMORE_SPRING', label: '大二下' },
  { value: 'JUNIOR_FALL', label: '大三上' },
  { value: 'JUNIOR_SPRING', label: '大三下' },
  { value: 'SENIOR_FALL', label: '大四上' },
  { value: 'SENIOR_SPRING', label: '大四下' },
];
</script>

<style scoped>

</style>
