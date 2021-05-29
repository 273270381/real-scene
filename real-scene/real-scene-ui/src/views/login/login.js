
import { isvalidUsername } from '@/utils/validate'
import LangSelect from '@/components/LangSelect'
/*
require('../../dist/index.js')
*/
import VueLazyLoad from 'vue-lazyload'
/* import '../../dist/index.js'*/

/*
import Vue from 'vue'
import progressive from 'progressive-image/dist/vue'

Vue.use(progressive, {
  removePreview: true,
  scale: true,
 /!* lazyClass: 'lazy'*!/
})
*/

export default {
  name: 'login',
  components: { LangSelect },
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error(this.$t('login.errorAccount')))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      /* if (value.length < 5) {
       // callback(new Error(this.$t('login.errorPassword')))
      } else {*/
      callback()
    //  }
    }
    return {
      imgs4: [
        {
          src: require('../../assets/img/bg3.png'),
          srcset: require('../../assets/img/bg10.jpg'),
          preview: require('../../assets/img/content.png')
        }
      ],

      imgs3: [
        {
          src: require('../../assets/img/frame.png'),
          srcset: require('../../assets/img/bg10.jpg'),
          preview: require('../../assets/img/content.png')
        }
      ],

      imgs2: [
        {
          src: require('../../assets/img/biaoti.png'),
          srcset: require('../../assets/img/bg10.jpg'),
          preview: require('../../assets/img/content.png')
        }
      ],

      imgs: [
        {
          src: require('../../assets/img/bg10.jpg'),
          srcset: require('../../assets/img/bg10.jpg'),
          preview: require('../../assets/img/r3.jpg')
        }

      ],
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 2, max: 16, pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '长度在2到16个数字、字母或汉字组合字符', trigger: 'blur' }
        ],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      pwdType: 'password',
      redirect: '/',
      imgLink: require('../../assets/img/bg11.jpg'),
      srcImgLink: require('../../assets/img/bg10.jpg'),
      checked: false
    }
  },
  mounted() {
    this.init(),
    this.getCookie()
  },
  methods: {
    init() {
      const redirect = this.$route.query.redirect

      if (redirect) {
        this.redirect = redirect
      }
    },
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    // 设置cookie
    setCookie(c_name, c_pwd, exdays) {
      var exdate = new Date() // 获取时间
      exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays) // 保存的天数
      // 字符串拼接cookie
      window.document.cookie =
        'userName' + '=' + c_name + ';path=/;expires=' + exdate.toGMTString()
      window.document.cookie =
        'userPwd' + '=' + c_pwd + ';path=/;expires=' + exdate.toGMTString()
    },
    getCookie: function() {
      if (document.cookie.length > 0) {
        var arr = document.cookie.split('; ') // 这里显示的格式需要切割一下自己可输出看下
        for (var i = 0; i < arr.length; i++) {
          var arr2 = arr[i].split('=') // 再次切割
          // 判断查找相对应的值
          if (arr2[0] == 'userName') {
            this.loginForm.username = arr2[1] // 保存到保存数据的地方
          } else if (arr2[0] == 'userPwd') {
            this.loginForm.password = arr2[1]
          }
        }
      }
    }, // 清除cookie
    clearCookie: function() {
      this.setCookie('', '', -1) // 修改2值都为空，天数为负1天就好了
    },
    handleLogin() {
      /* const self = this;
      if (self.checked == true) {
        // console.log("checked == true");
        //传入账号名，密码，和保存天数3个参数
        self.setCookie(self.userInfo.user, self.userInfo.pass, 7);
      } else {
        // console.log("清空Cookie");
        //清空Cookie
        self.clearCookie();
      }*/
      const self = this
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then((data) => {
            this.loading = false

            // if (this.redirect === '/WebGL') {
            // window.location.href = this.$store.state.settings.WebGL
            // } else {
            if (self.checked == true) {
              // 传入账号名，密码，和保存天数3个参数
              self.setCookie(self.loginForm.username, self.loginForm.password, 7)
              // this.$router.push('/')
            } else {
              // console.log("清空Cookie");
              // 清空Cookie
              self.clearCookie()
              // this.$router.push('/')
            }

            window.location.href = this.$store.state.settings.WebGL

            //  this.$router.push({ path: this.redirect })
            // }
          }).catch((err) => {
            this.loading = false
          })
        } else {
          self.clearCookie()
          return false
        }
      })
    }
  }
}
