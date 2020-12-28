<template>
  <div>
    <div class="input-container">
      <el-input
        v-model="keyword"
        placeholder="请输入关键词"
        suffix-icon="el-icon-search"
        @keyup.enter.native="getImages"/>
    </div>
    <div v-if="data.total === 0" style="text-align: center;padding-top: 30px;padding-bottom: 30px">
      暂无数据
    </div>
    <el-row>
      <el-col v-for="(record, index) in data.records" :key="index" :span="8">
        <div class="image-container">
          <el-image :src="record.path" fit="center" style="height:80px" @click="drawerHandler(index)"/>
        </div>
      </el-col>
    </el-row>
    <div style="display: block;text-align: center">
      <el-pagination
        :page-size="data.size"
        :total="data.total"
        :current-page="data.current"
        layout="prev, pager, next"
        @current-change="handleCurrentChange" />
    </div>
    <el-drawer :append-to-body="true" :visible.sync="drawer" direction="rtl" title="附件详情">
      <el-image :src="record.path" style="height: 50%"/>
      <el-divider><i class="el-icon-picture-outline-round"/></el-divider>
      <el-card>
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
      </el-card>
    </el-drawer>
  </div>
</template>

<script>
import {pageBy} from "@/api/upload";

export default {
  name: "AttachmentDrawer",
  created() {
    this.getImages()
  },
  data() {
    return {
      "drawer": false,
      "keyword": "",
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
        "size": 9,
        "current": 1,
        "pages": 1
      },
      record: {
        name: '',
        path: '',
        mediaType: '',
        width: 0,
        height: 0,
        attachmentType: ''
      },
    }
  },
  methods: {
    drawerHandler(index) {
      this.record = this.data.records[index]
      this.drawer = true
    },
    getImages() {
      pageBy({
        'page': this.data.current,
        'size': this.data.size,
        'keyword': this.keyword
      }).then(resp => {
        this.data = resp.data
        this.data.records.forEach((value, index) => {
          if (value.attachmentType === 0) {
            value.path = process.env.VUE_APP_SERVER_HOST + value.path
          }
        })
      })
    },
    handleCurrentChange(val) {
      this.data.current = val
      this.getImages()
    }
  }
}
</script>

<style scoped>
/*去除Drawer header预选择特效*/
/deep/ :focus {
  outline: 0 !important;
}

.input-container {
  padding: 10px;
}

.image-container {
  padding: 10px;
  height: 100px;
  margin-bottom: 10px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
</style>
