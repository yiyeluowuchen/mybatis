package com.mybatis.mybatis.mapper.mapper1;

import com.mybatis.mybatis.entity.Socks;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISocksMapper {

    int insert(Socks socks);

    List<Socks> select(Socks socks);

    String selectCron(Integer id);
    
}
