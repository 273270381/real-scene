import { remove, getList, save, isHaveInfo } from '@/api/modeldata/company'
import { isEmpty } from '@/utils/validate.js'
// 权限判断指令
import permission from '@/directive/permission/index.js'
import * as fecha from 'element-ui/lib/utils/date'

export default {
  directives: { permission },
  data() {
    // 验证手机号是否存在
    var validatePhoneExist = (rule, value, callback) => {
      if (!isEmpty(value)) {
        // 验证手机号是否存在
        this.isHaveInfo({ phone: value })
        if (this.isExist) {
          // true 存在重复手机号
          callback(new Error('手机号已经存在，请重试。'))
        }
      }
      callback()
    }

    // 验证企业名称是否存在
    var validateNameExist = (rule, value, callback) => {
      if (!isEmpty(value)) {
        // 验证名称是否存在
        this.isHaveInfo({ name: value })
        if (this.isExist) {
          // true 存在重复企业名称
          callback(new Error('企业名称已经存在，请重试。'))
        }
      }
      callback()
    }

    // 验证邮箱号是否存在
    var validateEmailExist = (rule, value, callback) => {
      if (!isEmpty(value)) {
        // 验证邮箱号是否存在
        this.isHaveInfo({ email: value })
        if (this.isExist) {
          // true 存在重复邮箱号
          callback(new Error('邮箱号已经存在，请重试。'))
        }
      }
      callback()
    }

    // 验证做记号/做记号也可以填手机号
    var validateLandLine = (rule, value, callback) => {
      if (!isEmpty(value)) {
        var rex = /^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/
        if (!rex.test(value)) {
          callback(new Error('请输入正确座机号格式为：0000-0000000或手机号'))
        }
      }
      callback()
    }

    return {
      formVisible: false,
      formTitle: '添加',
      isAdd: true,
      form: {
        id: '',
        name: '',
        userName: '',
        phone: '',
        address: '',
        email: '',
        fax: '',
        landLine: '', // 座机号码
        englishName: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入企业名称', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' },
          { required: true, validator: validateNameExist, trigger: 'blur' }
        ],
        phone: [
          // { required: true, message: '请输入手机号', trigger: 'blur' },
          { required: false, pattern: /^1[3456789]\d{9}$/, message: '请输入正确格式手机号', trigger: 'blur' }
          // { required: true, validator: validatePhoneExist, trigger: 'blur' }
        ],
        email: [
          // { required: true, message: '请输入邮箱号', trigger: 'blur' },
          { required: false, pattern: /[\w-]+(\.[\w-]+)*@([\w-]+\.)+\w{2,14}/, message: '请输入正确邮箱号', trigger: 'blur' },
          { required: false, validator: validateEmailExist, trigger: 'blur' }
        ],
        landLine: [
          { required: true, message: '请输入座机号', trigger: 'blur' },
          { required: true, pattern: /^\d{3,4}-?\d{7,9}$/, message: '请输入正确座机号格式为：0000-0000000', trigger: 'blur' }
          // /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,4}))?$/   座机号，不带格式
          // { required: true, validator: validateLandLine, trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1,
        limit: 20,
        name: undefined
      },
      total: 0,
      list: null,
      listLoading: true,
      selRow: {},
      isExist: false, // 是否存在
      isDisabled: false // 按钮是否可用
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
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.name = ''
      this.listQuery.page = 1
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
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
      this.$nextTick(() => {
        this.$refs['form'].resetFields()
      })
      // 重置data对象到初始化状formVisible态
      // Object.assign(this.$data, this.$options.data())
      this.form = this.$options.data().form
    },
    add() {
      this.resetForm()
      this.formTitle = '添加'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.isDisabled = true
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          var form = self.form
          form.createTime = undefined
          form.modifyTime = undefined
          save(form).then(response => {
            this.$message({
              message: this.$t('common.optionSuccess'),
              type: 'success'
            })
            this.fetchData()
            this.formVisible = false
          })
        } else {
          return false
        }
      })

      setTimeout(() => {
        this.isDisabled = false
      }, 2000)
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: this.$t('common.mustSelectOne'),
        type: 'warning'
      })
      return false
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '编辑'

        // 验证表单
        this.$nextTick(() => {
          // this.$refs['form'].resetFields()
          this.$refs['form'].validate((valid) => {
            return false
          })
        })

        this.formVisible = true
      }
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id
        this.$confirm(this.$t('common.deleteConfirm'), this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
          remove(id).then(response => {
            this.$message({
              message: this.$t('common.optionSuccess'),
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    },
    isHaveInfo(param) {
      // 验证信息是否存在
      this.isExist = false
      isHaveInfo(param).then(response => {
        if (response.success) {
          this.isExist = response.data
        }
        this.isExist = false
      })
    },
    dateFormat(dateTime) {
      return dateTime ? fecha.format(new Date(dateTime), 'yyyy-MM-dd HH:mm:ss') : ''
    }

  }
}
