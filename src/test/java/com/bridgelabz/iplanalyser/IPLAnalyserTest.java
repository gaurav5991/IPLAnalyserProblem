package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.model.BattingPOJO;
import com.bridgelabz.iplanalyser.model.BowlingPOJO;
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
    List<BattingPOJO> sortedBattingList;
    List<BowlingPOJO> sortedBowlingList;

    @Before
    public void init() throws IPLAnalyserException {
        iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
    }

    /*TestCase for printing Welcome Message*/
    @Test
    public void givenWelcomeMessageShouldReturnTrue() {
        iplAnalyser = new IPLAnalyser();
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
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.BAT_AVG, "Batsman");
        assertEquals("83.2", sortedBattingList.get(0).getAvg());
    }

    /*Test Case to Sort the Batting Data by Strike Rate should return Highest Strike Rate*/
    @Test
    public void givenBattingData_WhenSortedBySR_ShouldReturnHighestSRFirst() throws IPLAnalyserException {
        List<BattingPOJO> sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.BAT_SR, "Batsman");
        assertEquals("333.33", sortedBattingList.get(0).getStrikeRate());
    }

    /*Test Case to Sort the Batting Data by Maximum Fours and Sixes*/
    @Test
    public void givenBattingData_WhenSortedByBoundaries_ShouldReturnHighestTotalBoundaries() throws IPLAnalyserException {
        sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.BOUNDARIES, "Batsman");
        int totalBoundaries = Integer.parseInt(sortedBattingList.get(0).getFours()) + Integer.parseInt(sortedBattingList.get(0).getSixes());
        assertEquals(83, totalBoundaries);
    }

    /*Test Case to Sort the Batting Data by Highest Strike Rate with Boundaries*/
    @Test
    public void givenBattingData_WhenSortedByStrikeRateAndBoundaries_ShouldReturnProperList() throws IPLAnalyserException {
        sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.SR_AND_BOUNDARIES, "Batsman");
        assertEquals("Ishant Sharma", sortedBattingList.get(0).getPlayer());
    }

    /*Test Case to Sort the Batting Data by Highest Strike Rate and best Average */
    @Test
    public void givenBattingData_WhenSortedByAvgAndStrikeRate_ShouldReturnSortedList() throws IPLAnalyserException {
        sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.AVG_AND_SR, "Batsman");
        assertEquals("MS Dhoni", sortedBattingList.get(0).getPlayer());
    }

    /*Test Case to Sort the Batting Data by maximum runs with highest average */
    @Test
    public void givenBattingData_WhenSortedByRunsAndAvg_ShouldReturnSortedList() throws IPLAnalyserException {
        sortedBattingList = iplAnalyser.getSortedList(DataSorting.Order.RUNS_AND_AVG, "Batsman");
        assertEquals("David Warner", sortedBattingList.get(0).getPlayer());
    }

    /*Test Case to Sort the Bowling Data by average should return best Average */
    @Test
    public void givenBowlingData_WhenSortedByAvg_ShouldReturnBestAvgFirst() throws IPLAnalyserException {
        sortedBowlingList = iplAnalyser.getSortedList(DataSorting.Order.BOWL_AVG, "Bowler");
        assertEquals("11", sortedBowlingList.get(0).getAvg());
    }

    /*Test Case to Sort the Bowling Data by Best Strike of Bowlers */
    @Test
    public void givenBowlingData_WhenSortedByStrikeRate_ShouldReturnBestStrikeRateFirst() throws IPLAnalyserException {
        sortedBowlingList = iplAnalyser.getSortedList(DataSorting.Order.BOWL_SR, "Bowler");
        assertEquals("8.66", sortedBowlingList.get(0).getStrikeRate());
    }

    /*Test Case to Sort the Bowling Data by Best Economy of Bowlers */
    @Test
    public void givenBowlingData_WhenSortedByEconomy_ShouldReturnBestEconomyBowlerFirst() throws IPLAnalyserException {
        iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
        sortedBowlingList = iplAnalyser.getSortedList(DataSorting.Order.ECONOMY, "Bowler");
        assertEquals("4.8", sortedBowlingList.get(0).getEconomy());
    }
    /*Test Case to Sort the Bowling Data by Best Bowling Strike Rate and 5W or 4W haul of Bowlers */
    @Test
    public void givenBowlingData_WhenSortedBySRandWicketHauls_ShouldReturnBestSortedList() throws IPLAnalyserException {
        iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
        sortedBowlingList = iplAnalyser.getSortedList(DataSorting.Order.BOWL_SR_AND_WICKET_HAULS,"Bowler");
        assertEquals("8.66", sortedBowlingList.get(0).getStrikeRate());
    }
}
