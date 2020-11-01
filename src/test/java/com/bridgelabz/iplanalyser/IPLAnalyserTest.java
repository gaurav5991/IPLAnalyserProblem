package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.model.BattingPOJO;
import com.bridgelabz.iplanalyser.service.DataSorting;
import com.bridgelabz.iplanalyser.service.IPLAnalyser;
import com.bridgelabz.iplanalyser.Exception.IPLAnalyserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IPLAnalyserTest {
    private static final String BATTING_DATA_PATH = "C:\\Users\\User\\IdeaProjects\\IPLAnalyserProblem\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String BOWLING_DATA_PATH = "C:\\Users\\User\\IdeaProjects\\IPLAnalyserProblem\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    private IPLAnalyser iplAnalyser;

    @Before
    public void init() {
        iplAnalyser = new IPLAnalyser();
    }

    /*TestCase for printing Welcome Message*/
    @Test
    public void givenWelcomeMessageShouldReturnTrue() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        boolean message = iplAnalyser.printWelcomeMessage();
        Assert.assertTrue(message);
    }

    /*Test Case to check the number of entries in Batting data csv file*/
    @Test
    public void givenBattingDataCSVFile_ShouldLoadBattingData() throws IPLAnalyserException {
        int totalRecords = iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        assertEquals(100, totalRecords);
    }

    /*Test Case to check the number of entries in Bowling data csv file*/
    @Test
    public void givenBowlingDataCSVFile_ShouldLoadBowlingData() throws IPLAnalyserException {
        int totalRecords = iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
        assertEquals(99, totalRecords);
    }

    /*Test Case to Sort the Batting Data by Average should return Highest Average*/
    @Test
    public void givenBattingData_WhenSortedByAvg_ShouldReturnHighestAvgFirst() throws IPLAnalyserException {
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.AVG);
        assertEquals("83.2", sortedBattingList.get(0).getAvg());
    }

    /*Test Case to Sort the Batting Data by Strike Rate should return Highest Strike Rate*/
    @Test
    public void givenBattingData_WhenSortedBySR_ShouldReturnHighestSRFirst() throws IPLAnalyserException {
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.SR);
        assertEquals("333.33", sortedBattingList.get(0).getStrikeRate());
    }

    /*Test Case to Sort the Batting Data by Maximum Fours and Sixes*/
    @Test
    public void givenBattingData_WhenSortedByBoundaries_ShouldReturnHighestTotalBoundaries() throws IPLAnalyserException {
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.BOUNDARIES);
        int totalBoundaries = Integer.parseInt(sortedBattingList.get(0).getFours()) + Integer.parseInt(sortedBattingList.get(0).getSixes());
        assertEquals(83, totalBoundaries);
    }

    /*Test Case to Sort the Batting Data by Highest Strike Rate with Boundaries*/
    @Test
    public void givenBattingData_WhenSortedByStrikeRateAndBoundaries_ShouldReturnProperList() throws IPLAnalyserException {
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.SR_AND_BOUNDARIES);
        assertEquals("Ishant Sharma", sortedBattingList.get(0).getPlayer());
    }

    /*Test Case to Sort the Batting Data by Highest Strike Rate and best Average */
    @Test
    public void givenBattingData_WhenSortedByAvgAndStrikeRate_ShouldReturnSortedList() throws IPLAnalyserException {
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.AVG_AND_SR);
        assertEquals("MS Dhoni", sortedBattingList.get(0).getPlayer());
    }

    /*Test Case to Sort the Batting Data by maximum runs with highest average */
    @Test
    public void givenBattingData_WhenSortedByRunsAndAvg_ShouldReturnSortedList() throws IPLAnalyserException {
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.RUNS_AND_AVG);
        assertEquals("David Warner", sortedBattingList.get(0).getPlayer());
    }
}
