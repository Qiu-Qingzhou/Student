package com.shiwu.server;

import com.shiwu.dao.Bookdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class Bookserver {
    @Autowired
    private Bookdao bookdao;

    //结账
    @Transactional(timeout = 3,readOnly = false,noRollbackFor = {ArithmeticException.class},rollbackFor = {FileNotFoundException.class})//事务管理通知符
    //timeout单位是秒，当方法超过规定的时间时，事务回滚
    // readOnly只能用于读数据库，可以加快速度，默认为false
    //noRollbackFor规定，发生什么异常不回滚
    //RollbackFor规定，发生什么异常回滚，默认编译时异常是不回滚的，通过设置可以让他回滚
    public void checkout(String name,int id)  {
        int price = bookdao.price(id);
        bookdao.kou(name,price);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bookdao.koushu(id);
        System.out.println("结账成功");
        //int a = 1/0;
        //InputStream inputStream = new FileInputStream("haha.txt");//编译时异常
    }

    //修改书的价格
    @Transactional
    public void gai(int id){
        bookdao.xungaishu(id);
        //int i = 1/0;
    }
}
