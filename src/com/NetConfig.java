package com;

public class NetConfig {

    /**
     * ��½������URL
     */

    //����������
    public static String LOGIN_URL  = "http://veggies.socogame.com:8000/GameCenter/Start";
    //����Ļ���
//    public static String LOGIN_URL  = "http://192.168.2.247:8080/GameCenter/Start";
//    //��˾�ڲ�������
//    public static String LOGIN_URL  = "http://192.168.2.247:9090/GameCenter/Start";

    //======================�汾��ض��� start=========================//

    /**
     * �汾����
     * ǰ����Ϊ��汾�ţ�byte<128�����󲿷�Ϊ�׶��԰汾�ţ�byte<128��
     */
    public static String ver        = "0.01";
    /**
     * ����
     * 1-��׿ͨ�ð棺2-iosͨ�ð�
     */
    public static byte   mobileType = 1;
    /**
     * ����(1-soco��2-fb)
     */
    public static byte   peersite   = 2;
    /**
     * ʵ��mac��ַ
     */
    public static String mac        = "0800270094C1";
    /**
     *ʵ�ʵĻ���  
     */
    public static String realphone  = "iphone31";

    public static String getVersion() {
        return ver + "." + mobileType + "." + peersite + "." + mac + "." + realphone;
    }

    //======================�汾��ض��� end=========================//

    public static int lang = 0;

}
