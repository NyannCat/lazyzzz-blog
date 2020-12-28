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
      <el-table-column label="文章标题" prop="title" sortable width="200">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="阅读量" prop="views" sortable width="110">
        <template slot-scope="scope">
          {{ scope.row.views }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="点赞数" prop="likes" sortable width="110">
        <template slot-scope="scope">
          {{ scope.row.likes }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="分享数" prop="shares" sortable width="110">
        <template slot-scope="scope">
          {{ scope.row.shares }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="评论数" prop="comments" sortable width="110">
        <template slot-scope="scope">
          {{ scope.row.comments }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="文章类型" prop="type" sortable width="110">
        <template slot-scope="scope">
          <el-tag :type="scope.row.type | statusFilter" size="medium">{{ scope.row.type == 0 ? '公开' : '私密' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="文章分类" prop="categoryName" sortable width="110">
        <template slot-scope="scope">
          {{ scope.row.categoryName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" sortable width="175">
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" prop="updateTime" sortable width="175">
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <!--右侧操作栏-->
      <el-table-column align="center">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="keyword"
            placeholder="输入关键字搜索"
            size="mini"/>
        </template>
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="openUrl(scope.row.id)">查看</el-button>
          <el-button size="small" type="text" @click="handleEdit(scope.row.id)">编辑</el-button>
          <el-button size="small" type="text" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="display: block;text-align: center">
      <el-pagination
        :page-size="data.size"
        :total="data.total"
        background
        layout="total, prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {deleteArticleById, pageBy} from '@/api/article'

export default {
  name: "index",
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'gray',
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      keyword: '',
      data: {
        records: [{
          id: 0,
          title: '',
          views: 0,
          likes: 0,
          shares: 0,
          comments: 0,
          type: 0,
          createTime: '',
          updateTime: '',
          categoryName: ''
        }],
        total: 0,
        size: 10,
        current: 1,
      },
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      pageBy({
        page: this.data.records.current,
        size: this.data.size
      }).then(resp => {
        this.data = resp.data
        this.listLoading = false
      })
    },
    openUrl(id) {
      const url = process.env.VUE_APP_SERVER_HOST + '/article/' + id
      window.open(url, '_blank')
    },
    handleEdit(id) {
      console.log(id)
      this.$router.push({
        path: '/article/editor',
        query: {
          articleId: id
        }
      })
    },
    handleDelete(id) {
      this.$confirm('此操作将删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteArticleById({
          id: id
        }).then(resp => {
          this.$message.success('文章删除成功')
        })
      })
    },
    handleSizeChange(size) {
      this.data.size = size
      this.fetchData()
    },
    handleCurrentChange(current) {
      this.data.current = current
      this.fetchData()
    }
  }
}
</script>

<style scoped>
i {
  padding-right: 5px;
}
</style>
