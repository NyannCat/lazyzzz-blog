<template>
  <div class="install-container">
    <div class="step-container">
      <el-steps :active="active" align-center finish-status="success">
        <el-step icon="el-icon-edit" title="博客安装"/>
      </el-steps>
      <div v-if="active === 0">
        <el-form v-model="userForm">
          <el-form-item label="用户名" required>
            <el-input v-model="userForm.username"/>
          </el-form-item>
          <el-form-item label="密码" required>
            <el-input v-model="userForm.password"/>
          </el-form-item>
          <el-form-item label="邮箱" required>
            <el-input v-model="userForm.email"/>
          </el-form-item>
        </el-form>
      </div>
      <el-button style="float: right;margin: 10px" type="primary" @click="next">下一步</el-button>
    </div>
  </div>
</template>

<script>
import {register} from '@/api/user'

export default {
  name: "index",
  data() {
    return {
      active: 0,
      userForm: {
        username: '',
        password: '',
        email: ''
      }
    }
  },
  methods: {
    next() {
      register(this.userForm)
        .then(resp => {
          this.$message.success(resp.msg)
          this.active++;
          this.$router.push({path: '/login'})
        })
    }
  }
}
</script>

<style scoped>
.install-container {
  min-height: 100%;
  width: 100%;
  overflow: hidden;
  background: url("../../assets/bg.png");
}

.step-container {
  width: 520px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
