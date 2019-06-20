# MySql常用命令

密码登录：**mysql -h** localhost **-u** root **-p**

查看数据源：**show dababases**

指定数据源：**use** test

查询所有的表：**show tables**

查看数据库安装地址：**select @@basedir**

查看数据库数据存储地址：**select @@basedir**   数据库配置放在数据存储的地方

查看事务级别：

​			**SELECT @@global.tx_isolation;（老版本）SELECT @@global.transaction_isolation**（新版本8.0）

查看回话级别的隔离级别：

​			**SELECT @@tx_isolation**;（老版本）**SELECT  @@transaction_isolation**（新版本8.0）

​			**SELECT @@session.tx_isolation**（老版本)**SELECT@@session.transaction_isolation**（新版本8.0）

设置系统级别的隔离级别：**set** **global** **transaction** **isolation** **level** **read** **committed**;

设置回话级别的隔离级别：**set** **session** **transaction** **isolation** **level** **read** **committed**;

查询事务提交方式： **select @@autocommit**;  1自动提交

设置事务提交方式：**set autocommit = 0**; 手动提交

开启事务： start **transaction**;   **begin**（开始）;**rollback**（回滚）;**commit**（提交）;

在事务中创建保存点：**savepoint** tx1; **rollback** tx1（回滚到保存点）;

