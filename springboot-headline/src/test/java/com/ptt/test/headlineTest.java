package com.ptt.test;

import com.ptt.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: test
 * Package: com.ptt
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/25 17:15
 * @Version 1.0
 */
@SpringBootTest
public class headlineTest {
    @Autowired
    private JwtHelper jwtHelper;
    @Test
    public void testToken(){

        String token = jwtHelper.createToken(1L);
        Long userId = jwtHelper.getUserId(token);
        boolean expiration = jwtHelper.isExpiration(token);

        System.out.println("token = " + token);
        System.out.println("userId = " + userId);
        System.out.println("expiration = " + expiration);


    }
}
