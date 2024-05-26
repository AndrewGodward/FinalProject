package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter

public class Course {
    private String courseId; //C-departmentId-twoDigitCourseId
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Double> finalScores;
    private static int nextId;

    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0;
    }

    public boolean registerStudent(Student student) {
        registeredStudents.add(student);
        assignments.add(null);
        finalScores.add(null);
        return true;
    }

    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double weightedSum = 0;
            for (Assignment assignment : assignments) {
                int score = assignment.getScores().get(i);
                if (score >= 0) {
                    weightedSum += score * assignment.getWeight();
                }
            }
            averages[i] = (int) Math.round(weightedSum);
        }
        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment assignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(assignment);
        return true;
    }

    public void generateScore() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }
        int[] averages = calcStudentsAverage();
        for (int i = 0; i < averages.length; i++) {
            finalScores.set(i, (double) averages[i]);
        }
    }

    public void displayScores() {
        System.out.println("Course: " + courseName + " (C-" + department.getDepartmentId() + "-" + courseId + ")");
        System.out.println("                ");
        for (Assignment assignment : assignments) {
            System.out.printf("%s\t", assignment.getAssignmentName());
        }
        System.out.printf("%s\t\t\n", "Final Score");
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%s\t\t", student.getStudentName());
            generateScore();
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.printf("%d\t\t", score);
            }
            System.out.printf("%.2f\t\t\n", finalScores.get(i));
        }
        System.out.printf("%s\t\t", "Average");
        for (Assignment assignment : assignments) {
            assignment.calcAssignment();
            System.out.printf("%.2f\t\t", assignment.getAssignmentAverage());
        }
        System.out.println();
    }

    public String toSimplifiedString() {
        return String.format("Course: " +
                "Course ID: C-%s-%s\n"+
                "Course name: %s\n" +
                "Credits: %f\n" +
                "Department: %s", courseName, department.getDepartmentId(), courseId, credits,
                department);
    }

    @Override
    public String toString() {
        String students = "";
        for (Student student : registeredStudents) {
            students = student.toSimplifiedString() + "\n";
        }
        return String.format("Course: " +
                        "Course name: %s\n" +
                        "Course ID: C-%s-%s\n" +
                        "Department: %s\n" +
                        "Assignment %s\n" +
                        "Registered students: %s",
                courseName, department.getDepartmentId(), courseId, department.getDepartmentName(),
                assignments, students);


    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public ArrayList<Double> getFinalScores() {
        return finalScores;
    }

    public void setFinalScores(ArrayList<Double> finalScores) {
        this.finalScores = finalScores;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Course.nextId = nextId;
    }


}
