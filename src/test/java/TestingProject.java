
import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestingProject {
@Test
    public void testCalcStudentAv1() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment("Assignment1", 0.2, 100));
        assignments.add(new Assignment("Assignment2", 0.8, 100));
        Department department = new Department("D01", "Computer Science");
        Course course = new Course("01", "Programming", 4, department,
            assignments, new ArrayList<Student>(), new ArrayList<Double>());
        Student student = new Student("James Bond", Gender.MALE, new Address(007, "Spider Street",
            "Kingston", "Ontario", "H1R 3J7", "Canada"), department);
        student.registerCourse(course);
        assignments.getFirst().setScores(new ArrayList<>(List.of(17)));
        assignments.get(1).setScores(new ArrayList<>(List.of(69)));

        int[] averages = course.calcStudentsAverage();
        assertEquals(59, averages[0]);
}

    @Test
    public void testCalcStudentAv2() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment("Assignment1", 0.1, 100));
        assignments.add(new Assignment("Assignment2", 0.9, 100));
        Department department = new Department("D01", "Computer Science");
        Course course = new Course("01", "Programming", 4, department,
                assignments, new ArrayList<Student>(), new ArrayList<Double>());
        Student student = new Student("James Bond", Gender.MALE, new Address(007, "Spider Street",
                "Kingston", "Ontario", "H1R 3J7", "Canada"), department);
        student.registerCourse(course);
        assignments.getFirst().setScores(new ArrayList<>(List.of(69)));
        assignments.get(1).setScores(new ArrayList<>(List.of(69)));

        int[] averages = course.calcStudentsAverage();
        assertEquals(69, averages[0]);
    }

    @Test
    public void testCalcAssignmentAvg1() {
        Assignment assignment = new Assignment("Assignment1", 0.0, 100);
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(1);
        scores.add(10);
        scores.add(4);
        scores.add(6);
        assignment.setScores(scores);
        assignment.calcAssignmentAvg();
        assertEquals(5.25, assignment.getAssignmentAverage(), 0.01);
    }
    @Test
    public void testClcAssignmentAvg2() {
        Assignment assignment = new Assignment("Assignment2", 0.5, 100);
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(10);
        scores.add(10);
        scores.add(10);
        scores.add(10);
        assignment.setScores(scores);
        assignment.calcAssignmentAvg();
        assertEquals(10.0, assignment.getAssignmentAverage(), 0.01);
    }

    @Test
    public void testIsAssignmentsTotalWeightValid1() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    Department department = new Department("Computer Science", "D02");
    assignments.add(new Assignment("Assignment1", 0.2, 100));
    assignments.add(new Assignment("Assignment2", 0.8, 100));
    Course course = new Course("01", "Programming", 5, department, assignments ,
            new ArrayList<Student>(), new ArrayList<Double>());
    assertTrue(course.isAssignmentWeightValid());
    }
    @Test
    public void testIsAssignmentsTotalWeightValid2() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    Department department = new Department("Computer Science", "D02");
    assignments.add(new Assignment("Assignment1", 0.2, 100));
    assignments.add(new Assignment("Assignment2", 0.9, 100));
    Course course = new Course("01", "Programming", 5, department, assignments ,
            new ArrayList<Student>(), new ArrayList<Double>());
    assertFalse(course.isAssignmentWeightValid());
    }

    @Test
    public void testIsPostalCodeValid() {
        assertTrue(Address.isPostalCodeValid("H1R4G7"));
        assertTrue(Address.isPostalCodeValid("H1R 4G7"));
        assertFalse(Address.isPostalCodeValid("412344"));
        assertFalse(Address.isPostalCodeValid("HRGYKK"));
        assertFalse(Address.isPostalCodeValid("H1R2G"));
        assertFalse(Address.isPostalCodeValid("H1 R4G7"));
        assertFalse(Address.isPostalCodeValid("1H4R7G"));

    }
    @Test
    public void testToTitleCase() {
        assertEquals("Andrew Godward", Util.toTitleCase("andrew godward"));
        assertEquals("Super Mario", Util.toTitleCase("super mario"));
        assertEquals("Andrew", Util.toTitleCase("ANDREW"));
        assertEquals("Andrew Godward", Util.toTitleCase("andrew GODWARD"));
        assertEquals("Fitbit", Util.toTitleCase("FitBIT"));
    }

}
