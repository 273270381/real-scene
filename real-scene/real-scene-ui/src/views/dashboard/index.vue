<template>
  <div class="dashboard-container" id="dashboard">
    <div class="account-info">

      <div class="media-left">
        <div class = "avatar avatar-online">
          <img src="@/assets/img/avatarnin.svg" alt="admin">
          <i class="avatar avatar-busy"></i>
        </div>
      </div>

      <div class="media-body" >
       <h4 class="media-heading">欢迎您, {{account}} <small>{{name}}</small></h4>
        <p>
          <i class="icon icon-color wb-bell" aria-hidden="true"></i>这是您第{{count}}次登陆，
          <a :class="{'a-hidden':isHiddened}">上次登陆日期:{{date}}，</a>详细信息请查看
          <app-link  :to="resolvePath(loginLog)"><a>日志</a></app-link>
          ,如果不是您本人登陆，请及时
          <router-link to="/account/updatePwd"><a>修改密码。</a></router-link>
        </p>
      </div>
    </div>

  </div>
</template>


<script src="./dashboard.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  .a-hidden{
    display: none;
  }
  .el-row{
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }

.dashboard {

  &-container {
    padding: 15px;
    background-color: #f0f2f5;
  }
  &-text {
    font-size: 14px;
    line-height: 22px;
    padding-bottom:15px;
  }
}
.echarts{
  width: 100%;
  height: 100%;
}
.box-card{
  height:108px;
}
  .chart{
    height: 350px;
  }
  .section{
    padding:20px;
    background-color: white;
    border: 1px solid #ebeef5;
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  }



  .box-card > div >div > .svg-icon {
    width: 4em;
    height: 4em;
    color:#34bfa3;
  }
  .card-panel-text{
    padding-top:10px;
    font-size:16px;
    color:gray;
  }
  .card-panel-num{
    padding-top:10px;
    font-size:20px;
    font-weight: bold;
  }
  .container-title h1{
    margin-top: 4px;
    font-size: 36px;
    color: rgb(31,135,232);
  }
  .p{
    text-indent: 2em;
    font-size: 18px;
    color: #666666;
    line-height: 30px;
  }
  .p1{
    text-indent: 2em;
    font-size: 14px;
    color: #666666;
    line-height: 20px;
  }

  .detail{
    float: right;
    font-size: 14px;
    color: #409EFF;
    padding: 10px;
  }
  .detail-a{
    float: right;
    font-size: 14px;
    color: #409EFF;
    margin-top: 40px;
  }
  .detail:hover{
    color: rgb(31,135,232);
  }
  .container-pic{
    width: 25%;
    float: left;
    margin-bottom: 20px;
  }
  .pic{
    width: 250px;
    height: auto;
  }
  .container-room{
    width: 75%;
    float: right;
  }
  .section-pic{
    width: 25%;
    float: left;
    margin-top: 5px;
  }
  .pic2{
    width: 125px;
    height: auto;
  }


  .avatar {
    position: relative;
    display: inline-block;
    width: 40px;
    white-space: nowrap;
    vertical-align: bottom;
    border-radius: 1000px;
  }
  .avatar img {
    width: 100%;
    max-width: 100%;
    height: auto;
    border: 0 none;
    border-radius: 1000px;
  }
  img {
    vertical-align: middle;
  }
  .avatar i {
    position: absolute;
    right: 0;
    bottom: 0;
    width: 10px;
    height: 10px;
    border: 2px solid #fff;
    border-radius: 100%;
  }
  .media-left, .media > .pull-left{
    padding-right: 10px;
  }
  .media-left, .media-right, .media-body {
    display: table-cell;
    vertical-align: top;
  }
  .avatar-online i {
    background-color: #46be8a;
  }

 .account-info {
    overflow: visible;
    border-bottom: none;
  }
  .media {
    padding: 10px 0;
    margin-top: 0;
    border-bottom: 1px solid #e4eaec;
  }
  .media:first-child {
    margin-top: 0;
  }
  .media-body {
    width: 10000px;
    font-family: "Helvetica Neue", Helvetica, Tahoma, Arial, "Microsoft Yahei", "Hiragino Sans GB", "WenQuanYi Micro Hei", sans-serif;
    font-size: 14px;
    line-height: 1.6;
    color: #76838f;
  }
  .media-heading {
    margin-top: 0;
    margin-bottom: 5px;
  }

  h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
    font-weight: 500;
  }
  h4, .h4 {
    font-size: 16px;
  }
  h4, .h4, h5, .h5, h6, .h6 {
    margin-top: 11px;
    margin-bottom: 11px;
  }
  h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
    font-family: "Helvetica Neue", Helvetica, Tahoma, Arial, "Microsoft Yahei", "Hiragino Sans GB", "WenQuanYi Micro Hei", sans-serif;
    font-weight: 500;
    line-height: 1.2;
    color: #37474f;
  }
  h4 small, .h4 small, h5 small, .h5 small, h6 small, .h6 small, h4 .small, .h4 .small, h5 .small, .h5 .small, h6 .small, .h6 .small {
    font-size: 75%;
  }

  h1 small, h2 small, h3 small, h4 small, h5 small, h6 small, .h1 small, .h2 small, .h3 small, .h4 small, .h5 small, .h6 small, h1 .small, h2 .small, h3 .small, h4 .small, h5 .small, h6 .small, .h1 .small, .h2 .small, .h3 .small, .h4 .small, .h5 .small, .h6 .small {
    font-weight: normal;
    line-height: 1;
    color: #a3afb7;
  }
  small, .small {
    font-size: 85%;
  }
  small {
    font-size: 80%;
  }
  .icon-color {
    color: rgba(55, 71, 79, .4);
  }
  .icon {
    position: relative;
    display: inline-block;
    font-style: normal;
    font-weight: normal;
    line-height: 1;
    -webkit-transform: translate(0, 0);
    -ms-transform: translate(0, 0);
    -o-transform: translate(0, 0);
    transform: translate(0, 0);
    text-rendering: auto;
    speak: none;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
  div {
    display: block;
  }
  p {
    margin: 0 0 11px;
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    margin: 0 0 11px;
  }
  a{
    color: #62a8ea;
  }
.dashboard-container{
  margin-left: 15px;
  margin-right: 15px;
}
</style>
