package com.mycompany.oopprojectpharmacy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 *
 * @author HP
 */
public class Person implements Serializable {

    String name;
    int age;
    String phoneNo;
    String gender;
    String CNIC;

    public Person() {
        name = "null";
        age = 0;
        phoneNo = "null";
        gender = "null";
        CNIC = "null";
    }

    public Person(String name, int age, String phoneNo, String gender, String CNIC) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.CNIC = CNIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void showChoice() {
        int choice;
        System.out.println("1. admin");
        System.out.println("2. customer");
    }

    public static boolean isValidUsername(String name) {
        String regex = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean isValidAge(int age) {
        if (age <= 0 || age >= 105) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidNumber(String number) {
        if (number.length() != 11) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidGender(String gender) {
        return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"));
    }

    public static boolean isValidCNIC(String CNIC) {
        if (((CNIC.length() == 15) && (Character.isDigit(CNIC.charAt(0)))
                && (Character.isDigit(CNIC.charAt(1))) && (Character.isDigit(CNIC.charAt(2)))
                && (Character.isDigit(CNIC.charAt(3))) && (Character.isDigit(CNIC.charAt(4)))
                && (CNIC.charAt(5) == '-') && (Character.isDigit(CNIC.charAt(6)))
                && (Character.isDigit(CNIC.charAt(7))) && (Character.isDigit(CNIC.charAt(8))) && (Character.isDigit(CNIC.charAt(9))) && (Character.isDigit(CNIC.charAt(10)))
                && (Character.isDigit(CNIC.charAt(11))) && (Character.isDigit(CNIC.charAt(12))) && (CNIC.charAt(13) == '-') && (Character.isDigit(CNIC.charAt(14))))) {
            return true;
        }
        return false;

    }

    void display() {
        System.out.println("\nName : " + name + "\nAge : " + age + "\nPhone No : " + phoneNo + "\nGender : " + gender + "\nCNIC # : " + CNIC);
    }
}
