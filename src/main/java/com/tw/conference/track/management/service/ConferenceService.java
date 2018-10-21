package com.tw.conference.track.management.service;

import com.tw.conference.track.management.domain.Conference;
import com.tw.conference.track.management.scheduler.LongDurationFirstScheduler;
import com.tw.conference.track.management.scheduler.TrackScheduler;
import com.tw.conference.track.management.util.Constants;

import java.io.IOException;

/**
 * Created by Forest.lin on 2018/10/20.
 */
public class ConferenceService {

    public void createTracks(String file) throws IOException {

        Conference conference = new Conference();
        conference.initTalks(file);
        //TODO The trackschedule strategy is configurate-able
        TrackScheduler longDurationFistStrategy = new LongDurationFirstScheduler();
        conference.scheduleTracks(longDurationFistStrategy);
        conference.printTracks();
    }
}
