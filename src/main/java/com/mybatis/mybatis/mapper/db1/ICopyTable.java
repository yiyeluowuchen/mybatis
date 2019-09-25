package com.mybatis.mybatis.mapper.db1;

import com.mybatis.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ICopyTable {

    int insertAll(List<User> list);

    int insertUser2(User user);


}
