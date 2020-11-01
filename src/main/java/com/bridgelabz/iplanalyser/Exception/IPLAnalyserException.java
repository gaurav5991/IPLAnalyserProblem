package com.bridgelabz.iplanalyser.Exception;

public class IPLAnalyserException extends Exception {
    public enum ExceptionType{
        INVALID_FILE_PATH,INVALID_CLASS_TYPE,NO_DATA, WRONG_PLAYER_TYPE
    }
    ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
