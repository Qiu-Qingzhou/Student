package com.shiwu.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Muserver {

    @Autowired
    private Bookserver bookserver;

    //大事务套小事务，才设置传播级别
    @Transactional
    public void dashiwu(){

        bookserver.checkout("li",1);

        bookserver.gai(1);
    }
}
