＃Test
人事管理系统由IntelliJ  IDEA开发工具开发使用了
SSM框架，实现了对用户的注册登录退出，以及对用户登录的验证，对部门功能模块信息的增删改查，职位功能模块信息的增删改查，员工功能模块信息的增删改查，公告管理功能模块信息的增删改查，以及上传下载和删除文件或图片。在各个模块下都能够再登陆成功的结果下进行独立操作，方便企业对用户职位信息的办公，提高系统执行力，使得繁琐的操作更简单化更人性化，同时体现出互联网时代对现代生活的质量和速度的大幅度的提升。

1	id	int	部门标识
2	name	varchar	部门名称
3	Remark	varchar	部门描述
表2文档表 document
序号	字段名	类型	描述
1	id	int	文件标识
2	TITLE	varchar	文件标题
3	filename	varchar	文件名
4	REMARK	varchar	文件描述
5	CREATE_DATE	timestamp	上传日期
6	USER_ID	int	上传人id
表3员工表employee
序号	字段名	类型	描述
1	id	int	员工标识
2	DEPT_ID	Int	外键：部门id
3	age	int	员工年龄
4	NAME	int	员工名
5	CARD_ID	int	外键：职位id
6	address	varchar	员工住址
7	post_code	varchar	邮政编码
8	Tel	varchar	电话
9	phone	varchar	手机号
10	qq_num	varchar	员工qq
11	Email	varchar	员工邮箱
12	sex	int	员工性别
13	party	varchar	政治面貌
14	birthday	varchar	生日
16	race	varchar	民族
17	education	varchar	学位
18	speciality	varchar	特长
19	hobby	varchar	业余爱好
20	remark	varchar	描述
21	Create_date	timestamp	创建日期
表4 职位表job
序号	字段名	类型	描述
1	id	int	职位标识
2	name	varchar	职位名
3	remark	varchar	职位描述


	表5公告表notice
序号	字段名	类型	描述
1	id	int	公告标识
2	title	varchar	公告姓名
3	content	text	公告内容
4	Create_date	timestamp	创建日期

	表3系统管理员（用户表）user
序号	字段名	类型	描述
1	id	int	用户标识
2	loginname	varchar	账户
3	password	int	密码
4	status	int	状态
5	createdate	int	创建日期
6	username	Varchar	用户名

3.1项目设计规划
(说明项目的功能模块划分，项目组分工情况)
人事管理系统分为

1用户管理：
登录，退出、登录验证
增加，删除，修改，查询

2部门管理：
增加，删除，修改，查询

3职位管理：
增加，删除，修改，查询

4员工管理：
增加，删除，修改，查询

5公告管理：
预览
增加，删除，修改，查询

6下载中心：
上传，下载
增加，删除，修改，查询
