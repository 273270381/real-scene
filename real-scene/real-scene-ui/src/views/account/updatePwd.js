import { updatePwd } from '@/api/user'


export default {
  data() {
    var validatePassword1  = (rule, value, callback) => {
        if (value === this.form.oldPassword){
          callback(new Error('新密码不能与原密码相同!'))
        }else{
          callback()
        }
      }
    var validatePassword2 = (rule, value, callback) => {
      if (value !== this.form.password){
        callback(new Error('与新密码不一致!'))
      }else{
        callback()
      }
    }
    return {
      form: {
        oldPassword: '',
        password: '',
        rePassword: ''
      },
      activeName: 'updatePwd',
      user: {},
      rules:{
        oldPassword: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 5, max: 100, message: '密码长度不能小于5', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度不能小于6', trigger: 'blur' },
          {validator: validatePassword1, trigger: 'blur'}
        ],
        rePassword: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度不能小于6', trigger: 'blur' },
          {validator: validatePassword2, trigger: 'blur'}
        ]
      }
    }
  },

  computed: {

  },
  mounted() {
    this.init()
  },
  methods: {
    init(){
      this.user = this.$store.state.user.profile
    },
    handleClick(tab, event){
      this.$router.push({ path: '/account/'+tab.name})
    },
    updatePwd() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updatePwd({
            oldPassword: this.form.oldPassword,
            password: this.form.password,
            rePassword: this.form.rePassword
          }).then( response => {
            console.log("response=",response)
            console.log("code=",response.code)
            if (response.code == 20000){
              this.$message({
                message: '密码修改成功',
                type: 'success'
              })
              //退出登录，该操作是个异步操作，所以后面跳转到登录页面延迟1s再执行（如果有更好的方法再调整）
              this.$store.dispatch('user/logout')
              const self = this
              setTimeout(function(){
                self.$router.push(`/login`)
              },1000)
            }
          }).catch((err) => {
            console.log("error="+err)
          })
        } else {
          return false
        }
      })
    }

  }
}
