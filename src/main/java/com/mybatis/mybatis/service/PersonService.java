package com.mybatis.mybatis.service;

import com.mybatis.mybatis.entity.db1.Person;
import com.mybatis.mybatis.mapper.db1.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonMapper personMapper;
    public void insertPerson(Person person){
        personMapper.insertPerson(person);

    }


}
