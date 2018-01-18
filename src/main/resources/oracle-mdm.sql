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