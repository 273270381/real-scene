<template xmlns:v-lazy="http://www.w3.org/1999/xhtml">

  <div class="login-container" >
<!--
  <div class="login-container" v-lazy:background-image="imgLink" >
-->

<!--
    <img class="lazy" v-lazy="/img/background.png">
-->
    <!--<div class="progressive">
      <img class="preview lazy" data-src="../../assets/img/bg10.jpg" src="../../assets/img/bg11.jpg"  />
    </div>-->

<!--
    <img src="/img/frame.png" style=" display:flex; justify-content:center; align-items:center;width:560px;height:330px;"   />
-->

      <div class="progressive" v-for="item in imgs">
        <img class="preview" v-progressive="item.src" :src="item.preview" />
      </div>


    <div  style="position:absolute;top:15%;width:40%;height: 7%"  >
    <div class="progressive_preview" v-for="item in imgs2">
      <img class="preview" v-progressive_preview="item.src" :src="item.preview" />
    </div>
    </div>
  <!--  <img src="/img/biaoti.png" style="position:absolute;top:15%;width:40%;height: 7%"   />-->


    <div  style="position:absolute;top:31.5%;width:560px;height:330px;" >
      <div class="progressive_preview" v-for="item in imgs3">
        <img class="preview" v-progressive_preview="item.src" :src="item.preview" />
      </div>
    </div>


    <div  style="position:absolute;top:33%;width:530px;height:300px"  >
      <div class="progressive_preview" v-for="item in imgs4">
        <img class="preview" v-progressive_preview="item.src" :src="item.preview" />
      </div>
    </div>
    <!--<img src="/img/frame.png" style="position:absolute;top:30%;width:30%;height:36%;"   />
    <img src="/img/bg3.png" style="position:absolute;top:31.5%;width:28%;height:33%"   />-->

    <!--<div class="descendant-A">
      <div class="descendant-B">-->
    <div style="position:absolute;top:31.5%;width:560px;height:330px;">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="off" label-position="left" >

          <!--  <div class="title-container">
              <h3 class="title">{{ $t('login.title') }}</h3>
             &lt;!&ndash; <lang-select class="set-language"/>&ndash;&gt;
            </div>-->

          <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
            <el-input
              v-model="loginForm.username"
              :placeholder="$t('login.username')"
              name="username"
              type="text"
              auto-complete="off"
            />
          </el-form-item>

          <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
            <el-input
              :type="pwdType"
              v-model="loginForm.password"
              :placeholder="$t('login.password')"
              name="password"
              auto-complete="off"
              @keyup.enter.native="handleLogin" />
<!--
            <span class="show-pwd" @click="showPwd">
-->
          <svg-icon icon-class="eye" />
        <!--</span>-->

          </el-form-item>
          <el-checkbox v-model="checked" style="color:#a0a0a0;margin-bottom:30px">记住密码</el-checkbox>
          <el-button :loading="loading" type="primary" style="width:50%;margin-bottom:30px;" @click.native.prevent="handleLogin">{{ $t('login.logIn') }}</el-button>
        </el-form>
    </div>
<!--
      </div>
-->
<!--    </div>-->
<!--
    <img src="/img/background2.png" style="position:absolute;bottom:0px;right:0px"width="100%" />
-->
    </div>

</template>

<!--<script>
  import  '../../dist/index.js'
</script>-->
<script type="text/javascript" src="http://jileiba.com/wp-includes/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="http://jileiba.com/wp-includes/js/jquery/jquery.lazyload.js"></script>
<script src="./login.js"></script>
<style rel="stylesheet/scss" lang="scss" src="./login.scss">



</style>

<style>
  * {
    box-sizing: border-box;
    padding: 0;
    margin: 0;
  }

  main {
    width: 70%;
    max-width: 750px;
    margin: 0 auto;
  }

  h1{
    margin: 20px 0;
    text-align: center;
  }

  img {
    max-width: 100%;
  }

  .full {
    width: 100vw;
    left: 50%;
    margin-left: -50vw;
  }

  .space {
    width: 100%;
    height: 200px;
  }
</style>

