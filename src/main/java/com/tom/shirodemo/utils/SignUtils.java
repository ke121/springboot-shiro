package com.tom.shirodemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

@Slf4j
public class SignUtils {
    public static final String MD5 = "MD5";
    public static final String RSA = "RSA";
    public static final String RSA2 = "RSA2";

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
        return resultSb.toString();
    }


    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5Encode(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }


    /**
     * md5加密
     * @param origin
     * @param charsetname
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception exception) {
            log.error("MD5编码失败",exception);
        }
        return resultString;
    }

    /**
     * 对参数进行md5加密
     *
     * @param
     * @param parameters
     * @return
     * @throws Exception
     */
    public static String createSignStr(SortedMap<String, String> parameters){
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String,String>> es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> entry = (Map.Entry<String,String>) it.next();
            String k =  entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equalsIgnoreCase(k)
                    && !"key".equalsIgnoreCase(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String sign = null;
        try {
            log.debug("验签字段为:{}",sb.toString());
            sign = MD5Encode(sb.toString()).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    public static boolean isValidSign(SortedMap<String,String> map){
        if(map == null){
            return false;
        }
        String sign = map.get("sign");
        if(StrUtils.isNotBlank(sign)){
            map.remove("sign");
            String signStr = createSignStr(map);
            log.debug("valid sign={}",signStr);
            return sign.equalsIgnoreCase(signStr);
        }
        return false;
    }

    public static boolean isValidSign(HttpServletRequest request){
        SortedMap<String, String> paras = RequestUtils.getParas(request);
        return isValidSign(paras);
    }
}
