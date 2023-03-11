package org.easyquery.springbootdemo.controller;

import org.easy.query.core.api.client.EasyQuery;
import org.easyquery.springbootdemo.domain.TestUserMysql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: EasyQueryController.java
 * @Description: 文件说明
 * @Date: 2023/3/11 14:37
 * @Created by xuejiaming
 */
@RestController
@RequestMapping("/easy-query")
public class EasyQueryController {
    @Autowired
    private EasyQuery easyQuery;

    @GetMapping("/sayHello")
    public Object sayHello() {
        Logger logger = LoggerFactory.getLogger(EasyQueryController.class);
        logger.debug("123");
        TestUserMysql testUserMysql = easyQuery.query(TestUserMysql.class)
                .firstOrNull();
        return testUserMysql;
    }

    @GetMapping("/sayHello1")
    public Object sayHello1() {
        TestUserMysql testUserMysql1 = new TestUserMysql();
        testUserMysql1.setId("123321123321");
        testUserMysql1.setAge(1);
        testUserMysql1.setName("xxx");
        easyQuery.insert(testUserMysql1).executeRows();
        return testUserMysql1;
    }

    @GetMapping("/sayHello2")
    @Transactional(rollbackFor = Exception.class)
    public Object sayHello2() {
        TestUserMysql testUserMysql1 = new TestUserMysql();
        testUserMysql1.setId("123321123321xxx");
        testUserMysql1.setAge(1);
        testUserMysql1.setName("xxx");
        easyQuery.insert(testUserMysql1).executeRows();
        throw new RuntimeException("错误了");
    }

    @GetMapping("/sayHello3")
    public Object sayHello3() {
        TestUserMysql testUserMysql = easyQuery.query(TestUserMysql.class).whereId("123321123321xxx").firstOrNull();
        return testUserMysql;
    }
    @GetMapping("/sayHello4")
    public Object sayHello4() {
        TestUserMysql testUserMysql = easyQuery.query(TestUserMysql.class).whereId("123321123321").firstOrNull();
        return testUserMysql;
    }
}