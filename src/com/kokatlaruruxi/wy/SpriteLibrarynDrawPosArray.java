package com.kokatlaruruxi.wy;

public class SpriteLibrarynDrawPosArray {

//	public static short nDrawPos72[][]={//{状态表->帧表->子图表}		
//		//蘑菇3
//		{0,101,55,9,9,4},   //索引:[0] 水平镜象
//		{0,87,81,12,10},    //索引:[1]
//		{0,71,0,36,33},     //索引:[2]
//		{0,71,55,14,18},    //索引:[3]
//		{0,71,77,6,8},      //索引:[4]
//		{0,0,-1,70,52},     //索引:[5]
//		{0,101,81,12,11},   //索引:[6]
//		{0,101,55,9,9},     //索引:[7]
//		{0,70,34,17,19},    //索引:[8]
//		{0,88,63,10,17},    //索引:[9]
//		{0,87,34,11,12},    //索引:[10]
//		{0,79,78,7,7},      //索引:[11]
//		{0,87,48,11,14},    //索引:[12]
//		{0,48,-1,22,52},    //索引:[13]
//		{0,107,1,8,10,5},   //索引:[14] 垂直镜象
//		{0,0,-1,48,52},     //索引:[15]
//		{0,100,64,11,8},    //索引:[16]
//		{0,101,55,9,9,5},   //索引:[17] 垂直镜象
//		{0,102,73,8,6},     //索引:[18]
//		{0,100,34,9,20},    //索引:[19]
//		{0,75,80,2,2},      //索引:[20]
//		{0,75,80,2,2,4},    //索引:[21] 水平镜象
//		{1,6,1,66,74},      //索引:[22]
//		{1,5,82,72,80},     //索引:[23]
//		{1,6,164,66,67},    //索引:[24]
//		{1,5,237,73,80},    //索引:[25]
//		{1,98,14,35,21},    //索引:[26]
//		{0,0,72,71,20},     //索引:[27]
//		{0,0,52,71,19},     //索引:[28]
//		{0,0,52,71,40},     //索引:[29]
//		{0,0,70,71,22},     //索引:[30]
//		{0,12,52,49,20},    //索引:[31]
//		{2,0,0,180,180},    //索引:[32]
//		{3,0,0,71,51},      //索引:[33]
//		{2,180,0,180,180},  //索引:[34]
//		{3,0,51,71,51},     //索引:[35]
//		{2,360,0,180,180},  //索引:[36]
//		{3,0,102,71,51}     //索引:[37]	
//	};
//	
//	public static short nDrawPos73[][]={//{状态表->帧表->子图表}		
//		//花菜
//			{0,0,0,70,40},      //索引:[0]
//			{0,57,91,9,9},      //索引:[1]
//			{0,57,91,9,9,4},    //索引:[2] 水平镜象
//			{0,0,40,70,17},     //索引:[3]
//			{0,0,57,45,33},     //索引:[4]
//			{0,45,73,10,7},     //索引:[5]
//			{0,55,74,9,6},      //索引:[6]
//			{0,47,95,10,5},     //索引:[7]
//			{0,57,85,7,4},      //索引:[8]
//			{0,64,85,5,4},      //索引:[9]
//			{0,40,91,7,7},      //索引:[10]
//			{0,31,92,8,6},      //索引:[11]
//			{0,45,80,10,5},     //索引:[12]
//			{0,55,80,9,5},      //索引:[13]
//			{0,47,90,10,5},     //索引:[14]
//			{0,46,65,24,8},     //索引:[15]
//			{0,47,85,10,5},     //索引:[16]
//			{0,0,0,70,39},      //索引:[17]
//			{0,0,91,8,9},       //索引:[18]
//			{0,16,92,7,7},      //索引:[19]
//			{0,23,92,7,8},      //索引:[20]
//			{0,8,91,7,9},       //索引:[21]
//			{0,47,96,10,4},     //索引:[22]
//			{1,0,0,70,80},      //索引:[23]
//			{1,70,0,70,80},     //索引:[24]
//			{1,140,0,70,80},    //索引:[25]
//			{1,210,0,70,80},    //索引:[26]
//			{1,53,-31,22,21},   //索引:[27]
//			{1,72,-17,22,21}    //索引:[28]
//		
//	};
//	
//	public static short nDrawPos74[][]={//{状态表->帧表->子图表}		
//		//花菜2
//			{0,0,0,70,40},      //索引:[0]
//			{0,57,91,9,9},      //索引:[1]
//			{0,57,91,9,9,4},    //索引:[2] 水平镜象
//			{0,0,40,70,17},     //索引:[3]
//			{0,0,57,45,33},     //索引:[4]
//			{0,45,73,10,7},     //索引:[5]
//			{0,55,74,9,6},      //索引:[6]
//			{0,47,95,10,5},     //索引:[7]
//			{0,57,85,7,4},      //索引:[8]
//			{0,64,85,5,4},      //索引:[9]
//			{0,40,91,7,7},      //索引:[10]
//			{0,31,92,8,6},      //索引:[11]
//			{0,45,80,10,5},     //索引:[12]
//			{0,55,80,9,5},      //索引:[13]
//			{0,47,90,10,5},     //索引:[14]
//			{0,46,65,24,8},     //索引:[15]
//			{0,47,85,10,5},     //索引:[16]
//			{0,0,0,70,39},      //索引:[17]
//			{0,0,91,8,9},       //索引:[18]
//			{0,16,92,7,7},      //索引:[19]
//			{0,23,92,7,8},      //索引:[20]
//			{0,8,91,7,9},       //索引:[21]
//			{0,47,96,10,4},     //索引:[22]
//			{1,0,0,70,80},      //索引:[23]
//			{1,70,0,70,80},     //索引:[24]
//			{1,140,0,70,80},    //索引:[25]
//			{1,210,0,70,80},    //索引:[26]
//			{1,53,-31,22,21},   //索引:[27]
//			{1,72,-17,22,21}    //索引:[28]		
//	};
//	
//	public static short nDrawPos75[][]={//{状态表->帧表->子图表}		
//		//花菜3
//			{0,82,70,6,9},      //索引:[0]
//			{0,82,70,6,9,4},    //索引:[1] 水平镜象
//			{0,0,0,65,40},      //索引:[2]
//			{0,0,40,65,22},     //索引:[3]
//			{0,65,27,24,14},    //索引:[4]
//			{0,49,63,8,8},      //索引:[5]
//			{0,58,63,7,7,4},    //索引:[6] 水平镜象
//			{0,0,63,50,36},     //索引:[7]
//			{0,73,58,10,6},     //索引:[8]
//			{0,74,64,9,5},      //索引:[9]
//			{0,84,41,4,5,4},    //索引:[10] 水平镜象
//			{0,84,46,4,5,4},    //索引:[11] 水平镜象
//			{0,74,41,9,5},      //索引:[12]
//			{0,65,0,12,13},     //索引:[13]
//			{0,78,0,11,11},     //索引:[14]
//			{0,82,79,6,9},      //索引:[15]
//			{0,0,0,65,39},      //索引:[16]
//			{0,65,13,16,14},    //索引:[17]
//			{0,81,12,8,12},     //索引:[18]
//			{0,0,0,65,38},      //索引:[19]
//			{0,82,79,6,9,4},    //索引:[20] 水平镜象
//			{0,49,63,8,7},      //索引:[21]
//			{0,50,70,32,29},    //索引:[22]
//			{0,65,41,8,9},      //索引:[23]
//			{0,65,50,8,9},      //索引:[24]
//			{1,0,0,68,88},      //索引:[25]
//			{1,68,0,68,88},     //索引:[26]
//			{1,136,0,68,88},    //索引:[27]
//			{1,204,0,68,88},    //索引:[28]
//			{1,53,-31,22,21}    //索引:[29]
//				
//	};
//	
//	public static short nDrawPos76[][]={//{状态表->帧表->子图表}		
//		//竹笋
//		{0,126,151,10,13},  //索引:[0]
//		{0,126,98,14,15},   //索引:[1]
//		{0,0,1,56,50},      //索引:[2]
//		{0,0,51,56,28},     //索引:[3]
//		{0,116,0,24,26},    //索引:[4]
//		{0,116,75,11,7},    //索引:[5]
//		{0,129,26,12,24},   //索引:[6]
//		{0,126,138,14,13},  //索引:[7]
//		{0,128,75,12,7},    //索引:[8]
//		{0,116,50,20,12},   //索引:[9]
//		{0,116,63,18,11},   //索引:[10]
//		{0,117,39,10,9},    //索引:[11]
//		{0,117,27,11,11},   //索引:[12]
//		{0,57,0,58,82},     //索引:[13]
//		{0,126,151,10,13,5},//索引:[14] 垂直镜象
//		{0,125,126,12,12},  //索引:[15]
//		{0,126,138,14,13,4},//索引:[16] 水平镜象
//		{0,126,82,12,8},    //索引:[17]
//		{0,126,90,12,8},    //索引:[18]
//		{0,0,82,62,86},     //索引:[19]
//		{0,0,82,62,86,4},   //索引:[20] 水平镜象
//		{0,62,82,62,86},    //索引:[21]
//		{0,62,82,62,86,4},  //索引:[22] 水平镜象
//		{0,45,1,22,21}      //索引:[23]
//	};
//	
//	public static short nDrawPos77[][]={//{状态表->帧表->子图表}		
//		//竹笋2
//		{0,126,151,10,13},  //索引:[0]
//		{0,126,98,14,15},   //索引:[1]
//		{0,0,1,56,50},      //索引:[2]
//		{0,0,51,56,28},     //索引:[3]
//		{0,116,0,24,26},    //索引:[4]
//		{0,116,75,11,7},    //索引:[5]
//		{0,129,26,12,24},   //索引:[6]
//		{0,126,138,14,13},  //索引:[7]
//		{0,128,75,12,7},    //索引:[8]
//		{0,116,50,20,12},   //索引:[9]
//		{0,116,63,18,11},   //索引:[10]
//		{0,117,39,10,9},    //索引:[11]
//		{0,117,27,11,11},   //索引:[12]
//		{0,57,0,58,82},     //索引:[13]
//		{0,126,151,10,13,5},//索引:[14] 垂直镜象
//		{0,125,126,12,12},  //索引:[15]
//		{0,126,138,14,13,4},//索引:[16] 水平镜象
//		{0,126,82,12,8},    //索引:[17]
//		{0,126,90,12,8},    //索引:[18]
//		{0,0,82,62,86},     //索引:[19]
//		{0,0,82,62,86,4},   //索引:[20] 水平镜象
//		{0,62,82,62,86},    //索引:[21]
//		{0,62,82,62,86,4},  //索引:[22] 水平镜象
//		{0,45,1,22,21}      //索引:[23]
//	};
//	
//	public static short nDrawPos78[][]={//{状态表->帧表->子图表}		
//		//竹笋3
//		{0,204,30,11,14},   //索引:[0]
//		{0,204,89,11,13},   //索引:[1]
//		{0,0,46,64,33},     //索引:[2]
//		{0,0,0,64,46},      //索引:[3]
//		{0,184,65,14,15},   //索引:[4]
//		{0,184,0,14,14},    //索引:[5]
//		{0,184,37,19,10},   //索引:[6]
//		{0,184,15,11,14},   //索引:[7]
//		{0,201,63,14,13},   //索引:[8]
//		{0,199,15,16,14},   //索引:[9]
//		{0,201,0,14,12},    //索引:[10]
//		{0,128,20,56,20},   //索引:[11]
//		{0,128,40,56,20},   //索引:[12]
//		{0,128,60,56,20},   //索引:[13]
//		{0,128,0,56,20},    //索引:[14]
//		{0,207,105,8,16},   //索引:[15]
//		{0,207,105,8,16,4}, //索引:[16] 水平镜象
//		{0,210,105,5,16},   //索引:[17]
//		{0,210,105,5,16,4}, //索引:[18] 水平镜象
//		{0,0,80,62,84},     //索引:[19]
//		{0,204,44,11,13},   //索引:[20]
//		{0,184,57,19,8},    //索引:[21]
//		{0,184,47,19,11},   //索引:[22]
//		{0,136,80,68,85},   //索引:[23]
//		{0,136,80,68,85,4}, //索引:[24] 水平镜象
//		{0,66,80,68,85},    //索引:[25]
//		{0,66,80,68,85,4},  //索引:[26] 水平镜象
//		{0,51,1,22,21}      //索引:[27]
//	};
//	
//	public static short nDrawPos79[][]={//{状态表->帧表->子图表}		
//		//灼烧效果
//		{0,0,0,35,47},      //索引:[0]
//		{0,35,0,35,47},     //索引:[1]
//		{0,70,0,35,47},     //索引:[2]
//		{0,105,0,35,47},    //索引:[3]
//		{0,140,0,35,47},    //索引:[4]
//		{0,175,0,35,47},    //索引:[5]
//		{0,0,0,35,47,4},    //索引:[6] 水平镜象
//		{0,35,0,35,47,4},   //索引:[7] 水平镜象
//		{0,70,0,35,47,4},   //索引:[8] 水平镜象
//		{0,105,0,35,47,4},  //索引:[9] 水平镜象
//		{0,140,0,35,47,4},  //索引:[10] 水平镜象
//		{0,175,0,35,47,4}   //索引:[11] 水平镜象
//	};
//	
//	public static short nDrawPos80[][]={//{状态表->帧表->子图表}		
//		//南瓜
//		{0,228,129,16,11},  //索引:[0]
//		{0,271,85,12,21},   //索引:[1]
//		{0,-1,78,75,71},    //索引:[2]
//		{0,226,85,12,22},   //索引:[3]
//		{0,155,89,54,22},   //索引:[4]
//		{0,251,138,19,10},  //索引:[5]
//		{0,229,112,45,13},  //索引:[6]
//		{0,253,85,12,21,4}, //索引:[7] 水平镜象
//		{0,-1,141,75,8},    //索引:[8]
//		{0,-1,78,75,62},    //索引:[9]
//		{0,240,85,12,22},   //索引:[10]
//		{0,79,77,74,78},    //索引:[11]
//		{0,250,124,19,10},  //索引:[12]
//		{0,156,134,54,22},  //索引:[13]
//		{0,0,0,74,78},      //索引:[14]
//		{0,75,-2,78,78},    //索引:[15]
//		{0,155,5,74,81},    //索引:[16]
//		{0,232,0,79,80},    //索引:[17]
//		{0,33,-51,16,33}    //索引:[18]
//	};
//	
//	public static short nDrawPos81[][]={//{状态表->帧表->子图表}		
//		//南瓜2
//		{0,228,129,16,11},  //索引:[0]
//		{0,271,85,12,21},   //索引:[1]
//		{0,-1,78,75,71},    //索引:[2]
//		{0,226,85,12,22},   //索引:[3]
//		{0,155,89,54,22},   //索引:[4]
//		{0,251,138,19,10},  //索引:[5]
//		{0,229,112,45,13},  //索引:[6]
//		{0,253,85,12,21,4}, //索引:[7] 水平镜象
//		{0,-1,141,75,8},    //索引:[8]
//		{0,-1,78,75,62},    //索引:[9]
//		{0,240,85,12,22},   //索引:[10]
//		{0,79,77,74,78},    //索引:[11]
//		{0,250,124,19,10},  //索引:[12]
//		{0,156,134,54,22},  //索引:[13]
//		{0,0,0,74,78},      //索引:[14]
//		{0,75,-2,78,78},    //索引:[15]
//		{0,155,5,74,81},    //索引:[16]
//		{0,232,0,79,80},    //索引:[17]
//		{0,33,-51,16,33}    //索引:[18]
//	};
//	
//	public static short nDrawPos82[][]={//{状态表->帧表->子图表}		
//		//南瓜3
//		{0,0,160,14,24,4},  //索引:[0] 水平镜象
//		{0,73,159,78,6},    //索引:[1]
//		{0,73,104,78,38},   //索引:[2]
//		{0,73,142,78,18},   //索引:[3]
//		{0,0,160,14,24},    //索引:[4]
//		{0,0,135,58,11},    //索引:[5]
//		{0,0,146,58,14},    //索引:[6]
//		{0,0,104,49,31},    //索引:[7]
//		{0,14,160,13,24,4}, //索引:[8] 水平镜象
//		{0,252,144,78,38},  //索引:[9]
//		{0,73,142,78,17},   //索引:[10]
//		{0,14,160,13,24},   //索引:[11]
//		{0,0,135,58,10},    //索引:[12]
//		{0,73,166,78,19},   //索引:[13]
//		{0,27,160,14,24,4}, //索引:[14] 水平镜象
//		{0,252,144,78,37},  //索引:[15]
//		{0,27,160,14,24},   //索引:[16]
//		{0,151,104,78,71},  //索引:[17]
//		{0,229,98,101,46},  //索引:[18]
//		{0,0,0,83,104},     //索引:[19]
//		{0,83,0,83,104},    //索引:[20]
//		{0,165,0,82,104},   //索引:[21]
//		{0,246,0,83,98},    //索引:[22]
//		{0,39,-34,25,14}    //索引:[23]
//	};
//	
//	public static short nDrawPos83[][]={//{状态表->帧表->子图表}		
//		//警报器
//		{0,0,0,20,10},      //索引:[0]
//		{0,0,0,20,10,5},    //索引:[1] 垂直镜象
//		{0,0,54,44,40},     //索引:[2]
//		{0,0,54,44,40,4},   //索引:[3] 水平镜象
//		{0,0,94,54,39,4},   //索引:[4] 水平镜象
//		{0,0,94,54,39},     //索引:[5]
//		{0,0,10,40,44},     //索引:[6]
//		{0,0,10,40,44,5},   //索引:[7] 垂直镜象
//		{0,40,0,39,54},     //索引:[8]
//		{0,40,0,39,54,5},   //索引:[9] 垂直镜象
//		{3,112,161,42,26},  //索引:[10]
//		{3,0,124,60,67},    //索引:[11]
//		{3,0,176,60,15},    //索引:[12]
//		{3,0,125,60,51},    //索引:[13]
//		{3,0,72,80,52},     //索引:[14]
//		{2,0,0,80,80},      //索引:[15]
//		{1,0,0,32,43},      //索引:[16]
//		{2,80,0,80,80},     //索引:[17]
//		{2,0,80,80,80},     //索引:[18]
//		{2,80,80,80,80},    //索引:[19]
//		{3,80,96,19,22},    //索引:[20]
//		{3,112,162,44,25},  //索引:[21]
//		{3,60,129,50,63},   //索引:[22]
//		{3,134,142,21,14},  //索引:[23]
//		{3,61,173,50,17},   //索引:[24]
//		{3,60,129,50,43},   //索引:[25]
//		{3,99,72,57,65},    //索引:[26]
//		{3,0,-2,146,37},    //索引:[27]
//		{3,0,35,156,37},    //索引:[28]
//		{4,7,-6,73,80},     //索引:[29]
//		{3,111,138,22,21},  //索引:[30]
//		{3,112,161,42,26,4},//索引:[31] 水平镜象
//		{3,0,124,60,67,4},  //索引:[32] 水平镜象
//		{3,0,176,60,15,4},  //索引:[33] 水平镜象
//		{3,0,125,60,51,4},  //索引:[34] 水平镜象
//		{3,0,72,80,52,4},   //索引:[35] 水平镜象
//		{3,80,96,19,22,4},  //索引:[36] 水平镜象
//		{3,112,162,44,25,4},//索引:[37] 水平镜象
//		{3,60,129,50,63,4}, //索引:[38] 水平镜象
//		{3,134,142,21,14,4},//索引:[39] 水平镜象
//		{3,61,173,50,17,4}, //索引:[40] 水平镜象
//		{3,60,129,50,43,4}, //索引:[41] 水平镜象
//		{3,99,72,57,65,4},  //索引:[42] 水平镜象
//		{3,0,-2,146,37,4},  //索引:[43] 水平镜象
//		{3,0,35,156,37,4},  //索引:[44] 水平镜象
//		{4,7,-6,73,80,4},   //索引:[45] 水平镜象
//		{3,111,138,22,21,4} //索引:[46] 水平镜象
//	};
//	
//	public static short nDrawPos84[][]={//{状态表->帧表->子图表}		
//		//时空门
//		{0,0,0,45,80},      //索引:[0]
//		{0,45,0,45,80},     //索引:[1]
//		{0,90,0,45,80},     //索引:[2]
//		{0,135,0,45,80},    //索引:[3]
//		{0,180,0,45,80},    //索引:[4]
//		{0,225,0,45,80}     //索引:[5]
//	};
//		
//	public static short nDrawPos85[][]={//{状态表->帧表->子图表}		
//		//魔法水滴
//		{0,0,0,70,70},      //索引:[0]
//		{1,238,0,46,33},    //索引:[1]
//		{0,70,0,70,70},     //索引:[2]
//		{0,0,70,70,70},     //索引:[3]
//		{0,70,70,70,70},    //索引:[4]
//		{1,238,34,46,33},   //索引:[5]
//		{1,238,67,46,33},   //索引:[6]
//		{1,0,118,120,111},  //索引:[7]
//		{1,120,102,103,87}, //索引:[8]
//		{1,120,0,117,102},  //索引:[9]
//		{1,0,0,120,118}     //索引:[10]
//	};
//	
//	public static short nDrawPos86[][]={//{状态表->帧表->子图表}		
//		//爆破弹LV1的效果
//		{0,0,0,100,100},    //索引:[0]
//		{0,100,0,100,100},  //索引:[1]
//		{0,200,0,100,100},  //索引:[2]
//		{0,0,100,100,100},  //索引:[3]
//		{0,100,100,100,100},//索引:[4]
//		{0,200,100,100,100} //索引:[5]
//	};
//	
//	public static short nDrawPos87[][]={//{状态表->帧表->子图表}		
//		//爆破弹LV2的效果
//		{0,0,0,100,100},    //索引:[0]
//		{0,100,0,100,100},  //索引:[1]
//		{0,200,0,100,100},  //索引:[2]
//		{0,0,100,100,100},  //索引:[3]
//		{0,100,100,100,100},//索引:[4]
//		{0,200,100,100,100} //索引:[5]
//	};
//	
//	public static short nDrawPos88[][]={//{状态表->帧表->子图表}		
//		//爆破弹LV3的效果
//		{0,0,0,100,86},     //索引:[0]
//		{0,0,86,159,172},   //索引:[1]
//		{0,159,60,169,198}, //索引:[2]
//		{0,0,258,168,212},  //索引:[3]
//		{0,168,261,168,209} //索引:[4]
//	};
//	
//	public static short nDrawPos89[][]={//{状态表->帧表->子图表}		
//		//锤子
//		{0,0,0,82,81},      //索引:[0]
//		{0,81,0,82,81},     //索引:[1]
//		{1,0,0,87,87,4},    //索引:[2] 水平镜象
//		{1,0,0,87,87},      //索引:[3]
//		{0,176,19,88,17},   //索引:[4]
//		{0,162,36,69,43},   //索引:[5]
//		{1,87,0,87,87,4},   //索引:[6] 水平镜象
//		{1,87,0,87,87},     //索引:[7]
//		{0,229,36,69,43},   //索引:[8]
//		{0,189,6,106,15},   //索引:[9]
//		{1,174,0,87,87,4},  //索引:[10] 水平镜象
//		{1,174,0,87,87}     //索引:[11]
//	};
}
