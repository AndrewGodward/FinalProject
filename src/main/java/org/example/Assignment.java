package org.example;

import lombok.*;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
@AllArgsConstructor
public class Assignment {

    private String assignmentId;
    private String assignmentName;
    private double weight;
    private int maxScore;
    private double assignmentAverage;
    private ArrayList<Integer> scores;
    private static int nextId = 1;

    /**
     * it calculates the average score for one assignment
     */
    public void calcAssignment() {
        if (scores.isEmpty()) {
            this.assignmentAverage = 0;
            return;
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        this.assignmentAverage = (double) sum / scores.size();
    }

    /**
     * generates random scores for all students in an assignment, it generates a number from [0, 10] then if number 0
     * score equals [0, 60), 1 and 2 score equals [60, 70), 3 and 4 score equals [70, 80), 5, 6, 7 and 8 score equals
     * [80, 90) lastly 9 and 10 score equals [90, 100]
     */
    public void generateRandomScore() {
        Random random = new Random();
        int randomRange = random.nextInt(0, 11);
        int score = 0;
        score = switch (randomRange) {
            case 0 -> random.nextInt(0, 60);
            case 1, 2 -> random.nextInt(60, 70);
            case 3, 4 -> random.nextInt(70, 80);
            case 5, 6, 7, 8 -> random.nextInt(80, 90);
            default -> random.nextInt(90, 101);
        };
        System.out.println(score);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                ", maxScore=" + maxScore +
                '}';
    }
}
