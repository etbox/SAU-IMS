package com.fekpal.web.model;

import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

/**
 * Created by APone on 2018/2/28 19:00.
 */
@Component
public class PageList extends BaseModel {

    private static final long serialVersionUID = -5864235926106437627L;

    private Integer offset;

    private Integer limit;

    public PageList() {
        this.offset = 1;
        this.limit = 1000000;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
