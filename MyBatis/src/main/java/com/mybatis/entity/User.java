package com.mybatis.entity;

import org.apache.ibatis.type.Alias;

@Alias("User")//批量命名别名时,他可以不使用类名,而指定特殊的别名
public class User {
    private String name;
    private Integer age;
    private Integer id;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
