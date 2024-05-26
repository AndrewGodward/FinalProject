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

    /**
     * checks if the sum of the weights of the assignments of the course equal to 1(100%)
     * @return true if 1, false if it is not equal to 1
     */
    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0;
    }

    /**
     * adds a student to the student list of the course, which also adds a new null element to each of the assignments
     * of this course and adds a new null element for the finalScores
     * @param student inputs the Student class
     * @return true when the student is registered
     */
    public boolean registerStudent(Student student) {
        registeredStudents.add(student);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }
        finalScores.add(null);
        return true;
    }

    /**
     * calculates the weighted average of a student
     * @return weighted average
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double weightedSum = 0;
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    weightedSum += score * assignment.getWeight();
                }
            }
            averages[i] = (int) Math.round(weightedSum);
        }
        return averages;
    }

    /**
     * adds an assignment to the course
     * @param assignmentName inputs the String assignmentName
     * @param weight inputs the double weight
     * @param maxScore inputs the int maxScore
     * @return true when the assignment has been added
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment assignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(assignment);
        return true;
    }

    /**
     * generates a random score for each assignment and student then it calculates the score for each student
     */
    public void generateScore() {
        for (Assignment assignment : assignments) {
            assignment.getScores().clear();
            for (int i = 0; i < registeredStudents.size(); i++) {
                assignment.generateRandomScore();
            }
        }
        int[] finalAverages = calcStudentsAverage();
        for (int i = 0; i < finalAverages.length; i++) {
            finalScores.set(i, (double) finalAverages[i]);
        }
    }

    /**
     * displays the scores of the course in a table with the assignment averages and the students weighted averages
     */
    public void displayScores() {
        System.out.println("Course: " + courseName + " (C-" + department.getDepartmentId() + "-" + courseId + ")");
        System.out.println("                ");
        for (Assignment assignment : assignments) {
            System.out.printf("  %s\t", assignment.getAssignmentName());
        }
        System.out.printf("%s\t\t\n", "Final Score");
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%s\t\t", student.getStudentName());
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.printf("%d\t\t", score);
            }
            System.out.printf(" \t\t%.0f\n", finalScores.get(i));
        }
        System.out.printf("%s\t\t", "Average");
        for (Assignment assignment : assignments) {
            assignment.calcAssignmentAvg();
            System.out.printf("%.0f\t\t", assignment.getAssignmentAverage());
        }
        System.out.println();
    }

    /**
     * coverts a course to a simple string of course id, course name, credits and department only
     * @return converted strings of course
     */
    public String toSimplifiedString() {
        return String.format("Course: " +
                "Course ID: C-%s-%s\n"+
                "Course name: %s\n" +
                "Credits: %f\n" +
                "Department: %s", courseName, department.getDepartmentId(), courseId, credits,
                department);
    }

    /**
     * converts the course to a string that has courseId, courseName, credits, departmentName,
     * assignments, and registeredStudents
     * @return the converted String
     */
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

    public Course(String courseId, String courseName, double credits, Department department,
                  ArrayList<Assignment> assignments, ArrayList<Student> registeredStudents,
                  ArrayList<Double> finalScores) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = assignments;
        this.registeredStudents = registeredStudents;
        this.finalScores = finalScores;
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
