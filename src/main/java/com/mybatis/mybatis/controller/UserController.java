package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;
import java.util.List;

@Api(value = "简单test测试",tags = "简单测试API")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "根据id来查找某条记录")
    @RequestMapping(value = "/getUser/{id1}",method = RequestMethod.GET)//这个是网址传入参数，要和下面参数一样
    public  String  getUser(@ApiParam @PathVariable Integer id1){
        System.out.println("进入getUser----------------");
        return  userService.sel(id1).toString();//返回的是一个字符串，toString在User类里有重载，返回的是User{id=2,userName1='小鑫',password2='234',realName3='小辛'}
     //   return userService.sel(id1);//这样返回的是一个对象，要在类名前加User属性应该是数据库的列名{"id":2,"userName":"小鑫","password":"234","realName":"小辛"}
    }


    @ApiOperation(value = "根据userName来查找多条记录")
    @RequestMapping(value = "/getListByUserName" ,method = RequestMethod.GET)
    public List<User> getUserListByUser( @RequestParam(value = "name",required = true)String userName){
        //@RequestParam 后面跟着第一个参数，网址时，必须要有 ？id可以不跟值。
        //这里@RequestParam(value = "id7",required = true) String id6   这样格式的话，你的网址参数必须是 id7
        // @RequestParam String id6 则网址的参数必须是id6了
        //(@RequestParam(value = "id7",required = true) String id6,@RequestParam(value = "username1",required = true)  String userName),这样也是的
        User user = new User();
        if(!StringUtils.isEmpty(userName)){
            user.setUserName(userName);
        }
        return  userService.selectByUser(user);
    }
    //添加一条记录
    @ApiOperation(value = "添加一条记录")
    @RequestMapping(value = "/insertUser",method = RequestMethod.GET)
    public String insertUser(User user){
            System.out.println("进入controller方法");
            int result = userService.insertUser(user);
            if(result == 1){
                System.out.println("插入成功！");
            }
            else{
                System.out.println("插入失败");
            }
            return  "insert";
    }


    //添加多条记录
    @ApiOperation(value = "添加多条记录")
    @RequestMapping(value = "insertUserList" ,method = RequestMethod.GET)
    public String insertUserList(){
        System.out.println("进入了insertUserList方法");
        int result = userService.insertUserList();
        if(result == 1){
            System.out.println("插入成功！");
        }
        else{
            System.out.println("插入失败");
        }
        return  "insert";
    }


    @ApiOperation("删除某条记录")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(Integer id,String userName){
            User user =new User();
            user.setId(id);
            user.setUserName(userName);
            System.out.println("进入-----------------");
            System.out.println(user);
            int result = userService.deleteUser(user);
            if(result > 0){
                System.out.println("删除成功！");
            }
            else{
                System.out.println("删除失败！");
            }
            return "delete";
    }



    @ApiOperation(value = "修改某条记录")
    @RequestMapping( value = "/updateUser" ,method = RequestMethod.GET)
    public String updateUser(User user){
        int result = userService.updateUser(user);
        if(result > 0){
            System.out.println("更新成功");
        }
        else{
            System.out.println("更新失败");
        }
        return "update";

    }



    @ApiOperation("按照id或者name删除缓存")
    @GetMapping("/deleteCache")
    public String  deleteCache(Integer id,String userName){
        User user =new User();
        user.setId(id);
        user.setUserName(userName);
        userService.deleteCach(user);

        return "deleterCache";

    }

    /**
     * 查询mybatis_table所有
     * @return
     */
    @ApiOperation("查询mybatis_table所有")
    @GetMapping("/selectAll")
    public List<User> selectAll(){
        return userService.selectAll();
    }



}
