package com.audio;

import android.R.integer;


public class SoundFile {
        
        private int id;
        private int result;
        private AudioStream audioStream;
        private boolean res;
        
        public SoundFile(int streamId) {
                id = streamId;
        }
        
        public int getId() {
                return id;
        }
        
        /**
         * @return the AudioStream through which the sound is playing
         */
        public AudioStream play() {
        	if(Audio.mute)
                        return null;
//        	   result = Audio.instance.getSoundPool().play(id,1,1, 1, 0, 1f);
                result = Audio.instance.getSoundPool().play(id, Audio.instance.getMasterVolume(), Audio.instance.getMasterVolume(), 1, 0, 1f);
                if(result != 0) {
                        audioStream = new AudioStream(result, false, Audio.instance.getMasterVolume());
//                	audioStream = new AudioStream(result, true, 1);
                        return audioStream;
                } else
                        return null;
        }
        
        /**
         * @return the AudioStream through which the sound is playing
         */
        public AudioStream playContinuous(int loopnum) {
                if(Audio.mute)
                        return null;
//                int result = Audio.instance.getSoundPool().play(id,1, 1, 0, -1, 1);
                
                int result = Audio.instance.getSoundPool().play(id, Audio.instance.getMasterVolume(), Audio.instance.getMasterVolume(), 0, loopnum, 1);
                if(result != 0) {
                        audioStream = new AudioStream(result, true, Audio.instance.getMasterVolume());
//                	audioStream = new AudioStream(result, true, 1);
                        return audioStream;
                } else
                        return null;
        }
        
        /**
         * Removes this file from the memory. Should be used when this file is no longer needed.
         */
        public void unload() {
                res = Audio.instance.getSoundPool().unload(id);
            
        }
}