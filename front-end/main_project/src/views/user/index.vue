<template>
  <div class="app-container">
    <div class="mb-5">
      <el-upload
        :action="importURL()"
        :show-file-list="false"
        accept="xlsx"
        :on-success="handleExcelImportSuccess"
        style="display: inline-block"
        class="mr-3"
      >
        <el-button type="primary" class="ml-5" size="mini" icon="el-icon-bottom">导入</el-button>
      </el-upload>
      <el-button
        size="mini"
        type="primary"
        icon="el-icon-top"
        @click="exportExcelBtn"
      >
        导出
      </el-button>
      <el-button
        size="mini"
        type="danger"
        icon="el-icon-delete"
        @click="delBatch"
      >
        批量删除
      </el-button>
    </div>
    <el-table
      :data="
        tableData.filter(
          (data) =>
            !search ||
            data.username.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
      class="mb-5"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      >
      </el-table-column>
      <el-table-column label="Id" prop="id"></el-table-column>
      <el-table-column label="用户名" prop="username"></el-table-column>
      <el-table-column label="昵称" prop="nickname"></el-table-column>
      <el-table-column label="电话" prop="phone"></el-table-column>
      <el-table-column label="邮箱" prop="email"></el-table-column>
      <el-table-column label="地址" prop="address"></el-table-column>
      <el-table-column label="角色">
        <template v-slot="scope">
          <el-tag
            size="medium"
            :type="scope.row.role === 'ROLE_ADMIN' ? 'success' : 'info'"
          >{{
              scope.row.role === 'ROLE_ADMIN' ? '管理员' : '普通用户'
            }}
          </el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime">
        <template v-slot="scope">
          {{ formatDate(scope.row.createTime) }}
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input v-model="search" size="mini" placeholder="输入用户名查找"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
          >编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page=this.pageNum
      :page-sizes="[5, 10, 25, 50]"
      :page-size=this.pageSize
      layout="total, sizes, prev, pager, next, jumper"
      :total="this.totalUser"
    >
    </el-pagination>
    <el-dialog title="编辑用户" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item
          label="昵称"
          :label-width="formLabelWidth"
          prop="nickname"
        >
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" :label-width="formLabelWidth" prop="role">
          <el-select v-model="form.role" placeholder="请选择角色">
            <el-option label="管理员" value="ROLE_ADMIN"></el-option>
            <el-option label="普通用户" value="ROLE_COMMON"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="警告"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <span>此操作将批量删除用户</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="confirm()">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import { getList, findOne, updateOne, deleteOne, deleteBatch, exportURL, importURL } from '@/api/table'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      listLoading: true,
      tableData: [],
      multipleSelection: [],
      dialogVisible: false,
      search: '',
      form: {},
      rules: {
        nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          {
            pattern:
              /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
            message: '请输入正确的电话号码',
            trigger: 'blur'
          }
        ],
        email: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        role: [{ required: true, message: '请输入地址', trigger: 'blur' }]
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      pageNum: 1,
      pageSize: 5,
      totalUser: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    importURL,
    formatDate(time, format = 'YY-MM-DD hh:mm:ss') {
      var date = new Date(time)

      var year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds()
      var preArr = Array.apply(null, Array(10)).map(function(elem, index) {
        return '0' + index
      })

      var newTime = format
        .replace(/YY/g, year)
        .replace(/MM/g, preArr[month] || month)
        .replace(/DD/g, preArr[day] || day)
        .replace(/hh/g, preArr[hour] || hour)
        .replace(/mm/g, preArr[min] || min)
        .replace(/ss/g, preArr[sec] || sec)

      return newTime
    },
    fetchData() {
      this.listLoading = true
      const params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      getList(params).then((res) => {
        if (res.code === '200') {
          const { records, total } = res.data
          this.tableData = records
          this.totalUser = total
          this.listLoading = false
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    confirm() {
      const ids = []
      this.multipleSelection.forEach(i => {
        ids.push(i.id)
      })
      if (!ids.length) {
        this.dialogVisible = false
        this.$message.warning('没有选中任何用户')
        return
      }
      deleteBatch(ids).then(res => {
        this.$message.success('批量删除成功')
        this.fetchData()
        this.dialogVisible = false
      })
    },
    handleEdit(index, row) {
      this.dialogFormVisible = true
      findOne(row.id).then((res) => {
        const { data } = res
        const { id, nickname, email, phone, address, role } = data
        this.form = {
          id,
          nickname,
          email,
          phone,
          address,
          role
        }
      })
    },
    handleDelete(index, row) {
      deleteOne(row.id).then(res => {
        this.$message.success('删除成功')
        this.fetchData()
      })
    },
    delBatch() {
      this.dialogVisible = true
    },
    handleExcelImportSuccess() {
      this.$message.success('导入成功')
      this.fetchData()
    },
    exportExcelBtn() {
      window.open(exportURL())
    },
    cancel() {
      this.$refs['form'].resetFields()
      this.dialogFormVisible = false
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.fetchData()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.fetchData()
    },
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updateOne(this.form).then((res) => {
            this.$message.success('修改成功')
            this.dialogFormVisible = false
            this.$refs['form'].resetFields()
            this.fetchData()
          })
        }
      })
    }
  }
}
</script>
