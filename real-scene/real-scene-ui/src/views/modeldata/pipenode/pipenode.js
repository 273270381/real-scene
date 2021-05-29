import { remove, getList, save, exportXls,importXls,importTemplate,queryDictTypeList,queryCompanyList,queryManholeTypeList,getPipeNodeDetail} from '@/api/modeldata/pipenode'
import { getApiUrl } from '@/utils/utils'

export default {
  data() {
    return {
      formVisible: false,
      formTitle: this.$t('pipenode.add'),
      isAdd: true,
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      form: {
        id: '',
        probe_point: '',
        features: '',
        appendages: '',
        symbol_angle:'',
        x_coordinates:'',
        y_coordinates:'',
        ground_elevation:'',
        ownership_unit:'',
        map_number:'',
        manhole_diameter:'',
        manhole_type:'',
        manhole_px:'',
        remarks:'',
        hole_depth:'',
        detection_unit:'',
        supervisor_unit:'',
        manhole_material:'',
        build_date:null,
        detection_date:null,
        type:'',
        road_name:''
      },
      fileList: [],
      typeList:[],
      ownershipUnitList:[],
      manholeTypeList:[],
      detectionUnitList:[],
      supervisorUnitList:[],
      // 是否显示详细弹出层
      openView: false,
      //用户导入参数
      interval:0,
      timeStop:0,
      loading: false,
      upload:{
        colors:[
          {color: '#f56c6c', percentage: 20},
          {color: '#e6a23c', percentage: 40},
          {color: '#5cb87a', percentage: 60},
          {color: '#1989fa', percentage: 80},
          {color: '#6f7ad3', percentage: 100}
        ],
        percentage:0,
        // 是否显示弹出层（用户导入）
        open: false,
        flag:false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 上传的地址
        url: "/pipeline/importData"
      },
      listQuery: {
        page: 1,
        limit: 20,
        type: undefined,
        roadName: undefined
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
  computed: {
    rules() {
      return {
        probe_point: [
          { required: true, message: '请输入物探点号', trigger: 'blur' }
        ],
        x_coordinates: [
          { required: true, message: '请输入X坐标', trigger: 'blur' }
        ],
        y_coordinates: [
          { required: true, message: '请输入Y坐标', trigger: 'blur' }
        ],
        ground_elevation: [
          { required: true, message: '请输入地面高程', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择管线点类型', trigger: 'blur' }
        ],
      }
    }
  },
  created() {
    this.init()

  },
  methods: {
    init() {
      this.fetchData()
      queryDictTypeList('管线类型').then(response => {
        this.typeList = response.data
      })
      queryCompanyList().then(response => {
        this.ownershipUnitList = response.data
        this.detectionUnitList = response.data
        this.supervisorUnitList = response.data
      })
      queryManholeTypeList('井盖类型').then(response => {
        this.manholeTypeList = response.data
      })
    },
    fetchData() {
      this.listLoading = true;
      getList(this.listQuery).then(response => {
        this.list = response.data.records;
        this.listLoading = false;
        this.total = response.data.total
      })
    },
    search() {
      this.listQuery.page = 1;
      this.fetchData()
    },
    reset() {
      this.listQuery.type = '';
      this.listQuery.roadName = '';
      this.listQuery.page = 1;
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1;
      this.getList()
    },
    handleClose() {

    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1;
      this.fetchData()
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1;
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page;
      this.fetchData()
    },
    changeSize(limit) {
      this.listQuery.limit = limit;
      this.fetchData()
    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {
        id: '',
        probe_point: '',
        features: '',
        appendages: '',
        symbol_angle:'',
        x_coordinates:'',
        y_coordinates:'',
        ground_elevation:'',
        ownership_unit:'',
        map_number:'',
        manhole_diameter:'',
        manhole_type:'',
        manhole_px:'',
        remarks:'',
        hole_depth:'',
        detection_unit:'',
        supervisor_unit:'',
        manhole_material:'',
        build_date:null,
        detection_date:null,
        type:'',
        road_name:''
      }
    },
    add() {
      this.resetForm();
      this.formTitle = this.$t('pipenode.add');
      this.formVisible = true;
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          save({
            id: this.form.id,
            probe_point: this.form.probe_point,
            features: this.form.features,
            appendages: this.form.appendages,
            manhole_material: this.form.manhole_material,
            build_date: this.form.build_date,
            detection_date: this.form.detection_date,
            type: this.form.type,
            road_name: this.form.road_name,
            symbol_angle: this.form.symbol_angle,
            x_coordinates: this.form.x_coordinates,
            y_coordinates: this.form.y_coordinates,
            ground_elevation: this.form.ground_elevation,
            ownership_unit: this.form.ownership_unit,
            map_number: this.form.map_number,
            manhole_diameter: this.form.manhole_diameter,
            manhole_type: this.form.manhole_type,
            manhole_px: this.form.manhole_px,
            remarks: this.form.remarks,
            hole_depth: this.form.hole_depth,
            detection_unit: this.form.detection_unit,
            supervisor_unit: this.form.supervisor_unit
          }).then(response => {
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
    handleView(row) {
      getPipeNodeDetail(row.probe_point).then(response => {
        this.form = response.data;
        this.openView = true;
      });
    },
    edit() {
      const probe_point = this.ids;
      getPipeNodeDetail(probe_point).then(response =>{
        this.isAdd = false
        this.form = response.data
        this.formTitle = this.$t('pipenode.edit')
        this.formVisible = true
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.probe_point);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    remove() {
      const probePoints = this.ids;
      this.$confirm('是否确认删除管点为"' + probePoints + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return remove(probePoints);
      }).then(() => {
        this.fetchData();
        this.$message({
          message: '删除成功',
          type: 'success'
        })
      }).catch(function() {});
    },
    exportXls() {
      exportXls(this.listQuery).then(response => {
        window.location.href= getApiUrl() + '/file/download?fileName='+response.data;
      })

    },

    handleImport(){
      this.upload.title = "用户导入";
      this.upload.open = true;
      this.upload.flag=false;
    },
    /** 下载模板操作 */
    importTemplate() {
      importTemplate().then(response => {
        window.location.href= getApiUrl() + '/file/download?fileName='+response.data;
      });
    },

    showProgress(){
      let endPro = 95;
      let that = this;
      that.loading = true;
      that.interval = setInterval(function () {
        if(that.upload.percentage<endPro){
          that.upload.percentage ++;
        }
      },50);
    },

    handleExceed(files,fileList){
      fileList[0].name = files[0].name;
    },

    httpRequest(params){
      let formdata = new FormData();
      formdata.append("file", params.file);
      this.upload.isUploading = true;
      this.showProgress();
      importXls(formdata).then(res =>{
        this.upload.percentage = 100;
        clearInterval(this.interval);
        this.upload.open = false;
        this.upload.isUploading = false;
        this.loading = false;
        this.$refs.upload.clearFiles();
        if(res.success){
          this.$alert(res.data, "导入结果", { dangerouslyUseHTMLString: false});
          this.fetchData();
        }
      }).catch((e) => {
        this.upload.percentage = 0;
        clearInterval(this.interval);
        this.upload.flag=false;
        this.upload.isUploading = false;
        this.loading = false;
        this.$refs.upload.clearFiles();
        this.fetchData();
      });
    },

    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
      this.upload.flag=true;
    }

  }
}
