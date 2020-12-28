<template>
  <div style="text-align: center">
    <el-card>
      <el-row :gutter="10">
        <el-col :span="1">
          <el-button type="primary" @click="visible=true">上传</el-button>
        </el-col>
        <el-col :span="4">
          <el-select v-model="args.type" placeholder="请选择上传策略">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-col>
        <el-col :span="6">
          <el-input v-model="keyword" placeholder="请输入关键词" suffix-icon="el-icon-search"
                    @keyup.enter.native="getImage(data.current, data.size, keyword)"/>
        </el-col>
      </el-row>
    </el-card>
    <div v-if="data.total === 0" style="text-align: center;padding-top: 50px">
      暂无数据
    </div>
    <el-row id="uploads" :gutter="20" style="padding: 10px">
      <el-col v-for="(record, index) in data.records" :key="index" :span="3">
        <el-card :body-style="{ padding: '0px' }" class="image-container">
          <el-image :src="record.path" fit="cover" style="height: 160px;" @click="handleDrawer(index)"/>
          <div style="padding: 14px">
            <span style="font-size: 12px;color: #999">{{ record.name }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <div style="display: block;text-align: center">
      <el-pagination
        :page-size="data.size"
        :page-sizes="[16, 32, 48]"
        :total="data.total"
        background
        layout="sizes, prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>
    <el-dialog
      :visible.sync="visible"
      title="上传文件">
      <el-upload
        :action="action"
        :data="args"
        :on-success="handleSuccess"
        :with-credentials="true"
        :headers="headers"
        drag multiple>
        <i class="el-icon-upload"></i>
        <div>将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
    </el-dialog>
    <el-drawer :append-to-body="true" :visible.sync="sync" direction="rtl" title="附件详情">
      <el-image :src="record.path" style="height: 50%"/>
      <el-divider><i class="el-icon-picture-outline-round"/></el-divider>
      <el-card style="height: 50%">
        <div class="item text">
          <div>名称</div>
          {{ record.name }}
        </div>
        <div class="item text">
          <div>类型</div>
          {{ record.mediaType }}
        </div>
        <div class="item text">
          <div>引用连接</div>
          <el-link
            :href="record.path"
            type="primary">
            {{ record.path }}
          </el-link>
        </div>
        <div style="right: 0;bottom: 0;position: absolute;padding: 20px">
          <el-button type="danger" @click="handleDelete(record.id)">删除</el-button>
        </div>
      </el-card>
    </el-drawer>
  </div>
</template>

<script>
import {deleteBy, pageBy} from '@/api/upload';
import { getToken } from '@/utils/auth';

export default {
  name: "index",
  created() {
    this.getImage(this.data.current, this.data.size, '')
  },
  data() {
    return {
      "headers": {
        Authorization: getToken()
      },
      "options": [{
        value: 'LOCAL',
        label: '本地'
      }, {
        value: 'QI_NIU_YUN',
        label: '七牛云'
      }],
      "args": {
        'type': 'LOCAL'
      },
      "data": {
        "records": [
          {
            "id": 1,
            "name": "",
            "path": "",
            "fileKey": "",
            "mediaType": "",
            "width": 0,
            "height": 0,
            "attachmentType": 0
          },
        ],
        "total": 0,
        "size": 16,
        "current": 1,
        "pages": 1
      },
      "record": {
        id: '',
        name: '',
        path: '',
        mediaType: '',
        width: 0,
        height: 0,
        attachmentType: ''
      },
      'action': process.env.VUE_APP_SERVER_HOST + '/api/admin/attachment',
      "visible": false,
      "keyword": "",
      "sync": false
    }
  },
  methods: {
    getImage(page, size, keyword) {
      pageBy({
        'page': page,
        'size': size,
        'keyword': keyword
      }).then(resp => {
        this.data = resp.data
        this.data.records.forEach((value, index) => {
          if (value.attachmentType === 0) {
            value.path = process.env.VUE_APP_SERVER_HOST + value.path
          }
        })
      })
    },
    handleSuccess(resp) {
      if (resp.code !== 200) {
        this.$message.error('上传失败, ' + resp.msg)
      } else {
        this.$message.success('上传成功')
        this.getImage(this.data.current, this.data.size, '')
      }
    },
    handleCurrentChange(val) {
      this.data.current = val
      this.getImage(val, this.data.size, this.keyword)
    },
    handleSizeChange(val) {
      this.data.size = val
      this.getImage(1, val, this.keyword)
    },
    handleDrawer(index) {
      this.record = this.data.records[index]
      this.sync = true
    },
    handleDelete(id) {
      this.$confirm('此操作将删除该附件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteBy({
          id: id
        }).then(resp => {
          this.$message.success('附件删除成功')
          this.getImage(this.data.current, this.data.size, '')
        })
      })
    }
  }
}
</script>

<style scoped>
/*去除Drawer header预选择特效*/
/deep/ :focus {
  outline: 0 !important;
}

.image-container {
  height: 220px;
  margin-bottom: 20px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
</style>
