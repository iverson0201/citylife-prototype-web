package com.citylife.trackup.backend.domain.metrics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Meter {

    public Meter(String name) {
        this.name = name;
    }

    private String name;
    private double meanRate;
    private double oneMinuteRate;
    private double fiveMinuteRate;
    private double fifteenMinuteRate;
    private long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMeanRate() {
        return meanRate;
    }

    public void setMeanRate(double meanRate) {
        this.meanRate = meanRate;
    }

    public double getOneMinuteRate() {
        return oneMinuteRate;
    }

    public void setOneMinuteRate(double oneMinuteRate) {
        this.oneMinuteRate = oneMinuteRate;
    }

    public double getFiveMinuteRate() {
        return fiveMinuteRate;
    }

    public void setFiveMinuteRate(double fiveMinuteRate) {
        this.fiveMinuteRate = fiveMinuteRate;
    }

    public double getFifteenMinuteRate() {
        return fifteenMinuteRate;
    }

    public void setFifteenMinuteRate(double fifteenMinuteRate) {
        this.fifteenMinuteRate = fifteenMinuteRate;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
