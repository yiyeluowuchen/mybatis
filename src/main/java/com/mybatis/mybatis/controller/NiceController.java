package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.Content;
import com.mybatis.mybatis.entity.NiceDetail;
import com.mybatis.mybatis.mapper.mapper1.ContentMapper;
import com.mybatis.mybatis.service.NiceDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.Detail;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@RestController
@Api(value = "点赞" ,tags = "点赞")
@RequestMapping("/nice")
public class NiceController {


    @Autowired
    NiceDetailService niceDetailService;

    @Autowired
    ContentMapper contentMapper;

    @ApiOperation(value = "点赞后插入数据")
    @GetMapping("/insert")//在上面加一个RequestMapping是因为在，其他地方有了这个请求路径，必须加个了，而且要以request的开头
    public void insert (long userId,long contentId){
        NiceDetail niceDetail =new NiceDetail();
        niceDetail.setUserId(userId);
        niceDetail.setContentId(contentId);
       niceDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        niceDetailService.insert(niceDetail);
    }




//     public static void main(String[] args) {
//
//        System.out.println(System.currentTimeMillis());
//        System.out.println(new Date(System.currentTimeMillis()));
//        System.out.println(LocalDateTime.now());
//        System.out.println(new Timestamp(System.currentTimeMillis()));
//
//
////结果
////         1566473937985
////         Thu Aug 22 19:38:57 CST 2019
////         2019-08-22T19:38:58.045
////         2019-08-22 19:38:58.045
//
//    }

    /**
     * 这个接口就可以实现点赞和取消赞的功能
     * @param userId
     * @param contentId
     * @return
     */
    @ApiOperation("查询数据")
    @GetMapping("/logic")
    public String niceLogic (Long userId,Long contentId){
            NiceDetail niceDetail = new NiceDetail();
            niceDetail.setUserId(userId);
            niceDetail.setContentId(contentId);
            NiceDetail detail = niceDetailService.find(niceDetail);
            long  result = 0;
            if(detail != null){
                //,如果查询到有，说明点赞过，就删除。点赞数-1再放进去
                niceDetailService.delete(detail);
                Content content =contentMapper.findContent(contentId);
              System.out.println(content.getNice()+"--------------------");
              content.setNice(content.getNice()-1);
                result = content.getNice();
              contentMapper.save(content);

            }else{
                //如果查询到没有，则添加一条记录，点赞数也+1

                niceDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
                niceDetailService.insert(niceDetail);
               Content content = contentMapper.findContent(contentId);
               content.setNice(content.getNice()+1);
               result = content.getNice();
               contentMapper.save(content);

            }

            return ""+result;

}

}
