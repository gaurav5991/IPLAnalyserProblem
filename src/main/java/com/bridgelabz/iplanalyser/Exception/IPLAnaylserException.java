package com.bridgelabz.iplanalyser.Exception;

public class IPLAnaylserException extends Exception {
    public enum ExceptionType{
        INVALID_FILE_PATH,INVALID_CLASS_TYPE
    }
    ExceptionType type;

    public IPLAnaylserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
