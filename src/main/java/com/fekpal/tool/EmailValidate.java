package com.fekpal.tool;


/**
 * Created by hasee on 2017/8/18.
 * 邮箱合法性检测
 */
public class EmailValidate {

    /**
     * 检查邮箱合法性
     *
     * @param email String
     * @return boolean
     */
    public static boolean check(String email) {

        /*
        p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
        w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
        [a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
        [.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
        p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
        */
        String format = "\\w{1,15}\\w{1,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";

        return email.matches(format);
    }
}