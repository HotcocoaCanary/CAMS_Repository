<template>
  <div class="container-top">
    <span class="title-top">我的审批</span>
    <div class="content">
      <div class="approval-list">
        <div
            v-for="(item, index) in approvals"
            :key="index"
            class="approval-item"
            @click="selectApproval(item)"
            :class="{ 'selected': selectedApproval === item }"
        >
          <div class="approval-info">
            <span>{{ item.name }}</span>
            <span>{{ item.studentId }}</span>
            <span>{{ item.className }}</span>
            <span>{{ item.status }}</span>
          </div>
        </div>
      </div>
      <div class="approval-details">
        <div v-if="selectedApproval">
          <h3>详细信息</h3>
          <p>姓名：{{ selectedApproval.name }}</p>
          <p>学号：{{ selectedApproval.studentId }}</p>
          <p>班级：{{ selectedApproval.className }}</p>
          <p>审批状态：{{ selectedApproval.status }}</p>
          <button @click="approve">同意审批</button>
          <button @click="reject">拒绝审批</button>
        </div>
        <div v-else>
          <p>请选择一个审批项以查看详细信息</p>
        </div>
      </div>
    </div>
  </div>
  <div class="container-bottom">
    <span class="title-bottom">审批统计</span>
    <div class="content">
      <div class="stats-item" id="pending-stats">
        <span>待审批</span>
        <span>10</span>
      </div>
      <div class="stats-item" id="approved-stats">
        <span>已审批</span>
        <span>5</span>
      </div>
      <div class="stats-item" id="rejected-stats">
        <span>已拒绝</span>
        <span>2</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      approvals: [
        // 示例数据
        { name: '段', studentId: 'S001', className: '22软工2班', status: '待审批' },
        { name: '董', studentId: 'S002', className: '22网工2班', status: '待审批' },
        // ... 更多审批项
      ],
      selectedApproval: null, // 当前选中的审批项
    };
  },
  methods: {
    selectApproval(item) {
      this.selectedApproval = item;
    },
    approve() {
      if (this.selectedApproval) {
        this.selectedApproval.status = '已审批';
        // 这里可以添加更新后端状态的代码
      }
    },
    reject() {
      if (this.selectedApproval) {
        this.selectedApproval.status = '已拒绝';
        // 这里可以添加更新后端状态的代码
      }
    }
  }
};
</script>

<style scoped>
.container-top, .container-bottom {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2), inset 0 0 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  display: flex; /* 使用flex布局 */
  flex-direction: column; /* 子元素垂直排列 */
  justify-content: center; /* 垂直居中 */
}

.container-top {
  height: 63%;
  margin-bottom: 10px; /* 为container-top添加下边距 */
}

.container-bottom {
  height: 35%;
}

.title-top, .title-bottom {
  font-size: 28px;
  font-weight: bold;
  padding: 5px 10px;
}

.content {
  background-color: #f5f5f5; /* 设置背景颜色 */
  flex: 1; /* 使内容自适应父容器的高度 */
  display: flex; /* 使用flex布局使子元素横向排列 */
  justify-content: space-around; /* 在水平方向上均匀分布子元素 */
  padding: 10px;
  margin: 0 20px 10px;
  border-radius: 15px; /* 为内容添加圆角 */
}

.stats-item {
  flex: 1; /* 使每个统计项都可以伸缩，占据相等的空间 */
  text-align: center;
  padding: 20px;
  border-right: 1px solid #ccc;
  color: gray; /* 设置文字颜色为灰色 */
  font-size: 14px; /* 设置文字大小为14px */
}

.stats-item:last-child {
  border-right: none;
}

.stats-item span:last-child {
  display: block; /* 使数字在新的一行显示 */
  margin-top: 5px; /* 在数字和文字之间添加间距 */
  color: #007bff; /* 设置数字颜色为蓝色 */
  font-size: 32px; /* 设置数字字体大小为32px */
}

.approval-list {
  flex: 1;
  overflow-y: auto; /* 添加滚动条 */
  max-height: 100%; /* 限制最大高度 */
}

.approval-item {
  padding: 10px;
  border-bottom: 1px solid #ccc;
  cursor: pointer;
}

.approval-item.selected {
  background-color: #e9e9e9;
}

.approval-info span {
  margin-right: 10px;
}

.approval-details {
  flex: 1;
  padding: 10px;
  margin-left: 10px;
  border-left: 1px solid #ccc;
}

.approval-details button {
  margin-top: 10px;
  padding: 5px 10px;
  cursor: pointer;
}

.approval-details button:first-child {
  margin-right: 5px;
}

.approval-details button:last-child {
  background-color: #f44336;
  color: white;
}
</style>
