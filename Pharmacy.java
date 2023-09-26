
package com.mycompany.oopprojectpharmacy;


import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Pharmacy implements Serializable {

    private String name;
    private int ID;
    private String logo;
    private String address;

    public Pharmacy() {
        name = "null";
        ID = 0;
        logo = "null";
        address = "null";
    }

    public Pharmacy(String name, int ID, String logo, String address) {
        this.name = name;
        this.ID = ID;
        this.logo = logo;
        this.address = address;
    }

    public void display() {
        System.out.println("Pharmacy Name:  Green Pharmacy");
        System.out.println("Pharmacy ID: 1774");
//        System.out.println("Pharmacy Logo: " + logo);
        System.out.println("Pharmacy Address: Islababad");
        System.out.println("Contact no.     03455992638     03135365527");

    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PharmacyData.txt", true))) {
            oos.writeObject(this);
            System.out.println("Pharmacy data saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving pharmacy data to file.");
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PharmacyData.txt"))) {
            Pharmacy pharmacy = (Pharmacy) ois.readObject();
            pharmacy.display();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading pharmacy data from file.");
            e.printStackTrace();
        }
    }
}