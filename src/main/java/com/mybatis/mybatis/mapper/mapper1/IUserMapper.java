package com.mybatis.mybatis.mapper.mapper1;
import com.mybatis.mybatis.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;

@Repository //这个加不加都可以，不加的话，在userService会有报错，但是还是会运行。
public interface IUserMapper {


    User sel(int id);

    List<User> selectByUser(User user);

   int insertUser(User user);

   int deleteUser(User user);

   int updateUser(User user);

   int insertUserList(List<User> list);

    User selectLogin (User user);

    User selectByName(String userName);
    //做一个备份mybaits_table表
    List<User> selectAll();




}
