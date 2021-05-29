<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
    <div class="side-hamburger">
      <hamburger :is-active="sidebar.opened" class="hamburger-container" :class = "removeContainer" @toggleClick="toggleSideBar" />
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
import Hamburger from '@/components/Hamburger'

export default {
  components: { SidebarItem, Logo, Hamburger },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      return this.$store.state.menu.routes
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if st path, the sidebar will highlight the path you sete
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    },
    removeContainer(){
      return{
        removecontainer:!this.sidebar.opened,
        hide:this.device === 'mobile'
      }
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    }
  }
}
</script>
<style lang="scss" scoped>
  .hamburger-container{
    transition-duration: 0.3s;
    position: fixed;
    top:50%;
    left: 12%;
    overflow: hidden;
  }
  .hamburger-container.removecontainer{
    transition-duration: 0.3s;
    left: 54px;
  }
</style>
