<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="data.records"
      border
      element-loading-text="Loading"
      fit
      highlight-current-row>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="评论内容" prop="content" sortable>
        <template slot-scope="scope">
          {{ scope.row.content }}
        </template>
      </el-table-column>
      <el-table-column label="评论人昵称" prop="nickname" sortable>
        <template slot-scope="scope">
          {{ scope.row.nickname }}
        </template>
      </el-table-column>
      <el-table-column label="评论人邮箱" prop="email" sortable>
        <template slot-scope="scope">
          {{ scope.row.email }}
        </template>
      </el-table-column>
      <el-table-column label="评论时间" prop="email" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员评论" prop="email" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.isAdmin === 0 ? '否' : '是' }}</span>
        </template>
      </el-table-column>
      <!--右侧操作栏-->
      <el-table-column align="center" width="200">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="keyword"
            placeholder="输入关键字搜索"
            size="mini"/>
        </template>
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="handleDetail(scope.row.articleId)">查看</el-button>
          <el-button size="small" type="text" @click="handleOpenWindows(scope.row.articleId, scope.row.id)">回复
          </el-button>
          <el-button size="small" type="text" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="visible"
      title="评论回复">
      <el-input
        v-model="replyMsg"
        :rows="2"
        placeholder="请输入内容"
        type="textarea">
      </el-input>
      <span slot="footer" class="dialog-footer">
    <el-button @click="visible=false">取消</el-button>
    <el-button type="primary" @click="handleReply">确定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {deleteArticleById, pageBy, reply} from "@/api/comment";

export default {
  name: "index",
  created() {
    this.getList()
  },
  data() {
    return {
      data: {
        records: [{
          id: 0,
          content: '',
          nickname: '',
          email: '',
          createTime: '',
          articleId: '',
          parentId: '',
          isAdmin: 0
        }],
        total: 0,
        size: 10,
        current: 1,
      },
      keyword: '',
      replyArticleId: 0,
      replyParentId: 0,
      replyMsg: '',
      listLoading: true,
      visible: false,
    }
  },
  methods: {
    getList() {
      this.listLoading = true
      pageBy({
        page: this.data.current,
        size: this.data.size,
      }).then(resp => {
        this.data = resp.data
        this.listLoading = false
      })
    },
    handleDetail(articleId) {
      const url = process.env.VUE_APP_SERVER_HOST + '/article/'+  articleId
      window.open(url, '_blank')
    },
    handleOpenWindows(articleId, commentId) {
      this.visible = true
      this.replyArticleId = articleId
      this.replyParentId = commentId
    },
    handleReply() {
      reply({
        'content': this.replyMsg,
        'articleId': this.replyArticleId,
        'parentId': this.replyParentId,
      }).then(resp => {
        this.$message.success(resp.msg)
        this.replyMsg = ''
      })
      this.visible = false
    },
    handleDelete(commentId) {
      this.$confirm('此操作将删除该评论及其子评论, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteArticleById({
          id: commentId
        }).then(resp => {
          this.$message.success(resp.msg)
        })
      })
    }
  }
}
</script>

<style scoped>
i {
  padding-right: 5px;
}
</style>
