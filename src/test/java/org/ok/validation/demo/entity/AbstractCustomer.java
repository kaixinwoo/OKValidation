package org.ok.validation.demo.entity;

import org.ok.validation.annotation.NotEmpty;

public abstract class AbstractCustomer {

    @NotEmpty(errCode = "7777", errMsg = "无效的用户编号")
    protected String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
