<template>
  <div id="app-container">
    <div id="markdown-container">
      <mavon-editor
        ref="md"
        v-model="form.originalContent"
        style="min-height: 600px"
        @change="change"/>
    </div>
    <div id="form-container">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" size="medium">
        <el-form-item label="文章标题" prop="title" required>
          <el-input v-model="form.title"/>
        </el-form-item>
        <el-form-item label="文章摘要" prop="summary" required>
          <el-input v-model="form.summary" type="textarea"/>
        </el-form-item>
        <el-form-item label="文章封面" prop="coverPage" required>
          <el-input v-model="form.coverPage"/>
        </el-form-item>
        <el-form-item label="文章分类" required>
          <el-select v-model="form.categoryId" :loading="loading" :remote-method="remote" placeholder="请选择文章分类" remote>
            <el-option v-for="item in categories" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="私密">
          <el-switch v-model="form.type" active-color="#13ce66" active-value="1" inactive-value="0"/>
        </el-form-item>
        <el-form-item style="text-align: right">
          <el-button type="primary" @click="submit('form')">提交</el-button>
          <el-button type="info" @click="onDrawer">附件库</el-button>
        </el-form-item>
      </el-form>
      <el-drawer
        :visible.sync="drawer"
        direction="rtl"
        title="附件库">
        <attachment-drawer/>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import AttachmentDrawer from "@/views/attachment/drawer";
import {getCategoryList} from "@/api/category";
import {submit, getArticleById} from "@/api/article";

export default {
  name: "index",
  created() {
    this.remote()
    const id = this.$route.query.articleId
    console.log(id)
    if (id !== undefined) {
      getArticleById({
        id: id
      }).then(resp => {
        this.form = resp.data
      })
    }
  },
  data() {
    return {
      drawer: false,
      direction: 'rtl',
      form: {
        originalContent: '',
        formatContent: '',
        title: '',
        coverPage: '',
        type: 0,
        summary: '',
        categoryId: '',
      },
      categories: [{
        id: 1,
        name: ''
      }],
      rules: {
        title: [{required: true, message: '文章标题不能为空'}],
        summary: [{required: true, message: '文章摘要不能为空'}],
        coverPage: [{required: true, message: '文章封面不能为空'}]
      },
      loading: true,
    }
  },
  methods: {
    change(value, render) {
      this.form.formatContent = render
    },
    submit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          submit(this.form).then(resp => {
            this.$message.success('文章上传成功')
          })
        } else {
          this.$message.error("请完整填写表单")
        }
      })
    },
    onDrawer() {
      this.drawer = true
    },
    remote() {
      this.loading = true
      getCategoryList().then(resp => {
        this.categories = resp.data
        this.loading = false
      })
    }
  },
  components: {
    AttachmentDrawer,
    "mavon-editor": mavonEditor
  }
}
</script>

<style scoped>
/deep/ :focus {
  outline: 0 !important;
}

#markdown-container {
  padding: 10px;
}

#form-container {
  padding: 10px;
}
</style>
