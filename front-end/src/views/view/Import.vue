<template>
  <el-upload
      class="upload-demo"
      drag
      :action="uploadUrl1"
      :name="'courseExcelFile'"
  >
  <el-icon class="el-icon--upload"><upload-filled /></el-icon>
  <div class="el-upload__text">
    提交文件 <em>点击上传</em>
  </div>
  <template #tip>
    <div class="el-upload__tip">
      请提交 学生成绩excel 文件 (<500kb)
    </div>
  </template>
  </el-upload>
  <el-form>
    <!-- 学期下拉列表 -->
    <el-form-item label="选择学期">
      <el-select v-model="selectedTerm" placeholder="请选择学期">
        <el-option
            v-for="term in termOptions"
            :key="term"
            :label="termLabel(term)"
            :value="term">
        </el-option>
      </el-select>
    </el-form-item>

    <!-- 文件上传组件 -->
    <el-upload
        class="upload-demo"
        drag
        :action="uploadUrl2"
        :name="'otherCourseExcelFile'"
        :data="{ term: selectedTerm }"
        :on-success="handleSuccess"
        :on-error="handleError"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        提交文件 <em>点击上传</em>
      </div>
      <template #tip>
        <div class="el-upload__tip">
          请提交其他课程excel文件 (<500kb)
        </div>
      </template>
    </el-upload>
  </el-form>
</template>

<script setup lang="ts">
import { UploadFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'

const uploadUrl1 = ref('/api/teacher/course/intellectual')  // 替换为后端接口的实际URL
const uploadUrl2= ref('/api/teacher/course/other')  // 替换为后端接口的实际URL

const selectedTerm = ref('')  // 绑定选择的学期
const termOptions = ref([
  'FRESHMAN_FALL', 'FRESHMAN_SPRING',
  'SOPHOMORE_FALL', 'SOPHOMORE_SPRING',
  'JUNIOR_FALL', 'JUNIOR_SPRING',
  'SENIOR_FALL', 'SENIOR_SPRING'
])

const termLabel = (term: string) => {
  // 根据Term枚举值返回对应的中文标签
  const labels: { [key: string]: string } = {
    'FRESHMAN_FALL': '大一上',
    'FRESHMAN_SPRING': '大一下',
    'SOPHOMORE_FALL': '大二上',
    'SOPHOMORE_SPRING': '大二下',
    'JUNIOR_FALL': '大三上',
    'JUNIOR_SPRING': '大三下',
    'SENIOR_FALL': '大四上',
    'SENIOR_SPRING': '大四下'
  };
  return labels[term] || term;
}
</script>



<style scoped>

</style>
