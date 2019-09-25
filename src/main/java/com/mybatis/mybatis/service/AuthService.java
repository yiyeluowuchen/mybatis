package com.mybatis.mybatis.service;

import com.mybatis.mybatis.entity.Auth;
import com.mybatis.mybatis.mapper.mapper1.AuthMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Configuration
@Service
public class AuthService  implements UserDetailsService {

    Logger logger =  LoggerFactory.getLogger(this.getClass());


    @Autowired
    AuthMapper authMapper;


    /**
     * 查询所有信息
     * @return
     */
    public List<Auth> findAll(){
        return authMapper.findAll();
    }

    /**
     * 通过名字和密码查询所有信息
     * @param username，password
     * @return
     * @throws
     */

    public Auth getAuth(String username,String password){
         return authMapper.getAuth(username,password);
    }

    /**
     * 重载了UserDetailsService中的方法，loadUserByUsername，这个可以获得用户的名字，密码，角色的信息
     * @param username  根据用户的username来获取用户信息
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //根据用户名字来查出用户信息,意思说利用这个框架传入的用户名就会通过这里咯，来知道用户的信息
        Auth auth = authMapper.byAuth(username);

        if(auth == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(auth.getPassword());
        logger.info("密码加密为：{}",newPassword);
        logger.info("用户名为：{}",username);
        logger.info("用户名为：{}",auth.getUsername());
        //User这个类实现了UserDedatil
        return new User(auth.getUsername(),newPassword,createAuthority(auth.getAuth()));

    }


    //这里将数据库的角色分割，构造GrantedAuthority   ：这个英文我翻译为同意权限

    private List<SimpleGrantedAuthority> createAuthority(String roles){
            logger.info("roles:{}",roles);
        String [] roleArray = roles.split(",");
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(String role:roleArray){

            list.add(new SimpleGrantedAuthority(role));
        }

        return list;
    }

}
