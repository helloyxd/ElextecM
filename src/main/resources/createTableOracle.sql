delete from MDM_DATAPERMISSION_DEFINED
drop table mdm_product
drop table mdm_productinfo

INSERT ALL INTO mdm_datapermission(id,definedId,roleId,permissionValue,createTime,creater,status)
VALUES(sys_guid(), ?, ?, ?, sysdate, ?, 0) 
INTO mdm_datapermission(id,definedId,roleId,permissionValue,createTime,creater,status) 
VALUES(sys_guid(), ?, ?, ?, sysdate, ?, 0) 
SELECT 1 FROM DUAL


create table MDM_User
(
   id                   varchar2(32) primary key,
   user_name            varchar2(64) not null,
   user_password        varchar2(64) not null,
   full_name            varchar2(64),
   department_id        varchar2(64),
   status               number(1) default 0,
   creater              varchar2(64) not null,
   create_time          TIMESTAMP
);
comment on table MDM_User is '系统用户表';
comment on column MDM_User.id is '主键';
comment on column MDM_User.user_name is '用户名';
comment on column MDM_User.user_password is '用户密码';
comment on column MDM_User.full_name is '用户全名';
comment on column MDM_User.department_id is '用户部门';
comment on column MDM_User.status is '用户状态,0正常，1锁定，2禁用，3删除';
comment on column MDM_User.creater is '创建者';
comment on column MDM_User.create_time is '创建时间';

select * from mdm_user where id='798D5F70C6434815A1A3194C48695EC4';
select * from user_tab_columns where Table_Name='MDM_USER';
select * from user_tab_comments where Table_Name='MDM_USER';
select * from user_col_comments where Table_Name='MDM_USER'; 


create table MDM_Department
(
   id                   varchar2(32) primary key,
   depart_code          varchar2(32) not null,
   depart_name          varchar2(64) not null,
   parent_id            varchar2(32),
   status               number(1) default 0,
   creater              varchar2(64) not null,
   create_time          TIMESTAMP
);

create table MDM_User_Role
(
   id                   varchar2(32) primary key,
   role_id              varchar2(32) not null,
   user_id              varchar2(32) not null
);

create table MDM_Role
(
   id                   varchar2(32) primary key,
   role_name            varchar2(64) not null,
   role_desc            varchar2(128),
   status               number(1) default 0,
   creater              varchar2(64) not null,
   create_time          TIMESTAMP
);

create table MDM_Role_Menu
(
   id                   varchar2(32) primary key,
   role_id              varchar2(32) not null,
   menu_id              varchar2(32) not null,
   role_type            number(1) not null
);

alter table
   MDM_Role_Menu
modify
   (
   role_type number(1) null
   );

create table MDM_Role_Data
(
   id                   varchar2(32) primary key,
   role_id              varchar2(32) not null,
   data_id              varchar2(32) not null,
   role_type            number(1) null
);

create table MDM_Menu
(
   id 					varchar2(32) primary key,
   menu_name			varchar2(64) not null,
   menu_url				varchar2(128) not null,
   parent_id			varchar2(32),
   menu_level			number(1) default 0,
   sort_order			integer default 1000,
   remark				varchar2(256),
   status				number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);
alter table
   MDM_Menu
add
   (
   method varchar2(32)
   );
   
   alter table
   MDM_Menu
add
   (
   icon varchar2(64)
   );
alter table
   MDM_Menu
modify
   (
   menu_url varchar2(32) null
   );
   
alter table
   MDM_Menu
modify
   (
   menu_level number(4) default 0
   );

create table MDM_DataPermission
(
   id                   varchar2(32) primary key,
   defined_id           varchar2(32) not null,
   role_id              varchar2(32) not null,
   permission_value     varchar2(64) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

select * from MDM_DataPermission

alter table
   MDM_DataPermission
modify
   (
   role_id varchar2(32) null
   );
   
alter table
   MDM_DataPermission
drop (role_id)
 

create table MDM_DataPermission_Defined
(
   id                   varchar2(32) primary key,
   table_id             varchar2(32) not null,
   permission_field     varchar2(64) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table MDM_Model
(
   id                   varchar2(32) primary key,
   mdm_model            varchar2(128) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table MDM_BS
(
   id                   varchar2(32) primary key,
   bs_name              varchar2(128) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table MDM_BS_Model
(
   id                   varchar2(32) primary key,
   model_id             varchar2(32) not null,
   bs_id                varchar2(32) not null
);

create table MDM_Data_Mapper
(
   id                   varchar2(32) primary key,
   mdm_data_id          varchar2(32) not null,
   bs_data_id           varchar2(32) not null,
   model_id             varchar2(32) not null,
   bs_id                varchar2(32) not null,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP,
   modifier             varchar2(64) not null,
   modifier_time        TIMESTAMP
);

create table MDM_TableDefinition
(
   id                   varchar2(32) primary key,
   table_name           varchar2(64) not null,
   table_label          varchar2(128) not null,
   model_id             varchar2(32) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

alter table
   MDM_TableDefinition
add
   (
   isMenu  number(1) default 0
   );

create table MDM_TableRelation
(
   id                   varchar2(32) primary key,
   table1               varchar2(32) not null,
   table2               varchar2(32) not null,
   relation             number(1),
   foreign_key1         varchar2(64),
   foreign_key2         varchar2(64),
   muti_relation_table  varchar2(64),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP	
);


create table MDM_BasicData
(
   id                   varchar2(32) primary key,
   basicType            varchar2(64) not null,
   basicValue           varchar2(64) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP	
);



