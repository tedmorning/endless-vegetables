package com;

public class NetConfig {

    /**
     * 登陆服务器URL
     */

    //外网服务器
    public static String LOGIN_URL  = "http://veggies.socogame.com:8000/GameCenter/Start";
    //老余的机器
//    public static String LOGIN_URL  = "http://192.168.2.247:8080/GameCenter/Start";
//    //公司内部服务器
//    public static String LOGIN_URL  = "http://192.168.2.247:9090/GameCenter/Start";

    //======================版本相关定义 start=========================//

    /**
     * 版本定义
     * 前部分为大版本号（byte<128），后部分为阶段性版本号（byte<128）
     */
    public static String ver        = "0.01";
    /**
     * 机型
     * 1-安卓通用版：2-ios通用版
     */
    public static byte   mobileType = 1;
    /**
     * 渠道(1-soco，2-fb)
     */
    public static byte   peersite   = 2;
    /**
     * 实际mac地址
     */
    public static String mac        = "0800270094C1";
    /**
     *实际的机型  
     */
    public static String realphone  = "iphone31";

    public static String getVersion() {
        return ver + "." + mobileType + "." + peersite + "." + mac + "." + realphone;
    }

    //======================版本相关定义 end=========================//

    public static int lang = 0;

}
