package com.tw.conference.track.management.domain;

/**
 * Created by Forest.lin on 2018/10/20.
 * Talk class
 */
public class Talk {

    //variables
    private int minutes;
    private String title;
    private boolean lunchIndicator = false;
    private String networkingTitle;
    private boolean networkingIndicator = false;
    private String sessionTime;
    private String lunchTitle;
    private String trackTitle;

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLunchIndicator() {
        return lunchIndicator;
    }

    public void setLunchIndicator(boolean lunchIndicator) {
        this.lunchIndicator = lunchIndicator;
    }

    public String getNetworkingTitle() {
        return networkingTitle;
    }

    public void setNetworkingTitle(String networkingTitle) {
        this.networkingTitle = networkingTitle;
    }

    public boolean isNetworkingIndicator() {
        return networkingIndicator;
    }

    public void setNetworkingIndicator(boolean networkingIndicator) {
        this.networkingIndicator = networkingIndicator;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getLunchTitle() {
        return lunchTitle;
    }

    public void setLunchTitle(String lunchTitle) {
        this.lunchTitle = lunchTitle;
    }


    // constructor
    public Talk(int minutes,String title)
    {
        this.minutes = minutes;
        this.title = title;
    }
}
