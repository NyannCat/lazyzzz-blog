<template>
  <div class="app-container">
    <el-form :inline="true" :model="form">
      <el-form-item label="分类名称">
        <el-input v-model="form.name"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">新建分类</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="records"
      border
      element-loading-text="Loading"
      fit
      highlight-current-row>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="分类名称" prop="name" sortable>
        <template slot-scope="scope">
          {{ scope.row.name }}
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
          <el-button size="small" type="text"  @click="handleDialog(scope.row.id, scope.row.name)">编辑</el-button>
          <el-button size="small" type="text" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="修改分类" :visible.sync="visible">
      <el-form v-model="updateForm" :inline="true">
        <el-form-item label="分类名称">
          <el-input v-model="updateForm.name"/>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="visible=false">取消</el-button>
        <el-button type="primary" @click="handleEdit">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {getCategoryList, createCategory, updateCategory, deleteCategory} from '@/api/category'

export default {
  name: "index",
  created() {
    this.getList()
  },
  data() {
    return {
      form: {
        name: ''
      },
      updateForm: {
        id: '',
        name: ''
      },
      records: [{
        id: 0,
        name: '',
      }],
      keyword: '',
      listLoading: true,
      visible: false,
    }
  },
  methods: {
    getList() {
      this.listLoading = true
      getCategoryList().then(resp => {
        this.records = resp.data
        this.listLoading = false
      })
    },
    submit() {
      createCategory({
        name: this.form.name
      }).then(resp => {
        this.getList()
        this.$message.success(resp.msg)
      })
    },
    handleDialog(id, name) {
      this.updateForm.id = id
      this.updateForm.name = name
      this.visible = true
    },
    handleEdit() {
      updateCategory({
        'id': this.updateForm.id,
        'name': this.updateForm.name
      }).then(resp => {
        this.getList()
        this.$message.success(resp.msg)
        this.visible = false
      })
    },
    handleDelete(id) {
      this.$confirm('此操作将删除该分类，若存在引用则置为未分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCategory({
          id: id
        }).then(resp => {
          this.getList()
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
