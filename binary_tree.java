
// 节点的高度：节点到叶子节点的最长路径。
// 结点的深度：根节点到节点的最长路径。
// 节点的层数：节点的深度加1.
// 树的高度：根节点高度


// 如果某棵二叉树是一棵完全二叉树，那用数组存储无疑是最节省内存的一种方式。
// 因为数组的存储方式并不需要像链式存储法那样，要存储额外的左右子节点的指针。
// 堆其实是一种完全二叉树，最常用的存储方式就是数组。

// 二叉树遍历
// 前序遍历：根节点->左子树->右子树
preOrder(root) = root -> preOrder(root->left) -> preOrder(root->right)
// 中序遍历：左子树->根节点->右子树
preOrder(root) = preOrder(root->left) -> root -> preOrder(root->right)
// 后序遍历：左子树->右子树->根节点
preOrder(root) = preOrder(root->left) -> preOrder(root->right) -> root


// 递推公式实现三种遍历
// 前序遍历
public void preOrder(Node root) {
	if (root == null) return;
	print(root) //伪代码，表示打印根节点root
	preOrder(root->left);
	preorder(root->right);
}

// 中序遍历
public void preOrder(Node root) {
	if (root == null) return;
	preOrder(root->left);
	print(root) //伪代码，表示打印根节点root
	preOrder(root->right);
}

// 后序遍历
public void preOrder(Node root) {
	if (root == null) return;
	preOrder(root->left);
	preOrder(root->right);
	print(root) //伪代码，表示打印根节点root	
}


// 二叉查找树的特点
// 二叉查找树要求对树中的任意一个节点，其左子树中每个节点的值都要小于该节点的值，
// 右子树中每个节点的值都大于该节点的值。

// 二叉查找树的查找操作
public class BinarySearchTree {
	private Node Tree;

	public Node find(int data) {
		Node p = tree;
		while (p != null) {
			if (data < p.data) p = p.left;
			else if (data > p.data) p = p.right;
			else return p;
		}
		return null;
	}

	public static class Node {
		private int data;
		private Node left;
		private Node right;

		public Node (int data) {
			this.data = data;
		}
	}
}

// 二叉查找树插入操作代码
public void insert(int data) {
	if (tree == null) {
		tree = new Node(data);
		return;
	}

	Node p = tree;
	while (p != null) {
		if (data > p.data) {
			if (p.right == null) {
				p.right = new Node(data);
				return;
			}	
			p = p.right;		
		} else { //data < p.data
			if (p.left == null) {
				p.left = new Node(data);
				return;
			}
			p = p.left;
		}
	}
}

// 二叉查找树删除节点
public void delete(int data) {
	Node p = tree; // p指向要删除的节点，初始化指向根节点
	Node pp = null; // pp记录的是p的父节点
	while (p != null && p.data != data) {
		pp = p;
		if (data > p.data) p = p.right;
		else p = p.left;
	}
	if (p == null) return; //没有找到目标节点

	// 要删除的节点具有两个子节点
	if (p.left != null && p.right != null) { //查找右子树中最小的节点
		Node minP = p.right;
		Node minPP = p;
		while (minP.left != null) {
			minP = minP.left;
			minPP = minP;
		}
		p.data = minP.data;
		p = minP;
		pp = minPP;
	}

	// 删除结点是叶子节点或者仅有一个子节点
	Node child; //p的子节点
	if (p.left != null) child = p.left;
	else if (p.right != null) child = p.right;
	else child = null;

	if (pp == null) tree = child; // 删除的节点时根节点；
	else if (pp.left == p) pp.left = child;
	else pp.right = child;
}