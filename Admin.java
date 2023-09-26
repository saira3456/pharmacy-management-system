package com.mycompany.oopprojectpharmacy;

import java.util.*;
import java.io.*;

public class Admin extends Person implements Serializable {

    final String password;
    int option;

    public Admin() {
        this.password = "admin@123";
        this.option = 0;
    }

    public Admin(String name, int age, String phoneNo, String gender, String CNIC, String password, int option) {
        super(name, age, phoneNo, gender, CNIC);
        this.password = password;
        this.option = option;
    }

    public String getPassword() {
        return password;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    boolean isValidPassword(String pass) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (count <= 3) {
            System.out.println("Enter password here:");
            pass = input.nextLine();
            String password = "admin@123";
            if (pass.equals(password)) {
                return true;
            } else {
                count++;
            }
        }
        return false;
    }

    public void writeToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("admin.txt",true))) {
            outputStream.writeObject(this.password);
            System.out.println("Admin data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing admin data to file: " + e.getMessage());
        }
    }

    @Override
    public void display() {
        
    }
}