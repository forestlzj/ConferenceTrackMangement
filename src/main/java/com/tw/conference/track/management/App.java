package com.tw.conference.track.management;

import com.tw.conference.track.management.service.ConferenceService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Forest.lin on 2018/10/20.
 */
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner( System.in );
        try {
            System.out.println( "### WELCOME TO THE CONFERENCE TRACK MANAGEMENT ###" );
            System.out.println("PLEASE INPUT THE TALK FILE");
            String inputFile = scanner.nextLine();
            ConferenceService conferenceService = new ConferenceService();
            conferenceService.createTracks(inputFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            scanner.close();
        }
    }
}
