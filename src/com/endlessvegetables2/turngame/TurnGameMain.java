package com.endlessvegetables2.turngame;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.GameItem;
import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.Gameoverxiangzi;
import com.endlessvegetables2.ui.LevelData;
import com.endlessvegetables2.ui.LevelFailModule;
import com.endlessvegetables2.ui.LevelSuccessModule;
import com.endlessvegetables2.ui.VeggiesData;
import com.facebook.UserRequest;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.socogame.coolEdit.CoolEditData;
import com.socogame.coolEdit.CoolEditDefine;
import com.socogame.gameScript.GameScriptRun;
import com.socogame.sax.RSSFeed;
import com.socogame.sax.RSSHandler;
import com.socogame.turnGameScript.TurnGameScriptRun;

public class TurnGameMain extends Module{
	public static int turn=0;//0主角时间 123切换（主角->敌人）4敌人 567切换（敌人->主角） 8等待主角出屏
	
	public static int turntime=0;
	public static int turn13time_max=10;
	public static int turn2time_max=20;
	
	public static int turn_num=1;
	
	public static int turn_guaitime=100;
	public static int turn_guaitime_max=100;
	
	public static byte isShow_nengliang=0;
	public static int turn_nengliang=100;
	public static int turn_nengliang_max=100;
	
	public float turn_Gear=0;
	public boolean anjian_add=false;
	public static char NumChars[]={'0','1','2','3','4','5','6','7','8','9'};	
	public static char NumChars2[]={'0','1','2','3','4','5','6','7','8','9','/'};	
	
	public static int pangxie2=-1;
	public static int pangxie2X=-1;
	public static int pangxie2Y=-1;
	
	public static Sprite sprite_ui_Action_2;
	public Sprite sprite_ui_Action_3;
	public Sprite sprite_ui_combat_enemy;
	public Sprite sprite_ui_combat_player;
	public Sprite sprite_ui_Gear;
	public Sprite sprite_ui_progress_03;
	public Sprite sprite_ui_progress_04;
	public Sprite sprite_ui_round;
	public Sprite sprite_map_add;
	public static Sprite sprite_skill_silent;
	
	public Bitmap bitmap_reward_num[];
	public static Sprite sprite_reward_num[];
	public Bitmap bitmap_ui_round_num[];
	public Sprite sprite_ui_round_num[];
	
	public int stageIndex;//关卡索引
	
	public int gameMainLeftPlayerID;//左边蔬菜id
	
	public int latticeLife;//栅栏的生命值
	
	public int goldenNumber;//金币数
	
	public int gemNumber;//宝石数
	
	public int gameNumber;//游戏分数
	
	public int comboShowTime;//暴击持续时间			
	
	public int gameMainLeftPlayerSpecial;//左边蔬菜技能
	
	public int getGoldenNumber;//游戏中获得的金币数量
	
	public int getGemNumber;//游戏中获得的宝石数量
	
	public int getCardNumber;//游戏中获得的卡片数量
	
	public ArrayList<Integer> getCard;//游戏中获得的卡片数量
	
//	public int hitNum;//游戏中击中怪物数
//	
//	public int sendNum;//游戏中发射蔬菜数
	
	//---------------------------- 自动发射子弹间隔时间 ---------------------------------
	private int automaticBulletIntervalTime;
	
//	//------------------------------ 使用读xml的方式读取关卡中的所有怪物信息 ------------------------
//	private GameReadMap gameReadMap;
	
	//------------------------------ 关卡脚本 ----------------------------------------------
	public TurnGameScriptRun gameScriptRun;
	
	//------------------------------ 弹弓 -------------------------------
	public TurnGameSlingshot slingshot;
	
	//------------------------------ 准备中的蔬菜 --------------------------------	
	public TurnGameReadSpriteBullet readSpriteBullet;
	
	//------------------------------ 蔬菜子弹队列 ----------------------------	
	public TurnGameSpriteBullet spriteBullet;

	//------------------------------ 蔬菜子弹队列 ----------------------------	
	public TurnGameWaitingSpriteBulletLeft waitingSpriteBulletLeft;
	
	//------------------------------ 栅栏 -----------------------------------
	public static TurnGameSpriteLattice spriteLattice;
	
	//------------------------------ 背景 ------------------------------------
	public TurnGameBackground gameBackground;
	
	//------------------------------ 游戏UI ---------------------------------
	public TurnGameUI gameUI;
	
//	//------------------------------ Combo ---------------------------------
//	public Combo combo;
	
	//------------------------------ 敌人 ------------------------------------ 
	public TurnGameMonster gameMonster;	
	
	//------------------------------ 金币队列 -------------------------------
	public ArrayList<TurnGameGoldenAnimation> goldenList;
	
//	//------------------------------ 冰雹 -----------------------------------
//	public GameHailStone gameHailStone;
	
//	//------------------------------ 飞艇 ------------------------------------
//	public GameAirship gameAirship;
		
//	//------------------------------ 道具炸弹  ------------------------------------
//	public GameBomb gameBomb;
	
	//------------------------------ 道具寒冰弹炸弹  ------------------------------------
//	public GameColdBomb gameColdBomb;
	
//	//------------------------------ 显示中间效果 ------------------------------
//	public GameCenterEffect gameCenterEffect;
	
	//------------------------------ 游戏结束 ---------------------------------
//	private Sprite gameOver;
	
	//------------------------------ 右边等待的蔬菜 ---------------------------------
	public TurnGameWaitingSpriteBulletRight waitingSpriteBulletRight;
	
	//------------------------------ 组队的蔬菜id --------------------------------
	public int groupId;	
	
	//------------------------------ 金币翻倍时间 ---------------------------------
	public int doubleGameNumberTime;
	
	//----------------------------- 迷雾 ------------------------------------------	
	public TurnGameSmoke gameSmoke;
	
	//----------------------------- 时空门  ------------------------------------------
	public TurnGameTeleport gameTeleport;
	
	//----------------------------- 跳板 ------------------------------------------
	public TurnGameSpringboard gameSpringboard;
	
//	//----------------------------- 乱舞  ------------------------------------------
//	public GameFingerDance gameFingerDance;
	
//	//----------------------------- 好友援助锤子  ------------------------------------------
//	public GameFirendlyHammer gameFirendlyHammer;
	
	//----------------------------- 关卡任务  -------------------------------------
	public TurnGameMission gameMission;
	
	//----------------------------- 栅栏回血  -------------------------------------
	public TurnGameLatticeRestore gameLatticeRestore;
	
	//----------------------------- 气泡  -------------------------------------
	public TurnGameBubble gameBubble;
	
	//----------------------------- 外部数据 --------------------------------------
	public TurnGameReadData gameReadData;
	
	//----------------------------- 获得卡片的动画 ----------------------------
	public ArrayList<TurnGameCardAnimation> gameCardAnimation;
	
	//----------------------------- 教学  ----------------------------	
	public TurnGameTeaching gameTeaching;
	
//	//--------------------------- 进入隐藏关卡 ----------------------------
//	public boolean enterHideStage;
	
	//----------------------------- 开关触摸  ----------------------------	
//	public boolean isTouch;
	
//	//----------------------------- 送小蘑菇  ----------------------------	
//	public boolean getMogo;
	
//	//----------------------------- 爆发显示等级  ----------------------------	
//	public int comboShowLevel;
	
	//----------------------------- 关于卡片 -----------------------------
	private byte cardFinalNum;
	private int  nextCardWaitingTime;
	
	private boolean isover=false;
//	//----------------------------
//	private byte index = 0;
	
//	public SirenLight sirenLight;
	
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub								
		
		//----------------------------- 外部传来的数据 -------------------------------------
		
		gameReadData = new TurnGameReadData();
		
		gameReadData.setPowerCard();//设置奖励卡槽
		
		gameReadData.setLeftPlayerID(this);//左边蔬菜id
		
		playerLeftSpecial(gameMainLeftPlayerID);		
		
		stageIndex = 0;
		
		gameReadData.stageNumber(this);
		
		latticeLife = 150;
		
		gameReadData.setLatticeLife(this);
		
		goldenNumber = 0;
		
		gameReadData.setGoldNumber(this);
		
		gemNumber = 0;
		
		gameReadData.setGemNumber(this);
		
		gameNumber = 0;
		
		comboShowTime = 150;
		
		gameReadData.setComboShowTime(this);
		
		getGoldenNumber = 0;
		
		getGemNumber = 0;
		
		getCardNumber = 0;
		
		getCard = new ArrayList<Integer>();
		
		gameTeaching = new TurnGameTeaching();
		
		gameTeaching.loadImage();
		
		gameScriptRun = new TurnGameScriptRun();
				
		gameScriptRun.initScript(this, stageIndex);	
		
		gameScriptRun.loadSpriteImage();
		
		//关卡任务
		gameMission = new TurnGameMission(stageIndex);						
				
//		//载入精灵所有的图片				
//		for(int i=0;i<CoolEditData.imageName.length;i++)		
//			SpriteLibrary.loadSpriteImage(i);		
			
		slingshot = new TurnGameSlingshot();
		
		gameReadData.setSlingShotLevel(this);
		
		slingshot.init();
		
		slingshot.loadImage();
		
		readSpriteBullet = new TurnGameReadSpriteBullet();
		
		readSpriteBullet.initSpriteBullet(gameMainLeftPlayerID, slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, gameMainLeftPlayerSpecial);
		
		spriteBullet = new TurnGameSpriteBullet();		
		
		waitingSpriteBulletLeft = new TurnGameWaitingSpriteBulletLeft();
		
		waitingSpriteBulletLeft.init(gameMainLeftPlayerID, slingshot.SLINGSHOT_X, slingshot.SLINGSHOT_Y, gameMainLeftPlayerSpecial);
		
		waitingSpriteBulletRight = new TurnGameWaitingSpriteBulletRight();
		
		waitingSpriteBulletRight.init(slingshot.SLINGSHOT_X, slingshot.SLINGSHOT_Y);
		
		gameReadData.setRightPlayerID(this);
		
		spriteLattice = new TurnGameSpriteLattice();
		
		spriteLattice.initSpriteLattice(latticeLife, slingshot.SLINGSHOT_X, slingshot.SLINGSHOT_Y, gameReadData.getLatticeLevel());
		
		gameBackground = new TurnGameBackground();
		
		gameBackground.init();
		
		if(stageIndex<=29)
			gameBackground.loadImage("ui/bg0");
		else
			gameBackground.loadImage("ui/bg1");
			
		gameUI = new TurnGameUI();
		
		gameUI.loadImage();
		
		gameReadData.setGameUITool(this);
		
		gameUI.setGameTime(gameScriptRun.getGameTime());
		
		gameUI.setStageLength(gameScriptRun.getStageLength());
		
		gameUI.setGemNumber(gemNumber);
		
		gameUI.setGoldNumber(goldenNumber);
		
//		combo = new Combo();
//		
//		combo.init(comboShowTime);
//		
//		combo.loadImage();
		
		gameMonster = new TurnGameMonster();
		
		gameMonster.init();
		
		gameMonster.loadImage();
		
		goldenList = new ArrayList<TurnGameGoldenAnimation>();
		
//		gameHailStone = new GameHailStone();
		
//		int airshipLevel = 1;
//		
//		for(int i=0;i<gameReadData.slot.length;i++)
//		{
//			if(gameReadData.slot[i] == GameUI.ITEM_TYPE_1)
//				airshipLevel = gameReadData.slot_level[i];
//		}
		
//		gameAirship = new GameAirship(airshipLevel);
		
//		int bombLevel = 1;
//			
//		for(int i=0;i<gameReadData.slot.length;i++)
//		{
//			if(gameReadData.slot[i] == GameUI.ITEM_TYPE_3)
//				bombLevel = gameReadData.slot_level[i];
//		}
		
//		gameBomb = new GameBomb(bombLevel);
		
//		gameColdBomb = new GameColdBomb();
		
//		gameCenterEffect = new GameCenterEffect();
		
		gameSmoke = new TurnGameSmoke();
		
		gameTeleport = new TurnGameTeleport();
				
		gameSpringboard = new TurnGameSpringboard();
		
//		gameFingerDance = new GameFingerDance();
//		
//		gameFirendlyHammer = new GameFirendlyHammer();				
//		
		gameLatticeRestore = new TurnGameLatticeRestore(this);
		
		gameBubble = new TurnGameBubble();
		gameBubble.loadImage();
		
		gameCardAnimation = new ArrayList<TurnGameCardAnimation>();				
		
		GameStaticImage.loadGameMenuImage();
		
		groupId = 0;
		
//		getMogo = false;
//		
//		comboShowLevel = 1;
		
		cardFinalNum = 3;//每个关卡最多获得3张卡片
		
		nextCardWaitingTime = 0;
		
		isover=false;
//		loadSmallCardImage();
		
//		sirenLight = new SirenLight();
		sprite_ui_Action_2 = new Sprite(GameImage.getImage("turn/ui_Action_2"));
		sprite_ui_Action_3 = new Sprite(GameImage.getImage("turn/ui_Action_3"));
		sprite_ui_combat_enemy = new Sprite(GameImage.getImage("turn/ui_combat_enemy"));
		sprite_ui_combat_player= new Sprite(GameImage.getImage("turn/ui_combat_player"));
		sprite_ui_Gear= new Sprite(GameImage.getImage("turn/ui_Gear"));
		sprite_skill_silent= new Sprite(GameImage.getImage("turn/skill_silent"));
		sprite_ui_progress_03= new Sprite(GameImage.getImage("turn/ui_progress_03"));
		sprite_ui_progress_04= new Sprite(GameImage.getImage("turn/ui_progress_04"));
		sprite_map_add= new Sprite(GameImage.getImage("s_map/map_add"));
		sprite_ui_round= new Sprite(GameImage.getImage("turn/ui_round"));
		bitmap_reward_num=GameImage.getAutoSizecutBitmap("turn/reward_num", 11, 1, GameImage.Sort_line);
		sprite_reward_num=new Sprite[bitmap_reward_num.length];
		for(int i=0;i<sprite_reward_num.length;i++){
			sprite_reward_num[i]=new Sprite(bitmap_reward_num[i]);
		}
		
		bitmap_ui_round_num=GameImage.getAutoSizecutBitmap("turn/ui_round_num", 10, 1, GameImage.Sort_line);
		sprite_ui_round_num=new Sprite[bitmap_ui_round_num.length];
		for(int i=0;i<sprite_ui_round_num.length;i++){
			sprite_ui_round_num[i]=new Sprite(bitmap_ui_round_num[i]);
		}
		
		turn=0;
		turntime=0;
		turn_num=1;
		turn_guaitime=100;
		isShow_nengliang=0;
		turn_nengliang=100;
		turn_nengliang_max=100;
		turn_Gear=0;
		anjian_add=false;
		pangxie2=-1;
		pangxie2X=-1;
		pangxie2Y=-1;
		return false;
	}
	
//	public void enterGoldenMap()
//	{
//		transition.startTransition();
//		
//		isGoldenMap = true;				
//	}

	private void resetGame() 
	{
		initialize();
		
//		GameImage.initImageHashMap();
	}
	
	
	public void paintturnUI(Canvas canvas) {
		//显示回合数
		int tempx=(int)((GameConfig.GameScreen_Width - GameConfig.GameScreen_Width/4)+15*GameConfig.f_zoomx);
		int tempy=(int)(0*GameConfig.f_zoomy);
		sprite_ui_round.drawBitmap(canvas, sprite_ui_round.bitmap, tempx-sprite_ui_round.bitmap.getWidth()/2, tempy, null);
		GameLibrary.DrawNumber(canvas, sprite_ui_round_num,tempx-sprite_ui_round_num[0].bitmap.getWidth(), 30*GameConfig.f_zoomy, NumChars, ""+(turn_num>9?turn_num:"0"+turn_num), null, GameLibrary.TL, 0);
		
		//显示能量槽
		tempx = (int)(65*GameConfig.f_zoomx);
		tempy = (int)(GameConfig.GameScreen_Height-5*GameConfig.f_zoomy-sprite_ui_progress_03.bitmap.getHeight());
		sprite_ui_progress_03.drawBitmap(canvas, sprite_ui_progress_03.bitmap, tempx, tempy, null);
		
		sprite_ui_Gear.drawBitmap(canvas, tempx-35*GameConfig.f_zoom, tempy-20*GameConfig.f_zoom, 1f, 1f, 255, turn_Gear, sprite_ui_Gear.bitmap.getWidth()/2, sprite_ui_Gear.bitmap.getHeight()/2, 0, 0, 0);
		sprite_ui_Gear.drawBitmap(canvas, tempx-50*GameConfig.f_zoom, tempy+24*GameConfig.f_zoom, 1f, 1f, 255, turn_Gear, sprite_ui_Gear.bitmap.getWidth()/2, sprite_ui_Gear.bitmap.getHeight()/2, 0, 0, 0);
		tempx = tempx+sprite_ui_progress_03.bitmap.getWidth()/2-sprite_ui_progress_04.bitmap.getWidth()/2;
		tempy = tempy+sprite_ui_progress_03.bitmap.getHeight()/2-sprite_ui_progress_04.bitmap.getHeight()/2;
		int tempnengliangxiaohao=TurnGameSpriteLibrary.Getnengliang(readSpriteBullet.getSpriteBullet().kind);
		if(TurnGameMain.isShow_nengliang>0){
			tempnengliangxiaohao=0;
		}
		int tempnengliang=turn_nengliang-tempnengliangxiaohao;
		canvas.save();
		canvas.clipRect(tempx, tempy, tempx+sprite_ui_progress_04.bitmap.getWidth()*tempnengliang/turn_nengliang_max, tempy+sprite_ui_progress_04.bitmap.getHeight());
		sprite_ui_progress_04.drawBitmap(canvas, sprite_ui_progress_04.bitmap, tempx, tempy, null);
		canvas.restore();
		//显示消耗
		canvas.save();
		canvas.clipRect(tempx+sprite_ui_progress_04.bitmap.getWidth()*tempnengliang/turn_nengliang_max, tempy, tempx+sprite_ui_progress_04.bitmap.getWidth()*(tempnengliang+tempnengliangxiaohao)/turn_nengliang_max, tempy+sprite_ui_progress_04.bitmap.getHeight());
//		sprite_ui_progress_04.drawBitmap(canvas, sprite_ui_progress_04.bitmap, tempx, tempy, null);
		int tempicoke = GameConfig.i_coke%30;
		if(tempicoke>15){
			tempicoke=30-tempicoke;
		}
		sprite_ui_progress_04.drawBitmap(canvas, tempx, tempy, 1f, 1f, 50+tempicoke*10, 0, 0, 0, 0, 0, 0);
		canvas.restore();
		
		tempx = (int)(tempx+sprite_ui_progress_04.bitmap.getWidth()-10*GameConfig.f_zoomx);
		tempy = tempy+sprite_ui_progress_04.bitmap.getHeight()/2-sprite_map_add.bitmap.getHeight()/2;
		sprite_map_add.drawBitmap(canvas, tempx-(anjian_add?sprite_map_add.bitmap.getWidth()/10:0), tempy-(anjian_add?sprite_map_add.bitmap.getHeight()/10:0), anjian_add?1.2f:1f, anjian_add?1.2f:1f, 255, 0, 0, 0, 0, 0, 0);
		
		tempx = (int)(tempx-sprite_ui_progress_04.bitmap.getWidth()+10*GameConfig.f_zoomx-sprite_ui_Action_2.bitmap.getWidth());
		tempy = tempy+sprite_map_add.bitmap.getHeight()/2-sprite_ui_Action_2.bitmap.getHeight()/2;
		sprite_ui_Action_2.drawBitmap(canvas, sprite_ui_Action_2.bitmap, tempx, tempy, null);
		
		tempx = (int)(tempx+sprite_ui_progress_04.bitmap.getWidth()/2+sprite_ui_Action_2.bitmap.getWidth());
		tempy = tempy+sprite_ui_Action_2.bitmap.getHeight()-sprite_reward_num[0].bitmap.getHeight();
		int tempw=(turn_nengliang+"/"+turn_nengliang_max).length()*sprite_reward_num[0].bitmap.getWidth();
		GameLibrary.DrawNumber(canvas, sprite_reward_num,tempx-tempw/2, tempy, NumChars2, (turn_nengliang+"/"+turn_nengliang_max), null, GameLibrary.TL, 0);
		
		tempy = GameConfig.GameScreen_Height/2-(int)(50*GameConfig.f_zoom);
		switch (turn) {
		case 1:
			canvas.save();
			canvas.clipRect(0, tempy-sprite_ui_combat_enemy.bitmap.getHeight()/2, GameConfig.GameScreen_Width, tempy+sprite_ui_combat_enemy.bitmap.getHeight()/2);
			sprite_ui_combat_enemy.drawBitmap(canvas, sprite_ui_combat_enemy.bitmap, GameConfig.GameScreen_Width/2-sprite_ui_combat_enemy.bitmap.getWidth()/2, tempy+sprite_ui_combat_enemy.bitmap.getHeight()/2-sprite_ui_combat_enemy.bitmap.getHeight()*turntime/turn13time_max, null);
			canvas.restore();
			break;
		case 2:
			sprite_ui_combat_enemy.drawBitmap(canvas, sprite_ui_combat_enemy.bitmap, GameConfig.GameScreen_Width/2-sprite_ui_combat_enemy.bitmap.getWidth()/2, tempy-sprite_ui_combat_enemy.bitmap.getHeight()/2, null);
			break;
		case 3:
			sprite_ui_combat_enemy.drawBitmap(canvas, GameConfig.GameScreen_Width/2-sprite_ui_combat_enemy.bitmap.getWidth()/2, tempy-sprite_ui_combat_enemy.bitmap.getHeight()/2, 1f, 1f, 255-255*turntime/turn13time_max, 0, 0, 0, 0, 0, 0);
			break;
		case 5:
			canvas.save();
			canvas.clipRect(0, tempy-sprite_ui_combat_player.bitmap.getHeight()/2, GameConfig.GameScreen_Width, tempy+sprite_ui_combat_player.bitmap.getHeight()/2);
			sprite_ui_combat_player.drawBitmap(canvas, sprite_ui_combat_player.bitmap, GameConfig.GameScreen_Width/2-sprite_ui_combat_player.bitmap.getWidth()/2, tempy+sprite_ui_combat_player.bitmap.getHeight()/2-sprite_ui_combat_player.bitmap.getHeight()*turntime/turn13time_max, null);
			canvas.restore();
			break;
		case 6:
			sprite_ui_combat_player.drawBitmap(canvas, sprite_ui_combat_player.bitmap, GameConfig.GameScreen_Width/2-sprite_ui_combat_player.bitmap.getWidth()/2, tempy-sprite_ui_combat_player.bitmap.getHeight()/2, null);
			break;
		case 7:
			sprite_ui_combat_player.drawBitmap(canvas, GameConfig.GameScreen_Width/2-sprite_ui_combat_player.bitmap.getWidth()/2, tempy-sprite_ui_combat_player.bitmap.getHeight()/2, 1f, 1f, 255-255*turntime/turn13time_max, 0, 0, 0, 0, 0, 0);
			break;
		}
	}
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub	
		gameBackground.paint(canvas);
						
		slingshot.paintIndicator(canvas);
		
		gameSpringboard.paint(canvas);
		
		spriteBullet.paintShadow(canvas);		
		
		gameMonster.paint(canvas);
		
		spriteLattice.paint(canvas);
			
		spriteBullet.paint(canvas);
			
		slingshot.paint(canvas, this);	
		
		gameUI.paint(this, canvas, gameNumber);
		
//		gameBomb.paint(canvas);
//		
//		gameHailStone.paint(canvas);
//		
//		combo.paint(canvas, this);	
		
		gameTeleport.paint(canvas);
		
//		gameAirship.paint(canvas);				
		
		gameSmoke.paint(canvas);
		
//		gameFingerDance.paint(canvas);
//		
//		gameFirendlyHammer.paint(canvas);
		
		gameLatticeRestore.paint(canvas);
		
//		gameCenterEffect.drawCombo(canvas);
		
		drawGoldenAnimation(canvas);	
		
		drawGameCardAnimation(canvas);
		
		gameBubble.paint(canvas);
		
		paintturnUI(canvas);
		
		gameTeaching.paint(canvas);
		
//		sirenLight.draw(canvas);
		
////		drawGameOver(canvas);
//		
////		GameAwards.showAwards(canvas);
	}

	
	/*
	 * 发射单发蔬菜
	 * */
	private void addSpriteBullet()
	{				
		if(slingshot.getSendSpriteBullet())
		{
			if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_WD||
			   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_WD_2||
			   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_WD_3)
			{
				if(slingshot.sendWaitingTime%2==0)
				{
//					sendNum ++;
					
					spriteBullet.addSpriteBullet(slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, slingshot.slingShotDegree, 
							slingshot.slingShotSpeed, 3,
							readSpriteBullet.getSpriteBullet().kind, groupId, 
						    readSpriteBullet.getSpriteBullet().special,
						    slingshot.getIndicator().getSnipe_x(), slingshot.getIndicator().getSnipe_y());					
				}
								
				slingshot.sendWaitingTime --;
				
				if(slingshot.sendWaitingTime<=0)
				{
					readSpriteBullet.delReadSpriteBullet();
					
					slingshot.setSendSpriteBullet(false);
					
					groupId ++;
					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.peas, 0);
				}
			}
			else
			{
//				sendNum ++;
				
				spriteBullet.addSpriteBullet(slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, slingshot.slingShotPieceDegree, 
				slingshot.slingShotSpeed, 3,
				readSpriteBullet.getSpriteBullet().kind, 0, 
			    readSpriteBullet.getSpriteBullet().special,
			    slingshot.getIndicator().getSnipe_x(), slingshot.getIndicator().getSnipe_y());	
				
				readSpriteBullet.delReadSpriteBullet();
				
				slingshot.setSendSpriteBullet(false);
				
				//声音
				if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_FQ||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_FQ_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_FQ_3)
				{					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.tomatos, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_HC||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_HC_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_HC_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.broccolis, 0);
					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.parabolas, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LB||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LB_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LB_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.carrots, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LJ||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LJ_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LJ_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.peppers, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_MG||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_MG_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_MG_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.mushrooms, 0);
					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.parabolas, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_NG||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_NG_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_NG_3)		
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.pumpkins, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_TD||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_TD_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_TD_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.potatos, 0);
					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.parabolas, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_YC||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_YC_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_YC_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.onions, 0);
				}
				else if(readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_ZS||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_ZS_2||
				   readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_ZS_3)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.bambooshoots, 0);
				}
				
//				//教学
//				if(gameTeaching.pauseState())
//				{
//					if(gameTeaching.getTeachId()==GameTeaching.TEACH_VOL1)
//					{
//					   getMogo = true;
//						
//					   gameTeaching.finish();
//					}
//					else if(gameTeaching.getTeachId()==GameTeaching.TEACH_VOL2)
//					{
//					   gameTeaching.finish();
//					}
//				}												 
			}
			
//			gameMission.setSendPlayId(true);
		}				
	}
	
	/*
	 * 怪物和子弹的碰撞 
	 * */
	private void collision()
	{
		for(int i=0;i<spriteBullet.getSpriteBulletList().size();i++)
		{			
			//土豆
			if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_TD||
				spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_TD_2||
				spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_TD_3)
			{
				if(spriteBullet.getSpriteBulletList().get(i).getActionName()==7)
				{
					if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_TD_2||
						spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_TD_3)
					{	
						if(spriteBullet.getSpriteBulletList().get(i).getframes()>spriteBullet.getSpriteBulletList().get(i).getMaxFrames()-3)
						{
							spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
									spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
									spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);			
														
							gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
						}
						else if(spriteBullet.getSpriteBulletList().get(i).getframes()>spriteBullet.getSpriteBulletList().get(i).getMaxFrames()-2)
						{
							spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
									spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*5,
									spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*5,
									spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*5, 
									spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*5);			
														
							gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
						}
					}
					else
					{						
						if(spriteBullet.getSpriteBulletList().get(i).getframes()>spriteBullet.getSpriteBulletList().get(i).getMaxFrames()-2)
						{
							spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
									spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
									spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);			
														
							gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
						}
					}
				}
			}
			//蘑菇
			else if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG||
				spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG_2||
				spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG_3)
			{
				if(spriteBullet.getSpriteBulletList().get(i).getActionName()==7)
				{
					if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG)
					{						
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2, 
								spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2);											
					}
					else if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG_2)
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
								spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);																														
					}
					else if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG_3)
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*4,
								spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*4,
								spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*4, 
								spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*4);																								
					}
					
					gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
				}
			}
			//花菜
			else if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_HC||
				spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_HC_2||
				spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_HC_3)
			{
				if(spriteBullet.getSpriteBulletList().get(i).getActionName()==7)
				{
					if(spriteBullet.getSpriteBulletList().get(i).size==1)
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
								spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);																													
					}
					else 
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).y-TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).x+TurnGameSpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2, 
								spriteBullet.getSpriteBulletList().get(i).y+TurnGameSpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2);				
					}
					
					gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
				}
			}
			//一般蔬菜
			else if(spriteBullet.getSpriteBulletList().get(i).isMove)				
				gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
										
		}
		
		spriteBullet.changeSameGroupIDisCombo();
	}
						
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		if(GameConfig.b_gameReset)
		{
			resetGame();
			
			GameConfig.b_gameReset = false;
			
			return;
		}
		if(pangxie2>0){
			pangxie2--;
			if(pangxie2==0){
				gameMonster.addEnemy(CoolEditDefine.Enemy_PANGXIE2, pangxie2X, pangxie2Y, 1, 
						slingshot.SLINGSHOT_Y-spriteLattice.getSpriteLatticeHeight(), 2, 500, 1);
				pangxie2=-1;
				pangxie2X=-1;
				pangxie2Y=-1;
			}
		}
		switch (turn) {
		case 0:
			if(isShow_nengliang>0){
				isShow_nengliang--;
			}
			break;
		case 1:
			turntime++;
			if(turntime>=turn13time_max){
				turntime=0;
				turn=2;
			}
			break;
		case 2:
			turntime++;
			if(turntime>=turn2time_max){
				turntime=0;
				turn=3;
			}
			break;
		case 3:
			turntime++;
			if(turntime>=turn13time_max){
				turn=4;
				turn_guaitime=turn_guaitime_max;
				for (int i = 0; i < TurnGameMonster.Enemy.size(); i++) {
					TurnGameMonster.Enemy.get(i).attackTime=(int)Math.abs((Math.random()%20));
				}
				turntime=0;
			}
			break;
		case 4:
			turntime++;
			turn_guaitime--;
			if(turn_guaitime<=0){
				turn=5;
				turntime=0;
				for (int i = 0; i < TurnGameMonster.Enemy.size(); i++) {
					if(TurnGameMonster.Enemy.get(i).dizzinessTime>0){
						TurnGameMonster.Enemy.get(i).dizzinessTime--;
					}
				}
			}
			turn_nengliang+=2;
			turn_Gear+=25;
			if(turn_Gear>360){
				turn_Gear-=360;
			}
			if(turn_nengliang>turn_nengliang_max){
				turn_nengliang=turn_nengliang_max;
			}
			break;
		case 5:
			turntime++;
			if(turntime>=turn13time_max){
				turn=6;
				turntime=0;
			}
			break;
		case 6:
			turntime++;
			if(turntime>=turn2time_max){
				turn=7;
				turntime=0;
			}
			break;
		case 7:
			turntime++;
			if(turntime>=turn13time_max){
				turntime=0;
				turn=0;
				turn_nengliang=turn_nengliang_max;
				turn_num++;
			}
			break;
		case 8:		
			turntime++;
			if(turntime>3){
				if(spriteBullet.getSpriteBulletList().size()<=0){
					turn=1;
					turntime=0;
				}
			}
			break;
		}
		
		gameTeaching.updata();
		
//		if(gameTeaching.getState()==gameTeaching.STATE_3)
//		{
//			isTouch = true;
//		}
		
		if(spriteLattice.getGameOver())
			return;
				
		addSpriteBullet();
		
		slingshot.updata(this);
		
		readSpriteBullet.updata(slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, slingshot.slingShotPieceDegree);
		
		spriteBullet.updata(this);
			
		waitingSpriteBulletLeft.updata(this);
		
		waitingSpriteBulletRight.updata(gameMonster.shxSkillTime>0?true:false);
		
		spriteLattice.updata();
		
		gameBackground.updata();
		
		if(!gameTeaching.pauseState())
		gameUI.updata(this);
		
		gameSmoke.updata(this);
		
		gameTeleport.updata(this);
		
		gameSpringboard.updata(this);
				
		gameLatticeRestore.updata();
		
		gameBubble.updata();
		
		if(!gameTeaching.pauseState())
		gameMonster.updata(this);
		
		collision();
		
		gameScriptRun.runScript(this);
		
		goldenAnimationUpdata();						
		
		gameCardAnimationUpdata();
		
		gameMissionProcess();
		
		gameFinish();
		
		gameOver();
		
		//清理音效资源
		GameMedia.clearBuffer();
		
		nextCardWaitingTime --;
		
		if(nextCardWaitingTime<=0)
		nextCardWaitingTime = 0;
		
//		sirenLight.run();
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
//		GameAwards.setAwards(GameAwards.GAMEAWARD_0);
		
		if(!gameTeaching.getIsTouch())
			return;
		
		if(gameTeaching.getOnTouchState())
		{
			gameTeaching.onTouchEvent(event);	
			
			return;
		}
		
		if(!spriteLattice.getGameOver())
		{					
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL1)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL2)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL3)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL29)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL43)||
			   !gameTeaching.pauseState())	
			{
				if(slingshot.onTouchEvent(event, this))
					return;
			}
				
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL44)||					  
			   !gameTeaching.pauseState())	
			{
				gameSmoke.onTouchEvent(event, this);
			}
			
//			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL15)||
//			   !gameTeaching.pauseState())	
//			{
//				gameFingerDance.onTouchEvent(event, this);
//			}
			
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL12)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL14)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL15)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL19)||
			   !gameTeaching.pauseState())	
			{		
				gameUI.onTouchEvent(event, this);	
			}
			
//			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==TurnGameTeaching.TEACH_VOL13)||					  
//			   !gameTeaching.pauseState())	
//			{		
//				if(gameMonster.shxSkillTime<=0&&!combo.getComboState()&&!combo.getComboSpecialTimeState())
					waitingSpriteBulletRight.onTouchEvent(event, this);
					waitingSpriteBulletLeft.onTouchEvent(event, this);
//			}
		}
	}		
	
	public void addGoldenAnimation(TurnGameSprite sprite)
	{								
		addGoldenAnimation((int)sprite.x, (int)sprite.y, 
				TurnGameSpriteLibrary.GetGoldenPercent(sprite.kind, this), TurnGameSpriteLibrary.GetGoldenNumber(sprite.kind, this), TurnGameGoldenAnimation.GOLDEN_TYPE);
			
//		addGoldenAnimation((int)sprite.x, (int)sprite.y, 
//				SpriteLibrary.GetGemPercent(sprite.kind), SpriteLibrary.GetGemNumber(sprite.kind), GoldenAnimation.GEM_TYPE);
		
		addGameCardAnimation(this, sprite);
	}
	
	private void addGoldenAnimation(int x, int y, int percent, int num, int type)
	{				
		int rmd = GameLibrary.getIntRandom(0, 99);
		
		if(rmd<percent)
		{			
			TurnGameGoldenAnimation g = new TurnGameGoldenAnimation();
			
			g.setGoldenAnimation(x, y, 50, 50, num, type);
		
			goldenList.add(g);	
		}
	}
	
	private void goldenAnimationUpdata()
	{
		for(int i=0;i<goldenList.size();i++)
		{
			if(goldenList.get(i).getGoldenState())
			{
				goldenList.get(i).updata(this);
			}
			else
			{
				goldenList.remove(i);
			}
		}
	}
	
	private void drawGoldenAnimation(Canvas canvas)
	{
		for(int i=0;i<goldenList.size();i++)
		{
			if(goldenList.get(i).getGoldenState())
			{
				goldenList.get(i).drawGoldenAnimation(canvas);
			}			
		}
	}
	
//	private int gameOverOffset;
//	
//	private void drawGameOver(Canvas canvas)
//	{
//		if(spriteLattice.getGameOver())
//		{		
//			Paint paint = new Paint();
//			
//			paint.setColor(Color.BLACK);
//			
//			paint.setAlpha(125);
//			
//			canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
////			
////			paint.setColor(Color.RED);
////			
////			paint.setTextSize(50);
////			
////			paint.setTextAlign(Align.CENTER);
////			
////			canvas.drawText("Game Over", GameConfig.GameScreen_Width/2, GameConfig.GameScreen_Height/2, paint);
//									
////			ExternalMethods.drawImage(canvas, gameOver, 0, 0, GameConfig.f_zoomx, GameConfig.f_zoomy, 255, 0, 0, 0);
//			
//			paint.reset();
//			
//			gameOverOffset += 60;
//			
//			if(gameOverOffset>=GameConfig.GameScreen_Height/2)
//			gameOverOffset = GameConfig.GameScreen_Height/2;
//			
//			gameOver.drawBitmap(canvas, gameOver.bitmap, GameConfig.GameScreen_Width/2, gameOverOffset, null, GameLibrary.VH, paint);			
//		}
//		else
//		{
//			gameOverOffset = -gameOver.bitmap.getHeight()/2;
//		}
//	}
	
	/*
	 * 没有完成游戏
	 * */
	private void gameOver()
	{
		if(spriteLattice.getGameOver()/*||gameUI.getCurrentGameTime()==0*/)
		{
			setLevelData();
			
			GameManager.forbidModule(new LevelFailModule());
		}
	}
		
	/*
	 * 完成游戏
	 * */
	private void gameFinish()
	{				
		if(isover){
			return;
		}
				
		if(gameScriptRun.gameScriptFinish()&&
		   gameMonster.isAliveMonsterNumber()<=0&&
		   goldenList.size()==0&&
		   gameCardAnimation.size()==0)
		{
			gameFinishMissionProcess();
			
			setLevelData();
			
			if(LevelData.getData().get(7)>0){
				GameManager.forbidModule(new Gameoverxiangzi(false));
			}else{
				GameManager.forbidModule(new LevelSuccessModule(false));
			}
			isover=true;
		}
	}
	
	/*
	 * 游戏中的相关任务处理
	 * */
	private void gameMissionProcess()
	{		
		
	}	

	/*
	 * 游戏暂停中的相关任务处理
	 * */
	public void gamePauseMissionProcess()
	{
				
	}	
	
	
	/*
	 * 游戏后的相关任务处理
	 * */
	private void gameFinishMissionProcess()
	{
		
	}		
	
	/*
	 * 游戏中任务22处理
	 * */
	public void gameMission22Process()
	{			
		
	}	
	
	private void playerLeftSpecial(int kind)
	{		
		switch(kind)
		{
			case CoolEditDefine.Player_FQ:
				gameMainLeftPlayerSpecial = TurnGameSprite.PLAYER_SPECIAL_0;
				break;	
			
			case CoolEditDefine.Player_FQ_2:
				gameMainLeftPlayerSpecial = TurnGameSprite.PLAYER_SPECIAL_1;
				break;	
				
			case CoolEditDefine.Player_FQ_3:
				gameMainLeftPlayerSpecial = TurnGameSprite.PLAYER_SPECIAL_1;
				break;		
				
			case CoolEditDefine.Player_WD:
			case CoolEditDefine.Player_WD_2:
			case CoolEditDefine.Player_WD_3:
				gameMainLeftPlayerSpecial = TurnGameSprite.PLAYER_SPECIAL_0;
				
			case CoolEditDefine.Player_LJ:
			case CoolEditDefine.Player_LJ_2:
			case CoolEditDefine.Player_LJ_3:
				gameMainLeftPlayerSpecial = TurnGameSprite.PLAYER_SPECIAL_0;
				
				break;			
		}		
	}
	
	public int playerRightSpecial(int kind)
	{		
		switch(kind)
		{						
			case CoolEditDefine.Player_YC:
			case CoolEditDefine.Player_YC_2:
			case CoolEditDefine.Player_YC_3:
				return TurnGameSprite.PLAYER_SPECIAL_0;	
				
			case CoolEditDefine.Player_LB:
			case CoolEditDefine.Player_LB_2:
			case CoolEditDefine.Player_LB_3:
				return TurnGameSprite.PLAYER_SPECIAL_0;		
				
			case CoolEditDefine.Player_ZS:				
			case CoolEditDefine.Player_ZS_2:				
			case CoolEditDefine.Player_ZS_3:
				return TurnGameSprite.PLAYER_SPECIAL_1;	
				
			case CoolEditDefine.Player_TD:	
				return TurnGameSprite.PLAYER_SPECIAL_0;		
				
			case CoolEditDefine.Player_TD_2:
				return TurnGameSprite.PLAYER_SPECIAL_1;		
				
			case CoolEditDefine.Player_TD_3:
				return TurnGameSprite.PLAYER_SPECIAL_2;		
				
			case CoolEditDefine.Player_MG:				
			case CoolEditDefine.Player_MG_2:				
			case CoolEditDefine.Player_MG_3:
				return TurnGameSprite.PLAYER_SPECIAL_0;	
				
			case CoolEditDefine.Player_HC:				
			case CoolEditDefine.Player_HC_2:				
			case CoolEditDefine.Player_HC_3:
				return TurnGameSprite.PLAYER_SPECIAL_0;		
				
			case CoolEditDefine.Player_NG:				
			case CoolEditDefine.Player_NG_2:				
			case CoolEditDefine.Player_NG_3:
				return TurnGameSprite.PLAYER_SPECIAL_3;			
				
			default:
				return TurnGameSprite.PLAYER_SPECIAL_0;		
		}		
	}
	
	public int getPlayerRightCdtime(int kind)
	{
//		switch(kind)
//		{
//			case CoolEditDefine.Player_YC:
//				return 75;
//			
//			case CoolEditDefine.Player_YC_2:
//				return 100;
//				
//			case CoolEditDefine.Player_YC_3:
//				return 125;		
//				
//			case CoolEditDefine.Player_LB:
//				return 75;
//			
//			case CoolEditDefine.Player_LB_2:
//				return 100;
//				
//			case CoolEditDefine.Player_LB_3:
//				return 125;			
//				
//			case CoolEditDefine.Player_ZS:
//				return 150;	
//				
//			case CoolEditDefine.Player_ZS_2:
//				return 175;	
//				
//			case CoolEditDefine.Player_ZS_3:
//				return 200;	
//				
//			case CoolEditDefine.Player_TD:
//				return 150;	
//				
//			case CoolEditDefine.Player_TD_2:
//				return 175;	
//				
//			case CoolEditDefine.Player_TD_3:
//				return 200;		
//				
//			case CoolEditDefine.Player_MG:
//				return 150;	
//				
//			case CoolEditDefine.Player_MG_2:
//				return 175;	
//				
//			case CoolEditDefine.Player_MG_3:
//				return 200;		
//				
//			case CoolEditDefine.Player_HC:
//				return 150;	
//				
//			case CoolEditDefine.Player_HC_2:
//				return 175;	
//				
//			case CoolEditDefine.Player_HC_3:
//				return 200;		
//				
//			case CoolEditDefine.Player_NG:
//				return 150;	
//				
//			case CoolEditDefine.Player_NG_2:
//				return 175;	
//				
//			case CoolEditDefine.Player_NG_3:
//				return 200;			
//				
//			default:
//				return 0;		
//		}		
		return 1;		
	}
	
	public void addGameCardAnimation(TurnGameMain gameMain, TurnGameSprite sprite)
	{
		if(TurnGameSpriteLibrary.GetCardsPercent(sprite.kind)>-1)
		{
			if(cardFinalNum>0&&nextCardWaitingTime==0)
			{
				cardFinalNum --;
				
				nextCardWaitingTime = 100;
				
				TurnGameCardAnimation _gameCardAnimation = new TurnGameCardAnimation();
			
				_gameCardAnimation.setGameCardAnimation(gameMain, sprite, gameMain.gameMonster.getEffect().light);			
			
				gameCardAnimation.add(_gameCardAnimation);
			}
		}
	}
	
	private void gameCardAnimationUpdata()
	{
		for(int i=0;i<gameCardAnimation.size();i++)
		{
			gameCardAnimation.get(i).updata();
			
			if(!gameCardAnimation.get(i).getState())
			{				
//				gameCardAnimation.get(i).delImage();
				
				gameCardAnimation.remove(i);
			}
		}
	}
	
	private void drawGameCardAnimation(Canvas canvas)
	{
		for(int i=0;i<gameCardAnimation.size();i++)
		{
			gameCardAnimation.get(i).paint(canvas);
		}
	}
	
	private void gameCardAnimationDelImage()
	{
//		for(int i=0;i<gameCardAnimation.size();i++)
//		{			
//			gameCardAnimation.get(i).delImage();
//				
//			gameCardAnimation.remove(i);
//		}
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Release() {
		// TODO Auto-generated method stub
		GameImage.initImageHashMap();
		
		slingshot.delImage();
		
		gameBackground.delImage();
		
		gameBubble.delImage();
		
		gameLatticeRestore.delImage();
		
		gameSmoke.delImage();
		
		gameSpringboard.delImage();
		
		gameTeleport.delImage();
		
		gameUI.delImage();
		
		spriteLattice.delImage();
		
		waitingSpriteBulletLeft.delImage(this);
		
		waitingSpriteBulletRight.delImage();
		
		gameScriptRun.releaseSpriteImage();
		
		gameMonster.delImage();
		
		gameTeaching.delImage();
		
		GameStaticImage.delGameMenuImage();				
		
		GameImage.showImageHashMap();
		
		GameImage.delImage(sprite_ui_Action_2.bitmap);
		sprite_ui_Action_2=null;
		GameImage.delImage(sprite_ui_Action_3.bitmap);
		sprite_ui_Action_3=null;
		GameImage.delImage(sprite_ui_combat_enemy.bitmap);
		sprite_ui_combat_enemy=null;
		GameImage.delImage(sprite_ui_combat_player.bitmap);
		sprite_ui_combat_player=null;
		GameImage.delImage(sprite_ui_Gear.bitmap);
		sprite_ui_Gear=null;
		GameImage.delImage(sprite_skill_silent.bitmap);
		sprite_skill_silent=null;
		GameImage.delImage(sprite_ui_progress_03.bitmap);
		sprite_ui_progress_03=null;
		GameImage.delImage(sprite_ui_progress_04.bitmap);
		sprite_ui_progress_04=null;
		GameImage.delImage(sprite_ui_round.bitmap);
		sprite_ui_round=null;
		GameImage.delImage(sprite_map_add.bitmap);
		sprite_map_add=null;
		GameImage.delImageArray(bitmap_reward_num);
		bitmap_reward_num=null;
		sprite_reward_num=null;
		GameImage.delImageArray(bitmap_ui_round_num);
		bitmap_ui_round_num=null;
		sprite_ui_round_num=null;
		System.gc();
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}	
	
	/**
	 * 0:	score 	分数<br/>
	 * 1:	time	关卡剩余时间<br/>
	 * 2-4:	task	当前任务完成度(0:false,1:true)(0,1,0)<br/>
	 * 5	gold	关卡中获得的金币<br/>
	 * 6	gem		关卡中获得的宝石<br/>
	 * 7	card	关卡中获得的卡片数<br/>
	 * 8	sgold	剩余总金币数<br/>
	 * 9	sgem	剩余总宝石数<br/>
	 * 10	cardlen 删减卡片的长度（card_len）<br/>
	 * card_len*3 	 删减卡片条目(cardId, +, -)<br/>
	 * 10+card_len*3+1 任务信息数(miss_len)<br/>
	 * miss_len * 4	任务信息列表(任务类型,怪物/蔬菜,完成数,目标数)
	 */
	public void setLevelData()
	{
		ArrayList<Integer> lData = new ArrayList<Integer>();
		
		lData.add(gameNumber);
		lData.add(gameUI.getCurrentGameTime());
		lData.add(gameMission.getStageMissionList().get(0).result);
		lData.add(gameMission.getStageMissionList().get(1).result);
		lData.add(gameMission.getStageMissionList().get(2).result);
		lData.add(getGoldenNumber);
		lData.add(getGemNumber);
		lData.add(getCardNumber);
		lData.add(goldenNumber);
		lData.add(gemNumber);
		
		lData.add(spriteLattice.getSpriteLattice().life);
		lData.add(spriteLattice.getSpriteLattice().lifeMax);
		lData.add(1);
		lData.add(1);
		
		for(int i=0;i<gameMission.getStageMissionList().size();i++)
		{
			lData.add((int)(gameMission.getStageMissionList().get(i).missionId));
			
			int info[] = gameMission.getMissionInfo(gameMission.getStageMissionList().get(i));
			
			for(int j=0;j<info.length;j++)
			{
				lData.add(info[j]);
			}
		}
		
		int uiCardNumber = gameUI.getItemCardInfo().get(gameUI.getItemCardInfo().size()-1);				
		
		int getCardNumber = getCard.size();
		
		lData.add(uiCardNumber+getCardNumber);
		
		for(int i=0;i<uiCardNumber;i++)
		{
			lData.add(gameUI.getItemCardInfo().get(i*2));
			lData.add(0);
			lData.add(gameUI.getItemCardInfo().get(i*2+1));
		}
		
		for(int i=0;i<getCardNumber;i++)
		{
			lData.add(getCard.get(i));
			lData.add(1);
			lData.add(0);
		}
		
		for(int i=0;i<lData.size();i++)
		{
			System.out.println("======================>>>"+lData.get(i));
		}
		
		LevelData.setData(lData);		
	}		
}
