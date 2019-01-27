

// 分析一个排序算法需要从下面几点衡量
// 1、排序算法的执行效率
// （1）分析最好、最坏、平均情况时间复杂度
// （2）考虑时间复杂度的系数、常数和低阶因子
// （3）考量数据的比较次数和移动次数
// 2、排序算法的内存消耗
// 原地排序算法是指空间复杂度为O(1)的排序算法
// 3、排序算法的稳定性
// 是指待排序序列中存在值相同的元素，排序之后，相同元素的前后顺序不变。


// 冒泡排序,a表示数组,n表示数组大小
public void bubbleSort(int[] a, int n) {
	if (n <= 1) return;

	for (int i = 0; i < n; ++i) {
		// 提前退出冒泡循环的标志位
		boolean flag = false;
		for (int j = 0; j < n - i -1; ++j) {
			if (a[j] > a[j+1]) {
				// 交换元素
				int tmp = a[j];
				a[j] = a[j+1];
				a[j+1] = tmp;
				flag = true; //表示产生数据交换
			}
		}
		if (!flag) break;
 	}
}

// 冒泡排序中数据的交换操作
if (a[j] > a[j+1]) { //交换
	int tmp = a[j];
	a[j] = a[j+1];
	a[j+1] = tmp;
	flag = true;
}

// 冒泡排序时间复杂度为O(n^2);属于原地排序，空间复杂度为O(1)；
// 属于稳定排序。


// 插入排序，a表示数组,n表示数组大小
public void insertionSort(int[] a, int n) {
	if (n <= 1) return;
	for (int i = 1; i < n; ++i) {
		int value = a[i];
		int j = i - 1;
		// 查找插入的位置
		for (; j >= 0; --j) {
			if (a[j] > value) {
				a[j+1] = a[j];  // 数据移动
			} else {
				break;
			}
		}
		a[j+1] = value; //插入数据
	}
}
// 插入排序时间复杂度为O(n^2);属于原地排序，空间复杂度为O(1);
// 属于稳定排序

// 插入排序中数据的移动操作
if (a[j] > value) {
	a[j+1] = a[j];
} else {
	break;
}

// public void insertionSort(int[] a, int n) {
// 	if (n <= 1) return;

// 	for (i = 1; i < n; ++i) {
// 		int value = a[i];
// 		for (int j = i-1; j >=0; --j) {
// 			if (a[j] > value) {
// 				a[j+1] = a[j];
// 			} else {
// 				break;
// 			}
// 		}
// 		a[j+1] = value;
// 	}
// }


// 快速排序，A是数组，n表示数组的大小
quick_sort(A, n) {
	quick_sort_c(A, 0 , n-1) 
}

// 快速排序递归函数，p,r为下标
quick_sort_c(A, p, r) {
	if p >= r then return

	q = partition(A, p, r)  //获取分区点
	quick_sort_c(A, p, q-1)
	quick_sort_c(A, q+1, r)
}
// 快排中原地分区函数的伪代码实现
partition(A, p, r) {
	pivot := A[r]
	i := p 
	for j := p to r-1 do {
		if A[j] < pivot {
			swap A[i] with A[j]
			i := i+1
		}
	}
	swap A[i] with A[r]
	return i
}

// 归并排序处理过程是由下到上的，先处理子问题，然后合并。
// 快排正好相反，西安分区，然后处理子问题。
// 归并排序虽然是稳定的，时间复杂度为O(nlogn)，但是它是非原地排序算法
// 主要是因为合并函数无法在原地执行。
// 快速排序通过设计巧妙的原地分区函数可以实现原地排序，
// 解决了归并排序占用太多内存的问题。


// 思考题：
// 现在你有 10 个接口访问日志文件，每个日志文件大小约 300MB，
// 每个文件里的日志都是按照时间戳从小到大排序的。
// 将这 10 个较小的日志文件，合并为 1 个日志文件，
// 合并之后的日志仍然按照时间戳从小到大排列。
// 如果处理上述排序任务的机器内存只有 1GB

// 解决思路：
// 每次从10个文件中各读取一条时间戳最小的文件，10个文件构成一个小顶堆。
// 将小顶堆中时间戳最小的文件存入新文件，并从其来源文件中新读取一条文件放入小顶堆
// 循环执行，直到所有文件都存入新文件中。


// 桶排序的适用环境
// 首先，要求排序的数据很容易划分为m个桶；并且，桶与桶之间存在着天然的大小顺序。
// 其次，数据在各个桶之间的分布是比较均匀的。
// 桶排序比较适合用在外部排序中。
// 

// 计数排序是桶排序的一种特殊情况
// 计数排序只能用在数据范围不大的场景中，如果数据范围k比要排序的数据n大很多，
// 那就不适合用计数排序了。而且计数排序只能给非负整数进行排序。
// 代码实现
// 计数排序，a是数组，n是数组的大小。假设数组中存储的都是非负整数。
public void countingSort(int[] a, int n) {
	if (n < = 1) return;
	// 查找数组数据的范围
	int max = a[0];
	for (int i = 1; i < n; ++i) {
		if (max < a[i]) {
			max = a[i];
		}
	}
	int[] c = new int[max +1]; //申请一个计数数组c,下标大小[0, max]
	for (int i = 0; i < max; ++i) {
		c[i] = 0;
	}

	// 计算每个元素的个数，放入c中
	for (int i = 0; i < n; ++i) {
		c[a[i]]++;
	}
	// 依次累加
	for (int i = 1; i <= max; ++i){
		c[i] = c[i-1] + c[i];
	}
	// 临时数组r，存储排序之后的结果
	int[] r =new int[n];
	// 计算排序的关键步骤
	for (int i = n-1; i >=0; --i) {
		int index = c[a[i]] - 1;
		r[index] = a[i];
		c[a[i]]--;
	}
	// 将结果拷贝给a数组
	for (int i = 0; i < n; ++i) {
		a[i] = r[i];
	}
}

