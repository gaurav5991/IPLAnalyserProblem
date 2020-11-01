package com.bridgelabz.iplanalyser.service;

import com.bridgelabz.iplanalyser.Exception.CSVBuilderException;
import com.bridgelabz.iplanalyser.Exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.adapter.CSVBuilderFactory;
import com.bridgelabz.iplanalyser.adapter.ICSVBuilder;
import com.bridgelabz.iplanalyser.model.BattingPOJO;
import com.bridgelabz.iplanalyser.model.BowlingPOJO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser<T> {
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
        sortedList = IPLAnalyser.filterList(sortedList, order);
        System.out.println(sortedList);
        return sortedList;
    }

    /*Method to filter batting data for zero hundreds and fifties with best average*/
    private static <T> List<T> filterList(List<T> list, DataSorting.Order order) {
        if (list.get(0).getClass().equals(BattingPOJO.class)) {
            List<BattingPOJO> filteredList = (List<BattingPOJO>) list;
            if (order.equals(DataSorting.Order.ZERO_100AND50_BEST_AVG))
                return (List<T>) filteredList.stream().filter(batting -> batting.getHundreds().equals("0") && batting.getFifties().equals("0")).collect(Collectors.toList());
            else
                return (List<T>) filteredList;
        } else {
            List<BowlingPOJO> filteredList = (List<BowlingPOJO>) list;
        }
        return list;
    }
}