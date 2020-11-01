package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.service.IPLAnalyser;
import com.bridgelabz.iplanalyser.Exception.IPLAnaylserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void givenBattingDataCSVFile_ShouldLoadBattingData() throws IPLAnaylserException {
        int totalRecords = iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        assertEquals(100, totalRecords);
    }

    @Test
    public void givenBowlingDataCSVFile_ShouldLoadBowlingData() throws IPLAnaylserException {
        int totalRecords = iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
        assertEquals(99, totalRecords);
    }
}
