package com.mybatis.mybatis.mapper.mapper1;


import com.mybatis.mybatis.entity.Total;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface INewsMapper {

            //通过id来多表查询查询
            Total select(Integer id);


}
