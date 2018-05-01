package com.socoGameEngine;

import java.util.ArrayList;
import java.util.HashMap;

import com.audio.Audio;
import com.audio.Music;
import com.audio.SoundFile;
import com.kokatlaruruxi.wy.Main;




public class GameMedia 
{
	private static Audio audio = new Audio();
	
	//store sound file and its id
	private static HashMap<Integer, SoundFile> soundMap;
	
	//bg music file name
	private static String musicFile = null;
	private static int musicFileID = -1;
	//bg music is mute or not
	public static boolean isMusicMute = false;//音乐静音
//	public static boolean mute = false;//音效静音
	//bg music volume
	public static float musicVolume = 1 ;
	
	public GameMedia()
	{
	
	}		
	
	/**
	 * load all sound effect files
	 * 
	 * @param soundID an array of sound integer ID
	 * @param soundName an array of sound name
	 */
	public static void loadSounds(int[] soundID, String[] soundName)
	{
		int length = soundID.length;
		soundMap = new HashMap<Integer, SoundFile>(length);
		
		for(int i = 0; i < length; i++)
		{						
			soundMap.put(soundID[i], audio.createSoundFile("audio/"+soundName[i]));						
		}
	}
	/**
	 * load all sound effect files
	 * 
	 * @param soundID an array of sound integer ID
	 *
	 */
	public static void loadSounds(int[] soundID)
	{
		int length = soundID.length;
		soundMap = new HashMap<Integer, SoundFile>(length);
		
		for(int i = 0; i < length; i++)
		{						
			soundMap.put(soundID[i], audio.createSoundFile(soundID[i]));						
		}
	}
	
	public static void clearBuffer()
	{
		playList	= null;
		playList	= new ArrayList<Integer>();
	}
	
	/**
	 * play single sound
	 * 
	 * @param id sound ID
	 * @param loopnum loop number -1 forever 0 no loop  1 loop twice
	 */
	private static ArrayList<Integer> playList	= new ArrayList<Integer>();
	
	public static void playSound(int id, int loopnum)
	{
//		System.out.println("clpqy:"+playList.contains(id));
		if(playList.contains(id) )
			return;
		
		playList.add(id);
		
//		System.out.println("clpqy2:"+Audio.mute+","+GameManager.b_GameStart);
		if(Audio.mute||!GameManager.b_GameStart){
			return;
		}
		SoundFile sound = soundMap.get(id);
		if(loopnum != 0)
		{
			sound.playContinuous(loopnum);
		}
		else
		{
			try {
				sound.play();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogShow.d(e.toString());
				LogShow.d("id="+id+" is not found");
			}
		}
	}
	
	/**
	 * 防止玩家点击两次只响一次音乐
	 * 
	 * */
	public static void xl_playSound(int id, int loopnum)
	{
		if(Audio.mute||!GameManager.b_GameStart){
			return;
		}
		SoundFile sound = soundMap.get(id);
		if(loopnum != 0)
		{
			sound.playContinuous(loopnum);
		}
		else
		{
			try {
				sound.play();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogShow.d(e.toString());
				LogShow.d("id="+id+" is not found");
			}
		}
	}
	/**
	 * play background music
	 * 
	 * @param id music id
	 * @param isLoop loop or not
	 * @param b_play play immediately or not
	 */
	public static void playMusic(int id, boolean isLoop,boolean b_play)
	{		
		if(musicFileID == id&&Music.getMediaPlayer()!=null&&Music.getMediaPlayer().isPlaying()){
			return;
		}

		musicFileID = id;
		
		if(!isMusicMute&&GameManager.b_GameStart)
		{
			Main.requestAudioFocus();
			Music.stop();
			Music.play(id, isLoop,b_play);
			Music.getMediaPlayer().setVolume(musicVolume, musicVolume);
		}			
	}
	
	public static void playMusic2(int id, boolean isLoop,boolean b_play)
	{
		
			if(musicFileID == id&&Music.getMediaPlayer()!=null&&Music.getMediaPlayer().isPlaying()){
				return;
			}

			musicFileID = id;
			if(!isMusicMute)
			{
				Main.requestAudioFocus();
				Music.stop();
				Music.play(id, isLoop,b_play);
				Music.getMediaPlayer().setVolume(musicVolume, musicVolume);
			}
			
		}
	/**
	 * play background music
	 * 
	 * @param file music name
	 * @param isLoop loop or not
	 * @param b_play play immediately or not
	 */
	public static void playMusic(String file, boolean isLoop,boolean b_play)
	{
		
		if(musicFile == file&&Music.getMediaPlayer()!=null&&Music.getMediaPlayer().isPlaying()){
			return;
		}
		musicFile = file;
		if(!isMusicMute)
		{			
			Main.requestAudioFocus();
			Music.stop();
			Music.play(file, isLoop,b_play);
			Music.getMediaPlayer().setVolume(musicVolume, musicVolume);
		}
	
	
	}
	
	/** pause background music */
	public static void pauseMusic()
	{		
		Main.abandonAudioFocus();
		Music.pause();
	}
	
//	/*
//	 * justinpy 用于游戏载入时
//	 * */
//	public static void pauseMusicGameLoading()
//	{		
//		musicFileID = -1;
//		
//		Main.abandonAudioFocus();
//		Music.pause();
//	}
	
	/** resume background music */
	public static void resumeMusic()
	{
		if(!isMusicMute){						
			if(Music.getloop()){
				Main.requestAudioFocus();
				Music.play();
			}
		}
	}
	
	/**
	 * set sound effect mute or not
	 * 
	 * @param mute true is mute
	 */
	public static void muteSound(boolean mute)
	{
		Audio.mute = mute;
	}
	
	/**
	 * set music mute or not
	 * 
	 * @param mute true is mute
	 */
	public static void muteMusic(boolean mute)
	{
		isMusicMute = mute;
		if(mute)
		{
		
				if(Music.getMediaPlayer() != null
						&&Music.getMediaPlayer().isPlaying()){
					Main.abandonAudioFocus();
					Music.pause();
				}
					
		}
		else
		{
			if(Music.getMediaPlayer()==null){
				if(musicFileID != -1 ){
					Main.requestAudioFocus();
					Music.play(musicFileID, true);
				}
				else if(musicFile != null){
					Main.requestAudioFocus();
					Music.play(musicFile, true);
					Music.getMediaPlayer().setVolume(musicVolume, musicVolume);
				}
			}
			else{
				LogShow.d("playmusic");
				Main.requestAudioFocus();
				Music.play();
			}
		}
	}
	
	/**
	 * set sound volume
	 * 
	 * @param volume range[0,1]
	 */
	public static void setSoundVolume(float volume)
	{
		audio.setMasterVolume(volume);
	
	}
	
	/**
	 * set music volume
	 * 
	 * @param volume range[0,1]
	 */
	public static void setMusicVolume(float volume)
	{
		
		if(Music.getMediaPlayer() != null)
		Music.getMediaPlayer().setVolume(volume, volume);
	
	}
	
	/** release instances, garbage collection */
	public static void dealloc()
	{
		try 
		{
			audio.destroy();
			Music.stop();
			Music.getMediaPlayer().release();
			soundMap.clear();
			audio = null;
			soundMap = null;	
			musicFile = null;
		} 
		catch (NullPointerException e) 
		{
		}
	}
	/**
	 * 得到音效状态
	 * @return
	 */
	public static boolean getSoundmute() {
		// TODO Auto-generated method stub
		return 	Audio.mute ;
	}
	/**
	 * 得到音乐状态
	 * @return
	 */
	public static boolean getMusicmute() {
		// TODO Auto-generated method stub
		return 	isMusicMute ;
	}
	
	public static int getMusicFileID()
	{
		return musicFileID;
	}			
}
