package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchSession {
    private String sessionId;
    private LocalDate date;
    private List<Shot> shots;

    // Constructor
    public MatchSession(String sessionId) {
        this.sessionId = sessionId;
        this.date = LocalDate.now(); // Automatically sets to today's date
        this.shots = new ArrayList<>(); // Initializes the empty list
    }

    // Getters
    public String getSessionId() {
        return sessionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Shot> getShots() {
        return shots;
    }

    // Behavior Method: Adding a single shot to the session
    public void addShot(Shot shot) {
        if (shots.size() < 60) {
            shots.add(shot);
        } else {
            System.out.println("Match complete. Cannot add more than 60 shots.");
        }
    }

    @Override
    public String toString() {
        return "Session ID: " + sessionId + " | Date: " + date + " | Total Shots Logged: " + shots.size();
    }
}