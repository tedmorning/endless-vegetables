package com.socoGameEngine;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class GameRecord{
	final static  String sdpath = "/sdcard/soco/";//sd卡路径
	public static void saveGame(String fileNameString,GameSave gs) {
	       try {
	    	 
//	    	   	if (Environment.getExternalStorageState() != null
//	    	   		&&Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 这个方法在试探终端是否有sdcard!
//	    	   		String fileName = sdpath+fileNameString;
//	    	   		Log.v("System", "have SD card");
//	    	   		File path = new File(sdpath);// 创建目录
//	    	   		File f = new File(fileName);// 创建文件
//	    	   		if (!path.exists()) {// 目录存在返回false
//	    	   			path.mkdirs();// 创建一个目录
//	    	   		}
//	    	   		if (!f.exists()) {// 文件存在返回false
//	    	   			f.createNewFile();// 创建一个文件
//	    	   		}
//	    	   		FileOutputStream fos = new FileOutputStream(f);// 将数据存入sd卡中
//	    	   		ObjectOutputStream p = new ObjectOutputStream(fos);
//	    	   		gs.writefile(p);
//	    	   		p.flush();
//	    	   		fos.close();
//	    	   		
//	    	   		
//	    	   	}
//	    	   	else{//没有卡
		    	   		
			    	   	  OutputStream ostream = MainActivity.getActivity().openFileOutput(fileNameString, 0);
			    	   	  ObjectOutputStream p = new ObjectOutputStream(ostream);
			    	   	  gs.writefile(p);
			    	   	  p.flush();
			    	  	  ostream.close();
//	    	   		}
				        } catch (Exception e) {
				        	e.printStackTrace();
				        	LogShow.d("Error saving file:");
				        	LogShow.d(e.getMessage());
			        }
	       			
		    }
	public static boolean loadGame(String fileNameString,GameSave gs) {
		 try {
   	  
//   	 	if (Environment.getExternalStorageState() != null
//   	 			&&Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//   	 		// 这个方法在试探终端是否有sdcard!
//   	   		String fileName = sdpath+fileNameString;
//   	   		Log.v("System", "have SD card");
//   	   		File path = new File(sdpath);// 创建目录
//   	   		File f = new File(fileName);// 创建文件
//   	   		if (!path.exists()) {// 目录存在返回false
//   	   			return false;
//   	   		}
//   	   		if (!f.exists()) {// 文件存在返回false
//   	   			return false;
//   	   		}
//   	   		FileInputStream fis = new FileInputStream(f);// 将数据存入sd卡中
//   	   		ObjectInputStream p = new ObjectInputStream(fis);
//   	   		gs.loadfile(p);
//   	   		p.close();
//   	   		fis.close();
//   	   	}
//   	   	else{//没有卡
   	   		InputStream instream = MainActivity.getActivity().openFileInput(fileNameString);
	        ObjectInputStream p = new ObjectInputStream(instream);
	        gs.loadfile(p);
	        p.close();
	    	instream.close();
//   	   	}
   	 	return true;
	        } catch (Exception e) {
	        	LogShow.d("Error loading file:");
	        	LogShow.d(e.toString());
	            return false;
	        }
	    }
	
	
}
