package com.mybatis.mybatis.service;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.mapper.db1.ICopyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyTableService {

    @Autowired
    ICopyTable iCopyTable;

    /**
     * 插入所有
     * @param list
     * @return
     */
    public int insertAll(List<User> list){
        return iCopyTable.insertAll(list);
    }


    /**
     * 插入一条
     */
    public int insertUser2(User user){
       return  iCopyTable.insertUser2(user);
    }

}
