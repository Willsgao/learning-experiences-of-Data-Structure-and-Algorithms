#!/usr/bin/env python
# coding: utf-8

# # 某航空公司客户价值分析

# ## 实验目的：<br>
# 借助某航空公司客户数据，对客户进行分类。<br>
# 对不同的客户类别进行特征分析，比较不同类别客户的客户价值。<br>
# 对不同价值的客户类别提供个性化服务，制定相应的营销策略。<br>

# ### 读取数据，指定编码为gb18030

# In[1]:


import numpy as np
import pandas as pd

data = pd.read_csv('../data/air_data.csv',encoding='gb18030')
data


# <img src='../data/data1.png'>

# <img src='../data/data2.png'>

# ### 数据描述性分析

# In[2]:


# 查看数据的总数量，共有62988条数据
# print(len(data1))
# 查看数据的确实信息
data.info()


# In[3]:


# 查看前10条数据
data.describe()


# In[4]:


# 查看数据的确实信息
data.head(10)


# ### 有总体数据中可以得到以下基本数据特性
# * WORK_CITY,GENDER,WORK_PROVINCE,WORK_COUNTRY,AGE,SUM_YR_1,SUM_YR_2存在空值<br>
# 

# ### 数据预处理<br>
# #### 1. 去除票价为空的数据
# 

# In[5]:


# 将SUM_YR_1，SUM_YR_2为0的值去除
exp1 = data["SUM_YR_1"].notnull()
exp2 = data["SUM_YR_2"].notnull()
exp = exp1 & exp2
data_notnull = data.loc[exp, :]
print('删除缺失值后数据形状为：', data_notnull.shape)


# #### 2.只保留票价不为0，平均折扣率不为0，总飞行公里数大于0的记录。

# In[6]:


# 只保留票价非零，并且平均折扣率不为零，总飞行里程大于零的数据
index1 = data_notnull["SUM_YR_1"] != 0
index2 = data_notnull["SUM_YR_2"] != 0
index3 = (data_notnull['SEG_KM_SUM'] > 0) & (data_notnull['avg_discount'] != 0)
data = data_notnull[(index1|index2) & index3]
print('删除缺失值后数据形状为：', data.shape)


# ### 构建特征

# <img src='../data/1.jpg'>

# L: LOAD_TIME  观测窗口的结束时间----FFP_DATE	入会时间<br>
# R: LAST_TO_END  最后一次乘机时间至观测窗口结束时长<br>
# F: FLIGHT_COUNT 观测窗口内的飞行次数 <br>
# M: SEG_KM_SUM 观测窗口的总飞行公里数 <br>
# C: avg_discount 平均折扣率<br>
# 

# In[7]:


## 选取需求特征
data_filter = data[["FFP_DATE", "LOAD_TIME", "LAST_TO_END","FLIGHT_COUNT",  
                     "SEG_KM_SUM", "avg_discount"]]
data_filter.head()
## 构建L特征
L = pd.to_datetime(data_filter['LOAD_TIME']) - pd.to_datetime(data_filter['FFP_DATE'])
L = L.astype('str').str.split().str[0]
L = L.astype('int') / 30
L

## 合并特征
airline_features = pd.concat([L, data_filter.iloc[:,2:]], axis=1)
print('构建LRFMC模型的前5行数据：\n', airline_features.head())


# In[8]:


airline_features = airline_features.rename(columns={0:'L'})
airline_features.head()


# ### 数据标准化处理

# In[9]:


# 标准差标准化 airline_features_scaled
airline_zscore = (airline_features - airline_features.mean()) / (airline_features.std())
airline_zscore.head()


# ### 使用k均值构建模型

# In[12]:


from sklearn.cluster import KMeans #导入kmeans算法
k = 5  # 人为确定聚类中心的数量
# 构建模型
kmeans_model = KMeans(n_clusters = k, random_state=123)
fit_means = kmeans_model.fit(airline_zscore) # 进行模型训练
# 统计不同类别样本的数量
r1 = pd.Series(kmeans_model.labels_).value_counts()
print('最终每个类别的样本数目为：\n', r1)
kmeans_model.labels_[:20]  # 查看样本的类别标签
kmeans_model.cluster_centers_  # 查看聚类中心
# # kmeans_model


# In[14]:


##写array类型数据到csv文件方法1
##先利用pandas转成DataFrame模式，然后保存，建议用这个方法dataframe这个格式做数据分析很好用
dd=pd.DataFrame(kmeans_model.cluster_centers_)
dd.to_csv("km1.csv",header=['L','R','F','M','C'])


# In[15]:


#写array类型数据到csv文件方法2
import csv
out = open('new2.csv','w',newline="")
#设定写入模式
csv_write = csv.writer(out,dialect='excel')

##写入多行
csv_write.writerows(kmeans_model.cluster_centers_)
out.close()


# In[11]:


import matplotlib.pyplot as mp

# 定义函数，绘制雷达图
def radar_chart(result):
    # 解析出类别标签和种类
    #result = np.ndarray(result)
    labels = ['L', 'R', 'F', 'M', 'C']
#     kinds = list(result.iloc[:, 0])
    kinds = list(result.iloc[:, 0])##这个地方不要一次把所有数据全画了，电脑扛不住，挑几个
    
    # 在雷达图中要保证数据闭合，此处添加L列，并转化为np.ndarray类型
    result = pd.concat([result, result[['L']]], axis=1)
    
    ##centers = np.ndarray(result.iloc[:, 1:]) ##这个地方不需要转成array，你这个维数太多了，所以报错
    centers = result
    
    # 分割圆周，让图形实现闭合
    n = len(labels)
    angle = np.linspace(0, 2*np.pi, n, endpoint=False)
    angle = np.concatenate((angle, [angle[0]]))
    
    # 绘图
    fig = mp.figure()
    ax = fig.add_subplot(111, polar=True) # 参数polar以极坐标形式绘图
    

    # 画线
    for i in range(len(kinds)):
        ax.plot(angle, centers.iloc[i].values, linewidth=2, label = kinds[i]) ##dataframe的数据可以直接用
    
    # 添加属性标签
    ax.set_thetagrids(angle*180 / np.pi, labels)
    mp.title('不同种类')
    mp.legend(loc= 'lower right')
    mp.show()

radar_chart(airline_zscore)

