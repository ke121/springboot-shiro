package com.tom.shirodemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: WC
 * @Version: 1.0
 * @Create Time: 2018/10/11
 * @Modify Time: 2018/10/11
 **/
@Slf4j
public class RequestUtils {
    /**
     *将post请求的json字符串格式的参数转为JSONObject
     */
    public static String getPostParams(HttpServletRequest request){
        StringBuffer sb = new StringBuffer() ;
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = request.getInputStream();
            br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            if(sb.toString().length()<=0){
                return null;
            }else {
                return sb.toString();
            }
        }catch (Exception e){
            log.error("获取REQUEST请求参数出错",e);
            return null;
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 获取GET请求参数
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SortedMap<String, String> getParas(HttpServletRequest request) {
        SortedMap<String, String> queryParas = new TreeMap<String, String>();
        try {
            String parasKey;
            String parasValue;
            Enumeration<String> enumeration = request.getParameterNames();
            //参数里面找不到就到Body里找，适配@RequestBody注解
            if (enumeration.hasMoreElements()) {
                while (enumeration.hasMoreElements()) {
                    parasKey = (String) enumeration.nextElement();
                    parasValue = URLDecoder.decode(request.getParameter(parasKey),"UTF-8");
                    queryParas.put(parasKey, parasValue);
                }
            } else {
                try {
                    BufferedReader br = request.getReader();
                    String temp = null;
                    StringBuffer body = new StringBuffer();
                    while ((temp = br.readLine()) != null) {
                        body.append(temp);
                    }
                    if(body.length() == 0){
                        return queryParas;
                    }
                    JSONObject json = new JSONObject(body.toString());
                    Iterator it = json.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        String value = json.isNull(key)?null:json.getString(key);
                        queryParas.put(key, value);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return queryParas;
    }


}
