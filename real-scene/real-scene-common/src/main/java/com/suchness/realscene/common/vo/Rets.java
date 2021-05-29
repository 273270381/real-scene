package com.suchness.realscene.common.vo;

import java.util.Map;

public class Rets {

    public static final Integer SUCCESS = 20000;
    public static final Integer FAILURE = 9999;
    public static  final Integer TOKEN_EXPIRE=50014;
    public  static  final  Integer IS_LOGINED=8888;
    public  static  final  Integer   failureLogin=3333;

    public static Ret success(Object data) {
        return new Ret(Rets.SUCCESS, "成功", data);
    }

    public static Ret failure(String msg) {
        return new Ret(Rets.FAILURE, msg, null);
    }
    public static Ret failureLogin(String msg) {
        return new Ret(Rets.failureLogin, msg, null);
    }


    public  static  Ret isLogined(String msg, Map<String,String>map){
        return new Ret(Rets.IS_LOGINED, msg, map);

    }
    public static Ret success() {
        return new Ret(Rets.SUCCESS, "成功", null);
    }
    public static  Ret expire(){
        return new Ret(Rets.TOKEN_EXPIRE,"token 过期",null);
    }
}
