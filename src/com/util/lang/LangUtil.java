package com.util.lang;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.NetConfig;
import com.androidnet.MainActivity;
import com.kokatlaruruxi.wy.R;
import com.kokatlaruruxi.wy.Main;
import com.net.Decode;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameManager;

/**
 * 多语言处理
 * 
 * @author Administrator
 * 
 */
public class LangUtil {
    /** 默认语言 */
//    public static int                       defautlLang      = 0;
    /** 预定义支持的多语言缩写 */
    public static String                    langSub[]        = {
        "CH", "EN", "JP", "HKTS", "KO"                      };

    /** 存放多语言文本 */
    public static HashMap<String, String>[] langStringClient = new HashMap[langSub.length];
    public static HashMap<String, String>[] langStringServer = new HashMap[langSub.length];

    /*
     * 设置语言id
     * */
    private static void setLanId()
    {
    	//自动判断语言
		String string=Main.getActivity().getString(R.string.language);
		
		if(string.equals("zh")){
			NetConfig.lang = 0;
		}else if(string.equals("tw")){
			NetConfig.lang = 3;
		}else if(string.equals("ko")){
			NetConfig.lang = 4;
		}else if(string.equals("ja")){
			NetConfig.lang = 2;
		}else if(string.equals("fr")){
			
		}else if(string.equals("it")){
			
		}else if(string.equals("de")){
			
		}else if(string.equals("es")){
			NetConfig.lang = 1;
		}else{
			System.out.println("not this language:"+string);
			NetConfig.lang = 1;
		}
    }
    
    
    /**
     * 初始化多语言文本
     */
    public static void init() {    
    	
    	setLanId();
    	
        try {
            loadLangString(langStringServer, "Server");
            loadLangString(langStringClient, "Client");
        } catch (Exception e) {
            System.out.println("加载多语言文本出错");
        }
    }

    /*
     * 客户端用
     * */
    public static String getLangString(String str) {
    	return getLangString(NetConfig.lang, str);
    }
    
    public static String getLangString(int lang, String str) {
    	if (str.startsWith("@Client@"))
    		return getLangStringClient(lang, str);

    	if (str.startsWith("@Server@"))
    		return getLangStringServer(lang, str);
    	
    	String old = str;    	
        String temp = getLangStringServer(lang, str);
        if (temp != null && temp != old)
            return temp;
        temp = getLangStringClient(lang, str);
        if (temp != null && temp != old)
            return temp;
        return str;
    }

    public static String getLangStringServer(int lang, String str) {    	
        String temp = null;
        if (str.startsWith("@Server@"))
        	str = str.substring(7);
        if (str.startsWith("@"))
            temp = langStringServer[lang].get(str.substring(1));
        else
            temp = langStringServer[lang].get(str);
        if (temp != null)
            return temp;
        return str;
    }

    public static String getLangStringClient(int lang, String str) {
        String temp = null;
        if (str.startsWith("@Client@"))
        	str = str.substring(7);
        if (str.startsWith("@"))
            temp = langStringClient[lang].get(str.substring(1));
        else
            temp = langStringClient[lang].get(str);
        if (temp != null)
            return temp;
        return str;
    }

    /**
     * 将文本加载到内存
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void loadLangString(HashMap[] temp, String tip) throws FileNotFoundException, IOException {
        for (int i = -1; ++i < langSub.length;) {
            DataInputStream strDis = new DataInputStream(Main.getActivity().getResources().getAssets().open("lang/" + tip + "Str_" + langSub[i] + ".dat"));
            byte zip = strDis.readByte();
            byte[] data = new byte[strDis.available()];
            strDis.read(data);
            if (zip == 1)
                data = new Decode().inflate(data);
            strDis.close();

            strDis = new DataInputStream(new ByteArrayInputStream(data));
            int size = strDis.readInt();

            DataInputStream defDis = new DataInputStream(Main.getActivity().getResources().getAssets().open("lang/" + tip + "Str_Def.dat"));
            byte defzip = defDis.readByte();
            byte[] defdata = new byte[defDis.available()];
            defDis.read(defdata);
            if (defzip == 1)
                defdata = new Decode().inflate(defdata);
            defDis.close();

            defDis = new DataInputStream(new ByteArrayInputStream(defdata));
            int defsize = defDis.readInt();

            temp[i] = new HashMap<String, String>();
            for (int j = -1; ++j < size;) {
                String defStr = readString(defDis);
                String valueStr = readString(strDis);
                temp[i].put(defStr, valueStr);
                // System.out.println(defStr + "=" + valueStr);
            }
            strDis.close();
            defDis.close();
        }
    }

    public static String readString(DataInputStream defDis) throws IOException {
        StringBuffer buf = new StringBuffer();
        int len = defDis.readShort();
        for (int i = 0; i < len; i++)
            buf.append(defDis.readChar());
        return buf.toString();
    }
}
