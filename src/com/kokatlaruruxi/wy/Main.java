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
	 * justinpy ����ֻ������Ϸ�е���Ч�������ڲ���ʱ������
	 * ��soundpool���ڴ�������1�����ң���ע��������Ч��������
	 * */
	public static void initsound() {
		// TODO Auto-generated method stub
		int soundID[]= {
				R.raw.tomatos,//1����
				R.raw.onions,//2���
				R.raw.carrots,//3���ܲ�
				R.raw.mushrooms,//4Ģ��
				R.raw.peppers,//5����
				R.raw.potatos,//6����
				R.raw.peas,//7�㶹
				R.raw.bambooshoots,//8��
				R.raw.broccolis,//9��Ҭ��
				R.raw.pumpkins,//10�Ϲ�
				
				R.raw.ices_01,//11����
				R.raw.bombs,//12��
				R.raw.slingshots,//13�ظ���ǽ
				R.raw.airships,//14��ͧ

				R.raw.npchurts,//15NPC����
				R.raw.npchurt1s,//16NPC����
				R.raw.npcdies,//17NPC��������ΪĹ����
				R.raw.attacks,//18NPC����(���̣�
				R.raw.npcattacks,//19NPC����(Զ�̣�
				R.raw.npcflys,//20NPC������
				R.raw.hitwalls_01,//21ײǽ
				R.raw.hitwalls_02,//22��ǽ��ײ��
				R.raw.intogrounds,//23��أ�����
				R.raw.moves,//24����ƶ�������
				R.raw.eats,//25���ɣ����ܣ�
//				R.raw.octopuss,//������ī֭
				R.raw.bosss,//26BOSS�ٻ�
				R.raw.mammoths,//27����������
//				R.raw.walks,//�ȵ�
				R.raw.bosshurts,//28��������
				R.raw.hprs,//29����ظ�Ѫ��
				R.raw.bubbles,//30����
				R.raw.summons,//31�������ٻ�
				R.raw.bear1s,//32�ܼ���1
				R.raw.bear2s,//33�ܼ���2
				
				R.raw.vertigos,//34ѣ��
				R.raw.presss,//35ѹ��
				R.raw.parabolas,//36������
				R.raw.littlebombs,//37С��ը
				
				R.raw.boss2s,//38BOSS������Ч
				R.raw.goldfingers,//39���裨����ָ)����
				R.raw.combos,//40combo����
				R.raw.coinsdowns,//41��ҵ���
				R.raw.coinspicks,//42���ʰȡ
				R.raw.gemdowns,//43��ʯ����
				R.raw.gempicks,//44��ʯʰȡ
				R.raw.catapults,//45�Ĵ�����
				R.raw.doors,//46�ӵ�����ʱ����
				R.raw.water1s,//47ħ��ˮ�γ���
				R.raw.fogs,//48�������
				R.raw.water2s,//49ħ��ˮ������
				
				R.raw.buy,//50�����������ɹ�
				R.raw.clicks,//51�������
			
				R.raw.buyfails,//52����������ʧ��
				R.raw.rewards,//53��ý�������ʯ�����ġ���Ƭ�ȣ�
				R.raw.pushouts,//54�鿨��������
				R.raw.boxs,//55���䵯����Ƭ

//				R.raw.loginl,//56�˵�����
//				R.raw.levell_01,//57ս������1
//				R.raw.levell_02,//58ս������2
//				R.raw.levell_03,//59ս������3
//				R.raw.levell_04,//60ս������4
//				R.raw.bossl,//61BOSSս
//				
//				R.raw.cardl,//62�鿨����
//				R.raw.levelsuccessl,//63�ؿ�ʤ��	ϵͳ����
//				R.raw.levelfaill//64�ؿ�ʧ��	ϵͳ����
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
					//��ʾ��Ϣ
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
		
		int SCREEN_WIDTH = getWindowManager().getDefaultDisplay().getWidth(); // �õ���Ļ��
		int SCREEN_HEIGHT = getWindowManager().getDefaultDisplay().getHeight(); // �õ���Ļ��
		
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

	