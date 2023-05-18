<template>
  <div class="app-container">
    <div class="my-4">
      <el-button type="primary" @click="addUser">添加用户</el-button>
    </div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="序号"
        prop="date">
      </el-table-column>
      <el-table-column
        label="用户名"
        prop="name">
      </el-table-column>
      <el-table-column
        label="昵称"
        prop="name">
      </el-table-column>
      <el-table-column
        label="电话"
        prop="date">
      </el-table-column>
      <el-table-column
        label="地址"
        prop="date">
      </el-table-column>
      <el-table-column
        label="创建时间"
        prop="date">
      </el-table-column>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="Type to search"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="formType==='add'?'添加用户':'编辑用户'" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" :label-width="formLabelWidth" prop="nickName">
          <el-input v-model="form.nickName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="tel">
          <el-input v-model="form.tel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth" prop="addr">
          <el-input v-model="form.addr" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getList} from '@/api/table'

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
      list: null,
      listLoading: true,
      tableData: [{
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-02',
        name: 'John',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-04',
        name: 'Morgan',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }],
      search: '',
      form: {
        name: '',
        nickName: '',
        tel: '',
        addr: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '请输入昵称', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/, message: '请输入正确的电话号码',trigger: 'blur'}
        ],
        addr: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      formType:''
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        this.list = response.data.items
        this.listLoading = false
      })
    },
    handleEdit(index, row) {
      this.formType = 'edit'
      this.dialogFormVisible = true

      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    addUser(){
      this.formType = 'add'
      this.dialogFormVisible = true
    },
    cancel(){
      this.$refs['form'].resetFields();
      this.dialogFormVisible = false
    },
    submit(){
      if(this.formType === 'add'){
        this.$refs['form'].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        return
      }

      this.$refs['form'].resetFields();
      this.dialogFormVisible = false
    }
  }
}
</script>
