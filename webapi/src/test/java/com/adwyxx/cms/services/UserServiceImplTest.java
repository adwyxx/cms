package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void getByID() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void save() {
    }

    @Test
    public void getByLogonNameAndPassword() {
        User user = userService.getByLogonNameAndPassword("admin","admin888");
        System.out.println(user==null?"null":user.toString());
    }

    @Test
    public void deleteById() {
    }
}