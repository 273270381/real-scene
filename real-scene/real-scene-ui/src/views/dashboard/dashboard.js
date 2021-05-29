//import { getList } from '@/api/system/notice'
import { mapGetters } from 'vuex'
import ECharts from 'vue-echarts/components/ECharts'
import 'echarts/lib/chart/bar'
import 'echarts/lib/chart/line'
import 'echarts/lib/chart/pie'
import 'echarts/lib/chart/map'
import 'echarts/lib/chart/radar'
import 'echarts/lib/chart/scatter'
import 'echarts/lib/chart/effectScatter'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/polar'
import 'echarts/lib/component/geo'
import 'echarts/lib/component/legend'
import 'echarts/lib/component/title'
import 'echarts/lib/component/visualMap'
import 'echarts/lib/component/dataset'
import 'echarts/map/js/world'
import 'zrender/lib/svg/svg'
import { getLogInfo } from '@/api/system/loginLog'
import AppLink from '@/layout/components/Sidebar/Link'
import { isExternal } from '@/utils/validate'
import path from 'path'
export default {

  name: 'dashboard',
  components: { AppLink },
  props:{
    basePath: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      id:1,
      date:'',
      count:'',
      loginLog:'/loginLog',
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'account',
      'userid'
    ]),
    isHiddened(){
      console.log("count="+this.count)
      if (this.count == 1){
        return true
      } else{
        return false
      }
    }

  },
  created() {
    this.fetchData()
  },
  mounted(){
  },
  watch:{

  },
  methods: {
    fetchData() {
      this.listLoading = true
      const self = this
      setTimeout(function () {
        getLogInfo(self.userid).then(response => {
          self.date = response.data.date
          self.count = response.data.count
        })
      },300)
    },
    resolvePath(routePath) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      return path.resolve(this.basePath, routePath)
    }
  },
}
