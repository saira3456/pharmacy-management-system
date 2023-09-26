package com.mycompany.oopprojectpharmacy;

public class MainGui {

    public static void main(String[] args) {
        Person P = new Person();
        PharmacyGui pharmacyGui = new PharmacyGui();
        PersonGui PG = new PersonGui();
        PG.Runner();
    }
}