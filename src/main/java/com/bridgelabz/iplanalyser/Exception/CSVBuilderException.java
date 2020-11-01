package com.bridgelabz.iplanalyser.Exception;

public class CSVBuilderException extends Exception {
    public enum ExceptionType {
        IPL_FILE_PROBLEM, UNABLE_TO_PARSE
    }

    public ExceptionType type;

    public CSVBuilderException(String message, CSVBuilderException.ExceptionType type) {
        super(message);
        this.type = type;
    }

}