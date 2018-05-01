package com.googleInAppBillingV3;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

public class VegetablesInApp {

	public static final String PUBLIC_KEY = "";//应用密钥
	
	public static final String SKU_ID_1 = "";//产品id
	public static final String SKU_ID_2 = "";//产品id
	
	public static final byte EXECUTION = 0;//执行状态
	public static final byte SUCCESS = 1;//成功状态
	public static final byte FAIL = -1;//失败状态
	
	private static IabHelper mHelper;
	
	private static byte isBandService;
	
	private static byte isSearch;
	
	private static byte isBuy;
	
	private static boolean isFinished;
	
	private static ArrayList<String> skuMassage;
	
	/*
	 * 初始化，一定要放在主Activity的onCreate(Bundle savedInstanceState)中
	 * 
	 * 参数 _context：当前游戏 Activity
	 * */
	public static void create(Context _context)
	{			
		skuMassage = new ArrayList<String>();
		
		isBandService = 0;
		
		isSearch = 0;
		
		isBuy = 0;
		
		mHelper = new IabHelper(_context, PUBLIC_KEY);		
		
		//处理服务绑定
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() 
		{
			public void onIabSetupFinished(IabResult result) 
			{
				if (!result.isSuccess()) 
				{
					isBandService = -1;
					
					// Oh noes, there was a problem.					
					System.out.println("Problem setting up In-app Billing: " + result);
				}
				else
				{
					isBandService = 1;
					
					// Hooray, IAB is fully set up! ��
				}				
			}
		 });
	}
			
	
	/*
	 * 销毁，一定要放在主Activity的onDestroy()中
	 * */
	public static void destroy()
	{
		if (mHelper != null) 
		mHelper.dispose();
		
		mHelper = null;
	}
	
	/*
	 * 判断是否与服务器绑定
	 * 
	 * 0：为执行状态，1：为成功状态，-1：为失败状态
	 * 
	 * */
	public static byte isBandService()
	{
		return isBandService;
	}
	
	/*
	 * 判断是否查询成功
	 * 
	 * 0：为执行状态，1：为成功状态，-1：为失败状态
	 * 
	 * */
	public static byte isSearch()
	{
		return isSearch;
	}
	
	/*
	 * 判断是否购买成功
	 * 
	 * 0：为执行状态，1：为成功状态，-1：为失败状态
	 * 
	 * */
	public static byte isBuy()
	{
		return isBuy;
	}
	
	/*
	 * 得到查询产品是否已经购买过的返回值
	 * 
	 * false：没有购买过，true：已经购买过
	 * 
	 * */	
	public static boolean isFinished()
	{
		return isFinished;
	}
	
	/*
	 * 获得可购买的商品信息
	 * 
	 * 如果list长度为0表示没有找到
	 * 
	 * 获得一个list，间隔5为一个产品的所有可获得的相关信息，得到的值分别为：价格，描述，产品ID，标题，类型
	 * */
	public ArrayList<String> getSkuMassage()
	{
		return skuMassage;
	}
	
	/*
	 * 查询还未购买的商品信息
	 * 
	 * 参数 _sku：想要查询的一个产品的ID
	 * 
	 * */
	public static void search(final String _sku)
	{
		ArrayList<String> additionalSkuList = new ArrayList<String>();
		additionalSkuList.add(_sku);

		skuMassage.clear();
		
		isSearch = 0;
		
		mHelper.queryInventoryAsync(true, additionalSkuList, new IabHelper.QueryInventoryFinishedListener() 
		{
			public void onQueryInventoryFinished(IabResult result, Inventory inventory)
			{
				if(result.isFailure()) 
				{
					isSearch = -1;
					
					// handle error					
					return;
				}

				isSearch = 1;
				
				skuMassage.add(inventory.getSkuDetails(_sku).getSku());
				skuMassage.add(inventory.getSkuDetails(_sku).getPrice());
				skuMassage.add(inventory.getSkuDetails(_sku).getDescription());			
				skuMassage.add(inventory.getSkuDetails(_sku).getTitle());
				skuMassage.add(inventory.getSkuDetails(_sku).getType());
			}
		});		
	}
	
	/*
	 * 查询还未购买的商品信息
	 * 
	 * 参数 _additionalSkuList：想要查询的一个产品或多个产品的ID，
	 * 
	 * */
	public static void searchList(final ArrayList<String> _additionalSkuList)
	{				
		skuMassage.clear();
		
		isSearch = 0;
		
		mHelper.queryInventoryAsync(true, _additionalSkuList, new IabHelper.QueryInventoryFinishedListener() 
		{
			public void onQueryInventoryFinished(IabResult result, Inventory inventory)
			{
				if(result.isFailure()) 
				{
					// handle error	
					
					isSearch = -1;
					
					return;
				}

				isSearch = 1;
				
				for(int i=0;i<_additionalSkuList.size();i++)
				{
					skuMassage.add(inventory.getSkuDetails(_additionalSkuList.get(i)).getSku());
					skuMassage.add(inventory.getSkuDetails(_additionalSkuList.get(i)).getPrice());
					skuMassage.add(inventory.getSkuDetails(_additionalSkuList.get(i)).getDescription());					
					skuMassage.add(inventory.getSkuDetails(_additionalSkuList.get(i)).getTitle());
					skuMassage.add(inventory.getSkuDetails(_additionalSkuList.get(i)).getType());
				}
			}
		});		
	}
	
	/*
	 * 购买产品
	 * 
	 * 参数 _activity：当前游戏 Activity
	 * 
	 * 参数 _sku：想要购买的一个产品的ID
	 * 
	 * 参数 _requestCode：请求码，任意正整数。Google Play 会回传这个请求码给 Activity 的 onActivityResult 。
	 * 
	 * 参数_extraData：用来附加其他信息的字符串，可以是空的。好的做法是，传送一组字符串，让应用程序可以识别这个购买商品的使用者。
	 * 
	 * 为了安全性，建议去检查回传的附加字符串(_extraData)是否相符。
	 * 
	 * */
	public void pay(Activity _activity, final String _sku, int _requestCode, final String _extraData)
	{
		isBuy = 0;
		
		IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() 
		{
			public void onIabPurchaseFinished(IabResult result, Purchase purchase) 
			{
				if(result.isFailure()) 
				{
					isBuy = -1;
					
					System.out.println("Error purchasing: " + result);
					return;
				}
				else if (purchase.getSku().equals(_sku)) 
				{			
					if(purchase.getDeveloperPayload().equals(_extraData))
					{
						isBuy = 1;
					}
					else
					{
						isBuy = -1;
					}
				}
			}
		};
		
		mHelper.launchPurchaseFlow(_activity, _sku, _requestCode, mPurchaseFinishedListener, _extraData);
	}
	
	/*
	 * 查询该产品是否已经购买过
	 * 
	 * 参数 _sku：想要查询一个产品的ID
	 * 
	 * (此方法为本地缓存查询)
	 * */
	public void searchFinishedList(final String _sku)
	{
		isSearch = 0;
		
		IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() 
		{
			public void onQueryInventoryFinished(IabResult result, Inventory inventory) 
			{
				if(result.isFailure()) 
				{
					// handle error here
					
					isSearch = -1;
					
					System.out.println("Error inventory: " + result);
				}
				else 
				{		
					isSearch = 1;
					
					if(inventory.hasPurchase(_sku))
					{
						isFinished = true;
					}
					else
					{
						isFinished = false;
					}					
				}
			}
		};
		
		mHelper.queryInventoryAsync(mGotInventoryListener);
	}	
}
