package com.bridgelabz.iplanalyser.service;

import com.bridgelabz.iplanalyser.Exception.CSVBuilderException;
import com.bridgelabz.iplanalyser.Exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.adapter.CSVBuilderFactory;
import com.bridgelabz.iplanalyser.adapter.ICSVBuilder;
import com.bridgelabz.iplanalyser.model.BattingPOJO;
import com.bridgelabz.iplanalyser.model.BowlingPOJO;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class IPLAnalyser {
    List<BattingPOJO> battingList;
    List<BowlingPOJO> bowlingList;

    /*Method to print Welcome Message*/
    public boolean printWelcomeMessage() {
        System.out.println("Welcome to IPL Analyser Problem");
        return true;
    }

    /*Method to load csv File data */
    public int loadData(String filePath, String fileType) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            if (fileType.equals("Batting")) {
                try {
                    battingList = csvBuilder.getCSVFileList(reader, BattingPOJO.class);
                    return battingList.size();
                } catch (CSVBuilderException e) {
                    throw new IPLAnalyserException("Invalid class", IPLAnalyserException.ExceptionType.INVALID_CLASS_TYPE);
                }
            }
            if (fileType.equals("Bowling")) {
                try {
                    bowlingList = csvBuilder.getCSVFileList(reader, BowlingPOJO.class);
                    return bowlingList.size();
                } catch (CSVBuilderException e) {
                    throw new IPLAnalyserException("Invalid class", IPLAnalyserException.ExceptionType.INVALID_CLASS_TYPE);
                }
            } else
                throw new IPLAnalyserException("No such file", IPLAnalyserException.ExceptionType.NO_DATA);
        } catch (IOException e) {
            throw new IPLAnalyserException("Invalid file location", IPLAnalyserException.ExceptionType.INVALID_FILE_PATH);
        }
    }

    /*Generic Method to get Sorted List of Data*/
    public <T> List<T> getSortedList(DataSorting.Order order, String playerType) throws IPLAnalyserException {
        DataSorting sort = new DataSorting<>(order);
        List<T> sortedList;
        if (playerType.equals("Batsman")) {
            if (battingList == null || battingList.size() == 0)
                throw new IPLAnalyserException("No batting list data", IPLAnalyserException.ExceptionType.NO_DATA);
            sortedList = (List<T>) battingList;
        } else if (playerType.equals("Bowler")) {
            if (bowlingList == null || bowlingList.size() == 0)
                throw new IPLAnalyserException("No bowling list data", IPLAnalyserException.ExceptionType.NO_DATA);
            sortedList = (List<T>) bowlingList;
        } else
            throw new IPLAnalyserException("Wrong player type", IPLAnalyserException.ExceptionType.WRONG_PLAYER_TYPE);
        Collections.sort(sortedList, sort);
        System.out.println(sortedList);
        return sortedList;
    }
}