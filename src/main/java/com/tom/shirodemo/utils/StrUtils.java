package com.tom.shirodemo.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author wc
 */
@Slf4j
public class StrUtils extends org.apache.commons.lang3.StringUtils{

    /**
     * 创建随机数
     *
     * @param length
     * @return
     */
    public static String createNoncestr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer res = new StringBuffer();
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            res.append(chars.charAt(rd.nextInt(chars.length() - 1))) ;
        }
        return res.toString();
    }

    /**
     * Base64解码参数
     * @param param
     * @return
     */
    public static String base64Decoder(String param) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            log.debug("base64decode init="+param);
            param = param.replaceAll("EQUALS", "=").replaceAll("SLASHS", "/").replaceAll("PLUSS", "+");
            param = new String(decoder.decodeBuffer(param),"UTF-8");
            log.debug("base64decode replace="+param);
        } catch (IOException e) {
            log.error("base64解码{}失败",param,e);
            return null;
        }
        return param;
    }

    /**
     * Base64编码参数
     * @param param
     * @return
     */
    public static String base64Encode(String param) {
        try {
            final Base64.Encoder encoder = Base64.getEncoder();
            final byte[] paramByte = param.getBytes("UTF-8");
            //编码
            param = new String(encoder.encode(paramByte),"UTF-8");
            log.debug("base64encode init="+param);
            param = param.replaceAll("=", "EQUALS").replaceAll("/", "SLASHS").replaceAll("[\\s*\t\n\r]", "").replaceAll("/+", "PLUSS");
            log.debug("base64encode replace="+param);
        } catch (Exception e) {
            log.error("base64编码{}失败",param,e);
        }
        return param;
    }

    /**
     * 拼接字符串
     * @param splitCode
     * @param strs
     * @return
     */
    public static String concat(String splitCode,String... strs){
        StringBuffer sb = new StringBuffer();
        for (String str: strs) {
            sb.append(str).append(splitCode);
        }
        return sb.substring(0,sb.length()-1);
    }



    public static String createNoncestr() {
        return createNoncestr(6);
    }


}