<template>
  <div class="content" @click="toggleClick" :class = "{'hidecontent':!isActive}" title="Toggle sidebar">
    <svg
      :class="{'is-active':isActive}"
      class="svg"
      viewBox="0 0 1024 1024"
      xmlns="http://www.w3.org/2000/svg"
      width="64"
      height="64"
    >
      <path d="M408 442h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8zm-8 204c0 4.4 3.6 8 8 8h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56zm504-486H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 632H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM142.4 642.1L298.7 519a8.84 8.84 0 0 0 0-13.9L142.4 381.9c-5.8-4.6-14.4-.5-14.4 6.9v246.3a8.9 8.9 0 0 0 14.4 7z" />
    </svg>


    <span id="span" class="span" v-if =isActive >收起侧边栏</span>
  </div>
</template>

<script>
  export default {
    name:'HidenBar',
    data(){
      return{
        see : true
      }
    },
    props: {
      isActive: {
        type: Boolean,
        default: false
      }
    },
    computed:{

    },
    methods:{
      toggleClick() {
        this.$emit('toggleClick')
      }
    }
  }
</script>

<style scoped>

  .content{
    cursor: pointer;
    background: #304156;
    width:210px;
    height:50px;
    line-height:50px;
    position: fixed;
    left: 0px;
    bottom: 0px;
    transition: width 0.28s;
  }


  .content:hover{
    background: #263445;
  }


  .svg {
    display: inline-block;
    vertical-align: middle;
    width: 18px;
    height: 18px;
    overflow: hidden;
    margin-right: 16px;
    margin-left: 20px;
  }

  .svg.is-active {
    transform: rotate(180deg);
  }

  .span{
    display: inline-block;
    text-align: center;
    color: #bfcbd9;
  }

  .content.hidecontent{
    transition: width 0.28s;
    width: 54px
  }

</style>
