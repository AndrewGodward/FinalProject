package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    private int streetNo;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;


    /**
     * It checks if the postal code is valid or not, if it has a length of 6 it must follow CDCDCD or if its length is
     * 7 it must follow CDC DCD and if it's not valid it returns null
     * @param postalCode inputs a String
     * @return either true or false
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null) return  false;

        postalCode = postalCode.toUpperCase();
        int length = postalCode.length();

        if (length != 6 && length != 7) return false;

        if (length == 6) {
            for (int i = 0; i < length; i++) {
                char c = postalCode.charAt(i);
                if (i % 2 == 0) {
                    if (c < 'A' || c > 'Z') {
                        return false;
                    }
                } else {
                    if (c < '0' || c > '9') {
                        return false;
                    }
                }
            }
        } else {
            if (postalCode.charAt(3) != ' ') {
                return false;
            }
            for (int i = 0; i < length; i++) {
                char c = postalCode.charAt(i);
                if (i == 3) {
                    continue;
                }
                if (i < 3) {
                    if (i % 2 == 0) {
                        if (c < 'A' || c > 'Z') {
                            return false;
                        }
                    } else {
                        if (c < '0' || c > '9') {
                            return false;
                        }
                    }
                } else {
                    if (i % 2 != 0) {
                        if (c < 'A' || c > 'Z') {
                            return false;
                        }
                    } else {
                        if (c < '0' || c > '9') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public Address(int streetNo, String street, String city, String province, String postalCode, String country) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode;
            this.country = country;
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
            this.country = null;
        }
    }
}
