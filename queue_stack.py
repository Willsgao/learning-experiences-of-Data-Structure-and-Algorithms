# -*- coding: utf-8 -*-
from __future__ import unicode_literals


#  使用两个栈实现队列效果
class MyQueue:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.stack1 = []
        self.stack2 = []

    def push(self, x):
        """
        Push element x to the back of queue.
        :type x: int
        :rtype: void
        """
        self.stack1.append(x)

    def pop(self):
        """
        Removes the element from in front of queue and returns that element.
        :rtype: int
        """
        while self.stack1:
            self.stack2.append(self.stack1.pop())
        res = self.stack2.pop()
        while self.stack2:
            self.stack1.append(self.stack2.pop())
        return res

    def peek(self):
        """
        Get the front element.
        :rtype: int
        """
        return self.stack1[0]

    def empty(self):
        """
        Returns whether the queue is empty.
        :rtype: bool
        """
        return not self.stack1
