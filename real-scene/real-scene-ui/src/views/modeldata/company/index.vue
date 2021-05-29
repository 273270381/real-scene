<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="24">
          <span class="inputTitle">名称:</span>
          <el-input class="searchInput" v-model="listQuery.name" placeholder="请输入企业名称" />

          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>

      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button v-permission="['/company/edit']" type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
          <el-button v-permission="['/company/edit']" type="primary" icon="el-icon-edit" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button v-permission="['/company/delete']" type="danger" icon="el-icon-delete" @click.native="remove">{{ $t('button.delete') }}</el-button>
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

      <el-table-column
        label="序号"
        type="index"
        show-overflow-tooltip
        width="60"
        align="center"
        fixed
      >
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column label="企业名称">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="英文名称">
        <template slot-scope="scope">
          {{ scope.row.englishName }}
        </template>
      </el-table-column>
      <el-table-column label="联系人">
        <template slot-scope="scope">
          {{ scope.row.userName }}
        </template>
      </el-table-column>
      <el-table-column label="座机号">
        <template slot-scope="scope">
          {{ scope.row.landLine }}
        </template>
      </el-table-column>
      <el-table-column label="手机号">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="地址">
        <template slot-scope="scope">
          {{ scope.row.address }}
        </template>
      </el-table-column>
      <el-table-column label="邮箱">
        <template slot-scope="scope">
          {{ scope.row.email }}
        </template>
      </el-table-column>
      <el-table-column label="传真">
        <template slot-scope="scope">
          {{ scope.row.fax }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ dateFormat(scope.row.createTime) }}
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
      width="400px"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="企业名称" prop="name">
              <el-input v-model="form.name" minlength="1" placeholder="请输入企业名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="英文名称" prop="name">
              <el-input v-model="form.englishName" minlength="1" placeholder="请输入企业英文名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="联系人">
              <el-input v-model="form.userName" minlength="1" placeholder="请输入联系人名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="座机号" prop="landLine">
              <el-input v-model="form.landLine" placeholder="请输入座机号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入地址" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱号码" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="传真" prop="fax">
              <el-input v-model="form.fax" placeholder="请输入传真号码" />
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

<script src="./company.js"></script>

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

  .el-pagination button, .el-pagination span:not([class*=suffix]){
    font-size: 14px;
  }

  .el-table .warning-row {
    /*background: #f0f9eb;*/
  }

  .inputTitle{
    margin-right: 8px;
    color: #777777;
  }

</style>

