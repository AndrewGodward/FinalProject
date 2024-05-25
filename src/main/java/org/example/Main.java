package org.example;

public class Main {
    public static void main(String[] args) {

        Address address = new Address(1111, "1st Street", "Montreal", "Quebec",
                "H1R 2G6", "Canada");

        System.out.println(address);

        Department department = new Department("Andrew Andrew");
        Department department1 = new Department("Andrew Godward");
        System.out.println(department);
        System.out.println(department1);


    }
}