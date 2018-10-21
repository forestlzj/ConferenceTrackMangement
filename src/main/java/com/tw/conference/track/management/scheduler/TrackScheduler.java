package com.tw.conference.track.management.scheduler;

import com.tw.conference.track.management.domain.Conference;
import com.tw.conference.track.management.domain.Talk;

import java.util.List;

/**
 * Created by Forest.lin on 2018/10/20.
 * Track Scheduler Interface
 */
public interface TrackScheduler {
    public void schedule(Conference conference);
}
