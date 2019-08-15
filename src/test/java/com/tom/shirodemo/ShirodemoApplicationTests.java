package com.tom.shirodemo;

import com.tom.shirodemo.utils.ShiroKit;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShirodemoApplicationTests {

    @Test
    public void contextLoads() {
        String salt = ShiroKit.getRandomSalt( "tom") ;
        System.out.println("盐值======" + salt );
        String password = ShiroKit.md5("123456",salt ) ;
        System.out.println("密码======" + password);
        System.out.println("============" + ByteSource.Util.bytes("admin3b41d727b0af4164a9454f6805ac36b9")) ;
    }
}
