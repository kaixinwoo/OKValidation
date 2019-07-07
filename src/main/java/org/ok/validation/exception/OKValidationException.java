package org.ok.validation.exception;

import java.io.Serializable;

public class OKValidationException extends Exception implements Serializable {

    // 错误码
    private String errCode;
    // 错误信息
    private String errMsg;

    public OKValidationException() {
    }

    public static OKValidationException builder() {
        return new OKValidationException();
    }

    public OKValidationException errCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public OKValidationException errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "OKValidationException{" +
                "errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
