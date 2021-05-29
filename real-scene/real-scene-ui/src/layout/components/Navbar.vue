<template>
  <div class="navbar">
    <!--<hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />-->

    <!--<breadcrumb class="breadcrumb-container" />-->

    <div class="img-wrapper">
      <img class="img-suchness" src="@/assets/img/20200803162331.png">
    </div>

    <div class="text-wrapper">
      <p class="text">三维实景地图后台管理系统</p>
    </div>

    <div class="right-menu">

      <!--<template>-->
        <!--<a target="real-scene" :href="this.$store.state.settings.WebGL" class="webgl">三维实景平台</a>-->
      <!--</template>-->

      <!--<template>-->
      <!--<lang-select class="international right-menu-item"/>-->
      <!--</template>-->
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img class="user-avatar" src="@/assets/img/avatar.gif">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/account/profile">
            <el-dropdown-item>
              {{ $t('navbar.profile') }}
            </el-dropdown-item>
          </router-link>
          <router-link to="/account/updatePwd">
            <el-dropdown-item>
              {{ $t('navbar.updatePwd') }}
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">{{ $t('navbar.logOut') }}</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import LangSelect from '@/components/LangSelect'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    LangSelect
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push('/login')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @font-face{
    font-family: 本墨锵黑;
    src: url("../../assets/custom-theme/fonts/本墨锵黑.ttf")format("truetype");
  }
  .navbar {
    /*position: fixed;*/
    height: 80px;
    width: 100%;
    line-height: 80px;
    /*z-index: 1;*/
    background: #304156;
    overflow: hidden;
    border-radius: 0px !important;
    display: block;
    .hamburger-container {
      line-height: 58px;
      height: 50px;
      float: left;
      padding: 0 10px;
    }
    .breadcrumb-container{
      float: left;
    }
    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }
    .right-menu {
      float: right;
      height: 100%;
      &:focus{
        outline: none;
      }
      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }
      .screenfull {
        height: 20px;
      }
      .international{
        vertical-align: top;
      }
      .theme-switch {
        vertical-align: 15px;
      }
      .avatar-container {
        height: 50px;
        margin-right: 30px;
        .avatar-wrapper {
          margin-top: 20px;
          position: relative;
          .user-avatar {
            margin-bottom: 20px;
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }
          .el-icon-caret-bottom {
            cursor: pointer;
            position: absolute;
            right: -20px;
            top: 15px;
            font-size: 12px;
            color: #fcf8e3;
          }
        }
      }

    .webgl{
      font-family: 本墨锵黑;
      vertical-align: text-bottom;
      color:#409EFF;
      font-size: 17px;
      border-bottom: 1px solid;
    }
    .webgl:hover{
      color: #0789CF;
    }
  }

      .img-wrapper{
        margin-left: 20px;
        height: 80px;
        float: left;
        display: inline-block;
        /*position: fixed;*/
      .img-suchness{
        margin-top: 26px;
        width: auto;
        height: auto;
        max-width: 50%;
        max-height: 100%;
        overflow: hidden;
      }
    }
    .text-wrapper{
      margin-left: -130px;
      display: inline-block;
      .text{
        color:#bfcbd9 ;
        font-size: 30px;
        font-family: 本墨锵黑;
        width: auto;
        height: auto;
        margin: 0 auto;
      }
    }
  }
</style>

