package com.tongyuan.distributeFrame.base;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangcy on 2018/2/12
 */
public class BaseModel implements Serializable{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
