<template>
  <div class="app-container">
    <div class="block">
      <el-button type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
    </div>

    <el-table
      :data="data"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      height="600"
      border
      :default-expand-all="false"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >

      <el-table-column label="简称">
        <template slot-scope="scope">
          <el-button type="text" @click="edit(scope.row)">{{ scope.row.simplename }}</el-button>

        </template>
      </el-table-column>
      <el-table-column label="全称">
        <template slot-scope="scope">
          <span>{{ scope.row.fullname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="顺序" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.num }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="remove(scope.row)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      :close-on-click-modal="false"
      width="40%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="70px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="simplename">
              <el-tooltip class="item" effect="dark" content="长度在3到20个数字、字母或汉字组合字符" placement="top-start">
                <el-input v-model="form.simplename" minlength="1" placeholder="请输入部门名称" />
              </el-tooltip>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="全称" prop="fullname">
              <el-tooltip class="item" effect="dark" content="长度在3到20个数字、字母或汉字组合字符" placement="top-start">
                <el-input v-model="form.fullname" minlength="1" placeholder="请输入部门全称" />
              </el-tooltip>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="上级部门">
              <el-cascader
                ref="cascader"
                v-model="selectId"
                :options="options"
                :show-all-levels="false"
                :props="{ checkStrictly: true }"
                clearable
                filterable
                style="width: 100%;"
                @change="selectOptionClick"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="排序" prop="num">
              <el-tooltip class="item" effect="dark" content="请输入1-10000正整数" placement="top-start">
                <!--<el-input-number v-model="form.num" :step="1" :min="1" :max="10000" @keyup.native="btKeyUp" @change="handleChange" placeholder="请输入排序"></el-input-number>-->
                <el-input
                  v-model="form.num"
                  type="number"
                  @keyup.native="btKeyUp"
                  :step="1"
                  :min="1"
                  :max="10000"
                />
              </el-tooltip>
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item>
          <el-button type="primary" :disabled="isDisabled" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./dept.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";

  .el-table th>.cell {
    font-size: 15px;
    font-weight: initial;
    color: #1c1d1d;
    text-align: center;
  }

  .el-input-number{
    width: 100%;
  }

</style>
