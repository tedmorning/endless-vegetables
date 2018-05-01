package com.audio;


import com.socoGameEngine.MainActivity;

import android.media.AudioManager;
import android.media.SoundPool;
/**
 * ����Ч������
 * @author caishunqi
 *
 */
public class Audio   {

        public static final int MAX_SOUNDS = 80;
        public static final int MAX_STREAMS = 8;
        
        public static Audio instance;
        private int i, j;
        
        private float masterVolume = 1;
        private SoundPool soundPool;
        private SoundFile[] soundArr = new SoundFile[MAX_SOUNDS];
        
        public static boolean mute = false;
        
        public Audio() {
        		instance = this;
                soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
                
                masterVolume = 1;
        }
        
        public void mute() {
                mute = true;
        }
        
        public void unmute() {
                mute = false;
        }
        
        public boolean isMuted() {
                return mute;
        }
        
        /**
         * Frees up some memory, this should be called when you are finished.
         */
        public void destroy() {
                try {
                        for(i = 0; i < MAX_STREAMS; i++)
                                soundArr[i].unload();
                        soundPool.release();
                } catch (Exception e) { }
                soundPool = null;
        }
        
        /**
         * @return a HashSet of all current SoundFile's 
         */
        public SoundFile[] getSounds() {
                return soundArr;
        }
        
        /**
         * @return the SoundPool object currently being used
         */
        public SoundPool getSoundPool() {
                return soundPool;
        }
        
        /**
         * Loads a file from the /assets/ folder in your APK, ready to be played
         * @param filename
         * @return 
         */
        Object object = new Object();
        
        public SoundFile createSoundFile( int resID )
        {
        	int id = soundPool.load(MainActivity.getActivity(),resID,1);
        	
        	return createSound(id);
        }
        
        public SoundFile createSoundFile(String filename)
        {
        	int id	= -1;
        	 try {
         		id = soundPool.load(MainActivity.getActivity().getAssets().openFd(filename), 0);
        	 }
        	 catch(Exception e){}
        	 
        	 return  createSound(id);
        }
      
        public SoundFile createSound(int id) {
                try {
                        SoundFile soundFile = new SoundFile(id);

                        j = -1;
                        for(i = 0; i < MAX_SOUNDS; i++)
                                if(soundArr[i] == null){
                                        j = i;
                               
                                     break;
                                }
                        if(j == -1) {
                                return null;
                        }
                        soundArr[j] = soundFile;
                        return soundFile;
                } catch (Exception e) {
                        return null;
                }
        }
        
        /**
         * @param soundFile SoundFile to be removed from the memory
         */
        public void removeSoundFile(SoundFile soundFile) {
                soundPool.unload(soundFile.getId());
                for(i = 0; i < MAX_SOUNDS; i++)
                        if(soundArr[i] != null)
                                if(soundArr[i].equals(soundFile))
                                        soundArr[i] = null;
        }
        
        /**
         * Removes all SoundFile's from the memory
         */
        public void removeAllSoundFiles() {
                for(i = 0; i < MAX_SOUNDS; i++)
                        soundArr[i] = null;
                soundPool.release();
        }
        
        /**
         * @param masterVolume the volume at which all future AudioStream's will play
         */
        public void setMasterVolume(float masterVolume) {
                this.masterVolume = masterVolume;
        }
        
        /**
         * @return the current volume at which AudioStream's will play at
         */
        public float getMasterVolume() {
                return masterVolume;
        }
        

}