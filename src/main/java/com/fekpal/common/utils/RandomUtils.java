package com.fekpal.common.utils;

import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by APone on 2018/2/9 2:27.
 * 随机数工具
 */
public class RandomUtils {

    /**
     * 生成盐
     * @return 盐
     */
    public static String createSalt(){
        Random RANDOM = new SecureRandom();
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return new BASE64Encoder().encode(salt);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(createSalt());
    }
}
