package com.tongyuan.distributeFrame.demo.check.enums;

/**
 * Created by zhangcy on 2018/3/17
 */
public enum FlowStatus {
    Handling("handing"),
    Success("success"),
    UnSuccess("unSuccess")
    ;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    FlowStatus(String status) {
        this.status = status;
    }
}
