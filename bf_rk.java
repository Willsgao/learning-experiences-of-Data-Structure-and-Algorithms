

// BM算法中的坏字符规则实现代码
// 变量b是模式串，m是模式串的长度，bc表示刚刚讲的散列表
private static final int SIZE = 256;  //全局变量或成员变量
private void generateBC(char[] b, int m, int[] bc) {
	for (int i = 0; i < SIZE; ++i) {
		bc[i] = -1; // 初始化bc
	}
	for (int i = 0; i < m; ++i) {
		int ascii = (int)b[i]; // 计算b[i]的ASCII值
		bc[ascii] = i;  //当存在多个相同的字符时，后面的字符会将前面的覆盖掉
	}
}

public int bm(char[] a, int n, char[] b, int m) {
	int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
	generateBC(b, m, bc);  //构建坏字符哈希表
	int i = 0;  // i表示珠串与模式串对齐的第一个字符
	while (i <= n-m) {
		int j;
		for (j = m-1; j >= 0; --j) {// 模式串从后往前匹配
			if (a[i+j] != b[j]) break;  //坏字符对应模式串中的下标是j
		}
		if (j < 0) {
			return i;  //匹配成功，返回朱传宇模式串第一个匹配的字符的位置
		}
		// 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
		i = i + (j - bc[(int)a[i+j]]);
	}
	return -1;
}


// 好后缀规则，suffix,prefix实现原则
// b表示模式串，m表示长度，suffix,prefix数组事先申请好了
private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
	for (int i = 0; i < m; ++i) {//初始化
		suffix[i] = -1;
		prefix[i] = false;
	}
	for (int i = 0; i < m-1; ++i) {//b[0,i]
		int j = i;
		int k = 0; //公共后缀字串长度
		while (j >= 0 && b[j] == b[m-1-k]) {// 与b[0,m-1]求公共后缀字串
			--j;
			++k;
			suffix[k] = j+1;  //j+1表示公共后缀淄川在b[0,i]中的起始下标
		}
		if (j == -1) prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
	}
}



// BM算法完整版代码实现
// a,b表示主串和模式串，n,m表示主串和模式串长度
public int bm(char[] a, int n, char[] b, int m) {
	int[] bc = new int[SIZE];  //记录模式串每个字符最后出现的位置
	generateBC(b, m, bc);  //构建坏字符哈希表
	int[] suffix = new int[m]; 
	boolean[] prefix = new boolean[m];
	generateGS(b, m, suffix, prefix);
	int i = 0; // j表示主串与模式串匹配的第一个字符
	while (i <= n-m) {
		int j;
		for (j = m-1; j >= 0; --j) {//模式串从后往前匹配
			if (a[i+j] != b[j]) break;   //坏字符对应模式串中的下标为j
		}
		if (j < 0) {
			return i;  //匹配成功，返回主串与模式串第一个匹配的字符的位置
		}
		int x = j - bc[(int)a[i+j]];
		int y = 0;
		if (j < m-1) { //如果有好后缀的话
			y = moveByGS(j, m, suffix, prefix);
		}
		i = i + Math.max(x,y);
	}
	return -1;
}

// j 表示坏字符对应的模式串中的字符下标；m表示模式串长度
private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
	int k = m - 1 - j; // 好后缀长度
	if (suffix[k] != -1) return j - suffix[k] +1;
	for (int r = j+2; r <= m-1; ++r) {
		if (prefix[m-r] == true) {
			return r;
		}
	}
	return m;
}


// KMP算法
// KMP算法框架代码
public static int kmp(char[] a, int n, char[] b, int m) {
	int[] next = getNexts(b, m);
	int j = 0;
	for (int i = 0; i < n; ++i) {
		while (j > 0 && a[i] != b[j]) { //一直找到a[i]和b[j]
			j = next[j-1] + 1;
		}
		if (a[i] == b[j]) {
			++j;
		}
		if (j == m) { //找到匹配模式串
			return i - m + 1；
		}
	}
	return -1;
}


// b表示模式串，m表示模式串的长度
private static int[] getNexts(char[] b, int m) {
	int[] next = new int[m];
	next[0] = -1;
	int k = -1;
	for (int i = 1; i < m; ++i) {
		while (k != -1 && b[k+1] != b[i]) {
			k = next[k];
		}
		if (b[k+1] == b[i]) {
			++k;
		}
		next[i] = k;
	}
	return next;
}