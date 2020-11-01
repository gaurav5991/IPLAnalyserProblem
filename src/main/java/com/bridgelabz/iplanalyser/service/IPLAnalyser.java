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

public class IPLAnalyser {
    List battingList;
    List bowlingList;
    /*Method to print Welcome Message*/
    public boolean printWelcomeMessage() {
        System.out.println("Welcome to IPL Analyser Problem");
        return true;
    }
    /*Method to load batting data */
    public int loadBattingData(String battingDataPath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(battingDataPath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            try {
                battingList = csvBuilder.getCSVFileList(reader, BattingPOJO.class);
            } catch (CSVBuilderException e) {
                throw new IPLAnalyserException("Invalid class", IPLAnalyserException.ExceptionType.INVALID_CLASS_TYPE);
            }
        } catch (IOException e) {
            throw new IPLAnalyserException("Invalid file location", IPLAnalyserException.ExceptionType.INVALID_FILE_PATH);
        }
        return battingList.size();
    }
    /*Method to load bowling data */
    public int loadBowlingData(String bowlingDataPath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(bowlingDataPath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            try {
                bowlingList = csvBuilder.getCSVFileList(reader, BowlingPOJO.class);
            } catch (CSVBuilderException e) {
                throw new IPLAnalyserException("Invalid class", IPLAnalyserException.ExceptionType.INVALID_CLASS_TYPE);
            }
        } catch (IOException e1) {
            throw new IPLAnalyserException("Invalid file location", IPLAnalyserException.ExceptionType.INVALID_FILE_PATH);
        }
        return bowlingList.size();
    }

    public List<BattingPOJO> getSortedData(DataSorting.Order order) throws IPLAnalyserException {
        if(battingList==null||battingList.size()==0) {
            throw new IPLAnalyserException("No batting list data", IPLAnalyserException.ExceptionType.NO_DATA);
        }
        DataSorting dataSorting = new DataSorting(order);
        List<BattingPOJO> sortedBattingList = battingList;
        Collections.sort(sortedBattingList, dataSorting);
        System.out.println(sortedBattingList);
        return sortedBattingList;
    }
}