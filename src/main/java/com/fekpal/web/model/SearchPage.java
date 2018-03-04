package com.fekpal.web.model;

import org.springframework.stereotype.Component;

/**
 * Created by APone on 2018/3/4 15:16.
 */
@Component
public class SearchPage extends PageList {

    private static final long serialVersionUID = -4855087708286906790L;

    private String key;

    public SearchPage() {
        super();
        this.key = "";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
