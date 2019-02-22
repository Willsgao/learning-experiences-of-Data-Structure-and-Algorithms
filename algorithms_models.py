# -*- coding: utf-8 -*-
from __future__ import unicode_literals

# recursion terminator
# 盗梦空间，递归模式
# 递归终止条件


# 递归模板
def recursion(self, level, param1, param2):

    # recursion terminator
    if level > MAX_LEVEL:
        print_result
        return

    # process logic in current level
    process_data(level, data, ...)

    # drill down
    self.recursion(level + 1, p1, p2)

    # reverse the current level status if needed
    reverse_state(level)


def divide_conquer(problem, param1, param2):
    # recursion terminator
    if problem is None:
        print_result
        return

    # prepare data
    data = prepare_data(problem)
    subproblems = split_problem(problem, data)

    # conquer subproblems
    subresult1 = self.divide_conquer(subproblems[0], p1, p2)
    subresult2 = self.divide_conquer(subproblems[1], p1, p2)
    subresult3 = self.dibide_conquer(subproblems[2], p1, p2)
    ...

    # process and generate the final result
    result = process_result(subresult1, subresult2, subresult3)


# DFS/BFS 递归写法
visited = set()


# DFS 深度优先算法
def dfs(node, visited):
    visited.add(node)
    # process current node here
    ...
    for next_node in node.children():
        if not next_node in visited:
            dfs(next_node, visited)


def DSF(node, visited):
    visited.add(node)

    # process in current level
    for nexr_node in node.children():
        if next_node not in visited:
            dfs(next_node, visited)


def BFS(graph, start, end):
    queue = []
    queue.append([start])
    visited.add(start)

    while queue:
        node = queue.pop()
        visited.add(node)

        process(node)
        nodes = generate_related_nodes(node)
        queue.push(nodes)


def bfs(graph, start, end):
    queue = []
    queue.append([start])
    visited.add(start)

    while queue:
        node = queue.pop()
        visited.add(node)

        process(node)
        nodes = generate_related_nodes(node)
        queue.push(node)


# 二分法查找
def binarySearch(self, nums, target):
    if not nums:
        return

    n = len(nums)
    l, r = 0, n - 1
    while l <= r:
        m = l + (r - l) >> 1
        if nums[m] == target:
            return m
        elif nums[m] > target:
            r = m - 1
        else:
            l = m + 1
    return


def BinarySearch(self, nums, target):
    if not nums:
        return

    n = len(nums)
    left, right = 0, n - 1

    while left <= right:
        mid = left + (right - left) >> 1
        if nums[mid] == target:
            return mid
        elif nums[mid] > target:
            right = mid - 1
        else:
            left = mid + 1
    return


# 动态规划
def DP(self):
    # 状态定义
    dp = [[0 for _ in range(m + 1)]
          for _ in range(n + 1)]

    # 状态初始化
    dp[0][0], dp[0][1] = x, y
    ...

    # DP状态的推导
    for i in range(m + 1):
        for j in range(n + 1):
            dp[i][j] = max(dp[i - 1][j],
                           dp[i][j - 1], dp[i - 1][j - 1])
            # dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    return dp[m][n]


def df(self):
    # 定义状态变量
    dp = [[0 for _ in range(m + 1)]
          for _ in range(n + 1)]

    # 状态初始化
    dp[0][0], dp[0][1] = x, y

    # 状态转化方程
    for i in range(m + 1):
        for j in range(n + 1):
            dp[i][j] = max(or_min)(dp[i - 1][j - 1],
                                   dp[i - 1][j], dp[i][j - 1])
    return dp[m][n]
