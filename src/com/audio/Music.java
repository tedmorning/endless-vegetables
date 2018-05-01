package com.audio;

import java.io.IOException;

import com.socoGameEngine.LogShow;
import com.socoGameEngine.MainActivity;


import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * 音乐文件
 * @author caishunqi
 *
 */

public class Music {
	
	protected static MediaPlayer mediaPlayer;
	protected static boolean mustResume = false;
	protected static boolean b_loop = false;
	private static void prepareMediaPlayer() {
		if(mediaPlayer == null) {
			LogShow.d("prepareMediaPlayer");
			mediaPlayer = new MediaPlayer();
		}
	}
	
	/**
	 * Plays a file from assets, no looping
	 * 
	 * @param file valid path to a file in assets
	 * @param b_play true to play immediately
	 */
	public static void play(String file,boolean b_play) {
		play(file, true,b_play);
	}
	/**
	 * Plays a file from assets, no looping
	 * 
	 * @param id  music id for R
	 * @param b_play true to play immediately
	 */
	public static void play(int ID,boolean b_play) {
		play(ID, true,b_play);
	}
	/**
	 * Plays a file from raw
	 * 
	 * @param id  music id from R
	 * @param loop TRUE to loop the file, FALSE to play once
	 * @param b_play True to play music immediately. False to play by manual
	 */
	public static void play(int ID, boolean loop,boolean b_play) {
		prepareMediaPlayer();
		try {
			mediaPlayer = MediaPlayer.create(MainActivity.getActivity().getApplicationContext(), ID);//初始化MediaPlayer
			
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			 if (mediaPlayer != null) { 
				 mediaPlayer.stop();
		    }
		}  catch (IllegalStateException e) {
			LogShow.d(e.toString());
			LogShow.d("Error setting data source in Music.play, fail ...");
			return;
		}
		try {
			mediaPlayer.prepare();	
			b_loop = loop;
			mediaPlayer.setLooping(loop);
		} catch (Exception e) { 
			e.printStackTrace();
			LogShow.d(e.toString());
			LogShow.d("Error preparing MediaPlayer");
			
			return;
		}
		if(b_play)
		mediaPlayer.start();
	}
	
	/**
	 * Plays a file from assets
	 * 
	 * @param file valid path to a file in assets
	 * @param loop TRUE to loop the file, FALSE to play once
	 */
	public static void play(String file, boolean loop,boolean b_play) {
		prepareMediaPlayer();
		AssetFileDescriptor afd = null;
		try {
			afd = MainActivity.getActivity().getAssets().openFd(file);
		} catch (Exception e) { 
			LogShow.d(e.toString());
			LogShow.d("Tried creating RokonMusic with missing asset ... " + file);
			
			return;
		}
		try {
			mediaPlayer.reset();
			mediaPlayer.setLooping(loop);
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			afd.close();
		} catch (IOException e) {
			LogShow.d(e.toString());
			LogShow.d("Error setting data source in RokonMusic.play, IO exception");
	

			return;
		} catch (IllegalStateException e) {
			LogShow.d(e.toString());
			LogShow.d("Error setting data source in RokonMusic.play, fail ...");
			return;
		}
		try {
			mediaPlayer.prepare();	
		} catch (Exception e) { 
			LogShow.d(e.toString());
			LogShow.d("Error preparing MediaPlayer");
			
			return;
		}
		if(b_play)
		mediaPlayer.start();
	}
	
	/**
	 * Plays the current music file, if paused or stopped
	 */
	public static void play() {
		if(mediaPlayer == null) 
			return;
		if(!mediaPlayer.isPlaying())
			mediaPlayer.start();
	}
	
	/**
	 * Stops the current music file from playing
	 */
	public static void stop() {
		if(mediaPlayer == null) 
			return;
			
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
	}
	
	/**

	 * Pauses the current music file, resume with start()
	 */
	public static void pause() {
		if(mediaPlayer == null)
			return;
		mediaPlayer.pause();
	}
	
	/**
	 * Call when RokonActivity is paused
	 */
	public static void onPause() {
		if(mediaPlayer == null) return;
		if(mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			mustResume = true;
		}
	}
	
	/**
	 * Call when RokonActivity is resumed
	 */
	public static void onResume() {
		if(mediaPlayer == null) return;
		if(mustResume) {
			mediaPlayer.start();
			mustResume = false;
		}
	}
	
	/**
	 * Fetches the associated MediaPlayer object, so you can make your own changes or hook your own Listeners
	 * 
	 * @return MediaPlayer object, NULL if none used
	 */
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public static boolean getloop() {
		// TODO Auto-generated method stub
		return b_loop;
	}
	
}
