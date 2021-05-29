<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <el-col :span="6">
          <el-form>
            <el-form-item label="管点类型:" :inline="true" label-width="80px" class="type">
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
              <el-input v-model="listQuery.roadName"   :placeholder="$t('config.roadName')"></el-input>
            </el-form-item>
          </el-form>

        </el-col>
        <el-col :span="6">
          <el-button type="success"  icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button  type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
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
<!--      <el-table-column label="序号" align="center">
        <template slot-scope="scope" >
          {{scope.row.id}}
        </template>
      </el-table-column>-->
      <el-table-column :label="$t('pipenode.probePoint')" align="center">
        <template slot-scope="scope">
          {{scope.row.probe_point}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.features')" align="center">
        <template slot-scope="scope">
          {{scope.row.features}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.appendages')" align="center">
        <template slot-scope="scope">
          {{scope.row.appendages}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.groundElevation')" align="center"  width="140">
        <template slot-scope="scope">
          {{scope.row.ground_elevation}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.buildDate')" align="center">
        <template slot-scope="scope">
          {{scope.row.build_date}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.manholeMaterial')" align="center">
        <template slot-scope="scope">
          {{scope.row.manhole_material}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.manholeDiameter')" align="center" width="200">
        <template slot-scope="scope">
          {{scope.row.manhole_diameter}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.holeDepth')" align="center">
        <template slot-scope="scope">
          {{scope.row.hole_depth}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.detectDate')" align="center">
        <template slot-scope="scope">
          {{scope.row.detection_date}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.type')" align="center">
        <template slot-scope="scope">
          {{scope.row.typeName}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('pipenode.roadName')" align="center">
        <template slot-scope="scope">
          {{scope.row.road_name}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
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
      :close-on-click-modal="false"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="140px">
        <el-row>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.probePoint')" prop="probe_point">
              <el-input v-model="form.probe_point" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.features')" prop="features">
              <el-input v-model="form.features"  minlength=1></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item :label="$t('pipenode.appendages')" prop="appendages">
              <el-input v-model="form.appendages"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item :label="$t('pipenode.x')" prop="x_coordinates">
              <el-input v-model="form.x_coordinates"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.y')" prop="y_coordinates">
              <el-input v-model="form.y_coordinates"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item :label="$t('pipenode.symbolAngle')" prop="symbol_angle">
              <el-input v-model="form.symbol_angle"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.groundElevation')" prop="ground_elevation">
              <el-input v-model="form.ground_elevation"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item :label="$t('pipenode.ownershipUnit')"  >
              <el-select  v-model="form.ownership_unit" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in ownershipUnitList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                  <span style="float: left">{{ item.name }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.englishName }}</span>
                </el-option>
              </el-select >

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.mapNumber')" prop="map_number">
              <el-input v-model="form.map_number"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item :label="$t('pipenode.manholeMaterial')" prop="manhole_material">
              <el-input v-model="form.manhole_material"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.manholeDiameter')" prop="manhole_diameter">
              <el-input v-model="form.manhole_diameter"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item :label="$t('pipenode.manholeType')"  >
              <el-select  v-model="form.manhole_type" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in manholeTypeList"
                  :key="parseInt(item.id)"
                  :label="item.name"
                  :value="parseInt(item.id)"

                >
                </el-option>
              </el-select >

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.manholePx')" prop="manhole_px">
              <el-input v-model="form.manhole_px"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item :label="$t('pipenode.remarks')" prop="remarks">
              <el-input v-model="form.remarks"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.holeDepth')" prop="hole_depth">
              <el-input v-model="form.hole_depth"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item :label="$t('pipenode.detectionUnit')"  >
              <el-select  v-model="form.detection_unit" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in detectionUnitList"
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
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.supervisorUnit')"  >
              <el-select  v-model="form.supervisor_unit" filterable placeholder="请选择" style="width: 100%;">
                <el-option
                  v-for="item in supervisorUnitList"
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


          <el-col :span="12">
            <el-form-item :label="$t('pipenode.buildDate')">
              <el-date-picker v-model="form.build_date" type="year" placeholder="选择日期" style="width: 100%;" value-format="yyyy" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.detectDate')">
              <el-date-picker v-model="form.detection_date" type="month" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.type')" prop="type" >
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
          <el-col :span="12">
            <el-form-item :label="$t('pipenode.roadName')" prop="road_name">
              <el-input v-model="form.road_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
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
        :http-request="httpRequest"
        :file-list="fileList"
        :auto-upload="false"
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
        <div class="el-upload__tip" style="color:blue" slot="tip">提示：管点类型、井盖类型请填写字典对应汉字！</div>
        <div class="el-upload__tip" style="color:blue" slot="tip">提示：探测单位、监理单位、权属单位请确认公司管理是否存在，不存在请添加相关信息！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm" :disabled="upload.flag">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 管点详细 -->
    <el-dialog title="管点详细数据" :visible.sync="openView" width="700px" append-to-body>
      <el-form ref="form" :model="form" label-width="120px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label-width="140" label="物探点号：">{{ form.probe_point }}</el-form-item>
            <el-form-item label-width="140" label="特征：">{{ form.features }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="附属物：">{{ form.appendages }}</el-form-item>
            <el-form-item label-width="140" label="Y：">{{ form.y_coordinates }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="X：">{{ form.x_coordinates }}</el-form-item>
            <el-form-item label-width="140" label="符号旋转：">{{ form.symbol_angle }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="地面高程（米）：">{{ form.ground_elevation }}</el-form-item>
            <el-form-item label-width="140" label="权属单位：">{{ form.ownershipUnitName }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="建设年代：">{{ form.build_date }}</el-form-item>
            <el-form-item label-width="140" label="道路名称：">{{ form.road_name }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="图幅号：">{{ form.map_number }}</el-form-item>
            <el-form-item label-width="140" label="井盖材料：">{{ form.manhole_material }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="井盖直径（毫米）：">{{ form.manhole_diameter }}</el-form-item>
            <el-form-item label-width="140" label="井盖类型：">{{ form.manholeTypeName }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="偏心井位：">{{ form.manhole_px }}</el-form-item>
            <el-form-item label-width="140" label="备注：">{{ form.remarks }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="井底深：">{{ form.hole_depth }}</el-form-item>
            <el-form-item label-width="140" label="探测单位：">{{ form.detectionUnitName }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="监理单位：">{{ form.supervisorUnitName }}</el-form-item>
            <el-form-item label-width="140" label="探测日期：">{{ form.detection_date }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="140" label="管线点类型：">{{ form.typeName }}</el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="openView = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script src="./pipenode.js"></script>


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

