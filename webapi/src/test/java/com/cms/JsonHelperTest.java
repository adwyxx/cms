package com.cms;

import com.cms.entities.User;
import com.cms.utils.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: webapi
 * @description: JsonHelper类单元测试
 * @author: Leo
 * @create: 2018-11-09 15:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonHelperTest {
    @Test
    public void SerializeObject() throws JsonProcessingException {
        User user = new User(1,"leo","Leo","123","leo@gmail.com","18801012424");
        String json = JsonHelper.SerializeObject(user);
        System.out.print(json);
    }

}
