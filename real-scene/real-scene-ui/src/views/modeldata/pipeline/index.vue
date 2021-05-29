<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <!--        <el-col :span="6">
                  <el-input v-model="listQuery.type" size="mini" :placeholder="$t('config.pipeType')"></el-input>
                </el-col>-->
        <el-col :span="6">
          <el-form>
            <el-form-item label="管线类型:" :inline="true" label-width="80px" class="type">
              <el-select  v-model="listQuery.type" filterable :placeholder="$t('config.pipeType')"  style="width: 100%;">
                <el-option
                  v-for="item in typeList"
                  :key="parseInt(item.id)"
                  :label="item.name"
                  :value="parseInt(item.id)"
                >
                </el-option>
              </el-select >
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6">
          <el-form>
            <el-form-item label="道路名称:" :inline="true" label-width="80px" class="name">
              <el-input v-model="listQuery.roadName"  :placeholder="$t('config.roadName')"></el-input>
            </el-form-item>
          </el-form>

        </el-col>
        <el-col :span="6">
          <el-form>
            <el-form-item>
              <el-button type="success"  icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
              <el-button  type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
            </el-form-item>
          </el-form>

        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <!--          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>-->
          <el-button type="primary"  icon="el-icon-edit" :disabled="single" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button type="danger"  icon="el-icon-delete" :disabled="multiple" @click.native="remove">{{ $t('button.delete') }}</el-button>
          <el-button type="info"  icon="el-icon-upload2" @click.native="exportXls">{{ $t('button.export') }}</el-button>
          <el-button type="warning"  icon="el-icon-download" @click.native="handleImport">{{ $t('button.import') }}</el-button>
        </el-col>
      </el-row>
    </div>


    <el-table height="600" :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="60"
        align="center"
        fixed
      >
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column label="record_id" align="center">
        <template slot-scope="scope">
          {{scope.row.record_id}}
        </template>
      </el-table-column>
<!--      <el-table-column label="ID" align="center">
        <template slot-scope="scope">
          {{scope.row.gid}}
        </template>
      </el-table-column>-->
      <el-table-column :label="$t('pipeline.startPoint')" align="center">
        <template slot-scope="scope">
          {{scope.row.start_point}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.endPoint')" align="center">
        <template slot-scope="scope">
          {{scope.row.end_point}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.fillType')" align="center">
        <template slot-scope="scope">
          {{scope.row.fillType}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.startDepth')" align="center">
        <template slot-scope="scope">
          {{scope.row.start_depth}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.endDepth')" align="center">
        <template slot-scope="scope">
          {{scope.row.end_depth}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.pipeDiameter')" align="center">
        <template slot-scope="scope">
          {{scope.row.pipe_diameter}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.detectDate')" align="center" width="200">
        <template slot-scope="scope">
          {{scope.row.detect_date}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.type')" align="center">
        <template slot-scope="scope">
          {{scope.row.typeName}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipeline.roadName')" align="center">
        <template slot-scope="scope">
          {{scope.row.road_name}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
          >详细</el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.limit"
      :total="total"
      :current-page.sync="listQuery.page"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      :before-close="handleClose"
      :close-on-click-modal="false"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-row>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.startPoint')" prop="start_point">
              <el-input v-model="form.start_point" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.endPoint')" prop="end_point">
              <el-input v-model="form.end_point"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.fillType')" prop="fill_type_id" >
              <el-select  v-model="form.fill_type_id" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in fillTypeList"
                  :key="parseInt(item.id)"
                  :label="item.name"
                  :value="parseInt(item.id)"

                >
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item :label="$t('pipeline.startDepth')" prop="start_depth" >
              <el-input v-model="form.start_depth" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.endDepth')" prop="end_depth">
              <el-input v-model="form.end_depth"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.pipeDiameter')" prop="pipe_diameter">
              <el-input v-model="form.pipe_diameter"  minlength=1></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item :label="$t('pipeline.flowDirection')" prop="flow_direction">
              <el-input v-model="form.flow_direction" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.voltagePressure')" prop="voltage_pressure">
              <el-input v-model="form.voltage_pressure"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.cableNumber')" prop="cable_number">
              <el-input v-model="form.cable_number"  minlength=1></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item :label="$t('pipeline.holeNumber')" prop="hole_number">
              <el-input v-model="form.hole_number" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.holeAllocateNumber')" prop="hole_allocate_number">
              <el-input v-model="form.hole_allocate_number"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.buildDate')">
              <el-date-picker v-model="form.build_date" type="year" placeholder="选择日期" style="width: 100%;" value-format="yyyy"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item :label="$t('pipeline.holeUsedNumber')" prop="hole_used_number">
              <el-input v-model="form.hole_used_number" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.pipeSize')" prop="pipe_size">
              <el-input v-model="form.pipe_size"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.materialProtect')" prop="material_protect">
              <el-input v-model="form.material_protect"  minlength=1></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item :label="$t('pipeline.startPipeTopHeight')" prop="start_pipe_top_height">
              <el-input v-model="form.start_pipe_top_height" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.endPipeTopHeight')" prop="end_pipe_top_height">
              <el-input v-model="form.end_pipe_top_height"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.pipelineOwnership')" prop="pipeline_ownership">
              <el-input v-model="form.pipeline_ownership"  minlength=1></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item :label="$t('pipeline.remarks')" prop="remarks">
              <el-input v-model="form.remarks" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.startGroundHeight')" prop="start_ground_height">
              <el-input v-model="form.start_ground_height"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.endGroundHeight')" prop="end_ground_height">
              <el-input v-model="form.end_ground_height"  minlength=1></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item :label="$t('pipeline.superviseCompany')"  >
              <el-select  v-model="form.supervise_company_id" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in superviseCompanyList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                  <span style="float: left">{{ item.name }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.englishName }}</span>
                </el-option>
              </el-select >

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.ownershipCompany')"  >
              <el-select  v-model="form.ownership_company_id" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in ownershipCompanyList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                  <span style="float: left">{{ item.name }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.englishName }}</span>
                </el-option>
              </el-select >

            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item :label="$t('pipeline.assistType')" prop="assist_type">
              <el-input v-model="form.assist_type"  minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.detectVideo')" prop="detect_video">
              <el-input v-model="form.detect_video"  minlength=1></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item :label="$t('pipeline.material')"  >
              <el-select  v-model="form.material_id" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in materialType"
                  :key="parseInt(item.id)"
                  :label="item.name"
                  :value="parseInt(item.id)"

                >
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.detectCompany')"  >
              <el-select  v-model="form.detect_company_id" filterable  placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in detectCompanyList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                  <span style="float: left">{{ item.name }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.englishName }}</span>
                </el-option>
              </el-select >

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.detectDate')">
              <el-date-picker v-model="form.detect_date" type="month" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.type')"  prop="type">
              <el-select  v-model="form.type" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in typeList"
                  :key="parseInt(item.id)"
                  :label="item.name"
                  :value="parseInt(item.id)"

                >
                </el-option>
              </el-select >

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.roadName')" prop="road_name">
              <el-input v-model="form.road_name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('pipeline.uniqueId')" prop="record_id">
              <el-input v-model="form.record_id" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="cancel">{{ $t('button.cancel') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :close-on-click-modal="false" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url"
        :disabled="upload.isUploading"
        :auto-upload="false"
        :http-request="httpRequest"
        :file-list="fileList"
        :on-exceed="handleExceed"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <el-progress :text-inside="true" :stroke-width="26" type="line" v-if="loading" :percentage="upload.percentage" :color="upload.colors" ></el-progress>
        <div class="el-upload__tip" slot="tip">
          <el-link type="danger" style="font-size:30px" @click="importTemplate"><u>点击此处下载模板!</u></el-link>
        </div>
        <div class="el-upload__tip" style="color:blue" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
        <div class="el-upload__tip" style="color:blue" slot="tip">提示：管线类型、材质类型、埋设类型请填写字典对应汉字！</div>
        <div class="el-upload__tip" style="color:blue" slot="tip">提示：探测单位、监理单位、权属单位请确认公司管理是否存在，不存在请添加相关信息！</div>

      </el-upload>


      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm" :disabled="upload.flag">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 管线详细 -->
    <el-dialog title="管线详细数据" :visible.sync="openView" width="700px" append-to-body>
      <el-form ref="form" :model="form" label-width="120px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label-width="140" label="起点号：">{{ form.start_point }}</el-form-item>
            <el-form-item label-width="140" label="起点埋深（米）：">{{ form.start_depth }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="终点号：">{{ form.end_point }}</el-form-item>
            <el-form-item label-width="140" label="终点埋深（米）：">{{ form.end_depth }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="埋设类型：">{{ form.fillType }}</el-form-item>
            <el-form-item label-width="140" label="材质：">{{ form.material }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="管径（毫米）：">{{ form.pipe_diameter }}</el-form-item>
            <el-form-item label-width="140"label="电压压力（千伏）：">{{ form.voltage_pressure }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="电缆条数：">{{ form.cable_number }}</el-form-item>
            <el-form-item label-width="140" label="总孔数：">{{ form.hole_number }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="分配孔数：">{{ form.hole_allocate_number }}</el-form-item>
            <el-form-item label-width="140" label="建设年代：">{{ form.build_date }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="已用孔数：">{{ form.hole_used_number }}</el-form-item>
            <el-form-item label-width="140" label="套管尺寸（毫米）：">{{ form.pipe_size }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="保护材料：">{{ form.material_protect }}</el-form-item>
            <el-form-item label-width="140" label="起点管顶高程（米）：">{{ form.start_pipe_top_height }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="终点管顶高程（米）：">{{ form.end_pipe_top_height }}</el-form-item>
            <el-form-item label-width="140" label="管线权属代码：">{{ form.pipeline_ownership }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="道路名称：">{{ form.road_name }}</el-form-item>
            <el-form-item label-width="140" label="备注：">{{ form.remarks }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="起点地面高程（米）：">{{ form.start_ground_height }}</el-form-item>
            <el-form-item label-width="140" label="终点地面高程（米）：">{{ form.end_ground_height }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="探测单位：">{{ form.detectCompany }}</el-form-item>
            <el-form-item label-width="140" label="监理单位：">{{ form.superviseCompany }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="探测日期：">{{ form.detect_date }}</el-form-item>
            <el-form-item label-width="140" label="管线段类型：">{{ form.typeName }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="权属单位：">{{ form.ownershipCompany }}</el-form-item>
            <el-form-item label-width="140" label="record_id：">{{ form.record_id }}</el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="openView = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script src="./pipeline.js"></script>


<style rel="stylesheet/scss" lang="scss">
  @import "src/styles/common.scss";
  .type{
    .el-form-item__label{
      color: #777777;
      font-size: 16px;
      font-weight:normal;
    }
  }
  .name{
    .el-form-item__label{
      color: #777777;
      font-size: 16px;
      font-weight:normal;
    }
  }

  .el-button--primary {
    background-color: #0789cf;
    border-color: #0789cf;
  }
</style>

