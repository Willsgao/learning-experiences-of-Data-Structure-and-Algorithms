
// 计算两个字符串的莱文斯坦距离
// 回溯算法实现
private int[] a = 'mitcmu'.toCharArray();
private int[] b = 'mtacmu'.toCharArray();
private int n = 6;
private int m = 6;
private int minDist = Integer.MAX_VALUE; //记录最短距离
public int lwstBT(int i, int j, int edist) {
	if (i==n || j==m) {
		if (i<n) edist += (n-i);
		if (j<m) edist += (m-j);
		if (edist<minDist) minDist=edist;
		return;
	}

	if (a[i] == a[j]) {
		lwstBT(i+1, j+1, edist);
	} else {
		lwstBT(i+1, j, edist+1);
		lwstBT(i, j+1, edist+1);
		lwstBT(i+1, j+1, edist+1);
	}
}

// 动态规划代码实现
public int lwstDP(char[] a, char[] b, int m) {
	int[][] minDist = new int[n][m];
	for (int j=0; j<m; ++j) {//初始化第0行:a[0..0]与b[0..j]编辑距离
		if (a[0] == b[j]) minDist[0][j] = j;
		else if (j != 0) minDist[0][j] = minDist[0][j-1]+1;
		else minDist[0][j] = 1;
	}
	for (int i=0; i<n; ++i) {//初始化第0列：a[0..i]与b[0..0]编辑距离
		if (a[i] == b[0]) minDist[i][0]=i;
		else if (i != 0) minDist[i][0] = minDist[i-1][0] + 1;
		else minDist[i][0] = 1;
	}

	for (int i=1; i<n; ++i) {//按行填表
		for (int j=1; j<m; ++j) {
			if (a[i] == a[j]) minDist[i][j] = min(
				minDist[i-1][j-1],minDist[i-1][j]+1,minDist[i][j-1]+1);
			else minDist[i][j] = min(
				minDist[i-1][j-1]+1,minDist[i-1][j]+1,minDist[i][j-1]+1);
		}
	}
	return minDist[n-1][m-1];
}

private int min(int x, int y, int z) {
	int minv = Integer.MAX_VALUE;
	if (x < minv) minv = x;
	if (y < minv) minv = y;
	if (z < minv) minv =z;
	return minv;
}


// 动态规划最短路径
private minDist = Integer.MAX_VALUE;
private int[][] states = new int[n][n];

public int lwstDP(int i, int j, int[][] matrix) {
	int sum = 0;
	for (j=0; j<n; ++j) {
		sum += matrix[0][j];
		states[0][j] = sum;
	}
	int sum = 0;
	for (i=0; i<n; ++i) {
		sum += matrix[i][0];
		states[i][0] = sum;
	}

	for (i=1; i<n; ++i) {
		for (j=1; j<n; ++j) {
			states[i][j] = matrix[i][j] +
			 Math.min(states[i-1][j], states[i][j-1]);
		}
	}
	return states[n-1][n-1];
}

// 棋盘最短路径
// 状态转移方程
opt[i, j] = opt[i-1, j] + opt[i, j-1];


// 爬楼梯
public int climbStairs(int n) {
	if (n==0 || n==1 || n==2) return n;
	int[] mem = new int[n];
	mem[0] = 0;
	mem[1] = 1;
	for (i=2; i<n; ++i) {
		mem[i] = mem[i-1] + mem[i-2];
	}
	return mem[n-1];
}

// 方法二
public int climbStairs2(int n) {
	if (n <= 2) return n;

	int one_step_before = 2;
	int two_steps_before = 1;
	int all_ways = 0;

	for (int i=2; i<n; ++i) {
		all_ways = one_step_before + two_steps_before;
		two_steps_before = one_step_before;
		one_step_before = all_ways;
	}
	return all_ways;
}

// python代码
def climbStairs(self, n):
	x,y = 1,1
	for _ in range(1,n):
		x, y = y, x+y
	return y

// 三角形最小路径之和
private int[][] states = new int[n][n];
public int lwstDP(int n, int[][] matrix) {
	// n-1行状态初始化
	for (j=0; j<n; ++j) {
		states[n-1][j] = matrix[n-1][j];
	}

	// 动态转化
	for (i=n-2; i>=0; --i) {
		for (j=0; j<i+1; ++j) {
			states[i][j] = matrix[i][j] + Math.min(
				states[i+1][j], states[i+1][j+1]);
		}
	}
	return states[0][0];
}