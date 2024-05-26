package org.example;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.registerStudent(this);
        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);
        return true;

    }

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = "S" + String.format("%05d", nextId++);
        this.registeredCourses = new ArrayList<>();
    }


    public String toSimplifiedString() {
        return  "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", departmentName= '" + department.getDepartmentName() + '\'' +
                '}';
    }

    @Override
    public String toString() {
        String courses = "";
        for (Course course : registeredCourses) {
            courses = course.toSimplifiedString() + "\n";
        }
        return String.format("Student:\n" +
                "Student ID: S%05d\n" +
                "Student Name: %s\n" +
                "Gender: %s" +
                "Address: %s" +
                "Department: %s" +
                "Registered Courses: %s", nextId++, studentName, gender, address, department, courses);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }
}
