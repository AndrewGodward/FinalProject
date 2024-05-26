package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Department {

    private String departmentId; //2-digits starts with a character D
    private String departmentName;
    private static int nextId = 1; //indicates the next ID that will be used

    /**
     * checks if the department name is valid by making sure it does not contain numbers if it does then it will return
     * false if it contains only space and letters it will return true
     * @param departmentName inputs a String such as the department name
     * @return either true or false
     */
    public static boolean validateDepartmentName(String departmentName) {
        if (departmentName == null) {
            return false;
        }
        for (int i = 0; i < departmentName.length(); i++) {
            char c = departmentName.charAt(i);
            if (!(('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * if the departmentName is invalid it will create the object with everything set as null
     * @param departmentName inputs String departmentName
     * @param departmentId inputs String departmentId
     */
    public Department(String departmentName, String departmentId) {
        if (validateDepartmentName(departmentName)) {
            this.departmentName = departmentName;
            this.departmentId = "D" + String.format("0%d", nextId++);
        } else {
            this.departmentName = null;
            this.departmentId = null;
        }
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Department.nextId = nextId;
    }
}
