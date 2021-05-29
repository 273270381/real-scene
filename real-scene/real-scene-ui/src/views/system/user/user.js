import { deleteUser, getList, saveUser, remove, setRole, changeStatus, isHaveUserInfo } from '@/api/system/user'
import { list as deptList, getOptionsList } from '@/api/system/dept'
import { resetPwd } from '@/api/user'
import { parseTime } from '@/utils/index'
import { isEmpty } from '@/utils/validate'
import { roleTreeListByIdUser } from '@/api/system/role'
// 权限判断指令
import permission from '@/directive/permission/index.js'

export default {
  directives: { permission },
  data() {
    // 验证密码
    var validatePwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.form.rePassword !== '') {
          this.$refs.form.validateField('rePassword')
        }
        callback()
      }
    }
    // 验证重复密码
    var validateRePwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入重复密码'))
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }

    return {
      roleDialog: {
        visible: false,
        roles: [],
        roleTree: [],
        checkedRoleKeys: [],
        defaultProps: {
          id: 'id',
          label: 'name',
          children: 'children'
        }
      },
      passwordDialog: {
        visible: false
      },
      formVisible: false,
      formTitle: '添加用户',
      deptTree: {
        show: false,
        data: [],
        defaultProps: {
          id: 'id',
          label: 'simplename',
          children: 'children'
        }
      },
      isAdd: true,
      form: {
        id: '',
        account: '',
        name: '',
        birthday: '',
        phone: '',
        sex: 1,
        email: '',
        password: '',
        rePassword: '',
        dept: '',
        status: true,
        deptid: 1,
        deptName: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { required: true, min: 2, max: 16, message: '长度在2到16个数字、字母或汉字组合字符', trigger: 'blur' },
          { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在2到16个数字、字母或汉字组合字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 16, message: '长度在2到20个数字、字母或汉字组合字符', trigger: 'blur' },
          { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在2到20个数字、字母或汉字组合字符', trigger: 'blur' }
        ],
        email: [
          { required: false, pattern: /[\w-]+(\.[\w-]+)*@([\w-]+\.)+\w{2,14}/, message: '请输入正确邮箱号', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入正确格式手机号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在6到20个字符', trigger: 'blur' },
          { required: true, validator: validatePwd, trigger: 'blur' }
        ],
        rePassword: [
          { required: true, message: '请输入重复密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在6到20个字符', trigger: 'blur' },
          { required: true, validator: validateRePwd, trigger: 'blur' }
        ],
        deptName: [
          { required: true, message: '请输选择部门', trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1,
        limit: 20,
        account: undefined,
        name: undefined
      },
      total: 0,
      list: null,
      listLoading: true,
      selRow: {},
      isExist: false, // 是否存在
      selectId: [], // 部门选中的ID
      options: [], // 部门下拉框选择
      isDisabled: false, // 按钮是否可用
      Height: 600 // table高度

    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    formatDate: function(value) { // 日期格式过滤
      return parseTime(value, '{y}-{m}-{d} {h}:{i}:{s}')
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      deptList().then(response => {
        this.deptTree.data = response.data
      })
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })

      // 加载下拉框选项
      this.getOptionsData()
    },
    getOptionsData() {
      getOptionsList().then(response => {
        this.options = response.data
        // 去除数据空值
        this.removeArr(this.options)
      })
    },
    removeArr(list) {
      // 去除数据空值
      for (var i = 0; i < list.length; i++) {
        var item = list[i]
        if (!isEmpty(item['children']) && item['children'].length > 0) {
          this.removeArr(item.children)
        } else {
          delete item.children
        }
      }
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.account = ''
      this.listQuery.name = ''
      this.listQuery.page = 1
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page
      this.fetchData()
    },
    changeSize(limit) {
      this.listQuery.limit = limit
      this.fetchData()
    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      // 清除之前验证
      if (this.$refs['form'] !== undefined) {
        this.$nextTick(() => {
          this.$refs['form'].resetFields()
        })
      }

      // 重置表单数据
      this.form = this.$options.data().form

      // 清空级联选中样式
      this.resetCascader()
    },
    resetCascader() {
      // 清空级联选中样式
      if (this.$refs.cascader) {
        this.$refs.cascader.$refs.panel.activePath = []
        // this.$refs.cascader.$refs.panel.clearCheckedNodes()
        this.$refs.cascader.$refs.panel.calculateCheckedNodePaths()
      }

      // 清空级联选择器选中状态
      // this.$refs['cascader'].$refs['panel'].clearCheckedNodes()
      // 清除高亮
      // this.$refs['cascader'].$refs['panel'].activePath = []
    },
    add() {
      this.resetForm()
      this.selectId = []
      this.formTitle = '添加用户'
      this.formVisible = true
      this.isAdd = true
    },
    changeUserStatus(record) {
      changeStatus(record.id).then(response => {
        this.$message({
          message: '提交成功',
          type: 'success'
        })
        this.fetchData()
      })
    },
    selectOptionClick(data) {
      var option = this.$refs['cascader'].getCheckedNodes()[0].data
      this.form.deptid = option.value
      this.form.deptName = option.label
    },
    validPasswd() {
      if (!this.isAdd) {
        return true
      }
      if (this.form.password !== this.form.rePassword) {
        return false
      }
      if (this.form.password === '' || this.form.rePassword === '') {
        return false
      }
      return true
    },
    saveUser() {
      this.isDisabled = true
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.validPasswd()) {
            var form = self.form
            if (form.status === true) {
              // 启用
              form.status = 1
            } else {
              // 冻结
              form.status = 2
            }
            form.birthday = parseTime(form.birthday, '{y}-{m}-{d}')
            form.createtime = parseTime(form.createtime)
            form.dept = null
            saveUser(form).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          } else {
            this.$message({
              message: '提交失败',
              type: 'error'
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })

      setTimeout(() => {
        this.isDisabled = false
      }, 1000)
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false

        this.form = this.selRow

        this.form.deptid = this.form.dept.id
        this.form.deptName = this.form.dept.simplename

        this.form.status = this.selRow.statusName === '启用'
        this.form.password = ''
        this.formTitle = '修改用户'

        this.selectId = this.getDeptPids(this.form.dept.pids, this.form.dept.id)

        // 验证表单
        this.$nextTick(() => {
          // this.$refs['form'].resetFields()
          this.$refs['form'].validate((valid) => {
            return false
          })
        })

        // 清空级联选中样式
        this.resetCascader()

        this.formVisible = true
      }
    },
    getDeptPids(pids, myId) {
      // 获取父节点ids，逗号拼接
      var arr = pids.split(',')
      if (isEmpty(arr) || arr.length <= 1) {
        return
      }
      var result = []
      for (let i = 1; i < arr.length; i++) {
        if (isEmpty(arr[i])) {
          continue
        }
        var id = arr[i].replace('[', '').replace(']', '')
        result.push(Number(id))
      }
      // 自己的ID
      result.push(Number(myId))
      return result
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id

        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          remove(id).then(response => {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.fetchData()
          }).catch(err => {
            this.$notify.error({
              title: '错误',
              message: err
            })
          })
        }).catch(() => {
        })
      }
    },
    handleNodeClick(data, node) {
      this.form.deptid = data.id
      this.form.deptName = data.simplename
      this.deptTree.show = false
    },

    openRole() {
      if (this.checkSel()) {
        roleTreeListByIdUser(this.selRow.id).then(response => {
          this.roleDialog.roles = response.data.treeData
          this.roleDialog.checkedRoleKeys = response.data.checkedIds
          this.roleDialog.visible = true
        })
      }
    },
    setRole() {
      var checkedRoleKeys = this.$refs.roleTree.getCheckedKeys()
      var roleIds = ''
      for (var index in checkedRoleKeys) {
        roleIds += checkedRoleKeys[index] + ','
      }
      var data = {
        userId: this.selRow.id,
        roleIds: roleIds
      }
      setRole(data).then(response => {
        this.roleDialog.visible = false
        this.fetchData()
        this.$message({
          message: '提交成功',
          type: 'success'
        })
      })
    },
    isHaveUserInfoFuc(param) {
      // 验证用户信息是否存在
      this.isExist = false
      isHaveUserInfo(param).then(response => {
        if (response.success) {
          this.isExist = response.data
        }
      })
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return 'warning-row'
      }
      return ''
    },
    btKeyUp(e) {
      e.target.value = e.target.value.replace(/[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/g, '')
    },
    openPassword() {
      // 打开重置密码弹框
      if (this.checkSel()) {
        this.passwordDialog.visible = true

        // 清除之前验证
        if (this.$refs['form'] !== undefined) {
          this.$nextTick(() => {
            this.$refs['form'].resetFields()
          })
        }
      }
    },
    savePassword() {
      // 保存密码
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.validPasswd()) {
            var form = self.form
            form.id = self.selRow.id
            resetPwd(form).then(response => {
              if (response.success) {
                this.$message({
                  message: '重置成功',
                  type: 'success'
                })
                this.passwordDialog.visible = false
              } else {
                this.$message({
                  message: response.msg,
                  type: 'error'
                })
              }
            })
          } else {
            this.$message({
              message: '重置失败',
              type: 'error'
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }

  }
}
