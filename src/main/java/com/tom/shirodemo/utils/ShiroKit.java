package com.tom.shirodemo.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;
import java.util.UUID;

/**
 * shiro工具类
 *
 * @author dafei, Chill Zhuang
 */
public class ShiroKit {

    private static final String NAMES_DELIMETER = ",";

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 2;

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public static String md5(String credentials, String saltSource) {
        ByteSource salt = ByteSource.Util.bytes(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }

    /**
     * 获取随机盐值
     *
     * @param
     * @return
     */
    public static String getRandomSalt(String username) {
        String uuid = UUID.randomUUID().toString().replace("-", "") ;
        return username + uuid ;
    }
}
