<!--

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

-->

# 一、工作流程

## 主要链接

IoTDB 官网：https://iotdb.apache.org/

代码库：https://github.com/apache/incubator-iotdb/tree/master

快速上手：https://iotdb.apache.org/#/Documents/Quick%20Start

## 订阅邮件列表

邮件列表是 Apache 项目进行技术讨论和用户沟通的地方，关注邮件列表就可以收到邮件了。

邮件列表地址：dev@iotdb.apache.org

关注方法：用想接收邮件的邮箱向 dev-subscribe@iotdb.apache.org 发一封邮件，主题内容不限，收到回复后，再次向确认地址发一封确认邮件（确认地址比较长，推荐qq邮箱）。

## 新功能、Bug 反馈、改进等目标记录

在 Jira 上提 issue：https://issues.apache.org/jira/projects/IOTDB/issues/IOTDB-9?filter=allopenissues

选择 issue 类型：bug、improvement、new feature等。新建的 issue 会自动向邮件列表中同步邮件，之后的讨论可在 jira 上留言，也可以在邮件列表进行。当问题解决后请关闭 issue。

## 邮件讨论内容（英文）

* 初来乍到：大家好，我是xxx，新加入 IoTDB 社区，希望。

* 小试牛刀：Hi，我正在解决这个 issue，给个链接，我预计的解决方案是xxx。

## 贡献代码

可以去 jira 上领取现有 issue 或者自己创建 issue 再领取，评论说我要做这个 issue 就可以。

* 克隆仓库到自己的本地的仓库，clone到本地，关联apache仓库为上游 upstream 仓库。
* 从 master 切出新的分支，分支名根据这个分支的功能决定，一般叫 f_【新功能】 或者 fix_【bug】
* 在 idea 中添加code style为 根目录的 java-google-style.xml
* 修改代码，增加测试用例（单元测试、集成测试）
	* 集成测试参考: server/src/test/java/org/apache/iotdb/db/integration/IoTDBTimeZoneIT
* 提交 PR, 以 [IOTDB-jira号] 开头
* dev发邮件：我已经提交PR，附上pr链接
* 根据其他人的审阅意见进行修改，继续更新，直到合并
* 关闭 jira issue

## 二、IoTDB 调试方式

* 服务器主函数：server/src/main/java/org/apache/iotdb/db/service/IoTDB，可以debug启动
* 客户端：client/src/main/java/org/apache/iotdb/client，linux 用 Clinet，windows 用WinClint，可以直接启动，需要参数"-h 127.0.0.1 -p 6667 -u root -pw root"
* 服务器的 rpc 实现（主要用来客户端和服务器通信，一般在这里开始打断点）：server/src/main/java/org/apache/iotdb/db/service/TSServiceImpl
	* jdbc所有语句：executeStatement(TSExecuteStatementReq req)
	* jdbc查询语句：executeQueryStatement(TSExecuteStatementReq req)	* native写入接口：insert(TSInsertReq req)


* 写入引擎 org.apache.iotdb.db.engine.StorageEngine
* 查询引擎 org.apache.iotdb.db.qp.QueryProcessor

