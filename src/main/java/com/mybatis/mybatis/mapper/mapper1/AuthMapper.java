package com.mybatis.mybatis.mapper.mapper1;

import com.mybatis.mybatis.entity.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthMapper {


    public List<Auth> findAll();

    public Auth getAuth(String username,String password);

    public Auth byAuth(String username);




}
