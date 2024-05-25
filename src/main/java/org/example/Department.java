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

    public Department(String departmentName) {
        if (validateDepartmentName(departmentName)) {
            this.departmentName = departmentName;
            this.departmentId = "D" + String.format("0%d", nextId++);
        } else {
            this.departmentName = null;
            this.departmentId = null;
        }
    }
}
