package com.kokatlaruruxi.wy;

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
import android.util.Log;
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

public class GameMain extends Module{

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
	public GameScriptRun gameScriptRun;
	
	//------------------------------ 弹弓 -------------------------------
	public Slingshot slingshot;
	
	//------------------------------ 准备中的蔬菜 --------------------------------	
	public ReadSpriteBullet readSpriteBullet;
	
	//------------------------------ 蔬菜子弹队列 ----------------------------	
	public SpriteBullet spriteBullet;

	//------------------------------ 蔬菜子弹队列 ----------------------------	
	public WaitingSpriteBulletLeft waitingSpriteBulletLeft;
	
	//------------------------------ 栅栏 -----------------------------------
	public static SpriteLattice spriteLattice;
	
	//------------------------------ 背景 ------------------------------------
	public GameBackground gameBackground;
	
	//------------------------------ 游戏UI ---------------------------------
	public GameUI gameUI;
	
	//------------------------------ Combo ---------------------------------
	public Combo combo;
	
	//------------------------------ 敌人 ------------------------------------ 
	public GameMonster gameMonster;	
	
	//------------------------------ 金币队列 -------------------------------
	public ArrayList<GoldenAnimation> goldenList;
	
	//------------------------------ 冰雹 -----------------------------------
	public GameHailStone gameHailStone;
	
	//------------------------------ 飞艇 ------------------------------------
	public GameAirship gameAirship;
		
	//------------------------------ 道具炸弹  ------------------------------------
	public GameBomb gameBomb;
	
	//------------------------------ 道具寒冰弹炸弹  ------------------------------------
//	public GameColdBomb gameColdBomb;
	
	//------------------------------ 显示中间效果 ------------------------------
	public GameCenterEffect gameCenterEffect;
	
	//------------------------------ 游戏结束 ---------------------------------
//	private Sprite gameOver;
	
	//------------------------------ 右边等待的蔬菜 ---------------------------------
	public WaitingSpriteBulletRight waitingSpriteBulletRight;
	
	//------------------------------ 组队的蔬菜id --------------------------------
	public int groupId;	
	
	//------------------------------ 金币翻倍时间 ---------------------------------
	public int doubleGameNumberTime;
	
	//----------------------------- 迷雾 ------------------------------------------	
	public GameSmoke gameSmoke;
	
	//----------------------------- 时空门  ------------------------------------------
	public GameTeleport gameTeleport;
	
	//----------------------------- 跳板 ------------------------------------------
	public GameSpringboard gameSpringboard;
	
	//----------------------------- 乱舞  ------------------------------------------
	public GameFingerDance gameFingerDance;
	
	//----------------------------- 好友援助锤子  ------------------------------------------
	public GameFirendlyHammer gameFirendlyHammer;
	
	//----------------------------- 关卡任务  -------------------------------------
	public GameMission gameMission;
	
	//----------------------------- 栅栏回血  -------------------------------------
	public GameLatticeRestore gameLatticeRestore;
	
	//----------------------------- 气泡  -------------------------------------
	public GameBubble gameBubble;
	
	//----------------------------- 外部数据 --------------------------------------
	public GameReadData gameReadData;
	
	//----------------------------- 获得卡片的动画 ----------------------------
	public ArrayList<GameCardAnimation> gameCardAnimation;
	
	//----------------------------- 教学  ----------------------------	
	public GameTeaching gameTeaching;
	
	//--------------------------- 进入隐藏关卡 ----------------------------
	public boolean enterHideStage;
	
	//----------------------------- 开关触摸  ----------------------------	
//	public boolean isTouch;
	
	//----------------------------- 送小蘑菇  ----------------------------	
	public boolean getMogo;
	
	//----------------------------- 爆发显示等级  ----------------------------	
	public int comboShowLevel;
	
	//----------------------------- 香蕉道具 --------------------------------------
	public GameBanana gameBanana;
	
	//----------------------------- 彩虹道具 ---------------------------------
	public GameRainbow gameRainbow;
	
	//----------------------------- 蜘蛛网 ---------------------------------
	public GameCobweb gameCobweb;
	
	//----------------------------- 蔷薇道具 ------------------------------
	public GameRose gameRose;
	
	//----------------------------- 魔法门道具 ------------------------------
	public GameMagicDoor gameMagicDoor;
	
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
		if(!GameConfig.b_gameReset){
	    	GameImage.RemoveAllImage();
			GameImage.showImageHashMap();
			GameImage.showMemory();
			System.out.println("进入游戏前的内存");
		}
		//----------------------------- 外部传来的数据 -------------------------------------
		
		gameReadData = new GameReadData();
		
		gameReadData.setPowerCard();//设置奖励卡槽
		
//		index ++;
//		
//		if(index>3)
//			index = 1;
//		
//		if(index==1)
//			gameMainLeftPlayerID = CoolEditDefine.Player_FQ;//左边蔬菜id
//		else if(index==2)
//			gameMainLeftPlayerID = CoolEditDefine.Player_FQ_3;//左边蔬菜id
//		else if(index==3)
//			gameMainLeftPlayerID = CoolEditDefine.Player_WD_3;//左边蔬菜id
		
		gameReadData.setLeftPlayerID(this);//左边蔬菜id
		
		if(getHideStageState())
			gameMainLeftPlayerID = CoolEditDefine.Player_FQ;
		
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
		
//		hitNum = 0;
//		
//		sendNum = 0;
		
		//------------------------------ 读关卡数据 --------------------------------------------------
		
//		gameReadMap = new GameReadMap();
//		
//		gameReadMap.readMapInfo("orderingType.xml");
//		
//		gameReadMap.readMapGroupInfo("groupsRefresh.xml");
		
		setHideStageState();
		
		gameTeaching = new GameTeaching();
		
		gameTeaching.loadImage();
		
		gameScriptRun = new GameScriptRun();
		
		if(getHideStageState())//进入隐藏关卡
			gameScriptRun.initScript(this, 60/*stageIndex*/);	
		else
			gameScriptRun.initScript(this, stageIndex);	
		
		gameScriptRun.loadSpriteImage();
		
		//关卡任务
		gameMission = new GameMission(stageIndex);						
				
//		//载入精灵所有的图片				
//		for(int i=0;i<CoolEditData.imageName.length;i++)		
//			SpriteLibrary.loadSpriteImage(i);		
			
		slingshot = new Slingshot();
		
		gameReadData.setSlingShotLevel(this);
		
		slingshot.init();
		
		slingshot.loadImage();
		
		readSpriteBullet = new ReadSpriteBullet();
		
		readSpriteBullet.initSpriteBullet(gameMainLeftPlayerID, slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, gameMainLeftPlayerSpecial);
		
		spriteBullet = new SpriteBullet();		
		
		waitingSpriteBulletLeft = new WaitingSpriteBulletLeft();
		
		waitingSpriteBulletLeft.init(gameMainLeftPlayerID, slingshot.SLINGSHOT_X, slingshot.SLINGSHOT_Y, gameMainLeftPlayerSpecial);
		
		waitingSpriteBulletRight = new WaitingSpriteBulletRight();
		
		waitingSpriteBulletRight.init(slingshot.SLINGSHOT_X, slingshot.SLINGSHOT_Y);
		
		gameReadData.setRightPlayerID(this);
		
		spriteLattice = new SpriteLattice();
		
		spriteLattice.initSpriteLattice(latticeLife, slingshot.SLINGSHOT_X, slingshot.SLINGSHOT_Y, gameReadData.getLatticeLevel());
		
		gameBackground = new GameBackground();
		
		gameBackground.init();
		
		if(stageIndex<=29)
			gameBackground.loadImage("ui/bg0");
		else
			gameBackground.loadImage("ui/bg1");
			
		gameUI = new GameUI();
		
		gameUI.loadImage();
		
		gameReadData.setGameUITool(this);
		
		gameUI.setGameTime(gameScriptRun.getGameTime());
		
		gameUI.setStageLength(gameScriptRun.getStageLength());
		
		gameUI.setGemNumber(gemNumber);
		
		gameUI.setGoldNumber(goldenNumber);
		
		combo = new Combo();
		
		combo.init(comboShowTime);
		
		combo.loadImage();
		
		gameMonster = new GameMonster();
		
		gameMonster.init();
		
		gameMonster.loadImage();
		
		goldenList = new ArrayList<GoldenAnimation>();
		
		gameHailStone = new GameHailStone();
		
		int airshipLevel = 1;
		
		for(int i=0;i<gameReadData.slot.length;i++)
		{
			if(gameReadData.slot[i] == GameUI.ITEM_TYPE_1)
				airshipLevel = gameReadData.slot_level[i];
		}
		
		gameAirship = new GameAirship(airshipLevel);
		
		int bombLevel = 1;
			
		for(int i=0;i<gameReadData.slot.length;i++)
		{
			if(gameReadData.slot[i] == GameUI.ITEM_TYPE_3)
				bombLevel = gameReadData.slot_level[i];
		}
		
		gameBomb = new GameBomb(bombLevel);
		
//		gameColdBomb = new GameColdBomb();
		
		gameCenterEffect = new GameCenterEffect();
		
		gameSmoke = new GameSmoke();
		
		gameTeleport = new GameTeleport();
				
		gameSpringboard = new GameSpringboard();
		
		gameFingerDance = new GameFingerDance();
		
		gameFirendlyHammer = new GameFirendlyHammer();				
		
		gameLatticeRestore = new GameLatticeRestore(this);
		
		gameBubble = new GameBubble();		
		gameBubble.loadImage();
		
		gameCardAnimation = new ArrayList<GameCardAnimation>();				
		
		gameBanana = new GameBanana();
		gameBanana.loadImage();
		
		gameRainbow = new GameRainbow();
		
		gameCobweb = new GameCobweb();
		gameCobweb.loadImage();
		
		gameRose = new GameRose();
		gameRose.loadImage();
		
		gameMagicDoor = new GameMagicDoor();
		gameMagicDoor.loadImage();
				
		GameStaticImage.loadGameMenuImage();
										
		groupId = 0;
		
		getMogo = false;
		
		comboShowLevel = 1;
		
		cardFinalNum = 3;//每个关卡最多获得3张卡片
		
		nextCardWaitingTime = 0;
		
		isover=false;
//		loadSmallCardImage();
		
//		sirenLight = new SirenLight();
		
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
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub	
		gameBackground.paint(canvas);
						
		slingshot.paintIndicator(canvas);
		
		gameSpringboard.paint(canvas);
		
		gameBanana.paint(canvas);
		
		spriteBullet.paintShadow(canvas);		
						
		gameMonster.paint(canvas, this);				
		
		gameRose.paint(canvas);
		
		gameMagicDoor.paintDoor(canvas);
		
//		gameRainbow.paint(canvas);
		
		gameCobweb.paint(canvas);
		
		spriteLattice.paint(canvas);
			
		spriteBullet.paint(canvas);
			
		slingshot.paint(canvas, this);	
		
		gameUI.paint(this, canvas, gameNumber);
		
		gameBomb.paint(canvas);
		
		gameHailStone.paint(canvas);
		
		combo.paint(canvas, this);	
		
		gameTeleport.paint(canvas);
		
		gameAirship.paint(canvas);				
		
		gameSmoke.paint(canvas);
		
		gameFingerDance.paint(canvas);
		
		gameFirendlyHammer.paint(canvas);
		
		gameLatticeRestore.paint(canvas);
		
		gameCenterEffect.drawCombo(canvas);
		
		drawGoldenAnimation(canvas);	
		
		drawGameCardAnimation(canvas);
		
		gameBubble.paint(canvas);	
				
		gameTeaching.paint(canvas);
		
//		sirenLight.draw(canvas);
		
////		drawGameOver(canvas);
//		
////		GameAwards.showAwards(canvas);
	}

	/*
	 * 绘制奖励关怪物和通常关卡怪物
	 * */
//	private void drawGameGoldMap(Canvas canvas)
//	{
//		if(isGoldenMap)
//		{			
//			if(transition.getTransitionEnd())
//			{
//				gameGoldMap.drawGoldMap(canvas);
//			}
//			else if(!transition.openTransition())
//			{
//				gameMonster.paint(canvas);
//			}
//		}
//		else
//		{
//			if(transition.getTransitionEnd()||
//			   transition.openTransition())
//			{
//				gameMonster.paint(canvas);				
//			}
//			else if(!transition.openTransition())
//			{
//				gameGoldMap.drawGoldMap(canvas);
//			}
//		}		
//	}
	
	/*
	 * 发射单发蔬菜
	 * */
	private void addSpriteBullet()
	{		
		if(combo.getComboState()||combo.getComboSpecialTimeState())
			return;		
		
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
							slingshot.slingShotSpeed, combo.getComboState()?5:3,
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
				slingshot.slingShotSpeed, combo.getComboState()?5:3,
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
				
				//教学
				if(gameTeaching.pauseState())
				{
					if(gameTeaching.getTeachId()==GameTeaching.TEACH_VOL1)
					{
					   getMogo = true;
						
					   gameTeaching.finish();
					}
					else if(gameTeaching.getTeachId()==GameTeaching.TEACH_VOL2)
					{
					   gameTeaching.finish();
					}
				}												 
			}
			
//			gameMission.setSendPlayId(true);
		}				
	}
	
	/*
	 * 自动发射蔬菜
	 * */
	private void automaticAddSpriteBulletUpdata()
	{
		//教学
		if(gameTeaching.pauseState())
			return;
		
		if(combo.getComboState()||combo.getComboSpecialTimeState())
		{									
			slingshot.getIndicator().setSnipeState(false);
			
			automaticBulletIntervalTime--;
			
			if(automaticBulletIntervalTime<=0)
			{
//				sendNum ++;
				
				int actionName = 5;
				
				if(comboShowLevel==1)
					actionName = 5;
				else if(comboShowLevel==2)
					actionName = 9;
				else if(comboShowLevel==3)
					actionName = 11;
				
				spriteBullet.addSpriteBullet(slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, 
						slingshot.slingShotPieceDegree, slingshot.slingShotMaxLen/2, actionName, 
						gameMainLeftPlayerID, 0, 
						0,
						0, 0);
				
				automaticBulletIntervalTime = 4;
			}
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
									spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
									spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);			
										
							gameCobweb.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
							
							gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
							
							gameBanana.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
						}
						else if(spriteBullet.getSpriteBulletList().get(i).getframes()>spriteBullet.getSpriteBulletList().get(i).getMaxFrames()-2)
						{
							spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
									spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*5,
									spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*5,
									spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*5, 
									spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*5);			
										
							gameCobweb.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
							
							gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
							
							gameBanana.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
						}
					}
					else
					{						
						if(spriteBullet.getSpriteBulletList().get(i).getframes()>spriteBullet.getSpriteBulletList().get(i).getMaxFrames()-2)
						{
							spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
									spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
									spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
									spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);			
							
							gameCobweb.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
							
							gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
							
							gameBanana.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
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
								spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2, 
								spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2);											
					}
					else if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG_2)
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
								spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);																														
					}
					else if(spriteBullet.getSpriteBulletList().get(i).kind==CoolEditDefine.Player_MG_3)
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*4,
								spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*4,
								spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*4, 
								spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*4);																								
					}
					
					gameCobweb.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
					
					gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
					
					gameBanana.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
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
								spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3,
								spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*3, 
								spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*3);																													
					}
					else 
					{
						spriteBullet.getSpriteBulletList().get(i).setCollisionRect(
								spriteBullet.getSpriteBulletList().get(i).x-SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).y-SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2,
								spriteBullet.getSpriteBulletList().get(i).x+SpriteLibrary.GetW(spriteBullet.getSpriteBulletList().get(i).kind)/2*2, 
								spriteBullet.getSpriteBulletList().get(i).y+SpriteLibrary.GetH(spriteBullet.getSpriteBulletList().get(i).kind)/2*2);				
					}
					
					gameCobweb.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
					
					gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
					
					gameBanana.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
				}
			}
			//一般蔬菜
			else if(spriteBullet.getSpriteBulletList().get(i).isMove)	
			{
				gameCobweb.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
				
				gameMonster.collision(this, spriteBullet.getSpriteBulletList().get(i));
				
				gameBanana.bulletCollision(this, spriteBullet.getSpriteBulletList().get(i));
			}										
		}
		
		spriteBullet.changeSameGroupIDisCombo();
	}
	
	/*
	 * 怪物和龙卷之间的碰撞
	 * */
//	private void collisionWind()
//	{
//		if(!gameWind.getWindCome())
//			return;
//		
//		gameWind.windSuction(gameMonster);
//		
////		for(int i=0;i<gameMonster.getEnemyList().size();i++)
////		{
////			gameWind.windSuction(gameMonster.getEnemyList().get(i));
////		}
//	}
	
	/*
	 * 怪物和爆炸之间的碰撞
	 * */
	private void collisionBoom()
	{				
//		if(!gameAirship.getState())
//		{
//			showAirshipTime --;
//			
//			if(showAirshipTime<0)
//			{
//				gameCenterEffect.show(GameCenterEffect.AIRSHIP_EFFECT);
//				
////				gameAirship.setStart();
//				
//				showAirshipTime = 3000;
//			}
//			
//			return;
//		}
		
		for(int i=0;i<gameMonster.getEnemyList().size();i++)
		{
			if(
//			   gameMonster.getEnemyList().get(i).kind!=SpriteLibrary.Enemy_HZXJ&&
			   gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_CHG&&
			   gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMB1&&
			   gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMB2&&
			   gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMB3&&
			   gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMB4)
			{
				if(gameMonster.getEnemyList().get(i).transitionWaiting <= 0)
				{
					gameAirship.collision(gameMonster.getEnemyList().get(i), this);
					
					gameBomb.collision(gameMonster.getEnemyList().get(i), this);
					
//					gameColdBomb.collision(gameMonster.getEnemyList().get(i), this);
					
					gameHailStone.collision(gameMonster.getEnemyList().get(i), this);
				}
			}
		}				
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
			
		gameTeaching.updata();
		
//		if(gameTeaching.getState()==gameTeaching.STATE_3)
//		{
//			isTouch = true;
//		}
		
		if(spriteLattice.getGameOver())
			return;				
		
		gameCenterEffect.updata(this);
		
		if(gameCenterEffect.getState()&&
		   gameCenterEffect.getKind()!=GameCenterEffect.COMBO_EFFECT)
		return;				
		
		addSpriteBullet();
		
		automaticAddSpriteBulletUpdata();
		
		slingshot.updata(this);
		
		readSpriteBullet.updata(slingshot.slingShotPiece_x, slingshot.slingShotPiece_y, slingshot.slingShotPieceDegree);
		
		spriteBullet.updata(this);
			
		waitingSpriteBulletLeft.updata(this);
		
		waitingSpriteBulletRight.updata(gameMonster.shxSkillTime>0?true:false);
		
		spriteLattice.updata();
		
		gameBackground.updata();
		
		if(!gameTeaching.pauseState())
		gameUI.updata(this);
		
		combo.updata(this);
		
		gameSmoke.updata(this);
		
		gameTeleport.updata(this);
		
		gameSpringboard.updata(this);
		
		if(!gameTeaching.pauseState())
		gameFingerDance.updata();
		
		gameFirendlyHammer.updata(this);
		
		gameLatticeRestore.updata();
		
		gameBubble.updata();
		
		gameCenterEffect.updata(this);
		
		gameAirship.updata();
		
		gameBomb.updata();
		
		gameHailStone.updata();
		
		collisionBoom();
				
		gameBanana.updata(this);
		
		gameCobweb.updata();
		
		gameRose.updata(this);
		
		gameMagicDoor.update();
		
		if(!gameTeaching.pauseState())
		gameMonster.updata(this);
		
		collision();
		
		gameScriptRun.runScript(this);
		
		goldenAnimationUpdata();						
		
		gameCardAnimationUpdata();
		
//		gameRainbow.updata();
		
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
			if(gameCenterEffect.getState()&&
			   gameCenterEffect.getKind()!=GameCenterEffect.COMBO_EFFECT)
				return;				
			
			if(gameBomb.onTouchEvent(event, this))
				return;
			
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL1)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL2)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL3)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL29)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL43)||
			   !gameTeaching.pauseState())	
			{
				if(slingshot.onTouchEvent(event, this))
					return;
			}
				
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL44)||					  
			   !gameTeaching.pauseState())	
			{
				gameSmoke.onTouchEvent(event, this);
			}
			
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL15)||
			   !gameTeaching.pauseState())	
			{
				gameFingerDance.onTouchEvent(event, this);
			}
			
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL12)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL14)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL15)||
			   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL19)||
			   !gameTeaching.pauseState())	
			{		
				gameUI.onTouchEvent(event, this);
				
			}
			
			if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL13)||					  
			   !gameTeaching.pauseState())	
			{		
				if(gameMonster.shxSkillTime<=0&&!combo.getComboState()&&!combo.getComboSpecialTimeState())
					waitingSpriteBulletRight.onTouchEvent(event, this);
			}
			
			gameRose.onTouchEvent(event);
		}
	}		
	
	public void addGoldenAnimation(Sprite sprite)
	{								
		addGoldenAnimation((int)sprite.x, (int)sprite.y, 
					SpriteLibrary.GetGoldenPercent(sprite.kind, this), SpriteLibrary.GetGoldenNumber(sprite.kind, this), GoldenAnimation.GOLDEN_TYPE);
			
//		addGoldenAnimation((int)sprite.x, (int)sprite.y, 
//				SpriteLibrary.GetGemPercent(sprite.kind), SpriteLibrary.GetGemNumber(sprite.kind), GoldenAnimation.GEM_TYPE);
		
		addGameCardAnimation(this, sprite);
	}
	
	private void addGoldenAnimation(int x, int y, int percent, int num, int type)
	{				
		int rmd = GameLibrary.getIntRandom(0, 99);
		
		if(rmd<percent)
		{			
			GoldenAnimation g = new GoldenAnimation();
			
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
//		System.out.println(gameScriptRun.gameScriptFinish()+"===>>"+gameMonster.getEnemyList().size()+"===>>"+goldenList.size());
//		
//		for(int i=0;i<gameMonster.getEnemyList().size();i++)
//		{
//			System.out.println("111111=============>>>>"+gameMonster.getEnemyList().get(i).kind);
//		}
		if(isover){
			return;
		}
		if(getHideStageState())
		{
			if(gameUI.getCurrentGameTime()==0)
			{
				setLevelData();
				if(LevelData.getData().get(7)>0){
					GameManager.forbidModule(new Gameoverxiangzi(getHideStageState()));
				}else{
					GameManager.forbidModule(new LevelSuccessModule(getHideStageState()));
				}
				isover=true;
			}
		}
		else
		{
			if(gameScriptRun.gameScriptFinish()&&
//			   gameMonster.getEnemyList().size()<=0&&
			   gameMonster.isAliveMonsterNumber()<=0&&
			   goldenList.size()==0&&
			   gameCardAnimation.size()==0)
			{
				gameFinishMissionProcess();
				
				setLevelData();
				
				if(LevelData.getData().get(7)>0){
					GameManager.forbidModule(new Gameoverxiangzi(getHideStageState()));
				}else{
					GameManager.forbidModule(new LevelSuccessModule(getHideStageState()));
				}
				isover=true;
			}
		}
	}
	
	/*
	 * 游戏中的相关任务处理
	 * */
	private void gameMissionProcess()
	{
		if(getHideStageState())
			return;
		
		ArrayList<Integer> index = new ArrayList<Integer>();		
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_10);
	
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission10(spriteLattice.getSpriteLattice().lifeMax, spriteLattice.getSpriteLattice().life);
			}
		}	
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_12);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission12(spriteLattice.getLostBloodNum());				
			}
		}	
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_15);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{				
				gameMission.getStageMissionList().get(index.get(i)).setMission15(gameUI.getGameLostTime());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission15(slingshot.getIsSend());
			}
		}
		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_18);
//		
//		for(int i=0;i<index.size();i++)
//		{
//			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
//			{
//				gameMission.getStageMissionList().get(index.get(i)).setMission18(gameUI.getGameLostTime());
//				
//				gameMission.getStageMissionList().get(index.get(i)).getMission18();
//			}
//		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_19);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission19(gameSpringboard.getTouchSpringBoardNumber());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission19();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_21);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission21(gameMonster.getSirenCallNumber());
			}
		}
	}	

	/*
	 * 游戏暂停中的相关任务处理
	 * */
	public void gamePauseMissionProcess()
	{
		ArrayList<Integer> index = new ArrayList<Integer>();						
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_13);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission13(gameNumber);
				
				gameMission.getStageMissionList().get(index.get(i)).getMission13();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_14);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission14(combo.getStartComboNumber());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission14();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_16);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission16(gameMonster.getAbnormalStateNumber());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission16();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_17);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission17(slingshot.getRightSideSendNumber());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission17();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_20);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission20(gameTeleport.getTouchSpriteNumber());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission20();
			}
		}				
	}	
	
	
	/*
	 * 游戏后的相关任务处理
	 * */
	private void gameFinishMissionProcess()
	{
		if(getHideStageState())
			return;
		
		ArrayList<Integer> index = new ArrayList<Integer>();				
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_10);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).getMission10(spriteLattice.getSpriteLattice().lifeMax, spriteLattice.getSpriteLattice().life);
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_12);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).getMission12();
			}
		}	
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_18);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).setMission18(gameUI.getGameLostTime());
				
				gameMission.getStageMissionList().get(index.get(i)).getMission18();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_21);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).getMission21();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_22);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).getMission22();
			}
		}
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_23);
		
		for(int i=0;i<index.size();i++)
		{
			if(gameMission.getStageMissionList().get(index.get(i)).getMissionResult()==-1)
			{
				gameMission.getStageMissionList().get(index.get(i)).getMission23();
			}
		}
		
		gamePauseMissionProcess();
	}	
	
//	/*
//	 * 游戏后的相关任务处理
//	 * */
//	private void gameFinishMissionProcess()
//	{		
//		if(gameScriptRun.gameScriptFinish()&&gameMonster.getEnemyList().size()<=0)
//		{		
//			ArrayList<Integer> index = new ArrayList<Integer>();
//			
////			index = gameMission.getGameMissionIndex(GameMission.MISSION_1);
////			
////			for(int i=0;i<index.size();i++)
////			{
////				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////				{
////					gameMission.getStageMissionList().get(index.get(i)).getMission1(spriteLattice.getSpriteLattice().lifeMax, spriteLattice.getSpriteLattice().life);
////				}
////			}
////			
////			index = gameMission.getGameMissionIndex(GameMission.MISSION_2);
////			
////			for(int i=0;i<index.size();i++)
////			{
////				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////				{
////					gameMission.getStageMissionList().get(index.get(i)).getMission2((byte)gameMission.lastEndPlayId);
////				}
////			}
////			
////			index = gameMission.getGameMissionIndex(GameMission.MISSION_3);
////			
////			for(int i=0;i<index.size();i++)
////			{
////				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////				{				
////					gameMission.getStageMissionList().get(index.get(i)).getMission3();
////				}
////			}
////			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_10);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{					
//					gameMission.getStageMissionList().get(index.get(i)).setMission10(spriteLattice.getSpriteLattice().lifeMax, spriteLattice.getSpriteLattice().life);
//					
//					gameMission.getStageMissionList().get(index.get(i)).getMission10();
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_12);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission12(spriteLattice.getLostBloodNum());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission12();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_13);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission13(gameNumber);
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission13();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_14);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission14(combo.getStartComboNumber());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission14();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_16);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission16(gameMonster.getAbnormalStateNumber());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission16();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_17);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission17(slingshot.getRightSideSendNumber());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission17();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_18);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission18(gameUI.getGameLostTime());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission18();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_19);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission19(gameSpringboard.getTouchSpringBoardNumber());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission19();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_20);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission20(gameTeleport.getTouchSpriteNumber());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission20();				
//				}
//			}
//			
//			index = gameMission.getGameMissionIndex(GameMission.MISSION_21);
//			
//			for(int i=0;i<index.size();i++)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{							
//					gameMission.getStageMissionList().get(index.get(i)).setMission21(gameMonster.getSirenCallNumber());
//						
//					gameMission.getStageMissionList().get(index.get(i)).getMission21();				
//				}
//			}
//		}
//	}
//	
//	/*
//	 * 游戏中的相关任务处理
//	 * */
//	private void gameMissionProcess()
//	{
//		ArrayList<Integer> index = new ArrayList<Integer>();
//		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_3);
////				
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////			{
////				for(int j=0;j<gameMonster.getEnemyList().size();j++)
////				{
////					gameMission.getStageMissionList().get(index.get(i)).setMission3((byte)gameMonster.getEnemyList().get(j).kind);
////				}
////				
////				gameMission.getStageMissionList().get(index.get(i)).setMission3();
////			}
////		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_4);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////			{							
////				gameMission.getStageMissionList().get(index.get(i)).setMission4(combo.getComboNumber());
////					
////				gameMission.getStageMissionList().get(index.get(i)).getMission4();				
////			}
////		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_5);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////			{		
////				for(int j=0;j<gameMonster.getEnemyList().size();j++)
////				{				
////					gameMission.getStageMissionList().get(index.get(i)).setMission5((byte)gameMonster.getEnemyList().get(j).kind);
////				}	
////				
////				gameMission.getStageMissionList().get(index.get(i)).getMission5();
////			}
////		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_6);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////				gameMission.getStageMissionList().get(index.get(i)).getMission6();
////		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_7);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(slingshot.getLeftSideSendNumber()<=0)
////			{
////				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////				{									
////					gameMission.getStageMissionList().get(index.get(i)).setMission7(gameUI.getGameLostTime());
////					gameMission.getStageMissionList().get(index.get(i)).getMission7();
////				}
////			}
////		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_8);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////			{													
////				gameMission.getStageMissionList().get(index.get(i)).getMission8();
////			}
////		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_9);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////			{													
////				gameMission.getStageMissionList().get(index.get(i)).getMission9();
////			}
////		}
////		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_10);
//		
//		for(int i=0;i<index.size();i++)
//		{
//			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//			{					
//				gameMission.getStageMissionList().get(index.get(i)).setMission10(spriteLattice.getSpriteLattice().lifeMax, spriteLattice.getSpriteLattice().life);
//			}
//		}
////		
////		index = gameMission.getGameMissionIndex(GameMission.MISSION_11);
////		
////		for(int i=0;i<index.size();i++)
////		{
////			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
////			{		
////				for(int j=0;j<gameMonster.getEnemyList().size();j++)
////				{	
////					if(gameMonster.getEnemyList().get(j).dizzinessTime>0)
////					gameMission.getStageMissionList().get(index.get(i)).setMission11();
////				}	
////				
////				gameMission.getStageMissionList().get(index.get(i)).getMission11();
////			}
////		}
//		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_12);
//		
//		for(int i=0;i<index.size();i++)
//		{
//			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//			{							
//				gameMission.getStageMissionList().get(index.get(i)).setMission12(spriteLattice.getLostBloodNum());							
//			}
//		}
//		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_13);
//		
//		for(int i=0;i<index.size();i++)
//		{
//			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//			{							
//				gameMission.getStageMissionList().get(index.get(i)).setMission13(gameNumber);						
//			}
//		}
//		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_15);
//		
//		for(int i=0;i<index.size();i++)
//		{
//			if(slingshot.getLeftSideSendNumber()<=0&&slingshot.getRightSideSendNumber()<=0)
//			{
//				if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//				{									
//					gameMission.getStageMissionList().get(index.get(i)).setMission15(gameUI.getGameLostTime());
//					gameMission.getStageMissionList().get(index.get(i)).getMission15();
//				}
//			}
//		}
//		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_22);
//		
//		for(int i=0;i<index.size();i++)
//		{
//			if(!gameMission.getStageMissionList().get(index.get(i)).getMissionResult())
//			{													
//				gameMission.getStageMissionList().get(index.get(i)).getMission22();
//			}
//		}
//	}
	
	/*
	 * 游戏中任务6处理
	 * */
//	public void gameMission6Process(byte kind)
//	{				
//		ArrayList<Integer> index = new ArrayList<Integer>();
//		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_6);
//				
//		for(int i=0;i<index.size();i++)
//		{			
//			gameMission.getStageMissionList().get(index.get(i)).setMission6(kind);			
//		}
//	}
	
	/*
	 * 游戏中任务9处理
	 * */
//	public void gameMission9Process(byte kind)
//	{				
//		ArrayList<Integer> index = new ArrayList<Integer>();
//		
//		index = gameMission.getGameMissionIndex(GameMission.MISSION_9);
//				
//		for(int i=0;i<index.size();i++)
//		{			
//			gameMission.getStageMissionList().get(index.get(i)).setMission9(kind);			
//		}
//	}		
	
	/*
	 * 游戏中任务22处理
	 * */
	public void gameMission22Process()
	{			
		if(getHideStageState())
			return;
		
		ArrayList<Integer> index = new ArrayList<Integer>();
		
		index = gameMission.getGameMissionIndex(GameMission.MISSION_22);
				
		for(int i=0;i<index.size();i++)
		{			
			gameMission.getStageMissionList().get(index.get(i)).setMission22();			
		}
	}	
	
	private void playerLeftSpecial(int kind)
	{		
		switch(kind)
		{
			case CoolEditDefine.Player_FQ:
				gameMainLeftPlayerSpecial = Sprite.PLAYER_SPECIAL_0;
				break;	
			
			case CoolEditDefine.Player_FQ_2:
				gameMainLeftPlayerSpecial = Sprite.PLAYER_SPECIAL_1;
				break;	
				
			case CoolEditDefine.Player_FQ_3:
				gameMainLeftPlayerSpecial = Sprite.PLAYER_SPECIAL_1;
				break;		
				
			case CoolEditDefine.Player_WD:
			case CoolEditDefine.Player_WD_2:
			case CoolEditDefine.Player_WD_3:
				gameMainLeftPlayerSpecial = Sprite.PLAYER_SPECIAL_0;
				
			case CoolEditDefine.Player_LJ:
			case CoolEditDefine.Player_LJ_2:
			case CoolEditDefine.Player_LJ_3:
				gameMainLeftPlayerSpecial = Sprite.PLAYER_SPECIAL_0;
				
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
				return Sprite.PLAYER_SPECIAL_0;	
				
			case CoolEditDefine.Player_LB:
			case CoolEditDefine.Player_LB_2:
			case CoolEditDefine.Player_LB_3:
				return Sprite.PLAYER_SPECIAL_0;		
				
			case CoolEditDefine.Player_ZS:				
			case CoolEditDefine.Player_ZS_2:				
			case CoolEditDefine.Player_ZS_3:
				return Sprite.PLAYER_SPECIAL_1;	
				
			case CoolEditDefine.Player_TD:	
				return Sprite.PLAYER_SPECIAL_0;		
				
			case CoolEditDefine.Player_TD_2:
				return Sprite.PLAYER_SPECIAL_1;		
				
			case CoolEditDefine.Player_TD_3:
				return Sprite.PLAYER_SPECIAL_2;		
				
			case CoolEditDefine.Player_MG:				
			case CoolEditDefine.Player_MG_2:				
			case CoolEditDefine.Player_MG_3:
				return Sprite.PLAYER_SPECIAL_0;	
				
			case CoolEditDefine.Player_HC:				
			case CoolEditDefine.Player_HC_2:				
			case CoolEditDefine.Player_HC_3:
				return Sprite.PLAYER_SPECIAL_0;		
				
			case CoolEditDefine.Player_NG:				
			case CoolEditDefine.Player_NG_2:				
			case CoolEditDefine.Player_NG_3:
				return Sprite.PLAYER_SPECIAL_3;			
				
			default:
				return Sprite.PLAYER_SPECIAL_0;		
		}		
	}
	
	public int getPlayerRightCdtime(int kind)
	{
		switch(kind)
		{
			case CoolEditDefine.Player_YC:
				return 75;
			
			case CoolEditDefine.Player_YC_2:
				return 100;
				
			case CoolEditDefine.Player_YC_3:
				return 125;		
				
			case CoolEditDefine.Player_LB:
				return 75;
			
			case CoolEditDefine.Player_LB_2:
				return 100;
				
			case CoolEditDefine.Player_LB_3:
				return 125;			
				
			case CoolEditDefine.Player_ZS:
				return 150;	
				
			case CoolEditDefine.Player_ZS_2:
				return 175;	
				
			case CoolEditDefine.Player_ZS_3:
				return 200;	
				
			case CoolEditDefine.Player_TD:
				return 150;	
				
			case CoolEditDefine.Player_TD_2:
				return 175;	
				
			case CoolEditDefine.Player_TD_3:
				return 200;		
				
			case CoolEditDefine.Player_MG:
				return 150;	
				
			case CoolEditDefine.Player_MG_2:
				return 175;	
				
			case CoolEditDefine.Player_MG_3:
				return 200;		
				
			case CoolEditDefine.Player_HC:
				return 150;	
				
			case CoolEditDefine.Player_HC_2:
				return 175;	
				
			case CoolEditDefine.Player_HC_3:
				return 200;		
				
			case CoolEditDefine.Player_NG:
				return 150;	
				
			case CoolEditDefine.Player_NG_2:
				return 175;	
				
			case CoolEditDefine.Player_NG_3:
				return 200;			
				
			default:
				return 25;		
		}		
	}
	
	public void addGameCardAnimation(GameMain gameMain, Sprite sprite)
	{
		if(SpriteLibrary.GetCardsPercent(sprite.kind)>-1||getMogo)
		{
			if(cardFinalNum>0&&nextCardWaitingTime==0)
			{
				cardFinalNum --;
				
				nextCardWaitingTime = 100;
				
				GameCardAnimation _gameCardAnimation = new  GameCardAnimation();
			
//			int index = ExternalMethods.throwDice(0, 2);
			
//			if(gameMain.getMogo)
//			{
//				index = 0;
//				
//				gameMain.getMogo = false;
//			}
			
//			_gameCardAnimation.setGameCardAnimation(gameMain, sprite, card[index], light, type[index]);
			
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
		if(keyCode == KeyEvent.KEYCODE_BACK
				){
		if((gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL12)||
				   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL14)||
				   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL15)||
				   (gameTeaching.pauseState()&&gameTeaching.getTeachId()==GameTeaching.TEACH_VOL19)||
				   !gameTeaching.pauseState())	
				{		
					gameUI.onKeyDown(this);
					
				}
		}
		
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
		
		gameAirship.delImage();
		
		combo.delImage();
		
		gameBackground.delImage();
		
		gameBomb.delImage();
		
		gameBubble.delImage();
		
		gameCenterEffect.delImage();
		
		gameFirendlyHammer.delImage();
		
		gameHailStone.delImage();
		
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
		
		gameBanana.delImage();
		
		gameCobweb.delImage();
		
		gameRose.delImage();
		
		gameMagicDoor.delImage();
		
//		gameFingerDance.delImage();
		
//		gameCardAnimationDelImage();
		
		GameStaticImage.delGameMenuImage();				
		
//		delSmallCardImage();
		
//		System.out.println("peiyu1");
		GameImage.showImageHashMap();
//		System.out.println("peiyu2");
		
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
	
	private void setHideStageState()
	{		
		if(UserRequest.getUser().getHideLevel() && UserRequest.getUser().gethideLevel() == VeggiesData.getCurrentLevel())
		{
			enterHideStage = true;
			
			UserRequest.getUser().setHideLevel(false, false);
		}else
			enterHideStage = false;
		LevelSuccessModule.isNext = false; //点击next还原
	}
	
	public boolean getHideStageState()
	{
		return enterHideStage;	
	}
	
	//-----------------------------------------------------------------
	
//	private Sprite card[];
//	
//	private Sprite light;
//	
//	private int type[];
//	
//	private void loadSmallCardImage()
//	{
//		int cardNumber = 3;
//		
//		card = new Sprite[cardNumber];
//		
//		type = new int[cardNumber];
//		
//		for(int i=0;i<card.length;i++)
//		{
//			if(i==0&&!gameTeaching.teachingArrary[GameTeaching.TEACH_VOL1])
//			{								
//				type[i] = GameItem.Item04;
//			}
//			else
//			{		
//				//type以GameItem类中的卡片ID相对应
//				int rmd = ExternalMethods.throwDice(0, 20);
//												
//				type[i] = rmd*3+1;							
//			}
//			
//			if(type[i]<10)
//				card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_0"+type[i]+"_s"));
//			else 
//				card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_"+type[i]+"_s"));
//		}
//		
//		light = new Sprite(GameImage.getImage("newEffect/Effect_dust_bc"));
//	}
//	
//	public void delSmallCardImage()
//	{
//		for(int i=0;i<card.length;i++)
//		{
//			if(card[i]!=null&&card[i].bitmap!=null)
//			{
//				GameImage.delImage(card[i].bitmap);
//				
//				card[i].recycleBitmap();
//			}
//		}
//	}
}
