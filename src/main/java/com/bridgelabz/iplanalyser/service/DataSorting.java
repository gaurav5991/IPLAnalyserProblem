package com.bridgelabz.iplanalyser.service;

import com.bridgelabz.iplanalyser.model.BattingPOJO;
import com.bridgelabz.iplanalyser.model.BowlingPOJO;
import com.mysql.cj.protocol.x.OkBuilder;

import java.util.Comparator;

public class DataSorting<T> implements Comparator<T> {
    public enum Order{BAT_AVG,BAT_SR,BOUNDARIES,SR_AND_BOUNDARIES,AVG_AND_SR,RUNS_AND_AVG,BOWL_AVG}

    public Order sortingBy;

    public DataSorting(Order sortingBy) {
        this.sortingBy = sortingBy;
    }
    @Override
    public int compare(T Object1,T Object2) {
        BattingPOJO bat1 = null,bat2 = null;
        BowlingPOJO bowl1 = null,bowl2 = null;
        if(Object1.getClass().equals(BattingPOJO.class)) {
            bat1= (BattingPOJO) Object1;
            bat2= (BattingPOJO) Object2;
        }else if(Object1.getClass().equals(BowlingPOJO.class)) {
            bowl1= (BowlingPOJO) Object1;
            bowl2= (BowlingPOJO)Object2;
        }
        switch(sortingBy) {
            case BAT_AVG:
                if(bat1.getAvg().contains("-"))
                    bat1.setAvg("0");
                return (int) (Double.parseDouble(bat2.getAvg())-Double.parseDouble((bat1.getAvg())));
            case BAT_SR:
                if(bat1.getStrikeRate().contains("-"))
                    bat1.setStrikeRate("0");
                return (int) (Double.parseDouble(bat2.getStrikeRate())-Double.parseDouble((bat1.getStrikeRate())));
            case BOUNDARIES:
                return (Integer.parseInt(bat2.getFours())+Integer.parseInt(bat2.getSixes()))
                        -(Integer.parseInt(bat1.getFours())+Integer.parseInt(bat1.getSixes()));
            case SR_AND_BOUNDARIES:
                if (bat1.getStrikeRate().contains("-"))
                    bat1.setStrikeRate("0");
                double value = Double.parseDouble(bat2.getStrikeRate()) - Double.parseDouble((bat1.getStrikeRate()));
                if (value == 0) {
                    return (Integer.parseInt(bat2.getFours()) + Integer.parseInt(bat2.getSixes()))
                            - (Integer.parseInt(bat1.getFours()) + Integer.parseInt(bat1.getSixes()));
                }
                value = setValue(value);
                return (int) value;
            case AVG_AND_SR:
                if (bat1.getAvg().contains("-"))
                    bat1.setAvg("0");
                value = (Double.parseDouble(bat2.getAvg()) - Double.parseDouble((bat1.getAvg())));
                if (value == 0)
                    return (int) (Double.parseDouble(bat2.getStrikeRate()) - Double.parseDouble((bat1.getStrikeRate())));
                value = setValue(value);
                return (int) value;
            case RUNS_AND_AVG:
                if(bat1.getAvg().contains("-"))
                    bat1.setAvg("0");
                value = Integer.parseInt(bat2.getRuns()) - Integer.parseInt(bat1.getRuns());
                if (value == 0) {
                    return (int) (Double.parseDouble(bat2.getAvg()) - Double.parseDouble((bat1.getAvg())));
                }
                value = setValue(value);
                return (int) value;
            case BOWL_AVG:
                if(bowl1.getAvg().contains("-"))
                    bowl1.setAvg("999999");
                return (int) (Double.parseDouble(bowl1.getAvg()) - Double.parseDouble((bowl2.getAvg())));
        }
        return 0;
    }
    public double setValue(double value) {
        if (value > 0)
            value = 1;
        else if (value < 0)
            value = -1;
        return value;
    }
}