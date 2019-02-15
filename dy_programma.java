
// 回溯算法实现0-1背包问题
private int maxW = Ieteger.MIN_VALUE; //初始化最大值
private int[] weight = {2,2,4,6,3};  //物品重量
private int n = 5;  //物品个数
private int w = 9;  //背包承受的最大重量
public void f(int i, int cw) {//调用f(0,0)
	if (cw == w || i == n) {// 背包已装满或者所有物品已装完
		if (cw > maxW) maxW = cw;
		return;
	}
	f(i+1, cw);  //选择不装第i个物品
	if (cw + weight[i] <= w) {
		f(i+1, cw+weight[i]); //选择第i个物品
	}
}

// 借助递归备忘录，避免重复算法
private int maxW = Ieteger.MIN_VALUE;  //结果放到maxW中
private int[] weight = {2,2,4,6,3};  //物品重量
private int n = 5;  //物品个数
private int w = 9;  //背包承受的最大重量
private boolean[][] mem = new boolean[5][10];  //备忘录，默认值为false
public void f(int i, int cw) {//调用f(0,0);
	if (cw == w || i == n) {//cw ==w 表示达到背包承重，i==n物品考察完
		if (cw >maxW) maxW = cw;
		return;
	}
	if (mem[i][cw]) return;  //重复状态
	mem[i][cw] = true;  //记录(i,cw)状态
	f(i+1, cw);
	if (cw + weight[i] <= w) {
		f(i+1, cw+weight[i]);  //选择加入第i个物品
	}
}

// 动态规划截距0-1问题
// weight:物品重量；n:物品个数；w:背包承重
public int knapsack(int[] weight, int n, int w) {
	boolean[][] states = new boolean[n][w+1];  //默认值false
	states[0][0] = true;  //第一行的数据要特殊处理，利用哨兵优化
	states[0][weight[0]] = true;
	for (int i = 1; i<n; ++i) {//动态规划状态转移
		for (int j = 0; j<=w; ++j) {//不把第i个物品装入背包
			if (states[i-1][j] == true) states[i][j] = states[i-1][j];
		}
		for (int j=0; j<=w-weight[i]; ++j) {//把第i个物品放入背包
			if (states[i-1][i]==true) states[i][j+weight[i]] = true;
		}
	}
	for (int i=w; i>=0; --i) {//输出结果
		if (states[n-1][i]==true) return i;
	}
	return 0;
}

//一维数组解决动态规划所需的额外空间开支
public static int knapsack2(int[] items, int n, int w) {
	boolean[] states = new boolean[w+1]; //默认值false
	states[0] = true; //第一行特殊处理，利用哨兵优化
	states[items[0]] = true;
	for (int i = 1; i<n; ++i) {//动态规划
		for (int j=w-items[i]; j>=0; --j) {//把第i个物品放入背包
			if (states[j]==true) states[j+items[i]] = true;
		}
	}
	for (int i=w; i>=0; --i) { //输出结果
		if (ststes[i] == true) return i;
	}
	return 0;
}


// 0-1问题升级，回溯算法
private maxV = Integer.MIN_VALUE; //最大值初始化
private int[] items = {2,2,4,6,3}; //物品的重量
private int[] value = {3,4,8,9,6}; //物品的价值
private int n = 5; //物品的个数
private int w = 9; //背包的承重
public void f(int i, int cw ,int cv) {//调用函数f(0,0,0)
	if (cw == w || i == n) {// cw==w表示装满了，i==n表示物品考察完
		if (cv>maxV) maxV = cv;
		return;
	}
	f(i+1, cw, cv); //选择不装第i个物品
	if (cw+items[i]<=w) {
		f(i+1, cw+items[i], cv+value[i]); //选择装入第i个物品
	}
}


// 0-1问题升级版，动态规划
public static int knapsack3(int[] weight, int[] value, int n, int w) {
	int[][] states = new int[n][w+1];
	for (int i=0; i<n; ++i) {//初始化states
		for (int j=0; j<w+1; ==j) {
			states[i][j] = -1;
		}
	}
	states[0][0] = 0;
	states[0][weight[0]] = value[0];
	for (int i=1; i<n; ++i) {//动态规划
		for (int j=0; j<w+1; ++j) {
			if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
		}
		for (int j=0; j<=w-weight[i]; ++j) {//选择第i个物品
			if (states[i-1][j] >= 0) {
				int v = states[i-1][j] + value[i];
				if (v > states[i][j+weight[i]]) {
					states[i][j+weight[i]] = v;
				}
			}
		}
	}
	// 找出最大值
	int maxvalue = -1;
	for (int j = 0; j<=w; ++j) {
		if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
	}
	return maxvalue;
}

// items商品价格， n商品个数，w商品满减条件
public static void double11advance(int[] items, int n, int w) {
	boolean[][] states = new boolean[n][3*w+1]; //设置购买上限
	states[0][0] = true; //第一行数据特殊处理
	states[0][items[0]] = true;
	for (int i=1; i<n; ++i) {//动态规划
		for (int j=0; j<=3*w; ++j) {//不购买第i个物品
			if (states[i-1][j] == true) states[i][j] = states[i-1][j];
		}
		for (int j=0; j<=3*w+1; ++j) {//购买第i个商品
			if (states[i-1][j]==true) states[i][j+items[i]] = true;
		}
	}

	int j;
	for (j=w; j<3*w+1; ++j) {
		if (states[n-1][j] == true) break;  //输出结果大于w的最小值
	}
	if (j==-1) return; //无解
	for (int i=n-1; i>=1; --i) {//i表示二维数组中的行，j表示列
		if (j-items[i]>=0 && states[i-1][j-items[i]]==true) {
			System.out.print(items[i] + " "); //购买此物品
			j = j -items[i];
		}  //没有购买此物品时，j不变
	}
	if (j != 0) System.out.print(items[0]);
}


// 状态转移表法解决棋盘最小值问题
public int minDist(int[][] matrix, int n) {
	int[][] states = new int[n][n];
	int sum = 0;
	for (int j=0; j<n; ++j) {
		sum += matrix[0][j];
		states[0][j] = sum;
	}
	int sum =0;
	for (int i=0; i<n; ++i) {
		sum += matrix[i][0];
		states[i][0] = sum;
	}
	for (int i=0; i<n; ++i) {
		for (int j=0; j<n; ++j) {
			states[i][j] = 
				matrix[i][j] + Math.min(states[i-1][j], states[i][j-1]);
		}
	}
	return states[n-1][n-1];
}


// 动态转移方程
private int[][] matrix = 
	{{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};
private int n = 4;
private int[][] mem = int[4][4];
public int minDist(int i, int j) {
	if (i==0 && j == 0) return matrix[0][0];
	if (mem[i][j] > 0) return mem[i][j];
	int minLeft = Integer.MAX_VALUE;
	if (j-1 >=0) {
		minLeft = minDist(i,j-1);
	}
	int minUp = Integer.MAX_VALUE;
	if (i-1>=0) {
		minUp = minDist(i-1, j);
	}

	int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
	mem[i][j] = currMinDist;
	return currMinDist;
}

