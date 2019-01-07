package com.adwyxx.cms.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: MD5加密
 * @Auther: Leo.W
 * @Date: 2019/1/7 17:31
 */
public class Md5Helper {

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        if(null==str || str.isEmpty())
        {
            return str;
        }
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
