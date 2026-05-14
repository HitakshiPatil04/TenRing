package main;

import models.MatchSession;
import models.Shot;
import services.AnalyticsEngine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLIApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnalyticsEngine engine = new AnalyticsEngine();

        System.out.println("=========================================");
        System.out.println("   TENRING PRECISION ANALYTICS SYSTEM    ");
        System.out.println("=========================================");

        System.out.print("Enter a new Session ID (e.g., MATCH-01): ");
        String sessionId = scanner.nextLine();
        
        // Instantiate our Session (The Data)
        MatchSession currentSession = new MatchSession(sessionId);
        
        boolean running = true;
        int shotCounter = 1;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Log a New Shot");
            System.out.println("2. View All Logged Shots");
            System.out.println("3. Calculate Total Match Score");
            System.out.println("4. Calculate Current Average");
            System.out.println("5. Count 'Inner Tens' (>= 10.4)");
            System.out.println("6. End Match & Exit");
            System.out.print("Select an option (1-6): ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        if (currentSession.getShots().size() >= 60) {
                            System.out.println("Match complete! 60 shots reached.");
                            break;
                        }
                        System.out.print("Enter score for Shot " + shotCounter + " (0.0 to 10.9): ");
                        double score = scanner.nextDouble();
                        
                        // Basic validation before adding
                        if (score >= 0.0 && score <= 10.9) {
                            currentSession.addShot(new Shot(shotCounter, score));
                            System.out.println("Shot logged successfully.");
                            shotCounter++;
                        } else {
                            System.out.println("Invalid score. Must be between 0.0 and 10.9.");
                        }
                        break;
                        
                    case 2:
                        System.out.println("\n--- SHOT LOG ---");
                        if (currentSession.getShots().isEmpty()) {
                            System.out.println("No shots logged yet.");
                        } else {
                            for (Shot s : currentSession.getShots()) {
                                System.out.println(s);
                            }
                        }
                        break;
                        
                    case 3:
                        double total = engine.calculateTotalScore(currentSession);
                        // Using String.format to keep it to 1 decimal place
                        System.out.println("Total Match Score: " + String.format("%.1f", total));
                        break;
                        
                    case 4:
                        double average = engine.calculateAverage(currentSession);
                        System.out.println("Current Average: " + String.format("%.2f", average));
                        break;
                        
                    case 5:
                        long innerTens = engine.countInnerTens(currentSession);
                        System.out.println("Total Inner Tens: " + innerTens);
                        break;
                        
                    case 6:
                        running = false;
                        System.out.println("Saving session data...");
                        System.out.println(currentSession);
                        System.out.println("Exiting TenRing Analytics...");
                        break;
                        
                    default:
                        System.out.println("Invalid option. Please select 1-6.");
                }
            } catch (InputMismatchException e) {
                // This catches the error if a user types a letter instead of a number!
                System.out.println("\n[ERROR] Invalid input detected. Please enter numbers only.");
                scanner.nextLine(); // This clears the bad input from the scanner buffer
            }
        }
        
        scanner.close(); // Always close your resources!
    }
}