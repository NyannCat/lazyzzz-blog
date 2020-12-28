<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="card">
          <div slot="header">
            <span>文章</span>
            <div class="right-item" @click="toArticleEditor"><i class="el-icon-plus"/></div>
          </div>
          <h2>{{ data.articleCount }}</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="card">
          <div slot="header">
            <span>留言</span>
            <div class="right-item" @click="toComment"><i class="el-icon-s-order"/></div>
          </div>
          <h2>{{ data.commentCount }}</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="card">
          <div slot="header">
            <span>阅读量</span>
            <div class="right-item"><i class="el-icon-info"/></div>
          </div>
          <h2>{{ data.totalViews }}</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="card">
          <div slot="header">
            <span>建立天数</span>
            <div class="right-item"><i class="el-icon-info"/></div>
          </div>
          <h2>{{ data.duration }}</h2>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="8">
        <el-card>
          <div slot="header"><span>最新评论</span></div>
          <div v-for="o in data.comments.records" :key="o.id" style="margin-bottom: 10px">
            {{ o.content }}
            <div class="right-item">
              {{ o.createTime }}
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-card>
          <div slot="header"><span>操作记录</span></div>
          <el-table :data="logs.records" border fit highlight-current-row>
            <el-table-column align="center" label="ID">
              <template slot-scope="scope">
                {{ scope.row.id }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="IP">
              <template slot-scope="scope">
                {{ scope.row.ip }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="国家">
              <template slot-scope="scope">
                {{ scope.row.country }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="省份">
              <template slot-scope="scope">
                {{ scope.row.province }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="城市">
              <template slot-scope="scope">
                {{ scope.row.city }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="描述">
              <template slot-scope="scope">
                {{ scope.row.description }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="时间">
              <template slot-scope="scope">
                {{ scope.row.createTime }}
              </template>
            </el-table-column>
          </el-table>
          <div style="display: block;text-align: center">
            <el-pagination
              :page-size="logs.size"
              :total="logs.total"
              background
              layout="total, prev, pager, next"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange">
            </el-pagination>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import {infoDashboard, logs} from '@/api/statistics'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  created() {
    infoDashboard().then(resp => {
      this.data = resp.data
    })
    this.getLogs()
  },
  data() {
    return {
      data: {
        articleCount: 0,
        totalViews: 0,
        commentCount: 0,
        duration: 0,
        comments: {
          records: [{
            id: 0,
            articleId: 0,
            content: '',
            createTime: ''
          }]
        }
      },
      logs: {
        records: [{
          id: 0,
          ip: 0,
          country: '',
          province: '',
          city: '',
          description: '',
          createTime: '',
        }],
        current: 1,
        size: 10,
        total: 0,
      }
    }
  },
  methods: {
    getLogs() {
      logs({
        page: this.logs.current,
        size: this.logs.size
      }).then(resp => {
        this.logs = resp.data
      })
    },
    handleSizeChange(size) {
      this.logs.size = size
      this.getLogs()
    },
    handleCurrentChange(current) {
      this.logs.current = current
      this.getLogs()
    },
    toArticleEditor() {
      this.$router.push('/article/editor')
    },
    toComment() {
      this.$router.push('/comment/table')
    }
  }
}
</script>

<style lang="scss" scoped>
.card {
  height: 150px;
}

.right-item {
  float: right;
  cursor: pointer;
}

.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
