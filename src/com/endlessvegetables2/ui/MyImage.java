package com.endlessvegetables2.ui;

import java.util.List;

public class MyImage {
	private List<MImage> frames;
	private Meta meta;
	public List<MImage> getFrames() {
		return frames;
	}
	public void setFrames(List<MImage> frames) {
		this.frames = frames;
	}
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public class Meta {
		public String app;
		public String version;
		public String image;
		public String format;
		public Size size;
		public int scale;
		public String smartupdate;
		public class Size{
			public int w,h;
		}
	}
	
	public class MImage{
		private String filename;
		private Frame frame;
		private boolean rotated;
		private boolean trimmed;
		private SpriteSourceSize spriteSourceSize;
		private SourceSize sourceSize;
		
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public Frame getFrame() {
			return frame;
		}
		public void setFrame(Frame frame) {
			this.frame = frame;
		}
		public boolean isRotated() {
			return rotated;
		}
		public void setRotated(boolean rotated) {
			this.rotated = rotated;
		}
		public boolean isTrimmed() {
			return trimmed;
		}
		public void setTrimmed(boolean trimmed) {
			this.trimmed = trimmed;
		}
		public SpriteSourceSize getSpriteSourceSize() {
			return spriteSourceSize;
		}
		public void setSpriteSourceSize(SpriteSourceSize spriteSourceSize) {
			this.spriteSourceSize = spriteSourceSize;
		}
		public SourceSize getSourceSize() {
			return sourceSize;
		}
		public void setSourceSize(SourceSize sourceSize) {
			this.sourceSize = sourceSize;
		}
		public String toString() {
			return filename + ":" + frame.toString() + ":" + rotated + ":" + trimmed + ":" + spriteSourceSize.toString() + ":" + sourceSize.toString();
		}
	}
	public class Frame{
		public int x,y,w,h;
		public String toString() {
			return x+":"+y+":"+w+":"+h;
		}
	}
	public class SpriteSourceSize {
		public int x,y,w,h;		
	}
	public class SourceSize {
		public int w,h;
	}
}
