package com.audio;

/**
 * 音效处理
 * @author caishunqi
 *
 */
public class AudioStream {
        
        private float volume;
        private int id;
        private boolean paused;
        private boolean continuous;
        private float rate;
        
        /**
         *  创建音效文件
         * 
         * @param id the streamId
         * @param continuous TRUE if looping indefinately
         * @param volume 0.0 to 1.0
         */
        public AudioStream(int id, boolean continuous, float volume) {
                this.id = id;
                this. continuous = continuous;
                this.volume = volume;
                rate = 1;
        }
        
        /**
         * @return TRUE if the AudioStream is paused
         */
        public boolean isPaused() {
                return paused;
        }
        
        /**
         * @return The raw streamId from the SoundPool interface
         */
        public int getId() {
                return id;
        }
        
        /**
         * Pauses the AudioStream, it must be started again with resume()
         */
        public void pause() {
                paused = true;
                Audio.instance.getSoundPool().pause(id);
        }
        
        /**
         * Resumes a paused AudioStream
         */
        public void resume() {
                paused = false;
                Audio.instance.getSoundPool().resume(id);
        }
        
        /**
         * Stops an AudioStream from being played, it cannot be restarted
         */
        public void stop() {
                Audio.instance.getSoundPool().stop(id);
        }
        
        /**
         * Defines whether the AudioStream is looping indefinately
         * 
         * @param continuous TRUE if indefinate looping
         */
        public void setContinuous(boolean continuous) {
                if(continuous) 
                        Audio.instance.getSoundPool().setLoop(id, -1);
                else
                        Audio.instance.getSoundPool().setLoop(id, 0);
                this.continuous = continuous;
        }
        
        /**
         * @return TRUE if the AudioStream is looping indefinately
         */
        public boolean isContinuous() {
                return continuous;
        }
        
        /**
         * Sets the volume of this particular AudioStream
         * 
         * @param volume 0.0 to 1.0
         */
        public void setVolume(float volume) {
        		this.volume = volume;
                Audio.instance.getSoundPool().setVolume(id, volume, volume);
        }
        
        /**
         * @return 0.0 to 1.0 the volume of this AudioStream
         */
        public float getVolume() {
                return volume;
        }
        
        /**
         * Sets the rate at which the AudioStream is played, slow normal or fast.
         * 
         * @param rate 0.5 to 2.0
         */
        public void setRate(float rate) {
        		this.rate = rate;
                Audio.instance.getSoundPool().setRate(id, rate);
        }
        
        /**
         * Returns the rate at which the AudioStream is playing
         * 
         * @return 0.5 to 2.0
         */
        public float getRate() {
                return rate;
        }

}