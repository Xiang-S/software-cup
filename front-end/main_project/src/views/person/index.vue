<template>
  <el-card style="width: 500px;height: 90%" class="ml-28 mt-10">
    <el-form label-width="80px" size="small">
      <el-upload
        class="avatar-uploader"
        :action="uploadAvatarURL()"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatarUrl" :src="getBaseUrl() + form.avatarUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>

      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input type="textarea" v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { uploadAvatarURL } from '@/api/user'
import { getInfo, saveInfo } from '@/api/user'

export default {
  name: 'Person',
  data() {
    return {
      form: {},
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {}
    }
  },
  created() {
    getInfo(this.user.username).then(res => {
      const { data } = res
      this.form = data
    })
  },
  methods: {
    uploadAvatarURL,
    save() {
      saveInfo(this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('保存成功')
          // 更新浏览器存储的用户信息
          getInfo(this.form.username).then(res => {
            const { data } = res
            data.token = JSON.parse(localStorage.getItem('user')).token
            localStorage.setItem('user', JSON.stringify(data))
          })
        } else {
          this.$message.error('保存失败')
        }
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res
      this.$message.success('修改成功')
    }
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}

.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>
