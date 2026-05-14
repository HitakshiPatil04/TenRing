package services;

import models.MatchSession;
import models.Shot;

public class AnalyticsEngine {

    // 1. Calculate Total Score (Standard Loop Example)
    public double calculateTotalScore(MatchSession session) {
        double total = 0.0;
        for (Shot shot : session.getShots()) {
            total += shot.getScore();
        }
        return total;
    }

    // 2. Calculate Decimal Average (Java 8 Streams Example - Interviewer Bait!)
    public double calculateAverage(MatchSession session) {
        if (session.getShots().isEmpty()) {
            return 0.0;
        }
        
        // Using Streams makes this much cleaner
        double sum = session.getShots().stream()
                            .mapToDouble(Shot::getScore)
                            .sum();
                            
        return sum / session.getShots().size();
    }

    // 3. Count Inner Tens (Shots >= 10.4)
    public long countInnerTens(MatchSession session) {
        return session.getShots().stream()
                      .filter(shot -> shot.getScore() >= 10.4)
                      .count();
    }
}