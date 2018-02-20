package com.tongyuan.distributeFrame.base;

import java.util.Date;

/**
 * Created by zhangcy on 2018/2/17
 *
 * BaseModel是更抽象的类，AbstractModel则提供了更多的属性，使用时可以根据需要选择继承BaseModel类或者继承AbstractModel类
 */
public class AbstractModel extends BaseModel{
    private Long id;
    private Boolean isDel;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
