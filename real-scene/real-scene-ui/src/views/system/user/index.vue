<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="24">
          <span class="inputTitle">帐号:</span>
          <el-input v-model="listQuery.account" class="searchInput" @keyup.native="btKeyUp" placeholder="请输入帐号" />

          <span class="inputTitle" style="margin-left: 12px;">姓名:</span>
          <el-input v-model="listQuery.name" class="searchInput" @keyup.native="btKeyUp" placeholder="请输入姓名" />

          <el-button type="success" icon="el-icon-search" @click.native="search" style="margin-left: 12px;">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button v-permission="['/mgr/add']" type="success" icon="el-icon-plus" @click.native="add">
            {{ $t('button.add') }}
          </el-button>
          <el-button v-permission="['/mgr/edit']" type="primary" icon="el-icon-edit" @click.native="edit">
            {{ $t('button.edit') }}
          </el-button>
          <el-button v-permission="['/mgr/delete']" type="danger" icon="el-icon-delete" @click.native="remove">
            {{ $t('button.delete') }}
          </el-button>
          <el-button v-permission="['/mgr/setRole']" type="warning" icon="el-icon-s-custom" @click.native="openRole">角色分配</el-button>
          <el-button v-permission="['/mgr/reset']" type="warning" style="background-color: #ff0000;border-color: #ff0000;" @click.native="openPassword">
            <svg-icon icon-class="password" />
            重置密码
          </el-button>

        </el-col>
      </el-row>
    </div>

    <el-table
      ref="tableList"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      height="600"
      border
      fit
      highlight-current-row
      :row-class-name="tableRowClassName"
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

      <el-table-column label="账号">
        <template slot-scope="scope">
          {{ scope.row.account }}
        </template>
      </el-table-column>
      <el-table-column label="姓名">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" width="100">
        <template slot-scope="scope">
          {{ scope.row.sex === 1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="部门">
        <template slot-scope="scope">
          {{ scope.row.dept.simplename != null ? scope.row.dept.simplename : '' }}
        </template>
      </el-table-column>
      <el-table-column label="邮箱">
        <template slot-scope="scope">
          {{ scope.row.email }}
        </template>
      </el-table-column>
      <el-table-column label="角色">
        <template slot-scope="scope">
          {{ scope.row.roleName }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime | formatDate }}
        </template>
      </el-table-column>
      <el-table-column label="启用/禁用" align="center" width="150">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status==1" @change="changeUserStatus(scope.row)" />
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
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" label-position="right">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账户" prop="account">
              <el-tooltip class="item" effect="dark" content="长度在2到16个数字、字母或汉字组合字符" placement="top-start">
                <el-input v-model="form.account" placeholder="请输入账户名称" />
              </el-tooltip>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-tooltip class="item" effect="dark" content="长度在2到16个数字、字母或汉字组合字符" placement="top-start">
                <el-input v-model="form.name" minlength="1" placeholder="请输入姓名" />
              </el-tooltip>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" auto-complete="off" />
            </el-form-item>
          </el-col>

          <el-col v-if="isAdd" :span="12">
            <el-form-item label="密码" prop="password">
              <el-tooltip class="item" effect="dark" content="长度在6到20个字符" placement="top-start">
                <el-input v-model="form.password" type="password" />
              </el-tooltip>
            </el-form-item>
          </el-col>
          <el-col v-if="isAdd" :span="12">
            <el-form-item label="确认密码" prop="rePassword">
              <el-tooltip class="item" effect="dark" content="长度在6到20个字符" placement="top-start">
                <el-input v-model="form.rePassword" type="password" />
              </el-tooltip>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="所属部门" prop="deptName">
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
            <el-form-item label="性别">
              <el-radio-group v-model="form.sex">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="2">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="是否启用" prop="status">
              <el-switch v-model="form.status" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期">
              <el-date-picker v-model="form.birthday" type="date" placeholder="选择日期" style="width: 100%;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" :disabled="isDisabled" @click="saveUser">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="角色分配"
      :visible.sync="roleDialog.visible"
      :close-on-click-modal="false"
      width="25%"
    >
      <el-form>
        <el-row>
          <el-col :span="12">
            <el-tree
              ref="roleTree"
              :data="roleDialog.roles"
              show-checkbox
              node-key="id"
              :default-checked-keys="roleDialog.checkedRoleKeys"
              :props="roleDialog.defaultProps"
            />

          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="setRole">{{ $t('button.submit') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>



    <el-dialog
      title="重置密码"
      :visible.sync="passwordDialog.visible"
      :close-on-click-modal="false"
      width="25%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" label-position="right">
        <el-row>

          <el-col :span="24">
            <el-form-item label="密码" prop="password">
              <el-tooltip class="item" effect="dark" content="长度在6到20个字符" placement="top-start">
                <el-input v-model="form.password" type="password" />
              </el-tooltip>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="确认密码" prop="rePassword">
              <el-tooltip class="item" effect="dark" content="长度在6到20个字符" placement="top-start">
                <el-input v-model="form.rePassword" type="password" />
              </el-tooltip>
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item>
          <el-button type="primary" @click="savePassword">{{ $t('button.submit') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


  </div>
</template>

<script src="./user.js"></script>
<style rel="stylesheet/scss" lang="scss">
  @import "src/styles/common.scss";

  .el-cascader-node.is-selectable.in-active-path {
     color: #409EFF;
  }

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
    font-weight: normal;
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

