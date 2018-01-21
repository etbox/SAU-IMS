package com.fekpal.tool.msg.phone;

import com.fekpal.tool.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机工具类
 */
public class PhoneUtils {

    private static final String regex="";

    private static final Pattern pattern=Pattern.compile(regex);

    public static boolean isValid(String phone){
        if(StringUtils.isEmpty(phone)){
            return false;
        }
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }
}
