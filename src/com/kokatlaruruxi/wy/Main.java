package com.kokatlaruruxi.wy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
//import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.kokatlaruruxi.wy.R;
//import com.endlessvegetables2.ui.ChooseLevelModule;
//import com.endlessvegetables2.ui.GameLevelInfoModule;
import com.endlessvegetables2.ui.ChooseLevelModule2;
import com.endlessvegetables2.ui.Configs;
import com.endlessvegetables2.ui.GameMenu;
import com.endlessvegetables2.ui.GameOutEvent;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.MainActivity;
import com.socogame.coolEdit.CoolEditData;
import com.util.lang.LangUtil;

public class Main extends MainActivity 
{
	public static AlertDialog.Builder  builder;
	
	private static String str_getNameString = null;
	
	private GameLoading loadingMoudule = new GameLoading();
	
    /** Called when the activity is first created. */

	/*
	 * justinpy 这里只载入游戏中的音效，音乐在播放时再载入
	 * （soundpool的内存容量在1兆左右），注意所有音效的总容量
	 * */
	public static void initsound() {
		// TODO Auto-generated method stub
		int soundID[]= {
				R.raw.tomatos,//1番茄
				R.raw.onions,//2洋葱
				R.raw.carrots,//3胡萝卜
				R.raw.mushrooms,//4蘑菇
				R.raw.peppers,//5辣椒
				R.raw.potatos,//6土豆
				R.raw.peas,//7豌豆
				R.raw.bambooshoots,//8笋
				R.raw.broccolis,//9花椰菜
				R.raw.pumpkins,//10南瓜
				
				R.raw.ices_01,//11冰弹
				R.raw.bombs,//12火弹
				R.raw.slingshots,//13回复城墙
				R.raw.airships,//14飞艇

				R.raw.npchurts,//15NPC受伤
				R.raw.npchurt1s,//16NPC受伤
				R.raw.npcdies,//17NPC死亡（变为墓碑）
				R.raw.attacks,//18NPC攻击(近程）
				R.raw.npcattacks,//19NPC攻击(远程）
				R.raw.npcflys,//20NPC被击飞
				R.raw.hitwalls_01,//21撞墙
				R.raw.hitwalls_02,//22城墙被撞击
				R.raw.intogrounds,//23钻地（鼹鼠）
				R.raw.moves,//24钻地移动（鼹鼠）
				R.raw.eats,//25吞噬（青蛙）
//				R.raw.octopuss,//章鱼吐墨汁
				R.raw.bosss,//26BOSS召唤
				R.raw.mammoths,//27猛犸象吼叫声
//				R.raw.walks,//踩地
				R.raw.bosshurts,//28猛犸受伤
				R.raw.hprs,//29猛犸回复血量
				R.raw.bubbles,//30气泡
				R.raw.summons,//31警报器召唤
				R.raw.bear1s,//32熊技能1
				R.raw.bear2s,//33熊技能2
				
				R.raw.vertigos,//34眩晕
				R.raw.presss,//35压扁
				R.raw.parabolas,//36抛物线
				R.raw.littlebombs,//37小爆炸
				
				R.raw.boss2s,//38BOSS出场音效
				R.raw.goldfingers,//39乱舞（金手指)启动
				R.raw.combos,//40combo触发
				R.raw.coinsdowns,//41金币掉落
				R.raw.coinspicks,//42金币拾取
				R.raw.gemdowns,//43宝石掉落
				R.raw.gempicks,//44宝石拾取
				R.raw.catapults,//45蹦床弹射
				R.raw.doors,//46子弹进出时空门
				R.raw.water1s,//47魔法水滴出现
				R.raw.fogs,//48迷雾出现
				R.raw.water2s,//49魔法水滴死亡
				
				R.raw.buy,//50购买（升级）成功
				R.raw.clicks,//51点击按键
			
				R.raw.buyfails,//52购买（升级）失败
				R.raw.rewards,//53获得奖励（宝石、爱心、卡片等）
				R.raw.pushouts,//54抽卡弹出金番茄
				R.raw.boxs,//55宝箱弹出卡片

//				R.raw.loginl,//56菜单界面
//				R.raw.levell_01,//57战斗场景1
//				R.raw.levell_02,//58战斗场景2
//				R.raw.levell_03,//59战斗场景3
//				R.raw.levell_04,//60战斗场景4
//				R.raw.bossl,//61BOSS战
//				
//				R.raw.cardl,//62抽卡界面
//				R.raw.levelsuccessl,//63关卡胜利	系统音乐
//				R.raw.levelfaill//64关卡失败	系统音乐
		};
		
		GameMedia.loadSounds(soundID);		
	}

	@Override
	public void ConfigurationChanged(Configuration arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void Destory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean Pause() {
		// TODO Auto-generated method stub	
		if(!GameConfig.isFacebook)
			GameManager.forbidModule(new GameOutEvent());	
		GameConfig.isFacebook = false;
		return false;
	}

	public static Dialog initAlertDlg() {
		builder = new AlertDialog.Builder(MainActivity.getActivity());
		builder.setMessage(str_getNameString);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					//提示信息
				}
		});
		
	    return builder.show();    	
	}
	
	public static Handler dialogHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				initAlertDlg();
				break;
			case 1:
				Toast.makeText(Main.getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	@Override
	public boolean Resume() {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	@Override
	public void receiveMessage(int id) {
		// TODO Auto-generated method stub
	}
		
	@Override
	public void start() {
		
		int SCREEN_WIDTH = getWindowManager().getDefaultDisplay().getWidth(); // 得到屏幕宽
		int SCREEN_HEIGHT = getWindowManager().getDefaultDisplay().getHeight(); // 得到屏幕高
		
		loadingMoudule	=	new GameLoading();
		
		// TODO Auto-generated method stub		
//		MainActivity.initGame(this, loadingMoudule, 0, GameConfig.ORGScreen_Width, GameConfig.ORGScreen_Height);
		
		MainActivity.initGame(this, loadingMoudule, 0, SCREEN_WIDTH, SCREEN_HEIGHT);		
		
		GameConfig.b_showMemory = true;
		
		GameConfig.b_PngRead = true;
		
		GameConfig.ShowFps = false;

//		initsound();	
		
		Configs.ScreenRectF = new RectF(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		
		VeggiesData data = new VeggiesData();
		
		data.loadGame();

		LangUtil.init();
//		GameAwards.initAwardsList();
		
//		setContentView(/*GameManager.getGameManager()*/R.layout.main);
		 
//		GameManager.ChangeModule(new GameMain());
//		GameManager.forbidModule(new GameMain());
		
//		GameManager.ResetToRunModule(new GameMain());
		
//		GameManager.ResetToRunModule(new PageInfo());
		
//		GameManager.ResetToRunModule(new GameDial());			
		
//		GameManager.ResetToRunModule(new GameMenu());
//		GameManager.ResetToRunModule(new ChooseLevelModule());

//		GameManager.ResetToRunModule(new ChooseLevelModule2());
		
		GameManager.ResetToRunModule(new GameMenu());
		
//		GameManager.ResetToRunModule(new GameStory2());
		
//		CoolEditData.init();
		
//		LangUtil.init();
	}

	
	public boolean onCreateOptionsMenu(Menu menu){
		return false;
	}
	
	public void gotoMenu() {
		// TODO Auto-generated method stub
		
	}

	public void openGameModel(String gameModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keyCode, KeyEvent event) {		
		// TODO Auto-generated method stub		
		return false;
	}

	@Override
	public boolean keyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
}

	