package com.adwyxx.cms.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 15:52
 */
public class TokenUtil {

    /**
    * @description : 生成Token
    * @author : Leo.W
    * @date : 2019/3/14 15:55
    * @return : Token
    **/
    public static String generateToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + UUID.randomUUID().toString().replace("-","");
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
