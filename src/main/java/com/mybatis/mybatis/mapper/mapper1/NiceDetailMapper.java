package com.mybatis.mybatis.mapper.mapper1;

import com.mybatis.mybatis.entity.NiceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Mapper
@Repository
public interface  NiceDetailMapper {

    public NiceDetail find(NiceDetail niceDetail);

    public void delete(NiceDetail niceDetail);

    public void   insert(NiceDetail niceDetail);


}
