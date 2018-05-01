package com.socogame.coolEdit;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;

import android.util.Log;

import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.MainActivity;

public class CoolEditData {
	public static String[][] imageName = null;
	public static short[][][] nDrawPos = null;
	public static short[][][][] npcItem0 = null;
		
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(" CoolEditData init success!!");
	}

	public static void init() {
		try {
			// File file = null;
			DataInputStream dis = null;
			String ResPath = "";
			// try {
			// file = new File(ResPath + "data.dat");
			// dis = new DataInputStream(new FileInputStream(file));
			// } catch (Exception e) {
			// ResPath =
			// "E:\\android\\workspace\\EndlessVegetables2\\ResourceManager\\output\\";
			// file = new File(ResPath + "data.dat");
			// dis = new DataInputStream(new FileInputStream(file));
			// }
			InputStream inputStream = MainActivity.getActivity().getResources().getAssets().open("data.dat");
			dis = new DataInputStream(inputStream);
			int max = dis.readInt() + 1;
			imageName = new String[max][];
			nDrawPos = new short[max][][];
			npcItem0 = new short[max][][][];
			while (true) {
				try {
					short id = dis.readShort();
					short len = dis.readShort();
					String imageName1[] = new String[len];
					for (int i = 0; i < imageName1.length; i++) {
						len = dis.readShort();
						char[] chars = new char[len];
						for (int j = 0; j < len; j++) {
							chars[j] = dis.readChar();
						}
						imageName1[i] = new String(chars);
					}
					imageName[id] = imageName1;
//					if (SpriteLibrary.imageName.length > id)
//						SpriteLibrary.imageName[id] = imageName[id];
					len = dis.readShort();
					short nDrawPos1[][] = new short[len][];
					for (int i = 0; i < nDrawPos1.length; i++) {
						len = dis.readShort();
						nDrawPos1[i] = new short[len];
						for (int j = 0; j < nDrawPos1[i].length; j++) {
							nDrawPos1[i][j] = dis.readShort();
						}
					}
					nDrawPos[id] = nDrawPos1;
//					if (SpriteLibrary.nDrawPos.length > id)
//						SpriteLibrary.nDrawPos[id] = nDrawPos[id];
					len = dis.readShort();
					short npcItem01[][][][] = new short[len][][][];
					for (int i = 0; i < npcItem01.length; i++) {
						len = dis.readShort();
						npcItem01[i] = new short[len][][];
						for (int j = 0; j < npcItem01[i].length; j++) {
							len = dis.readShort();
							npcItem01[i][j] = new short[len][];
							for (int k = 0; k < npcItem01[i][j].length; k++) {
								len = dis.readShort();
								npcItem01[i][j][k] = new short[len];
								for (int l = 0; l < npcItem01[i][j][k].length; l++) {
									npcItem01[i][j][k][l] = dis.readShort();
								}
							}
						}
					}
					npcItem0[id] = npcItem01[0];
//					if (SpriteLibrary.npcItem0.length > id)
//						SpriteLibrary.npcItem0[id] = npcItem0[id];
				} catch (EOFException e) {
					break;
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Log.e("","");
	}
}