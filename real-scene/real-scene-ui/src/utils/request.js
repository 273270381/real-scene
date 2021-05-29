import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import router from '@/router'
 import {message} from "./resetMessage";
// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 25000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    var token = getToken()
    if (token) {
      config.headers['Authorization'] = token // 让每个请求携带自定义token 请根据实际情况自行修改
      console.log('config.headers-----',config.headers)
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data



 if(response.headers.token){
      //如果后台通过header返回token，说明token已经更新，则更新客户端本地token
      store.dispatch('user/updateToken',{token:response.headers.token})
    }
    console.log("res------------",res)

    if(res.code==8888){

      MessageBox.confirm('该账号已经登录', '警告', {
        confirmButtonText: '强制登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var data={username:res.data.username,password: res.data.password,letOut:true}

        store.dispatch('user/login',data).then(() => {
          router.replace({

            path: '/',


          })


        })
      })
    }






    // if the custom code is not 20000, it is judged as an error.
    if (res.code != 20000&&res.code!=8888) {
      /*Message({
        message: '该账号已在异地登录',
        type: 'error',
        duration: 2 * 1000
      })*/

       if(res.code==3333){
         message.error(res.msg)

       }

       if (res.code==9999){
        // message.error(res.msg)
         return res
       }


      store.dispatch('user/logout').then(()=>{
        router.replace({
          path: '/login',
          query:{redirect:router.currentRoute.path}
        })
      })


      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(res)
    } else {
      return res
    }
  },
  error => {
    if(error.response.status === 401){
      store.dispatch('user/logout').then(()=>{
        router.replace({
          path: '/login',
          query:{redirect:router.currentRoute.path}
        })
      })
      return
    }
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error )
  }
)

export default service
