package com.bridgelabz.iplanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class BowlingPOJO {
    @CsvBindByName(column = "POS")
    public String pos;
    @CsvBindByName(column = "PLAYER")
    public String player;
    @CsvBindByName(column = "Mat")
    public String matches;
    @CsvBindByName(column = "Inns")
    public String innings;
    @CsvBindByName(column = "Ov")
    public String overs;
    @CsvBindByName(column = "Runs")
    public String runs;
    @CsvBindByName(column = "Wkts")
    public String wickets;
    @CsvBindByName(column = "BBI")
    public String bbi;
    @CsvBindByName(column = "Avg")
    public String avg;
    @CsvBindByName(column = "Econ")
    public String economy;
    @CsvBindByName(column = "SR")
    public String strikeRate;
    @CsvBindByName(column = "4w")
    public String fourWickets;
    @CsvBindByName(column = "5w")
    public String fiveWickets;

    @Override
    public String toString() {
        return "Bowling [pos=" + pos + ", player=" + player + ", matches=" + matches + ", innings=" + innings
                + ", overs=" + overs + ", runs=" + runs + ", wickets=" + wickets + ", bbi=" + bbi + ", avg=" + avg
                + ", economy=" + economy + ", strikeRate=" + strikeRate + ", fourWickets=" + fourWickets
                + ", fiveWickets=" + fiveWickets + "]";
    }
}
