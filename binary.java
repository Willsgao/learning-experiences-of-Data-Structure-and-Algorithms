

// 二分查找代码
public int bsearch(int[] a, int n, int value) {
	int low = 0; 
	int hign = n - 1;

	while (low < = hign) {
		int mid = (hign + low) / 2;
		if (a[mid] == value) {
			return mid;
		} else if (a[mid] < value) {
			low = mid + 1;
		} else {
			hign = mid -1;
		}
	}
	return -1;
}

// 注意：1、循环退出条件：low <= hign;
// 2、mid取值：mid = (hign - low) /2 + low; 或 low + ((hign -low)>>1)
// 3、low 与hign更新：low = mid + 1; hign = mid - 1;

// 二分查找递归实现
public int bsearch(int[] a, int n, int val) {
	return bsearchMid(a, 0, n-1, val);
}

private int bsearchMid(int[] a , int low, int hign, int value) {
	if (low > hign) return -1;

	mid = low + ((hign - low)>>1);
	if (a[mid] == value) return mid;
	if (a[mid] < value) {
		bsearchMid(a, mid+1, hign, value);
	} else {
		bsearchMid(a, low, mid-1, value);
	}
}

// 二分查找使用条件
// 首先，二分查找依赖顺序表结构，就是依赖数组
// 其次，二分查找针对有序数据
// 再次，数据量太小不适合二分查找
// 最后，数据量太大也不适合二分查找