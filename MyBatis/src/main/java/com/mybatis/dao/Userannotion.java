package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface Userannotion {

    @Select("select * from person where id = #{id}")
    public User getuserbyid(Integer id);
    @Update("update person set name = #{name},age = #{age} where id = #{id}")
    public int update(User user);
    @Delete("delete from person where id = #{id}")
    public int delete(Integer id);
    @Insert("insert into person (name , age) values (#{name},#{age})")
    public int insert(User user);
}
