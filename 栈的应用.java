

// 顺序栈的实现
public class ArrayStack {
	private String[] items; //声明数组
	private int count; //栈中元素个数
	private int n; //栈的大小

	// 初始化数组，生情一个大小为n的数组空间
	public ArrayStack(int n) {
		this.items = new String[n];
		this.n = n;
		this.count = 0;
	}

	// 入栈操作
	public boolean push(String item) {
		// 数组空间不够，直接返回false,入栈失败
		if (count == n) return false;
		// 将item放到下标为count的位置，并且count加一
		items[count] = item;
		++count;
		return true;
	}

	// 出栈操作
	public String pop() {
		// 栈为空，则直接返回null;
		if (count == 0) return null;
		// 返回下标为count-1的数组元素，栈中元素个数减一
		String tmp = items[cpunt-1];
		--count;
		return tmp;
	}
}


// 调用函数为什么要采用栈来实现呢？
// 从调用函数进入被调用函数，对于数据来说变化的是作用域。
// 从根本上讲，要保证每进入一个新的函数，都是一个新的作用域。
// 要实现这个，用栈是非常方便的。在进入被调用函数的时候，分配一段空间给函数变量，
// 函数结束的时候，将栈顶复位，然后回到调用函数的作用域内。
// 函数调用栈的C语言实现示例
// 主栈
int main() {
	int a = 1;
	int ret = 0;
	int res = 0;
	ret = add(3, 5);
	res = a + ret;
	printf('%d', res);
	return 0;
}
// add栈
int add(int x, int y) {
	int sum = 0;
	sum = x + y;
	return sum;
}


// 栈在表达式求值中的应用
// 编译器通过两个栈实现表达式求值的操作。
// 一个栈保存操作数的栈，一个保存运算符的栈。从左向右遍历表达式，
// 当遇到数字时，将数字压入栈；当遇到运算符时，与运算符栈中的元素进行比较。
// 如果运算符优先级比栈顶元素优先级高，就将运算符压入栈；
// 反之，则将操作数栈中取出栈顶两个元素进行计算，并将结果压入操作数栈，继续比较。


// 栈在括号匹配中的应用
