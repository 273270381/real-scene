
export default {
  data() {
    return {
      activeName: 'profile',
      user:{},
      birthday:''
    }
  },
  mounted() {
    this.init()
    this.getDate()
  },
  methods: {
    init(){
      this.user = this.$store.state.user.profile
    },
    handleClick(tab, event){
      this.$router.push({ path: '/account/'+tab.name})
    },
    getDate(){
      this.birthday = this.user.birthday.toString().substring(0,10);
    }
  }
}
