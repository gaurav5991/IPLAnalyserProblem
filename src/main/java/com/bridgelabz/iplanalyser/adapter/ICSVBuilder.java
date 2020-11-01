package com.bridgelabz.iplanalyser.adapter;

import com.bridgelabz.iplanalyser.Exception.CSVBuilderException;

import java.io.Reader;
import java.util.List;

public interface ICSVBuilder<E> {
    List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;
}
