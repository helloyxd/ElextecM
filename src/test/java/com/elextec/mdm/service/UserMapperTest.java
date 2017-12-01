package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.SampleWarApplication;
import com.elextec.mdm.entity.UserEntity;
import com.elextec.mdm.entity.UserSexEnum;
import com.elextec.mdm.mapper.TableDLLMapper;
import com.elextec.mdm.mapper.UserMapper;
import com.elextec.mdm.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	/*@Autowired
	private IUserService userService;
	
	@Test
	public  void testUser() {
		System.out.println(userService.getUserName());
	}*/
	
	
    @Autowired
    private TableDLLMapper UserMapper;

    /*@Test
    public void testInsert() throws Exception {
        UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, UserMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = UserMapper.getAll();
        System.out.println(users.toString());
    }
*/
    @Test
    public void testUpdate() throws Exception {
        //UserEntity user = UserMapper.getOne(3l);
        //System.out.println(user.toString());
        //user.setNickName("neo1");
        //UserMapper.update(user);
        //Assert.assertTrue(("neo".equals(UserMapper.getOne(3l).getNickName())));
    	//String sql = "create table TableName1(id int(11) NOT NULL AUTO_INCREMENT,empno varchar(32),empname varchar(32),PRIMARY KEY (id))";
        List<Map> mapList = UserMapper.getTableColumnDefine("memuser");
        System.out.println(mapList.get(0).get("columnName"));
    	
    }
}
