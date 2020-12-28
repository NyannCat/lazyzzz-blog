<template>
  <div class="container" style="padding: 20px">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-card>
          <el-image :src="data.user.avatar" class="avatar" fit="fit"/>
          <h3 style="text-align: center">{{ data.user.username }}</h3>
          <div style="font-size: 80%">
            <p><i class="el-icon-message"></i>{{ data.user.email }}</p>
            <p><i class="el-icon-medal-1"></i>{{ data.duration }} 天</p>
          </div>
          <div style="font-size: 80%">
            <el-divider/>
            <div>累计发表了 {{ data.articleCount }} 篇文章</div>
            <el-divider/>
            <div>累计创建了 {{ data.categoryCount }} 篇分类</div>
            <el-divider/>
            <div>累计获得了 {{ data.commentCount }} 个评论</div>
            <el-divider/>
          </div>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-card>
          <div slot="header">
            <span>个人资料</span>
          </div>
          <el-tabs>
            <el-tab-pane>
              <span slot="label"><i class="el-icon-user-solid"/>基本信息</span>
              <el-form ref="basicForm" :model="basicForm">
                <el-form-item label="用户名" prop="username" required>
                  <el-input v-model="basicForm.username"/>
                </el-form-item>
                <el-form-item label="邮箱" prop="email" required>
                  <el-input v-model="basicForm.email"/>
                </el-form-item>
                <el-form-item label="头像" prop="avatar" required>
                  <el-input v-model="basicForm.avatar"/>
                </el-form-item>
                <el-form-item label="个人简介" prop="description">
                  <el-input v-model="basicForm.description" type="textarea"/>
                </el-form-item>
                <el-form-item style="text-align: right">
                  <el-button type="primary" @click="handleUpdateBasicInfo">保存</el-button>
                  <el-button type="info" @click="drawer=true">附件库</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label"><i class="el-icon-lock"/>密码</span>
              <el-form ref="passwordForm" :model="passwordForm">
                <el-form-item label="原密码" required>
                  <el-input v-model="passwordForm.pwd" type="password"/>
                </el-form-item>
                <el-form-item label="新密码" required>
                  <el-input v-model="passwordForm.new_pwd" type="password"/>
                </el-form-item>
                <el-form-item label="确认密码" required>
                  <el-input v-model="passwordForm.new_pwd_check" type="password"/>
                </el-form-item>
                <el-form-item>
                  <el-button style="float: right" type="primary" @click="handleUpdatePassword">保存</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
    <el-drawer
      :visible.sync="drawer"
      direction="rtl"
      title="附件库">
      <attachment-drawer/>
    </el-drawer>
  </div>
</template>

<script>
import AttachmentDrawer from "@/views/attachment/drawer";
import {user} from '@/api/statistics'
import {updateBasicInfo, updatePassword} from "@/api/user";

export default {
  name: "index",
  created() {
    user().then(resp => {
      this.data = resp.data
      const user = this.data.user
      this.basicForm.username = user.username
      this.basicForm.email = user.email
      this.basicForm.avatar = user.avatar
      this.basicForm.description = user.description
      this.data.user.avatar = process.env.VUE_APP_SERVER_HOST + user.avatar
    })
  },
  data() {
    return {
      data: {
        articleCount: 0,
        categoryCount: 0,
        commentCount: 0,
        duration: 0,
        user: {
          id: 1,
          username: '',
          email: '',
          avatar: '',
          description: '',
        }
      },
      basicForm: {
        username: '',
        email: '',
        avatar: '',
        description: '',
      },
      passwordForm: {
        pwd: '',
        new_pwd: '',
        new_pwd_check: '',
      },
      drawer: false,
    }
  },
  methods: {
    handleUpdateBasicInfo() {
      this.$refs['basicForm'].validate(valid => {
        if (valid) {
          updateBasicInfo(this.basicForm).then(resp => {
            this.$message.success(resp.msg)
          })
        }
      })
    },
    handleUpdatePassword() {
      this.$refs['passwordForm'].validate(valid => {
        if (valid && this.passwordForm.new_pwd === this.passwordForm.new_pwd_check) {
          updatePassword({
            'oldPassword': this.passwordForm.pwd,
            'newPassword': this.passwordForm.new_pwd
          }).then(resp => {
            this.$message.success(resp.msg)
          })
        } else {
          this.$message.error("新密码不一致")
        }
      })
    }
  },
  components: {
    AttachmentDrawer,
  }
}
</script>

<style scoped>
i {
  padding-right: 10px;
}

.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  overflow: hidden;
  left: 50%;
  transform: translateX(-50%);
}

/deep/ :focus {
  outline: 0 !important;
}
</style>
