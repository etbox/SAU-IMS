package com.fekpal.common.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by hasee on 2017/8/15.
 */
public class WebAppPath {

    /**
     * web项目根目录，斜杠
     */
    static final String WEB_APP_ROOT = System.getProperty("root").replace("\\", "/");
}
