
// 链表不需要连续的内存空间，通过指针来访问下一个节点
// 链表分为单链表、双向链表和循环链表
// 针对链表的插入和删除操作，我们只需要操作相邻节点的指针改变，
// 时间复杂度为O(1).

// 循环链表是一种特殊的单链表，尾节点指向头结点
// 当处理的数据存在环形结构时，适合采用循环链表。比如约瑟夫问题。

// 双向链表有两个方向，节点同时具有prev,next指针.
// java中的LinkedHashMap容器使用了双向链表


// 删除操作：
// 当删除节点“值等于某个给定值”的节点时，需要先进行查找操作，时间复杂度为O(n),
// 因此删除操作总的时间复杂度为O(n)

// 删除给定指针的节点：单链表删除中需要查询该节点的前驱节点，因此时间复杂度为O(n);
// 双向链表不需要从头查询，查找上一个节点的时间复杂度为O(1)

// 缓存原理本质上是利用了空间换时间的思想

// 利用链表实现LRU缓存淘汰算法，基本思想如下：
// 维护一个有序单链表，越早访问的数据越排在链表的尾部。
// 新访问的数据不断地被添加到链表的头部。
// 1、如果新的访问数据存在于链表之中，通过遍历链表查找到目标节点，
// 将目标节点删除，并将目标节点值添加到链表头部
// 2、如果目标节点不存在于链表之中。
// （1）当内存空间充足时，直接将目标节点添加到链表头部；
// （2）当内存空间不足时，将尾节点删除，然后将目标节点添加到链表头部


// 使用单链表检测字符串是否为回文字符串
// 算法思想：使用快慢指针找到字符串链表中点，
// 慢指针前进过程中将节点链表反转，比较前后两半节点值是否相同。
class Solution {
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}

		ListNode prev = null;
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) { //快指针走两步，慢指针走一步找中点
			// 将慢指针前半部分指针反转
			fast = fast.next.next;
			ListNode next = slow.next;
			slow.next = prev;
			prev = slow;
			slow = next;
		}
		// 根据快指针节点是否为空，调整中点位置
		if (fast != null) {
			slow = slow.next;
		}
		// 对比慢指针后半部分节点与前半部分反转后的节点是否相同
		while (slow != null) {
			if (slow.val != prev.val) {
				return false;
			}
			slow = slow.next;
			prev = prev.next;
		}
		return true;
	}
}