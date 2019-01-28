# -*- coding: utf-8 -*-
from __future__ import unicode_literals


# 查找第一个值等于给定值的元素
class Solution1(object):

    def firstValue(self, a, n, val):
        low = 0
        hign = n - 1
        if low > hign:
            return None
        while low <= hign:
            mid = low + ((hign - low) >> 1)
            if a[mid] == val and a[mid - 1] != val:
                return mid
            elif a[mid] == val and a[mid - 1] == val:
                hign = mid - 1
            elif a[mid] < val:
                low = mid + 1
            elif a[mid] > val:
                hign = mid - 1
        return


# 查找最后一个值等于给定值的元素
class Solution2(object):

    def lastValue(self, a, n, val):
        low = 0
        hign = n - 1
        while low <= hign:
            mid = low + ((hign - low) >> 1)
            if a[mid] < val:
                low = mid + 1
            elif a[mid] > val:
                hign = mid - 1
            else:
                if mid == n - 1 or a[mid + 1] != val:
                    return mid
                else:
                    low = mid + 1
        return


# 查找第一个大于等于给定值的元素
class Solution3(object):

    def firstValue(self, a, n, val):
        low = 0
        hign = n - 1
        while low <= hign:
            mid = low + ((hign - low) >> 1)
            if a[mid] < val:
                low = mid + 1
            else:
                if mid == 0 or a[mid - 1] < val:
                    return mid
                else:
                    hign = mid - 1
        return


# 查找最后一个小于等于给定值的元素
class Solution4(object):

    def lastValue(self, a, n, val):
        low = 0
        hign = n - 1
        while low <= hign:
            mid = low + ((hign - low) >> 1)
            if a[mid] > val:
                hign = mid - 1
            else:
                if mid == n - 1 or a[mid + 1] > val:
                    return mid
                else:
                    low = mid + 1
