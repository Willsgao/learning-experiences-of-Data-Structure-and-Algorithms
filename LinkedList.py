# -*- coding: utf-8 -*-
from __future__ import unicode_literals
'''
本文用python代码实现了链表中常见的五个问题
1、单链表反转问题
2、链表中是否存在环的问题
3、两个有序链表的合并问题
4、删除单链表中倒数第k个结点
5、查找单链表中间节点
'''


# 单链表反转问题
class ListNode:

    def __init__(self, x):
        self.val = x
        self.next = None


# 1、利用三个指针反转
class Solution(object):

    def reverse1(self, head):
        if not head:
            return head
        p, q = head, head.next
        p.next = None
        while q:
            q.next, p, q = p, q, q.next
        return p

    def reverse2(self, head):
        prev, cur = None, head
        while cur:
            cur.next, prev, cur = prev, cur, cur.next
        return prev


# 2、利用尾插法反转
class Solution(object):

    def reverse3(self, head):
        if not head or not head.next:
            return head
        p = head.next
        while p.next:
            q = p.next
            p.next, q.next, head.next = q.next, head.next, q
        p.next, head, p.next.next = head, head.next, None
        # head = head.next
        # p.next.next = None
        return head


# 3、递归方式反转
class Solution(object):

    def reverse4(self, head):
        if not head or not head.next:
            return head
        new_head = self.reverse4(head.next)
        new_head.next = head
        head.next = None
        return new_head


# 链表中环的检测
# 暴力求解，
class Solution(object):

    def findCircle01(self, head):
        if not head or not head.next:
            return False
        p = head.next
        while p:
            p = p.next
        return False


# 使用集合求解
class Solution(object):

    def findCircle02(self, head):
        if not head or not head.next:
            return False
        st = {}
        p = head.next
        while P:
            if p in st:
                return True
            st[p] = p.val
            p = p.next
        return False


# 使用快慢指针
class Solution(object):

    def findCircle02(self, head):
        if not head or not head.next:
            return False
        fast = slow = head
        while fast.next and fast.next.next:
            fast = fast.next.next
            slow = slow.next
            if fast == slow:
                return True
        return False


# 两个有序链表的合并
# 循环比较结点值
class ListNode(obeject):

    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):

    def mergeList(self, l1, l2):
        head = ListNode(0)
        first = head
        while l1 and l2:
        	if l1.val <= l2.val:
	        	head.next = l1
	        	l1 = l1.next
	        elif l1.val > l2.val:
	        	head.next = l2
	        	l2 = l2.next
	        head = head.next
	    if l1:
        	head.next = l1
        elif l2:
        	head.next = l2
        return first.next


# 循环比较结点值
class ListNode(obeject):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def mergeList(self, l1, l2):
    	head = ListNode(0)
    	first = head
    	while l1 and l2:
    		if l1.val <= l2.val:
    			head.next = self.mergeList(self, l1.next, l2)
    		else:
    			head.next = self.mergeList(self, l1, l2.next)
    	if not l1:
    		return l2 
    	if not l2:
    		return l1
    	return first.next



# 删除链表中倒数第k个结点
class Solution(object):
	def deleteknode(self, head, k):
		prev = ListNode(0)
		prev.next = head
		fast = slow = prev
		for _ in range(k+1):
			fast = fast.next
		while fast.next:
			fast, slow = fast.next, slow.next
		slow.next = slow.next.next
		return head


# 求链表的中间节点
# 快慢双指针
class Solution(object):
	def midNode(self, head):
		if not head or not head.next:
			return head 
		prev = ListNode(0)
		prev.next = head 
		fast = slow = prev 
		while fast and fast.next:
			fast, slow = fast.next.next, slow.next 
		if fast:
			slow = slow.next 
		return slow
