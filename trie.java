
// a-z字符，Trie树实现
public class Trie {
	private TrieNode root = new TrieNode('/');  //存储无意义字符

	// 往Trie树种插入一个字符串
	public void insert(char[] text) {
		TrieNode p = root;
		for (int i = 0; i < text.length; ++i) {
			int index = text[i] - 'a';
			if (p.children[index] == null) {
				TrieNode newNode = new TrieNode(text[i]);
				p.children[index] = newNode;
			}
			p = p.children[index];
		}
		p.isEndingChar = true;
	}

	// 在Trie树种查找一个字符串
	public boolean find(char[] pattern) {
		TrieNode p = root;
		for (int i = 0; i < pattern.length; ++i) {
			int index = pattern[i] - 'a';
			if (p.children[index] == null) {
				return false; // 不存在pattern
			}
			p = p.children[index];
		}
		if (p.isEndingChar == false) return false; //不能完全匹配，只是前缀
		else return true;  //找到pattern
	}

	public class TrieNode {
		public char data;
		public TrieNode[] children = new TrieNode[26];
		public boolean isEndingChar = false;
		public TrieNode(char data) {
			this.data = data;
		}
	}
}


// 多模式串匹配、AC自动机敏感词过滤功能
// AC自动机构建步骤
// 将多模式串构建成Trie树
// 在Trie树上构建失败指针(相当于KMP中的失效函数next指针)


// 构建AC自动机的失败指针
public void buildFailurePointer() {
	Queue<AcNode> queue = new LinkedList<>();
	root.fail = null;
	queue.add(root);
	while (!queue.isEmpty()) {
		AcNode p = queue.remove();
		for (int i = 0; i < 26; ++i) {
			AcNode pc = p.children[i];
			if (pc == null) continue;
			if (p == root) {
				pc.fail == root;
			} else {
				AcNode q = p.fail;
				while (q != null) {
					AcNode qc = q.children[pc.data - 'a'];
					if (qc != null) {
						pc.fail = qc;
						break;
					}
					q = q.fail;
				}
				if (q == null) {
					pc.fail = root;
				}
			}
			queue.add(pc);
		}
	}
}

// 敏感字符串替换
public void match(char[] text) { // text是主串
	int n = text.length;
	AcNode p = root;
	for (int i = 0; i < n; ++i) {
		int idx = text[i] - 'a';
		while (p.children[idx] == null && p != root) {
			p = p.fail;  // 失败指针发挥作用的地方
		}
		p = p.children[idx];
		if (p == null) p =root;  //如果没有匹配的，从root重新匹配
		AcNode tmp = p;
		while (tmp != root) {  //打印出可以匹配的模式串
			if (tmp.isEndingChar == true) {
				int pos = i - tmp.length+1;
				System.out.println("匹配起始下标"+pos+";长度"+tmp.length);
			}
			tmp = tmp.fail;
		}
	}
}