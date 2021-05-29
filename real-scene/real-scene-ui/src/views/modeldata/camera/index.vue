<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="24">
          <span class="inputTitle">地址:</span>
          <el-input class="searchInput" v-model="listQuery.address" placeholder="请输入地址" />

          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>

      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button v-permission="['/camera/edit']" type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
          <el-button v-permission="['/camera/edit']" type="primary" icon="el-icon-edit" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button v-permission="['/camera/delete']" type="danger" icon="el-icon-delete" @click.native="remove">{{ $t('button.delete') }}</el-button>
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

      <el-table-column label="类型">
        <template slot-scope="scope">
          {{ scope.row.typeName }}
        </template>
      </el-table-column>
      <el-table-column label="地址">
        <template slot-scope="scope">
          {{ scope.row.address }}
        </template>
      </el-table-column>
      <el-table-column label="摄像头ip">
        <template slot-scope="scope">
          {{ scope.row.ip }}
        </template>
      </el-table-column>
      <el-table-column label="摄像头端口">
        <template slot-scope="scope">
          {{ scope.row.port }}
        </template>
      </el-table-column>
      <el-table-column label="云台ip">
        <template slot-scope="scope">
          {{ scope.row.cloudIp }}
        </template>
      </el-table-column>
      <el-table-column label="云台端口">
        <template slot-scope="scope">
          {{ scope.row.cloudPort }}
        </template>
      </el-table-column>
      <el-table-column label="用户名">
        <template slot-scope="scope">
          {{ scope.row.loginName }}
        </template>
      </el-table-column>
      <el-table-column label="密码">
        <template slot-scope="scope">
          {{ scope.row.loginPassword }}
        </template>
      </el-table-column>
      <el-table-column label="播放地址">
        <template slot-scope="scope">
          {{ scope.row.url }}
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
      width="600px"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型" prop="typeName">
              <el-input v-model="form.typeName" minlength="1" placeholder="请输入类型名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address" minlength="1" placeholder="请输入地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="ip" prop="ip">
              <el-input v-model="form.ip" minlength="1" placeholder="请输入ip" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="端口" prop="port">
              <el-input v-model="form.port" placeholder="请输入端口" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="云台ip" prop="cloudIp">
              <el-input v-model="form.cloudIp" placeholder="请输入云台ip" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="云台端口" prop="cloudPort">
              <el-input v-model="form.cloudPort" placeholder="请输入云台端口" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户名" prop="loginName">
              <el-input v-model="form.loginName" placeholder="请输入用户名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="loginPassword">
              <el-input v-model="form.loginPassword" placeholder="请输入密码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="播放地址" prop="url">
              <el-input v-model="form.url" placeholder="请输入视频流播放地址" />
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

<script src="./camera.js"></script>

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

