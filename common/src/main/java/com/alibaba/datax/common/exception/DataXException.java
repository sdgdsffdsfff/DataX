package com.alibaba.datax.common.exception;

import com.alibaba.datax.common.spi.ErrorCode;

public class DataXException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public DataXException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private DataXException(ErrorCode errorCode, String errorMessage,
                           Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage)
                + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static DataXException asDataXException(ErrorCode errorCode, String message) {
        return new DataXException(errorCode, message);
    }

    public static DataXException asDataXException(ErrorCode errorCode, String message,
                                                  Throwable cause) {
        if (cause instanceof DataXException) {
            return (DataXException) cause;
        }
        return new DataXException(errorCode, message, cause);
    }

    public static DataXException asDataXException(ErrorCode errorCode,
                                                  Throwable cause) {
        if (cause instanceof DataXException) {
            return (DataXException) cause;
        }
        return new DataXException(errorCode, getMessage(cause), cause);
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }


    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Throwable) {
            return ((Throwable) obj).getMessage();
        } else {
            return obj.toString();
        }
    }
}
