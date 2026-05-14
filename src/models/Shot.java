package models;

public class Shot {
    private int shotNumber;
    private double score; // e.g., 10.4, 9.8

    // Constructor
    public Shot(int shotNumber, double score) {
        this.shotNumber = shotNumber;
        this.score = score;
    }

    // Getters and Setters
    public int getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(int shotNumber) {
        this.shotNumber = shotNumber;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        // Basic validation: A 10m air rifle shot cannot be above 10.9 or below 0.0
        if(score >= 0.0 && score <= 10.9) {
            this.score = score;
        } else {
            System.out.println("Invalid score. Must be between 0.0 and 10.9");
        }
    }

    @Override
    public String toString() {
        return "Shot " + shotNumber + ": " + score;
    }
}