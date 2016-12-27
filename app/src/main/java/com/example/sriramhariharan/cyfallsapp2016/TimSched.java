package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 3/30/16.
 */
public class TimSched {

    private String period;
    private String time;
    private String roomnumb;
    public TimSched(String perod,String tie){
        period = perod;
        time = tie;
    }

    public String getRoomnumb() {
        return roomnumb;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return period + " "+time;
    }
}
