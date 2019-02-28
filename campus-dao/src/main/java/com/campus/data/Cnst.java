package com.campus.data;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-05-16 12:35
 */
public class Cnst {
    /*日程类型**/
    public  static  final  Short SCHEDULE_STATE_END =  2 ;//已结束
    /***七牛云IP**/
    public  static  String QINIU_BASE_URL =  "http://static.cjqc.com";
    /**默认的运动时间间隔**/
    public  static  Integer SPORT_TIME_SPACE =  5;
    /**默认图片**/
    public static String DefaultTrainerImg = "http://static.cjqc.com/campus/defaultImg/defaultTrainer.png";
    public static String DefaultTeamImg = "/adminStyles/images/defaultTeamImg.png";
    /***最快心率默认值***/
    public static  Integer HIS_FAST_HEART_RATE = 188;//历史最快心率默认值、
    public static  Integer SLOW_HEART_RATE = 80;//历史最快心率默认值
    public static  Integer FAST_HEART_RATE = 120;//历史最快心率默认值
    /***无效数据****/
    public static  Integer INVALID_DATA = -1 ;//无效数据

    /**短信**/
    public static  String smsUrl="http://222.73.117.156/msg/";
    public static String smsUsername="chaojqc";
    public static String smsPassword="Cjqc165823";

    //手机短息类型
    public final static class SmsCodeType {
        public final static int register = 1;//注册
        public final static int editPassword = 2;//修改密码
        public final static int thirdBinding = 3;//第三方绑定
        public final static int changePhone = 4;//变更手机号
        public final static int refereeResetPassword = 5;//裁判重置密码
    }

    /**
     * 正则
     */
    public final static class Regex {
        public final static String phone = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
        public final static String password = "^[0-9a-zA-Z_]{6,16}$";
        public final static String numberArray = "(\\d+[,\\d+]?)+";
        public final static String idCard = "^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X)$";
    }

    public final static String LOGIN_SMS_KEY = "loginSMSKey_";
    public final static String LOGIN_SMS_SECS_KEY = "loginSMSSecsKey_";
    public final static int SMS_SECS_NUM = 60;
    //密码错误错误次数
    public final static String PASSWORD_ERROR_COUNT = "passwordErrorCount_";
    public final static int ERROR_NUM = 5;
    public final static String PASSWORD_SECRET_KEY = "passwordSecretKey_";

    /**
     * 上传图片缩放大小
     */
    public static String UPLOAD_FORUM = ""; // 动态，帖子图片
    public static String UPLOAD_FIELD = ""; // 球场图片
    public static String UPLOAD_PLAYER = "";//用户，球队图片
    public static String UPLOAD_DEFAULT = "";//默认大小

    public static void setUPLOAD_FORUM(String UPLOAD_FORUM) {
        Cnst.UPLOAD_FORUM = UPLOAD_FORUM;
    }

    public static void setUPLOAD_FIELD(String UPLOAD_FIELD) {
        Cnst.UPLOAD_FIELD = UPLOAD_FIELD;
    }

    public static void setUPLOAD_PLAYER(String UPLOAD_PLAYER) {
        Cnst.UPLOAD_PLAYER = UPLOAD_PLAYER;
    }

    public static void setUPLOAD_DEFAULT(String UPLOAD_DEFAULT) {
        Cnst.UPLOAD_DEFAULT = UPLOAD_DEFAULT;
    }

    public static String imgFormat(String imgs){
        if(imgs != null && !imgs.isEmpty()){
            String[] imgArr = imgs.split(";");
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<imgArr.length;i++){
                String temp = imgArr[i];
                if(!temp.contains("http")){
                    temp = QINIU_BASE_URL.concat(temp);
                }
                if(i==0){
                    sb.append(temp);
                }else{
                    sb.append(";");
                    sb.append(temp);
                }
            }
            imgs = sb.toString();
        }
        return imgs;
    }
}
