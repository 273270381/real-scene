import { remove, getList, save } from '@/api/modeldata/camera'
import { isEmpty } from '@/utils/validate.js'
// 权限判断指令
import permission from '@/directive/permission/index.js'
import * as fecha from 'element-ui/lib/utils/date'

export default {
  directives: { permission },
  data() {
    return {
      formVisible: false,
      formTitle: '添加',
      isAdd: true,
      form: {
        id: '',
        typeName: '海康球机',
        url: '',
        address: '',
        ip: '',
        port: '',
        cloudIp: '',
        cloudPort: '',
        loginName: '',
        loginPassword: '',
        createTime: ''
      },
      rules: {
        typeName: [
          { required: true, message: '请输入类型名称', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' },
          { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
        ],
        ip: [
          { required: true, message: '请输入ip', trigger: 'blur' },
          { pattern: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/, message: '请输入正确格式ip', trigger: 'blur' }
        ],
        port: [
          { required: true, message: '请输入端口', trigger: 'blur' },
          { pattern: /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/, message: '请输入正确端口号', trigger: 'blur' }
        ],
        cloudIp: [
          { required: false, pattern: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/, message: '请输入正确格式ip', trigger: 'blur' }
        ],
        cloudPort: [
          { required: false, pattern: /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/, message: '请输入正确端口号', trigger: 'blur' }
        ],
        loginName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        loginPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入视频流地址', trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1,
        limit: 20,
        address: undefined
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
      this.listQuery.address = ''
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
