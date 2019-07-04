package org.ok.validation.exception;

public class OKValidationException extends Exception {

    private String errCode;

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
