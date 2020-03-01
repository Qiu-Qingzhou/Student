package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface Userdao {

    public User getuserbyid(Integer id);
    public int update(User user);
    public int delete(Integer id);
    public int insert(User user);

    //用于非自增主键的数据库,全字段插入
    public int insert2(User user);

    //传入两个参数查询,不同于一个参数
    public User getuserbyidandname(@Param("id") Integer id, @Param("name") String name);

    @MapKey("id")
    public Map<Integer,User> getuserMap();
}
