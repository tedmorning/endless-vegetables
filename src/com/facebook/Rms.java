//package com.facebook;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class Rms {
//	public SharedPreferences sp;
//	public final static String PREF = "MY_PREF_";
//
//	private static Rms mRms;
//	public static Rms getInstance(Context mContext) {
//		if (mRms == null) {
//			mRms = new Rms(mContext, mContext.getPackageName());
//		}
//		return mRms;
//	}
//
//	public Rms(Context act, String rmsnum) {
//		sp = act.getSharedPreferences(PREF + rmsnum, 0);
//	}
//
//	public static void save(Context act, int rmsnum, String[] key) {
//		SharedPreferences sp = act.getSharedPreferences(PREF + rmsnum, 0);
//		SharedPreferences.Editor editor = sp.edit();
//		for (int i = 0; i < key.length / 2; i++)
//			editor.putString(key[i * 2], key[i * 2 + 1]);
//		editor.commit();
//	}
//
//	public static Map<String, String> read(Context act, int rmsnum, String[] key) {
//		Map<String, String> map = new HashMap<String, String>();
//
//		SharedPreferences sp = act.getSharedPreferences(PREF + rmsnum, 0);
//		if (sp != null)
//			for (int i = 0; i < key.length / 2; i++) {
//				String temp = sp.getString(key[i * 2], null);
//				key[i * 2 + 1] = temp;
//				map.put(key[i * 2], key[i * 2 + 1]);
//			}
//
//		return map;
//	}
//
//	public static void save(Context act, String rmsnum, String[] key) {
//		SharedPreferences sp = act.getSharedPreferences(PREF + rmsnum, 0);
//		SharedPreferences.Editor editor = sp.edit();
//		for (int i = 0; i < key.length / 2; i++)
//			editor.putString(key[i * 2], key[i * 2 + 1]);
//		editor.commit();
//	}
//
//	public static Map<String, String> read(Context act, String rmsnum,
//			String[] key) {
//		Map<String, String> map = new HashMap<String, String>();
//		SharedPreferences sp = act.getSharedPreferences(PREF + rmsnum, 0);
//		if (sp != null)
//			for (int i = 0; i < key.length / 2; i++) {
//				String temp = sp.getString(key[i * 2], null);
//				key[i * 2 + 1] = temp;
//				map.put(key[i * 2], key[i * 2 + 1]);
//			}
//		return map;
//	}
//
//	public void saveInt(String key, int value) {
//
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putInt(key, value);
//		editor.commit();
//	}
//
//	public int readInt(String key, int value) {
//		return sp.getInt(key, value);
//	}
//
//	public void saveString(String key, String value) {
//
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putString(key, value);
//		editor.commit();
//	}
//
//	public String readString(String key, String value) {
//		return sp.getString(key, value);
//	}
//
//	public void saveBoolean(String key, boolean value) {
//
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putBoolean(key, value);
//		editor.commit();
//	}
//
//	public boolean readBoolean(String key, boolean value) {
//		return sp.getBoolean(key, value);
//	}
//
//	public void saveLong(String key, long value) {
//
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putLong(key, value);
//		editor.commit();
//	}
//
//	public long readLong(String key, long value) {
//		return sp.getLong(key, value);
//	}
//
//	public void saveFloat(String key, float value) {
//
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putFloat(key, value);
//		editor.commit();
//	}
//
//	public float readFloat(String key, float value) {
//		return sp.getFloat(key, value);
//	}
//
//}