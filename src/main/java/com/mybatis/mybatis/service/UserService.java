package com.mybatis.mybatis.service;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.entity.db1.Person;
import com.mybatis.mybatis.mapper.db1.PersonMapper;
import com.mybatis.mybatis.mapper.mapper1.IUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.random;

@Service
public class UserService {

    @Autowired
    private IUserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private PersonMapper personMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**redis
     * 获取用户策略：先从缓存中获取用户，若是缓存中没有，则从数据表中获取，然后再把数据放入缓存中
     * @param id
     * @return User
     */
    /*事物管理,如果这样就会，固定了哪个事务管理对哪个数据源起作用，这里就对mapper1起作用，所以，这里10/0即使错了，person也插入进去了，
    *当你去掉时这个管理，只留  @Transactional，两个数据源都会起作用
    */
    @Transactional (transactionManager = "mapper1TransactionManager")
    public User sel(Integer id){
          String key = "user_"+id;//redis中存储的是k-v，定义一个key
          ValueOperations<String,User> operations = redisTemplate.opsForValue();//用来操作字符串
          boolean haskey = redisTemplate.hasKey(key);
          logger.info("id1:{},haskey:{}",id,haskey);
          if(haskey){
              long startTime = System.currentTimeMillis();
              User user = operations.get(key);
              long times = System.currentTimeMillis()-startTime;
              logger.info("从缓存中获取数据时间：{}",times);
              System.out.println("----从缓存中获取数据----"+user.getUserName()+"----");
              //******************  用来测试事务处理的
//              Person person =new Person();
//              person.setId(34);
//              person.setName("小何");
//              personMapper.insertPerson(person);
//              int i = 10/0;

              //****************
              return user;
          }else{
              long startTime = System.currentTimeMillis();
              User user = new User();
              user = userMapper.sel(id);
              long times = System.currentTimeMillis()-startTime;
              logger.info("从数据表中获取数据时间：{}",times);//就是为了比较时间
              System.out.println("-----从数据表中获取数据---"+user.getUserName()+"----");
              //要是从数据表里获取数据，则把获的的数据写入缓存
              operations.set(key,user,10, TimeUnit.HOURS);
              //******************  用来测试事务处理的
//              Person person =new Person();
//              person.setId(34);
//              person.setName("小何");
//              personMapper.insertPerson(person);
//              int i = 10/0;
              //****************
              return user;
          }
    }

    /**redis
     * 查询多个用户策略：先在缓存中查找来获取结果，没有的话从数据表中获取结果，再把结果存入缓存，
     * @param user
     * @return
     */
     public List<User> selectByUser(User user){

                 String key ="user_"+user.getUserName();
                 boolean haskey = redisTemplate.hasKey(key);
                 ListOperations listOperations = redisTemplate.opsForList();//这里就是ForList了
         if(haskey){
                List<User> list = new ArrayList<User>();
                list = listOperations.range(key,0,-1);//通过rangge( k key,long start,long end)来获取指定范围的集合值
                logger.info("---从缓存中获取值---");
             return list;
         }else{
                List<User> list = userMapper.selectByUser(user);
                logger.info("---从数据表中获取值----");
                listOperations.leftPushAll(key,list);//System.out.println("通过leftPushAll(K key, Collection<V> values)方法以集合的方式向左边批量添加元素:" + list);
             return list;
         }

     }

    /**redis
     * 插入一个用户的策略，先把一个用户插入数据库，再放入缓存
     * @param user
     * @return
     */
     public int insertUser(User user){
         int result = userMapper.insertUser(user);//这里一定要这条先，假设你是使得id自动增长，当你传入id是已经存在的，这条就会改变user的id
         String key = "user_"+user.getId();//这里就会获得刚放进去数据库user的id
        boolean haskey =  redisTemplate.hasKey(key);
         if(result > 0){
            ValueOperations valueOperations =  redisTemplate.opsForValue();
             User userI = userMapper.sel(user.getId());
            if(!haskey && userI != null){
                String newKey = "user_"+userI.getId();
                valueOperations.set(newKey,userI,10,TimeUnit.HOURS);
                logger.info("放入缓存成功"+user.getId());
            }else{
                logger.error("已有该条记录或者对象为空");
            }
         }else{
             logger.error("插入有误");
         }
         return result;
     }


    /**redis
     * 删除一个用户策略：先把缓存删掉，再将数据库删掉
     * @param user
     * @return
     */
     public int deleteUser(User user){
             String keyName = "user_"+user.getUserName();
             boolean haskeyName = redisTemplate.hasKey(keyName);
             String keyId = "user_" +user.getId();
             boolean haskeyId = redisTemplate.hasKey(keyId);
         if(haskeyName){
             redisTemplate.delete(keyName);
             logger.info("已经进入了删除keyName");
         }
         if(haskeyId){
             redisTemplate.delete(keyId);
             logger.info("已经进入了删除keyId");
         }
         return  userMapper.deleteUser(user);
     }

    /**redis
     * 更新用户策略：先把数据放入数据库，然后删除以前的缓存，添加新的缓存，我觉得就是要把查询操作一遍，不就进去缓存了嘛
     * @param user
     * @return
     */
     public int updateUser(User user){
        String key = "user_"+user.getId();
        int result =  userMapper.updateUser(user);
       boolean haskey =  redisTemplate.hasKey(key);
        if(result > 0){
           ValueOperations<String ,User> operations =  redisTemplate.opsForValue();
            if(haskey){
                redisTemplate.delete(haskey);
                logger.info("删除旧的缓存");
                }
            User userU = userMapper.sel(user.getId());
            if(!ObjectUtils.isEmpty(userU)) {
                operations.set(key,userU,10,TimeUnit.HOURS);
            }
        }

        return result;
     }


    /**
     * 删除一个缓存
     * @return
     */
    public void deleteCach(User user){
        String keyId = "user_"+user.getId();
        boolean hasKeyId = redisTemplate.hasKey(keyId);
        String  keyName = "user_"+user.getUserName();
        boolean haskeyName = redisTemplate.hasKey(keyName);

        if(hasKeyId){
            redisTemplate.delete(keyId);
        }
        if(haskeyName){
            redisTemplate.delete(keyName);
        }
    }




    public int insertUserList(){
    List<User> list=new ArrayList<>();
         User [] user =new User[200];
        for(int i=0;i<50;i++){

           user[i]=new User();
            user[i].setId(i);
            user[i].setUserName("小流"+i*random());
            user[i].setPassword(i+"5");
            user[i].setRealName("小笑"+i);
            list.add(user[i]);
        }
        return userMapper.insertUserList(list);
     }
     public User selectLogin(User user){

        return  userMapper.selectLogin(user);
     }

     public User selectByName (String userName){
        return userMapper.selectByName(userName);
     }

    /**
     * 查询mybatis_table所有
     * @return
     */
     public List<User> selectAll(){

       return  userMapper.selectAll();

     }

}
