package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.google.gson.Gson;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;

public class MySprite {
	private MyImage mi;
	private Sprite _sprite;
	
	public MySprite(String filePath, String imagePath) {
		Gson gson = new Gson();
		mi = gson.fromJson(ExternalMethods.getFromAssets(filePath), MyImage.class);
		_sprite = new Sprite(GameImage.getImage(imagePath));
	}
	
	public MyImage getMi() {
		return mi;
	}
	public Sprite get_sprite() {
		return _sprite;
	}
	
	public void drawBitmap(Canvas canvas, String imageName, float x, float y, Paint paint){
		for(int i=0; i<mi.getFrames().size(); i++) {
			if (imageName.equals(mi.getFrames().get(i).getFilename())) {				
				canvas.save();
				canvas.clipRect(x , y, x + mi.getFrames().get(i).getFrame().w * GameConfig.f_zoomx, y + mi.getFrames().get(i).getFrame().h * GameConfig.f_zoomy);
				_sprite.drawBitmap(canvas, _sprite.bitmap, x - mi.getFrames().get(i).getFrame().x * GameConfig.f_zoomx, y - mi.getFrames().get(i).getFrame().y * GameConfig.f_zoomy, paint);
				canvas.restore();
				break;
			}
		}
	}
	
	public void drawBitmap(Canvas canvas, String imageName, float x, float y, float sizeW, float sizeH, int Aphla, float Rotate, int RotateX, int RotateY ) {
		for(int i=0; i<mi.getFrames().size(); i++) {
			if (imageName.equals(mi.getFrames().get(i).getFilename())) {				
				canvas.save();
				canvas.clipRect(x , y, x + mi.getFrames().get(i).getFrame().w * GameConfig.f_zoomx * sizeW, y + mi.getFrames().get(i).getFrame().h * GameConfig.f_zoomy * sizeH);
				_sprite.drawBitmap(canvas, x - mi.getFrames().get(i).getFrame().x * GameConfig.f_zoomx * sizeW, y - mi.getFrames().get(i).getFrame().y * GameConfig.f_zoomy * sizeH, sizeW, sizeH, Aphla, Rotate, RotateX, RotateY);
				canvas.restore();
				break;
			}
		}
	}
	
	public void drawBitmap(Canvas canvas, String imageName, float x, float y, float sizeW, float sizeH, int Aphla, float Rotate, int	RotateX, int RotateY, int r, int g, int b) {
		for(int i=0; i<mi.getFrames().size(); i++) {
			if (imageName.equals(mi.getFrames().get(i).getFilename())) {				
				canvas.save();
				canvas.clipRect(x , y, x + mi.getFrames().get(i).getFrame().w * GameConfig.f_zoomx * sizeW, y + mi.getFrames().get(i).getFrame().h * GameConfig.f_zoomy * sizeH);
				_sprite.drawBitmap(canvas, x - mi.getFrames().get(i).getFrame().x * GameConfig.f_zoomx * sizeW, y - mi.getFrames().get(i).getFrame().y * GameConfig.f_zoomy * sizeH, sizeW, sizeH, Aphla, Rotate, RotateX, RotateY, r, g, b);
				canvas.restore();
				break;
			}
		}
	}
	
	public void release() {
		GameImage.delImage(_sprite.bitmap);
		_sprite = null;
		mi = null;
	}
	
	public float getImageWidth(String imageName) {
		for (int i=0; i<mi.getFrames().size(); i++) {
			if (imageName.equals(mi.getFrames().get(i).getFilename())) {
				return mi.getFrames().get(i).getFrame().w * GameConfig.f_zoomx;
			}
		}
		throw new NullPointerException(">>>>>>>>>MySprite no search image");//异常信息
	}
	public float getImageHeight(String imageName){
		for (int i=0; i<mi.getFrames().size(); i++) {
			if (imageName.equals(mi.getFrames().get(i).getFilename())) {
				return mi.getFrames().get(i).getFrame().h * GameConfig.f_zoomy;
			}
		}
		throw new NullPointerException(">>>>>>>>>MySprite no search image");//异常信息
	}
}
