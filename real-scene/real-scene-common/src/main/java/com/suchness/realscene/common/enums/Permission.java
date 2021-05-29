package com.suchness.realscene.common.enums;

/**
 * @author: rs
 * @date: 2020/6/20 8:51
 * @description:
 *权限编码列表<br>
 * 权限编码需要和菜单中的菜单编码一致
 */
public interface Permission {

    //系统管理
    String PIPELINE = "pipeline";
    String PIPENODE = "pipenode";
    String CFG_DEL = "cfgDelete";
    String DICT = "dict";
    String DICT_EDIT = "dictEdit";
    String LOG = "log";
    String LOG_CLEAR = "logClear";
    String LOGIN_LOG = "loginLog";
    String LOGIN_LOG_CLEAR = "loginLogClear";
    String ROLE = "role";
    String ROLE_EDIT = "roleEdit";
    String ROLE_DEL = "roleDelete";
    String TASK = "task";
    String TASK_EDIT = "taskEdit";
    String TASK_DEL = "taskDelete";
    String MENU = "menu";
    String MENU_EDIT = "menuEdit";
    String MENU_DEL = "menuDelete";
    String USER = "mgr";
    String USER_EDIT = "mgrEdit";
    String USER_DEL = "mgrDelete";
    String USER_RESET_PASSWORD = "mgrReset";
    String DEPT = "dept";
    String DEPT_EDIT = "deptEdit";
    String DEPT_DEL = "deptDelete";

    //消息管理
    String MSG = "msg";
    String MSG_CLEAR = "msgClear";
    String MSG_SENDER = "msgSender";
    String MSG_SENDER_EDIT = "msgSenderEdit";
    String MSG_SENDER_DEL = "msgSenderDelete";
    String MSG_TPL = "msgTpl";
    String MSG_TPL_EDIT = "msgTplEdit";
    String MSG_TPL_DEL = "msgTplDelete";

    //通知管理
    String NOTICE = "notice";
    String NOTICE_EDIT = "editNotice";
    String NOTICE_DEL = "deleteNotice";


    //企业管理
    String COMPANY = "company";
    String COMPANY_EDIT = "companyEdit";
    String COMPANY_DEL = "companyDelete";

    //摄像头管理
    String CAMERA = "camera";
    String CAMERA_EDIT = "cameraEdit";
    String CAMERA_DEL = "cameraDelete";

}
