package com.mybatis.mybatis.mapper.mapper1;

import com.mybatis.mybatis.entity.Content;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ContentMapper {

    public Content findContent(long id);

    public void save(Content content);
}
