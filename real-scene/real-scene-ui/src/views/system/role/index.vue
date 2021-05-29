<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form>
            <el-form-item label="角色名称:" :inline="true" label-width="100px" class="roleName">
              <el-input v-model="listQuery.name" placeholder="请输入角色名称" />
            </el-form-item>
          </el-form>

        </el-col>
        <el-col :span="6">
          <el-form>
            <el-form-item>
              <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
              <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
            </el-form-item>
          </el-form>

        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-button type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
          <el-button type="primary" icon="el-icon-edit" :disabled="single" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button type="danger" icon="el-icon-delete" :disabled="multiple" @click.native="remove">{{ $t('button.delete') }}</el-button>
          <el-button type="warning" icon="el-icon-setting" :disabled="single" @click.native="openPermissions">权限配置</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table
      v-loading="listLoading"
      height="600"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      @current-change="handleCurrentChange"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="编码" align="center">
        <template slot-scope="scope">
          {{ scope.row.tips }}
        </template>
      </el-table-column>
      <el-table-column label="所在部门" align="center">
        <template slot-scope="scope">
          {{ scope.row.deptName }}
        </template>
      </el-table-column>
      <el-table-column label="上级角色" align="center">
        <template slot-scope="scope">
          {{ scope.row.pName }}
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
      @next-click="fetchNext"
    />

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      :close-on-click-modal="false"
      width="50%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="编码" prop="tips">
              <el-tooltip class="item" effect="dark" content="长度在3到20个数字、字母或汉字组合字符" placement="top-start">
                <el-input v-model="form.tips" minlength="1" placeholder="请输入编码" />
              </el-tooltip>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-tooltip class="item" effect="dark" content="长度在3到20个数字、字母或汉字组合字符" placement="top-start">
                <el-input v-model="form.name" minlength="1" placeholder="请输入名称" />
              </el-tooltip>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="排序">
              <el-input-number v-model="form.num" :step="1" :min="1" :max="10000" :step-strictly="true" style="width: 100%;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上级角色">
              <el-select  v-model="form.pid" filterable placeholder="请选择上级角色" style="width: 100%;">
                <el-option
                  v-for="item in roleList"
                  :key="parseInt(item.id)"
                  :label="item.name"
                  :value="parseInt(item.id)"
                >
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属部门">
              <el-cascader
                ref="cascader"
                v-model="selectId"
                :options="options"
                :show-all-levels="false"
                @change="selectOptionClick"
                :props="{ checkStrictly: true }"
                clearable
                filterable
                style="width: 100%;"
              />
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="权限配置"
      :visible.sync="permissonVisible"
      :close-on-click-modal="false"
      width="25%"
    >
      <el-form>
        <el-row>
          <el-col :span="12">
            <el-tree
              ref="permissonTree"
              :data="permissons"
              show-checkbox
              node-key="id"
              :default-checked-keys="checkedPermissionKeys"
              :props="defaultProps"
            />

          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="savePermissions">{{ $t('button.submit') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script src="./role.js"></script>
<style rel="stylesheet/scss" lang="scss">

  @import "src/styles/common.scss";

  .roleName{
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

<style>

</style>
