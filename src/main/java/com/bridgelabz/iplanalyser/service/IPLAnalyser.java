package com.bridgelabz.iplanalyser.service;

import com.bridgelabz.iplanalyser.Exception.CSVBuilderException;
import com.bridgelabz.iplanalyser.Exception.IPLAnaylserException;
import com.bridgelabz.iplanalyser.adapter.CSVBuilderFactory;
import com.bridgelabz.iplanalyser.adapter.ICSVBuilder;
import com.bridgelabz.iplanalyser.model.BattingPOJO;
import com.bridgelabz.iplanalyser.model.BowlingPOJO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IPLAnalyser {
    List battingList;
    List bowlingList;

    public boolean printWelcomeMessage() {
        System.out.println("Welcome to IPL Analyser Problem");
        return true;
    }

    public int loadBattingData(String battingDataPath) throws IPLAnaylserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(battingDataPath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            try {
                battingList = csvBuilder.getCSVFileList(reader, BattingPOJO.class);
            } catch (CSVBuilderException e) {
                throw new IPLAnaylserException("Invalid class", IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
            }
        } catch (IOException e) {
            throw new IPLAnaylserException("Invalid file location", IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
        }
        return battingList.size();
    }

    public int loadBowlingData(String bowlingDataPath) throws IPLAnaylserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(bowlingDataPath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            try {
                bowlingList = csvBuilder.getCSVFileList(reader, BowlingPOJO.class);
            } catch (CSVBuilderException e) {
                throw new IPLAnaylserException("Invalid class", IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
            }
        } catch (IOException e1) {
            throw new IPLAnaylserException("Invalid file location", IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
        }
        return bowlingList.size();
    }
}