package com.tw.conference.track.management.domain;

import com.tw.conference.track.management.scheduler.TrackScheduler;
import com.tw.conference.track.management.util.Constants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Forest.lin on 2018/10/20.
 * This is the conference class, which calculates total track minutes, number of tracks and number of talk from an input file
 * Then it will schedule the tracks with a default strategy (can be extened) and print the track accordingly.
 */
public class Conference {
    List<Talk> trackTalks;
    int totalTrackMinutes;
    int countTrack;
    int countTalks;

    public int getTotalTrackMinutes() {
        return totalTrackMinutes;
    }

    public void setTotalTrackMinutes(int totalTrackMinutes) {
        this.totalTrackMinutes = totalTrackMinutes;
    }

    public int getCountTrack() {
        return countTrack;
    }

    public void setCountTrack(int countTrack) {
        this.countTrack = countTrack;
    }

    public int getCountTalks() {
        return countTalks;
    }

    public void setCountTalks(int countTalks) {
        this.countTalks = countTalks;
    }

    public List<Talk> getTrackTalks() {
        return trackTalks;
    }

    public void setTrackTalks(List<Talk> trackTalks) {
        this.trackTalks = trackTalks;
    }


    /**
     * .
     * Read the  input from file, extract the title, total track count, prepare a trackTalk list to include all
     * talks object
     */

    public void initTalks(String fileName) throws IOException {
        int id = 0;
        int noOfTracks = 0;
        FileInputStream fstream = null;

        fstream = new FileInputStream(fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        int intMinutes;
        int totalMinutes = 0;

        //Read Input File line by line
        while ((strLine = br.readLine()) != null) {
            if (strLine.isEmpty())
                continue;

            id = id + 1;

            System.out.println(strLine);

            // Process the received line, extract title, minutes and if it has lightning instead of minutes value

            String Title = strLine.substring(0, strLine.lastIndexOf(" "));
            String MinutesString = strLine.substring(strLine.lastIndexOf(" ") + 1);
            String Minutes = strLine.replaceAll("\\D+", "");
            // convert lighting to 5 mins
            if (Constants.LIGHTING.equals(MinutesString)) {
                intMinutes = Constants.LIGHTING_MINUTE;

                totalMinutes = totalMinutes + intMinutes;
            } else {
                intMinutes = Integer.parseInt(Minutes);
                totalMinutes = totalMinutes + intMinutes;
            }

            // Create a Talk Object, Fill all the input values
            Talk talk = new Talk(intMinutes, Title);

            // Add this Talk Object to the List of Track Talks
            trackTalks.add(talk);

        }


        // Set the total no. of count talks.
        this.setCountTalks(id);

        // set total no. of minutes of talks.
        this.setTotalTrackMinutes(totalMinutes);

        // Calculate the no. of tracks
        Double totalMinutesInDouble = totalMinutes * 1.0;

        Double numberOfTracks = totalMinutesInDouble / Constants.TOTAL_CONFERENCE_TRACK_MINUTES;

        double fractionalPart = numberOfTracks % 1;
        double integralPart = numberOfTracks - fractionalPart;

        int leftMinutes = totalMinutes - (int) integralPart * Constants.TOTAL_CONFERENCE_TRACK_MINUTES.intValue();

        // if it comes 1.x - it will have 2 Tracks
        if (leftMinutes > 0) {
            noOfTracks = (int) integralPart + 1;
        } else {
            noOfTracks = (int) integralPart;
        }

        this.setCountTrack(noOfTracks);

        //Close the input stream
        br.close();
    }

    public void scheduleTracks(TrackScheduler trackScheduler) {
        trackScheduler.schedule(this);
    }

    /**
     * Print the scheduled tracks
     */
    public void printTracks() {

        String TrackTitle = "";

        for (int trackCountIndex = 0; trackCountIndex < trackTalks.size(); trackCountIndex++) {

            // Print the Track Title
            if (!TrackTitle.equals(trackTalks.get(trackCountIndex).getTrackTitle())) {
                System.out.println(trackTalks.get(trackCountIndex).getTrackTitle() + ":");
                System.out.println("");
                TrackTitle = trackTalks.get(trackCountIndex).getTrackTitle();
            }

            // Print the talk's title for this Track
            System.out.println(trackTalks.get(trackCountIndex).getTitle());

            if (trackTalks.get(trackCountIndex).isLunchIndicator()) {
                System.out.println(trackTalks.get(trackCountIndex).getLunchTitle());
            }

            if (trackTalks.get(trackCountIndex).isNetworkingIndicator()) {
                System.out.println(trackTalks.get(trackCountIndex).getNetworkingTitle());
                // for better format
                System.out.println("");
            }

        }
    }

    // constructor
    public Conference() {
        this.trackTalks = new ArrayList(20);
    }
}


