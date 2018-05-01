package com.socoGameEngine;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class GameRecord{
	final static  String sdpath = "/sdcard/soco/";//sd��·��
	public static void saveGame(String fileNameString,GameSave gs) {
	       try {
	    	 
//	    	   	if (Environment.getExternalStorageState() != null
//	    	   		&&Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// �����������̽�ն��Ƿ���sdcard!
//	    	   		String fileName = sdpath+fileNameString;
//	    	   		Log.v("System", "have SD card");
//	    	   		File path = new File(sdpath);// ����Ŀ¼
//	    	   		File f = new File(fileName);// �����ļ�
//	    	   		if (!path.exists()) {// Ŀ¼���ڷ���false
//	    	   			path.mkdirs();// ����һ��Ŀ¼
//	    	   		}
//	    	   		if (!f.exists()) {// �ļ����ڷ���false
//	    	   			f.createNewFile();// ����һ���ļ�
//	    	   		}
//	    	   		FileOutputStream fos = new FileOutputStream(f);// �����ݴ���sd����
//	    	   		ObjectOutputStream p = new ObjectOutputStream(fos);
//	    	   		gs.writefile(p);
//	    	   		p.flush();
//	    	   		fos.close();
//	    	   		
//	    	   		
//	    	   	}
//	    	   	else{//û�п�
		    	   		
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
//   	 		// �����������̽�ն��Ƿ���sdcard!
//   	   		String fileName = sdpath+fileNameString;
//   	   		Log.v("System", "have SD card");
//   	   		File path = new File(sdpath);// ����Ŀ¼
//   	   		File f = new File(fileName);// �����ļ�
//   	   		if (!path.exists()) {// Ŀ¼���ڷ���false
//   	   			return false;
//   	   		}
//   	   		if (!f.exists()) {// �ļ����ڷ���false
//   	   			return false;
//   	   		}
//   	   		FileInputStream fis = new FileInputStream(f);// �����ݴ���sd����
//   	   		ObjectInputStream p = new ObjectInputStream(fis);
//   	   		gs.loadfile(p);
//   	   		p.close();
//   	   		fis.close();
//   	   	}
//   	   	else{//û�п�
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
