import treeTable from '@/components/TreeTable'
import { list, save, del, getOptionsList } from '@/api/system/dept'
import { isEmpty } from '@/utils/validate.js'

export default {
  name: 'customTreeTableDemo',
  components: { treeTable },
  data() {
    return {
      expandAll: true,
      data: [],
      formVisible: false,
      formTitle: '',
      isAdd: false,

      showTree: false,
      defaultProps: {
        id: 'id',
        label: 'simplename',
        children: 'children'
      },
      form: {
        id: '',
        simplename: '',
        fullname: '',
        pid: '',
        num: 1,
        tips: ''
      },
      selectId: [],
      rules: {
        simplename: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' },
          { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' }
        ],
        fullname: [
          { required: true, message: '请输入部门全称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' },
          { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '请输入排序', trigger: 'blur' },
          { pattern: /^[1-9]\d{0,3}|10000$/, message: '请输入1-10000正整数', trigger: 'change' }
          // { pattern: /^[1-9]\d*$/, message: '请输入1-10000正整数', trigger: 'change' }
        ]
      },
      options: [], // 部门下拉框选择
      optionsBack: [], // 部门下拉框选择（备份数据）
      isDisabled: false

    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list().then(response => {
        this.data = response.data
        this.listLoading = false
      })

      // 加载下拉框选项
      this.getOptionsData()
    },
    getOptionsData() {
      getOptionsList().then(response => {
        this.options = response.data
        // 去除数据空值
        this.removeArr(this.options)
        this.optionsBack = this.options
      })
    },
    removeArr(list) {
      // 去除数据空值
      for (var i = 0; i < list.length; i++) {
        var item = list[i]
        if (!isEmpty(item.children) && item.children.length > 0) {
          this.removeArr(item.children)
        } else {
          delete item.children
        }
      }
    },
    handleNodeClick(data, node) {
      console.log(data)
      this.form.pid = data.id
      this.form.pname = data.simplename
      this.showTree = false
    },
    selectOptionClick(data) {
      var option = this.$refs['cascader'].getCheckedNodes()[0].data
      this.form.pid = option.value
      this.form.pname = option.label
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
    resetForm() {
      // 清除之前验证
      if (this.$refs['form'] !== undefined) {
        this.$nextTick(() => {
          this.$refs['form'].resetFields()
        })
      }

      // 重置data对象到初始化状formVisible态
      // Object.assign(this.$data, this.$options.data())
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
    },
    add() {
      this.resetForm()
      this.options = JSON.parse(JSON.stringify(this.optionsBack))
      this.selectId = []
      this.formTitle = '添加部门'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.isDisabled = true
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log('form', self.form)
          const menuData = { id: self.form.id, simplename: self.form.simplename, fullname: self.form.fullname, num: self.form.num, pid: self.form.pid, tips: self.form.tips }// self.form
          menuData.parent = null
          save(menuData).then(response => {
            console.log(response)
            if (response.success) {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              self.fetchData()
              self.formVisible = false
            } else {
              this.$message({
                message: response.msg,
                type: 'error'
              })
            }
          })
        } else {
          return false
        }
      })

      setTimeout(() => {
        this.isDisabled = false
      }, 2000)
    },
    edit(row) {
      this.form = row

      this.selectId = this.getDeptPids(this.form.pids)
      if (row.parent) {
        this.form.pid = row.parent.id
        this.form.pname = row.parent.simplename
      }

      // 设置自己不能被选中
      this.options = JSON.parse(JSON.stringify(this.optionsBack))
      this.options = this.setOptionsSelectDisable(this.options, row.id)
      // 去除数据空值
      // this.removeArr(this.options)

      // 验证表单
      this.$nextTick(() => {
        // this.$refs['form'].resetFields()
        this.$refs['form'].validate((valid) => {
          return false
        })
      })

      // 清空级联选中样式
      this.resetCascader()

      this.formTitle = '编辑部门'
      this.formVisible = true
      this.isAdd = false
    },

    setOptionsSelectDisable(arr, id) {
      // 设置下拉项不能选中自己ID和所有子项目
      for (var i = 0; i < arr.length; i++) {
        var value = arr[i]
        var child = value.children
        if (value.value === id) {
          value.disabled = true
          if (isEmpty(child)) {
            delete value.children
          }
          return arr
        }

        if (!isEmpty(child)) {
          value.children = this.setOptionsSelectDisable(child, id)
          return arr
        }
      }
    },

    // setOptionsSelectDisable(arr, id) {
    //   // 设置下拉项不能选中自己ID
    //   for (var i = 0; i < arr.length; i++) {
    //     var value = arr[i]
    //     if (value.value === id) {
    //       value.disabled = true
    //       return arr
    //     }
    //
    //     var child = value.children
    //     if (!isEmpty(child)) {
    //       value.children = this.setOptionsSelectDisable(child, id)
    //       return arr
    //     }
    //   }
    // },

    getDeptPids(pids) {
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
      return result
    },
    remove(row) {
      this.$confirm('确定删除该记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        del(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.fetchData()
        })
      })
    },
    handleChange(value) {
      // 排序输入框
      this.$nextTick(() => {
        if (value > 10000) {
          this.form.num = 10000
        }
      })
    },

    btKeyUp(e) {
      e.target.value = e.target.value.replace(/[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/g, '')
      e.target.value = e.target.value.replace(/^[A-Za-z]+$/g, '')
      e.target.value
      if (e.target.value > 10000) {
        this.form.num = 10000
      }
    }
  }
}
