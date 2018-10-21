package com.tw.conference.track.management;

import com.tw.conference.track.management.service.ConferenceService;

import org.junit.Test;

/**
 * Created by Forest.lin on 2018/10/20.
 */
public class AppTest {

    @Test
    public void main() throws Exception {
        ConferenceService conferenceService = new ConferenceService();
        String inputFile = "Talks.txt";
        conferenceService.createTracks(inputFile);
    }

}