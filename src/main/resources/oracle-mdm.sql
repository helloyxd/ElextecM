SELECT * FROM mdm_datapermission_defined;
SELECT * FROM mdm_datapermission;
SELECT * FROM Empoyeer;
SELECT * FROM mdm_Employeer;
delete from mdm_Employeer where age is null
select * from MDM_TABLERELATION
update MDM_TABLERELATION set table2='685C9755336AAA91E05013AC0688141C'

SELECT * FROM TaskDataRecord_Summary

SELECT name,age,post FROM Empoyeer WHERE 1=1
ALTER TABLE Employeer RENAME TO Empoyeer;

select * from  mdm_table_mapper;
delete from mdm_table_mapper

create table Employeer
(
   name            varchar2(64),
   age       		integer,
   post            varchar2(64)
   
);

SELECT * FROM mdm_model WHERE mdm_model ='职员模块'
SELECT * FROM mdm_datapermission_defined WHERE table_id = ''
delete from mdm_datapermission_defined;
delete from mdm_datapermission;

insert into mdm_Employeer values(sys_guid(),'a',1,'111');
insert into mdm_Employeer values(sys_guid(),'zkj',1,'222');
insert into Employeer values('c',1,'ccc');
insert into Employeer values('d',1,'ddd');
insert into Employeer values('e',1,'eee');


delete from mdm_datapermission_defined
delete from mdm_datapermission

INSERT ALL 
INTO mdm_employeer(id,REMARK)VALUES(sys_guid(),null) 
INTO mdm_employeer(id,REMARK)VALUES(sys_guid(),null)
INTO mdm_employeer(id,REMARK)VALUES(sys_guid(),null) 
INTO mdm_employeer(id,REMARK)VALUES(sys_guid(),null) 
INTO mdm_employeer(id,REMARK)VALUES(sys_guid(),null) 
SELECT 1 FROM DUAL

INSERT ALL 
INTO mdm_datapermission(id,defined_id,role_id,permission_value,create_time,creater,status)
VALUES(sys_guid(), ?, ?, ?, sysdate, ?, 0)
INTO mdm_datapermission(id,defined_id,role_id,permission_value,create_time,creater,status) 
VALUES(sys_guid(), ?, ?, ?, sysdate, ?, 0) 
SELECT 1 FROM DUAL

select * from MDM_ServiceInterface_Defined;

select * from MDM_ServiceInterface_Param;
select * from MDM_ServiceParam_FieldDefined;
select * from MDM_ServiceParam_TableDefined;


update MDM_ServiceInterface_Defined set model_id='66F49B32E0229EFFE05013AC0688029C';

create or replace directory UTL_DIR as 'C:\dba';
grant write on directory UTL_DIR to public;
grant read on directory UTL_DIR to public;

CREATE OR REPLACE PROCEDURE P_EXPORTDLL(P_TABLE_NAME VARCHAR2, P_FILENAME   VARCHAR2) IS
BEGIN
  DECLARE
    L_FILE     UTL_FILE.FILE_TYPE;
    L_BUFFER   VARCHAR2(1000);
    L_AMOUNT   BINARY_INTEGER := 100;
    L_POS      INTEGER := 1;
    L_CLOB     CLOB;
    L_CLOB_LEN INTEGER;
  BEGIN
    SELECT DBMS_METADATA.GET_DDL('TABLE', P_TABLE_NAME) || ';'
      INTO L_CLOB
      FROM DUAL;
    L_CLOB_LEN := DBMS_LOB.GETLENGTH(L_CLOB);
    L_FILE     := UTL_FILE.FOPEN('UTL_DIR', P_FILENAME || '.sql', 'a', 1000);

    WHILE L_POS < L_CLOB_LEN LOOP
      DBMS_LOB.READ(L_CLOB, L_AMOUNT, L_POS, L_BUFFER);
      UTL_FILE.PUT(L_FILE, L_BUFFER);
      L_POS := L_POS + L_AMOUNT;
    END LOOP;
    UTL_FILE.FCLOSE(L_FILE);
  END;
END P_EXPORTDLL;


CREATE OR REPLACE PROCEDURE p_whole AS
BEGIN
  FOR x IN (SELECT table_name FROM user_tables) LOOP
       p_exportDLL(x.table_name,'paul');
  END LOOP;
END  p_whole;

execute MDM_TEST.P_WHOLE;

select t.TABLE_NAME 表名,b.comments 表备注 ,t.COLUMN_ID 序号 ,t.COLUMN_NAME 字段名 ,t.DATA_TYPE 类型 ,
t. DATA_LENGTH 长度 ,t.NULLABLE 是否为空,a.comments 字段备注  
 from user_tab_columns t   
 left join USER_COL_COMMENTS a on a.table_name=t.TABLE_NAME and a.column_name=t.COLUMN_NAME  
 left join USER_tab_COMMENTS b on b.table_name=t.TABLE_NAME 
 order by t.TABLE_NAME,t.COLUMN_ID


CREATE TABLE mdm_product(
	id VARCHAR2(32) PRIMARY KEY,
	product_name VARCHAR2(32) NOT NULL,
	product_type VARCHAR2(32) NOT NULL

);

select * from mdm_product
SELECT count(*) FROM mdm_product 

 CREATE TABLE mdm_product(id VARCHAR2(32) PRIMARY KEY,product_name VARCHAR2(32) NOT NULL,product_type VARCHAR2(32) NOT NULL);
drop table mdm_product



select id from mdm_user where user_name = 'admin0'
select * from MDM_MENU where id in(
	select menu_id from mdm_role_menu where role_id in(
		select id from mdm_role where id in(
			select role_id from MDM_USER_ROLE where user_id=(
				select id from mdm_user where user_name = 'admin0'
			)
		)
	)
)

insert into MDM_ROLE_MENU(id,role_id,menu_id,role_type) values(sys_guid(),'CD069501A052496C948AF41BED2E160A','B3BD7D039C4248B6B979A77743737522',1);
insert into MDM_ROLE_MENU(id,role_id,menu_id,role_type) values(sys_guid(),'CD069501A052496C948AF41BED2E160A','A02D448C63144FEBA73761656B7B346D',1);
insert into MDM_ROLE_MENU(id,role_id,menu_id,role_type) values(sys_guid(),'CD069501A052496C948AF41BED2E160A','7437C8863E7E4CC887D14B0828E16E06',1);
insert into MDM_ROLE_MENU(id,role_id,menu_id,role_type) values(sys_guid(),'CD069501A052496C948AF41BED2E160A','3DBEC894B4EE4223B2CBCF65B3349B75',1);

insert into MDM_MENU(id,menu_name,menu_url,method,parent_id,menu_level,sort_order,status,remark,creater,create_time)
values (sys_guid(),'添加菜单','/menu','post','','1','1','1','bz','sys',sysdate);
insert into MDM_MENU(id,menu_name,menu_url,method,parent_id,menu_level,sort_order,status,remark,creater,create_time)
values (sys_guid(),'更新菜单','/menu','put','','1','1','1','bz','sys',sysdate);
insert into MDM_MENU(id,menu_name,menu_url,method,parent_id,menu_level,sort_order,status,remark,creater,create_time)
values (sys_guid(),'删除菜单','/menu','delete','','1','1','1','bz','sys',sysdate);
insert into MDM_MENU(id,menu_name,menu_url,method,parent_id,menu_level,sort_order,status,remark,creater,create_time)
values (sys_guid(),'查询菜单','/menu','get','','1','1','1','bz','sys',sysdate);

 

SELECT *
  FROM (SELECT ROWNUM AS rn, t.*
          FROM MDM_USER t
         WHERE user_name like '%admin%'
           AND ROWNUM <= 20) tt
 WHERE tt.rn >= 10;
 
 SELECT COUNT(*) FROM mdm_user WHERE 1=1 AND user_name like 'admin2'
SELECT * FROM (
	SELECT ROWNUM AS rn, t.* FROM mdm_user t 
	WHERE 1=1 AND user_name LIKE 'admin' AND ROWNUM <= 20
) tt WHERE tt.rn >= 0

SELECT * FROM (
	SELECT ROWNUM AS rn, t.* FROM mdm_user t 
	WHERE 1=1 AND user_name LIKE '%admin%' AND ROWNUM <= 20*2
	) tt
WHERE tt.rn > 20
 
 
  SELECT *
  FROM (SELECT tt.*, ROWNUM AS rn
          FROM (  SELECT t.*
                    FROM MDM_USER t
                   WHERE user_name like '%admin%'
                ORDER BY user_name DESC) tt
         WHERE ROWNUM <= 20) table_alias
 WHERE table_alias.rn >= 10;