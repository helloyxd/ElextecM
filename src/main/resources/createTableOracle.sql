create table MDM_User(
   id                   varchar2(32) primary key,
   user_name            varchar2(64) not null,
   user_password        varchar2(64) not null,
   full_name            varchar2(64),
   department_id        integer,
   status               number(1) default 0,
   creater              varchar2(64),
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

select * from mdm_user
select * from user_tab_columns where Table_Name='MDM_USER';
select * from user_tab_comments where Table_Name='MDM_USER';
select * from user_col_comments where Table_Name='MDM_USER'; 





