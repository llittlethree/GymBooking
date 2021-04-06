CREATE DATABASE IF NOT EXISTS GymBooking DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use GymBooking;

-- 学生表
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
id int(11) not null primary key auto_increment comment "id",
openid varchar(100) null comment "微信的用户唯一标识，openid ",
student_number varchar(100) not null comment "学号" ,
student_phone varchar(50) not null comment  "手机号，作为账号",
student_password varchar(100) not null comment "密码 MD5",
student_name varchar(100) not null comment "学生姓名",
student_sex int(2) default 1 not null comment "学生性别,1:男 0:女",
class_id int(11) not null default 1 comment "班级id",
create_time int(11) not null comment "添加时间",
update_time int(11) default 0 comment "更新时间",
delete_time int(11) default 0 comment "删除时间",
student_status int(1) default 0 comment "帐号状态 0正常(默认) 1限制。2.待审核",
remark varchar(255) null comment "备注",

-- 学生的学号和密码创建组合索引
index(student_number,student_password),
-- 学生的手机号和密码创建组合索引
index(student_phone,student_password)
)comment "学生表" CHARACTER SET = utf8 COLLATE = utf8_general_ci ;

-- 管理员表
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
id int(11) not null primary key auto_increment comment "id",
admin_phone varchar(50) not null comment  "手机号，作为账号",
admin_name varchar(100) not null comment "姓名",
admin_password varchar(100) not null comment "密码 MD5",
admin_sex int(2) default 1 not null comment "性别,1:男 0:女",
create_time int(11) not null comment "添加时间",
update_time int(11) default 0 comment "更新时间",
delete_time int(11) default 0 comment "删除时间",
admin_status int(1) default 2 comment "帐号状态 0正常(默认) 1限制 2.待审核",
remark varchar(255) null comment "备注",

-- 管理员账号和密码创建组合索引
index(admin_phone,admin_password)
)comment "管理员表" CHARACTER SET = utf8 COLLATE = utf8_general_ci ;

-- 班级表
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
id int(11) not null primary key auto_increment comment "id",
class_name varchar(100) not null comment "班级名称",
college_name varchar(100) not null comment "学院名称",
professional_name varchar(100) not null comment "专业名称",
create_time int(11) not null comment "添加时间",
update_time int(11) default 0 comment "更新时间",
delete_time int(11) default 0 comment "删除时间",
class_status int(1) default 0 comment "状态 0可用 1不可用",
remark varchar(255) null comment "备注"
)comment "班级表" CHARACTER SET = utf8 COLLATE = utf8_general_ci ;

-- 预约表
DROP TABLE IF EXISTS `t_booking`;
CREATE TABLE `t_booking`  (
id int(11) not null primary key auto_increment comment "id",
number varchar(50) not null comment "预约号",
booking_userid int(11) not null comment "预约者id",
venue_id int(11) not null comment "预定场地id",
booking_start_time int(11) not null comment "预定开始时间",
booking_end_time int(11) not null comment "预定结束时间",
create_time int(11) not null comment "添加时间",
update_time int(11) default 0 comment "更新时间",
delete_time int(11) default 0 comment "删除时间",
booking_status int(1) default 2 comment "状态 0取消预约 1预约成功，2预约失败 3预约已核销 4预约逾期",
remark varchar(255) null comment "备注"
)comment "预约表" CHARACTER SET = utf8 COLLATE = utf8_general_ci ;


-- 体育馆场地表
DROP TABLE IF EXISTS `t_venue`;
CREATE TABLE `t_venue`  (
id int(11) not null primary key auto_increment comment "id",
venue_category_name int(11) not null comment "场地类别id",
venue_name varchar(50) not null comment "场地名称",
price decimal(6,2) not null default 0 comment "价格 0免费  ",-- 9999.00
create_time int(11) not null comment "添加时间",
update_time int(11) default 0 comment "更新时间",
delete_time int(11) default 0 comment "删除时间",
remark varchar(255) null comment "备注"
)comment "体育馆场地表" CHARACTER SET = utf8 COLLATE = utf8_general_ci ;
insert into t_venue values (1,1,'篮球场1',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (2,2,'羽毛球场1',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (3,2,'羽毛球场2',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (4,2,'羽毛球场3',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (5,2,'羽毛球场4',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (6,3,'排球场1',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (7,3,'排球场2',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (8,4,'乒乓球场1',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (9,4,'乒乓球场2',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (10,4,'乒乓球场3',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (11,4,'乒乓球场4',0,FLOOR(unix_timestamp()),0,0,null);
insert into t_venue values (12,'健身房',1,FLOOR(unix_timestamp()),0,0,null);

-- 体育馆场地分类表
DROP TABLE IF EXISTS `t_venue_category`;
CREATE TABLE `t_venue_category`  (
id int(11) not null primary key auto_increment comment "id",
venue_category_name varchar(50) not null comment "场地类别名称",
create_time int(11) not null comment "添加时间",
update_time int(11) default 0 comment "更新时间",
delete_time int(11) default 0 comment "删除时间",
remark varchar(255) null comment "备注"
)comment "体育馆场地分类表" CHARACTER SET = utf8 COLLATE = utf8_general_ci ;
insert into t_venue_category values (1,'篮球场',FLOOR(unix_timestamp()),0,0,null);
insert into t_venue_category values (2,'羽毛球场',FLOOR(unix_timestamp()),0,0,null);
insert into t_venue_category values (3,'排球场',FLOOR(unix_timestamp()),0,0,null);
insert into t_venue_category values (4,'乒乓球场',FLOOR(unix_timestamp()),0,0,null);
insert into t_venue_category values (5,'健身房',FLOOR(unix_timestamp()),0,0,null);
