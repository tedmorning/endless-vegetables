package com.endlessvegetables2.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socogame.coolEdit.CoolEditDefine;


public class GameStaticImage {
//	public static final String mapPath = "s1_map/";
	public static final String mapPath = "s2_map/";
	private static final String SHARE = "share/";
	private static final String INTERFACE = "Interface/";
	public static final String WORD = Configs.filePath + "/word/";
	private static final String GAMEOVER = "gameover/";
	public static final String SHOP = "shop/";
//	private static final String SMALLCARD = "smallcard/";
	public static final String SMALLCARD = "smallcard1/";
	private static final String SUCCESS = "success/";
	private static final String TCARD = "TCard/";
	private static final String FARM = "farm/";
	
	//------------大地图背景----------------
	public static final String map_seabeach = mapPath+"seabeach";
	public static final String map_green_2 = mapPath+"green_2";
	public static final String map_green_1 = mapPath+"green_1";
	public static final String map_yellow_2 = mapPath+"yellow_2";
	public static final String map_dune = mapPath+"dune";
	public static final String map_castle = mapPath+"castle";
	public static final String map_stump = mapPath+"stump";
	public static final String map_shell = mapPath+"shell";
	public static final String map_hill_1 = mapPath+"hill_1";
	public static final String map_hill_2 = mapPath+"hill_2";
	public static final String map_stone_2 = mapPath+"stone_2";
	public static final String map_tree_1 = mapPath+"tree_1";
	public static final String map_tree_2 = mapPath+"tree_2";
	public static final String map_grass = mapPath+"grass";
	public static final String map_flower = mapPath+"flower";
	public static final String map_seastar = mapPath+"sea star";
	public static final String map_fish = mapPath+"fish";
	public static final String map_bubble = mapPath+"Bubble";
	public static final String map_water_wave = mapPath+"water_wave";
	public static final String map_vortex = mapPath+"vortex";
	public static final String map_stone_1 = mapPath+"stone_1";
	public static final String map_island_1 = mapPath+"island_1";
	public static final String map_island_2 = mapPath+"island_2";
	public static final String map_ship_2 = mapPath+"ship_2";
	public static final String map_ship_1 = mapPath+"ship_1";
	public static final String map_octopus = mapPath+"map_octopus";
	public static final String map_cloud = mapPath+"cloud2";
	public static final String map_smoke = mapPath+"map_smoke";
	public static final String map_dot_1 = mapPath+"dot_1";
	public static final String map_dot_2 = mapPath+"dot_2";
	public static final String map_button1 = mapPath+"button_1";
	public static final String map_button2 = mapPath+"button_2";
	public static final String map_button3 = mapPath+"button_3";
	public static final String map_button4 = mapPath+"button_4";
	public static final String map_num_1 = mapPath+"map_num_1";
	public static final String map_star_1 = mapPath+"star_1";
	public static final String map_star_2 = mapPath+"star_2";
	public static final String map_star_3 = mapPath+"star_3";
	public static final String map_star_4 = mapPath+"star_4";
	public static final String map_ren_1 = mapPath+"ren_1";
	public static final String map_ren_2 = mapPath+"ren_2";
	public static final String map_enemy_01 = mapPath+"map_enemy_01";
	public static final String map_enemy_02 = mapPath+"map_enemy_02";
	public static final String map_enemy_04 = mapPath+"map_enemy_04";
	public static final String map_full = mapPath + "map_full";
	
	//-------------大地图菜单---------------
	public static final String map_bg = mapPath+"bg";
	public static final String map_kuang3 = mapPath+"kuang3";
	public static final String map_kuang4 = mapPath+"kuang4";
	public static final String map_back = mapPath+"map_back";
	public static final String map_gold = mapPath+"map_gold";
	public static final String map_gem = mapPath+"map_gem";
	public static final String map_heart = mapPath+"map_heart";
	public static final String map_add = mapPath+"map_add";
	public static final String map_store_2 = mapPath+"map_store_2";
	public static final String map_store = mapPath+"map_store";
	public static final String map_mail = mapPath+"map_mail";
	public static final String map_mail_2 = mapPath+"map_mail_2";
	public static final String map_farmbutton2 = mapPath+"farmbutton2";
	public static final String map_farmArrow = mapPath+"farmbutton1";
	public static final String map_num_2 = mapPath+"map_num_2";
	public static final String map_num_3 = mapPath+"map_num_3";
	
	//-----------------其他元素-----------------------
	public static final String share_ui_back_01 = SHARE + "ui_back_01";
	public static final String share_ui_back_02 = SHARE + "ui_back_02";
	public static final String share_ui_back_02_2 = SHARE + "ui_back_02_2";
	public static final String share_ui_close = SHARE + "ui_close";
	public static final String share_ui_button_01 = SHARE + "ui_button_01";
	public static final String share_ui_button_01_2 = SHARE + "ui_button_01_2";
	public static final String share_ui_button_04 = SHARE + "ui_button_04";
	public static final String share_ui_button_05_1 = SHARE + "ui_button_05_1";
	public static final String share_ui_button_05_2 = SHARE + "ui_button_05_2";
	public static final String share_ui_button_05 = SHARE + "ui_button_05";
	public static final String share_ui_button_06 = SHARE + "ui_button_06";
	public static final String share_ui_photo_01 = SHARE + "ui_photo_01";
	public static final String share_ui_photo_02 = SHARE + "ui_photo_02";
	public static final String share_ui_photo_03 = SHARE + "ui_photo_03";
	public static final String share_ui_arrows_01 = SHARE + "ui_arrows_01";
	public static final String share_ui_arrows_02 = SHARE + "ui_arrows_02";
	public static final String share_ui_arrows_03 = SHARE + "ui_arrows_03";
	public static final String share_ui_arrows_04 = SHARE + "ui_arrows_04";
	public static final String share_ui_back_07 = SHARE + "ui_back_07";
	public static final String share_ui_back_04 = SHARE + "ui_back_04";
	public static final String share_ui_back_05 = SHARE + "ui_back_05";
	public static final String share_ui_back_05_1 = SHARE + "ui_back_05_1";
	public static final String share_ui_back_03 = SHARE + "ui_back_03";
	public static final String share_loading_03 = SHARE + "loading_03";
	public static final String share_ui_photo_04 = SHARE + "ui_photo_04";
	public static final String share_ui_photo_05 = SHARE + "ui_photo_05";
	public static final String share_ui_button_03 = SHARE + "ui_button_03";
	public static final String share_ui_back_06 = SHARE + "ui_back_06";
	public static final String share_ui_line = SHARE + "ui_line";
	public static final String share_ui_button_02= SHARE + "ui_button_02";
	public static final String share_ui_back_08= SHARE + "ui_back_08";
	public static final String share_ui_button_07= SHARE + "ui_button_07";
	public static final String share_ui_back_12= SHARE + "ui_back_12";
	public static final String share_num_10= SHARE + "num_10";
	public static final String share_ui_arrows_05= SHARE + "ui_arrows_05";
	public static final String share_ui_arrows_06= SHARE + "ui_arrows_06";
	public static final String share_ui_shine_2= SHARE + "ui_shine_2";
	public static final String share_ui_button_08= SHARE + "ui_button_08";
	public static final String share_icon_facebook_05= SHARE + "icon_facebook_05";
	public static final String share_tips= SHARE + "tips";
	public static final String share_ui_egg= SHARE + "ui_egg";
	
	

	public static final String interface_ui_ribbon_01 = INTERFACE + "ui_ribbon_01";
	public static final String interface_ui_shine = INTERFACE + "ui_shine";
	public static final String interface_star_09 = INTERFACE + "Star_9";
	public static final String interface_star_10 = INTERFACE + "Star_10";
	public static final String interface_star_11 = INTERFACE + "Star_11";
	public static final String interface_star_12 = INTERFACE + "Star_12";
	public static final String interface_star_13 = INTERFACE + "Star_13";
	public static final String interface_star_14 = INTERFACE + "Star_14";
	public static final String interface_star_15 = INTERFACE + "Star_15";
	public static final String interface_icon_music = INTERFACE + "icon_music";
	public static final String interface_icon_music_2 = INTERFACE + "icon_music_2";
	public static final String interface_icon_sound = INTERFACE + "icon_sound";
	public static final String interface_icon_sound_2 = INTERFACE + "icon_sound_2";
	public static final String interface_star_08 = INTERFACE + "Star_08";
	public static final String interface_star_05 = INTERFACE + "Star_05";
	public static final String interface_BG_01 = INTERFACE + "BG_01";
	public static final String interface_loading_02 = INTERFACE + "loading_02";
	public static final String interface_loading_01 = INTERFACE + "loading_01";
	public static final String interface_ui_back_10 = INTERFACE + "ui_back_10";
	public static final String interface_ui_logo = INTERFACE + "ui_logo";
	public static final String interface_ui_play = INTERFACE + "ui_play";
	public static final String interface_ui_back_09 = INTERFACE + "ui_back_09";
	public static final String interface_icon_success = INTERFACE + "icon_success";
	public static final String interface_icon_facebook = INTERFACE + "icon_facebook";
	public static final String interface_icon_card = INTERFACE + "icon_card";
	public static final String interface_icon_about = INTERFACE + "icon_about";
	public static final String interface_icon_set = INTERFACE + "icon_set";
	public static final String interface_icon_facebook_02 = INTERFACE + "icon_facebook_02";
	public static final String interface_card_unlock = INTERFACE + "card_unlock";
	public static final String interface_card_lock = INTERFACE + "card_lock";
	public static final String icon_facebook_01 = INTERFACE + "icon_facebook_01";
	public static final String interface_icon_facebook_03 = INTERFACE + "icon_facebook_03";
	public static final String interface_card_lock_key = INTERFACE + "card_lock_key";
	public static final String interface_reward_card_01 = INTERFACE + "reward_card_01";
	public static final String interface_reward_card_02 = INTERFACE + "reward_card_02";
	public static final String interface_reward_card_03 = INTERFACE + "reward_card_03";
	public static final String interface_reward_back = INTERFACE + "reward_back";
	public static final String interface_ui_tick = INTERFACE + "ui_tick";
	public static final String interface_reward_button = INTERFACE + "reward_button";
	public static final String interface_heidi = INTERFACE + "heidi";
	public static final String interface_ui_new_1 = INTERFACE + "ui_new_1";
	public static final String interface_ui_new_2 = INTERFACE + "ui_new_2";
	
	
	public static final String[] interface_ui_line = 
	{
		INTERFACE + "ui_line_2",
		INTERFACE + "ui_line_3",
		INTERFACE + "ui_line_4",
		INTERFACE + "ui_line_5",
		INTERFACE + "ui_line_6",
		INTERFACE + "ui_line_7",
	};
	
	public static final String word_share = WORD + "key_share";
	public static final String word_again = WORD + "key_again";
	public static final String word_next = WORD + "key_next";
	public static final String word_newrecord = WORD + "W_record";
	public static final String word_gameover = WORD + "title_gameover";
	public static final String word_quit = WORD + "key_quit";
	public static final String word_restart = WORD + "key_restart";
	public static final String word_resume = WORD + "key_resume";
	public static final String word_continue = WORD + "key_continue";
	public static final String word_highscore = WORD + "title_highscore";
	public static final String word_num_02 = WORD + "num_02";
	public static final String word_title_level = WORD + "title_level";
	public static final String word_num_07 = WORD + "num_07";
	public static final String word_num_12 = WORD + "num_12";
	public static final String word_num_06 = WORD + "num_06";
	public static final String word_num_05 = WORD + "num_05";
	public static final String word_num_04 = WORD + "num_04";
	public static final String word_play = WORD + "key_play";
	public static final String word_ask = WORD + "title_ask";
	public static final String word_cards = WORD + "title_cards";
	public static final String word_cards_2 = WORD + "title_cards_2";
	public static final String word_upgrade = WORD + "title_upgrade";
	public static final String word_upgrade_2 = WORD + "title_upgrade_2";
	public static final String word_get = WORD + "title_get";
	public static final String word_ok = WORD + "key_ok";
	public static final String word_vegetable = WORD + "title_vegetable";
	public static final String word_vegetable_2 = WORD + "title_vegetable_2";
	public static final String word_item = WORD + "title_item";
	public static final String word_item_2 = WORD + "title_item_2";
	public static final String word_title_friend = WORD + "title_friend";
	public static final String word_key_request = WORD + "key_request";
	public static final String word_num_03 = WORD + "num_03";
	public static final String word_key_accept_2 = WORD + "key_accept_2";
	public static final String word_title_message = WORD + "title_message";
	public static final String word_key_accept = WORD + "key_accept";
	public static final String word_key_ask_1 = WORD + "key_ask_1";
	public static final String word_key_ask_2 = WORD + "key_ask_2";
	public static final String word_key_send_1 = WORD + "key_send_1";
	public static final String word_title_ranking = WORD + "title_ranking";
	public static final String word_title_ranking_2 = WORD + "title_ranking_2";
	public static final String word_title_success = WORD + "title_success";
	public static final String word_title_success_2 = WORD + "title_success_2";
	public static final String word_title_facebook = WORD + "title_facebook";
	public static final String word_key_invite = WORD + "key_invite";
	public static final String word_num_no1 = WORD + "num_no1";
	public static final String word_num_no2 = WORD + "num_no2";
	public static final String word_num_no3 = WORD + "num_no3";
	public static final String word_num08 = WORD + "num_08";
	public static final String word_title_reward = WORD + "title_reward";
	public static final String word_key_get_2 = WORD + "key_get_2";
	public static final String word_title_unlock = WORD + "title_unlock";
	public static final String word_title_gems = WORD + "title_gems";
	public static final String word_title_gems_2 = WORD + "title_gems_2";
	public static final String word_title_spree = WORD + "title_spree";
	public static final String word_title_spree_2 = WORD + "title_spree_2";
	public static final String word_title_options = WORD + "title_options";
	public static final String word_title_reward_2 = WORD + "title_reward_2";
	public static final String word_title_first = WORD + "title_first";
	public static final String word_title_second = WORD + "title_second";
	public static final String word_title_third = WORD + "title_third";
	public static final String word_title_fourth = WORD + "title_fourth";
	public static final String word__title_fifth = WORD + "title_fifth";
	public static final String word_reward_num = WORD + "reward_num";
	public static final String word_title_ranking_3 = WORD + "title_ranking_3";
	public static final String word_num_11 = WORD + "num_11";
	public static final String word_num_09 = WORD + "num_09";
	public static final String word_key_free = WORD + "key_free";
	
	
	
	
	
	
	public static final String gameover_ui_back_10 = GAMEOVER + "ui_back_10";
	public static final String gameover_shop_card_04 = GAMEOVER + "shop_card_04";
	public static final String gameover_ui_back_09 = GAMEOVER + "ui_back_09";
	public static final String gameover_tomato_over_01 = GAMEOVER + "tomato_over_01";
	public static final String gameover_tomato_over_02 = GAMEOVER + "tomato_over_02";
	public static final String gameover_tomato_over_03 = GAMEOVER + "tomato_over_03";
	public static final String gameover_box_gold = GAMEOVER + "box_gold";
	public static final String gameover_ui_renwu = GAMEOVER + "ui_renwu";
	
	public static final String shop_reward = SHOP + "shop_reward";
	public static final String shop_gold_06 = SHOP + "shop_gold_06";
	public static final String shop_gem_12 = SHOP + "shop_gem_12";
	public static final String shop_gem_12_1 = SHOP + "shop_gem_12_1";
	public static final String shop_gold_06_1 = SHOP + "shop_gold_06_1";
	public static final String shop_item_delete = SHOP + "item_4";
	public static final String shop_box_01 = SHOP + "shop_box_01";
	public static final String shop_box_02 = SHOP + "shop_box_02";
	public static final String shop_box_03 = SHOP + "shop_box_03";
	public static final String shop_box_04 = SHOP + "shop_box_04";
	public static final String shop_heart_01 = SHOP + "shop_heart_01";
	public static final String shop_heart_02 = SHOP + "shop_heart_02";
	public static final String shop_gem_11 = SHOP + "shop_gem_11";
	public static final String shop_shop_gold_11 = SHOP + "shop_gold_11";
	public static final String shop_star_2 = SHOP + "shop_star_2";
	public static final String shop_star_1 = SHOP + "shop_star_1";
	public static final String shop_reward_2 = SHOP + "shop_reward_2";
	public static final String shop_gold_07 = SHOP + "shop_gold_07";
	public static final String shop_gold_08 = SHOP + "shop_gold_08";
	public static final String shop_gold_09 = SHOP + "shop_gold_09";
	public static final String shop_gold_10 = SHOP + "shop_gold_10";
	public static final String shop_gem_13 = SHOP + "shop_gem_13";
	public static final String shop_gem_05 = SHOP + "shop_gem_05";
	public static final String shop_gem_06 = SHOP + "shop_gem_06";
	public static final String shop_gem_07 = SHOP + "shop_gem_07";
	public static final String shop_gem_08 = SHOP + "shop_gem_08";
	public static final String shop_gem_09 = SHOP + "shop_gem_09";
	public static final String shop_gem_10 = SHOP + "shop_gem_10";
	public static final String shop_card_01 = SHOP + "shop_card_01";
	public static final String shop_card_02 = SHOP + "shop_card_02";
	public static final String shop_card_03 = SHOP + "shop_card_03";
	public static final String shop_gem_01 = SHOP + "shop_gem_01";
	public static final String shop_gem_02 = SHOP + "shop_gem_02";
	public static final String shop_gem_03 = SHOP + "shop_gem_03";
	public static final String shop_gem_04 = SHOP + "shop_gem_04";
	public static final String shop_gold_01 = SHOP + "shop_gold_01";
	public static final String shop_gold_02 = SHOP + "shop_gold_02";
	public static final String shop_gold_03 = SHOP + "shop_gold_03";
	public static final String shop_gold_04 = SHOP + "shop_gold_04";
	public static final String shop_heart_03 = SHOP + "shop_heart_03";
	public static final String shop_box1 = SHOP + "box1";
	public static final String shop_box2 = SHOP + "box2";
	public static final String shop_card1 = SHOP + "card1";
	public static final String shop_card2 = SHOP + "card2";
	public static final String shop_king = SHOP + "king";
	public static final String shop_xing = SHOP + "xing";
	
	
	//弹弓
	public static final String shop_item_1[] = {"shop_item_09",
		"shop_item_10",
		"shop_item_11",
		"shop_item_12"};
	//冰山
	public static final String shop_item_2[] = { "shop_item_13",
		 "shop_item_14",
		 "shop_item_15",
		 "shop_item_16"};
	//time
	public static final String shop_item_3[] = { "shop_item_01",
		 "shop_item_02",
		 "shop_item_03",
		 "shop_item_04",};
	//番茄
	public static final String shop_item_4[] = {
		 "shop_item_05",
		 "shop_item_06",
		 "shop_item_07",
		 "shop_item_08",
		 };
//	
	public static final String shop_lv[] = {"shop_lv_01",
			"shop_lv_02"};
	
	//农场
	public static final String farm_ui_ribbon_02 = FARM + "ui_ribbon_02";
	public static final String farm_farm_01 = FARM + "farm_01";
	public static final String farm_icon_facebook_03 = FARM + "icon_facebook_03";
	public static final String farm_farm_card_01 = FARM + "farm_card_01";
	public static final String farm_farm_gold_01 = FARM + "farm_gold_01";
	public static final String farm_farm_heart_01 = FARM + "farm_heart_01";
	public static final String farm_box_01 = FARM + "farm_box_01";
	public static final String farm_farm_card_02 = FARM + "farm_card_02";
	public static final String farm_farm_gold_02 = FARM + "farm_gold_02";
	public static final String farm_farm_heart_02 = FARM + "farm_heart_02";
	public static final String farm_box_02 = FARM + "farm_box_02";
	public static final String farm_farm_card_03 = FARM + "farm_card_03";
	public static final String farm_farm_gold_03 = FARM + "farm_gold_03";
	public static final String farm_farm_heart_03 = FARM + "farm_heart_03";
	public static final String farm_box_03 = FARM + "farm_box_03";
	public static final String farm_farm_02 = FARM + "farm_02";
	public static final String farm_ui_back_11 = FARM + "ui_back_11";
	public static final String farm_ui_back = FARM + "back";
	public static final String farm_ui_soil_1 = FARM + "soil_1";
	public static final String farm_ui_soil_2 = FARM + "soil_2";
	public static final String farm_ui_soil_3 = FARM + "soil_3";
	public static final String farm_ui_soil_4 = FARM + "soil_4";
	public static final String farm_ui_icon_open = FARM + "icon_open";
	public static final String farm_ui_icon_back = FARM + "icon_back";
	public static final String farm_ui_icon_1 = FARM + "icon_1";
	public static final String farm_ui_icon_2 = FARM + "icon_2";
	public static final String farm_ui_icon_3 = FARM + "icon_3";
	public static final String farm_ui_icon_4 = FARM + "icon_4";
	public static final String farm_ui_item_1 = FARM + "item_1";
	public static final String farm_ui_item_2 = FARM + "item_2";
	public static final String farm_ui_item_3 = FARM + "item_3";
	public static final String farm_ui_item_6 = FARM + "item_6";
	public static final String farm_ui_icon_course_1= FARM + "icon_course_1";
	public static final String farm_ui_icon_course_2 = FARM + "icon_course_2";
	public static final String farm_ui_icon_5 = FARM + "icon_5";
	public static final String farm_ui_icon_6 = FARM + "icon_6";
	public static final String farm_ui_icon_open_1 = FARM + "icon_open_1";
	public static final String farm_ui_icon_open_2 = FARM + "icon_open_2";
	public static final String farm_ui_icon_open_3 = FARM + "icon_open_3";
	public static final String farm_ui_gem = FARM + "gem";
	public static final String farm_ui_gold = FARM + "gold";
	public static final String farm_box_04 = FARM + "farm_box_04";
	public static final String farm_box_05 = FARM + "farm_box_05";
	public static final String farm_box_07 = FARM + "farm_box_07";
	public static final String farm_box_08 = FARM + "farm_box_08";
	public static final String farm_box_10 = FARM + "farm_box_10";
	public static final String farm_box_11 = FARM + "farm_box_11";
	public static final String farm_item_4 = FARM + "item_4";
	public static final String farm_item_5 = FARM + "item_5";
	public static final String farm_hand = FARM + "hand";
	public static final String farm_item_41 = FARM + "item_41";
	public static final String farm_item_51 = FARM + "item_51";
	
	
	public static final String smallcard_card_s_01 = SMALLCARD + "card_s_01";
	public static final String smallcard_card_s_02 = SMALLCARD + "card_s_02";
	public static final String smallcard_card_friend = SMALLCARD + "card_friend";
	public static final String smallcard_card_s_cover = SMALLCARD + "card_s_cover";
	public static final String smallcard_card_gray = SMALLCARD + "card_gray";
	
	public static final String success_S_back_1 = SUCCESS + "S_back_1";
	public static final String success_key_get = SUCCESS + "key_get";
	public static final String success_S_course_1 = SUCCESS + "S_course_1";
	public static final String success_S_course_2 = SUCCESS + "S_course_2";
	public static final String success_S_gold = SUCCESS + "S_gold";
	public static final String success_S_icon_01 = SUCCESS + "S_icon_01";
	public static final String success_S_icon_02 = SUCCESS + "S_icon_02";
	public static final String success_S_icon_03 = SUCCESS + "S_icon_03";
	public static final String success_S_icon_04 = SUCCESS + "S_icon_04";
	public static final String success_S_icon_05 = SUCCESS + "S_icon_05";
	public static final String success_S_icon_06 = SUCCESS + "S_icon_06";
	public static final String success_S_icon_07 = SUCCESS + "S_icon_07";
	public static final String success_S_icon_08 = SUCCESS + "S_icon_08";
	public static final String success_S_icon_09 = SUCCESS + "S_icon_09";
	public static final String success_S_icon_10 = SUCCESS + "S_icon_10";
	public static final String success_S_num_1 = SUCCESS + "S_num_1"; 
	public static final String success_shop_gem_14 = SUCCESS + "shop_gem_14"; 
	
	
	public static final String TCard_ui_back_09 = TCARD + "ui_back_09";
	public static final String TCard_shop_box_05 = TCARD + "shop_box_05";
	public static final String TCard_shop_box_06 = TCARD + "shop_box_06";
	public static final String TCard_shop_box_07 = TCARD + "shop_box_07";
	public static final String TCard_shop_box_08 = TCARD + "shop_box_08";
	public static final String TCard_shop_box_09 = TCARD + "shop_box_09";
	public static final String TCard_shop_box_10 = TCARD + "shop_box_10";
	public static final String TCard_shop_box_11 = TCARD + "shop_box_11";
	public static final String TCard_shop_box_12 = TCARD + "shop_box_12";
	public static final String TCard_ui_shine_2 = TCARD + "ui_shine_2";
	public static final String TCard_ui_crossbow = TCARD + "ui_crossbow";
	public static final String TCard_ui_crossbow_2 = TCARD + "ui_crossbow_2";
	public static Paint paint;
	/**
	 * 加载图片资源和画笔资源
	 */
	
	//------------大地图背景----------------
	public static Sprite s_map_seabeach;
	public static Sprite s_map_green_2;
	public static Sprite s_map_green_1;
	public static Sprite s_map_yellow_2;
	public static Sprite s_map_dune;
	public static Sprite s_map_castle;
	public static Sprite s_map_stump;
	public static Sprite s_map_shell;
	public static Sprite s_map_hill_1;
	public static Sprite s_map_hill_2;
	public static Sprite s_map_stone_2;
	public static Sprite s_map_tree_1;
	public static Sprite s_map_tree_2;
	public static Sprite s_map_grass;
	public static Sprite s_map_flower;
	public static Sprite s_map_seastar;
	public static Sprite s_map_fish;
	public static Sprite s_map_bubble;
	public static Sprite s_map_water_wave;
	public static Sprite s_map_vortex;
	public static Sprite s_map_stone_1;
	public static Sprite s_map_island_1;
	public static Sprite s_map_island_2;
	public static Sprite s_map_ship_2;
	public static Sprite s_map_ship_1;
	public static Bitmap[] s_map_octopus;
	public static Sprite s_map_cloud;
	public static Bitmap[] s_map_smoke;
	public static Sprite[] s_map_dot;
	public static Sprite[] s_map_button;
	public static Sprite[] s_map_num_1;
	public static Sprite[] s_map_star;
	public static Sprite s_map_ren_1;
	public static Sprite s_map_ren_2;
//	public static Sprite s_map_enemy_01;
//	public static Sprite s_map_enemy_02;
//	public static Sprite s_map_enemy_04;
//	public static Sprite s_map_full;
	//-------------大地图菜单---------------
	public static Sprite s_map_bg;
	public static Sprite s_map_kuang3;
	public static Sprite s_map_kuang4;
//	public static Sprite s_map_back;
//	public static Sprite s_map_gold;
//	public static Sprite s_map_gem;
//	public static Sprite s_map_heart;
//	public static Sprite s_map_add;
//	public static Sprite s_map_store_2;
//	public static Sprite s_map_store;
//	public static Sprite s_map_mail;
//	public static Sprite s_map_mail_2;
//	public static Sprite s_map_farmbutton2;
//	public static Sprite s_map_farmArrow;
	public static Sprite[] s_map_num_2;
	public static Sprite[] s_map_num_3;
	
	//-----------------其他元素-----------------------
	public static Sprite s_share_ui_back_01;
	public static Sprite s_share_ui_back_02;
	public static Sprite s_share_ui_back_02_2;
	public static Sprite s_share_ui_close;
	public static Sprite[] s_share_ui_button_01;
	public static Sprite s_share_ui_button_03;
//	public static Sprite[] s_share_ui_button_04;
	public static Sprite s_share_ui_button_05_1;
	public static Sprite s_share_ui_button_05_2;
	public static Sprite s_share_ui_button_05;
	public static Sprite s_share_ui_photo_01;
	public static Sprite s_share_ui_photo_02;
	public static Sprite s_share_ui_photo_03;
	public static Sprite[] s_share_ui_arrows_01_02;
	public static Sprite[] s_share_ui_arrows_03_04;
	public static Sprite s_share_ui_back_07;
	public static Sprite[] s_share_ui_back_04;
	public static Sprite s_share_ui_back_03;
	
	public static Sprite s_interface_ui_ribbon_01;
	public static Sprite s_interface_ui_shine;
	public static Sprite[] s_interface_star_09;
	public static Sprite[] s_interface_star_12;
	public static Sprite s_interface_star_15;
	public static Sprite[] s_interface_icon_music;
	public static Sprite[] s_interface_icon_sound;
	public static Sprite s_interface_star_08;
	public static Sprite s_interface_star_05;
	
	public static Sprite s_loading_bg;
//	public static Sprite[] load2;
//	public static Sprite load1;
	public static Sprite s_interface_ui_back_10;
	public static Sprite s_interface_ui_logo;
	public static Sprite s_interface_ui_play;
	public static Sprite s_interface_ui_back_09;
	public static Sprite[] s_interface_menu;
	public static Sprite[] s_interface_smallmenu;
	public static Sprite s_interface_ui_new_1;
	
	public static Sprite s_interface_icon_facebook_02;
	public static Sprite s_interface_card_unlock;
	public static Sprite[] s_interface_ui_line;
	
	public static Sprite s_word_share;
	public static Sprite s_word_again;
	public static Sprite s_word_next;
	public static Sprite s_word_newrecord;
	public static Sprite s_word_gameover;
	public static Sprite s_word_quit;
	public static Sprite s_word_restart;
	public static Sprite s_word_resume;
	public static Sprite s_word_continue;
	public static Sprite s_word_highscore;
	public static Sprite[] s_word_num_02;
	public static Sprite s_word_title_level;
	public static Sprite[] s_word_num_07;
	public static Sprite[] s_word_num_06;
	public static Sprite[] s_word_num_05;
	public static Sprite[] s_word_num_04;
	public static Sprite s_word_play;
	public static Sprite s_word_ask;
	public static Sprite[] s_word_cards;
	public static Sprite[] s_word_upgrade;
	public static Sprite s_word_get;
	public static Sprite s_word_ok;
	public static Sprite[] s_word_vegetable;
	public static Sprite[] s_word_item;
	public static Sprite[] s_word_num_08;

	public static Sprite s_gameover_ui_back_10;
	public static Sprite[] reward;
	public static Sprite s_gameover_ui_back_09;
	public static Bitmap[] s_gameover_tomato_over_01;
	public static Bitmap[] s_gameover_tomato_over_02;
	public static Bitmap[] s_gameover_tomato_over_03;
	public static Sprite ui_renwu;
	public static Sprite s_shop_reward;
//	public static Sprite[] s_shop_box;
	
	public static Sprite[] s_smallcard_card;
	public static Sprite[] noCard;
	public static Sprite s_smallcard_card_friend;
	public static Sprite s_smallcard_card_friend1;
	public static Sprite s_smallcard_card_lock;
	public static Sprite s_smallcard_card_lock_key;
	public static Sprite s_smallcard_card_s_cover;
	public static Sprite s_smallcard_card_gray;
	
	public static Sprite s_success_S_back_1;

//	public static Sprite s_TCard_ui_back_09;
//	public static Sprite[] s_TCard_shop_box;
//	public static Sprite s_TCard_ui_shine_2;
//	public static Sprite[] s_TCard_ui_crossbow;
	
	public static void loadStaticImage() {
		paint = new Paint();
		paint.setTypeface(Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF"));
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		
		s_map_bg = new Sprite(GameImage.getImage(map_bg));
		s_map_kuang3 = new Sprite(GameImage.getImage(map_kuang3));
		s_map_kuang4 = new Sprite(GameImage.getImage(map_kuang4));
//		s_map_back = new Sprite(GameImage.getImage(map_back));
//		s_map_gold = new Sprite(GameImage.getImage(map_gold));
//		s_map_gem = new Sprite(GameImage.getImage(map_gem));
//		s_map_heart = new Sprite(GameImage.getImage(map_heart));
//		s_map_add = new Sprite(GameImage.getImage(map_add));
//		s_map_store_2 = new Sprite(GameImage.getImage(map_store_2));
//		s_map_store = new Sprite(GameImage.getImage(map_store));
//		s_map_mail = new Sprite(GameImage.getImage(map_mail));
//		s_map_mail_2 = new Sprite(GameImage.getImage(map_mail_2));
//		s_map_farmbutton2 = new Sprite(GameImage.getImage(map_farmbutton2));
//		s_map_farmArrow = new Sprite(GameImage.getImage(map_farmArrow));
		s_map_num_2 = GameImage.getAutoSizecutSprite(map_num_2, 11, 1, GameImage.Sort_line);
		s_map_num_3 = GameImage.getAutoSizecutSprite(map_num_3, 10, 1, GameImage.Sort_line);

		s_map_seabeach = new Sprite(GameImage.getImage(map_seabeach));
		s_map_green_2 = new Sprite(GameImage.getImage(map_green_2));
		s_map_green_1 = new Sprite(GameImage.getImage(map_green_1));
		s_map_yellow_2 = new Sprite(GameImage.getImage(map_yellow_2));
		s_map_dune = new Sprite(GameImage.getImage(map_dune));
		s_map_castle = new Sprite(GameImage.getImage(map_castle));
//		s_map_stump = new Sprite(GameImage.getImage(map_stump));
//		s_map_shell = new Sprite(GameImage.getImage(map_shell));
//		s_map_hill_1 = new Sprite(GameImage.getImage(map_hill_1));
		s_map_hill_2 = new Sprite(GameImage.getImage(map_hill_2));
		s_map_stone_2 = new Sprite(GameImage.getImage(map_stone_2));
		s_map_tree_1 = new Sprite(GameImage.getImage(map_tree_1));
		s_map_tree_2 = new Sprite(GameImage.getImage(map_tree_2));
		s_map_grass = new Sprite(GameImage.getImage(map_grass));
		s_map_flower = new Sprite(GameImage.getImage(map_flower));
		s_map_seastar = new Sprite(GameImage.getImage(map_seastar));
		s_map_fish = new Sprite(GameImage.getImage(map_fish));
		s_map_bubble = new Sprite(GameImage.getImage(map_bubble));
		s_map_water_wave = new Sprite(GameImage.getImage(map_water_wave));
		s_map_vortex = new Sprite(GameImage.getImage(map_vortex));
		s_map_stone_1 = new Sprite(GameImage.getImage(map_stone_1));
		s_map_island_1 = new Sprite(GameImage.getImage(map_island_1));
		s_map_island_2 = new Sprite(GameImage.getImage(map_island_2));
		s_map_ship_2 = new Sprite(GameImage.getImage(map_ship_2));
//		s_map_ship_1 = new Sprite(GameImage.getImage(map_ship_1));
//		s_map_octopus = GameImage.getAutoSizecutBitmap(map_octopus, 3, 1, GameImage.Sort_line);
		s_map_cloud = new Sprite(GameImage.getImage(map_cloud));
		s_map_smoke = GameImage.getAutoSizecutBitmap(map_smoke, 4, 1, GameImage.Sort_line);
		s_map_dot = new Sprite[2];
		s_map_dot[0] = new Sprite(GameImage.getImage(map_dot_1));
		s_map_dot[1] = new Sprite(GameImage.getImage(map_dot_2));
		s_map_button = new Sprite[4];
		s_map_button[0] = new Sprite(GameImage.getImage(map_button1));
		s_map_button[1] = new Sprite(GameImage.getImage(map_button2));
		s_map_button[2] = new Sprite(GameImage.getImage(map_button3));
		s_map_button[3] = new Sprite(GameImage.getImage(map_button4));
		s_map_num_1 = GameImage.getAutoSizecutSprite(map_num_1, 10, 1, GameImage.Sort_line);
		s_map_star = new Sprite[4];
		s_map_star[0] = new Sprite(GameImage.getImage(map_star_1));
		s_map_star[1] = new Sprite(GameImage.getImage(map_star_2));
		s_map_star[2] = new Sprite(GameImage.getImage(map_star_3));
		s_map_star[3] = new Sprite(GameImage.getImage(map_star_4));
		if(s_map_ren_1 == null)
			s_map_ren_1 = new Sprite(GameImage.getImage(map_ren_1));
		if(s_map_ren_2 == null)
			s_map_ren_2 = new Sprite(GameImage.getImage(map_ren_2));
//		s_map_enemy_01 = new Sprite(GameImage.getImage(map_enemy_01));
//		s_map_enemy_02 = new Sprite(GameImage.getImage(map_enemy_02));
//		s_map_enemy_04 = new Sprite(GameImage.getImage(map_enemy_04));
		
//		SpriteLibrary.loadSpriteImage(CoolEditDefine.NPC_RAINBOW);
//		SpriteLibrary.loadSpriteImage(CoolEditDefine.NPC_TOMATO);

		s_share_ui_back_01 = new Sprite(GameImage.getImage(share_ui_back_01));
		s_share_ui_back_02 = new Sprite(GameImage.getImage(share_ui_back_02));
		s_share_ui_back_02_2 = new Sprite(GameImage.getImage(share_ui_back_02_2));
		s_share_ui_close = new Sprite(GameImage.getImage(share_ui_close));
		s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(share_ui_button_01));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(share_ui_button_01_2));
//		s_share_ui_button_04 = new Sprite[2];
//		s_share_ui_button_04[0] = new Sprite(GameImage.getImage(share_ui_button_04));
//		s_share_ui_button_04[1] = new Sprite(GameImage.getImage(share_ui_button_06));
		s_share_ui_button_03 = new Sprite(GameImage.getImage(share_ui_button_03));
		s_share_ui_button_05_1 = new Sprite(GameImage.getImage(share_ui_button_05_1));
		s_share_ui_button_05_2 = new Sprite(GameImage.getImage(share_ui_button_05_2));
		s_share_ui_button_05 = new Sprite(GameImage.getImage(share_ui_button_05));
		s_share_ui_photo_01 = new Sprite(GameImage.getImage(share_ui_photo_01));
		s_share_ui_photo_02 = new Sprite(GameImage.getImage(share_ui_photo_02));
		s_share_ui_photo_03 = new Sprite(GameImage.getImage(share_ui_photo_03));
		s_share_ui_arrows_01_02 = new Sprite[2];
		s_share_ui_arrows_01_02[0] = new Sprite(GameImage.getImage(share_ui_arrows_01));
		s_share_ui_arrows_01_02[1] = new Sprite(GameImage.getImage(share_ui_arrows_02));
		s_share_ui_arrows_03_04 = new Sprite[2];
		s_share_ui_arrows_03_04[0] = new Sprite(GameImage.getImage(share_ui_arrows_03));
		s_share_ui_arrows_03_04[1] = new Sprite(GameImage.getImage(share_ui_arrows_04));
		s_share_ui_back_07 = new Sprite(GameImage.getImage(share_ui_back_07));
		s_share_ui_back_04 = new Sprite[3];
		s_share_ui_back_04[0] = new Sprite(GameImage.getImage(share_ui_back_04));
		s_share_ui_back_04[1] = new Sprite(GameImage.getImage(share_ui_back_05));
		s_share_ui_back_04[2] = new Sprite(GameImage.getImage(share_ui_back_05_1));
		s_share_ui_back_03 = new Sprite(GameImage.getImage(share_ui_back_03));
		
		s_loading_bg = new Sprite(GameImage.getImage(GameStaticImage.interface_BG_01));
		s_interface_ui_ribbon_01 = new Sprite(GameImage.getImage(interface_ui_ribbon_01));
		s_interface_ui_shine = new Sprite(GameImage.getImage(interface_ui_shine));
		s_interface_star_09 = new Sprite[3];
		s_interface_star_09[0] = new Sprite(GameImage.getImage(interface_star_09));
		s_interface_star_09[1] = new Sprite(GameImage.getImage(interface_star_10));
		s_interface_star_09[2] = new Sprite(GameImage.getImage(interface_star_11));
		s_interface_star_12 = new Sprite[3];
		s_interface_star_12[0] = new Sprite(GameImage.getImage(interface_star_12));
		s_interface_star_12[1] = new Sprite(GameImage.getImage(interface_star_13));
		s_interface_star_12[2] = new Sprite(GameImage.getImage(interface_star_14));
		s_interface_star_15 = new Sprite(GameImage.getImage(interface_star_15));
//		s_interface_icon_music = new Sprite[2];
//		s_interface_icon_music[0] = new Sprite(GameImage.getImage(interface_icon_music));
//		s_interface_icon_music[1] = new Sprite(GameImage.getImage(interface_icon_music_2));
//		s_interface_icon_sound = new Sprite[2];
//		s_interface_icon_sound[0] = new Sprite(GameImage.getImage(interface_icon_sound));
//		s_interface_icon_sound[1] = new Sprite(GameImage.getImage(interface_icon_sound_2));
		s_interface_star_08 = new Sprite(GameImage.getImage(interface_star_08));
		s_interface_star_05 = new Sprite(GameImage.getImage(interface_star_05));
//		s_interface_ui_back_10 = new Sprite(GameImage.getImage(interface_ui_back_10));
//		s_interface_ui_logo = new Sprite(GameImage.getImage(interface_ui_logo));
//		s_interface_ui_play = new Sprite(GameImage.getImage(interface_ui_play));
//		s_interface_ui_back_09 = new Sprite(GameImage.getImage(interface_ui_back_09));
//		s_interface_menu = new Sprite[3];
//		s_interface_menu[0] = new Sprite(GameImage.getImage(interface_icon_success));
//		s_interface_menu[1] = new Sprite(GameImage.getImage(interface_icon_facebook));
//		s_interface_menu[2] = new Sprite(GameImage.getImage(interface_icon_card));
//		s_interface_smallmenu = new Sprite[2];
//		s_interface_smallmenu[0] = new Sprite(GameImage.getImage(interface_icon_about));
//		s_interface_smallmenu[1] = new Sprite(GameImage.getImage(interface_icon_set));
		s_interface_ui_line = new Sprite[interface_ui_line.length];
		for (int i=0; i<interface_ui_line.length; i++) {
			s_interface_ui_line[i] = new Sprite(GameImage.getImage(interface_ui_line[i]));
		}
		s_interface_icon_facebook_02 = new Sprite(GameImage.getImage(interface_icon_facebook_02));
		s_interface_card_unlock = new Sprite(GameImage.getImage(interface_card_unlock));
		
//		s_word_share = new Sprite(GameImage.getImage(word_share));
//		s_word_again = new Sprite(GameImage.getImage(word_again));
//		s_word_next = new Sprite(GameImage.getImage(word_next));
//		s_word_newrecord = new Sprite(GameImage.getImage(word_newrecord));
//		s_word_gameover = new Sprite(GameImage.getImage(word_gameover));
//		s_word_quit = new Sprite(GameImage.getImage(word_quit));
//		s_word_restart = new Sprite(GameImage.getImage(word_restart));
//		s_word_resume = new Sprite(GameImage.getImage(word_resume));
		s_word_continue = new Sprite(GameImage.getImage(word_continue));
		s_word_highscore = new Sprite(GameImage.getImage(word_highscore));
		s_word_num_02 = GameImage.getAutoSizecutSprite(word_num_02, 10, 1, GameImage.Sort_line);
		s_word_title_level = new Sprite(GameImage.getImage(word_title_level));
		s_word_num_07 = GameImage.getAutoSizecutSprite(word_num_07, 11, 1, GameImage.Sort_line);
		s_word_num_06 = GameImage.getAutoSizecutSprite(word_num_06, 10, 1, GameImage.Sort_line);
		s_word_num_05 = GameImage.getAutoSizecutSprite(word_num_05, 10, 1, GameImage.Sort_line);
		s_word_num_04 = GameImage.getAutoSizecutSprite(word_num_04, 12, 1, GameImage.Sort_line);
		s_word_play = new Sprite(GameImage.getImage(word_play));
		s_word_ask = new Sprite(GameImage.getImage(word_ask));
		s_word_cards = new Sprite[2];
		s_word_cards[0] = new Sprite(GameImage.getImage(word_cards));
		s_word_cards[1] = new Sprite(GameImage.getImage(word_cards_2));
		s_word_upgrade = new Sprite[2];
		s_word_upgrade[0] = new Sprite(GameImage.getImage(word_upgrade));
		s_word_upgrade[1] = new Sprite(GameImage.getImage(word_upgrade_2));
		s_word_get = new Sprite(GameImage.getImage(word_get));
		s_word_ok = new Sprite(GameImage.getImage(word_ok));
		s_word_vegetable = new Sprite[2];
		s_word_vegetable[0] = new Sprite(GameImage.getImage(word_vegetable));
		s_word_vegetable[1] = new Sprite(GameImage.getImage(word_vegetable_2));
		s_word_item = new Sprite[2];
		s_word_item[0] = new Sprite(GameImage.getImage(word_item));
		s_word_item[1] = new Sprite(GameImage.getImage(word_item_2));
		
//		s_gameover_ui_back_10 = new Sprite(GameImage.getImage(gameover_ui_back_10));
//		reward = new Sprite[3];
//		reward[0] = new Sprite(GameImage.getImage(shop_gold_06));
//		reward[1] = new Sprite(GameImage.getImage(shop_gem_12));
//		reward[2] = new Sprite(GameImage.getImage(gameover_shop_card_04));
//		s_gameover_ui_back_09 = new Sprite(GameImage.getImage(gameover_ui_back_09));
//		s_gameover_tomato_over_01 = GameImage.getAutoSizecutBitmap(gameover_tomato_over_01, 3, 1, GameImage.Sort_line);
//		s_gameover_tomato_over_02 = GameImage.getAutoSizecutBitmap(gameover_tomato_over_02, 4, 1, GameImage.Sort_line);
//		s_gameover_tomato_over_03 = GameImage.getAutoSizecutBitmap(gameover_tomato_over_03, 4, 1, GameImage.Sort_line);

//		s_shop_reward = new Sprite(GameImage.getImage(shop_reward));
//		s_shop_box = new Sprite[4];
//		s_shop_box[0] = new Sprite(GameImage.getImage(shop_box_01));
//		s_shop_box[1] = new Sprite(GameImage.getImage(shop_box_02));
//		s_shop_box[2] = new Sprite(GameImage.getImage(shop_box_03));
//		s_shop_box[3] = new Sprite(GameImage.getImage(shop_box_04));

		noCard = new Sprite[2];
		noCard[0] = new Sprite(GameImage.getImage(smallcard_card_s_01));
		noCard[1] = new Sprite(GameImage.getImage(smallcard_card_s_02));
		s_smallcard_card_friend = new Sprite(GameImage.getImage(smallcard_card_friend));
		s_smallcard_card_lock = new Sprite(GameImage.getImage(interface_card_lock));
		s_smallcard_card_lock_key = new Sprite(GameImage.getImage(interface_card_lock_key));
		
		s_smallcard_card_s_cover = new Sprite(GameImage.getImage(smallcard_card_s_cover));
		s_smallcard_card_gray = new Sprite(GameImage.getImage(smallcard_card_gray));

		s_success_S_back_1 = new Sprite(GameImage.getImage(success_S_back_1));

//		s_TCard_ui_back_09 = new Sprite(GameImage.getImage(TCard_ui_back_09));
//		s_TCard_shop_box = new Sprite[8];
//		s_TCard_shop_box[0] = new Sprite(GameImage.getImage(TCard_shop_box_05));
//		s_TCard_shop_box[1] = new Sprite(GameImage.getImage(TCard_shop_box_06));
//		s_TCard_shop_box[2] = new Sprite(GameImage.getImage(TCard_shop_box_07));
//		s_TCard_shop_box[3] = new Sprite(GameImage.getImage(TCard_shop_box_08));
//		s_TCard_shop_box[4] = new Sprite(GameImage.getImage(TCard_shop_box_09));
//		s_TCard_shop_box[5] = new Sprite(GameImage.getImage(TCard_shop_box_10));
//		s_TCard_shop_box[6] = new Sprite(GameImage.getImage(TCard_shop_box_11));
//		s_TCard_shop_box[7] = new Sprite(GameImage.getImage(TCard_shop_box_12));
//		s_TCard_ui_shine_2 = new Sprite(GameImage.getImage(TCard_ui_shine_2));
//		s_TCard_ui_crossbow = new Sprite[2];
//		s_TCard_ui_crossbow[0] = new Sprite(GameImage.getImage(TCard_ui_crossbow));
//		s_TCard_ui_crossbow[1] = new Sprite(GameImage.getImage(TCard_ui_crossbow_2));
	
	}
	
	public static void releaseStaticImage() {
		GameImage.delImage(s_map_bg.bitmap);
		s_map_bg = null;
		GameImage.delImage(s_map_kuang3.bitmap);
		s_map_kuang3 = null;
		GameImage.delImage(s_map_kuang4.bitmap);
		s_map_kuang4 = null;
//		GameImage.delImage(s_map_back.bitmap);
//		s_map_back = null;
//		GameImage.delImage(s_map_gold.bitmap);
//		s_map_gold = null;
//		GameImage.delImage(s_map_gem.bitmap);
//		s_map_gem = null;
//		GameImage.delImage(s_map_heart.bitmap);
//		s_map_heart = null;
//		GameImage.delImage(s_map_add.bitmap);
//		s_map_add = null;
//		GameImage.delImage(s_map_store_2.bitmap);
//		s_map_store_2 = null;
//		GameImage.delImage(s_map_store.bitmap);
//		s_map_store = null;
//		GameImage.delImage(s_map_mail.bitmap);
//		s_map_mail = null;
//		GameImage.delImage(s_map_mail_2.bitmap);
//		s_map_mail_2 = null;
//		GameImage.delImage(s_map_farmbutton2.bitmap);
//		s_map_farmbutton2 = null;
//		GameImage.delImage(s_map_farmArrow.bitmap);
//		s_map_farmArrow = null;

		GameImage.delImage(s_map_seabeach.bitmap);
		s_map_seabeach = null;
		GameImage.delImage(s_map_green_2.bitmap);
		s_map_green_2 = null;
		GameImage.delImage(s_map_green_1.bitmap);
		s_map_green_1 = null;
		GameImage.delImage(s_map_yellow_2.bitmap);
		s_map_yellow_2 = null;
		GameImage.delImage(s_map_dune.bitmap);
		s_map_dune = null;
		GameImage.delImage(s_map_castle.bitmap);
		s_map_castle = null;
		GameImage.delImage(s_map_hill_2.bitmap);
		s_map_hill_2 = null;
		GameImage.delImage(s_map_stone_2.bitmap);
		s_map_stone_2 = null;
		GameImage.delImage(s_map_tree_1.bitmap);
		s_map_tree_1 = null;
		GameImage.delImage(s_map_tree_2.bitmap);
		s_map_tree_2 = null;
		GameImage.delImage(s_map_grass.bitmap);
		s_map_grass = null;
		GameImage.delImage(s_map_flower.bitmap);
		s_map_flower = null;
		GameImage.delImage(s_map_seastar.bitmap);
		s_map_seastar = null;
		GameImage.delImage(s_map_fish.bitmap);
		s_map_fish = null;
		GameImage.delImage(s_map_bubble.bitmap);
		s_map_bubble = null;
		GameImage.delImage(s_map_water_wave.bitmap);
		s_map_water_wave = null;
		GameImage.delImage(s_map_vortex.bitmap);
		s_map_vortex = null;
		GameImage.delImage(s_map_stone_1.bitmap);
		s_map_stone_1 = null;
		GameImage.delImage(s_map_island_1.bitmap);
		s_map_island_1 = null;
		GameImage.delImage(s_map_island_2.bitmap);
		s_map_island_2 = null;
		GameImage.delImage(s_map_ship_2.bitmap);
		s_map_ship_2 = null;
		GameImage.delImage(s_map_cloud.bitmap);
		s_map_cloud = null;
		GameImage.delImage(s_map_dot[0].bitmap);
		GameImage.delImage(s_map_dot[1].bitmap);
		s_map_dot = null;
		GameImage.delImage(s_map_button[0].bitmap);
		GameImage.delImage(s_map_button[1].bitmap);
		GameImage.delImage(s_map_button[2].bitmap);
		GameImage.delImage(s_map_button[3].bitmap);
		s_map_button = null;
		GameImage.delImage(s_map_star[0].bitmap);
		GameImage.delImage(s_map_star[1].bitmap);
		GameImage.delImage(s_map_star[2].bitmap);
		GameImage.delImage(s_map_star[3].bitmap);
		s_map_star = null;
		GameImage.delImage(s_map_ren_1.bitmap);
		s_map_ren_1 = null;
		GameImage.delImage(s_map_ren_2.bitmap);
		s_map_ren_2 = null;
//		GameImage.delImage(s_map_enemy_01.bitmap);
//		s_map_enemy_01 = null;
//		GameImage.delImage(s_map_enemy_02.bitmap);
//		s_map_enemy_02 = null;
//		GameImage.delImage(s_map_enemy_04.bitmap);
//		s_map_enemy_04 = null;
		
//		SpriteLibrary.DelSpriteImage(CoolEditDefine.NPC_RAINBOW);
//		SpriteLibrary.DelSpriteImage(CoolEditDefine.NPC_TOMATO);

		for (int i=0; i<interface_ui_line.length; i++) {
			GameImage.delImage(s_interface_ui_line[i].bitmap);
			s_interface_ui_line[i] = null;
		}
		s_interface_ui_line = null;
		GameImage.delImage(s_interface_card_unlock.bitmap);
		s_interface_card_unlock = null;
		GameImage.delImage(s_interface_icon_facebook_02.bitmap);
		s_interface_icon_facebook_02 = null;
		GameImage.delImage(s_share_ui_button_03.bitmap);
		s_share_ui_button_03 = null;
		GameImage.delImage(s_share_ui_button_05_1.bitmap);
		s_share_ui_button_05_1 = null;
		GameImage.delImage(s_share_ui_button_05_2.bitmap);
		s_share_ui_button_05_2 = null;
		GameImage.delImage(s_share_ui_button_05.bitmap);
		s_share_ui_button_05 = null;
		GameImage.delImage(s_share_ui_photo_01.bitmap);
		s_share_ui_photo_01 = null;
		GameImage.delImage(s_share_ui_photo_02.bitmap);
		s_share_ui_photo_02 = null;
		GameImage.delImage(s_share_ui_photo_03.bitmap);
		s_share_ui_photo_03 = null;
		GameImage.delImage(s_share_ui_arrows_01_02[0].bitmap);
		GameImage.delImage(s_share_ui_arrows_01_02[1].bitmap);
		s_share_ui_arrows_01_02 = null;
		GameImage.delImage(s_share_ui_arrows_03_04[0].bitmap);
		GameImage.delImage(s_share_ui_arrows_03_04[1].bitmap);
		s_share_ui_arrows_03_04 = null;
		GameImage.delImage(s_share_ui_back_07.bitmap);
		s_share_ui_back_07 = null;
		GameImage.delImage(s_share_ui_back_04[0].bitmap);
		GameImage.delImage(s_share_ui_back_04[1].bitmap);
		GameImage.delImage(s_share_ui_back_04[2].bitmap);
		s_share_ui_back_04 = null;
		GameImage.delImage(s_share_ui_back_03.bitmap);
		s_share_ui_back_03 = null;
//		GameImage.delImage(s_share_ui_button_04[0].bitmap);
//		GameImage.delImage(s_share_ui_button_04[1].bitmap);
//		s_share_ui_button_04 = null;

		GameImage.delImage(s_word_continue.bitmap);
		s_word_continue = null;
		GameImage.delImage(s_word_highscore.bitmap);
		s_word_highscore = null;
		GameImage.delImage(s_word_title_level.bitmap);
		s_word_title_level = null;
		GameImage.delImage(s_word_play.bitmap);
		s_word_play = null;
		GameImage.delImage(s_word_ask.bitmap);
		s_word_ask = null;
		GameImage.delImage(s_word_cards[0].bitmap);
		GameImage.delImage(s_word_cards[1].bitmap);
		s_word_cards = null;
		GameImage.delImage(s_word_upgrade[0].bitmap);
		GameImage.delImage(s_word_upgrade[1].bitmap);
		s_word_upgrade = null;
		GameImage.delImage(s_word_get.bitmap);
		s_word_get = null;
		GameImage.delImage(s_word_ok.bitmap);
		s_word_ok = null;
		GameImage.delImage(s_word_vegetable[0].bitmap);
		GameImage.delImage(s_word_vegetable[1].bitmap);		
		s_word_vegetable = null;
		GameImage.delImage(s_word_item[0].bitmap);
		GameImage.delImage(s_word_item[1].bitmap);		
		s_word_item = null;
		
//		GameImage.delImage(s_shop_box[0].bitmap);
//		GameImage.delImage(s_shop_box[1].bitmap);
//		GameImage.delImage(s_shop_box[2].bitmap);
//		GameImage.delImage(s_shop_box[3].bitmap);
//		s_shop_box = null;

		GameImage.delImage(noCard[0].bitmap);
		GameImage.delImage(noCard[1].bitmap);
		noCard = null;
		GameImage.delImage(s_smallcard_card_friend.bitmap);
		s_smallcard_card_friend = null;
		GameImage.delImage(s_smallcard_card_lock.bitmap);
		s_smallcard_card_lock = null;
		GameImage.delImage(s_smallcard_card_lock_key.bitmap);
		s_smallcard_card_lock_key = null;
		
		GameImage.delImage(s_smallcard_card_s_cover.bitmap);
		s_smallcard_card_s_cover = null;
		GameImage.delImage(s_smallcard_card_gray.bitmap);
		s_smallcard_card_gray = null;
	
		GameImage.delImage(s_success_S_back_1.bitmap);
		s_success_S_back_1 = null;

//		GameImage.delImage(s_TCard_ui_back_09.bitmap);
//		s_TCard_ui_back_09 = null;
//		GameImage.delImage(s_TCard_shop_box[0].bitmap);
//		GameImage.delImage(s_TCard_shop_box[1].bitmap);
//		GameImage.delImage(s_TCard_shop_box[2].bitmap);
//		GameImage.delImage(s_TCard_shop_box[3].bitmap);
//		GameImage.delImage(s_TCard_shop_box[4].bitmap);
//		GameImage.delImage(s_TCard_shop_box[5].bitmap);
//		GameImage.delImage(s_TCard_shop_box[6].bitmap);
//		GameImage.delImage(s_TCard_shop_box[7].bitmap);
//		s_TCard_shop_box = null;
//		GameImage.delImage(s_TCard_ui_shine_2.bitmap);
//		s_TCard_ui_shine_2 = null;
//		GameImage.delImage(s_TCard_ui_crossbow[0].bitmap);
//		GameImage.delImage(s_TCard_ui_crossbow[1].bitmap);
//		s_TCard_ui_crossbow = null;
		
		GameImage.delImageArray(s_map_num_2);
		s_map_num_2 = null;
		GameImage.delImageArray(s_map_num_3);
		s_map_num_3 = null;
		GameImage.delImageArray(s_word_num_02);
		s_word_num_02 = null;
		GameImage.delImageArray(s_map_smoke);
		s_map_smoke = null;
		GameImage.delImageArray(s_map_num_1);
		s_map_num_1 = null;
		GameImage.delImageArray(s_word_num_07);
		s_word_num_07 = null;
		GameImage.delImageArray(s_word_num_06);
		s_word_num_06 = null;
		GameImage.delImageArray(s_word_num_05);
		s_word_num_05 = null;
		GameImage.delImageArray(s_word_num_04);
		s_word_num_04 = null;
		
	}
	
	public static void shareReleaseStaticImage() {
		GameImage.delImage(s_share_ui_back_01.bitmap);
		s_share_ui_back_01 = null;
		GameImage.delImage(s_share_ui_back_02.bitmap);
		s_share_ui_back_02 = null;
		GameImage.delImage(s_share_ui_back_02_2.bitmap);
		s_share_ui_back_02_2 = null;
		GameImage.delImage(s_share_ui_close.bitmap);
		s_share_ui_close = null;
		GameImage.delImage(s_share_ui_button_01[0].bitmap);
		GameImage.delImage(s_share_ui_button_01[1].bitmap);
		s_share_ui_button_01 = null;
		
		GameImage.delImage(s_interface_ui_ribbon_01.bitmap);
		s_interface_ui_ribbon_01 = null;
		GameImage.delImage(s_interface_ui_shine.bitmap);
		s_interface_ui_shine = null;
		GameImage.delImage(s_interface_star_09[0].bitmap);
		GameImage.delImage(s_interface_star_09[1].bitmap);
		GameImage.delImage(s_interface_star_09[2].bitmap);
		s_interface_star_09 = null;
		GameImage.delImage(s_interface_star_12[0].bitmap);
		GameImage.delImage(s_interface_star_12[1].bitmap);
		GameImage.delImage(s_interface_star_12[2].bitmap);
		s_interface_star_12 = null;
//		GameImage.delImage(s_interface_icon_music[0].bitmap);
//		GameImage.delImage(s_interface_icon_music[1].bitmap);
//		s_interface_icon_music = null;
//		GameImage.delImage(s_interface_icon_sound[0].bitmap);
//		GameImage.delImage(s_interface_icon_sound[1].bitmap);
//		s_interface_icon_sound = null;
		GameImage.delImage(s_interface_star_08.bitmap);
		s_interface_star_08 = null;
		GameImage.delImage(s_interface_star_05.bitmap);
		s_interface_star_05 = null;
		
//		GameImage.delImage(s_word_share.bitmap);
//		s_word_share = null;
//		GameImage.delImage(s_word_again.bitmap);
//		s_word_again = null;
//		GameImage.delImage(s_word_next.bitmap);
//		s_word_next = null;
//		GameImage.delImage(s_word_newrecord.bitmap);
//		s_word_newrecord = null;
//		GameImage.delImage(s_word_gameover.bitmap);
//		s_word_gameover = null;
//		GameImage.delImage(s_word_quit.bitmap);
//		s_word_quit = null;
//		GameImage.delImage(s_word_restart.bitmap);
//		s_word_restart = null;
//		GameImage.delImage(s_word_resume.bitmap);
//		s_word_resume = null;
//		GameImage.delImage(s_shop_reward.bitmap);
//		s_shop_reward = null;
//		GameImage.delImage(s_gameover_ui_back_10.bitmap);
//		s_gameover_ui_back_10 = null;
//		GameImage.delImage(reward[0].bitmap);
//		GameImage.delImage(reward[1].bitmap);
//		GameImage.delImage(reward[2].bitmap);
//		reward = null;
//		GameImage.delImage(s_gameover_ui_back_09.bitmap);
//		s_gameover_ui_back_09 = null;
//		GameImage.delImageArray(s_gameover_tomato_over_01);
//		s_gameover_tomato_over_01 = null;
//		GameImage.delImageArray(s_gameover_tomato_over_02);
//		s_gameover_tomato_over_02 = null;
//		GameImage.delImageArray(s_gameover_tomato_over_03);
//		s_gameover_tomato_over_03 = null;
	}
	
	public static void delImage() {
		if (s_map_stump != null)
		GameImage.delImage(s_map_stump.bitmap);
		s_map_stump = null;
		if (s_map_shell != null)
		GameImage.delImage(s_map_shell.bitmap);
		s_map_shell = null;
		if (s_map_hill_1 != null)
		GameImage.delImage(s_map_hill_1.bitmap);
		s_map_hill_1 = null;
		if (s_map_octopus != null)
		GameImage.delImageArray(s_map_octopus);
		s_map_octopus = null;
		if (s_map_ship_1 != null)
		GameImage.delImage(s_map_ship_1.bitmap);
		s_map_ship_1 = null;
	}
	
//	public static void delLoadingImage() {
//		GameImage.delImage(s_loading_bg.bitmap);
//		s_loading_bg = null;
//		GameImage.delImageArray(load2);
//		load2 = null;
//		GameImage.delImage(load1.bitmap);
//		load1 = null;
//	}
//	
//	public static void loadLoadingImage() {
//		load2 = GameImage.getAutoSizecutSprite(GameStaticImage.interface_loading_02, 3, 1, GameImage.Sort_line);
//		load1 = new Sprite(GameImage.getImage(GameStaticImage.interface_loading_01));
//	}
	
	public static void delMenuImage() {
		GameImage.delImage(s_interface_ui_back_10.bitmap);
		s_interface_ui_back_10 = null;
		GameImage.delImage(s_interface_ui_logo.bitmap);
		s_interface_ui_logo = null;
		GameImage.delImage(s_interface_ui_play.bitmap);
		s_interface_ui_play = null;
		GameImage.delImage(s_interface_ui_back_09.bitmap);
		s_interface_ui_back_09 = null;
		GameImage.delImage(s_interface_menu[0].bitmap);
		GameImage.delImage(s_interface_menu[1].bitmap);
		GameImage.delImage(s_interface_menu[2].bitmap);
		s_interface_menu = null;
		GameImage.delImage(s_interface_ui_new_1.bitmap);
		s_interface_ui_new_1 = null;
		GameImage.delImage(s_interface_smallmenu[0].bitmap);
		GameImage.delImage(s_interface_smallmenu[1].bitmap);
		s_interface_smallmenu = null;
		GameImage.delImage(s_loading_bg.bitmap);
		s_loading_bg = null;
		
	}
	
	public static void loadMenuImage() {
		s_loading_bg = new Sprite(GameImage.getImage(interface_BG_01));
		s_interface_ui_back_10 = new Sprite(GameImage.getImage(interface_ui_back_10));
		s_interface_ui_logo = new Sprite(GameImage.getImage(interface_ui_logo));
		s_interface_ui_play = new Sprite(GameImage.getImage(interface_ui_play));
		s_interface_ui_back_09 = new Sprite(GameImage.getImage(interface_ui_back_09));
		s_interface_menu = new Sprite[3];
		
		s_interface_menu[0] = new Sprite(GameImage.getImage(interface_icon_success));
		s_interface_menu[1] = new Sprite(GameImage.getImage(interface_icon_facebook));
		s_interface_menu[2] = new Sprite(GameImage.getImage(interface_icon_card));
		
		s_interface_ui_new_1 = new Sprite(GameImage.getImage(interface_ui_new_1));
		s_interface_smallmenu = new Sprite[2];
		s_interface_smallmenu[0] = new Sprite(GameImage.getImage(interface_icon_about));
		s_interface_smallmenu[1] = new Sprite(GameImage.getImage(interface_icon_set));
	}
	
	public static void loadImage() {
		if (s_map_stump == null)
			s_map_stump = new Sprite(GameImage.getImage(map_stump));
		if (s_map_shell == null)
			s_map_shell = new Sprite(GameImage.getImage(map_shell));
		if (s_map_hill_1 == null)
			s_map_hill_1 = new Sprite(GameImage.getImage(map_hill_1));
		if (s_map_octopus == null)
			s_map_octopus = GameImage.getAutoSizecutBitmap(map_octopus, 3, 1, GameImage.Sort_line);
		if (s_map_ship_1 == null)
			s_map_ship_1 = new Sprite(GameImage.getImage(map_ship_1));
	}
	
	public static void loadSmallCard() {
		GameStaticImage.s_smallcard_card = new Sprite[63];
		for (int i=0; i<63; i++) {
			if (i  < 9) {
				GameStaticImage.s_smallcard_card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_0" + (i+1) + "_s"));				
			} else {				
				GameStaticImage.s_smallcard_card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_" + (i+1) + "_s"));				
			}
		}
	}
	
	public static void delSmallCard() {
		LoadSmallThread.isLoadSmalltoExit = true;
		if (s_smallcard_card != null)
		for (int i=0; i<63; i++) {
			if (s_smallcard_card[i] != null)
				GameImage.delImage(s_smallcard_card[i].bitmap);	
			s_smallcard_card[i] = null;
		}
		s_smallcard_card = null;
		
		GameImage.showImageHashMap();
	}
	
	public static void loadGameMenuImage() {
		s_share_ui_back_01 = new Sprite(GameImage.getImage(share_ui_back_01));
		s_share_ui_back_02 = new Sprite(GameImage.getImage(share_ui_back_02));
		s_share_ui_back_02_2 = new Sprite(GameImage.getImage(share_ui_back_02_2));
		s_share_ui_close = new Sprite(GameImage.getImage(share_ui_close));
		s_word_gameover = new Sprite(GameImage.getImage(word_gameover));
		s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(share_ui_button_01));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(share_ui_button_01_2));
		s_word_again = new Sprite(GameImage.getImage(word_again));
		s_gameover_tomato_over_01 = GameImage.getAutoSizecutBitmap(gameover_tomato_over_01, 3, 1, GameImage.Sort_line);
		s_gameover_tomato_over_02 = GameImage.getAutoSizecutBitmap(gameover_tomato_over_02, 4, 1, GameImage.Sort_line);
		s_gameover_tomato_over_03 = GameImage.getAutoSizecutBitmap(gameover_tomato_over_03, 4, 1, GameImage.Sort_line);
		s_interface_ui_ribbon_01 = new Sprite(GameImage.getImage(interface_ui_ribbon_01));
		s_interface_ui_shine = new Sprite(GameImage.getImage(interface_ui_shine));
		s_word_share = new Sprite(GameImage.getImage(word_share));
		s_word_next = new Sprite(GameImage.getImage(word_next));
		s_interface_star_09 = new Sprite[3];
		s_interface_star_09[0] = new Sprite(GameImage.getImage(interface_star_09));
		s_interface_star_09[1] = new Sprite(GameImage.getImage(interface_star_10));
		s_interface_star_09[2] = new Sprite(GameImage.getImage(interface_star_11));
		s_interface_star_12 = new Sprite[3];
		s_interface_star_12[0] = new Sprite(GameImage.getImage(interface_star_12));
		s_interface_star_12[1] = new Sprite(GameImage.getImage(interface_star_13));
		s_interface_star_12[2] = new Sprite(GameImage.getImage(interface_star_14));
		s_gameover_ui_back_10 = new Sprite(GameImage.getImage(gameover_ui_back_10));
		s_shop_reward = new Sprite(GameImage.getImage(shop_reward));
		ui_renwu = new Sprite(GameImage.getImage(gameover_ui_renwu));
		s_word_newrecord = new Sprite(GameImage.getImage(word_newrecord));
		reward = new Sprite[3];
		reward[0] = new Sprite(GameImage.getImage(shop_gold_06));
		reward[1] = new Sprite(GameImage.getImage(shop_gem_12));
		reward[2] = new Sprite(GameImage.getImage(gameover_shop_card_04));
		s_gameover_ui_back_09 = new Sprite(GameImage.getImage(gameover_ui_back_09));
		s_word_quit = new Sprite(GameImage.getImage(word_quit));
		s_word_restart = new Sprite(GameImage.getImage(word_restart));
		s_word_resume = new Sprite(GameImage.getImage(word_resume));
		s_interface_icon_music = new Sprite[2];
		s_interface_icon_music[0] = new Sprite(GameImage.getImage(interface_icon_music));
		s_interface_icon_music[1] = new Sprite(GameImage.getImage(interface_icon_music_2));
		s_interface_icon_sound = new Sprite[2];
		s_interface_icon_sound[0] = new Sprite(GameImage.getImage(interface_icon_sound));
		s_interface_icon_sound[1] = new Sprite(GameImage.getImage(interface_icon_sound_2));
		s_interface_star_08 = new Sprite(GameImage.getImage(interface_star_08));
		s_interface_star_05 = new Sprite(GameImage.getImage(interface_star_05));
		s_word_num_08 = GameImage.getAutoSizecutSprite(word_num08, 11, 1, GameImage.Sort_line);
	}
	
	public static void delGameMenuImage() {
		GameImage.delImage(s_share_ui_back_01.bitmap);
		s_share_ui_back_01 = null;
		GameImage.delImage(s_share_ui_back_02.bitmap);
		s_share_ui_back_02 = null;
		GameImage.delImage(s_share_ui_back_02_2.bitmap);
		s_share_ui_back_02_2 = null;
		GameImage.delImage(s_share_ui_close.bitmap);
		s_share_ui_close = null;
		GameImage.delImage(s_word_gameover.bitmap);
		s_word_gameover = null;
		GameImage.delImage(s_share_ui_button_01[0].bitmap);
		GameImage.delImage(s_share_ui_button_01[1].bitmap);
		s_share_ui_button_01 = null;
		GameImage.delImage(s_word_again.bitmap);
		s_word_again = null;
		GameImage.delImageArray(s_gameover_tomato_over_01);
		s_gameover_tomato_over_01 = null;
		GameImage.delImageArray(s_gameover_tomato_over_02);
		s_gameover_tomato_over_02 = null;
		GameImage.delImageArray(s_gameover_tomato_over_03);
		s_gameover_tomato_over_03 = null;
		GameImage.delImage(s_interface_ui_ribbon_01.bitmap);
		s_interface_ui_ribbon_01 = null;
		GameImage.delImage(s_interface_ui_shine.bitmap);
		s_interface_ui_shine = null;
		GameImage.delImage(s_word_share.bitmap);
		s_word_share = null;
		GameImage.delImage(s_word_next.bitmap);
		s_word_next = null;
		GameImage.delImage(s_interface_star_09[0].bitmap);
		GameImage.delImage(s_interface_star_09[1].bitmap);
		GameImage.delImage(s_interface_star_09[2].bitmap);
		s_interface_star_09 = null;
		GameImage.delImage(s_interface_star_12[0].bitmap);
		GameImage.delImage(s_interface_star_12[1].bitmap);
		GameImage.delImage(s_interface_star_12[2].bitmap);
		s_interface_star_12 = null;
		GameImage.delImage(s_gameover_ui_back_10.bitmap);
		s_gameover_ui_back_10 = null;
		GameImage.delImage(s_shop_reward.bitmap);
		s_shop_reward = null;
		GameImage.delImage(ui_renwu.bitmap);
		ui_renwu = null;
		GameImage.delImage(s_word_newrecord.bitmap);
		s_word_newrecord = null;
		GameImage.delImage(reward[0].bitmap);
		GameImage.delImage(reward[1].bitmap);
		GameImage.delImage(reward[2].bitmap);
		reward = null;
		GameImage.delImage(s_gameover_ui_back_09.bitmap);
		s_gameover_ui_back_09 = null;
		GameImage.delImage(s_word_quit.bitmap);
		s_word_quit = null;
		GameImage.delImage(s_word_restart.bitmap);
		s_word_restart = null;
		GameImage.delImage(s_word_resume.bitmap);
		s_word_resume = null;
		GameImage.delImage(s_interface_icon_music[0].bitmap);
		GameImage.delImage(s_interface_icon_music[1].bitmap);
		s_interface_icon_music = null;
		GameImage.delImage(s_interface_icon_sound[0].bitmap);
		GameImage.delImage(s_interface_icon_sound[1].bitmap);
		s_interface_icon_sound = null;
		GameImage.delImage(s_interface_star_08.bitmap);
		s_interface_star_08 = null;
		GameImage.delImage(s_interface_star_05.bitmap);
		s_interface_star_05 = null;
		GameImage.delImageArray(s_word_num_08);
		s_word_num_08 = null;
	}
	
	public static void loadMapMenu() {
//		s_map_back = new Sprite(GameImage.getImage(map_back));
//		s_map_gold = new Sprite(GameImage.getImage(map_gold));
//		s_map_gem = new Sprite(GameImage.getImage(map_gem));
//		s_map_heart = new Sprite(GameImage.getImage(map_heart));
//		s_map_add = new Sprite(GameImage.getImage(map_add));
//		s_map_store_2 = new Sprite(GameImage.getImage(map_store_2));
//		s_map_store = new Sprite(GameImage.getImage(map_store));
//		s_map_mail = new Sprite(GameImage.getImage(map_mail));
//		s_map_mail_2 = new Sprite(GameImage.getImage(map_mail_2));
//		s_map_farmbutton2 = new Sprite(GameImage.getImage(map_farmbutton2));
//		s_map_farmArrow = new Sprite(GameImage.getImage(map_farmArrow));
		s_map_num_2 = GameImage.getAutoSizecutSprite(map_num_2, 11, 1, GameImage.Sort_line);
		s_map_num_3 = GameImage.getAutoSizecutSprite(map_num_3, 10, 1, GameImage.Sort_line);
//		s_map_full = new Sprite(GameImage.getImage(map_full));
	}
	
	public static void delMapMenu() {
//		GameImage.delImage(s_map_back.bitmap);
//		s_map_back = null;
//		GameImage.delImage(s_map_gold.bitmap);
//		s_map_gold = null;
//		GameImage.delImage(s_map_gem.bitmap);
//		s_map_gem = null;
//		GameImage.delImage(s_map_heart.bitmap);
//		s_map_heart = null;
//		GameImage.delImage(s_map_add.bitmap);
//		s_map_add = null;
//		GameImage.delImage(s_map_store_2.bitmap);
//		s_map_store_2 = null;
//		GameImage.delImage(s_map_store.bitmap);
//		s_map_store = null;
//		GameImage.delImage(s_map_mail.bitmap);
//		s_map_mail = null;
//		GameImage.delImage(s_map_mail_2.bitmap);
//		s_map_mail_2 = null;
//		GameImage.delImage(s_map_farmbutton2.bitmap);
//		s_map_farmbutton2 = null;
//		GameImage.delImage(s_map_farmArrow.bitmap);
//		s_map_farmArrow = null;
		GameImage.delImageArray(s_map_num_2);
		s_map_num_2 = null;
		GameImage.delImageArray(s_map_num_3);
		s_map_num_3 = null;
//		GameImage.delImage(s_map_full.bitmap);
//		s_map_full = null;
	}
	
	public static void loadInfoEquip() {
		s_share_ui_arrows_01_02 = new Sprite[2];
		s_share_ui_arrows_01_02[0] = new Sprite(GameImage.getImage(share_ui_arrows_01));
		s_share_ui_arrows_01_02[1] = new Sprite(GameImage.getImage(share_ui_arrows_02));
		s_interface_star_12 = new Sprite[3];
		s_interface_star_12[0] = new Sprite(GameImage.getImage(interface_star_12));
		s_interface_star_12[1] = new Sprite(GameImage.getImage(interface_star_13));
		s_interface_star_12[2] = new Sprite(GameImage.getImage(interface_star_14));
		s_share_ui_back_01 = new Sprite(GameImage.getImage(share_ui_back_01));
		s_share_ui_back_02 = new Sprite(GameImage.getImage(share_ui_back_02));
		s_share_ui_back_02_2 = new Sprite(GameImage.getImage(share_ui_back_02_2));
		s_share_ui_close = new Sprite(GameImage.getImage(share_ui_close));
		s_interface_ui_ribbon_01 = new Sprite(GameImage.getImage(interface_ui_ribbon_01));
		s_interface_ui_shine = new Sprite(GameImage.getImage(interface_ui_shine));
		s_share_ui_button_05 = new Sprite(GameImage.getImage(share_ui_button_05));
		s_share_ui_button_05_1 = new Sprite(GameImage.getImage(share_ui_button_05_1));
		s_share_ui_button_05_2 = new Sprite(GameImage.getImage(share_ui_button_05_2));
		s_share_ui_button_03 = new Sprite(GameImage.getImage(share_ui_button_03));
		s_word_continue = new Sprite(GameImage.getImage(word_continue));
		s_interface_star_08 = new Sprite(GameImage.getImage(interface_star_08));
		s_interface_star_05 = new Sprite(GameImage.getImage(interface_star_05));
		s_word_highscore = new Sprite(GameImage.getImage(word_highscore));
		s_share_ui_photo_01 = new Sprite(GameImage.getImage(share_ui_photo_01));
		s_interface_icon_facebook_02 = new Sprite(GameImage.getImage(interface_icon_facebook_02));
		s_interface_star_09 = new Sprite[3];
		s_interface_star_09[0] = new Sprite(GameImage.getImage(interface_star_09));
		s_interface_star_09[1] = new Sprite(GameImage.getImage(interface_star_10));
		s_interface_star_09[2] = new Sprite(GameImage.getImage(interface_star_11));
		s_word_num_02 = GameImage.getAutoSizecutSprite(word_num_02, 10, 1, GameImage.Sort_line);
		
		noCard = new Sprite[10];
		for (int i=0; i<noCard.length; i++) {
			if (i >= 9)
			noCard[i] = new Sprite(GameImage.getImage(SMALLCARD + "card_s_" + (i+1)));
			else
			noCard[i] = new Sprite(GameImage.getImage(SMALLCARD + "card_s_0" + (i+1)));
		}
//		s_smallcard_card = new Sprite[63];
//		for (int i=0; i<63; i++) {
//			if (i  < 9) {
//				s_smallcard_card[i] = new Sprite(GameImage.getImage(SMALLCARD + "card_pc_0" + (i+1) + "_s"));				
//			} else {
//				s_smallcard_card[i] = new Sprite(GameImage.getImage(SMALLCARD + "card_pc_" + (i+1) + "_s"));				
//			}
//		}
		s_smallcard_card_friend = new Sprite(GameImage.getImage(smallcard_card_friend));
		s_smallcard_card_friend1 = new Sprite(GameImage.getImage(SMALLCARD + "card_friend_2"));
		s_smallcard_card_lock = new Sprite(GameImage.getImage(interface_card_lock));
		s_smallcard_card_lock_key = new Sprite(GameImage.getImage(interface_card_lock_key));
		s_smallcard_card_gray = new Sprite(GameImage.getImage(smallcard_card_gray));
		s_share_ui_photo_02 = new Sprite(GameImage.getImage(share_ui_photo_02));
		s_share_ui_photo_03 = new Sprite(GameImage.getImage(share_ui_photo_03));
		s_interface_ui_line = new Sprite[interface_ui_line.length];
		for (int i=0; i<interface_ui_line.length; i++) {
			s_interface_ui_line[i] = new Sprite(GameImage.getImage(interface_ui_line[i]));
		}
		s_word_title_level = new Sprite(GameImage.getImage(word_title_level));
		s_word_num_07 = GameImage.getAutoSizecutSprite(word_num_07, 11, 1, GameImage.Sort_line);
		s_word_num_06 = GameImage.getAutoSizecutSprite(word_num_06, 10, 1, GameImage.Sort_line);
		s_word_num_05 = GameImage.getAutoSizecutSprite(word_num_05, 10, 1, GameImage.Sort_line);
		s_word_num_04 = GameImage.getAutoSizecutSprite(word_num_04, 12, 1, GameImage.Sort_line);
		s_word_play = new Sprite(GameImage.getImage(word_play));
		s_share_ui_back_07 = new Sprite(GameImage.getImage(share_ui_back_07));
		s_share_ui_back_04 = new Sprite[3];
		s_share_ui_back_04[0] = new Sprite(GameImage.getImage(share_ui_back_04));
		s_share_ui_back_04[1] = new Sprite(GameImage.getImage(share_ui_back_05));
		s_share_ui_back_04[2] = new Sprite(GameImage.getImage(share_ui_back_05_1));
		s_interface_star_15 = new Sprite(GameImage.getImage(interface_star_15));
		s_word_ask = new Sprite(GameImage.getImage(word_ask));
		s_word_cards = new Sprite[2];
		s_word_cards[0] = new Sprite(GameImage.getImage(word_cards));
		s_word_cards[1] = new Sprite(GameImage.getImage(word_cards_2));
		s_word_upgrade = new Sprite[2];
		s_word_upgrade[0] = new Sprite(GameImage.getImage(word_upgrade));
		s_word_upgrade[1] = new Sprite(GameImage.getImage(word_upgrade_2));
		s_share_ui_back_03 = new Sprite(GameImage.getImage(share_ui_back_03));
		s_success_S_back_1 = new Sprite(GameImage.getImage(success_S_back_1));
		s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(share_ui_button_01));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(share_ui_button_01_2));
//		s_shop_box = new Sprite[4];
//		s_shop_box[0] = new Sprite(GameImage.getImage(shop_box_01));
//		s_shop_box[1] = new Sprite(GameImage.getImage(shop_box_02));
//		s_shop_box[2] = new Sprite(GameImage.getImage(shop_box_03));
//		s_shop_box[3] = new Sprite(GameImage.getImage(shop_box_04));
		s_interface_card_unlock = new Sprite(GameImage.getImage(interface_card_unlock));
	}
	
	public static void delInfoEquip() {
		GameImage.delImage(s_share_ui_arrows_01_02[0].bitmap);
		GameImage.delImage(s_share_ui_arrows_01_02[1].bitmap);
		s_share_ui_arrows_01_02 = null;
		GameImage.delImage(s_interface_star_12[0].bitmap);
		GameImage.delImage(s_interface_star_12[1].bitmap);
		GameImage.delImage(s_interface_star_12[2].bitmap);
		s_interface_star_12 = null;
		GameImage.delImage(s_share_ui_back_01.bitmap);
		s_share_ui_back_01 = null;
		GameImage.delImage(s_share_ui_back_02.bitmap);
		s_share_ui_back_02 = null;
		GameImage.delImage(s_share_ui_back_02_2.bitmap);
		s_share_ui_back_02_2 = null;
		GameImage.delImage(s_share_ui_close.bitmap);
		s_share_ui_close = null;
		GameImage.delImage(s_interface_ui_ribbon_01.bitmap);
		s_interface_ui_ribbon_01 = null;
		GameImage.delImage(s_interface_ui_shine.bitmap);
		s_interface_ui_shine = null;
		GameImage.delImage(s_share_ui_button_05.bitmap);
		s_share_ui_button_05 = null;
		GameImage.delImage(s_share_ui_button_05_1.bitmap);
		s_share_ui_button_05_1 = null;
		GameImage.delImage(s_share_ui_button_05_2.bitmap);
		s_share_ui_button_05_2 = null;
		GameImage.delImage(s_share_ui_button_03.bitmap);
		s_share_ui_button_03 = null;
		GameImage.delImage(s_word_continue.bitmap);
		s_word_continue = null;
		GameImage.delImage(s_interface_star_08.bitmap);
		s_interface_star_08 = null;
		GameImage.delImage(s_interface_star_05.bitmap);
		s_interface_star_05 = null;
		GameImage.delImage(s_word_highscore.bitmap);
		s_word_highscore = null;
		GameImage.delImage(s_share_ui_photo_01.bitmap);
		s_share_ui_photo_01 = null;
		GameImage.delImage(s_interface_icon_facebook_02.bitmap);
		s_interface_icon_facebook_02 = null;
		GameImage.delImage(s_interface_star_09[0].bitmap);
		GameImage.delImage(s_interface_star_09[1].bitmap);
		GameImage.delImage(s_interface_star_09[2].bitmap);
		s_interface_star_09 = null;
		GameImage.delImageArray(s_word_num_02);
		s_word_num_02 = null;
		for(int i=0; i<noCard.length; i++) {
			GameImage.delImage(noCard[i].bitmap);
			noCard[i] = null;
		}
		noCard = null;
//		for(int i=0; i<s_smallcard_card.length; i++) {
//			GameImage.delImage(s_smallcard_card[i].bitmap);
//			s_smallcard_card[i] = null;
//		}
//		s_smallcard_card = null;
		GameImage.delImage(s_smallcard_card_friend.bitmap);
		s_smallcard_card_friend = null;
		GameImage.delImage(s_smallcard_card_friend1.bitmap);
		s_smallcard_card_friend1 = null;
		GameImage.delImage(s_smallcard_card_lock.bitmap);
		s_smallcard_card_lock = null;
		GameImage.delImage(s_smallcard_card_lock_key.bitmap);
		s_smallcard_card_lock_key = null;
		
		GameImage.delImage(s_smallcard_card_gray.bitmap);
		s_smallcard_card_gray = null;
		GameImage.delImage(s_share_ui_photo_02.bitmap);
		s_share_ui_photo_02 = null;
		GameImage.delImage(s_share_ui_photo_03.bitmap);
		s_share_ui_photo_03 = null;
		for (int i=0; i<interface_ui_line.length; i++) {
			GameImage.delImage(s_interface_ui_line[i].bitmap);
			s_interface_ui_line[i] = null;
		}
		s_interface_ui_line = null;
		GameImage.delImage(s_word_title_level.bitmap);
		s_word_title_level = null;
		GameImage.delImageArray(s_word_num_07);
		s_word_num_07 = null;
		GameImage.delImageArray(s_word_num_06);
		s_word_num_06 = null;
		GameImage.delImageArray(s_word_num_05);
		s_word_num_05 = null;
		GameImage.delImageArray(s_word_num_04);
		s_word_num_04 = null;
		GameImage.delImage(s_word_play.bitmap);
		s_word_play = null;
		GameImage.delImage(s_share_ui_back_07.bitmap);
		s_share_ui_back_07 = null;
		GameImage.delImage(s_share_ui_back_04[0].bitmap);
		GameImage.delImage(s_share_ui_back_04[1].bitmap);
		GameImage.delImage(s_share_ui_back_04[2].bitmap);
		s_share_ui_back_04 = null;
		GameImage.delImage(s_interface_star_15.bitmap);
		s_interface_star_15 = null;
		GameImage.delImage(s_word_ask.bitmap);
		s_word_ask = null;
		GameImage.delImage(s_word_cards[0].bitmap);
		GameImage.delImage(s_word_cards[1].bitmap);
		s_word_cards = null;
		GameImage.delImage(s_word_upgrade[0].bitmap);
		GameImage.delImage(s_word_upgrade[1].bitmap);
		s_word_upgrade = null;
		GameImage.delImage(s_share_ui_back_03.bitmap);
		s_share_ui_back_03 = null;
		GameImage.delImage(s_success_S_back_1.bitmap);
		s_success_S_back_1 = null;
		GameImage.delImage(s_share_ui_button_01[0].bitmap);
		GameImage.delImage(s_share_ui_button_01[1].bitmap);
		s_share_ui_button_01 = null;
//		GameImage.delImage(s_shop_box[0].bitmap);
//		GameImage.delImage(s_shop_box[1].bitmap);
//		GameImage.delImage(s_shop_box[2].bitmap);
//		GameImage.delImage(s_shop_box[3].bitmap);
//		s_shop_box = null;
		GameImage.delImage(s_interface_card_unlock.bitmap);
		s_interface_card_unlock = null;
	}
	
//	public static HashMap<String, Sprite> hBitmap = new HashMap<String, Sprite>();
//	
//	public static void loadHashMapImage(String filePath, String imagePath) {
//		Gson gson = new Gson();
//		MyImage ps = gson.fromJson(ExternalMethods.getFromAssets(filePath), MyImage.class);
//		Bitmap bigbmp = GameImage.getFromAssets(imagePath);
//		for(int i=0; i<ps.getFrames().size(); i++) {
//			MyImage.MImage p = ps.getFrames().get(i);
//			Bitmap bmp = GameImage.CreateZoomImage(Bitmap.createBitmap(bigbmp, p.getFrame().x,p.getFrame().y,p.getFrame().w,p.getFrame().h));
//			hBitmap.put(p.getFilename(), new Sprite(bmp));
//		}
//		
//		bigbmp = null;
//	}
//	
//	public static void delHashMapImage() {
//		if (!hBitmap.isEmpty()) {
//			Iterator<String> iterator = hBitmap.keySet().iterator();
//			 while(iterator.hasNext()){
//				 String key = iterator.next();
//				 iterator.remove();//删除key				
//				 hBitmap.remove(key); //删除值
//			 }
//			 hBitmap.clear();
//		}
//	}
	
	/**
	 * 设置画笔大小和颜色
	 * @param size	0为默认大小，-1为不设置大小
	 * @param color
	 */
	public static void setPaint(int size, int color) {
		if (paint == null) {
			paint = new Paint();
			paint.setTypeface(Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF"));
			paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		}
		if (size == 0) size = 20;
		if (size != -1) 
			paint.setTextSize(20*GameConfig.f_zoom);
		paint.setColor(color);
	}
}
