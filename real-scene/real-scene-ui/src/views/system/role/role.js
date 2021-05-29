import { remove, getList, save, savePermissons,getRole,getAllRoles } from '@/api/system/role'
import { list as getDeptList,getOptionsList,treeselect,getDeptById } from '@/api/system/dept'
import { menuTreeListByRoleId } from '@/api/system/menu'
import { isEmpty } from '@/utils/validate.js'
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  components: { Treeselect },
  data() {
    return {
      formVisible: false,
      formTitle: '添加角色',
      deptList: [],
      roleList: [],
      selectId: [],
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      isAdd: true,
      // 部门树选项
      deptOptions: undefined,
      options: [], // 部门下拉框选择
      checkedPermissionKeys: [],
      permissons: [],
      defaultProps: {
        id: 'id',
        label: 'name',
        children: 'children'
      },
      permissonVisible: false,
      deptTree: {
        show: false,
        defaultProps: {
          id: 'id',
          label: 'simplename',
          children: 'children'
        }
      },
      roleTree: {
        show: false,
        defaultProps: {
          id: 'id',
          label: 'name',
          children: 'children'
        }
      },

      form: {
        tips: '',
        name: '',
        deptid: null,
        pid: null,
        id: '',
        version: '',
        deptName: '',
        pName: '',
        num: 1
      },
      rules: {
        tips: [
          { required: true, message: '请输入角色编码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' }
          //{ pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' },
          { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在3到20个数字、字母或汉字组合字符', trigger: 'blur' }
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
      selRow: {}
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
      treeselect().then(response => {
        this.deptOptions = response.data
      });
      getAllRoles().then(response =>{
        this.roleList = response.data;
      });
      // 加载下拉框选项
      this.getOptionsData()
      this.fetchData()
    },
    getOptionsData() {
      getOptionsList().then(response => {
        this.options = response.data
        // 去除数据空值
        this.removeArr(this.options)
      })
    },
    selectOptionClick(data) {
      var option = this.$refs['cascader'].getCheckedNodes()[0].data
      this.form.deptid = option.value
      this.form.deptName = option.label
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
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        console.log(response.data)
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
      if (this.$refs['form'] !== undefined) {
        this.$nextTick(() => {
          this.$refs['form'].resetFields()
        })
      }
      this.form = {
        tips: '',
        name: '',
        deptid: null,
        pid: null,
        id: '',
        version: '',
        deptName: '',
        pName: '',
        num: 1

      }
    },
    add() {
      this.resetForm();
      this.selectId = [];
      this.formTitle = '添加角色'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          save({
            id: this.form.id,
            num: this.form.num,
            deptid: this.form.deptid,
            pid: this.form.pid,
            name: this.form.name,
            tips: this.form.tips
          }).then(response => {
            this.$message({
              message: '提交成功',
              type: 'success'
            })
            this.fetchData()
            this.formVisible = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
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
      //this.resetForm();
      const roleId = this.ids;
      getRole(roleId).then(reponse =>{
        this.isAdd = false
        this.form = reponse.data;
        if(this.form.deptid != null){
          getDeptById(this.form.deptid).then(r1 =>{
            this.selectId =this.getDeptPids(r1.data.pids,r1.data.id)
          });
        }
        this.form.status = reponse.data.statusName === '启用'
        this.form.password = ''
        this.formTitle = '修改角色'
        this.formVisible = true
        // 清空级联选中样式
        this.resetCascader()
      });

    },
    getDeptPids(pids,deptid) {
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
      result.push(Number(deptid))
      return result
    },
    resetCascader() {
      // 清空级联选中样式
      if (this.$refs.cascader) {
        this.$refs.cascader.$refs.panel.activePath = []
        // this.$refs.cascader.$refs.panel.clearCheckedNodes()
        this.$refs.cascader.$refs.panel.calculateCheckedNodePaths()
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    remove() {
      const roleIds = this.ids;
      this.$confirm('是否确认删除角色编号为"' + roleIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return remove(roleIds);
      }).then(() => {
        this.fetchData();
        this.$message({
          message: '删除成功',
          type: 'success'
        })
      }).catch(function() {});
    },
    openPermissions() {
        const roleIds  = this.ids;
        menuTreeListByRoleId(roleIds).then(response => {
          this.permissons = response.data.treeData
          this.checkedPermissionKeys = response.data.checkedIds
          this.permissonVisible = true
        })
    },
    savePermissions() {
      let checkedNodes =this.$refs.permissonTree.getCheckedNodes(false,true)
      let menuIds = ''
      for (var index in checkedNodes) {
        menuIds += checkedNodes[index].id + ','
      }
      const data = {
        roleId: this.ids[0],
        permissions: menuIds
      }
      savePermissons(data).then(response => {
        this.permissonVisible = false
        this.$message({
          message: '提交成功',
          type: 'success'
        })
      })
    },
    handleDeptNodeClick(data, node) {
      this.form.deptid = data.id
      this.form.deptName = data.simplename
      this.deptTree.show = false
    },

    handleRoleNodeClick(data, node) {
      this.form.pid = data.id
      this.form.pName = data.name
      this.roleTree.show = false
    }

  }
}
