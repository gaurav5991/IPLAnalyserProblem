package com.bridgelabz.iplanalyser.service;

import com.bridgelabz.iplanalyser.model.BattingPOJO;

import java.util.Comparator;

public class DataSorting implements Comparator<BattingPOJO> {
    public enum Order{AVG,SR}

    public Order sortingBy;

    public DataSorting(Order sortingBy) {
        this.sortingBy = sortingBy;
    }
    @Override
    public int compare(BattingPOJO b1,BattingPOJO b2) {
        switch(sortingBy) {
            case AVG:
                if(b1.getAvg().contains("-"))
                    b1.setAvg("0");
                return (int) (Double.parseDouble(b2.getAvg())-Double.parseDouble((b1.getAvg())));
            case SR:
                if(b1.getStrikeRate().contains("-"))
                    b1.setStrikeRate("0");
                return (int) (Double.parseDouble(b2.getStrikeRate())-Double.parseDouble((b1.getStrikeRate())));
        }
        return 0;
    }
}