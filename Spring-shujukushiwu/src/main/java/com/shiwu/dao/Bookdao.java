package com.shiwu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Bookdao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //扣除人的钱
    public void kou(String name,int money){
        String sql = "update user set money=money-? where name=?";
        jdbcTemplate.update(sql,money,name);

    }

    //扣除书的库存

    public void koushu(int id){
        String sql = "update book_stock set stock=stock-1 where id=?";
        jdbcTemplate.update(sql, id);
    }

    //获取书的价格
    public int price(int id){
        String sql = "select price from book where id=?";
        int a =jdbcTemplate.queryForObject(sql, Integer.class, id);
        return a;
    }

    //修改书的价格
    public void xungaishu(int id){
        String sql = "update book set price=200 where id=?";
        jdbcTemplate.update(sql,id);

    }
}
