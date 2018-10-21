package com.tw.conference.track.management.scheduler;

import com.tw.conference.track.management.domain.Conference;
import com.tw.conference.track.management.domain.Talk;
import com.tw.conference.track.management.util.Constants;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Forest.lin on 2018/10/20.
 * Schedule the tracks with long duration talks first strategy
 */
public class LongDurationFirstScheduler implements TrackScheduler {
    @Override
    public void schedule(Conference conference) {

        int trackCountIndex = 0;
        int startIndex = 0;

        List<Talk> trackTalks = conference.getTrackTalks();

        // LongDurationFirst Strategy
        // Sort all talks based on the talk's duration in descending order.
        Collections.sort(trackTalks, new Comparator<Talk>() {
            @Override
            public int compare(Talk o1, Talk o2) {
                if(o1.getMinutes() < o2.getMinutes())
                    return 1;
                else
                    return -1;
            }
        });

        while (trackCountIndex < conference.getCountTrack()) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.HOUR, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.AM_PM, Calendar.AM);

            int sum180 = Constants.MORNING_SESSION_MINUTES;
            int sum240 = Constants.AFTERNOON_SESSION_MINUTES;

            int TalkIndex;

            String sessionTime;
            String SessionTitle;


            for (TalkIndex = startIndex; TalkIndex < conference.getCountTalks(); TalkIndex++) {

                // Fill the morining session
                if (sum180 >= trackTalks.get(TalkIndex).getMinutes()) {
                    sum180 = sum180 - trackTalks.get(TalkIndex).getMinutes();
                    sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(TalkIndex).getTitle() + " "
                            + this.getMinuteDescription(trackTalks.get(TalkIndex).getMinutes());
                    trackTalks.get(TalkIndex).setTitle(sessionTime);
                    cal.add(Calendar.MINUTE, trackTalks.get(TalkIndex).getMinutes());
                    SessionTitle = "Track" + " " + (trackCountIndex + 1);
                    trackTalks.get(TalkIndex).setTrackTitle(SessionTitle);
                }
                if (sum180 < trackTalks.get(TalkIndex).getMinutes())
                    break;

                if (sum180 > 0)
                    continue;

                if (sum180 <= 0)
                    break;
            }

            trackTalks.get(TalkIndex).setLunchIndicator(true);
            sessionTime = "12:00 PM" + " " + "Lunch";
            trackTalks.get(TalkIndex).setLunchTitle(sessionTime);
            cal.add(Calendar.MINUTE, 60);

            TalkIndex++;

            for (; TalkIndex < conference.getCountTalks(); TalkIndex++) {
                // Fill the afternoon session
                if (sum240 >= trackTalks.get(TalkIndex).getMinutes()) {
                    sum240 = sum240 - trackTalks.get(TalkIndex).getMinutes();
                    sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(TalkIndex).getTitle() + " "
                            + this.getMinuteDescription(trackTalks.get(TalkIndex).getMinutes());
                    trackTalks.get(TalkIndex).setTitle(sessionTime);
                    cal.add(Calendar.MINUTE, trackTalks.get(TalkIndex).getMinutes());
                    SessionTitle = "Track" + " " + (trackCountIndex + 1);
                    trackTalks.get(TalkIndex).setTrackTitle(SessionTitle);
                }
                if (sum240 < trackTalks.get(TalkIndex).getMinutes())
                    break;

                if (sum240 > 0)
                    continue;

                if (sum240 <= 0)
                    break;
            }


            if (conference.getCountTalks() == (TalkIndex))
                --TalkIndex;
            trackTalks.get(TalkIndex).setNetworkingIndicator(true);
            sessionTime = "5:00 PM" + " " + "Networking Event";
            trackTalks.get(TalkIndex).setNetworkingTitle(sessionTime);

            TalkIndex++;
            trackCountIndex++;

            startIndex = TalkIndex;
        }
    }

    private String getMinuteDescription(int minutes){
        if (minutes ==5){
            return Constants.LIGHTING;
        }
        else{
            return minutes + "min";
        }
    }

}
