package com.mybatis.mybatis.mapper.db1;

import com.mybatis.mybatis.entity.db1.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonMapper {

    void insertPerson(Person person);



}
