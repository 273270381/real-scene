<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="24">
          <span class="inputTitle">字典名称:</span>
          <el-input class="searchInput" v-model="listQuery.name" placeholder="请输入字典名称" />

          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
          <el-button type="primary" icon="el-icon-edit" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button type="danger" icon="el-icon-delete" @click.native="remove">{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      height="600"
      border
      fit
      highlight-current-row
      @current-change="handleCurrentChange"
    >
      <el-table-column label="ID" align="center" width="100">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="名称">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="详情">
        <template slot-scope="scope">
          {{ scope.row.detail }}
        </template>
      </el-table-column>

    </el-table>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      :close-on-click-modal="false"
      width="50%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="名称" prop="name">
          <el-tooltip class="item" effect="dark" content="长度在2到20个数字、字母或汉字组合字符" placement="top-start">
            <el-input v-model="form.name" minlength="1" placeholder="请输入字典名称" />
          </el-tooltip>
        </el-form-item>
        <el-form-item
          v-for="(rec, index) in form.details"
          :key="rec.key"
          :label="'字典' + (index+1)"
          :prop="'details.' + index + '.value'"
          :rules="{
            required: true, message: '不能为空', trigger: 'blur'
          }"
        >
          <el-col :span="10">
            <el-input v-model="rec.key" placeholder="值" />
          </el-col>
          <el-col class="line" :span="0.5">&nbsp; </el-col>
          <el-col :span="10">
            <el-input v-model="rec.value" placeholder="名称" />
          </el-col>
          <el-col class="line" :span="0.5">&nbsp; </el-col>
          <el-col :span="3">
            <el-button type="danger" icon="el-icon-delete" @click.prevent="removeDetail(rec)">{{ $t('button.delete')
            }}</el-button>
          </el-col>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click="addDetail">新增字典</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script src="./dict.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";

  .searchInput{
    width: 200px;
  }

  .el-button--primary {
    background-color: #0789cf;
    border-color: #0789cf;
  }

  .el-input__inner{
    border: 1px solid #b7b7b7;
  }

  .el-table th>.cell {
    font-size: 15px;
    font-weight: initial;
    color: #1c1d1d;
    text-align: center;
  }

  .inputTitle{
    margin-right: 6px;
    color: #777777;
  }

</style>
