DROP TABLE MDM_BASICDATA;
DROP TABLE MDM_BS;
DROP TABLE MDM_BS_MODEL;
DROP TABLE MDM_DATAPERMISSION;
DROP TABLE MDM_DATAPERMISSION_DEFINED;
DROP TABLE MDM_DATASTRUCTURE_MAPPING;
DROP TABLE MDM_DATA_MAPPER;
DROP TABLE MDM_DEPARTMENT;
DROP TABLE MDM_MENU;
DROP TABLE MDM_MODEL;
DROP TABLE MDM_QUERYFIELD_DEFINED;
DROP TABLE MDM_ROLE;
DROP TABLE MDM_ROLE_DATA;
DROP TABLE MDM_ROLE_MENU;
DROP TABLE MDM_TABLEDEFINITION;
DROP TABLE MDM_TABLERELATION;
DROP TABLE MDM_USER_ROLE;

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
insert into MDM_User(id,user_name,user_password,full_name,status,creater,create_time) 
values(sys_guid(),'test','test','my test',0,'sys',sysdate);

comment on table MDM_User is '系统用户表';
comment on column MDM_User.id is '主键';
comment on column MDM_User.user_name is '用户名';
comment on column MDM_User.user_password is '用户密码';
comment on column MDM_User.full_name is '用户全名';
comment on column MDM_User.department_id is '用户部门';
comment on column MDM_User.status is '用户状态,0正常，1锁定，2禁用，3删除';
comment on column MDM_User.creater is '创建者';
comment on column MDM_User.create_time is '创建时间';


--select table_name from user_tables where table_name like 'MDM%';
--select * from mdm_user where id='798D5F70C6434815A1A3194C48695EC4';
--select * from user_tab_columns where Table_Name='MDM_USER';
--select * from user_tab_comments where Table_Name='MDM_USER';
--select * from user_col_comments where Table_Name='MDM_USER'; 


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
select * from MDM_Role
INSERT INTO mdm_role(id,role_name,role_desc,status,creater,create_time)
values(sys_guid(),'admin','admin',0,'sys',sysdate);

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
   role_id              varchar2(32),
   permission_value     varchar2(64) not null,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

alter table
   MDM_DataPermission
modify
   (
   role_id varchar2(32) null
   );
   
alter table
   MDM_DataPermission
drop (role_id);
 

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

create table MDM_QueryField_Defined
(
   id        			varchar2(32) primary key,
   table_id  			varchar2(32) not null,
   field				varchar2(32) not null,
   field_type			varchar2(32),
   sort_order			number(1),
   column_span			number(2),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table MDM_DataStructure_Mapping
(
   id        			varchar2(32) primary key,
   table_id  			varchar2(32) not null,
   interface_id			varchar2(32) not null,
   field1				varchar2(32),
   field2				varchar2(32),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
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

create table MDM_Table_Mapper
(
   id        			varchar2(32) primary key,
   mdm_table_id  		varchar2(32),
   mdm_field_id			varchar2(32),
   bs_table_id			varchar2(32),
   bs_field_id			varchar2(32),
   bs_io_type			varchar2(2),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table MDM_ServiceInterface_Defined
(
   id        			varchar2(32) primary key,
   type  				varchar2(8),
   wsdl_location		varchar2(128),
   dburl				varchar2(128),
   user_name			varchar2(32),
   password				varchar2(32),
   model_id				varchar2(32),
   operation_type		varchar2(8),
   operation			varchar2(32),
   wsbinding			varchar2(32),
   bing_namespace		varchar2(128),
   operation_namespace	varchar2(128),   
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

alter table
   MDM_ServiceInterface_Defined
add
   (
   bs_id	varchar2(32)
   );

create table ServiceInterface_Param
(
   id        				varchar2(32) primary key,
   service_defined_id  		varchar2(32),
   io_type					varchar2(8),
   data_type				varchar2(16),
   table_id					varchar2(32),
   table_name				varchar2(32),
   status               	number(1) default 0,
   creater					varchar2(64) not null,
   create_time				TIMESTAMP
);
ALTER TABLE ServiceInterface_Param RENAME TO MDM_ServiceInterface_Param;

create table ServiceParam_FieldDefined
(
   id        			varchar2(32) primary key,
   table_id  			varchar2(32),
   field_name			varchar2(32),
   field_type			varchar2(32),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);
ALTER TABLE ServiceParam_FieldDefined RENAME TO MDM_ServiceParam_FieldDefined;

create table ServiceParam_TableDefined
(
   id        			varchar2(32) primary key,
   param_id  			varchar2(32),
   table_name			varchar2(32),
   parent_id			varchar2(32),
   relation_type		varchar2(8),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);
ALTER TABLE ServiceParam_TableDefined RENAME TO MDM_ServiceParam_TableDefined;

create table TaskList
(
   id        			varchar2(32) primary key,
   flow_id  			varchar2(32),
   flow_type			varchar2(32),
   data_id				varchar2(32),
   bs_id				varchar2(32),
   model_id				varchar2(32),
   remark				varchar2(128),
   current_user			varchar2(64),
   last_user			varchar2(64),
   current_node			varchar2(64),
   last_node			varchar2(64),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table TaskDataRecord_Summary
(
   id        			varchar2(32) primary key,
   flow_id  			varchar2(32),
   model_id				varchar2(32),
   task_type			varchar2(32),
   success_num			integer,
   fail_num				integer,
   remark				varchar2(256),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table TaskDataRecord_Detail
(
   id        			varchar2(32) primary key,
   flow_id  			varchar2(32),
   data_id				varchar2(32),
   model_id				varchar2(32),
   system_id			varchar2(32),
   task_type			varchar2(32),
   remark				varchar2(256),
   end_time				TIMESTAMP,
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);

create table mdm_model_flow
(
   id        			varchar2(32) primary key,
   activiti_id  		varchar2(32),
   model_id				varchar2(32),
   operation_type		varchar2(32),
   status               number(1) default 0,
   creater				varchar2(64) not null,
   create_time			TIMESTAMP
);
