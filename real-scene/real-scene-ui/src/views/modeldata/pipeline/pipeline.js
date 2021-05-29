import { remove, getList, save, exportXls,importXls,importTemplate,queryDictFillType ,queryDictMaterialType,queryCompanyList,queryDictTypeList,getPipeLineDetail} from '@/api/modeldata/pipeline'
import { getApiUrl } from '@/utils/utils'

export default {
  data() {
    return {
      formVisible: false,
      formTitle: this.$t('pipeline.add'),
      isAdd: true,
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      form: {
        id: '',
        start_point: '',
        end_point: '',
        fill_type_id: '',
        material_id:'',
        detect_company_id:'',
        detect_date:'',
        build_date:'',
        road_name:'',
        start_depth:'',
        end_depth:'',
        pipe_diameter:'',
        flow_direction:'',
        voltage_pressure:'',
        cable_number:'',
        hole_number:'',
        hole_allocate_number:'',
        hole_used_number:'',
        pipe_size:'',
        material_protect:'',
        start_pipe_top_height:'',
        end_pipe_top_height:'',
        pipeline_ownership:'',
        remarks:'',
        start_ground_height:'',
        end_ground_height:'',
        supervise_company_id:'',
        ownership_company_id:'',
        assist_type:'',
        detect_video:'',
        type:'',
        record_id:''
      },
      fileList: [],
      fillTypeList:[],
      materialType:[],
      detectCompanyList:[],
      typeList:[],
      superviseCompanyList:[],
      ownershipCompanyList:[],
      // 是否显示详细弹出层
      openView: false,
      interval:0,
      timeStop:0,
      loading: false,
      //用户导入参数
      upload:{
        colors:[
          {color: '#f56c6c', percentage: 20},
          {color: '#e6a23c', percentage: 40},
          {color: '#5cb87a', percentage: 60},
          {color: '#1989fa', percentage: 80},
          {color: '#6f7ad3', percentage: 100}
        ],
        percentage:0,
        flag:false,
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/pipeline/importData"
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
        start_point: [
          { required: true, message: '请输入起点号', trigger: 'blur' }
        ],
        end_point: [
          { required: true, message: '请输入终点号', trigger: 'blur' }
        ],
        start_depth: [
          { required: true, message: '请输入起点埋深', trigger: 'blur' }
        ],
        end_depth: [
          { required: true, message: '请输入终点埋深', trigger: 'blur' }
        ],
        fill_type_id: [
          { required: true, message: '请选择埋设类型', trigger: 'change' }
        ],
        pipe_diameter:[
          { required: true, message: '请输入管径', trigger: 'blur' }
        ],
        start_ground_height:[
          { required: true, message: '请输入起点地面高程', trigger: 'blur' }
        ],
        end_ground_height:[
          { required: true, message: '请输入终点地面高程', trigger: 'blur' }
        ],
        type:[
          { required: true, message: '请选择管线类型', trigger: 'change' }
        ],
        record_id:[
          { required: true, message: '请输入唯一id', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
      queryDictFillType('埋设类型').then(response => {
        this.fillTypeList = response.data
      })
      queryDictMaterialType('材质类型').then(response => {
          this.materialType = response.data
      })
      queryCompanyList().then(response => {
        this.detectCompanyList = response.data
        this.superviseCompanyList = response.data
        this.ownershipCompanyList = response.data
      })
      queryDictTypeList('管线类型').then(response => {
        this.typeList = response.data
      })
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
      this.listQuery.type = ''
      this.listQuery.roadName = ''
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
      this.form = {
        id: '',
        start_point: '',
        end_point: '',
        fill_type_id: '',
        material_id:'',
        detect_company_id:'',
        detect_date:'',
        build_date:'',
        road_name:'',
        start_depth:'',
        end_depth:'',
        pipe_diameter:'',
        flow_direction:'',
        voltage_pressure:'',
        cable_number:'',
        hole_number:'',
        hole_allocate_number:'',
        hole_used_number:'',
        pipe_size:'',
        material_protect:'',
        start_pipe_top_height:'',
        end_pipe_top_height:'',
        pipeline_ownership:'',
        remarks:'',
        start_ground_height:'',
        end_ground_height:'',
        supervise_company_id:'',
        ownership_company_id:'',
        assist_type:'',
        detect_video:'',
        type:'',
        record_id:''
      }
    },
    add() {
      this.resetForm()
      this.formTitle = this.$t('pipeline.add')
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          save({
            id: this.form.id,
            start_point: this.form.start_point,
            end_point: this.form.end_point,
            fill_type_id: this.form.fill_type_id,
            material_id: this.form.material_id,
            detect_company_id: this.form.detect_company_id,
            detect_date: this.form.detect_date,
            build_date:this.form.build_date,
            road_name: this.form.road_name,
            start_depth: this.form.start_depth,
            end_depth: this.form.end_depth,
            pipe_diameter: this.form.pipe_diameter,
            flow_direction: this.form.flow_direction,
            voltage_pressure: this.form.voltage_pressure,
            cable_number: this.form.cable_number,
            hole_number: this.form.hole_number,
            hole_allocate_number: this.form.hole_allocate_number,
            hole_used_number: this.form.hole_used_number,
            pipe_size: this.form.pipe_size,
            material_protect: this.form.material_protect,
            start_pipe_top_height: this.form.start_pipe_top_height,
            end_pipe_top_height: this.form.end_pipe_top_height,
            pipeline_ownership: this.form.pipeline_ownership,
            remarks: this.form.remarks,
            start_ground_height: this.form.start_ground_height,
            end_ground_height: this.form.end_ground_height,
            supervise_company_id: this.form.supervise_company_id,
            ownership_company_id: this.form.ownership_company_id,
            assist_type: this.form.assist_type,
            detect_video: this.form.detect_video,
            type:this.form.type,
            record_id: this.form.record_id
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
      getPipeLineDetail(row.record_id).then(response => {
        this.form = response.data;
        this.openView = true;
      });
     },
    edit() {
      const recordId = this.ids;
      getPipeLineDetail(recordId).then(response =>{
        this.isAdd = false
        this.form = response.data
        this.formTitle = this.$t('pipeline.edit')
        this.formVisible = true
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.record_id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    remove() {
      const recordIds = this.ids;
      this.$confirm('是否确认删除管线为"' + recordIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return remove(recordIds);
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
        this.$refs.upload.clearFiles();
        if(res.success){
          this.upload.open = false;
          this.upload.isUploading = false;
          this.loading = false;
          this.$alert(res.data, "导入结果", { dangerouslyUseHTMLString: true });
          this.fetchData();
        }
      }).catch((e) => {
        this.upload.percentage = 0;
        clearInterval(this.interval);
        this.$refs.upload.clearFiles();
        this.upload.isUploading = false;
        this.loading = false;
        this.upload.flag=false;
        this.fetchData();
      });
    },


    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
      this.upload.flag=true;
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.fetchData();
          done();
        })
        .catch(_ => {});
    },

    cancel(){
      this.formVisible = false;
      this.fetchData();
    }


  }
}
